package com.brsanthu.honey.handler.paste;
import com.brsanthu.honey.handler.AbstractPasteHandler;

public class PasteTextAsJavaStringHandler extends AbstractPasteHandler {

	@Override
	public String processText(Object clipboardContents) {
		String value = String.valueOf(clipboardContents);
		String processedText = value.replaceAll("\\\"", "\\\\\"");
				
		return "\"" + processedText + "\"";
	}
}
