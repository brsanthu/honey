package com.brsanthu.honey.handler.endecode;

import org.apache.commons.codec.binary.Base64;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.google.common.base.Charsets;

public class Base64EncodeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) {
		return new TextChangeResponse(Base64.encodeBase64String(selectedText.getBytes(Charsets.UTF_8)));
	}
}
