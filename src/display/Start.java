package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import utils.Client;
import utils.Lang;
import utils.Properties;
import utils.StringUtils;

import components.IButton;
import components.IComboBox;
import components.ILabel;
import components.IPPanel;
import components.IPanel;
import components.Window;

import entities.Server;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends JPanel {

	private static final long	serialVersionUID	= - 6969744533822338215L;

	private IPPanel				ip_panel;
	private JTextField			textField_Port;
	private JTextField			textField_User;
	private JPasswordField		passwordField;
	private IComboBox			comboBox_servers;
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

		IPanel loginPanel = new IPanel();
		loginPanel.setBorder(new TitledBorder(null, Lang.getLine("login_pane"),
		TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Calibri",
		Font.ITALIC, 14), null));
		Lang.setLine(loginPanel, "login_pane");
		GridBagConstraints gbc_loginPanel = new GridBagConstraints();
		gbc_loginPanel.insets = new Insets(10, 10, 10, 10);
		gbc_loginPanel.fill = GridBagConstraints.BOTH;
		gbc_loginPanel.gridx = 1;
		gbc_loginPanel.gridy = 1;
		add(loginPanel, gbc_loginPanel);
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] {0, 0, 0};
		gbl_loginPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		gbl_loginPanel.columnWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
		gbl_loginPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
		0.0, Double.MIN_VALUE};
		loginPanel.setLayout(gbl_loginPanel);

		ILabel lblIP = new ILabel();
		Lang.setLine(lblIP, "lbl_server_ip");
		lblIP.setForeground(Color.BLACK);
		lblIP.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblIP = new GridBagConstraints();
		gbc_lblIP.anchor = GridBagConstraints.WEST;
		gbc_lblIP.insets = new Insets(5, 0, 5, 5);
		gbc_lblIP.gridx = 0;
		gbc_lblIP.gridy = 0;
		loginPanel.add(lblIP, gbc_lblIP);

		ip_panel = new IPPanel();
		GridBagConstraints gbc_ip_panel = new GridBagConstraints();
		gbc_ip_panel.insets = new Insets(5, 0, 5, 0);
		gbc_ip_panel.fill = GridBagConstraints.BOTH;
		gbc_ip_panel.gridx = 1;
		gbc_ip_panel.gridy = 0;
		loginPanel.add(ip_panel, gbc_ip_panel);

		ILabel lblPORT = new ILabel();
		Lang.setLine(lblPORT, "lbl_server_port");
		lblPORT.setForeground(Color.BLACK);
		lblPORT.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPORT = new GridBagConstraints();
		gbc_lblPORT.anchor = GridBagConstraints.WEST;
		gbc_lblPORT.insets = new Insets(5, 0, 5, 5);
		gbc_lblPORT.gridx = 0;
		gbc_lblPORT.gridy = 1;
		loginPanel.add(lblPORT, gbc_lblPORT);

		textField_Port = new JTextField();
		textField_Port.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				System.out.println(textField_Port.getText().length());
				if (textField_Port.getText().length() > 0)
				{
					if (Integer.parseInt(textField_Port.getText()) > 65535)
					{
						textField_Port.setText("65535");
					}
				}
			}
		});
		textField_Port.setForeground(Color.BLACK);
		textField_Port.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_Port.setColumns(10);
		GridBagConstraints gbc_textField_Port = new GridBagConstraints();
		gbc_textField_Port.insets = new Insets(5, 0, 5, 0);
		gbc_textField_Port.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Port.gridx = 1;
		gbc_textField_Port.gridy = 1;
		loginPanel.add(textField_Port, gbc_textField_Port);

		ILabel lblUser = new ILabel();
		Lang.setLine(lblUser, "lbl_user");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(5, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 2;
		loginPanel.add(lblUser, gbc_lblUser);

		textField_User = new JTextField();
		textField_User.setForeground(Color.BLACK);
		textField_User.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_User = new GridBagConstraints();
		gbc_textField_User.insets = new Insets(5, 0, 5, 0);
		gbc_textField_User.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_User.gridx = 1;
		gbc_textField_User.gridy = 2;
		loginPanel.add(textField_User, gbc_textField_User);
		textField_User.setColumns(10);

		ILabel lblPassword = new ILabel();
		Lang.setLine(lblPassword, "lbl_password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(5, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		loginPanel.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(5, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		loginPanel.add(passwordField, gbc_passwordField);

		comboBox_servers = new IComboBox(getSavedServers());
		comboBox_servers.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (comboBox_servers.getSelectedIndex() > 0)
				{
					Server server = Properties.getServers().get(
					comboBox_servers.getSelectedIndex() - 1);
					textField_Port.setText(Integer.toString(server.getPORT()));
					textField_User.setText(server.getUser());
					passwordField.setText(server.getPassword());
					ip_panel.setIpAddress(server.getIP());
				}
			}
		});
		Lang.setLine(comboBox_servers, "combobox_servers");
		comboBox_servers.setForeground(Color.BLACK);
		comboBox_servers.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBox_servers.setVisible(Properties.isShowSavedServers());
		GridBagConstraints gbc_comboBox_savedUsers = new GridBagConstraints();
		gbc_comboBox_savedUsers.gridwidth = 2;
		gbc_comboBox_savedUsers.insets = new Insets(5, 0, 10, 0);
		gbc_comboBox_savedUsers.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_savedUsers.gridx = 0;
		gbc_comboBox_savedUsers.gridy = 4;
		loginPanel.add(comboBox_servers, gbc_comboBox_savedUsers);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		loginPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0};
		gbl_panel.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0,
		Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		IButton btnPreferences = new IButton();
		btnPreferences.setText("Preferencias");
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
					Properties.setShowSavedServers(p.areServersVisible());
					comboBox_servers.setVisible(p.areServersVisible());
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
		btnEnter.setText("Entrar");
		btnEnter.setFocusPainted(false);
		btnEnter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int port = getInputPort();
				String ip = ip_panel.getIpAddress();

				client = new Client(ip, port);
				if (client.isConnected())
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

		IButton btnExit = new IButton();
		btnExit.setText("Servers");
		btnExit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Window.getInstance().setContentPane(new ServersPanel());
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		});
		Lang.setLine(btnExit, "btn_servers");
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnExit.setFocusPainted(false);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.gridx = 1;
		gbc_btnExit.gridy = 0;
		panel.add(btnExit, gbc_btnExit);
		Lang.setLine(btnEnter, "btn_enter");
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnter.fill = GridBagConstraints.BOTH;
		gbc_btnEnter.gridx = 2;
		gbc_btnEnter.gridy = 0;
		panel.add(btnEnter, gbc_btnEnter);
	}

	private Vector<Object> getSavedServers()
	{
		Vector<Object> servers = new Vector<Object>();
		servers.add(Lang.getLine("combobox_servers"));
		for (int i = 0; i < Properties.getServers().size(); i++)
		{
			servers.add(Properties.getServers().get(i).toString());
		}
		return servers;
	}

	private int getInputPort()
	{
		if (textField_Port.getText().trim().equals(""))
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(textField_Port.getText().trim());
		}
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
			}
		});
	}

}