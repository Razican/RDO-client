package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import utils.Lang;
import utils.Properties;

import components.IButton;
import components.ILabel;
import components.IPPanel;
import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends JPanel {

	private static final long	serialVersionUID	= - 6969744533822338215L;
	private JTextField			textField_User;
	private JPasswordField		passwordField;
	private JComboBox<String>	comboBoxLanguage;

	/**
	 * Create the panel.
	 */
	public Start()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 250, 0};
		gridBagLayout.rowHeights = new int[] {0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
		null, null));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(10, 20, 20, 10);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		try
		{
			editorPane.setPage(new File("data/index.html").toURI().toURL());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		editorPane.setBorder(null);
		scrollPane.setViewportView(editorPane);

		JPanel rightPanel = new JPanel();
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.insets = new Insets(10, 10, 20, 20);
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 0;
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
		lblIP.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_lblIP = new GridBagConstraints();
		gbc_lblIP.anchor = GridBagConstraints.WEST;
		gbc_lblIP.insets = new Insets(5, 0, 5, 5);
		gbc_lblIP.gridx = 0;
		gbc_lblIP.gridy = 0;
		rightPanel.add(lblIP, gbc_lblIP);

		IPPanel ip_panel = new IPPanel();
		GridBagConstraints gbc_ip_panel = new GridBagConstraints();
		gbc_ip_panel.insets = new Insets(5, 0, 5, 0);
		gbc_ip_panel.fill = GridBagConstraints.BOTH;
		gbc_ip_panel.gridx = 1;
		gbc_ip_panel.gridy = 0;
		rightPanel.add(ip_panel, gbc_ip_panel);

		ILabel lblUser = new ILabel();
		Lang.setLine(lblUser, "lbl_user");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(5, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 1;
		rightPanel.add(lblUser, gbc_lblUser);

		textField_User = new JTextField();
		textField_User.setForeground(Color.BLACK);
		textField_User.setFont(new Font("Calibri", Font.PLAIN, 14));
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
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(5, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		rightPanel.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(5, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		rightPanel.add(passwordField, gbc_passwordField);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setForeground(Color.BLACK);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(5, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 3;
		rightPanel.add(textArea, gbc_textArea);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		rightPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0};
		gbl_panel.columnWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		comboBoxLanguage = new JComboBox<String>(Lang.getCombableLocales());
		comboBoxLanguage.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Properties.setLocale(Lang.getAvailableLocales().get(
				comboBoxLanguage.getSelectedIndex()));
				Lang.setLang(Lang.getAvailableLocales().get(
				comboBoxLanguage.getSelectedIndex()));
			}
		});
		comboBoxLanguage.setForeground(Color.BLACK);
		comboBoxLanguage.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxLanguage.setSelectedIndex(Lang.getCurrentLocaleKey());
		GridBagConstraints gbc_comboBoxLanguage = new GridBagConstraints();
		gbc_comboBoxLanguage.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxLanguage.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxLanguage.gridx = 0;
		gbc_comboBoxLanguage.gridy = 0;
		panel.add(comboBoxLanguage, gbc_comboBoxLanguage);

		IButton btnEnter = new IButton("Entrar");
		Lang.setLine(btnEnter, "btn_enter");
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 0;
		panel.add(btnEnter, gbc_btnEnter);
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
