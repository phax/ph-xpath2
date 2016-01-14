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
import com.helger.commons.string.ToStringGenerator;

/**
 * A predicate used for a step expression
 *
 * @author Philip Helger
 */
public class XP2Predicate implements IXP2Object
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

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ('[');
    m_aExpressionList.writeTo (aWriter);
    aWriter.write (']');
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expressionList", m_aExpressionList).toString ();
  }
}
