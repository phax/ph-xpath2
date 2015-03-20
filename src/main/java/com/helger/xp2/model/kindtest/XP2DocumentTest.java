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
package com.helger.xp2.model.kindtest;

import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;

public class XP2DocumentTest extends AbstractXP2KindTest
{
  private final AbstractXP2KindTest m_aKindTest;

  public XP2DocumentTest (@Nullable final AbstractXP2KindTest aKindTest)
  {
    m_aKindTest = aKindTest;
  }

  @Nullable
  public AbstractXP2KindTest getKindTest ()
  {
    return m_aKindTest;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("kindTest", m_aKindTest).toString ();
  }
}
