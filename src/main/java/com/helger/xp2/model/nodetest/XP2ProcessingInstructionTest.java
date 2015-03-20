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
package com.helger.xp2.model.nodetest;

import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;

public class XP2ProcessingInstructionTest extends AbstractXP2KindTest
{
  private final String m_sPITarget;

  public XP2ProcessingInstructionTest (@Nullable final String sPITarget)
  {
    m_sPITarget = sPITarget;
  }

  @Nullable
  public String getPITarget ()
  {
    return m_sPITarget;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("PITarget", m_sPITarget).toString ();
  }
}
