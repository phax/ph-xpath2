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
package com.helger.xp2.model.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.namespace.QName;

import org.junit.Test;

/**
 * Test class for class {@link TypeHierarchy}.
 *
 * @author Philip Helger
 */
public final class TypeHierarchyTest
{
  @Test
  public void testDefault ()
  {
    final TypeHierarchy aBuiltIn = TypeHierarchy.createBuiltIntTypeHierarchy ();
    assertNotNull (aBuiltIn);
    assertTrue (aBuiltIn.containsType (XSType.createName ("anyType")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("untyped")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("anySimpleType")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("IDREFS")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("NMTOKENS")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("ENTITIES")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("anyAtomicType")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("untypedAtomic")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("dateTime")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("date")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("time")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("duration")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("yearMonthDuration")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("dayTimeDuration")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("float")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("double")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("decimal")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("integer")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("nonPositiveInteger")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("negativeInteger")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("long")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("int")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("short")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("byte")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("nonNegativeInteger")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("unsignedLong")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("unsignedInt")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("unsignedShort")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("unsignedByte")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("positiveInteger")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("gYearMonth")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("gYear")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("gMonthDay")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("gDay")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("gMonth")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("string")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("normalizedString")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("token")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("language")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("NMTOKEN")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("Name")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("NCName")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("ID")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("IDREF")));
    assertTrue (aBuiltIn.containsType (XSType.createName ("ENTITY")));

    {
      final QName aDayTimeDuration = XSType.createName ("dayTimeDuration");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, aDayTimeDuration));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, XSType.createName ("duration")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, XSType.createName ("IDREF")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aDayTimeDuration, XSType.createName ("ENTITY")));
    }

    {
      final QName aUnsignedShort = XSType.createName ("unsignedShort");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, aUnsignedShort));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("unsignedInt")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("unsignedLong")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("nonNegativeInteger")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("integer")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("decimal")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("IDREF")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aUnsignedShort, XSType.createName ("ENTITY")));
    }

    {
      final QName aUnsignedByte = XSType.createName ("unsignedByte");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, aUnsignedByte));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("unsignedShort")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("unsignedInt")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("unsignedLong")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("nonNegativeInteger")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("integer")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("decimal")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("IDREF")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aUnsignedByte, XSType.createName ("ENTITY")));
    }

    {
      final QName aPositiveInteger = XSType.createName ("positiveInteger");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, aPositiveInteger));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("nonNegativeInteger")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("integer")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("decimal")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("IDREF")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aPositiveInteger, XSType.createName ("ENTITY")));
    }

    {
      final QName aID = XSType.createName ("ID");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, aID));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("NCName")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("Name")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("token")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("normalizedString")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("string")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aID, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("IDREF")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aID, XSType.createName ("ENTITY")));
    }
    {
      final QName aIDREF = XSType.createName ("IDREF");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, aIDREF));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("NCName")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("Name")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("token")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("normalizedString")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("string")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aIDREF, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("ID")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aIDREF, XSType.createName ("ENTITY")));
    }
    {
      final QName aENTITY = XSType.createName ("ENTITY");
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, aENTITY));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("NCName")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("Name")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("token")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("normalizedString")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("string")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("anyAtomicType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("anySimpleType")));
      assertTrue (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("anyType")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aENTITY, null));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("ID")));
      assertFalse (aBuiltIn.isSameOrSubTypeOf (aENTITY, XSType.createName ("IDREF")));
    }
  }
}
