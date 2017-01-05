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

public class XP2DocumentTest extends AbstractXP2KindTest
{
  private final IXP2KindTest m_aKindTest;

  public XP2DocumentTest (@Nullable final IXP2KindTest aKindTest)
  {
    m_aKindTest = aKindTest;
  }

  @Nullable
  public IXP2KindTest getKindTest ()
  {
    return m_aKindTest;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("document-node(");
    if (m_aKindTest != null)
      m_aKindTest.writeTo (aWriter);
    aWriter.write (')');
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("kindTest", m_aKindTest).toString ();
  }
}
