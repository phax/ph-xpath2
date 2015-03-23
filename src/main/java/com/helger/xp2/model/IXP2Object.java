package com.helger.xp2.model;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;

/**
 * Base interface for all objects that are part of the XPath 2 data model-
 *
 * @author Philip Helger
 */
public interface IXP2Object
{
  /**
   * Write the respective XPath expression to the passed Writer.
   *
   * @param aWriter
   *        The writer to write to. Never <code>null</code>.
   * @throws IOException
   *         In case writing fails.
   */
  void writeTo (@Nonnull Writer aWriter) throws IOException;
}
