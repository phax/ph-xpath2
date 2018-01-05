/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.xp2.parser;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;

@Immutable
public final class XP2ParserHelper
{
  private XP2ParserHelper ()
  {}

  @Nonnull
  public static String getUnescapedStringDQ (@Nonnull final String s)
  {
    final String sText = s.substring (1, s.length () - 1);
    final int i = sText.indexOf ("\"\"");
    if (i < 0)
      return sText;

    return StringHelper.replaceAll (sText, "\"\"", "\"");
  }

  @Nonnull
  public static String getUnescapedStringSQ (@Nonnull final String s)
  {
    final String sText = s.substring (1, s.length () - 1);
    final int i = sText.indexOf ("''");
    if (i < 0)
      return sText;

    return StringHelper.replaceAll (sText, "''", "'");
  }
}
