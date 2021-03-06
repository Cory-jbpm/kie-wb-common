/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.screens.library.client.screens.samples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.guvnor.common.services.project.client.context.WorkspaceProjectContext;
import org.guvnor.common.services.project.context.WorkspaceProjectContextChangeEvent;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.screens.examples.model.ExampleProject;
import org.kie.workbench.common.screens.examples.service.ExamplesService;
import org.kie.workbench.common.screens.library.api.LibraryService;
import org.kie.workbench.common.screens.library.client.screens.importrepository.ImportPresenter;
import org.kie.workbench.common.screens.library.client.util.LibraryPlaces;
import org.kie.workbench.common.screens.library.client.widgets.common.TileWidget;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.backend.vfs.Path;
import org.uberfire.mocks.CallerMock;
import org.uberfire.mocks.EventSourceMock;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.events.NotificationEvent;

import static org.jgroups.util.Util.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ExampleImportPresenterTest {

    @Mock
    private ImportPresenter.View view;

    @Mock
    private LibraryPlaces libraryPlaces;

    @Mock
    private LibraryService libraryService;
    private Caller<LibraryService> libraryServiceCaller;

    @Mock
    private ManagedInstance<TileWidget> tileWidgets;

    @Mock
    private ExamplesService examplesService;
    private Caller<ExamplesService> examplesServiceCaller;

    @Mock
    private EventSourceMock<NotificationEvent> notificationEvent;

    @Mock
    private EventSourceMock<WorkspaceProjectContextChangeEvent> projectContextChangeEvent;

    private TileWidget tileWidget;

    private ImportPresenter importPresenter;

    @Before
    public void setup() {
        libraryServiceCaller = new CallerMock<>(libraryService);
        examplesServiceCaller = new CallerMock<>(examplesService);
        tileWidget = spy(new TileWidget(mock(TileWidget.View.class)));

        doReturn(tileWidget).when(tileWidgets).get();

        importPresenter = ImportPresenter.examplesImportPresenter(view,
                                                                  libraryPlaces,
                                                                  libraryServiceCaller,
                                                                  tileWidgets,
                                                                  examplesServiceCaller,
                                                                  mock(WorkspaceProjectContext.class),
                                                                  notificationEvent,
                                                                  projectContextChangeEvent);
    }

    @Test
    public void onStartupWithoutProjectsTest() {
        Map<String, String> params = new HashMap<>();
        params.put("repositoryUrl",
                   "repoUrl");
        importPresenter.onStartup(new DefaultPlaceRequest(LibraryPlaces.PROJECT_SCREEN,
                                                                        params));

        verify(view).hideBusyIndicator();
        verify(notificationEvent).fire(any());
        verify(libraryPlaces).goToLibrary();
    }

    @Test
    public void filterProjectsTest() {
        Set<ExampleProject> projects = new HashSet<>();
        projects.add(new ExampleProject(mock(Path.class),
                                        "p1a",
                                        "p1a description",
                                        null));
        projects.add(new ExampleProject(mock(Path.class),
                                        "p3b",
                                        "p3b description",
                                        null));
        projects.add(new ExampleProject(mock(Path.class),
                                        "p2a",
                                        "p2a description",
                                        null));
        doReturn(projects).when(libraryService).getExampleProjects();

        Map<String, String> params = new HashMap<>();
        params.put("repositoryUrl",
                   "repoUrl");
        importPresenter.onStartup(new DefaultPlaceRequest(LibraryPlaces.PROJECT_SCREEN,
                                                                        params));
        final List<TileWidget> filteredProjects = importPresenter.filterProjects("a");

        assertEquals(2,
                     filteredProjects.size());
    }

    @Test
    public void cancelTest() {
        importPresenter.cancel();

        verify(libraryPlaces).goToLibrary();
    }
}
