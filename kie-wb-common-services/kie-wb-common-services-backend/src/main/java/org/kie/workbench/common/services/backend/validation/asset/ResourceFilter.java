/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.services.backend.validation.asset;

import org.kie.soup.commons.validation.PortablePreconditions;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.Path;
import org.uberfire.java.nio.IOException;
import org.uberfire.java.nio.file.DirectoryStream;

/**
 * Filter for a specific resource
 */
public class ResourceFilter
        implements DirectoryStream.Filter<org.uberfire.java.nio.file.Path> {

    private org.uberfire.java.nio.file.Path resourcePath;

    public ResourceFilter(final Path resourcePath) {
        this.resourcePath = Paths.convert(PortablePreconditions.checkNotNull("resourcePath",
                                                                             resourcePath));
    }

    @Override
    public boolean accept(final org.uberfire.java.nio.file.Path entry) throws IOException {
        return entry.equals(resourcePath);
    }
}
