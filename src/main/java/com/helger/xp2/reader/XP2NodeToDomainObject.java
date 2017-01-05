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
package com.helger.xp2.reader;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.xp2.model.EXP2Operator;
import com.helger.xp2.model.EXP2PathOperator;
import com.helger.xp2.model.EXP2QuantifiedExpressionType;
import com.helger.xp2.model.IXP2Expression;
import com.helger.xp2.model.IXP2LiteralExpression;
import com.helger.xp2.model.IXP2Object;
import com.helger.xp2.model.IXP2PrimaryExpression;
import com.helger.xp2.model.IXP2StepExpression;
import com.helger.xp2.model.XP2;
import com.helger.xp2.model.XP2BinaryExpression;
import com.helger.xp2.model.XP2ContextItemExpression;
import com.helger.xp2.model.XP2ExpressionList;
import com.helger.xp2.model.XP2FilterExpression;
import com.helger.xp2.model.XP2ForExpression;
import com.helger.xp2.model.XP2FunctionCall;
import com.helger.xp2.model.XP2IfExpression;
import com.helger.xp2.model.XP2NCName;
import com.helger.xp2.model.XP2NumericLiteral;
import com.helger.xp2.model.XP2ParenthesizedExpression;
import com.helger.xp2.model.XP2PathExpression;
import com.helger.xp2.model.XP2Predicate;
import com.helger.xp2.model.XP2PredicateList;
import com.helger.xp2.model.XP2QuantifiedExpression;
import com.helger.xp2.model.XP2RelativePathExpression;
import com.helger.xp2.model.XP2SequenceTypeExpression;
import com.helger.xp2.model.XP2SingleType;
import com.helger.xp2.model.XP2SingleTypeExpression;
import com.helger.xp2.model.XP2StringLiteral;
import com.helger.xp2.model.XP2UnaryExpression;
import com.helger.xp2.model.XP2VarNameAndExpression;
import com.helger.xp2.model.XP2VariableReference;
import com.helger.xp2.model.axisstep.EXP2Axis;
import com.helger.xp2.model.axisstep.IXP2SingleStep;
import com.helger.xp2.model.axisstep.XP2AbbreviatedAttributeStep;
import com.helger.xp2.model.axisstep.XP2AbbreviatedElementStep;
import com.helger.xp2.model.axisstep.XP2AbbreviatedReverseStep;
import com.helger.xp2.model.axisstep.XP2AxisStep;
import com.helger.xp2.model.axisstep.XP2SingleStep;
import com.helger.xp2.model.nodetest.IXP2KindTest;
import com.helger.xp2.model.nodetest.IXP2NameTest;
import com.helger.xp2.model.nodetest.IXP2NodeTest;
import com.helger.xp2.model.nodetest.XP2AttributeNameOrWildcard;
import com.helger.xp2.model.nodetest.XP2AttributeTest;
import com.helger.xp2.model.nodetest.XP2CommentTest;
import com.helger.xp2.model.nodetest.XP2DocumentTest;
import com.helger.xp2.model.nodetest.XP2ElementNameOrWildcard;
import com.helger.xp2.model.nodetest.XP2ElementTest;
import com.helger.xp2.model.nodetest.XP2LocalNameIsWildcardTest;
import com.helger.xp2.model.nodetest.XP2NamespaceIsWildcardTest;
import com.helger.xp2.model.nodetest.XP2NodeTest;
import com.helger.xp2.model.nodetest.XP2ProcessingInstructionTest;
import com.helger.xp2.model.nodetest.XP2QNameTest;
import com.helger.xp2.model.nodetest.XP2SchemaAttributeTest;
import com.helger.xp2.model.nodetest.XP2SchemaElementTest;
import com.helger.xp2.model.nodetest.XP2TextTest;
import com.helger.xp2.model.nodetest.XP2WildcardTest;
import com.helger.xp2.model.sequencetype.EXP2OccurrenceIndicator;
import com.helger.xp2.model.sequencetype.IXP2SequenceType;
import com.helger.xp2.model.sequencetype.XP2EmptySequence;
import com.helger.xp2.model.sequencetype.XP2SequenceTypeAtomicType;
import com.helger.xp2.model.sequencetype.XP2SequenceTypeItem;
import com.helger.xp2.model.sequencetype.XP2SequenceTypeKindTest;
import com.helger.xp2.parser.ParserQName;
import com.helger.xp2.parser.ParserXP2TreeConstants;
import com.helger.xp2.parser.XP2Node;

/**
 * This class is responsible for converting the abstract syntax tree created by
 * JavaCC to a domain model.
 *
 * @author Philip Helger
 */
public final class XP2NodeToDomainObject
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (XP2NodeToDomainObject.class);

  private XP2NodeToDomainObject ()
  {}

  private static void _expectNodeType (@Nonnull final XP2Node aNode, final int nExpected)
  {
    if (aNode.getNodeType () != nExpected)
      throw new XP2HandlingException (aNode,
                                      "Expected a '" +
                                             ParserXP2TreeConstants.jjtNodeName[nExpected] +
                                             "' node but received a '" +
                                             ParserXP2TreeConstants.jjtNodeName[aNode.getNodeType ()] +
                                             "'");
  }

  private static void _throwUnexpectedChildrenCount (@Nonnull final XP2Node aNode, @Nonnull @Nonempty final String sMsg)
  {
    s_aLogger.error (sMsg + " (having " + aNode.jjtGetNumChildren () + " children)");
    for (int i = 0; i < aNode.jjtGetNumChildren (); ++i)
      s_aLogger.error ("  " + aNode.jjtGetChild (i));
    throw new XP2HandlingException (aNode, sMsg);
  }

  // [70] TypeName ::= QName
  @Nonnull
  private static ParserQName _convertTypeName (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTTYPENAME);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    return (ParserQName) aNode.getValue ();
  }

  // [69] ElementName ::= QName
  @Nonnull
  private static ParserQName _convertElementName (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTELEMENTNAME);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    return (ParserQName) aNode.getValue ();
  }

  // [68] AttributeName ::= QName
  @Nonnull
  private static ParserQName _convertAttributeName (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTATTRIBUTENAME);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    return (ParserQName) aNode.getValue ();
  }

  // [67] ElementDeclaration ::= ElementName
  @Nonnull
  private static ParserQName _convertElementDeclaration (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTELEMENTDECLARATION);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    return _convertElementName (aNode.jjtGetChild (0));
  }

  // [66] SchemaElementTest ::= "schema-element" "(" ElementDeclaration ")"
  @Nonnull
  private static XP2SchemaElementTest _convertSchemaElementTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTSCHEMAELEMENTTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final ParserQName aElementDecl = _convertElementDeclaration (aNode.jjtGetChild (0));
    return new XP2SchemaElementTest (aElementDecl);
  }

  // [65] ElementNameOrWildcard ::= ElementName | "*"
  @Nonnull
  private static XP2ElementNameOrWildcard _convertElementNameOrWildcard (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTELEMENTNAMEORWILDCARD);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at last 1 child!");

    if (nChildCount == 0)
      return XP2ElementNameOrWildcard.createWildcard ();

    final ParserQName aElementName = _convertElementName (aNode.jjtGetChild (0));
    return XP2ElementNameOrWildcard.createElement (aElementName);
  }

  // [64] ElementTest ::= "element" "(" (ElementNameOrWildcard ("," TypeName
  // "?"?)?)? ")"
  private static XP2ElementTest _convertElementTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTELEMENTTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 3)
      _throwUnexpectedChildrenCount (aNode, "Expected 0 to 3 children!");

    XP2ElementNameOrWildcard aElementNameOrWildCard = null;
    ParserQName aTypeName = null;
    final boolean bNodeMayBeNilled = nChildCount == 3;

    if (nChildCount >= 1)
    {
      aElementNameOrWildCard = _convertElementNameOrWildcard (aNode.jjtGetChild (0));
      if (nChildCount >= 2)
      {
        aTypeName = _convertTypeName (aNode.jjtGetChild (1));
      }
    }

    return new XP2ElementTest (aElementNameOrWildCard, aTypeName, bNodeMayBeNilled);
  }

  // [63] AttributeDeclaration ::= AttributeName
  @Nonnull
  private static ParserQName _convertAttributeDeclaration (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTATTRIBUTEDECLARATION);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    return _convertAttributeName (aNode.jjtGetChild (0));
  }

  // [62] SchemaAttributeTest ::= "schema-attribute" "(" AttributeDeclaration
  // ")"
  @Nonnull
  private static XP2SchemaAttributeTest _convertSchemaAttributeTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTSCHEMAATTRIBUTETEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final ParserQName aAttributeDecl = _convertAttributeDeclaration (aNode.jjtGetChild (0));
    return new XP2SchemaAttributeTest (aAttributeDecl);
  }

  // [61] AttribNameOrWildcard ::= AttributeName | "*"
  @Nonnull
  private static XP2AttributeNameOrWildcard _convertAttributeNameOrWildcard (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTATTRIBNAMEORWILDCARD);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at last 1 child!");

    if (nChildCount == 0)
      return XP2AttributeNameOrWildcard.createWildcard ();

    final ParserQName aAttributeName = _convertAttributeName (aNode.jjtGetChild (0));
    return XP2AttributeNameOrWildcard.createAttribute (aAttributeName);
  }

  // [60] AttributeTest ::= "attribute" "(" (AttribNameOrWildcard (","
  // TypeName)?)? ")"
  private static XP2AttributeTest _convertAttributeTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTATTRIBUTETEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 0 to 2 children!");

    XP2AttributeNameOrWildcard aAttributeNameOrWildCard = null;
    ParserQName aTypeName = null;

    if (nChildCount >= 1)
    {
      aAttributeNameOrWildCard = _convertAttributeNameOrWildcard (aNode.jjtGetChild (0));
      if (nChildCount >= 2)
      {
        aTypeName = _convertTypeName (aNode.jjtGetChild (1));
      }
    }

    return new XP2AttributeTest (aAttributeNameOrWildCard, aTypeName);
  }

  // [59] PITest ::= "processing-instruction" "(" (NCName | StringLiteral)? ")"
  @Nonnull
  private static XP2ProcessingInstructionTest _convertProcessingInstructionTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTPITEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at last 1 child!");

    if (nChildCount == 1)
    {
      final XP2Node aChildNode = aNode.jjtGetChild (0);
      _expectNodeType (aChildNode, ParserXP2TreeConstants.JJTPISTRINGLITERAL);
      return new XP2ProcessingInstructionTest (new XP2StringLiteral (aChildNode.getText ()));
    }

    final String sText = aNode.getText ();
    if (sText == null)
      return new XP2ProcessingInstructionTest ();
    return new XP2ProcessingInstructionTest (new XP2NCName (sText));
  }

  // [58] CommentTest ::= "comment" "(" ")"
  @Nonnull
  private static XP2CommentTest _convertCommentTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTCOMMENTTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no child!");

    return new XP2CommentTest ();
  }

  // [57] TextTest ::= "text" "(" ")"
  @Nonnull
  private static XP2TextTest _convertTextTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTTEXTTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no child!");

    return new XP2TextTest ();
  }

  // [56] DocumentTest ::= "document-node" "(" (ElementTest |
  // SchemaElementTest)? ")"
  private static XP2DocumentTest _convertDocumentTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTDOCUMENTTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 0 to 1 children!");

    IXP2KindTest aKindTest = null;
    if (nChildCount == 1)
    {
      final XP2Node aChildNode = aNode.jjtGetChild (0);
      if (aChildNode.getNodeType () == ParserXP2TreeConstants.JJTELEMENTTEST)
        aKindTest = _convertElementTest (aChildNode);
      else
        aKindTest = _convertSchemaElementTest (aChildNode);
    }

    return new XP2DocumentTest (aKindTest);
  }

  // [55] AnyKindTest ::= "node" "(" ")"
  @Nonnull
  private static XP2NodeTest _convertAnyKindTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTANYKINDTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no child!");

    return new XP2NodeTest ();
  }

  // [54] KindTest ::= DocumentTest| ElementTest| AttributeTest|
  // SchemaElementTest| SchemaAttributeTest| PITest| CommentTest| TextTest|
  // AnyKindTest
  @Nonnull
  private static IXP2KindTest _convertKindTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTKINDTEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 child!");

    final XP2Node aChildNode = aNode.jjtGetChild (0);
    switch (aChildNode.getNodeType ())
    {
      case ParserXP2TreeConstants.JJTDOCUMENTTEST:
        return _convertDocumentTest (aChildNode);
      case ParserXP2TreeConstants.JJTELEMENTTEST:
        return _convertElementTest (aChildNode);
      case ParserXP2TreeConstants.JJTATTRIBUTETEST:
        return _convertAttributeTest (aChildNode);
      case ParserXP2TreeConstants.JJTSCHEMAELEMENTTEST:
        return _convertSchemaElementTest (aChildNode);
      case ParserXP2TreeConstants.JJTSCHEMAATTRIBUTETEST:
        return _convertSchemaAttributeTest (aChildNode);
      case ParserXP2TreeConstants.JJTPITEST:
        return _convertProcessingInstructionTest (aChildNode);
      case ParserXP2TreeConstants.JJTCOMMENTTEST:
        return _convertCommentTest (aChildNode);
      case ParserXP2TreeConstants.JJTTEXTTEST:
        return _convertTextTest (aChildNode);
      case ParserXP2TreeConstants.JJTANYKINDTEST:
        return _convertAnyKindTest (aChildNode);
      default:
        throw new XP2HandlingException (aChildNode,
                                        "Invalid node type for kind test: " +
                                                    ParserXP2TreeConstants.jjtNodeName[aChildNode.getNodeType ()]);
    }
  }

  // [53] AtomicType ::= QName
  @Nonnull
  private static ParserQName _convertAtomicType (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTATOMICTYPE);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    return (ParserQName) aNode.getValue ();
  }

  // [52] ItemType ::= KindTest | ("item" "(" ")") | AtomicType
  // [51] OccurrenceIndicator ::= "?" | "*" | "+"
  // [50] SequenceType ::= ("empty-sequence" "(" ")") | (ItemType
  // OccurrenceIndicator?)
  @Nonnull
  private static IXP2SequenceType _convertSequenceType (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTSEQUENCETYPE);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 2)
      _throwUnexpectedChildrenCount (aNode, "Expected at last 2 children!");

    if (nChildCount == 0)
      return new XP2EmptySequence ();

    // Find occurrence indicator (defaulting to "default once")
    EXP2OccurrenceIndicator eOccurrenceIndicator;
    if (nChildCount == 2)
    {
      final String sOccurrenceIndicator = aNode.jjtGetChild (1).getText ();
      eOccurrenceIndicator = EXP2OccurrenceIndicator.getFromTextOrThrow (sOccurrenceIndicator);
    }
    else
      eOccurrenceIndicator = EXP2OccurrenceIndicator.DEFAULT_ONCE;

    // Check content of kind type
    final XP2Node aItemTypeNode = aNode.jjtGetChild (0);
    _expectNodeType (aItemTypeNode, ParserXP2TreeConstants.JJTITEMTYPE);
    final int nItemTypeChildCount = aItemTypeNode.jjtGetNumChildren ();
    if (nItemTypeChildCount > 1)
      _throwUnexpectedChildrenCount (aItemTypeNode, "Expected at last 1 child!");

    if (nItemTypeChildCount == 0)
    {
      // It's "item()"
      return new XP2SequenceTypeItem (eOccurrenceIndicator);
    }

    final XP2Node aItemTypeChildNode = aItemTypeNode.jjtGetChild (0);
    if (aItemTypeChildNode.getNodeType () == ParserXP2TreeConstants.JJTKINDTEST)
    {
      // any kind test
      final IXP2KindTest aKindTest = _convertKindTest (aItemTypeChildNode);
      return new XP2SequenceTypeKindTest (aKindTest, eOccurrenceIndicator);
    }

    // Atomic type
    final ParserQName aAtomicType = _convertAtomicType (aItemTypeChildNode);
    return new XP2SequenceTypeAtomicType (aAtomicType, eOccurrenceIndicator);
  }

  // [49] SingleType ::= AtomicType "?"?
  @Nonnull
  private static XP2SingleType _convertSingleType (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTSINGLETYPE);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    final ParserQName aAtomicType = _convertAtomicType (aNode.jjtGetChild (0));
    final boolean bEmptySequenceAllowed = nChildCount == 2;

    return new XP2SingleType (aAtomicType, bEmptySequenceAllowed);
  }

  // [48] FunctionCall ::= QName "(" (ExprSingle ("," ExprSingle)*)? ")"
  @Nonnull
  private static XP2FunctionCall _convertFunctionCall (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTFUNCTIONCALL);
    final int nChildCount = aNode.jjtGetNumChildren ();

    final ParserQName aFunctionName = (ParserQName) aNode.getValue ();

    final ICommonsList <IXP2Expression> aExpressions = new CommonsArrayList<> ();
    for (int i = 0; i < nChildCount; ++i)
    {
      final XP2Node aChildNode = aNode.jjtGetChild (i);
      aExpressions.add (_convertExpressionSingle (aChildNode));
    }

    return new XP2FunctionCall (aFunctionName, aExpressions);
  }

  // [47] ContextItemExpr ::= "."
  @Nonnull
  private static XP2ContextItemExpression _convertContextItemExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTCONTEXTITEMEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no 0 child!");

    return new XP2ContextItemExpression ();
  }

  // [46] ParenthesizedExpr ::= "(" Expr? ")"
  @Nonnull
  private static XP2ParenthesizedExpression _convertParenthesizedExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTPARENTHESIZEDEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at last 1 child!");

    XP2ExpressionList aExprList = null;
    if (nChildCount == 1)
      aExprList = _convertExpressionList (aNode.jjtGetChild (0));

    return new XP2ParenthesizedExpression (aExprList);
  }

  // [45] VarName ::= QName
  @Nonnull
  private static ParserQName _convertVarName (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTVARNAME);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    return (ParserQName) aNode.getValue ();
  }

  // [44] VarRef ::= "$" VarName
  @Nonnull
  private static XP2VariableReference _convertVarRef (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTVARREF);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final ParserQName aVarName = _convertVarName (aNode.jjtGetChild (0));
    return new XP2VariableReference (aVarName);
  }

  // [43] NumericLiteral ::= IntegerLiteral | DecimalLiteral | DoubleLiteral
  // [42] Literal ::= NumericLiteral | StringLiteral
  @Nonnull
  private static IXP2LiteralExpression _convertLiteral (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTLITERAL);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no child!");

    final Object aValue = aNode.getValue ();
    if (aValue instanceof BigInteger)
      return new XP2NumericLiteral ((BigInteger) aValue);
    if (aValue instanceof BigDecimal)
      return new XP2NumericLiteral ((BigDecimal) aValue);
    if (aValue instanceof String)
      return new XP2StringLiteral ((String) aValue);
    throw new XP2HandlingException (aNode, "Invalid node value type: " + aValue.getClass ());
  }

  // [41] PrimaryExpr ::= Literal | VarRef | ParenthesizedExpr | ContextItemExpr
  // | FunctionCall
  @Nonnull
  private static IXP2PrimaryExpression _convertPrimaryExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTPRIMARYEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final XP2Node aChildNode = aNode.jjtGetChild (0);
    switch (aChildNode.getNodeType ())
    {
      case ParserXP2TreeConstants.JJTLITERAL:
        return _convertLiteral (aChildNode);
      case ParserXP2TreeConstants.JJTVARREF:
        return _convertVarRef (aChildNode);
      case ParserXP2TreeConstants.JJTPARENTHESIZEDEXPR:
        return _convertParenthesizedExpression (aChildNode);
      case ParserXP2TreeConstants.JJTCONTEXTITEMEXPR:
        return _convertContextItemExpression (aChildNode);
      case ParserXP2TreeConstants.JJTFUNCTIONCALL:
        return _convertFunctionCall (aChildNode);
      default:
        throw new XP2HandlingException (aChildNode, "Invalid node type for primary expression!");
    }
  }

  // [40] Predicate ::= "[" Expr "]"
  @Nonnull
  private static XP2Predicate _convertPredicate (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTPREDICATE);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final XP2ExpressionList aExpressionList = _convertExpressionList (aNode.jjtGetChild (0));
    return new XP2Predicate (aExpressionList);
  }

  // [39] PredicateList ::= Predicate*
  @Nonnull
  private static XP2PredicateList _convertPredicateList (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTPREDICATELIST);
    final int nChildCount = aNode.jjtGetNumChildren ();

    final ICommonsList <XP2Predicate> aPredicates = new CommonsArrayList<> ();
    for (int i = 0; i < nChildCount; ++i)
    {
      final XP2Predicate aPredicate = _convertPredicate (aNode.jjtGetChild (i));
      aPredicates.add (aPredicate);
    }

    return new XP2PredicateList (aPredicates);
  }

  // [38] FilterExpr ::= PrimaryExpr PredicateList
  @Nonnull
  private static XP2FilterExpression _convertFilterExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTFILTEREXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 2 children!");

    final IXP2PrimaryExpression aExpr = _convertPrimaryExpression (aNode.jjtGetChild (0));
    final XP2PredicateList aPredicateList = _convertPredicateList (aNode.jjtGetChild (1));
    return new XP2FilterExpression (aExpr, aPredicateList);
  }

  // [37] Wildcard ::= "*" | (NCName ":" "*") | ("*" ":" NCName)
  @Nonnull
  private static IXP2NameTest _convertWildcard (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTWILDCARD);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    if (aNode.getValue () == null)
    {
      // Only "*"
      return new XP2WildcardTest ();
    }

    final boolean bNamespaceIsWildcard = ((Boolean) aNode.getValue ()).booleanValue ();
    if (bNamespaceIsWildcard)
      return new XP2NamespaceIsWildcardTest (aNode.getText ());

    // else local name is wildcard
    return new XP2LocalNameIsWildcardTest (aNode.getText ());
  }

  // [36] NameTest ::= QName | Wildcard
  @Nonnull
  private static IXP2NameTest _convertNameTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTNAMETEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount > 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at last 1 child!");

    if (nChildCount == 0)
    {
      final ParserQName aQName = (ParserQName) aNode.getValue ();
      return new XP2QNameTest (aQName);
    }

    // Must be a wildcard
    return _convertWildcard (aNode.jjtGetChild (0));
  }

  // [35] NodeTest ::= KindTest | NameTest
  @Nonnull
  private static IXP2NodeTest _convertNodeTest (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTNODETEST);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final XP2Node aChildNode = aNode.jjtGetChild (0);
    switch (aChildNode.getNodeType ())
    {
      case ParserXP2TreeConstants.JJTKINDTEST:
        return _convertKindTest (aChildNode);
      case ParserXP2TreeConstants.JJTNAMETEST:
        return _convertNameTest (aChildNode);
      default:
        throw new XP2HandlingException (aChildNode, "Invalid node type for node test!");
    }
  }

  // [34] AbbrevReverseStep ::= ".."
  @Nonnull
  private static XP2AbbreviatedReverseStep _convertAbbreviatedReverseStep (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTABBREVREVERSESTEP);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 0 children!");

    return new XP2AbbreviatedReverseStep ();
  }

  // [33] ReverseAxis ::= ("parent" "::") | ("ancestor" "::") |
  // ("preceding-sibling" "::") | ("preceding" "::") | ("ancestor-or-self" "::")
  @Nonnull
  private static EXP2Axis _convertReverseAxis (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTREVERSEAXIS);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no children!");

    final EXP2Axis ret = EXP2Axis.getFromIDOrThrow (aNode.getText ());
    if (!ret.isReverseAxis ())
      throw new IllegalStateException ("Expected a reverse axis!");
    return ret;
  }

  // [32] ReverseStep ::= (ReverseAxis NodeTest) | AbbrevReverseStep
  @Nonnull
  private static IXP2SingleStep _convertReverseStep (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTREVERSESTEP);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
      return _convertAbbreviatedReverseStep (aNode.jjtGetChild (0));

    final EXP2Axis eAxis = _convertReverseAxis (aNode.jjtGetChild (0));
    final IXP2NodeTest aNodeTest = _convertNodeTest (aNode.jjtGetChild (1));
    return new XP2SingleStep (eAxis, aNodeTest);
  }

  // [31] AbbrevForwardStep ::= "@"? NodeTest
  private static IXP2SingleStep _convertAbbreviatedForwardStep (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTABBREVFORWARDSTEP);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
    {
      final IXP2NodeTest aNodeTest = _convertNodeTest (aNode.jjtGetChild (0));
      return new XP2AbbreviatedElementStep (aNodeTest);
    }

    // child "0" is the "@" sign
    final IXP2NodeTest aNodeTest = _convertNodeTest (aNode.jjtGetChild (1));
    return new XP2AbbreviatedAttributeStep (aNodeTest);
  }

  // [30] ForwardAxis ::= ("child" "::") | ("descendant" "::") | ("attribute"
  // "::") | ("self" "::") | ("descendant-or-self" "::") | ("following-sibling"
  // "::") | ("following" "::") | ("namespace" "::")
  @Nonnull
  private static EXP2Axis _convertForwardAxis (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTFORWARDAXIS);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected no children!");

    final EXP2Axis ret = EXP2Axis.getFromIDOrThrow (aNode.getText ());
    if (!ret.isForwardAxis ())
      throw new IllegalStateException ("Expected a forward axis!");
    return ret;
  }

  // [29] ForwardStep ::= (ForwardAxis NodeTest) | AbbrevForwardStep
  @Nonnull
  private static IXP2SingleStep _convertForwardStep (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTFORWARDSTEP);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
      return _convertAbbreviatedForwardStep (aNode.jjtGetChild (0));

    final EXP2Axis eAxis = _convertForwardAxis (aNode.jjtGetChild (0));
    final IXP2NodeTest aNodeTest = _convertNodeTest (aNode.jjtGetChild (1));
    return new XP2SingleStep (eAxis, aNodeTest);
  }

  // [28] AxisStep ::= (ReverseStep | ForwardStep) PredicateList
  @Nonnull
  private static XP2AxisStep _convertAxisStep (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTAXISSTEP);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 2 children!");

    final XP2Node aChildNode = aNode.jjtGetChild (0);
    IXP2SingleStep aSingleStep;
    if (aChildNode.getNodeType () == ParserXP2TreeConstants.JJTREVERSESTEP)
      aSingleStep = _convertReverseStep (aChildNode);
    else
      aSingleStep = _convertForwardStep (aChildNode);

    final XP2PredicateList aPredicateList = _convertPredicateList (aNode.jjtGetChild (1));
    return new XP2AxisStep (aSingleStep, aPredicateList);
  }

  // [27] StepExpr ::= FilterExpr | AxisStep
  @Nonnull
  private static IXP2StepExpression _convertStepExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTSTEPEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final XP2Node aChildNode = aNode.jjtGetChild (0);
    if (aChildNode.getNodeType () == ParserXP2TreeConstants.JJTFILTEREXPR)
      return _convertFilterExpression (aChildNode);

    return _convertAxisStep (aChildNode);
  }

  // [26] RelativePathExpr ::= StepExpr (("/" | "//") StepExpr)*
  @Nonnull
  private static IXP2Expression _convertRelativePathExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTRELATIVEPATHEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1 || (nChildCount % 2) != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or an odd number of children!");

    if (nChildCount == 1)
    {
      // no relative path expression - take only the contained step expression
      return _convertStepExpression (aNode.jjtGetChild (0));
    }

    // Maintain the order and make no prefix/postfix differentiation
    final ICommonsList <IXP2Object> aElements = new CommonsArrayList<> ();
    for (int i = 0; i < nChildCount; ++i)
    {
      final XP2Node aChildNode = aNode.jjtGetChild (i);
      if ((i % 2) == 0)
        aElements.add (_convertStepExpression (aChildNode));
      else
        aElements.add (EXP2PathOperator.getFromIDOrThrow (aChildNode.getText ()));
    }
    return new XP2RelativePathExpression (aElements);
  }

  // [25] PathExpr ::= ("/" RelativePathExpr?) | ("//" RelativePathExpr) |
  // RelativePathExpr
  @Nonnull
  private static IXP2Expression _convertPathExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTPATHEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();

    final String sOperator = aNode.getText ();
    if (sOperator == null)
    {
      // No operator present - use only relative path
      return _convertRelativePathExpression (aNode.jjtGetChild (0));
    }

    final EXP2PathOperator eOperator = EXP2PathOperator.getFromIDOrThrow (sOperator);
    final IXP2Expression aExpr = nChildCount == 0 ? null : _convertRelativePathExpression (aNode.jjtGetChild (0));
    return new XP2PathExpression (eOperator, aExpr);
  }

  // [21] ValueExpr ::= PathExpr
  @Nonnull
  private static IXP2Expression _convertValueExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTVALUEEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    return _convertPathExpression (aNode.jjtGetChild (0));
  }

  // [20] UnaryExpr ::= ("-" | "+")* ValueExpr
  @Nonnull
  private static IXP2Expression _convertUnaryExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTUNARYEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at least 1 child!");

    if (nChildCount == 1)
    {
      // no unary expression - take only the contained value expression
      return _convertValueExpression (aNode.jjtGetChild (0));
    }

    // Multiple operators are allowed
    EXP2Operator eFinalOperator = null;
    for (int i = 0; i < nChildCount - 1; ++i)
    {
      final EXP2Operator eOperator = EXP2Operator.getFromIDOrThrow (aNode.jjtGetChild (i).getText ());
      if (eFinalOperator == null)
        eFinalOperator = eOperator;
      else
        if (eOperator != eFinalOperator)
        {
          // ("+" and "-") or ("-" and "+") -> "-"
          eFinalOperator = EXP2Operator.MINUS;
        }
    }
    final IXP2Expression aExpr = _convertValueExpression (aNode.jjtGetChild (nChildCount - 1));
    final XP2UnaryExpression ret = new XP2UnaryExpression (eFinalOperator, aExpr);
    return ret;
  }

  // [19] CastExpr ::= UnaryExpr ( "cast" "as" SingleType )?
  @Nonnull
  private static IXP2Expression _convertCastExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTCASTEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
    {
      // no cast expression - take only the contained unary expression
      return _convertUnaryExpression (aNode.jjtGetChild (0));
    }

    final IXP2Expression aLeft = _convertUnaryExpression (aNode.jjtGetChild (0));
    final XP2SingleType aSingleType = _convertSingleType (aNode.jjtGetChild (1));
    final XP2SingleTypeExpression ret = new XP2SingleTypeExpression (aLeft, EXP2Operator.CAST_AS, aSingleType);
    return ret;
  }

  // [18] CastableExpr ::= CastExpr ( "castable" "as" SingleType )?
  @Nonnull
  private static IXP2Expression _convertCastableAsExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTCASTABLEEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
    {
      // no castable expression - take only the contained cast expression
      return _convertCastExpression (aNode.jjtGetChild (0));
    }

    final IXP2Expression aLeft = _convertCastExpression (aNode.jjtGetChild (0));
    final XP2SingleType aSingleType = _convertSingleType (aNode.jjtGetChild (1));
    final XP2SingleTypeExpression ret = new XP2SingleTypeExpression (aLeft, EXP2Operator.CASTABLE_AS, aSingleType);
    return ret;
  }

  // [17] TreatExpr ::= CastableExpr ( "treat" "as" SequenceType )?
  @Nonnull
  private static IXP2Expression _convertTreatAsExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTTREATEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
    {
      // no treat as expression - take only the contained castable expression
      return _convertCastableAsExpression (aNode.jjtGetChild (0));
    }

    final IXP2Expression aLeft = _convertCastableAsExpression (aNode.jjtGetChild (0));
    final IXP2SequenceType aSequenceType = _convertSequenceType (aNode.jjtGetChild (1));
    final XP2SequenceTypeExpression ret = new XP2SequenceTypeExpression (aLeft, EXP2Operator.TREAT_AS, aSequenceType);
    return ret;
  }

  // [16] InstanceofExpr ::= TreatExpr ( "instance" "of" SequenceType )?
  @Nonnull
  private static IXP2Expression _convertInstanceofExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTINSTANCEOFEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
    {
      // no instanceof expression - take only the contained treat as expression
      return _convertTreatAsExpression (aNode.jjtGetChild (0));
    }

    final IXP2Expression aExpr = _convertTreatAsExpression (aNode.jjtGetChild (0));
    final IXP2SequenceType aSequenceType = _convertSequenceType (aNode.jjtGetChild (1));
    final XP2SequenceTypeExpression ret = new XP2SequenceTypeExpression (aExpr,
                                                                         EXP2Operator.INSTANCE_OF,
                                                                         aSequenceType);
    return ret;
  }

  // [15] IntersectExceptExpr ::= InstanceofExpr ( ("intersect" | "except")
  // InstanceofExpr )*
  @Nonnull
  private static IXP2Expression _convertIntersectExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTINTERSECTEXCEPTEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1 || (nChildCount % 2) != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or an odd number of children!");

    if (nChildCount == 1)
    {
      // no intersect/except. expression - take only the contained instanceof
      // expression
      return _convertInstanceofExpression (aNode.jjtGetChild (0));
    }

    int nCurIndex = nChildCount - 1;
    IXP2Expression aTemp = null;
    while (nCurIndex >= 0)
    {
      if (aTemp == null)
        aTemp = _convertInstanceofExpression (aNode.jjtGetChild (nCurIndex--));

      final EXP2Operator eOperator = EXP2Operator.getFromIDOrThrow (aNode.jjtGetChild (nCurIndex--).getText ());
      final IXP2Expression aLeft = _convertInstanceofExpression (aNode.jjtGetChild (nCurIndex--));
      aTemp = new XP2BinaryExpression (aLeft, eOperator, aTemp);
    }
    return aTemp;
  }

  // [14] UnionExpr ::= IntersectExceptExpr ( ("union" | "|")
  // IntersectExceptExpr )*
  @Nonnull
  private static IXP2Expression _convertUnionExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTUNIONEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1 || (nChildCount % 2) != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or an odd number of children!");

    if (nChildCount == 1)
    {
      // no union expression - take only the contained intersect/except
      // expression
      return _convertIntersectExpression (aNode.jjtGetChild (0));
    }

    int nCurIndex = nChildCount - 1;
    IXP2Expression aTemp = null;
    while (nCurIndex >= 0)
    {
      if (aTemp == null)
        aTemp = _convertIntersectExpression (aNode.jjtGetChild (nCurIndex--));

      final EXP2Operator eOperator = EXP2Operator.getFromIDOrThrow (aNode.jjtGetChild (nCurIndex--).getText ());
      final IXP2Expression aLeft = _convertIntersectExpression (aNode.jjtGetChild (nCurIndex--));
      aTemp = new XP2BinaryExpression (aLeft, eOperator, aTemp);
    }
    return aTemp;
  }

  // [13] MultiplicativeExpr ::= UnionExpr ( ("*" | "div" | "idiv" | "mod")
  // UnionExpr )*
  @Nonnull
  private static IXP2Expression _convertMultiplicativeExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTMULTIPLICATIVEEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1 || (nChildCount % 2) != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or an odd number of children!");

    if (nChildCount == 1)
    {
      // no multiplication - take only the contained union expression
      return _convertUnionExpression (aNode.jjtGetChild (0));
    }

    int nCurIndex = nChildCount - 1;
    IXP2Expression aTemp = null;
    while (nCurIndex >= 0)
    {
      if (aTemp == null)
        aTemp = _convertUnionExpression (aNode.jjtGetChild (nCurIndex--));

      final EXP2Operator eOperator = EXP2Operator.getFromIDOrThrow (aNode.jjtGetChild (nCurIndex--).getText ());
      final IXP2Expression aLeft = _convertUnionExpression (aNode.jjtGetChild (nCurIndex--));
      aTemp = new XP2BinaryExpression (aLeft, eOperator, aTemp);
    }
    return aTemp;
  }

  // [12] AdditiveExpr ::= MultiplicativeExpr ( ("+" | "-") MultiplicativeExpr
  // )*
  @Nonnull
  private static IXP2Expression _convertAdditiveExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTADDITIVEEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1 || (nChildCount % 2) != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or an odd number of children!");

    if (nChildCount == 1)
    {
      // no additive expression - take only the contained multiplicative
      // expression
      return _convertMultiplicativeExpression (aNode.jjtGetChild (0));
    }

    int nCurIndex = nChildCount - 1;
    IXP2Expression aTemp = null;
    while (nCurIndex >= 0)
    {
      if (aTemp == null)
        aTemp = _convertMultiplicativeExpression (aNode.jjtGetChild (nCurIndex--));

      final EXP2Operator eOperator = EXP2Operator.getFromIDOrThrow (aNode.jjtGetChild (nCurIndex--).getText ());
      final IXP2Expression aLeft = _convertMultiplicativeExpression (aNode.jjtGetChild (nCurIndex--));
      aTemp = new XP2BinaryExpression (aLeft, eOperator, aTemp);
    }
    return aTemp;
  }

  // [11] RangeExpr ::= AdditiveExpr ( "to" AdditiveExpr )?
  @Nonnull
  private static IXP2Expression _convertRangeExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTRANGEEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 2 children!");

    if (nChildCount == 1)
    {
      // no "to" - take only the contained additive expression
      return _convertAdditiveExpression (aNode.jjtGetChild (0));
    }

    final IXP2Expression aLeft = _convertAdditiveExpression (aNode.jjtGetChild (0));
    final IXP2Expression aRight = _convertAdditiveExpression (aNode.jjtGetChild (1));
    final XP2BinaryExpression ret = new XP2BinaryExpression (aLeft, EXP2Operator.TO, aRight);
    return ret;
  }

  // [24] NodeComp ::= "is" | "<<" | ">>"
  // [23] ValueComp ::= "eq" | "ne" | "lt" | "le" | "gt" | "ge"
  // [22] GeneralComp ::= "=" | "!=" | "<" | "<=" | ">" | ">="
  // [10] ComparisonExpr ::= RangeExpr ( (ValueComp | GeneralComp | NodeComp)
  // RangeExpr )?
  @Nonnull
  private static IXP2Expression _convertComparisonExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTCOMPARISONEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1 && nChildCount != 3)
      _throwUnexpectedChildrenCount (aNode, "Expected 1 or 3 children!");

    if (nChildCount == 1)
    {
      // no comparator - take only the contained Range expression
      return _convertRangeExpression (aNode.jjtGetChild (0));
    }

    final IXP2Expression aLeft = _convertRangeExpression (aNode.jjtGetChild (0));
    final EXP2Operator eOperator = EXP2Operator.getFromIDOrThrow (aNode.jjtGetChild (1).getText ());
    final IXP2Expression aRight = _convertRangeExpression (aNode.jjtGetChild (2));
    final XP2BinaryExpression ret = new XP2BinaryExpression (aLeft, eOperator, aRight);
    return ret;
  }

  // [9] AndExpr ::= ComparisonExpr ( "and" ComparisonExpr )*
  @Nonnull
  private static IXP2Expression _convertAndExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTANDEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at least 1 child!");

    if (nChildCount == 1)
    {
      // no "and" - take only the contained Comparison expression
      return _convertComparisonExpression (aNode.jjtGetChild (0));
    }

    int nCurIndex = nChildCount - 1;
    IXP2Expression aTemp = null;
    while (nCurIndex >= 0)
    {
      final IXP2Expression aExpr = _convertComparisonExpression (aNode.jjtGetChild (nCurIndex));
      if (aTemp == null)
        aTemp = aExpr;
      else
        aTemp = new XP2BinaryExpression (aExpr, EXP2Operator.AND, aTemp);
      nCurIndex--;
    }
    return aTemp;
  }

  // [8] OrExpr ::= AndExpr ( "or" AndExpr )*
  @Nonnull
  private static IXP2Expression _convertOrExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTOREXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 1)
      _throwUnexpectedChildrenCount (aNode, "Expected at least 1 child!");

    if (nChildCount == 1)
    {
      // no "or" - take only the contained "and" expression
      return _convertAndExpression (aNode.jjtGetChild (0));
    }

    int nCurIndex = nChildCount - 1;
    IXP2Expression aTemp = null;
    while (nCurIndex >= 0)
    {
      final IXP2Expression aExpr = _convertAndExpression (aNode.jjtGetChild (nCurIndex));
      if (aTemp == null)
        aTemp = aExpr;
      else
        aTemp = new XP2BinaryExpression (aExpr, EXP2Operator.OR, aTemp);
      nCurIndex--;
    }
    return aTemp;
  }

  // [7] IfExpr ::= "if" "(" Expr ")" "then" ExprSingle "else" ExprSingle
  @Nonnull
  private static XP2IfExpression _convertIfExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTIFEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 3)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 3 children!");

    final XP2ExpressionList aTestExprs = _convertExpressionList (aNode.jjtGetChild (0));
    final IXP2Expression aThenExpr = _convertExpressionSingle (aNode.jjtGetChild (1));
    final IXP2Expression aElseExpr = _convertExpressionSingle (aNode.jjtGetChild (2));

    final XP2IfExpression ret = new XP2IfExpression (aTestExprs, aThenExpr, aElseExpr);
    return ret;
  }

  // [6] QuantifiedExpr ::= ("some" | "every") "$" VarName "in" ExprSingle (","
  // "$" VarName "in" ExprSingle)* "satisfies" ExprSingle
  @Nonnull
  private static XP2QuantifiedExpression _convertQuantifiedExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTQUANTIFIEDEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 4)
      _throwUnexpectedChildrenCount (aNode, "Expected at least 4 children!");
    if ((nChildCount % 2) != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected an even number of children!");

    final EXP2QuantifiedExpressionType eType = EXP2QuantifiedExpressionType.getFromIDOrThrow (aNode.jjtGetChild (0)
                                                                                                   .getText ());
    final int nPairs = (nChildCount / 2) - 1;
    final ICommonsList <XP2VarNameAndExpression> aClauses = new CommonsArrayList<> ();
    for (int i = 0; i < nPairs; ++i)
    {
      final ParserQName aVarName = _convertVarName (aNode.jjtGetChild (1 + i * 2));
      final IXP2Expression aExpression = _convertExpressionSingle (aNode.jjtGetChild (2 + i * 2));
      aClauses.add (new XP2VarNameAndExpression (aVarName, aExpression));
    }

    final IXP2Expression aSatisfyExpr = _convertExpressionSingle (aNode.jjtGetChild (nChildCount - 1));

    final XP2QuantifiedExpression ret = new XP2QuantifiedExpression (eType, aClauses, aSatisfyExpr);
    return ret;
  }

  // [5] SimpleForClause ::= "for" "$" VarName "in" ExprSingle ("," "$" VarName
  // "in" ExprSingle)*
  @Nonnull
  private static ICommonsList <XP2VarNameAndExpression> _convertSimpleForClause (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTSIMPLEFORCLAUSE);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount < 2)
      _throwUnexpectedChildrenCount (aNode, "Expected at least 2 children!");
    if ((nChildCount % 2) != 0)
      _throwUnexpectedChildrenCount (aNode, "Expected an even number of children!");

    final ICommonsList <XP2VarNameAndExpression> ret = new CommonsArrayList<> ();
    for (int i = 0; i < nChildCount; i += 2)
    {
      final ParserQName aVarName = _convertVarName (aNode.jjtGetChild (i));
      final IXP2Expression aExpression = _convertExpressionSingle (aNode.jjtGetChild (i + 1));
      ret.add (new XP2VarNameAndExpression (aVarName, aExpression));
    }
    return ret;
  }

  // [4] ForExpr ::= SimpleForClause "return" ExprSingle
  @Nonnull
  private static XP2ForExpression _convertForExpression (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTFOREXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 2)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 2 children!");

    final ICommonsList <XP2VarNameAndExpression> aForClause = _convertSimpleForClause (aNode.jjtGetChild (0));
    final IXP2Expression aReturnExpression = _convertExpressionSingle (aNode.jjtGetChild (1));

    final XP2ForExpression ret = new XP2ForExpression (aForClause, aReturnExpression);
    return ret;
  }

  // [3] ExprSingle ::= ForExpr | QuantifiedExpr | IfExpr | OrExpr
  @Nonnull
  private static IXP2Expression _convertExpressionSingle (@Nonnull final XP2Node aNode)
  {
    switch (aNode.getNodeType ())
    {
      case ParserXP2TreeConstants.JJTFOREXPR:
        return _convertForExpression (aNode);
      case ParserXP2TreeConstants.JJTQUANTIFIEDEXPR:
        return _convertQuantifiedExpression (aNode);
      case ParserXP2TreeConstants.JJTIFEXPR:
        return _convertIfExpression (aNode);
      case ParserXP2TreeConstants.JJTOREXPR:
        return _convertOrExpression (aNode);
      default:
        throw new XP2HandlingException (aNode, "Invalid node type for expression!");
    }
  }

  // [2] Expr ::= ExprSingle ("," ExprSingle)*
  @Nonnull
  private static XP2ExpressionList _convertExpressionList (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTEXPR);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount == 0)
      _throwUnexpectedChildrenCount (aNode, "Expected at least 1 child!");

    final ICommonsList <IXP2Expression> aExpressions = new CommonsArrayList<> ();
    for (int i = 0; i < nChildCount; ++i)
    {
      final XP2Node aChildNode = aNode.jjtGetChild (i);
      aExpressions.add (_convertExpressionSingle (aChildNode));
    }

    final XP2ExpressionList ret = new XP2ExpressionList (aExpressions);
    return ret;
  }

  // [1] XPath ::= Expr
  @Nullable
  public static XP2 convertToDomainObject (@Nonnull final XP2Node aNode)
  {
    _expectNodeType (aNode, ParserXP2TreeConstants.JJTROOT);
    final int nChildCount = aNode.jjtGetNumChildren ();
    if (nChildCount != 1)
      _throwUnexpectedChildrenCount (aNode, "Expected exactly 1 child!");

    final XP2 ret = new XP2 (_convertExpressionList (aNode.jjtGetChild (0)));
    return ret;
  }
}
