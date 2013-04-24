package com.brsanthu.honey.handler.bookmark;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;

import com.brsanthu.eclipse.common.ui.dialog.filtertreepopup.FilterTreePopupDialog;

public class OpenBookmarksDialog extends FilterTreePopupDialog {

    public OpenBookmarksDialog(Shell parent) {
        super(parent, new OpenBookmarkContentProvider().getInitialInput());
        setSize(600, 400);
    }
    
    @Override
    protected ITreeContentProvider createContentProvider() {
    	return new OpenBookmarkContentProvider();
    }
    
    @Override
    protected ColumnLabelProvider createLabelProvider() {
    	return new OpenBookmarkLabelProvider();
    }
    
    @Override
    protected void gotoSelectedElement(Object selectedElement) {
        if (selectedElement instanceof IMarker) {
        	new OpenBookmarkHandler().openBookmark((IMarker) selectedElement);
        }

    }
}
