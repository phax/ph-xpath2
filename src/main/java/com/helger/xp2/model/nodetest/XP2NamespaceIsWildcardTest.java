package com.helger.xp2.model.nodetest;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * Wildcard test where namespace URL can be anything but local name is fixed.
 *
 * @author Philip Helger
 */
public class XP2NamespaceIsWildcardTest extends AbstractXP2NameTest
{
  private final String m_sLocalName;

  public XP2NamespaceIsWildcardTest (@Nonnull @Nonempty final String sLocalName)
  {
    ValueEnforcer.notEmpty (sLocalName, "LocalName");
    m_sLocalName = sLocalName;
  }

  @Nonnull
  @Nonempty
  public String getLocalName ()
  {
    return m_sLocalName;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("localName", m_sLocalName).toString ();
  }
}
