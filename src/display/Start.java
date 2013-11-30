package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.Lang;

import components.IPasswordField;
import components.ITextField;
import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends JPanel {

	private static final long	serialVersionUID	= - 3019955922941567348L;

	private JComboBox<String>	comboBox_lang;
	private ITextField			textField_user;
	private IPasswordField		passwordField;
	private ITextField			textField_ip_port;
	private JButton				btnEnter;

	/**
	 * Constructor
	 */
	public Start()
	{
		setBackground(Color.BLACK);
		setLayout(null);

		comboBox_lang = new JComboBox<String>(Lang.getCombableLocales());
		comboBox_lang.setOpaque(true);
		comboBox_lang.setBackground(Color.BLACK);
		comboBox_lang.setBorder(null);
		comboBox_lang.setForeground(Color.GREEN);
		comboBox_lang.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBox_lang.setBounds(225, 390, 180, 40);
		add(comboBox_lang);

		textField_user = new ITextField("Usuario", new ImageIcon(
		"img/user-icon.png"));
		textField_user.setErrorIcon(new ImageIcon("img/error-icon.png"));
		textField_user.setBounds(225, 210, 350, 45);
		add(textField_user);
		textField_user.setColumns(15);

		passwordField = new IPasswordField(new ImageIcon("img/key-icon.png"));
		passwordField.setErrorIcon(new ImageIcon("img/error-icon.png"));
		passwordField.setBounds(225, 270, 350, 45);
		passwordField.setColumns(15);
		add(passwordField);

		textField_ip_port = new ITextField("IP:PORT", null);
		textField_ip_port.setErrorIcon(new ImageIcon("img/error-icon.png"));
		textField_ip_port.setColumns(15);
		textField_ip_port.setBounds(225, 330, 350, 45);
		add(textField_ip_port);

		btnEnter = new JButton("Entrar");
		btnEnter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String regex = "^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]):(102[4-9]|10[3-9][0-9]|1[1-9][0-9]{2}|[2-9][0-9]{3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";

				String ip = textField_ip_port.getText().trim();
				textField_user.showError();
				passwordField.showError();

				if ( ! ip.matches(regex))
				{
					textField_ip_port.showError();
				}
				else
				{
					textField_ip_port.hideError();
				}
			}
		});
		btnEnter.setBorder(null);
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setFocusPainted(false);
		btnEnter.setBorderPainted(false);
		btnEnter.setForeground(Color.GREEN);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnEnter.setBounds(425, 390, 150, 40);
		add(btnEnter);
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