package com.brsanthu.honey.handler.join;


/**
 * Joins the alternate lines specified char separator.
 *    
 * @author kumarsa
 */
public class JoinAltLinesWithStringHandler extends JoinLinesWithStringHandler {
	
	@Override
	protected int getNumberOfLines() {
		return 2;
	}
}
