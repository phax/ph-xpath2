/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
 * The entry class for the XPath2 expression domain model.
 *
 * @author Philip Helger
 */
public class XP2 implements IXP2Object
{
  private final XP2ExpressionList m_aExpressionList;

  public XP2 (@Nonnull final XP2ExpressionList aExpressionList)
  {
    m_aExpressionList = ValueEnforcer.notNull (aExpressionList, "ExpressionList");
  }

  @Nonnull
  public XP2ExpressionList getExpressionList ()
  {
    return m_aExpressionList;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    m_aExpressionList.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expressionList", m_aExpressionList).getToString ();
  }
}
