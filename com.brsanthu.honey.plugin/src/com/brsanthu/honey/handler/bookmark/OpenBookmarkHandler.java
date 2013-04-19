package com.brsanthu.honey.handler.bookmark;


public class OpenBookmarkHandler extends AbstractBookmarkHandler {

    @Override
    public void executeCommand() {
        OpenBookmarksDialog dialog = new OpenBookmarksDialog(getShell());
        dialog.open();
    }
}
