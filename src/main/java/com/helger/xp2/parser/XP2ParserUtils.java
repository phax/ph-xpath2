package com.helger.xp2.parser;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;

@Immutable
public final class XP2ParserUtils
{
  private XP2ParserUtils ()
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
