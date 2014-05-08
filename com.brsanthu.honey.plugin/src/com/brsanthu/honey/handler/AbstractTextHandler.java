package com.brsanthu.honey.handler;

public abstract class AbstractTextHandler extends AbstractEditorHandler {
    @Override
    public void executeCommand() throws Exception {
        executeCommand(getEditor().getSelectedText());
    }
    
    public abstract void executeCommand(String selectedText) throws Exception;
}
