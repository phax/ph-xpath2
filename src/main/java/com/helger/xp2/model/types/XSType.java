/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.xp2.model.types;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.xml.CXML;

public class XSType extends XPathType
{
  public XSType (@Nonnull @Nonempty final String sLocalName, @Nullable final XSType aParentType)
  {
    super (createName (sLocalName), aParentType);
  }

  @Override
  public boolean isBuiltIn ()
  {
    return true;
  }

  @Nonnull
  public static QName createName (@Nonnull @Nonempty final String sLocalName)
  {
    return new QName (CXML.XML_NS_XSD, sLocalName, "xs");
  }
}
