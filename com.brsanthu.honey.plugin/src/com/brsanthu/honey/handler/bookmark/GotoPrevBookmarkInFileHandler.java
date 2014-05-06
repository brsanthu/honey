package com.brsanthu.honey.handler.bookmark;

import static com.brsanthu.eclipseutils.EclipseUtils.*;
import java.util.List;

import org.eclipse.core.resources.IMarker;


public class GotoPrevBookmarkInFileHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        
    	IMarker bookmarkToGoto = null;
        List<IMarker> bookmarks = getCurrentEditorBookmarks();
        if (bookmarks.isEmpty()) {
            setTimedErrorMessage("There are no bookmarks in File!");
            return;
        }
        
        int currentEditorLineNumber = getEditor().getCurrentLineNumber();
        for (IMarker bookmark : bookmarks) {
			if (getLineNumber(bookmark) < currentEditorLineNumber) {
				bookmarkToGoto = bookmark;
				break;
			}
		}
        
        if (bookmarkToGoto == null) {
        	bookmarkToGoto = bookmarks.get(bookmarks.size() - 1);
        }
        
        openBookmark(bookmarkToGoto);
    }
}
