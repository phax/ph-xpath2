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

/**
 * If expression.<br>
 * <code>"if" "(" Expr ")" "then" ExprSingle "else" ExprSingle</code>
 *
 * @author Philip Helger
 */
public class XP2IfExpression extends AbstractXP2Expression
{
  private final XP2ExpressionList m_aTestExprs;
  private final AbstractXP2Expression m_aThenExpr;
  private final AbstractXP2Expression m_aElseExpr;

  public XP2IfExpression (@Nonnull final XP2ExpressionList aTestExprs,
                          @Nonnull final AbstractXP2Expression aThenExpr,
                          @Nonnull final AbstractXP2Expression aElseExpr)
  {
    m_aTestExprs = aTestExprs;
    m_aThenExpr = aThenExpr;
    m_aElseExpr = aElseExpr;
  }

  @Nonnull
  public XP2ExpressionList getTestExpressionList ()
  {
    return m_aTestExprs;
  }

  @Nonnull
  public AbstractXP2Expression getThenExpression ()
  {
    return m_aThenExpr;
  }

  @Nonnull
  public AbstractXP2Expression getElseExpression ()
  {
    return m_aElseExpr;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("testExprs", m_aTestExprs)
                                       .append ("thenExpr", m_aThenExpr)
                                       .append ("elseExpr", m_aElseExpr)
                                       .toString ();
  }
}
