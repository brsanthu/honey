package com.brsanthu.honey.handler.bookmark;

import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setTimedStatusErrorMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.resources.IMarker;


public class GotoBookmarkInFileHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        
        IMarker[] bookmarksArray = getBookmarks();
        if (bookmarksArray == null) {
            return;
        }
        List<IMarker> bookmarks = new ArrayList<IMarker>(Arrays.asList(bookmarksArray));
        if (bookmarks.size() < getBookmarkNumber()) {
            setTimedStatusErrorMessage("Specified numbered bookmark is defined in the file!");
            return;
        }
        
        Collections.sort(bookmarks, new Comparator<IMarker>() {
            @Override
            public int compare(IMarker o1, IMarker o2) {
                return ((Integer) getLineNumber(o1)).compareTo(getLineNumber(o2));
            }
        });
        
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
