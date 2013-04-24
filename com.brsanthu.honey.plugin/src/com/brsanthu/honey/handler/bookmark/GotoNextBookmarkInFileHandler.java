package com.brsanthu.honey.handler.bookmark;

import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setTimedStatusErrorMessage;

import java.util.List;

import org.eclipse.core.resources.IMarker;


public class GotoNextBookmarkInFileHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        
    	IMarker bookmarkToGoto = null;
        List<IMarker> bookmarks = getCurrentEditorBookmarks();
        if (bookmarks.isEmpty()) {
            setTimedStatusErrorMessage("There are no bookmarks in File!");
            return;
        }
        
        int currentEditorLineNumber = getEditor().getCurrentLineNumber();
        for (IMarker bookmark : bookmarks) {
			if (getLineNumber(bookmark) > currentEditorLineNumber) {
				bookmarkToGoto = bookmark;
				break;
			}
		}
        
        if (bookmarkToGoto == null) {
        	bookmarkToGoto = bookmarks.get(0);
        }
        
        openBookmark(bookmarkToGoto);
    }
}
