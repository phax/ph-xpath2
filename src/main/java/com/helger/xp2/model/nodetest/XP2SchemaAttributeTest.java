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
package com.helger.xp2.model.nodetest;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

public class XP2SchemaAttributeTest extends AbstractXP2KindTest
{
  private final ParserQName m_aAttributeDeclaration;

  public XP2SchemaAttributeTest (@Nonnull final ParserQName aAttributeDeclaration)
  {
    m_aAttributeDeclaration = ValueEnforcer.notNull (aAttributeDeclaration, "AttributeDeclaration");
  }

  @Nonnull
  public ParserQName getAttributeDeclaration ()
  {
    return m_aAttributeDeclaration;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("schema-attribute(");
    aWriter.write (m_aAttributeDeclaration.getAsString ());
    aWriter.write (')');
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("attributeDeclaration", m_aAttributeDeclaration).getToString ();
  }
}
