package com.brsanthu.honey.handler.font;

import com.brsanthu.eclipseutils.EclipseUtils;

public class DecreaseFontSizeHandler extends IncreaseFontSizeHandler {
    
	protected FontSetting getNewFontSetting(FontSetting fontSetting) {
		fontSetting.decreaseFontSize();
	    EclipseUtils.setTimedInfoMessage("Font size has been decreased to " + fontSetting.getFontSize());
	    return fontSetting;
	}

}
