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
package com.helger.xp2.reader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.annotation.Nonnull;

import org.junit.Test;

/**
 * Test class for class {@link XP2Reader}.
 *
 * @author Philip Helger
 */
public final class XP2ReaderTest
{
  private void _testOK (@Nonnull final String sData)
  {
    assertNotNull (sData, XP2Reader.readFromString (sData));
  }

  private void _testNotOK (@Nonnull final String sData)
  {
    assertNull (sData, XP2Reader.readFromString (sData));
  }

  @Test
  public void testSpecial ()
  {
    if (false)
      _testOK ("element(person, surgeon?)");
    _testOK ("(fn:root(self::node()) treat as document-node())/");
  }

  @Test
  public void testOKArbitrary ()
  {
    _testOK ("$N[@x castable as xs:date][xs:date(@x) gt xs:date(\"2000-01-01\")]");
    _testOK ("$N[if (@x castable as xs:date) then xs:date(@x) gt xs:date(\"2000-01-01\") else false()]");
    _testOK ("$book/(chapter | appendix)[fn:last()]");
    _testOK ("$emp/hiredate - $emp/birthdate");
    _testOK ("$orders[fn:position() = (5 to 9)]");
    _testOK ("$products[price gt 100]");
    _testOK ("$unit-price - $unit-discount");
    _testOK ("(1 to 100)[. mod 5 eq 0]");
    _testOK ("(21 to 29)[5]");
    _testOK ("*");
    _testOK ("*/para");
    _testOK ("-$bellcost + $whistlecost");
    _testOK ("-($bellcost + $whistlecost)");
    _testOK ("-3 div 2");
    _testOK ("-3 idiv 2");
    _testOK ("..");
    _testOK ("../@lang");
    _testOK (".//para");
    _testOK ("/");
    _testOK ("//*");
    _testOK ("//@version");
    _testOK ("//actor[1]/text()");
    _testOK ("//actor[@id<=3]");
    _testOK ("//actor[@id='3']");
    _testOK ("//actor[@id]");
    _testOK ("//actor[last()]");
    _testOK ("//actor[position() < 3]");
    _testOK ("//actor|//foo:singer");
    _testOK ("//foo:singer");
    _testOK ("//foo:singer/@id");
    _testOK ("//list/member");
    _testOK ("//para");
    _testOK ("//product[id = 47]");
    _testOK ("/book/chapter[5]/section[2]");
    _testOK ("/child::book/child::chapter[fn:position() = 5]/child::section[fn:position() = 2]");
    _testOK ("/descendant::figure[fn:position() = 42]");
    _testOK ("/descendant::list/child::member");
    _testOK ("/descendant::para");
    _testOK ("/root");
    _testOK ("/root/actors/actor");
    _testOK ("/root/foo:singers/*");
    _testOK ("/root/user[login='user1' and name='User 1' and profile='admin' and profile='operator']");
    _testOK ("/root/user[login='user1' or name='User 1' or profile='admin' or profile='operator']");
    _testOK ("1(: Houston, we have a problem :)");
    _testOK ("@*");
    _testOK ("@name");
    _testOK ("ancestor-or-self::div");
    _testOK ("ancestor::div");
    _testOK ("attribute::*");
    _testOK ("attribute::name");
    _testOK ("book/(chapter|appendix)/section");
    _testOK ("chapter//para");
    _testOK ("chapter[title=\"Introduction\"]");
    _testOK ("chapter[title]");
    _testOK ("child::*");
    _testOK ("child::*/child::para");
    _testOK ("child::*[self::chapter or self::appendix]");
    _testOK ("child::*[self::chapter or self::appendix][fn:position() = fn:last()]");
    _testOK ("child::chapter/descendant::para");
    _testOK ("child::chapter[2]");
    _testOK ("child::chapter[child::title = 'Introduction']");
    _testOK ("child::chapter[child::title]");
    _testOK ("child::employee[secretary][assistant]");
    _testOK ("child::node()");
    _testOK ("child::para");
    _testOK ("child::para[attribute::type eq 'warning'][fn:position() = 5]");
    _testOK ("child::para[attribute::type eq \"warning\"]");
    _testOK ("child::para[fn:position() = 1]");
    _testOK ("child::para[fn:position() = 5][attribute::type eq \"warning\"]");
    _testOK ("child::para[fn:position() = fn:last()-1]");
    _testOK ("child::para[fn:position() = fn:last()]");
    _testOK ("child::para[fn:position() > 1]");
    _testOK ("child::text()");
    _testOK ("count(//foo:singer)");
    _testOK ("descendant-or-self::para");
    _testOK ("descendant::para");
    _testOK ("descendant::toy[attribute::color = \"red\"]");
    _testOK ("employee[@secretary and @assistant]");
    _testOK ("fn:doc(\"zoo.xml\")/fn:id('tiger')");
    _testOK ("following-sibling::chapter[fn:position() = 1]");
    _testOK ("if (doc-available('abc.xml')) then doc('abc.xml') else ()");
    _testOK ("local-name(//foo:singer[1])");
    _testOK ("name(//*[1])");
    _testOK ("number(//actor[1]/@id)");
    _testOK ("para");
    _testOK ("para[1]");
    _testOK ("para[1 or 2 or 3 or 4]");
    _testOK ("para[1 or 2 or 3 and 4]");
    _testOK ("para[1 or 2 and 3 or 4]");
    _testOK ("para[1 and 2 or 3 or 4]");
    _testOK ("para[1 or 2 and 3 and 4]");
    _testOK ("para[1 and 2 or 3 and 4]");
    _testOK ("para[1 and 2 and 3 or 4]");
    _testOK ("para[1 and 2 and 3 and 4]");
    _testOK ("para[5][@type=\"warning\"]");
    _testOK ("para[@type=\"warning\"]");
    _testOK ("para[@type=\"warning\"][5]");
    _testOK ("para[fn:last()]");
    _testOK ("parent::node()");
    _testOK ("preceding-sibling::chapter[fn:position() = 1]");
    _testOK ("self::para");
    _testOK ("string(//actor[1]/@id)");
    _testOK ("string-length(//actor[1]/text())");
    _testOK ("sum(//foo:singer/@id)");
    _testOK ("text()");
    _testOK ("some $x in $expr1 satisfies $x = 47");
    _testOK ("some $x in $expr1, $y in $expr2 satisfies $x = $y");
    _testOK ("for $x in $expr1 return $x + 1");
    _testOK ("for $x in $expr1, $y in $expr2 return $x + 1");
  }

  @Test
  public void testOKFromSpecs ()
  {
    // From the specs
    // 2.5.4.1
    _testOK ("empty-sequence()");
    // 2.5.4.2
    _testOK ("item()");
    _testOK ("node()");
    _testOK ("text()");
    _testOK ("processing-instruction()");
    _testOK ("processing-instruction(foo)");
    _testOK ("comment()");
    _testOK ("document-node()");
    _testOK ("document-node(element(book))");
    // 2.5.4.3
    _testOK ("element()");
    _testOK ("element(*)");
    _testOK ("element(person)");
    _testOK ("element(ns:person)");
    _testOK ("element(person, surgeon)");
    _testOK ("element(ns:person, surgeon)");
    _testOK ("element(person, ns:surgeon)");
    _testOK ("element(ns:person, ns:surgeon)");
    _testOK ("element(person, surgeon?)");
    _testOK ("element(ns:person, surgeon?)");
    _testOK ("element(person, ns:surgeon?)");
    _testOK ("element(ns:person, ns:surgeon?)");
    _testOK ("element(*, surgeon)");
    _testOK ("element(*, ns:surgeon)");
    _testOK ("element(*, surgeon?)");
    _testOK ("element(*, ns:surgeon?)");
    // 2.5.4.4
    _testOK ("schema-element(foo)");
    // 2.5.4.5
    _testOK ("attribute()");
    _testOK ("attribute(*)");
    _testOK ("attribute(price)");
    _testOK ("attribute(price, currency)");
    _testOK ("attribute(*, currency)");
    // 2.5.4.6
    _testOK ("schema-attribute(foo)");
    // 2.6
    _testOK ("schema-attribute(foo) (: Houston, we have a problem :)");
    _testOK ("(: Houston, we have a problem :) schema-attribute(foo)");
    _testOK ("(: Houston, we have a problem :) schema-attribute(foo) (: Houston, we have a problem :)");
    // 3.1
    _testOK ("\"12.5\"");
    _testOK ("12");
    _testOK ("12.5");
    _testOK ("125E2");
    _testOK ("\"He said, \"\"I don't like it.\"\"\"");
    _testOK ("fn:true()");
    _testOK ("xs:integer(\"12\")");
    _testOK ("xs:date(\"2001-08-25\")");
    _testOK ("xs:dayTimeDuration(\"PT5H\")");
    _testOK ("xs:float(\"NaN\")");
    _testOK ("xs:double(\"INF\")");
    _testOK ("9 cast as hatsize");
    // 3.1.3
    _testOK ("(2 + 4) * 5");
    _testOK ("2 + 4 * 5");
    // 3.1.4
    _testOK (".");
    // 3.1.5
    _testOK ("my:three-argument-function(1, 2, 3)");
    _testOK ("my:two-argument-function((1, 2), 3)");
    _testOK ("my:two-argument-function(1, ())");
    _testOK ("my:one-argument-function((1, 2, 3))");
    _testOK ("my:one-argument-function(( ))");
    _testOK ("my:zero-argument-function( )");
    // 3.2
    _testOK ("/");
    _testOK ("(fn:root(self::node()) treat as document-node())/");
    _testOK ("//x");
    if (false)
      _testOK ("(fn:root(self::node()) treat as document-node())/descendant-or-self::node()/");
    _testOK ("child::div1/child::para");
    _testOK ("/");
    _testOK ("/*");
    _testOK ("/ *");
    _testOK ("(/) * 5");
    _testOK ("4 + (/) * 5");
    _testOK ("4 + /");
    // 3.2.1.2
    _testOK ("node()");
    _testOK ("text()");
    _testOK ("comment()");
    _testOK ("element()");
    _testOK ("schema-element(person)");
    _testOK ("element(person)");
    _testOK ("element(person, surgeon)");
    _testOK ("element(*, surgeon)");
    _testOK ("attribute()");
    _testOK ("attribute(price)");
    _testOK ("attribute(*, xs:decimal)");
    _testOK ("document-node()");
    _testOK ("document-node(element(book))");
    // 3.2.2
    _testOK ("child::chapter[2]");
    _testOK ("descendant::toy[attribute::color = \"red\"]");
    _testOK ("child::employee[secretary][assistant]");
    _testOK ("preceding::foo[1]");
    _testOK ("(preceding::foo)[1]");
    _testOK ("ancestor::*[1]");
    _testOK ("(ancestor::*)[1]");
    _testOK ("");
    _testOK ("");
    _testOK ("");
    _testOK ("");
    _testOK ("");
    _testOK ("");
    _testOK ("");
    _testOK ("");
    _testOK ("");
  }

  @Test
  public void testNotOKFromSpecs ()
  {
    // 3.2
    _testNotOK ("//");
    _testNotOK ("/*5");
    _testNotOK ("/ * 5");
    _testNotOK ("4+ / * 5");
  }
}
