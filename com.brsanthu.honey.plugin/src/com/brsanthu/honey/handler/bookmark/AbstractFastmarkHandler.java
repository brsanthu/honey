package com.brsanthu.honey.handler.bookmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;

public abstract class AbstractFastmarkHandler extends AbstractBookmarkHandler {
    
    public List<IMarker> getFastmarksAtCurrentLine() {
        int currentLineNumber = getEditor().getCurrentLineNumber();
        List<IMarker> fastmarks = getFastmarks();
        List<IMarker> matchedFastmarks = new ArrayList<IMarker>();
        for (IMarker fastmark : fastmarks) {
            if (getLineNumber(fastmark) == currentLineNumber && getMessage(fastmark).startsWith("Fastmark")) {
                matchedFastmarks.add(fastmark);
            }
        }
        
        return matchedFastmarks;
    }
    
    public List<IMarker> getFastmarks() {
        try {
            IMarker[] fastmarks = ResourcesPlugin.getWorkspace().getRoot().findMarkers(IMarker.BOOKMARK, false, IResource.DEPTH_INFINITE);
            return Arrays.asList(fastmarks);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    public IMarker getFastmark() {
        List<IMarker> fastmarks = getFastmarks();
        for (IMarker fastmark : fastmarks) {
            if (getMessage(fastmark).startsWith(getFastmarkPrefix())) {
                return fastmark;
            }
        }
        return null;
    }
    public String getFastmarkPrefix() {
        return "Fastmark " + getFastmarkNumber() + ":";
    }
    
    public abstract int getFastmarkNumber();
}
