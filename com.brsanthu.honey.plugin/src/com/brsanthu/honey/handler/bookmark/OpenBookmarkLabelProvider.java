package com.brsanthu.honey.handler.bookmark;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE.SharedImages;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.eclipse.wb.swt.ResourceManager;

import com.brsanthu.honey.Activator;

public class OpenBookmarkLabelProvider extends ColumnLabelProvider {
    
    @Override
    public String getText(Object element) {
    	if (element instanceof IFile) {
    		return ((IFile) element).getFullPath().toString();
    	}

    	if (element instanceof IMarker) {
    		return MarkerUtilities.getMessage((IMarker) element);
    	}

        return String.valueOf(element);
    }
    
    @Override
    public Image getImage(Object element) {
    	if (element instanceof IMarker) {
    		String marketText = getText(element);
    		if (marketText.startsWith("Fastmark")) {
    			return ResourceManager.getPluginImage(Activator.PLUGIN_ID, "icons/fastmark.gif");
    		} else {
    			return PlatformUI.getWorkbench().getSharedImages().getImage(SharedImages.IMG_OBJS_BKMRK_TSK);
    		}
    	}
    	
    	return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
    }
}
