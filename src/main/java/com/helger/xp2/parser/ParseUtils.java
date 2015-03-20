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
package com.helger.xp2.parser;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.xml.CXML;
import com.helger.commons.xml.namespace.MapBasedNamespaceContext;

/**
 * This class is used by the generated parsers to do some common stuff.
 *
 * @author Philip Helger
 */
@Immutable
public final class ParseUtils
{
  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final ParseUtils s_aInstance = new ParseUtils ();

  private ParseUtils ()
  {}

  /**
   * Source http://www.w3.org/TR/xpath-datamodel/#notation
   *
   * @return Namespace context with default namespaces.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext createParserNamespaceContext ()
  {
    final MapBasedNamespaceContext ret = new MapBasedNamespaceContext ();
    ret.addMapping ("xs", CXML.XML_NS_XSD);
    ret.addMapping ("xsi", CXML.XML_NS_XSI);
    ret.addMapping ("fn", "http://www.w3.org/2005/xpath-functions");
    return ret;
  }
}
