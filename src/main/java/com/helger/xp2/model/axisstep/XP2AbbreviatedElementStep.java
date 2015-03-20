package com.helger.xp2.model.axisstep;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.model.nodetest.AbstractXP2NodeTest;

public class XP2AbbreviatedElementStep extends AbstractXP2SingleStep
{
  private final AbstractXP2NodeTest m_aNodeTest;

  public XP2AbbreviatedElementStep (@Nonnull final AbstractXP2NodeTest aNodeTest)
  {
    m_aNodeTest = ValueEnforcer.notNull (aNodeTest, "NodeTest");
  }

  @Nonnull
  public AbstractXP2NodeTest getNodeTest ()
  {
    return m_aNodeTest;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("nodeTest", m_aNodeTest).toString ();
  }
}
