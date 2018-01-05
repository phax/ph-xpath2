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
package com.helger.xp2.model;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

public enum EXP2Operator implements IHasID <String>, IXP2Object
{
  OR ("or", EXP2OperatorType.OR),
  AND ("and", EXP2OperatorType.AND),
  EQ ("eq", EXP2OperatorType.VALUE_COMP),
  NE ("ne", EXP2OperatorType.VALUE_COMP),
  LT ("lt", EXP2OperatorType.VALUE_COMP),
  LE ("le", EXP2OperatorType.VALUE_COMP),
  GT ("gt", EXP2OperatorType.VALUE_COMP),
  GE ("ge", EXP2OperatorType.VALUE_COMP),
  EQUALS ("=", EXP2OperatorType.GENERAL_COMP),
  NOT_EQUALS ("!=", EXP2OperatorType.GENERAL_COMP),
  LOWER ("<", EXP2OperatorType.GENERAL_COMP),
  LOWER_EQUALS ("<=", EXP2OperatorType.GENERAL_COMP),
  GREATER (">", EXP2OperatorType.GENERAL_COMP),
  GREATER_EQUALS (">=", EXP2OperatorType.GENERAL_COMP),
  IS ("is", EXP2OperatorType.NODE_COMP),
  SHL ("<<", EXP2OperatorType.NODE_COMP),
  SHR (">>", EXP2OperatorType.NODE_COMP),
  TO ("to", EXP2OperatorType.TO),
  PLUS ("+", EXP2OperatorType.ADDITIVE),
  MINUS ("-", EXP2OperatorType.ADDITIVE),
  ASTERISK ("*", EXP2OperatorType.MULTIPLICATIVE),
  DIV ("div", EXP2OperatorType.MULTIPLICATIVE),
  IDIV ("idiv", EXP2OperatorType.MULTIPLICATIVE),
  MOD ("mod", EXP2OperatorType.MULTIPLICATIVE),
  UNION ("union", EXP2OperatorType.UNION),
  PIPE ("|", EXP2OperatorType.UNION),
  INTERSECT ("intersect", EXP2OperatorType.INTERSECT),
  EXCEPT ("except", EXP2OperatorType.INTERSECT),
  INSTANCE_OF ("instance of", EXP2OperatorType.INSTANCE_OF),
  TREAT_AS ("treat as", EXP2OperatorType.TREAT_AS),
  CASTABLE_AS ("castable as", EXP2OperatorType.CASTABLE_AS),
  CAST_AS ("cast as", EXP2OperatorType.CAST_AS);

  private final String m_sID;
  private final EXP2OperatorType m_eType;

  private EXP2Operator (@Nonnull @Nonempty final String sID, @Nonnull final EXP2OperatorType eType)
  {
    m_sID = sID;
    m_eType = eType;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public EXP2OperatorType getType ()
  {
    return m_eType;
  }

  public boolean needsBlanksAround ()
  {
    return true;
  }

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write (m_sID);
  }

  @Nonnull
  public static EXP2Operator getFromIDOrThrow (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrThrow (EXP2Operator.class, sID);
  }
}
