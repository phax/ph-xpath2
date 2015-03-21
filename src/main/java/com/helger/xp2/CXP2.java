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
package com.helger.xp2;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotations.PresentForCodeCoverage;

/**
 * Specific XPath constants. Based on:
 * <ul>
 * <li>http://www.w3.org/TR/xpath20/</li>
 * </ul>
 *
 * @author Philip Helger
 */
@Immutable
public final class CXP2
{
  public static final String XML_NS_XS = "http://www.w3.org/2001/XMLSchema";

  public static final String XML_NS_FN = "http://www.w3.org/2005/xpath-functions";

  public static final String XML_NS_ERR = "http://www.w3.org/2005/xqt-errors";

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final CXP2 s_aInstance = new CXP2 ();

  private CXP2 ()
  {}
}
