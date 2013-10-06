package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import utils.Lang;
import utils.Properties;

/**
 * @author Jordan Aranda Tejada
 */

public class Window extends JFrame implements Internationalizable {

	private static final long	serialVersionUID	= - 8641413596663241575L;
	private static Window		instance;

	private Window()
	{
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage((new ImageIcon("img/server-icon.png")).getImage());
		setSize(820, 650);
		setMinimumSize(new Dimension(800, 650));
		setLocationRelativeTo(null);
		Lang.setLine(this, "main_window_title");
	}

	/**
	 * @return instance of the window
	 */
	public static Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		return instance;
	}

	@Override
	public void changeLanguage(String newText)
	{
		setTitle(newText + " " + Properties.getVersion() + "]");
	}
}