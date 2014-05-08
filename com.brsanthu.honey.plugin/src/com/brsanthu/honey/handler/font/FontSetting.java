package com.brsanthu.honey.handler.font;

import com.google.common.base.Joiner;

public class FontSetting {
	
	/**
	 * Holds the segments of the font settings. Font setting looks like below when written
	 * to pref file, where 2nd element is font name and 3rd element is font size.
	 * 
	 * <pre>1|Consolas|12.0|0|WINDOWS|1|-16|0|0|0|400|0|0|0|0|3|2|1|49|Consolas</pre>
	 */
	private String[] segments = null;
	
	public FontSetting(String fontSetting) {
		segments = fontSetting.split("\\|");
	}
	
	public Double getFontSize() {
		return Double.valueOf(segments[2]);
	}
	
	public String getFontName() {
		return segments[1];
	}

	public FontSetting increaseFontSize() {
		segments[2] = String.valueOf(Double.parseDouble(segments[2]) + 1);
		return this;
	}

	public FontSetting decreaseFontSize() {
		segments[2] = String.valueOf(Math.max(1, Double.parseDouble(segments[2]) - 1));
		return this;
	}
	
	public String toString() {
		return Joiner.on("|").join(segments);
	}
	
	public static void main(String[] args) {
		System.out.println(new FontSetting("1|Consolas|12.0|0|WINDOWS|1|-16|0|0|0|400|0|0|0|0|3|2|1|49|Consolas").increaseFontSize());
	}
}
