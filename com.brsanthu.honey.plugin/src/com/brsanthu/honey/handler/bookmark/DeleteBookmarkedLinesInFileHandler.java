package com.brsanthu.honey.handler.bookmark;

import static com.brsanthu.eclipseutils.EclipseUtils.setTimedErrorMessage;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.texteditor.MarkerUtilities;

public class DeleteBookmarkedLinesInFileHandler extends AbstractBookmarkHandler {

	@Override
	public void executeCommand() {
        List<IMarker> bookmarks = getCurrentEditorBookmarks();
        if (bookmarks.isEmpty()) {
            setTimedErrorMessage("There are no bookmarks in File to delete!");
            return;
        }
        for (IMarker marker : bookmarks) {
            //getEditor().getDeleteLine(MarkerUtilities.getLineNumber(marker) - 1);
        }
		
	}

}
