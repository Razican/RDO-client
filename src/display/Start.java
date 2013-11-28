package display;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import components.Window;

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
				try
				{
					UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				}
				catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
				{
					e.printStackTrace();
				}

				Window.getInstance().setContentPane(new Login());
				Window.getInstance().setVisible(true);
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
		});
	}
}