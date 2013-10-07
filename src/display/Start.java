package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import utils.Client;
import utils.Lang;
import utils.Properties;
import utils.StringUtils;

import components.IButton;
import components.ILabel;
import components.IPPanel;
import components.IPanel;
import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends JPanel {

	private static final long	serialVersionUID	= - 6969744533822338215L;
	private IPPanel				ip_panel;
	private JTextField			textField_User;
	private JPasswordField		passwordField;
	private Client				client;

	/**
	 * Create the panel.
	 */
	public Start()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 250, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 1.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, 1.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		IPanel rightPanel = new IPanel();
		Lang.setLine(rightPanel, "login_pane");
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.insets = new Insets(10, 10, 10, 10);
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 1;
		add(rightPanel, gbc_rightPanel);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] {0, 0, 0};
		gbl_rightPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_rightPanel.columnWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0,
		Double.MIN_VALUE};
		rightPanel.setLayout(gbl_rightPanel);

		ILabel lblIP = new ILabel();
		Lang.setLine(lblIP, "lbl_server_ip");
		lblIP.setForeground(Color.BLACK);
		lblIP.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblIP = new GridBagConstraints();
		gbc_lblIP.anchor = GridBagConstraints.WEST;
		gbc_lblIP.insets = new Insets(5, 0, 5, 5);
		gbc_lblIP.gridx = 0;
		gbc_lblIP.gridy = 0;
		rightPanel.add(lblIP, gbc_lblIP);

		ip_panel = new IPPanel();
		GridBagConstraints gbc_ip_panel = new GridBagConstraints();
		gbc_ip_panel.insets = new Insets(5, 0, 5, 0);
		gbc_ip_panel.fill = GridBagConstraints.BOTH;
		gbc_ip_panel.gridx = 1;
		gbc_ip_panel.gridy = 0;
		rightPanel.add(ip_panel, gbc_ip_panel);

		ILabel lblUser = new ILabel();
		Lang.setLine(lblUser, "lbl_user");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(5, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 1;
		rightPanel.add(lblUser, gbc_lblUser);

		textField_User = new JTextField();
		textField_User.setForeground(Color.BLACK);
		textField_User.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_User = new GridBagConstraints();
		gbc_textField_User.insets = new Insets(5, 0, 5, 0);
		gbc_textField_User.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_User.gridx = 1;
		gbc_textField_User.gridy = 1;
		rightPanel.add(textField_User, gbc_textField_User);
		textField_User.setColumns(10);

		ILabel lblPassword = new ILabel();
		Lang.setLine(lblPassword, "lbl_password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(5, 0, 10, 5);
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		rightPanel.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(5, 0, 10, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		rightPanel.add(passwordField, gbc_passwordField);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		rightPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0};
		gbl_panel.columnWeights = new double[] {1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		IButton btnPreferences = new IButton();
		btnPreferences.setFocusPainted(false);
		Lang.setLine(btnPreferences, "preferences");
		btnPreferences.setForeground(Color.BLACK);
		btnPreferences.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnPreferences.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				final Preferences p = new Preferences();

				final String[] options = {Lang.getLine("accept_option"),
				Lang.getLine("cancel_option")};
				JOptionPane pane = new JOptionPane(p,
				JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
				options, options[1]);
				final JDialog dialog = pane.createDialog(Lang
				.getLine("preferences"));
				dialog.setLocationRelativeTo(Window.getInstance());
				dialog.setResizable(false);
				dialog.pack();
				dialog.setIconImage(new ImageIcon("img/sett-icon.png")
				.getImage());
				dialog.setVisible(true);

				if (pane.getValue() == options[0])
				{
					Properties.setLocale(Lang.getAvailableLocales().get(
					p.getLocaleIndex()));
					Lang.setLang(Lang.getAvailableLocales().get(
					p.getLocaleIndex()));

					Properties.setLookAndFeelClass(p.getLookAndFeel());
					if (Properties.getLookAndFeel().substring(0, 3)
					.equals("org"))
					{
						SubstanceLookAndFeel.setSkin(Properties
						.getLookAndFeel());
					}
					else
					{
						try
						{
							UIManager.setLookAndFeel(Properties
							.getLookAndFeel());
						}
						catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1)
						{
							e1.printStackTrace();
						}
					}
					SwingUtilities.updateComponentTreeUI(Window.getInstance());
					Window.getInstance().pack();
				}
				dialog.dispose();
			}
		});
		GridBagConstraints gbc_btnPreferences = new GridBagConstraints();
		gbc_btnPreferences.fill = GridBagConstraints.BOTH;
		gbc_btnPreferences.insets = new Insets(0, 0, 0, 5);
		gbc_btnPreferences.gridx = 0;
		gbc_btnPreferences.gridy = 0;
		panel.add(btnPreferences, gbc_btnPreferences);

		IButton btnEnter = new IButton();
		btnEnter.setFocusPainted(false);
		btnEnter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				client = new Client(ip_panel.getIpAddress());
				if (client.getClientSocket() != null)
				{
					client.sendData("USUARIO "
					+ textField_User.getText().trim());
					if (client.getInputCode() == 501
					|| client.getInputCode() == 502)
					{
						JOptionPane.showMessageDialog(Window.getInstance(),
						client.getInputDescription(), "Error",
						JOptionPane.ERROR_MESSAGE, new ImageIcon(
						"img/error-icon.png"));
					}
					else if (client.getInputCode() == 301)
					{
						client.sendData("CLAVE "
						+ StringUtils.sha1(new String(passwordField
						.getPassword())));
						if (client.getInputCode() == 503
						|| client.getInputCode() == 504)
						{
							JOptionPane.showMessageDialog(Window.getInstance(),
							client.getInputDescription(), "Error",
							JOptionPane.ERROR_MESSAGE, new ImageIcon(
							"img/error-icon.png"));
						}
						else if (client.getInputCode() == 302)
						{
							// BIENVENIDO AL SISTEMA
						}
					}
				}
			}
		});
		Lang.setLine(btnEnter, "btn_enter");
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnter.fill = GridBagConstraints.BOTH;
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 0;
		panel.add(btnEnter, gbc_btnEnter);

		IButton btnExit = new IButton();
		btnExit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Window.getInstance().dispose();
			}
		});
		Lang.setLine(btnExit, "btn_exit");
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnExit.setFocusPainted(false);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 0;
		panel.add(btnExit, gbc_btnExit);
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

				Start st = new Start();
				Window.getInstance().setContentPane(st);
				Window.getInstance().setVisible(true);
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
				Window.getInstance().pack();
			}
		});
	}

}