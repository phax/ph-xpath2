/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.ToStringGenerator;

public class XP2ExpressionList implements IXP2Object
{
  private final ICommonsList <IXP2Expression> m_aExpressions;

  public XP2ExpressionList (@Nonnull final Iterable <? extends IXP2Expression> aExprs)
  {
    ValueEnforcer.notNull (aExprs, "Expressions");
    m_aExpressions = new CommonsArrayList<> (aExprs);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IXP2Expression> getAllExpressions ()
  {
    return m_aExpressions.getClone ();
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    boolean bFirst = true;
    for (final IXP2Expression aExpression : m_aExpressions)
    {
      if (bFirst)
        bFirst = false;
      else
        aWriter.write (", ");
      aExpression.writeTo (aWriter);
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expressions", m_aExpressions).getToString ();
  }
}
