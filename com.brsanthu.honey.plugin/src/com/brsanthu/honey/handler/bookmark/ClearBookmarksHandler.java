package com.brsanthu.honey.handler.bookmark;

import org.eclipse.core.resources.IMarker;


public class ClearBookmarksHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        
        IMarker[] bookmarks = getBookmarks();
        if (bookmarks != null) {
            for (IMarker iMarker : bookmarks) {
                deleteBookmark(iMarker);
            }
        }
    }
}
