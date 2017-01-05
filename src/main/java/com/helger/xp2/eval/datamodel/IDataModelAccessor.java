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
package com.helger.xp2.eval.datamodel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import com.helger.commons.collection.ext.ICommonsList;

public interface IDataModelAccessor <NODETYPE>
{
  /**
   * 5.1 attributes Accessor<br>
   * dm:attributes($n as node()) as attribute()*<br>
   * <br>
   * The dm:attributes accessor returns the attributes of a node as a sequence
   * containing zero or more Attribute Nodes. The order of Attribute Nodes is
   * stable but implementation dependent.<br>
   * <br>
   * It is defined on all seven node kinds.
   *
   * @param aNode
   *        Source node
   * @return All attributes
   */
  @Nonnull
  ICommonsList <NODETYPE> getAllAttributes (@Nonnull NODETYPE aNode);

  /**
   * 5.2 base-uri Accessor<br>
   * dm:base-uri($n as node()) as xs:anyURI?<br>
   * <br>
   * The dm:base-uri accessor returns the base URI of a node as a sequence
   * containing zero or one URI reference. For more information about base URIs,
   * see [XML Base].<br>
   * <br>
   * It is defined on all seven node kinds.
   *
   * @param aNode
   *        Source node
   * @return Base URI
   */
  @Nullable
  String getBaseURI (@Nonnull NODETYPE aNode);

  /**
   * 5.3 children Accessor<br>
   * dm:children($n as node()) as node()*<br>
   * <br>
   * The dm:children accessor returns the children of a node as a sequence
   * containing zero or more nodes.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   *
   * @param aNode
   *        Source node
   * @return All attributes
   */
  @Nonnull
  ICommonsList <NODETYPE> getAllChildren (@Nonnull NODETYPE aNode);

  /**
   * 5.4 document-uri Accessor<br>
   * dm:document-uri($node as node()) as xs:anyURI?<br>
   * <br>
   * The dm:document-uri accessor returns the absolute URI of the resource from
   * which the Document Node was constructed, if the absolute URI is available.
   * If there is no URI available, or if it cannot be made absolute when the
   * Document Node is constructed, or if it is used on a node other than a
   * Document Node, the empty sequence is returned.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   *
   * @param aNode
   *        Source node
   * @return Document URI
   */
  @Nullable
  String getDocumentURI (@Nonnull NODETYPE aNode);

  /**
   * 5.5 is-id Accessor<br>
   * dm:is-id($node as node()) as xs:boolean?<br>
   * <br>
   * The dm:is-id accessor returns true if the node is an XML ID. Exactly what
   * constitutes an ID depends in part on how the data model was constructed,
   * see 6.2 Element Nodes and 6.3 Attribute Nodes.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   * 
   * @param aNode
   *        Source node
   * @return <code>true</code> if it is an XML ID.
   */
  boolean isID (@Nonnull NODETYPE aNode);

  /**
   * 5.6 is-idrefs Accessor<br>
   * dm:is-idrefs($node as node()) as xs:boolean?<br>
   * <br>
   * The dm:is-idrefs accessor returns true if the node is an XML IDREF or
   * IDREFS. Exactly what constitutes an IDREF or IDREFS depends in part on how
   * the data model was constructed, see 6.2 Element Nodes and 6.3 Attribute
   * Nodes.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  boolean isIDREFS (@Nonnull NODETYPE aNode);

  /**
   * 5.7 namespace-bindings Accessor<br>
   * dm:namespace-bindings($node as node()) as xs:string*<br>
   * <br>
   * The dm:namespace-bindings accessor returns the dynamic, in-scope namespaces
   * associated with a node as a set of prefix/URI pairs, using an
   * implementation-dependent representation.<br>
   * <br>
   * The prefix for the default namespace is the zero length string.<br>
   * <br>
   * The dm:namespace-bindings accessor is defined on all seven node kinds. <br>
   * <br>
   * Note: this accessor and the namespace-nodes accessor provide two views of
   * the same information.<br>
   */
  ICommonsList <String> getAllNamespaceBindings (@Nonnull NODETYPE aNode);

  /**
   * 5.8 namespace-nodes Accessor<br>
   * dm:namespace-nodes($n as node()) as node()*<br>
   * <br>
   * The dm:namespace-nodes accessor returns the dynamic, in-scope namespaces
   * associated with a node as a sequence containing zero or more Namespace
   * Nodes. The order of Namespace Nodes is stable but implementation dependent.
   * <br>
   * <br>
   * It is defined on all seven node kinds.<br>
   * <br>
   * Note: this accessor and the namespace-bindings accessor provide two views
   * of the same information. Implementations that do not need to expose
   * Namespace Nodes might choose not to implement this accessor.<br>
   */
  ICommonsList <NODETYPE> getAllNamespaceNodes (@Nonnull NODETYPE aNode);

  /**
   * 5.9 nilled Accessor<br>
   * dm:nilled($n as node()) as xs:boolean?<br>
   * <br>
   * The dm:nilled accessor returns true if the node is "nilled". [Schema Part
   * 1] introduced the nilled mechanism to signal that an element should be
   * accepted as valid when it has no content even when it has a content type
   * which does not require or even necessarily allow empty content.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  boolean isNilled (@Nonnull NODETYPE aNode);

  /**
   * 5.10 node-kind Accessor<br>
   * dm:node-kind($n as node()) as xs:string<br>
   * <br>
   * The dm:node-kind accessor returns a string identifying the kind of node. It
   * will be one of the following, depending on the kind of node: “attribute”,
   * “comment”, “document”, “element”, “namespace” “processing-instruction”, or
   * “text”.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   *
   * @see ENodeKind
   */
  @Nullable
  String getNodeKind (@Nonnull NODETYPE aNode);

  /**
   * 5.11 node-name Accessor<br>
   * dm:node-name($n as node()) as xs:QName?<br>
   * <br>
   * The dm:node-name accessor returns the name of the node as a sequence of
   * zero or one xs:QNames. Note that the QName value includes an optional
   * prefix as described in 3.3.3 QNames and NOTATIONS.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  QName getNodeName (@Nonnull NODETYPE aNode);

  /**
   * 5.12 parent Accessor<br>
   * dm:parent($n as node()) as node()?<br>
   * <br>
   * The dm:parent accessor returns the parent of a node as a sequence
   * containing zero or one nodes.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  NODETYPE getParent (@Nonnull NODETYPE aNode);

  /**
   * 5.13 string-value Accessor<br>
   * dm:string-value($n as node()) as xs:string<br>
   * <br>
   * The dm:string-value accessor returns the string value of a node.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  String getStringValue (@Nonnull NODETYPE aNode);

  /**
   * 5.14 type-name Accessor<br>
   * dm:type-name($n as node()) as xs:QName?<br>
   * <br>
   * The dm:type-name accessor returns the name of the schema type of a node as
   * a sequence of zero or one xs:QNames.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  QName getTypeName (@Nonnull NODETYPE aNode);

  /**
   * 5.15 typed-value Accessor<br>
   * dm:typed-value($n as node()) as xs:anyAtomicType*<br>
   * <br>
   * The dm:typed-value accessor returns the typed-value of the node as a
   * sequence of zero or more atomic values.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  ICommonsList <?> getTypedValue (@Nonnull NODETYPE aNode);

  /**
   * 5.16 unparsed-entity-public-id Accessor<br>
   * dm:unparsed-entity-public-id( $node as node(), $entityname as xs:string) as
   * xs:string?<br>
   * <br>
   * The dm:unparsed-entity-public-id accessor returns the public identifier of
   * an unparsed external entity declared in the specified document. If no
   * entity with the name specified in $entityname exists, or if the entity is
   * not an external unparsed entity, or if the entity has no public identifier,
   * the empty sequence is returned.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  String getUnparsedEntityPublicID (@Nonnull NODETYPE aNode, @Nonnull String sEntityName);

  /**
   * 5.17 unparsed-entity-system-id Accessor<br>
   * dm:unparsed-entity-system-id( $node as node(), $entityname as xs:string) as
   * xs:anyURI?<br>
   * <br>
   * The dm:unparsed-entity-system-id accessor returns the system identifier of
   * an unparsed external entity declared in the specified document. The value
   * is an absolute URI, and is obtained by resolving the [system identifier] of
   * the unparsed entity information item against the [declaration base URI] of
   * the same item. If no entity with the name specified in $entityname exists,
   * or if the entity is not an external unparsed entity, the empty sequence is
   * returned.<br>
   * <br>
   * It is defined on all seven node kinds.<br>
   */
  @Nullable
  String getUnparsedEntitySystemID (@Nonnull NODETYPE aNode, @Nonnull String sEntityName);
}
