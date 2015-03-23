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
package com.helger.xp2.model.axisstep;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.model.AbstractXP2StepExpression;
import com.helger.xp2.model.XP2PredicateList;

public class XP2AxisStep extends AbstractXP2StepExpression
{
  private final IXP2SingleStep m_aSingleStep;
  private final XP2PredicateList m_aPredicateList;

  public XP2AxisStep (@Nonnull final IXP2SingleStep aSingleStep, @Nonnull final XP2PredicateList aPredicateList)
  {
    m_aSingleStep = ValueEnforcer.notNull (aSingleStep, "SingleStep");
    m_aPredicateList = ValueEnforcer.notNull (aPredicateList, "PredicateList");
  }

  @Nonnull
  public IXP2SingleStep getSingleStep ()
  {
    return m_aSingleStep;
  }

  @Nonnull
  public XP2PredicateList getPredicateList ()
  {
    return m_aPredicateList;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("singleStep", m_aSingleStep)
                                       .append ("predicateList", m_aPredicateList)
                                       .toString ();
  }
}
