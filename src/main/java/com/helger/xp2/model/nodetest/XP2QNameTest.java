package com.helger.xp2.model.nodetest;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

/**
 * Fixed namespace URL (maybe null) and fixed local name.
 *
 * @author Philip Helger
 */
public class XP2QNameTest extends AbstractXP2NameTest
{
  private final ParserQName m_aQName;

  public XP2QNameTest (@Nonnull final ParserQName aQName)
  {
    ValueEnforcer.notNull (aQName, "QName");
    m_aQName = aQName;
  }

  @Nonnull
  public ParserQName getQName ()
  {
    return m_aQName;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("QName", m_aQName).toString ();
  }
}
