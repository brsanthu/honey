package com.brsanthu.honey.handler.copy;
import com.brsanthu.honey.handler.AbstractTextCopyHandler;

public class CopyFileNameHandler extends AbstractTextCopyHandler {

    @Override
    public String executeCopyCommand(String selectedText) {
        return getEditor().getFileName();
    }
}
