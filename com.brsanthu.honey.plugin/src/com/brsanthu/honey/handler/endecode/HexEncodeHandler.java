package com.brsanthu.honey.handler.endecode;

import org.apache.commons.codec.binary.Hex;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.google.common.base.Charsets;

public class HexEncodeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) {
		return new TextChangeResponse(Hex.encodeHexString(selectedText.getBytes(Charsets.UTF_8)));
	}
}
