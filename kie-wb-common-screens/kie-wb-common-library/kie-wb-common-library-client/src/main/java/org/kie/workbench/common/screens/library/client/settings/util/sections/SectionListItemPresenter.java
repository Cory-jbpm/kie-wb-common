package org.kie.workbench.common.screens.library.client.settings.util.sections;

import org.kie.workbench.common.widgets.client.widget.ListItemPresenter;
import org.kie.workbench.common.widgets.client.widget.ListItemView;


public abstract class SectionListItemPresenter<T, ParentPresenter, V extends ListItemView<? extends ListItemPresenter<T, ParentPresenter, V>>> extends ListItemPresenter<T, ParentPresenter, V> {

    public SectionListItemPresenter(V view) {
        super(view);
    }

    public SectionListPresenter getSectionListPresenter() {
        return (SectionListPresenter)this.getListPresenter();
    }
    
}
