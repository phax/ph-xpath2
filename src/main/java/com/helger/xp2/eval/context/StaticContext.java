package com.helger.xp2.eval.context;

import com.helger.commons.xml.namespace.MapBasedNamespaceContext;
import com.helger.xp2.CXP2;

/**
 * Static expression context.
 * 
 * @author Philip Helger
 */
public class StaticContext
{
  private final MapBasedNamespaceContext m_aNamespaces = new MapBasedNamespaceContext ();
  private final String m_sDefaultElementNamespaceURI = null;
  private final String m_sDefaultFunctionNamespaceURI = CXP2.XML_NS_FN;

  public StaticContext ()
  {}
}
