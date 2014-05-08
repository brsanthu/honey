package com.brsanthu.honey.handler.escape;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringEscapeUtils;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class HtmlEscapeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) throws DecoderException {
		return new TextChangeResponse(StringEscapeUtils.escapeHtml4(selectedText));
	}
}
