package com.brsanthu.honey.handler.copy;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.getEditorReferences;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.getFileNameWithPath;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.replace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.ui.IEditorReference;

import com.brsanthu.honey.handler.AbstractTextCopyHandler;

public class CopyAllFileNamesWithPathUnixHandler extends AbstractTextCopyHandler {

    @Override
    public String executeCopyCommand(String selectedText) {
        return replace(getEditorFileNames(), "\\", "/");
    }
    
    public String getEditorFileNames() {
        IEditorReference[] editorRefs = getEditorReferences();
        if (editorRefs == null) {
            return null;
        }
        
        List<String> files = new ArrayList<String>();
        for (IEditorReference editorRef : editorRefs) {
            String fileName = getFileNameWithPath(editorRef);
            if (isNotEmpty(fileName)) {
                files.add(fileName);
            }
        }
        Collections.sort(files);
        String fileNames = join(files, "\n");
        
        return fileNames;
    }
}
