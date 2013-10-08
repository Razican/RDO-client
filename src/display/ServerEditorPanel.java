package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import utils.Lang;

import components.ILabel;
import components.IPPanel;
import components.Window;

import entities.Server;

/**
 * @author Jordan Aranda Tejada
 */
public class ServerEditorPanel extends JPanel {

	private static final long	serialVersionUID	= - 5863508634818290364L;
	private IPPanel				ip_panel;
	private JTextField			textField_Port;
	private JTextField			textField_User;
	private JTextField			textField_Password;

	/**
	 * Create the panel.
	 * 
	 * @param server The server to edit (null for new server)
	 */
	public ServerEditorPanel(Server server)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 1.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, 1.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
		Lang.getLine("title_new_server"), TitledBorder.LEADING,
		TitledBorder.ABOVE_TOP, new Font("Calibri", Font.ITALIC, 14), null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(10, 10, 10, 10);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0,
		Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		ILabel lblIP = new ILabel(Lang.getLine("lbl_server_ip"));
		lblIP.setForeground(Color.BLACK);
		lblIP.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblIP = new GridBagConstraints();
		gbc_lblIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIP.insets = new Insets(5, 0, 5, 5);
		gbc_lblIP.gridx = 0;
		gbc_lblIP.gridy = 0;
		panel.add(lblIP, gbc_lblIP);

		ip_panel = new IPPanel();
		GridBagLayout gbl_ip_panel = (GridBagLayout) ip_panel.getLayout();
		gbl_ip_panel.rowWeights = new double[] {0.0};
		gbl_ip_panel.rowHeights = new int[] {10};
		gbl_ip_panel.columnWeights = new double[] {1.0, 0.0, 1.0, 0.0, 1.0,
		0.0, 1.0};
		gbl_ip_panel.columnWidths = new int[] {30, 5, 30, 5, 30, 5, 30};
		GridBagConstraints gbc_ip_panel = new GridBagConstraints();
		gbc_ip_panel.insets = new Insets(5, 0, 5, 0);
		gbc_ip_panel.fill = GridBagConstraints.BOTH;
		gbc_ip_panel.gridx = 1;
		gbc_ip_panel.gridy = 0;
		panel.add(ip_panel, gbc_ip_panel);

		ILabel lblPORT = new ILabel(Lang.getLine("lbl_server_port"));
		lblPORT.setForeground(Color.BLACK);
		lblPORT.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPORT = new GridBagConstraints();
		gbc_lblPORT.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPORT.insets = new Insets(5, 0, 5, 5);
		gbc_lblPORT.gridx = 0;
		gbc_lblPORT.gridy = 1;
		panel.add(lblPORT, gbc_lblPORT);

		textField_Port = new JTextField();
		textField_Port.setForeground(Color.BLACK);
		textField_Port.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_Port = new GridBagConstraints();
		gbc_textField_Port.insets = new Insets(5, 0, 5, 0);
		gbc_textField_Port.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Port.gridx = 1;
		gbc_textField_Port.gridy = 1;
		panel.add(textField_Port, gbc_textField_Port);
		textField_Port.setColumns(10);

		JLabel lblUser = new JLabel(Lang.getLine("lbl_user"));
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUser.insets = new Insets(5, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 2;
		panel.add(lblUser, gbc_lblUser);

		textField_User = new JTextField();
		textField_User.setForeground(Color.BLACK);
		textField_User.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_User.setColumns(10);
		GridBagConstraints gbc_textField_User = new GridBagConstraints();
		gbc_textField_User.insets = new Insets(5, 0, 5, 0);
		gbc_textField_User.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_User.gridx = 1;
		gbc_textField_User.gridy = 2;
		panel.add(textField_User, gbc_textField_User);

		ILabel lblPassword = new ILabel(Lang.getLine("lbl_password"));
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.insets = new Insets(5, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		panel.add(lblPassword, gbc_lblPassword);

		textField_Password = new JTextField();
		textField_Password.setForeground(Color.BLACK);
		textField_Password.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_Password.setColumns(10);
		GridBagConstraints gbc_textField_Password = new GridBagConstraints();
		gbc_textField_Password.insets = new Insets(5, 0, 5, 0);
		gbc_textField_Password.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Password.gridx = 1;
		gbc_textField_Password.gridy = 3;
		panel.add(textField_Password, gbc_textField_Password);

		if (server != null)
		{
			ip_panel.setIpAddress(server.getIP());
			textField_Port.setText(Integer.toString(server.getPORT()));
			textField_User.setText(server.getUser());
			textField_Password.setText(server.getPassword());
			panel
			.setBorder(new TitledBorder(null,
			Lang.getLine("title_edit_server"), TitledBorder.LEADING,
			TitledBorder.ABOVE_TOP, new Font("Calibri", Font.ITALIC, 14), null));
		}

		JPanel menu_panel = new JPanel();
		GridBagConstraints gbc_menu_panel = new GridBagConstraints();
		gbc_menu_panel.insets = new Insets(5, 0, 0, 0);
		gbc_menu_panel.gridwidth = 2;
		gbc_menu_panel.fill = GridBagConstraints.BOTH;
		gbc_menu_panel.gridx = 0;
		gbc_menu_panel.gridy = 4;
		panel.add(menu_panel, gbc_menu_panel);
		GridBagLayout gbl_menu_panel = new GridBagLayout();
		gbl_menu_panel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_menu_panel.rowHeights = new int[] {0, 0};
		gbl_menu_panel.columnWeights = new double[] {1.0, 0.0, 0.0,
		Double.MIN_VALUE};
		gbl_menu_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		menu_panel.setLayout(gbl_menu_panel);

		JButton btnSave = new JButton(Lang.getLine("btn_save_server"));
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		menu_panel.add(btnSave, gbc_btnSave);

		JButton btnCancell = new JButton(Lang.getLine("cancel_option"));
		btnCancell.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Window.getInstance().setContentPane(new ServersPanel());
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		});
		btnCancell.setForeground(Color.BLACK);
		btnCancell.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		gbc_btnReturn.gridx = 2;
		gbc_btnReturn.gridy = 0;
		menu_panel.add(btnCancell, gbc_btnReturn);
	}

}
