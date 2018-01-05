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
package com.helger.xp2.model.nodetest;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

public class XP2AttributeTest extends AbstractXP2KindTest
{
  private final XP2AttributeNameOrWildcard m_aAttributeNameOrWildcard;
  private final ParserQName m_aTypeName;

  public XP2AttributeTest (@Nullable final XP2AttributeNameOrWildcard aAttributeNameOrWildcard,
                           @Nullable final ParserQName aTypeName)
  {
    m_aAttributeNameOrWildcard = aAttributeNameOrWildcard;
    m_aTypeName = aTypeName;
  }

  @Nullable
  public XP2AttributeNameOrWildcard getAttributeNameOrWildcard ()
  {
    return m_aAttributeNameOrWildcard;
  }

  @Nullable
  public ParserQName getTypeName ()
  {
    return m_aTypeName;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("attribute(");
    if (m_aAttributeNameOrWildcard != null)
    {
      m_aAttributeNameOrWildcard.writeTo (aWriter);
      if (m_aTypeName != null)
      {
        aWriter.write (", ");
        aWriter.write (m_aTypeName.getAsString ());
      }
    }
    aWriter.write (')');
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("attributeNameOrWildcard", m_aAttributeNameOrWildcard)
                                       .appendIfNotNull ("typeName", m_aTypeName)
                                       .getToString ();
  }
}
