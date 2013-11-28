package display;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import components.Window;

import effects.SLAnimator;
import effects.SLConfig;
import effects.SLPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends JPanel {

	private static final long	serialVersionUID	= - 3019955922941567348L;
	private final SLConfig		mainCfg;
	private SLPanel				mainPanel;
	private Login				login;
	private Settings			settings;
	private Front				front;

	/**
	 * Constructor
	 */
	public Start()
	{
		setLayout(new BorderLayout(0, 0));

		mainPanel = new SLPanel();
		login = new Login();
		settings = new Settings();
		front = new Front();
		add(mainPanel, BorderLayout.CENTER);

		//@formatter:off
		mainCfg = new SLConfig(mainPanel)
		.gap(0, 0)
		.row(60).row(1F).col(1f)
		.beginGrid(0, 0)
			.row(1F).col(250).col(1f).col(250)
			.place(0, 0, settings)
			.place(0, 2, login)
		.endGrid()
		.place(1, 0, front);
		//@formatter:on

		mainPanel.setTweenManager(SLAnimator.createTweenManager());
		mainPanel.initialize(mainCfg);
	}

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

				Window.getInstance().setContentPane(new Start());
				Window.getInstance().setVisible(true);
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
		});
	}
}