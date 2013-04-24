package com.brsanthu.honey.handler.bookmark;

import java.util.List;

import org.eclipse.core.resources.IMarker;


public class ClearBookmarksHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        
        List<IMarker> bookmarks = getCurrentEditorBookmarks();
        for (IMarker iMarker : bookmarks) {
            deleteBookmark(iMarker);
        }
    }
}
