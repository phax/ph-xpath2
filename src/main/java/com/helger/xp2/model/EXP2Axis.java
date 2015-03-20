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
package com.helger.xp2.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

public enum EXP2Axis implements IHasID <String>
{
  // Forward axis
  CHILD ("child", true),
  DESCENDANT ("descendant", true),
  ATTRIBUTE ("attribute", true),
  SELF ("self", true),
  DESCENDANT_OR_SELF ("descendant-or-self", true),
  FOLLOWING_SIBLING ("following-sibling", true),
  FOLLOWING ("following", true),
  NAMESPACE ("namespace", true),
  // Reverse axis
  PARENT ("parent", false),
  ANCESTOR ("ancestor", false),
  PRECEDING_SIBLING ("preceding-sibling", false),
  PRECEDING ("preceding", false),
  ANCESTOR_OR_SELF ("ancestor-or-self", false);

  private final String m_sID;
  private final boolean m_bIsForward;

  private EXP2Axis (@Nonnull @Nonempty final String sID, final boolean bIsForward)
  {
    m_sID = sID;
    m_bIsForward = bIsForward;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isForwardAxis ()
  {
    return m_bIsForward;
  }

  public boolean isReverseAxis ()
  {
    return !m_bIsForward;
  }

  @Nonnull
  public static EXP2Axis getFromIDOrThrow (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrThrow (EXP2Axis.class, sID);
  }
}
