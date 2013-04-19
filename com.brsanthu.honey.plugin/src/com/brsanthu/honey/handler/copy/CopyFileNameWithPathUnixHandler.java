package com.brsanthu.honey.handler.copy;
import org.apache.commons.lang3.StringUtils;

import com.brsanthu.honey.handler.AbstractTextCopyHandler;

public class CopyFileNameWithPathUnixHandler extends AbstractTextCopyHandler {

    @Override
    public String executeCopyCommand(String selectedText) {
        String fileNameWithPath = getEditor().getFileNameWithPath();
        return StringUtils.replace(fileNameWithPath, "\\", "/");
    }
}
