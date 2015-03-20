package com.helger.xp2.model.kindtest;

import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

public class XP2AttributeTest extends AbstractXP2KindTest
{
  private final XP2AttributeNameOrWildcard m_aAttributeNameOrWildcard;
  private final ParserQName m_aTypeName;

  public XP2AttributeTest (@Nullable final XP2AttributeNameOrWildcard aAttributeNameOrWildcard,
                           @Nullable final ParserQName aTypeName)
  {
    m_aAttributeNameOrWildcard = aAttributeNameOrWildcard;
    m_aTypeName = aTypeName;
  }

  @Nullable
  public XP2AttributeNameOrWildcard getAttributeNameOrWildcard ()
  {
    return m_aAttributeNameOrWildcard;
  }

  @Nullable
  public ParserQName getTypeName ()
  {
    return m_aTypeName;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("attributeNameOrWildcard", m_aAttributeNameOrWildcard)
                                       .appendIfNotNull ("typeName", m_aTypeName)
                                       .toString ();
  }
}
