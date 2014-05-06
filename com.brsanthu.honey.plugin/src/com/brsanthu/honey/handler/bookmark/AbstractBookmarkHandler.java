package com.brsanthu.honey.handler.bookmark;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import static com.brsanthu.eclipseutils.EclipseUtils.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.ide.undo.CreateMarkersOperation;
import org.eclipse.ui.texteditor.MarkerUtilities;

import com.brsanthu.honey.handler.AbstractEditorHandler;

public abstract class AbstractBookmarkHandler extends AbstractEditorHandler {

    public void deleteBookmark(IMarker bookmark) {
        try {
            bookmark.delete();
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    public List<IMarker> getCurrentEditorBookmarks() {
        IResource resource = getEditor().getResource();
        try {
            IMarker[] markers = resource.findMarkers(IMarker.BOOKMARK, false, IResource.DEPTH_ZERO);
            List<IMarker> bookmarks = new ArrayList<IMarker>(Arrays.asList(markers));
            Collections.sort(bookmarks, new Comparator<IMarker>() {
                @Override
                public int compare(IMarker o1, IMarker o2) {
                    return ((Integer) getLineNumber(o1)).compareTo(getLineNumber(o2));
                }
            });
            return bookmarks;
        } catch (CoreException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public IMarker getBookmark(int lineNumber) {
        List<IMarker> bookmarks = getCurrentEditorBookmarks();
        for (IMarker iMarker : bookmarks) {
            if (MarkerUtilities.getLineNumber(iMarker) == (lineNumber + 1)) {
                return iMarker;
            }
        }
        return null;
    }

    public void createBookmark(int lineNumber) {
        createBookmark(lineNumber, null);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void createBookmark(int lineNumber, String prefix) {
        
        IResource resource = getEditor().getResource();
        
        Map attrs = new HashMap();
        attrs.put(IMarker.LINE_NUMBER, lineNumber + 1);
        String message = getEditor().getLine(lineNumber);
        if (isEmpty(message)) {
            message = "File:" + getEditor().getFileName() + " Line: " + lineNumber;
        }
        
        if (isNotEmpty(prefix)) {
            message = prefix + " " + message; 
        }
        
        attrs.put(IMarker.MESSAGE, message);
        CreateMarkersOperation op = new CreateMarkersOperation(IMarker.BOOKMARK, attrs, resource, "Creating Bookmark");
        try {
            op.execute(new NullProgressMonitor(), resource);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public int getLineNumber(IMarker marker) {
        return MarkerUtilities.getLineNumber(marker);
    }

    public String getMessage(IMarker marker) {
        return MarkerUtilities.getMessage(marker);
    }

    /**
     * Method copied from org.eclipse.ui.internal.views.markers.ExtendedMarkersView
     * @param marker
     */
    public void openBookmark(IMarker marker) {
        // optimization: if the active editor has the same input as
        // the
        // selected marker then
        // RevealMarkerAction would have been run and we only need
        // to
        // activate the editor
        IWorkbenchPage page = getActivePage();
        IEditorPart editor = page.getActiveEditor();
        if (editor != null) {
            IEditorInput input = editor.getEditorInput();
            IFile file = ResourceUtil.getFile(input);
            if (file != null) {
                if (marker.getResource().equals(file)
                        && OpenStrategy.activateOnOpen()) {
                    page.activate(editor);
                }
            }
        }

        if (marker != null && marker.getResource() instanceof IFile) {
            try {
                IDE.openEditor(page, marker, OpenStrategy.activateOnOpen());
            } catch (PartInitException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
