/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
 * For expression.<br>
 * <code>"for" "$" VarName "in" ExprSingle ("," "$" VarName "in" ExprSingle)* "return" ExprSingle</code>
 *
 * @author Philip Helger
 */
public class XP2ForExpression extends AbstractXP2Expression
{
  private final ICommonsList <XP2VarNameAndExpression> m_aForClauses;
  private final IXP2Expression m_aReturnExpression;

  public XP2ForExpression (@Nonnull final Iterable <? extends XP2VarNameAndExpression> aForClauses,
                           @Nonnull final IXP2Expression aReturnExpression)
  {
    ValueEnforcer.notNull (aForClauses, "ForClauses");
    ValueEnforcer.notNull (aReturnExpression, "ReturnExpression");
    m_aForClauses = new CommonsArrayList <> (aForClauses);
    m_aReturnExpression = aReturnExpression;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("for ");
    boolean bFirst = true;
    for (final XP2VarNameAndExpression aForClause : m_aForClauses)
    {
      if (bFirst)
        bFirst = false;
      else
        aWriter.write (", ");
      aForClause.writeTo (aWriter);
    }
    aWriter.write (" return ");
    m_aReturnExpression.writeTo (aWriter);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <XP2VarNameAndExpression> getAllForClauses ()
  {
    return m_aForClauses.getClone ();
  }

  @Nonnull
  public IXP2Expression getReturnExpression ()
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
