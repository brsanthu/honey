package com.brsanthu.honey.handler.paste;
import org.apache.commons.lang3.StringUtils;

import com.brsanthu.honey.handler.AbstractPasteHandler;

public class PasteTextAsUnixPathHandler extends AbstractPasteHandler {

	@Override
	public String processText(Object clipboardContents) {
		String value = String.valueOf(clipboardContents);
		return StringUtils.replace(value, "\\", "/");
	}
}
