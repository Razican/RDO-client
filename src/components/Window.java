package components;

import java.awt.Dimension;

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
		setSize(new Dimension(800, 600));
		setResizable(false);
		setLocationRelativeTo(null);
		Lang.setLine(this, "application_name");
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
		setTitle(newText + " [" + Lang.getLine("version") + " "
		+ Properties.getVersion() + "]");
	}
}