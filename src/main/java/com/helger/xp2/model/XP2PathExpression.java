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
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;

public class XP2PathExpression extends AbstractXP2ValueExpression
{
  private final EXP2PathOperator m_eOperator;
  private final AbstractXP2Expression m_aExpr;

  public XP2PathExpression (@Nonnull final EXP2PathOperator eOperator, @Nullable final AbstractXP2Expression aExpr)
  {
    m_eOperator = eOperator;
    m_aExpr = aExpr;
  }

  @Nonnull
  public EXP2PathOperator getOperator ()
  {
    return m_eOperator;
  }

  @Nullable
  public AbstractXP2Expression getExpression ()
  {
    return m_aExpr;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("operator", m_eOperator)
                                       .appendIfNotNull ("expression", m_aExpr)
                                       .toString ();
  }
}
