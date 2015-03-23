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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.junit.Test;

import com.helger.commons.io.streams.NonBlockingStringWriter;
import com.helger.xp2.model.XP2;

/**
 * Test class for class {@link XP2Reader}.
 *
 * @author Philip Helger
 */
public final class XP2ReaderTest
{
  private void _testOK (@Nonnull final String sData) throws IOException
  {
    _testOK (sData, sData);
  }

  private void _testOK (@Nonnull final String sData, @Nonnull final String sRecheck) throws IOException
  {
    final XP2 aParsed = XP2Reader.readFromString (sData);
    assertNotNull ("Failed to parse:\n" + sData, aParsed);

    // Only if no comment is contained
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    aParsed.writeTo (aSW);
    assertEquals (sRecheck, aSW.getAsString ());
  }

  private void _testNotOK (@Nonnull final String sData)
  {
    assertNull (sData, XP2Reader.readFromString (sData));
  }

  @Test
  public void testSpecial () throws IOException
  {
    _testOK ("element(person, surgeon?)");
  }

  @Test
  public void testOKArbitrary () throws IOException
  {
    _testOK ("$N[@x castable as xs:date][xs:date(@x) gt xs:date('2000-01-01')]");
    _testOK ("$N[if (@x castable as xs:date) then xs:date(@x) gt xs:date('2000-01-01') else false()]");
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
    _testOK ("//actor[@id <= 3]");
    _testOK ("//actor[@id = '3']");
    _testOK ("//actor[@id]");
    _testOK ("//actor[last()]");
    _testOK ("//actor[position() < 3]");
    _testOK ("//actor | //foo:singer");
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
    _testOK ("/root/user[login = 'user1' and name = 'User 1' and profile = 'admin' and profile = 'operator']");
    _testOK ("/root/user[login = 'user1' or name = 'User 1' or profile = 'admin' or profile = 'operator']");
    _testOK ("1(: Houston, we have a problem :)", "1");
    _testOK ("1(: Houston, we (:still:) have a problem :)", "1");
    _testOK ("@*");
    _testOK ("@name");
    _testOK ("ancestor-or-self::div");
    _testOK ("ancestor::div");
    _testOK ("attribute::*");
    _testOK ("attribute::name");
    _testOK ("book/(chapter | appendix)/section");
    _testOK ("chapter//para");
    _testOK ("chapter[title = 'Introduction']");
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
    _testOK ("child::para[attribute::type eq 'warning']");
    _testOK ("child::para[fn:position() = 1]");
    _testOK ("child::para[fn:position() = 5][attribute::type eq 'warning']");
    _testOK ("child::para[fn:position() = fn:last() - 1]");
    _testOK ("child::para[fn:position() = fn:last()]");
    _testOK ("child::para[fn:position() > 1]");
    _testOK ("child::text()");
    _testOK ("count(//foo:singer)");
    _testOK ("descendant-or-self::para");
    _testOK ("descendant::para");
    _testOK ("descendant::toy[attribute::color = 'red']");
    _testOK ("employee[@secretary and @assistant]");
    _testOK ("fn:doc('zoo.xml')/fn:id('tiger')");
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
    _testOK ("para[5][@type = 'warning']");
    _testOK ("para[@type = 'warning']");
    _testOK ("para[@type = 'warning'][5]");
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
  public void testOKFromSpecs () throws IOException
  {
    // From the specs
    // 2.5.4.1
    _testOK ("$x treat as empty-sequence()");
    // 2.5.4.2
    _testOK ("$x treat as item()");
    _testOK ("$x treat as node()");
    _testOK ("$x treat as text()");
    _testOK ("$x treat as processing-instruction()");
    _testOK ("$x treat as processing-instruction(foo)");
    _testOK ("$x treat as processing-instruction('bar')");
    _testOK ("$x treat as comment()");
    _testOK ("$x treat as document-node()");
    _testOK ("$x treat as document-node(element(book))");
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
    _testOK ("schema-attribute(foo) (: Houston, we have a problem :)", "schema-attribute(foo)");
    _testOK ("(: Houston, we have a problem :) schema-attribute(foo)", "schema-attribute(foo)");
    _testOK ("(: Houston, we have a problem :) schema-attribute(foo) (: Houston, we have a problem :)",
             "schema-attribute(foo)");
    // 3.1
    _testOK ("'12.5'");
    _testOK ("12");
    _testOK ("12.5");
    _testOK ("125E2", "1.25E+4");
    _testOK ("\"He said, \"\"I don't like it.\"\"\"", "'He said, \"I don''t like it.\"'");
    _testOK ("fn:true()");
    _testOK ("xs:integer('12')");
    _testOK ("xs:date('2001-08-25')");
    _testOK ("xs:dayTimeDuration('PT5H')");
    _testOK ("xs:float('NaN')");
    _testOK ("xs:double('INF')");
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
    _testOK ("my:one-argument-function(())");
    _testOK ("my:zero-argument-function()");
    // 3.2
    _testOK ("/");
    _testOK ("(fn:root(self::node()) treat as document-node())/foo");
    _testOK ("//x");
    _testOK ("(fn:root(self::node()) treat as document-node())/descendant-or-self::node()/foo");
    _testOK ("child::div1/child::para");
    _testOK ("/");
    _testOK ("/*");
    _testOK ("/ *", "/*");
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
    _testOK ("descendant::toy[attribute::color = 'red']");
    _testOK ("child::employee[secretary][assistant]");
    _testOK ("preceding::foo[1]");
    _testOK ("(preceding::foo)[1]");
    _testOK ("ancestor::*[1]");
    _testOK ("(ancestor::*)[1]");
    // 3.2.3
    _testOK ("child::para");
    _testOK ("child::*");
    _testOK ("child::text()");
    _testOK ("child::node()");
    _testOK ("attribute::name");
    _testOK ("attribute::*");
    _testOK ("parent::node()");
    _testOK ("descendant::para");
    _testOK ("ancestor::div");
    _testOK ("ancestor-or-self::div");
    _testOK ("descendant-or-self::para");
    _testOK ("self::para");
    _testOK ("child::chapter/descendant::para");
    _testOK ("child::*/child::para");
    _testOK ("/");
    _testOK ("/descendant::para");
    _testOK ("/descendant::list/child::member");
    _testOK ("child::para[fn:position() = 1]");
    _testOK ("child::para[fn:position() = fn:last()]");
    _testOK ("child::para[fn:position() = fn:last() - 1]");
    _testOK ("child::para[fn:position() > 1]");
    _testOK ("following-sibling::chapter[fn:position() = 1]");
    _testOK ("preceding-sibling::chapter[fn:position() = 1]");
    _testOK ("/descendant::figure[fn:position() = 42]");
    _testOK ("/child::book/child::chapter[fn:position() = 5]/child::section[fn:position() = 2]");
    _testOK ("child::para[attribute::type eq 'warning']");
    _testOK ("child::para[attribute::type eq 'warning'][fn:position() = 5]");
    _testOK ("child::para[fn:position() = 5][attribute::type eq 'warning']");
    _testOK ("child::chapter[child::title = 'Introduction']");
    _testOK ("child::chapter[child::title]");
    _testOK ("child::*[self::chapter or self::appendix]");
    _testOK ("child::*[self::chapter or self::appendix][fn:position() = fn:last()]");
    // 3.2.4
    _testOK ("para");
    _testOK ("*");
    _testOK ("text()");
    _testOK ("@name");
    _testOK ("@*");
    _testOK ("para[1]");
    _testOK ("para[fn:last()]");
    _testOK ("*/para");
    _testOK ("/book/chapter[5]/section[2]");
    _testOK ("chapter//para");
    _testOK ("//para");
    _testOK ("//@version");
    _testOK ("//list/member");
    _testOK (".//para");
    _testOK ("..");
    _testOK ("../@lang");
    _testOK ("para[@type = 'warning']");
    _testOK ("para[@type = 'warning'][5]");
    _testOK ("para[5][@type = 'warning']");
    _testOK ("chapter[title = 'Introduction']");
    _testOK ("chapter[title]");
    _testOK ("employee[@secretary and @assistant]");
    _testOK ("book/(chapter | appendix)/section");
    // 3.3.1
    _testOK ("(10, 1, 2, 3, 4)");
    _testOK ("(10, (1, 2), (), (3, 4))");
    _testOK ("(salary, bonus)");
    _testOK ("($price, $price)");
    _testOK ("(10, 1 to 4)");
    _testOK ("10 to 10");
    _testOK ("15 to 10");
    _testOK ("fn:reverse(10 to 15)");
    // 3.3.2
    _testOK ("$products[price gt 100]");
    _testOK ("(1 to 100)[. mod 5 eq 0]");
    _testOK ("(21 to 29)[5]");
    _testOK ("$orders[fn:position() = (5 to 9)]");
    _testOK ("$book/(chapter | appendix)[fn:last()]");
    _testOK ("fn:doc('zoo.xml')/fn:id('tiger')");
    // 3.3.3
    _testOK ("$seq1 union $seq2");
    _testOK ("$seq2 union $seq3");
    _testOK ("$seq1 intersect $seq2");
    _testOK ("$seq2 intersect $seq3");
    _testOK ("$seq1 except $seq2");
    _testOK ("$seq2 except $seq3");
    // 3.4
    _testOK ("-3 div 2");
    _testOK ("-3 idiv 2");
    _testOK ("$emp/hiredate - $emp/birthdate");
    _testOK ("$unit-price - $unit-discount");
    _testOK ("-$bellcost + $whistlecost");
    _testOK ("-($bellcost + $whistlecost)");
    // 3.5.1
    _testOK ("$book1/author eq 'Kennedy'");
    _testOK ("//product[weight gt 100]");
    _testOK ("my:hatsize(5) eq my:shoesize(5)");
    _testOK ("fn:QName('http://example.com/ns1', 'this:color') eq fn:QName('http://example.com/ns1', 'that:color')");
    // 3.5.2
    _testOK ("$book1/author = 'Kennedy'");
    _testOK ("(1, 2) = (2, 3)");
    _testOK ("(2, 3) = (3, 4)");
    _testOK ("(1, 2) = (3, 4)");
    _testOK ("(1, 2) = (2, 3)");
    _testOK ("(1, 2) != (2, 3)");
    // 3.5.3
    _testOK ("/books/book[isbn = '1558604820'] is /books/book[call = 'QA76.9 C3845']");
    _testOK ("/transactions/purchase[parcel = '28-451'] << /transactions/sale[parcel = '33-870']");
    // 3.6
    _testOK ("1 eq 1 and 2 eq 2");
    _testOK ("1 eq 1 or 2 eq 3");
    _testOK ("1 eq 2 and 3 idiv 0 = 1");
    _testOK ("1 eq 1 or 3 idiv 0 = 1");
    _testOK ("1 eq 1 and 3 idiv 0 = 1");
    // 3.7
    _testOK ("for $a in fn:distinct-values(book/author) return (book/author[. = $a][1], book[author = $a]/title)");
    _testOK ("for $i in (10, 20), $j in (1, 2) return ($i + $j)");
    _testOK ("for $x in $z, $y in f($x) return g($x, $y)");
    _testOK ("fn:sum(for $i in order-item return @price * @qty)");
    _testOK ("fn:sum(for $i in order-item return $i/@price * $i/@qty)");
    // 3.8
    _testOK ("if ($widget1/unit-cost < $widget2/unit-cost) then $widget1 else $widget2");
    _testOK ("if ($part/@discounted) then $part/wholesale else $part/retail");
    // 3.9
    _testOK ("every $part in /parts/part satisfies $part/@discounted");
    _testOK ("some $emp in /emps/employee satisfies ($emp/bonus > 0.25 * $emp/salary)");
    _testOK ("some $x in (1, 2, 3), $y in (2, 3, 4) satisfies $x + $y = 4");
    _testOK ("every $x in (1, 2, 3), $y in (2, 3, 4) satisfies $x + $y = 4");
    _testOK ("some $x in (1, 2, 'cat') satisfies $x * 2 = 4");
    _testOK ("every $x in (1, 2, 'cat') satisfies $x * 2 = 4");
    // 3.10.1
    _testOK ("5 instance of xs:integer");
    _testOK ("5 instance of xs:decimal");
    _testOK ("(5, 6) instance of xs:integer+");
    _testOK (". instance of element()");
    // 3.10.3
    _testOK ("if ($x castable as hatsize) then $x cast as hatsize else if ($x castable as IQ) then $x cast as IQ else $x cast as xs:string");
    // 3.10.4
    _testOK ("xs:date('2000-01-01')");
    _testOK ("xs:decimal($floatvalue * 0.2E-5)", "xs:decimal($floatvalue * 0.000002)");
    _testOK ("xs:dayTimeDuration('P21D')");
    _testOK ("usa:zipcode('12345')");
    _testOK ("17 cast as apple");
    _testOK ("apple(17)");
    // 3.10.5
    _testOK ("$myaddress treat as element(*, USAddress)");
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
