/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.xp2.XP2SourceLocation;
import com.helger.xp2.parser.XP2Node;

/**
 * Runtime exception that is thrown if interpreting the XPath fails. Only thrown
 * from classes in this package.
 *
 * @author Philip Helger
 */
public class XP2HandlingException extends RuntimeException
{
  private final XP2Node m_aNode;

  @Nonnull
  private static String _getSourceLocation (@Nonnull final XP2Node aNode)
  {
    final StringBuilder aRet = new StringBuilder ();
    final XP2SourceLocation aSL = aNode.getSourceLocation ();
    if (aSL != null)
    {
      String sFirstTokenLocation = null;
      if (aSL.hasFirstTokenArea ())
      {
        sFirstTokenLocation = aSL.getFirstTokenLocationAsString ();
        aRet.append (sFirstTokenLocation);
      }
      if (aSL.hasLastTokenArea ())
      {
        final String sLastTokenLocation = aSL.getLastTokenLocationAsString ();
        if (sFirstTokenLocation == null || !sFirstTokenLocation.equals (sLastTokenLocation))
        {
          if (aRet.length () > 0)
            aRet.append ('-');
          aRet.append (sLastTokenLocation);
        }
      }
      if (aRet.length () > 0)
        aRet.append (' ');
    }
    return aRet.toString ();
  }

  public XP2HandlingException (@Nonnull final XP2Node aNode, @Nonnull final String sMessage)
  {
    super (_getSourceLocation (aNode) + sMessage);
    m_aNode = aNode;
  }

  /**
   * @return The source node where the error occurred. Never <code>null</code>.
   */
  @Nonnull
  public XP2Node getNode ()
  {
    return m_aNode;
  }
}
