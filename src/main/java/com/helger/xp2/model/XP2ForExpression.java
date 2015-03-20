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
 * For expression.<br>
 * <code>"for" "$" VarName "in" ExprSingle ("," "$" VarName "in" ExprSingle)* "return" ExprSingle</code>
 *
 * @author Philip Helger
 */
public class XP2ForExpression extends AbstractXP2Expression
{
  private final List <XP2VarNameAndExpression> m_aForClauses;
  private final AbstractXP2Expression m_aReturnExpression;

  public XP2ForExpression (@Nonnull final List <XP2VarNameAndExpression> aForClauses,
                           @Nonnull final AbstractXP2Expression aReturnExpression)
  {
    m_aForClauses = CollectionHelper.newList (aForClauses);
    m_aReturnExpression = aReturnExpression;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <XP2VarNameAndExpression> getAllForClauses ()
  {
    return CollectionHelper.newList (m_aForClauses);
  }

  @Nonnull
  public AbstractXP2Expression getReturnExpression ()
  {
    return m_aReturnExpression;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("forClauses", m_aForClauses)
                                       .append ("returnExpression", m_aReturnExpression)
                                       .toString ();
  }
}
