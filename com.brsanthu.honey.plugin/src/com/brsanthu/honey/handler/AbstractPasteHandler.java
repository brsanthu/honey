package com.brsanthu.honey.handler;

import static com.brsanthu.eclipseutils.EclipseUtils.*;

public abstract class AbstractPasteHandler extends AbstractEditorHandler {
	
	@Override
	public void executeCommand() {
		Object clipboardContents = getClipboardContents();
		if (clipboardContents == null) {
			setTimedErrorMessage("There is nothing in the clipboard to paste!");
			return;
		}
		
		String processedText = processText(clipboardContents);
		getEditor().replaceSelection(processedText);
	}
	
	public abstract String processText(Object clipboardContents);
}
