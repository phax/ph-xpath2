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
package com.helger.xp2.model.nodetest;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;
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

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("element(");
    if (m_aElementNameOrWildcard != null)
    {
      m_aElementNameOrWildcard.writeTo (aWriter);
      if (m_aTypeName != null)
      {
        aWriter.write (", ");
        aWriter.write (m_aTypeName.getAsString ());
        if (m_bNodeMayBeNilled)
          aWriter.write ('?');
      }
    }
    aWriter.write (')');
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("elementNameOrWildcard", m_aElementNameOrWildcard)
                                       .appendIfNotNull ("typeName", m_aTypeName)
                                       .appendIfNotNull ("nodeMayBeNilled", m_bNodeMayBeNilled)
                                       .getToString ();
  }
}
