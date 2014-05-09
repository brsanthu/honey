package com.brsanthu.honey.handler.join;

import static com.brsanthu.eclipseutils.EclipseUtils.isOk;
import static com.brsanthu.utils.Utils.firstNonEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.brsanthu.eclipseutils.widgets.GetInputDialog;
import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.brsanthu.honey.handler.TextChangeResponse.Mode;
import com.brsanthu.utils.Utils;

/**
 * Joins the alternate lines specified char separator.
 *    
 * @author kumarsa
 */
public class JoinLinesWithStringHandler extends AbstractTextReplaceHandler {

	@Override
	public TextChangeResponse executeReplaceCommand(String selectedText) throws Exception {
		GetInputDialog dialog = new GetInputDialog(getShell(), "Enter Line Separator", "Enter the String to separate "
				+ "as lines are joined. You can leave it blank or enter any string you prefer incliding spaces.");
		if (isOk(dialog.open())) {
			String joinString = dialog.getInput();
			String textToJoin = firstNonEmpty(selectedText, getEditor().getContents());
			String joinedString = Utils.joinLines(textToJoin, getNumberOfLines(), joinString);
			
			TextChangeResponse textChangeResponse = new TextChangeResponse(Mode.REPLACE_ALL, joinedString);
			if (isNotBlank(selectedText)) {
				textChangeResponse.setMode(Mode.REPLACE_SELECTED);
			}
			
			return textChangeResponse;
		}
		
		return null;
	}

	protected int getNumberOfLines() {
		return -1;
	}
}
