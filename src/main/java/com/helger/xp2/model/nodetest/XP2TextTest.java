/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.xp2.model.nodetest;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Nonnull;

public class XP2TextTest extends AbstractXP2KindTest
{
  public XP2TextTest ()
  {}

  public void writeTo (@Nonnull final Writer aWriter) throws IOException
  {
    aWriter.write ("text()");
  }
}
