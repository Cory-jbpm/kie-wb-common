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

package org.kie.workbench.common.screens.projecteditor.client.forms.dependencies;

import org.gwtbootstrap3.client.ui.constants.ValidationState;

public interface NewDependencyPopupView {

    void show();

    void setPresenter( final NewDependencyPopup newDependencyPopup );

    void hide();

    void invalidGroupId( final String errorMessage );

    void invalidArtifactId( final String errorMessage );

    void invalidVersion( final String errorMessage );

    void setGroupIdValidationState( final ValidationState validationState );

    void setArtifactIdValidationState( final ValidationState validationState );

    void setVersionValidationState( final ValidationState validationState );

    void clean();
    
    void setGroupId(String groupId);
    
    void setArtifactId(String artifactId);
    
    void setVersion(String version);
}
