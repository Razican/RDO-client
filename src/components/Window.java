package components;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;

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
		setIconImage((new ImageIcon("img/vital-signs-icon.png")).getImage());
		setSize(Properties.getWindowSize());
		setMinimumSize(new Dimension(600, 450));
		setLocationRelativeTo(null);
		Lang.setLine(this, "application_name");
		setDefaultLookAndFeelDecorated(true);

		addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(java.awt.event.ComponentEvent e)
			{
				Properties.setWindowSize(getSize());
			}
		});
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