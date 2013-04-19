package com.brsanthu.honey.handler.bookmark;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;

public class BookmarkParameterValues implements IParameterValues {

	private static final int MAX = 15;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getParameterValues() {
		final Map values = new HashMap();
		for (int i = 1; i <= MAX; i++) {
			values.put("" + i, "" + i);
		}

		return values;
	}
}
