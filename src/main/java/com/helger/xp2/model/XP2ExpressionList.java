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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;

public class XP2ExpressionList
{
  private final List <AbstractXP2Expression> m_aExprs = new ArrayList <AbstractXP2Expression> ();

  public XP2ExpressionList ()
  {}

  public void addExpression (@Nonnull final AbstractXP2Expression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "Expr");
    m_aExprs.add (aExpr);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <AbstractXP2Expression> getAllExpressions ()
  {
    return CollectionHelper.newList (m_aExprs);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expressions", m_aExprs).toString ();
  }
}
