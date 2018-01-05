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
import com.helger.xp2.parser.ParserQName;

/**
 * A pair of variable name and expression.
 *
 * @author Philip Helger
 */
public class XP2VarNameAndExpression implements IXP2Object
{
  private final ParserQName m_aVarName;
  private final IXP2Expression m_aExpression;

  public XP2VarNameAndExpression (@Nonnull final ParserQName aVarName, @Nonnull final IXP2Expression aExpression)
  {
    m_aVarName = ValueEnforcer.notNull (aVarName, "VarName");
    m_aExpression = ValueEnforcer.notNull (aExpression, "Expression");
  }

  @Nonnull
  public ParserQName getVarName ()
  {
    return m_aVarName;
  }

  @Nonnull
  public IXP2Expression getExpression ()
  {
    return m_aExpression;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ('$');
    aWriter.write (m_aVarName.getAsString ());
    aWriter.write (" in ");
    m_aExpression.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("varName", m_aVarName)
                                       .append ("expression", m_aExpression)
                                       .getToString ();
  }
}
