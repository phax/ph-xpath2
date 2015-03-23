package com.helger.xp2.eval.datamodel;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;

public enum ENodeKind
{
  ATTRIBUTE ("attribute"),
  COMMENT ("comment"),
  DOCUMENT ("document"),
  ELEMENT ("element"),
  NAMESPACE ("namespace"),
  PROCESSING_INSTRUCTION ("processing-instruction"),
  TEXT ("text");

  private final String m_sText;

  private ENodeKind (@Nonnull @Nonempty final String sText)
  {
    m_sText = sText;
  }

  @Nonnull
  @Nonempty
  public final String getText ()
  {
    return m_sText;
  }
}
