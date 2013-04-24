package com.brsanthu.honey.handler.bookmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OpenBookmarkHandler extends AbstractBookmarkHandler {
	
	private static Logger logger = LoggerFactory.getLogger(OpenBookmarkHandler.class);
	
    @Override
    public void executeCommand() {
        OpenBookmarksDialog dialog = new OpenBookmarksDialog(getShell());
        dialog.open();
    }
}
