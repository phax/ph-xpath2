package com.helger.xp2.model.sequencetype;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.model.kindtest.AbstractXP2KindTest;

public class XP2SequenceTypeKindTest extends AbstractXP2SequenceType
{
  private final AbstractXP2KindTest m_aKindTest;

  public XP2SequenceTypeKindTest (@Nonnull final AbstractXP2KindTest aKindTest,
                                  @Nonnull final EXP2OccurrenceIndicator eOccurrenceIndicator)
  {
    super (eOccurrenceIndicator);
    ValueEnforcer.notNull (aKindTest, "KindTest");
    m_aKindTest = aKindTest;
  }

  @Nonnull
  public AbstractXP2KindTest getKindTest ()
  {
    return m_aKindTest;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("kindTest", m_aKindTest).toString ();
  }
}
