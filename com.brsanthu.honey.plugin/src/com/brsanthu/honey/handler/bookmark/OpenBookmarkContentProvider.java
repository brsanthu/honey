package com.brsanthu.honey.handler.bookmark;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class OpenBookmarkContentProvider implements ITreeContentProvider {

    public Object getInitialInput() {
        List<IResource> resources = new ArrayList<IResource>();
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        try {
            IMarker[] bookmarks = root.findMarkers(IMarker.BOOKMARK, false, IResource.DEPTH_INFINITE);
            for (IMarker bookmark : bookmarks) {
                if (!resources.contains(bookmark.getResource())) {
                    resources.add(bookmark.getResource());
                }
            }
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
        
        return resources;
    }
    
    @Override
    public void dispose() {
        
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        //default
    }

    @SuppressWarnings("unchecked")
	@Override
    public Object[] getElements(Object inputElement) {
        List<IResource> resources = ((List<IResource>) inputElement);
        return resources.toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
    	if (parentElement instanceof IFile) {
            IMarker[] bookmarks = null;
			try {
				bookmarks = ((IResource) parentElement).findMarkers(IMarker.BOOKMARK, false, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				e.printStackTrace();
			}
            return bookmarks;
    	}
        return null;
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return true;
    }
}
