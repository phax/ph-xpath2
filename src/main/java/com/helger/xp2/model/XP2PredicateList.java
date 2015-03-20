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
public class XP2PredicateList
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
