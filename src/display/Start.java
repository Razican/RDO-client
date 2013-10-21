package display;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import utils.Properties;

import components.Window;

import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Start {

	/**
	 * @param args Aplication arguments
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				if (Properties.getLookAndFeel().substring(0, 3).equals("org"))
				{
					try
					{
						SubstanceLookAndFeel.setSkin(Properties
						.getLookAndFeel());
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					try
					{
						UIManager.setLookAndFeel(Properties.getLookAndFeel());
					}
					catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e)
					{
						e.printStackTrace();
					}
				}

				User.loadRememberUser();
				if (User.getCurrent() == null)
				{
					Window.getInstance().setContentPane(new Login());
				}
				else
				{
					Window.getInstance().setContentPane(new MainPanel());
				}
				Window.getInstance().setVisible(true);
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
		});
	}
}