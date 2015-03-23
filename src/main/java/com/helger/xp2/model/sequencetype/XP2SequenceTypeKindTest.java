/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
import com.helger.xp2.model.nodetest.IXP2KindTest;

public class XP2SequenceTypeKindTest extends AbstractXP2SequenceType
{
  private final IXP2KindTest m_aKindTest;

  public XP2SequenceTypeKindTest (@Nonnull final IXP2KindTest aKindTest,
                                  @Nonnull final EXP2OccurrenceIndicator eOccurrenceIndicator)
  {
    super (eOccurrenceIndicator);
    m_aKindTest = ValueEnforcer.notNull (aKindTest, "KindTest");
  }

  @Nonnull
  public IXP2KindTest getKindTest ()
  {
    return m_aKindTest;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    m_aKindTest.writeTo (aWriter);
    m_eOccurrenceIndicator.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("kindTest", m_aKindTest).toString ();
  }
}
