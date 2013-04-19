package com.brsanthu.honey.handler;

public class TextChangeResponse {
    
    public static enum Mode {
        INSERT,
        REPLACE_SELECTED,
        REPLACE_ALL
    }
    
    private Mode mode = Mode.REPLACE_SELECTED;
    private String newText = null;
    
    
    public TextChangeResponse(Mode mode) {
        super();
        this.mode = mode;
    }
    
    public TextChangeResponse(String newText) {
        this(Mode.REPLACE_SELECTED, newText);
    }
    public TextChangeResponse(Mode mode, String newText) {
        super();
        this.mode = mode;
        this.newText = newText;
    }

    public String getNewText() {
        return newText;
    }
    
    public void setNewText(String newText) {
        this.newText = newText;
    }
    
    public Mode getMode() {
        return mode;
    }
    
    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
