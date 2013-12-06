package display.components;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import utils.Lang;
import utils.Properties;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */

public class Window extends JFrame implements Internationalizable {

	private static final long	serialVersionUID	= - 8641413596663241575L;
	private static Window		instance;

	private Window()
	{
		super();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setResizable(true);
		setIconImage((new ImageIcon("img/app-icon.png")).getImage());
		setLocationRelativeTo(null);
		Lang.setLine(this, "application_name");
		addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(final WindowEvent winEvt)
			{
				if (User.isLoaded())
				{
					System.out.println("Usuario cargado");
					final String[] options = {"Yes", "No"};
					//@formatter:off
					final int selection = JOptionPane.showOptionDialog(
					Window.getInstance(), "¿Estas seguro de querer salir?",
					"Salir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
					new ImageIcon("img/warning-icon.png"), options, options[1]);

					if (selection == 0)
					{
						User.getCurrent().logout();
						dispose();
					}
				}
				else
				{
					dispose();
				}
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