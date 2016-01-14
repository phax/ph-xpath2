/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.xp2.reader;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.charset.CCharset;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.io.IHasInputStream;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.io.streamprovider.StringInputStreamProvider;
import com.helger.xp2.model.XP2;
import com.helger.xp2.parser.CharStream;
import com.helger.xp2.parser.ParseException;
import com.helger.xp2.parser.ParserXP2;
import com.helger.xp2.parser.ParserXP2Constants;
import com.helger.xp2.parser.ParserXP2TokenManager;
import com.helger.xp2.parser.Token;
import com.helger.xp2.parser.XP2CharStream;
import com.helger.xp2.parser.XP2Node;

/**
 * This is the central user class for reading and parsing XPath2 from different
 * sources.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class XP2Reader
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (XP2Reader.class);
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final XP2Reader s_aInstance = new XP2Reader ();

  private XP2Reader ()
  {}

  @Nonnull
  @Nonempty
  public static String createLoggingStringParseError (@Nonnull final ParseException ex)
  {
    if (ex.currentToken == null)
    {
      // Is null if the constructor with String only was used
      return ex.getMessage ();
    }
    return createLoggingStringParseError (ex.currentToken, ex.expectedTokenSequences, ex.tokenImage, null);
  }

  @Nonnull
  @Nonempty
  public static String createLoggingStringParseError (@Nonnull final Token aLastValidToken,
                                                      @Nonnull final int [] [] aExpectedTokenSequencesVal,
                                                      @Nonnull final String [] aTokenImageVal,
                                                      @Nullable final Token aLastSkippedToken)
  {
    ValueEnforcer.notNull (aLastValidToken, "LastValidToken");
    ValueEnforcer.notNull (aExpectedTokenSequencesVal, "ExpectedTokenSequencesVal");
    ValueEnforcer.notNull (aTokenImageVal, "TokenImageVal");

    final StringBuilder aExpected = new StringBuilder ();
    int nMaxSize = 0;
    for (final int [] aExpectedTokens : aExpectedTokenSequencesVal)
    {
      if (nMaxSize < aExpectedTokens.length)
        nMaxSize = aExpectedTokens.length;

      if (aExpected.length () > 0)
        aExpected.append (',');
      for (final int nExpectedToken : aExpectedTokens)
        aExpected.append (' ').append (aTokenImageVal[nExpectedToken]);
    }

    final StringBuilder retval = new StringBuilder (1024);
    retval.append ('[')
          .append (aLastValidToken.next.beginLine)
          .append (':')
          .append (aLastValidToken.next.beginColumn)
          .append (']');
    if (aLastSkippedToken != null)
    {
      retval.append ("-[")
            .append (aLastSkippedToken.endLine)
            .append (':')
            .append (aLastSkippedToken.endColumn)
            .append (']');
    }
    retval.append (" Encountered");
    Token aCurToken = aLastValidToken.next;
    for (int i = 0; i < nMaxSize; i++)
    {
      retval.append (' ');
      if (aCurToken.kind == ParserXP2Constants.EOF)
      {
        retval.append (aTokenImageVal[ParserXP2Constants.EOF]);
        break;
      }
      retval.append ("text '")
            .append (aCurToken.image)
            .append ("' corresponding to token ")
            .append (aTokenImageVal[aCurToken.kind]);
      aCurToken = aCurToken.next;
    }
    retval.append (". ");
    if (aLastSkippedToken != null)
      retval.append ("Skipped until token ").append (aLastSkippedToken).append (". ");
    retval.append (aExpectedTokenSequencesVal.length == 1 ? "Was expecting:" : "Was expecting one of:")
          .append (aExpected);
    return retval.toString ();
  }

  /**
   * Main reading of the CSS
   *
   * @param aCharStream
   *        The stream to read from. May not be <code>null</code>.
   * @param eVersion
   *        The CSS version to use. May not be <code>null</code>.
   * @param aCustomErrorHandler
   *        A custom handler for recoverable errors. May be <code>null</code>.
   * @param aCustomExceptionHandler
   *        A custom handler for unrecoverable errors. May not be
   *        <code>null</code>.
   * @return <code>null</code> if parsing failed with an unrecoverable error
   *         (and no throwing exception handler is used), or <code>null</code>
   *         if a recoverable error occurred and no
   *         {@link com.helger.xpath.reader.errorhandler.ThrowingCSSParseErrorHandler}
   *         was used or non-<code>null</code> if parsing succeeded.
   */
  @Nullable
  private static XP2Node _readXPath (@Nonnull final CharStream aCharStream)
  {
    final ParserXP2TokenManager aTokenHdl = new ParserXP2TokenManager (aCharStream);
    final ParserXP2 aParser = new ParserXP2 (aTokenHdl);
    try
    {
      // Main parsing
      return aParser.xpath2 ();
    }
    catch (final ParseException ex)
    {
      // Unrecoverable error
      System.err.println (createLoggingStringParseError (ex));
      return null;
    }
  }

  @Nullable
  public static XP2 readFromString (@Nonnull final String sData)
  {
    return readFromStream (new StringInputStreamProvider (sData, CCharset.CHARSET_UTF_8_OBJ),
                           CCharset.CHARSET_UTF_8_OBJ);
  }

  @Nullable
  public static XP2 readFromStream (@Nonnull final IHasInputStream aISP, @Nonnull final Charset aCharset)
  {
    ValueEnforcer.notNull (aISP, "InputStreamProvider");
    ValueEnforcer.notNull (aCharset, "Charset");

    final InputStream aIS = aISP.getInputStream ();
    final Reader aReader = StreamHelper.createReader (aIS, aCharset);
    try
    {
      final XP2CharStream aCharStream = new XP2CharStream (aReader);

      final XP2Node aNode = _readXPath (aCharStream);

      // Failed to interpret content as CSS?
      if (aNode == null)
        return null;

      // Convert the AST to a domain object
      final XP2 ret = XP2NodeToDomainObject.convertToDomainObject (aNode);
      return ret;
    }
    finally
    {
      StreamHelper.close (aReader);
    }
  }
}
