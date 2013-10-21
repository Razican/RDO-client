package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import utils.Lang;
import utils.Properties;

import components.Window;

import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Login extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= - 2955634829598248100L;

	private JTextField			textField_user;
	private JPasswordField		passwordField;
	private JButton				btnPreferences;
	private JButton				btnRegister;
	private JButton				btnEnter;
	private JCheckBox			checkBoxRemember;

	/**
	 * Create the panel.
	 */
	public Login()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 0.0, 1.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.insets = new Insets(0, 0, 10, 10);
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 1;
		add(lblUser, gbc_lblUser);

		textField_user = new JTextField();
		textField_user.setMargin(new Insets(2, 5, 2, 2));
		textField_user.setForeground(Color.BLACK);
		textField_user.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_textField_user = new GridBagConstraints();
		gbc_textField_user.insets = new Insets(0, 0, 10, 5);
		gbc_textField_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_user.gridx = 2;
		gbc_textField_user.gridy = 1;
		add(textField_user, gbc_textField_user);
		textField_user.setColumns(15);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 10, 10);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(2, 5, 2, 2));
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField.setColumns(15);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 10, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		add(passwordField, gbc_passwordField);

		checkBoxRemember = new JCheckBox("Recordarme");
		checkBoxRemember.setForeground(Color.BLACK);
		checkBoxRemember.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_chckbxRecordarme = new GridBagConstraints();
		gbc_chckbxRecordarme.anchor = GridBagConstraints.EAST;
		gbc_chckbxRecordarme.insets = new Insets(0, 0, 10, 5);
		gbc_chckbxRecordarme.gridx = 2;
		gbc_chckbxRecordarme.gridy = 3;
		add(checkBoxRemember, gbc_chckbxRecordarme);

		Panel panel_buttons = new Panel();
		GridBagConstraints gbc_panel_buttons = new GridBagConstraints();
		gbc_panel_buttons.gridwidth = 2;
		gbc_panel_buttons.fill = GridBagConstraints.BOTH;
		gbc_panel_buttons.insets = new Insets(0, 0, 5, 5);
		gbc_panel_buttons.gridx = 1;
		gbc_panel_buttons.gridy = 4;
		add(panel_buttons, gbc_panel_buttons);
		GridBagLayout gbl_panel_buttons = new GridBagLayout();
		gbl_panel_buttons.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel_buttons.rowHeights = new int[] {0, 0};
		gbl_panel_buttons.columnWeights = new double[] {0.0, 1.0, 1.0,
		Double.MIN_VALUE};
		gbl_panel_buttons.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		panel_buttons.setLayout(gbl_panel_buttons);

		btnPreferences = new JButton("Preferencias");
		btnPreferences.addActionListener(this);
		btnPreferences.setForeground(Color.BLACK);
		btnPreferences.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_btnPreferences = new GridBagConstraints();
		gbc_btnPreferences.insets = new Insets(0, 0, 0, 5);
		gbc_btnPreferences.gridx = 0;
		gbc_btnPreferences.gridy = 0;
		panel_buttons.add(btnPreferences, gbc_btnPreferences);

		btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(this);
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 0;
		panel_buttons.add(btnRegister, gbc_btnRegister);

		btnEnter = new JButton("Entrar");
		btnEnter.addActionListener(this);
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.anchor = GridBagConstraints.EAST;
		gbc_btnEnter.gridx = 2;
		gbc_btnEnter.gridy = 0;
		panel_buttons.add(btnEnter, gbc_btnEnter);
	}

	/**
	 * @return The username textfield
	 */
	public JTextField getTextField_user()
	{
		return textField_user;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnPreferences)
		{
			final Preferences p = new Preferences();

			final String[] options = {Lang.getLine("accept_option"),
			Lang.getLine("cancel_option")};
			JOptionPane pane = new JOptionPane(p, JOptionPane.PLAIN_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION, null, options, options[1]);
			final JDialog dialog = pane.createDialog(Lang
			.getLine("preferences"));
			dialog.setLocationRelativeTo(Window.getInstance());
			dialog.setResizable(false);
			dialog.pack();
			dialog.setIconImage(new ImageIcon("img/sett-icon.png").getImage());
			dialog.setVisible(true);

			if (pane.getValue() == options[0])
			{
				Properties.setLocale(Lang.getAvailableLocales().get(
				p.getLocaleIndex()));
				Lang
				.setLang(Lang.getAvailableLocales().get(p.getLocaleIndex()));

				Properties.setLookAndFeelClass(p.getLookAndFeel());
				if (Properties.getLookAndFeel().substring(0, 3).equals("org"))
				{
					SubstanceLookAndFeel.setSkin(Properties.getLookAndFeel());
				}
				else
				{
					try
					{
						UIManager.setLookAndFeel(Properties.getLookAndFeel());
					}
					catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1)
					{
						e1.printStackTrace();
					}
				}
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
			dialog.dispose();
		}
		else if (e.getSource() == btnRegister)
		{
			RegisterPanel registerPanel = new RegisterPanel();
			Window.getInstance().setContentPane(registerPanel);
			((JPanel) Window.getInstance().getContentPane()).updateUI();
			registerPanel.getTextField_username().requestFocus();
		}
		else if (e.getSource() == btnEnter)
		{
			String username = textField_user.getText().trim();
			char[] password = passwordField.getPassword();
			User.load(username, new String(passwordField.getPassword()));
			if (username.equals("") || password.length == 0
			|| User.getCurrent() == null)
			{
				String errorMessage = "Error en los campos:\n";
				if (username.equals(""))
				{
					errorMessage += " - Introduce un nombre de usuario.\n";
				}
				if (password.length == 0)
				{
					errorMessage += " - Introduce la contraseña.\n";
				}
				if ( ! username.equals("") && password.length > 0
				&& User.getCurrent() == null)
				{
					errorMessage = "Error en los datos de acceso.";
				}
				JOptionPane.showMessageDialog(Window.getInstance(),
				errorMessage, "Error", JOptionPane.ERROR_MESSAGE,
				new ImageIcon("img/error-icon.png"));
			}
			else
			{
				User.getCurrent().setRemember(checkBoxRemember.isSelected());
				User.getCurrent().save();
				Window.getInstance().setContentPane(new MainPanel());
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		}
	}
}