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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * A list of {@link XP2Predicate} objects used for a step expression. May be
 * empty.
 *
 * @author Philip Helger
 */
public class XP2PredicateList implements IXP2Object
{
  private final List <XP2Predicate> m_aPredicates;

  public XP2PredicateList (@Nonnull final List <XP2Predicate> aPredicates)
  {
    ValueEnforcer.notNull (aPredicates, "Predicates");
    m_aPredicates = CollectionHelper.newList (aPredicates);
  }

  public boolean hasAnyPredicate ()
  {
    return !m_aPredicates.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <XP2Predicate> getAllPredicates ()
  {
    return CollectionHelper.newList (m_aPredicates);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("predicates", m_aPredicates).toString ();
  }
}
