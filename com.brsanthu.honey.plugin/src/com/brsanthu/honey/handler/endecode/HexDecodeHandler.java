package com.brsanthu.honey.handler.endecode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class HexDecodeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) throws DecoderException {
		return new TextChangeResponse(new String(Hex.decodeHex(selectedText.toCharArray())));
	}
}
