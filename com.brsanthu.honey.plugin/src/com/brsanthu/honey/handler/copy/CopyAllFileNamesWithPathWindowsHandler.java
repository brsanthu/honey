package com.brsanthu.honey.handler.copy;
import static org.apache.commons.lang3.StringUtils.replace;

public class CopyAllFileNamesWithPathWindowsHandler extends CopyAllFileNamesWithPathUnixHandler {

    @Override
    public String executeCopyCommand(String selectedText) {
        return replace(getEditorFileNames(), "/", "\\");
    }
}
