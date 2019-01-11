/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.xp2.model.sequencetype;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.xp2.model.IXP2Object;

/**
 * Occurrence indicators.
 *
 * @author Philip Helger
 */
public enum EXP2OccurrenceIndicator implements IXP2Object
{
  /** Element must occur 1 time */
  DEFAULT_ONCE (""),
  /** Element may occur 0 or 1 time */
  QUESTION_MARK ("?"),
  /** Element may occur 0 or multiple times */
  ASTERISK ("*"),
  /** Element must occur 1 or multiple times */
  PLUS ("+");

  private final String m_sText;

  private EXP2OccurrenceIndicator (@Nonnull final String sText)
  {
    m_sText = sText;
  }

  @Nonnull
  public String getText ()
  {
    return m_sText;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write (m_sText);
  }

  @Nonnull
  public static EXP2OccurrenceIndicator getFromTextOrThrow (@Nullable final String sText)
  {
    if (sText != null)
      for (final EXP2OccurrenceIndicator aElement : EXP2OccurrenceIndicator.class.getEnumConstants ())
        if (aElement.getText ().equals (sText))
          return aElement;
    throw new IllegalArgumentException ("Failed to resolve occurrence indicator '" + sText + "'");
  }
}
