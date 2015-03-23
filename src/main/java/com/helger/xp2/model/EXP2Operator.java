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

public enum EXP2Operator implements IHasID <String>, IXP2Object
{
  OR ("or"),
  AND ("and"),
  EQ ("eq"),
  NE ("ne"),
  LT ("lt"),
  LE ("le"),
  GT ("gt"),
  GE ("ge"),
  EQUALS ("="),
  NOT_EQUALS ("!="),
  LOWER ("<"),
  LOWER_EQUALS ("<="),
  GREATER (">"),
  GREATER_EQUALS (">="),
  IS ("is"),
  SHL ("<<"),
  SHR (">>"),
  TO ("to"),
  PLUS ("+"),
  MINUS ("-"),
  ASTERISK ("*"),
  DIV ("div"),
  IDIV ("idiv"),
  MOD ("mod"),
  UNION ("union"),
  PIPE ("|"),
  INTERSECT ("intersect"),
  EXCEPT ("except"),
  INSTANCE_OF ("instance of"),
  TREAT_AS ("treat as"),
  CASTABLE_AS ("castable as"),
  CAST_AS ("cast as");

  private final String m_sID;

  private EXP2Operator (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public static EXP2Operator getFromIDOrThrow (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrThrow (EXP2Operator.class, sID);
  }
}
