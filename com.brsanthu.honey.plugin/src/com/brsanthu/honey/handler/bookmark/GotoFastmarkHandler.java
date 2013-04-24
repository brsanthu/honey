package com.brsanthu.honey.handler.bookmark;

import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setTimedStatusErrorMessage;

import org.eclipse.core.resources.IMarker;

public class GotoFastmarkHandler extends AbstractFastmarkHandler {

	@Override
	public void executeCommand() {
		IMarker fastmark = getFastmark();
		if (fastmark != null) {
			openBookmark(fastmark);
		} else {
			setTimedStatusErrorMessage("Fastmark " + getFastmarkNumber() + " is not set!");
		}
	}

	@Override
	public int getFastmarkNumber() {
		String parameter = getEvent().getParameter("com.brsanthu.honey.command.gotoFastmark.fastmarkNumber");
		try {
			return Integer.parseInt(parameter);
		} catch (Exception e) {
		}
		return 1;
	}
}
