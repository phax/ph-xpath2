/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.model.nodetest.IXP2NodeTest;

public class XP2AbbreviatedElementStep extends AbstractXP2SingleStep
{
  private final IXP2NodeTest m_aNodeTest;

  public XP2AbbreviatedElementStep (@Nonnull final IXP2NodeTest aNodeTest)
  {
    m_aNodeTest = ValueEnforcer.notNull (aNodeTest, "NodeTest");
  }

  @Nonnull
  public IXP2NodeTest getNodeTest ()
  {
    return m_aNodeTest;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    m_aNodeTest.writeTo (aWriter);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("nodeTest", m_aNodeTest).toString ();
  }
}
