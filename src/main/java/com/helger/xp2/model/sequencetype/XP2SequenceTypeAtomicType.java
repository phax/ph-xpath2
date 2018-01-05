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
package com.helger.xp2.model.sequencetype;

import java.io.IOException;
import java.io.Writer;

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
    m_aTypeName = ValueEnforcer.notNull (aTypeName, "TypeName");
  }

  @Nonnull
  public ParserQName getTypeName ()
  {
    return m_aTypeName;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write (m_aTypeName.getAsString ());
    m_eOccurrenceIndicator.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("typeName", m_aTypeName).getToString ();
  }
}
