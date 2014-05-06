package com.brsanthu.honey.handler.bookmark;

import org.eclipse.core.resources.IMarker;

import static com.brsanthu.eclipseutils.EclipseUtils.*;
public class GotoFastmarkHandler extends AbstractFastmarkHandler {

	@Override
	public void executeCommand() {
		IMarker fastmark = getFastmark();
		if (fastmark != null) {
			openBookmark(fastmark);
		} else {
			setTimedErrorMessage("Fastmark " + getFastmarkNumber() + " is not set!");
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
