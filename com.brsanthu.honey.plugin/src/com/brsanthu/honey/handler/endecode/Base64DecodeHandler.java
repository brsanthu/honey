package com.brsanthu.honey.handler.endecode;

import org.apache.commons.codec.binary.Base64;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.google.common.base.Charsets;

public class Base64DecodeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) {
		return new TextChangeResponse(new String(Base64.decodeBase64(selectedText), Charsets.UTF_8));
	}
}
