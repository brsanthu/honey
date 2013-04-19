package com.brsanthu.honey.handler.bookmark;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.ITextSelection;


public class ToggleBookmarkHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        ITextSelection selection = getEditor().getSelection();
        if (selection != null) {
            toggleBookmarks(selection.getStartLine(), selection.getEndLine());
        } else {
            toggleBookmarks(getEditor().getCurrentLineNumber());
        }
    }
    
    public void toggleBookmarks(int startLine) {
        toggleBookmarks(startLine, startLine);
    }

    public void toggleBookmarks(int startLine, int endLine) {
        for (int lineNumber = startLine ; lineNumber <= endLine; lineNumber++) {
            IMarker marker = getBookmark(lineNumber);
            if (marker != null) {
                deleteBookmark(marker);
            } else {
                createBookmark(lineNumber);
            }
        }
    }
}
