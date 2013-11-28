package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import components.IPasswordField;
import components.ITextField;

import effects.SLPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class Login extends SLPanel {

	private static final long	serialVersionUID	= - 2955634829598248100L;
	private JPanel				contentPane;
	private ITextField			textField_user;
	private IPasswordField		passwordField;
	private JButton				btnEnter;
	private JCheckBox			checkBoxRemember;

	/**
	 * Create the panel.
	 */
	public Login()
	{
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());

		this.contentPane = new JPanel();
		this.contentPane.setLayout(null);

		textField_user = new ITextField("Usuario", new ImageIcon(
		"img/user-icon.png"));
		textField_user.setFont(new Font("Calibri", Font.PLAIN, 20));
		textField_user.setColumns(15);
		textField_user.setBounds(41, 41, 300, 40);
		this.contentPane.add(textField_user);

		passwordField = new IPasswordField(new ImageIcon("img/key-icon.png"));
		passwordField.setBounds(41, 101, 300, 40);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setColumns(15);
		this.contentPane.add(passwordField);

		checkBoxRemember = new JCheckBox("Recordarme");
		checkBoxRemember.setOpaque(false);
		checkBoxRemember.setBounds(41, 165, 115, 31);
		checkBoxRemember.setForeground(Color.WHITE);
		checkBoxRemember.setFont(new Font("Calibri", Font.PLAIN, 18));
		this.contentPane.add(checkBoxRemember);

		btnEnter = new JButton("Entrar");
		btnEnter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		});
		btnEnter.setBounds(241, 163, 100, 35);
		this.contentPane.add(btnEnter);
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 18));
	}
}