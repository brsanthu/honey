package com.brsanthu.honey.handler.endecode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.google.common.base.Charsets;

public class UrlDecodeHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) throws DecoderException {
		return new TextChangeResponse(new String(URLCodec.decodeUrl(selectedText.getBytes(Charsets.UTF_8))));
	}
}
