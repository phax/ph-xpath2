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

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Quantified expression.<br>
 * <code>("some" | "every") "$" VarName "in" ExprSingle ("," "$" VarName "in" ExprSingle)* "satisfies" ExprSingle</code>
 * 
 * @author Philip Helger
 */
public class XP2QuantifiedExpression extends AbstractXP2Expression
{
  private final EQuantifiedExpressionType m_eType;
  private final List <XP2VarNameAndExpression> m_aClauses;
  private final AbstractXP2Expression m_aSatisfyExpression;

  public XP2QuantifiedExpression (@Nonnull final EQuantifiedExpressionType eType,
                                  @Nonnull final List <XP2VarNameAndExpression> aClauses,
                                  @Nonnull final AbstractXP2Expression aSatisfyExpression)
  {
    m_eType = eType;
    m_aClauses = CollectionHelper.newList (aClauses);
    m_aSatisfyExpression = aSatisfyExpression;
  }

  @Nonnull
  public EQuantifiedExpressionType getType ()
  {
    return m_eType;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <XP2VarNameAndExpression> getAllClauses ()
  {
    return CollectionHelper.newList (m_aClauses);
  }

  @Nonnull
  public AbstractXP2Expression getSatisfyExpression ()
  {
    return m_aSatisfyExpression;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("type", m_eType)
                                       .append ("clauses", m_aClauses)
                                       .append ("satisfyExpression", m_aSatisfyExpression)
                                       .toString ();
  }

}
