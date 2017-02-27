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
package com.helger.xp2.model;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.ToStringGenerator;

/**
 * Quantified expression.<br>
 * <code>("some" | "every") "$" VarName "in" ExprSingle ("," "$" VarName "in" ExprSingle)* "satisfies" ExprSingle</code>
 *
 * @author Philip Helger
 */
public class XP2QuantifiedExpression extends AbstractXP2Expression
{
  private final EXP2QuantifiedExpressionType m_eType;
  private final ICommonsList <XP2VarNameAndExpression> m_aClauses;
  private final IXP2Expression m_aSatisfyExpression;

  public XP2QuantifiedExpression (@Nonnull final EXP2QuantifiedExpressionType eType,
                                  @Nonnull final Iterable <? extends XP2VarNameAndExpression> aClauses,
                                  @Nonnull final IXP2Expression aSatisfyExpression)
  {
    ValueEnforcer.notNull (aClauses, "Clauses");
    m_eType = ValueEnforcer.notNull (eType, "Type");
    m_aClauses = new CommonsArrayList<> (aClauses);
    m_aSatisfyExpression = ValueEnforcer.notNull (aSatisfyExpression, "SatisfyExpression");
  }

  @Nonnull
  public EXP2QuantifiedExpressionType getType ()
  {
    return m_eType;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <XP2VarNameAndExpression> getAllClauses ()
  {
    return m_aClauses.getClone ();
  }

  @Nonnull
  public IXP2Expression getSatisfyExpression ()
  {
    return m_aSatisfyExpression;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    m_eType.writeTo (aWriter);
    boolean bFirst = true;
    for (final XP2VarNameAndExpression aClause : m_aClauses)
    {
      if (bFirst)
        bFirst = false;
      else
        aWriter.write (", ");
      aClause.writeTo (aWriter);
    }
    aWriter.write (" satisfies ");
    m_aSatisfyExpression.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("type", m_eType)
                                       .append ("clauses", m_aClauses)
                                       .append ("satisfyExpression", m_aSatisfyExpression)
                                       .getToString ();
  }

}
