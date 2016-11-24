/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aesh.terminal.impl;

import org.aesh.terminal.Attributes;
import org.fusesource.jansi.AnsiOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author <a href=mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class WinExternalTerminal extends ExternalTerminal {

    public WinExternalTerminal(String name, String type, InputStream masterInput, OutputStream masterOutput, String encoding) throws IOException {
        super(name, type, masterInput, new AnsiOutputStream(masterOutput), encoding);
        Attributes attributes = new Attributes();
        attributes.setInputFlag(Attributes.InputFlag.IGNCR, true);
        attributes.setInputFlag(Attributes.InputFlag.ICRNL, true);
        setAttributes(attributes);
    }
}