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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;

public class XP2BinaryExpression extends AbstractXP2Expression
{
  private final AbstractXP2Expression m_aLeft;
  private final EXP2Operator m_eOperator;
  private final AbstractXP2Expression m_aRight;

  public XP2BinaryExpression (@Nonnull final AbstractXP2Expression aLeft,
                              @Nonnull final EXP2Operator eOperator,
                              @Nonnull final AbstractXP2Expression aRight)
  {
    m_aLeft = ValueEnforcer.notNull (aLeft, "Left");
    m_eOperator = ValueEnforcer.notNull (eOperator, "Operator");
    m_aRight = ValueEnforcer.notNull (aRight, "Right");
  }

  @Nonnull
  public AbstractXP2Expression getLeft ()
  {
    return m_aLeft;
  }

  @Nonnull
  public EXP2Operator getOperator ()
  {
    return m_eOperator;
  }

  @Nonnull
  public AbstractXP2Expression getRight ()
  {
    return m_aRight;
  }

  @Override
  @Nonnull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("left", m_aLeft)
                                       .append ("operator", m_eOperator)
                                       .append ("right", m_aRight)
                                       .toString ();
  }
}
