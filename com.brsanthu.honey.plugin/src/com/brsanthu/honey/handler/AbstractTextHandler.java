package com.brsanthu.honey.handler;


public abstract class AbstractTextHandler extends AbstractEditorHandler {
    @Override
    public void executeCommand() {
        executeCommand(getEditor().getSelectedText());
    }
    public abstract void executeCommand(String selectedText);
    
}
