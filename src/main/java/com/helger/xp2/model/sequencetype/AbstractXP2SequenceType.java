package com.helger.xp2.model.sequencetype;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;

public class AbstractXP2SequenceType
{
  private final EXP2OccurrenceIndicator m_eOccurrenceIndicator;

  public AbstractXP2SequenceType (@Nonnull final EXP2OccurrenceIndicator eOccurrenceIndicator)
  {
    ValueEnforcer.notNull (eOccurrenceIndicator, "OccurrenceIndicator");
    m_eOccurrenceIndicator = eOccurrenceIndicator;
  }

  @Nonnull
  public EXP2OccurrenceIndicator getOccurrenceIndicator ()
  {
    return m_eOccurrenceIndicator;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("occurrenceIndicator", m_eOccurrenceIndicator).toString ();
  }
}
