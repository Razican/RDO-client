package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.Client;
import utils.Lang;
import utils.Properties;

import components.IButton;
import components.ILabel;
import components.IPanel;
import components.IPasswordField;
import components.ITextField;
import components.Menu;
import components.Window;

import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends IPanel implements ActionListener {

	private static final long	serialVersionUID	= - 3019955922941567348L;

	private JComboBox<String>	comboBox_lang;
	private ITextField			textField_user;
	private IPasswordField		passwordField;
	private ITextField			textField_ip_port;
	private IButton				btnEnter;
	private Client				client;

	/**
	 * Constructor
	 */
	public Start()
	{
		setBackgroundImage(new ImageIcon("img/background.jpg"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {225, 350, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {50, 159, 50, 50, 50, 50, 115, 25,
		0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 1.0, 0.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
		1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		comboBox_lang = new JComboBox<String>(Lang.getCombableLocales());
		comboBox_lang.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Properties.setLocale(Lang.getAvailableLocales().get(
				comboBox_lang.getSelectedIndex()));
				Lang.setLang(Lang.getAvailableLocales().get(
				comboBox_lang.getSelectedIndex()));
			}
		});
		comboBox_lang.setOpaque(false);
		comboBox_lang.setBackground(Color.BLACK);
		comboBox_lang.setBorder(null);
		comboBox_lang.setForeground(Color.GREEN);
		comboBox_lang.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBox_lang.setSelectedIndex(Lang.getCurrentLocaleKey());
		GridBagConstraints gbc_comboBox_lang = new GridBagConstraints();
		gbc_comboBox_lang.fill = GridBagConstraints.BOTH;
		gbc_comboBox_lang.insets = new Insets(10, 0, 5, 10);
		gbc_comboBox_lang.gridx = 3;
		gbc_comboBox_lang.gridy = 0;
		add(comboBox_lang, gbc_comboBox_lang);

		btnEnter = new IButton();
		Lang.setLine(btnEnter, "start_btn_connect");
		btnEnter.addActionListener(this);

		textField_ip_port = new ITextField("", new ImageIcon(
		"img/network-icon.png"));
		Lang.setLine(textField_ip_port, "start_textfield_ip_port");
		textField_ip_port.setErrorIcon(new ImageIcon("img/error-icon.png"));
		textField_ip_port.setColumns(15);
		GridBagConstraints gbc_textField_ip_port = new GridBagConstraints();
		gbc_textField_ip_port.fill = GridBagConstraints.BOTH;
		gbc_textField_ip_port.insets = new Insets(0, 0, 10, 5);
		gbc_textField_ip_port.gridx = 1;
		gbc_textField_ip_port.gridy = 2;
		add(textField_ip_port, gbc_textField_ip_port);

		textField_user = new ITextField("", new ImageIcon("img/user-icon.png"));
		Lang.setLine(textField_user, "start_textfield_user");
		textField_user.setErrorIcon(new ImageIcon("img/error-icon.png"));
		GridBagConstraints gbc_textField_user = new GridBagConstraints();
		gbc_textField_user.fill = GridBagConstraints.BOTH;
		gbc_textField_user.insets = new Insets(0, 0, 10, 5);
		gbc_textField_user.gridx = 1;
		gbc_textField_user.gridy = 3;
		add(textField_user, gbc_textField_user);
		textField_user.setColumns(15);

		passwordField = new IPasswordField(new ImageIcon("img/key-icon.png"));
		passwordField.setErrorIcon(new ImageIcon("img/error-icon.png"));
		passwordField.setColumns(15);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 10, 5);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		add(passwordField, gbc_passwordField);
		btnEnter.setBorder(null);
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setFocusPainted(false);
		btnEnter.setBorderPainted(false);
		btnEnter.setForeground(Color.GREEN);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.anchor = GridBagConstraints.EAST;
		gbc_btnEnter.fill = GridBagConstraints.VERTICAL;
		gbc_btnEnter.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 5;
		add(btnEnter, gbc_btnEnter);

		ILabel lblVersion = new ILabel();
		lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersion.setText("Version " + Properties.getVersion());
		lblVersion.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblVersion.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.insets = new Insets(0, 0, 10, 10);
		gbc_lblVersion.anchor = GridBagConstraints.EAST;
		gbc_lblVersion.fill = GridBagConstraints.VERTICAL;
		gbc_lblVersion.gridx = 3;
		gbc_lblVersion.gridy = 7;
		add(lblVersion, gbc_lblVersion);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (btnEnter == e.getSource())
		{
			String ip = textField_ip_port.getText().trim();

			String regex = "^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).)"
			+ "{3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]):(10"
			+ "2[4-9]|10[3-9][0-9]|1[1-9][0-9]{2}|[2-9][0-9]{3}|[1"
			+ "-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2]["
			+ "0-9]|6553[0-5])$";

			if (ip.equals("") || ! ip.matches(regex))
			{
				textField_ip_port.showError();

				if (ip.equals(""))
				{
					JOptionPane.showMessageDialog(Window.getInstance(),
					"Introduce la dirección IP y el puerto.", "Error",
					JOptionPane.ERROR_MESSAGE);
				}
				else if ( ! ip.matches(regex))
				{
					JOptionPane.showMessageDialog(Window.getInstance(),
					"La dirección ip y el puerto no tienen el formato adecuado.\n"
					+ "Ejemplo: 127.0.0.1:5000", "Error",
					JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				String name = textField_user.getText().trim();
				char[] password = passwordField.getPassword();
				String[] array = ip.split(":");
				client = new Client(array[0], Integer.parseInt(array[1]));

				User.load(name, client);
				System.out.println("SOCKET CLIENTE: "
				+ User.getCurrent().getClient().getClientSocket());
				if (User.checkUser() == 501)
				{
					JOptionPane.showMessageDialog(this,
					"Falta el nombre de usuario", "Error",
					JOptionPane.ERROR_MESSAGE);
				}
				else if (User.checkUser() == 301)
				{
					if (User.checkPassword(password) == 502)
					{
						JOptionPane.showMessageDialog(this,
						"La contraseña es incorrecta.", "Error",
						JOptionPane.ERROR_MESSAGE);
					}
					else if (User.checkPassword(password) == 503)
					{
						JOptionPane.showMessageDialog(this, "Falta la clave.",
						"Error", JOptionPane.ERROR_MESSAGE);
					}
					else if (User.checkPassword(password) == 302)
					{
						Window.getInstance().setContentPane(new UserPanel());
						Window.getInstance().setJMenuBar(new Menu(Color.BLACK));
						((JPanel) Window.getInstance().getContentPane())
						.updateUI();
					}
				}
			}
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

				// User.load("Admin", new Client("127.0.0.1", 5000));
				Window.getInstance().setContentPane(new Start());
				// Window.getInstance().setJMenuBar(new Menu(Color.BLACK));
				Window.getInstance().setVisible(true);
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
		});
	}
}