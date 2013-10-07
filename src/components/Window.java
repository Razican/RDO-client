package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
		setSize(600, 450);
		setMinimumSize(new Dimension(600, 450));
		setLocationRelativeTo(null);
		setTitle("Cliente");
		setDefaultLookAndFeelDecorated(true);
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