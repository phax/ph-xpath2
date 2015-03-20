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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

public class XP2ElementNameOrWildcard
{
  private final ParserQName m_aElementName;

  protected XP2ElementNameOrWildcard (@Nullable final ParserQName aElementName)
  {
    m_aElementName = aElementName;
  }

  @Nullable
  public ParserQName getElementName ()
  {
    return m_aElementName;
  }

  public boolean isWildcard ()
  {
    return m_aElementName != null;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("elementName", m_aElementName).toString ();
  }

  @Nonnull
  public static XP2ElementNameOrWildcard createElement (@Nonnull final ParserQName aElementName)
  {
    return new XP2ElementNameOrWildcard (aElementName);
  }

  @Nonnull
  public static XP2ElementNameOrWildcard createWildcard ()
  {
    return new XP2ElementNameOrWildcard (null);
  }
}
