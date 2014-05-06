package com.brsanthu.honey.handler.copy;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.texteditor.MarkerUtilities;

import com.brsanthu.honey.handler.AbstractTextCopyHandler;
import com.brsanthu.honey.handler.bookmark.ClearBookmarksHandler;
import static com.brsanthu.eclipseutils.EclipseUtils.*;
public class CopyBookmarkedLinesHandler extends AbstractTextCopyHandler {

    @Override
    public String executeCopyCommand(String selectedText) {
        ClearBookmarksHandler util = new ClearBookmarksHandler();
        util.setEditor(getEditor());
        List<IMarker> bookmarks = util.getCurrentEditorBookmarks();
        if (bookmarks.isEmpty()) {
            setTimedErrorMessage("There are no bookmarks in File to copy!");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (IMarker iMarker : bookmarks) {
            String line = getEditor().getLine(MarkerUtilities.getLineNumber(iMarker) - 1);
            if (line != null) {
                sb.append(line);
            }
        }
        
        return sb.toString().trim();
    }
}
