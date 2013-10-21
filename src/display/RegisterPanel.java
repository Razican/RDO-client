package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.Window;

import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class RegisterPanel extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= - 6477954250916691859L;

	private JTextField			textField_username;
	private JPasswordField		passwordField;
	private JTextField			textField_email;

	private JButton				btnSave;

	private JButton				btnCancel;

	/**
	 * Create the panel.
	 */
	public RegisterPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 0.0, 1.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblUsername = new JLabel("Nombre de usuario");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 10, 10);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		add(lblUsername, gbc_lblUsername);

		textField_username = new JTextField();
		textField_username.setMargin(new Insets(2, 5, 2, 2));
		textField_username.setForeground(Color.BLACK);
		textField_username.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_textField_username = new GridBagConstraints();
		gbc_textField_username.insets = new Insets(0, 0, 10, 5);
		gbc_textField_username.fill = GridBagConstraints.BOTH;
		gbc_textField_username.gridx = 2;
		gbc_textField_username.gridy = 1;
		add(textField_username, gbc_textField_username);
		textField_username.setColumns(25);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 10, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(2, 5, 2, 2));
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField.setColumns(25);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 10, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		add(passwordField, gbc_passwordField);

		JLabel lblEmail = new JLabel("Correo electrónico");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 20, 10);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		add(lblEmail, gbc_lblEmail);

		textField_email = new JTextField();
		textField_email.setMargin(new Insets(2, 5, 2, 2));
		textField_email.setForeground(Color.BLACK);
		textField_email.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_textField_email = new GridBagConstraints();
		gbc_textField_email.insets = new Insets(0, 0, 20, 5);
		gbc_textField_email.fill = GridBagConstraints.BOTH;
		gbc_textField_email.gridx = 2;
		gbc_textField_email.gridy = 3;
		add(textField_email, gbc_textField_email);
		textField_email.setColumns(25);

		Panel panelBtns = new Panel();
		GridBagConstraints gbc_panelBtns = new GridBagConstraints();
		gbc_panelBtns.fill = GridBagConstraints.BOTH;
		gbc_panelBtns.gridwidth = 2;
		gbc_panelBtns.insets = new Insets(0, 0, 5, 5);
		gbc_panelBtns.gridx = 1;
		gbc_panelBtns.gridy = 4;
		add(panelBtns, gbc_panelBtns);
		GridBagLayout gbl_panelBtns = new GridBagLayout();
		gbl_panelBtns.columnWidths = new int[] {0, 0, 0};
		gbl_panelBtns.rowHeights = new int[] {0, 0};
		gbl_panelBtns.columnWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBtns.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		panelBtns.setLayout(gbl_panelBtns);

		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(this);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 0;
		panelBtns.add(btnCancel, gbc_btnCancel);

		btnSave = new JButton("Guardar");
		btnSave.addActionListener(this);
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		panelBtns.add(btnSave, gbc_btnSave);
	}

	/**
	 * @return The username textfield.
	 */
	public JTextField getTextField_username()
	{
		return textField_username;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnCancel)
		{
			Login l = new Login();
			Window.getInstance().setContentPane(l);
			((JPanel) Window.getInstance().getContentPane()).updateUI();
			l.getTextField_user().requestFocus();
		}
		else if (e.getSource() == btnSave)
		{
			String username = textField_username.getText().trim();
			char[] password = passwordField.getPassword();
			String email = textField_email.getText().trim();

			if (username.equals("") || username.length() < 4
			|| password.length == 0 || password.length < 4)
			{
				String errorMessage = "Error en los campos:\n";
				if (username.equals(""))
				{
					errorMessage += " - Introduce un nombre de usuario.\n";
				}
				else if (username.length() < 4)
				{
					errorMessage += " - El nombre de usuario debe tener un mínimo de 4 carácteres.\n";
				}
				if (password.length == 0)
				{
					errorMessage += " - Introduce una contraseña.\n";
				}
				else if (password.length < 4)
				{
					errorMessage += " - La contraseña debe tener un mínimo de 4 carácteres.\n";
				}
				if ( ! email.equals("") && ! validateEmail(email))
				{
					errorMessage += " - El email introducido no es válido.\n";
				}
				JOptionPane.showMessageDialog(Window.getInstance(),
				errorMessage, "Error", JOptionPane.ERROR_MESSAGE,
				new ImageIcon("img/error-icon.png"));
			}
			else if (User.existsUser(username))
			{
				JOptionPane.showMessageDialog(Window.getInstance(),
				"El usuario " + username
				+ " ya existe.\nIntroduce otro nombre de usuario.", "Error",
				JOptionPane.ERROR_MESSAGE, new ImageIcon("img/error-icon.png"));
			}
			else
			{
				User.login(username, new String(password), email, "");
				JOptionPane.showMessageDialog(Window.getInstance(),
				"El usuario " + username + " se ha creado correctamente.",
				"Usuario creado", JOptionPane.PLAIN_MESSAGE, new ImageIcon(
				"img/ok-icon.png"));
			}
		}
	}

	private boolean validateEmail(String email)
	{
		Pattern pattern = Pattern
		.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}