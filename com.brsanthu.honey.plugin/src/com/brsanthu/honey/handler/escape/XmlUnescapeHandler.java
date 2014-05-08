package com.brsanthu.honey.handler.escape;

import org.apache.commons.lang3.StringEscapeUtils;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class XmlUnescapeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) {
		return new TextChangeResponse(StringEscapeUtils.unescapeXml(selectedText));
	}
}
