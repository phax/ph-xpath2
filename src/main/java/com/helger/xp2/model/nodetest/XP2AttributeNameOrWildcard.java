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
package com.helger.xp2.model.nodetest;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.model.IXP2Object;
import com.helger.xp2.parser.ParserQName;

public class XP2AttributeNameOrWildcard implements IXP2Object
{
  private final ParserQName m_aAttributeName;

  protected XP2AttributeNameOrWildcard (@Nullable final ParserQName aAttributeName)
  {
    m_aAttributeName = aAttributeName;
  }

  @Nullable
  public ParserQName getAttributeName ()
  {
    return m_aAttributeName;
  }

  public boolean isWildcard ()
  {
    return m_aAttributeName == null;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    if (isWildcard ())
      aWriter.write ('*');
    else
      aWriter.write (m_aAttributeName.getAsString ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("attributeName", m_aAttributeName).toString ();
  }

  @Nonnull
  public static XP2AttributeNameOrWildcard createAttribute (@Nonnull final ParserQName aAttributeName)
  {
    return new XP2AttributeNameOrWildcard (aAttributeName);
  }

  @Nonnull
  public static XP2AttributeNameOrWildcard createWildcard ()
  {
    return new XP2AttributeNameOrWildcard (null);
  }
}
