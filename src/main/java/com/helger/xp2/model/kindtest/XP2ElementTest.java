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
package com.helger.xp2.model.kindtest;

import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.xp2.parser.ParserQName;

public class XP2ElementTest extends AbstractXP2KindTest
{
  private final XP2ElementNameOrWildcard m_aElementNameOrWildcard;
  private final ParserQName m_aTypeName;
  private final boolean m_bNodeMayBeNilled;

  public XP2ElementTest (@Nullable final XP2ElementNameOrWildcard aElementNameOrWildcard,
                         @Nullable final ParserQName aTypeName,
                         final boolean bNodeMayBeNilled)
  {
    m_aElementNameOrWildcard = aElementNameOrWildcard;
    m_aTypeName = aTypeName;
    m_bNodeMayBeNilled = bNodeMayBeNilled;
  }

  @Nullable
  public XP2ElementNameOrWildcard getElementNameOrWildcard ()
  {
    return m_aElementNameOrWildcard;
  }

  @Nullable
  public ParserQName getTypeName ()
  {
    return m_aTypeName;
  }

  public boolean nodeMayBeNilled ()
  {
    return m_bNodeMayBeNilled;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("elementNameOrWildcard", m_aElementNameOrWildcard)
                                       .appendIfNotNull ("typeName", m_aTypeName)
                                       .appendIfNotNull ("nodeMayBeNilled", m_bNodeMayBeNilled)
                                       .toString ();
  }
}
