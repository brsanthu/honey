package com.brsanthu.honey.handler.bookmark;

import java.util.List;

import org.eclipse.core.resources.IMarker;

public class SetFastmarkHandler extends AbstractFastmarkHandler {
    
    @Override
    public void executeCommand() {
    	
    	//Delete other fastmarks at this line.
    	List<IMarker> fastmarks = getFastmarksAtCurrentLine();
        for (IMarker fastmark : fastmarks) {
            deleteBookmark(fastmark);
        }
        
        //Delete the current numbered fastmark from other location.
        IMarker fastmark = getFastmark();
        if (fastmark != null) {
        	deleteBookmark(fastmark);
        }
        
        createBookmark(getEditor().getCurrentLineNumber(), getFastmarkPrefix());
    }
    
	@Override
	public int getFastmarkNumber() {
		String parameter = getEvent().getParameter("com.brsanthu.honey.command.setFastmark.fastmarkNumber");
		try {
			return Integer.parseInt(parameter);
		} catch (Exception e) {
		}
		return 1;
	}
    
}
