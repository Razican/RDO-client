package display;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import effects.SLPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class Front extends SLPanel {

	private static final long	serialVersionUID	= 5891868417867045791L;

	/**
	 * Constructor
	 */
	public Front()
	{
		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon("img/green-cross.png")),
		BorderLayout.CENTER);
		setBackground(Color.BLACK);
		setOpaque(true);
	}
}
