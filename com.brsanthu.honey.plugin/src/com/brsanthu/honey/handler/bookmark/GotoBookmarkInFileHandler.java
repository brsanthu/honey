package com.brsanthu.honey.handler.bookmark;

import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setTimedStatusErrorMessage;

import java.util.List;

import org.eclipse.core.resources.IMarker;


public class GotoBookmarkInFileHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        
        List<IMarker> bookmarks = getCurrentEditorBookmarks();
        if (bookmarks.isEmpty()) {
            return;
        }
        if (bookmarks.size() < getBookmarkNumber()) {
            setTimedStatusErrorMessage("Specified numbered bookmark is defined in the file!");
            return;
        }
        
        openBookmark(bookmarks.get(getBookmarkNumber() - 1));
    }
    
	public int getBookmarkNumber() {
		String parameter = getEvent().getParameter("com.brsanthu.honey.command.gotoBookmarkInFile.bookmarkNumber");
		try {
			return Integer.parseInt(parameter);
		} catch (Exception e) {
		}
		return 1;
	}
    
}
