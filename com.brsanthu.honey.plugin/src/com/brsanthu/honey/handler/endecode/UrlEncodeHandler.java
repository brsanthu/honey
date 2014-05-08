package com.brsanthu.honey.handler.endecode;

import org.apache.commons.codec.net.URLCodec;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.google.common.base.Charsets;

public class UrlEncodeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) {
		return new TextChangeResponse(new String(URLCodec.encodeUrl(null, selectedText.getBytes(Charsets.UTF_8)), Charsets.UTF_8));
	}
}
