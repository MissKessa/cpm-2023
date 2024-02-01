package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FocusBackgroundListener extends FocusAdapter {
	@Override
	public void focusGained(FocusEvent e) {
		((Component) e.getSource()).setBackground(Color.WHITE);
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		((Component) e.getSource()).setBackground(Color.LIGHT_GRAY);
	}
}
