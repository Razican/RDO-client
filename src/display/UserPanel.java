package display;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;

import display.components.IPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class UserPanel extends IPanel {

	private static final long	serialVersionUID	= - 1961905928730398258L;

	/**
	 * Create the panel.
	 */
	public UserPanel()
	{
		setBackgroundImage(new ImageIcon("img/background.jpg"));
		setLayout(new BorderLayout(0, 0));

	}

}
