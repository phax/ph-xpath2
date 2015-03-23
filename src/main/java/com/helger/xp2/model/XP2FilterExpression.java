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

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;

public class XP2FilterExpression extends AbstractXP2StepExpression
{
  private final IXP2PrimaryExpression m_aExpression;
  private final XP2PredicateList m_aPredicateList;

  public XP2FilterExpression (@Nonnull final IXP2PrimaryExpression aExpr, @Nonnull final XP2PredicateList aPredicateList)
  {
    m_aExpression = ValueEnforcer.notNull (aExpr, "Expression");
    m_aPredicateList = ValueEnforcer.notNull (aPredicateList, "PredicateList");
  }

  @Nonnull
  public IXP2PrimaryExpression getExpression ()
  {
    return m_aExpression;
  }

  @Nonnull
  public XP2PredicateList getPredicateList ()
  {
    return m_aPredicateList;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    m_aExpression.writeTo (aWriter);
    m_aPredicateList.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expression", m_aExpression)
                                       .append ("predicateList", m_aPredicateList)
                                       .toString ();
  }
}
