/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.xp2.model.nodetest;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.model.IXP2Object;

public class XP2ProcessingInstructionTest extends AbstractXP2KindTest
{
  private final IXP2Object m_aPITarget;

  public XP2ProcessingInstructionTest ()
  {
    this (null);
  }

  public XP2ProcessingInstructionTest (@Nullable final IXP2Object aPITarget)
  {
    m_aPITarget = aPITarget;
  }

  @Nullable
  public IXP2Object getPITarget ()
  {
    return m_aPITarget;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("processing-instruction(");
    if (m_aPITarget != null)
      m_aPITarget.writeTo (aWriter);
    aWriter.write (')');
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("PITarget", m_aPITarget).toString ();
  }
}
