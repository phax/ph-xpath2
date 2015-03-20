package com.helger.xp2.model;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;

/**
 * A predicate used for a step expression
 *
 * @author Philip Helger
 */
public class XP2Predicate
{
  private final XP2ExpressionList m_aExpressionList;

  public XP2Predicate (@Nonnull final XP2ExpressionList aExpressionList)
  {
    ValueEnforcer.notNull (aExpressionList, "ExpressionList");
    m_aExpressionList = aExpressionList;
  }

  @Nonnull
  public XP2ExpressionList getExpressionList ()
  {
    return m_aExpressionList;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expressionList", m_aExpressionList).toString ();
  }
}
