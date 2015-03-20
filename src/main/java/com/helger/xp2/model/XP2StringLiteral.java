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
package com.helger.xp2.model;

import javax.annotation.Nonnull;

import com.helger.commons.string.ToStringGenerator;

public class XP2StringLiteral extends AbstractXP2LiteralExpression
{
  private final String m_sValue;

  public XP2StringLiteral (@Nonnull final String sValue)
  {
    m_sValue = sValue;
  }

  @Nonnull
  public String getValue ()
  {
    return m_sValue;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("value", m_sValue).toString ();
  }
}
