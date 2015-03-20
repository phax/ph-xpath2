package com.helger.xp2.model.sequencetype;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

public class XP2SequenceTypeAtomicType extends AbstractXP2SequenceType
{
  private final ParserQName m_aTypeName;

  public XP2SequenceTypeAtomicType (@Nonnull final ParserQName aTypeName,
                                    @Nonnull final EXP2OccurrenceIndicator eOccurrenceIndicator)
  {
    super (eOccurrenceIndicator);
    ValueEnforcer.notNull (aTypeName, "TypeName");
    m_aTypeName = aTypeName;
  }

  @Nonnull
  public ParserQName getTypeName ()
  {
    return m_aTypeName;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("typeName", m_aTypeName).toString ();
  }
}
