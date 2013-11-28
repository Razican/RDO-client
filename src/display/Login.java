package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.Lang;
import utils.Properties;

import components.IPasswordField;
import components.ITextField;
import components.VibratePanel;
import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Login extends JPanel {

	private static final long		serialVersionUID	= - 2955634829598248100L;

	private final JComboBox<String>	langCombo;
	private ITextField				textField_user;
	private IPasswordField			passwordField;
	private JButton					btnEnter;
	private JCheckBox				checkBoxRemember;
	private VibratePanel			panel;

	/**
	 * Create the panel.
	 */
	public Login()
	{
		setBackground(Color.BLACK);
		setLayout(null);
		setSize(new Dimension(800, 600));

		langCombo = new JComboBox<>(Lang.getCombableLocales());
		langCombo.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Properties.setLocale(Lang.getAvailableLocales().get(
				langCombo.getSelectedIndex()));
				Lang.setLang(Lang.getAvailableLocales().get(
				langCombo.getSelectedIndex()));
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
		});
		langCombo.setSize(200, 30);
		langCombo.setLocation(590, 10);
		langCombo.setForeground(Color.BLACK);
		langCombo.setFont(new Font("Calibri", Font.PLAIN, 15));
		langCombo.setSelectedIndex(Lang.getCurrentLocaleKey());
		add(langCombo);

		textField_user = new ITextField("Usuario", new ImageIcon(
		"img/user-icon.png"));
		textField_user.setFont(new Font("Calibri", Font.ITALIC, 20));
		textField_user.setColumns(15);
		textField_user.setBounds(250, 255, 300, 40);
		add(textField_user);

		passwordField = new IPasswordField(null, new ImageIcon(
		"img/key-icon.png"));
		passwordField.setBounds(250, 315, 300, 40);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setColumns(15);
		add(passwordField);

		checkBoxRemember = new JCheckBox("Recordarme");
		checkBoxRemember.setOpaque(false);
		checkBoxRemember.setBounds(250, 379, 115, 31);
		checkBoxRemember.setForeground(Color.WHITE);
		checkBoxRemember.setFont(new Font("Calibri", Font.PLAIN, 18));
		add(checkBoxRemember);

		btnEnter = new JButton("Entrar");
		btnEnter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				panel.vibrate();
			}
		});
		btnEnter.setBounds(450, 377, 100, 35);
		add(btnEnter);
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 18));

		panel = new VibratePanel(86, 464);
		panel.setBounds(86, 464, 124, 80);
		add(panel);
	}
}