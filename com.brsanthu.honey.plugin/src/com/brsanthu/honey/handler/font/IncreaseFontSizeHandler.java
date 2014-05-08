package com.brsanthu.honey.handler.font;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.brsanthu.eclipseutils.EclipseUtils;
import com.brsanthu.honey.handler.AbstractEditorHandler;

public class IncreaseFontSizeHandler extends AbstractEditorHandler {
    
	@Override
	public void executeCommand() {
	    ScopedPreferenceStore preferenceStore = new ScopedPreferenceStore( InstanceScope.INSTANCE, "org.eclipse.ui.workbench" );
	    FontSetting fontSetting = getNewFontSetting(new FontSetting(preferenceStore.getString(JFaceResources.TEXT_FONT)));
	    preferenceStore.setValue(JFaceResources.TEXT_FONT, fontSetting.toString());
	}

	protected FontSetting getNewFontSetting(FontSetting fontSetting) {
		fontSetting.increaseFontSize();
	    EclipseUtils.setTimedInfoMessage("Font size has been increased to " + fontSetting.getFontSize());
	    return fontSetting;
	}

}
