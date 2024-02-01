package uo.cpm.module.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key listener that doesn't allow writing semicolons to protect the files that
 * are loaded and saved
 * 
 * @author paula
 *
 */
public class NotSemiColonKeyListener extends KeyAdapter {
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == KeyEvent.VK_SEMICOLON) {
			e.consume();
		}
	}
}
