package com.helger.xp2.model.nodetest;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * Wildcard test where the local name can be anything but the namespace URL is
 * fixed.
 *
 * @author Philip Helger
 */
public class XP2LocalNameIsWildcardTest extends AbstractXP2NameTest
{
  private final String m_sNamespaceURL;

  public XP2LocalNameIsWildcardTest (@Nonnull @Nonempty final String sNamespaceURL)
  {
    ValueEnforcer.notEmpty (sNamespaceURL, "NamespaceURL");
    m_sNamespaceURL = sNamespaceURL;
  }

  @Nonnull
  @Nonempty
  public String getNamespaceURL ()
  {
    return m_sNamespaceURL;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("namespaceURL", m_sNamespaceURL).toString ();
  }
}
