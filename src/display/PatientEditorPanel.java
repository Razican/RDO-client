package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.freixas.jcalendar.JCalendar;

import utils.Lang;
import utils.Utilities;

import components.IPPanel;
import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class PatientEditorPanel extends JPanel {

	private static final long	serialVersionUID	= 8017107363850239824L;
	private JTextField			textField_dni;
	private JTextField			textField_dni_letter;
	private JTextField			textField_name;
	private JTextField			textField_lastnames;
	private JTextField			textField_address;
	private JTextField			textField_telephone;
	private JTextField			textField_email;
	private JLabel				lblSelectedDate;
	private JTextField			textField_port;

	/**
	 * Create the panel.
	 */
	public PatientEditorPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 250, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {10.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel leftPanel = new JPanel();
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(10, 10, 10, 5);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		add(leftPanel, gbc_leftPanel);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] {0, 0};
		gbl_leftPanel.rowHeights = new int[] {0, 0};
		gbl_leftPanel.columnWeights = new double[] {10.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[] {10.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);

		JPanel mainDataPanel = new JPanel();
		mainDataPanel.setBorder(new TitledBorder(UIManager
		.getBorder("TitledBorder.border"), "Datos principales",
		TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_mainDataPanel = new GridBagConstraints();
		gbc_mainDataPanel.fill = GridBagConstraints.BOTH;
		gbc_mainDataPanel.gridx = 0;
		gbc_mainDataPanel.gridy = 0;
		leftPanel.add(mainDataPanel, gbc_mainDataPanel);
		GridBagLayout gbl_mainDataPanel = new GridBagLayout();
		gbl_mainDataPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gbl_mainDataPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainDataPanel.columnWeights = new double[] {0.0, 1.0, 1.0, 0.0,
		0.0, Double.MIN_VALUE};
		gbl_mainDataPanel.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0,
		1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
		Double.MIN_VALUE};
		mainDataPanel.setLayout(gbl_mainDataPanel);

		JLabel lblDni = new JLabel("Dni");
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.WEST;
		gbc_lblDni.insets = new Insets(10, 10, 5, 20);
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 0;
		mainDataPanel.add(lblDni, gbc_lblDni);

		textField_dni = new JTextField();
		textField_dni.setForeground(Color.BLACK);
		textField_dni.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_textField_dni = new GridBagConstraints();
		gbc_textField_dni.gridwidth = 2;
		gbc_textField_dni.insets = new Insets(10, 0, 5, 5);
		gbc_textField_dni.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_dni.gridx = 1;
		gbc_textField_dni.gridy = 0;
		mainDataPanel.add(textField_dni, gbc_textField_dni);
		textField_dni.setColumns(10);

		JLabel lbl = new JLabel("-");
		lbl.setForeground(Color.BLACK);
		lbl.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.EAST;
		gbc_lbl.insets = new Insets(10, 5, 5, 5);
		gbc_lbl.gridx = 3;
		gbc_lbl.gridy = 0;
		mainDataPanel.add(lbl, gbc_lbl);

		textField_dni_letter = new JTextField();
		textField_dni_letter.setForeground(Color.BLACK);
		textField_dni_letter.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_textField_dni_letter = new GridBagConstraints();
		gbc_textField_dni_letter.insets = new Insets(10, 0, 5, 10);
		gbc_textField_dni_letter.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_dni_letter.gridx = 4;
		gbc_textField_dni_letter.gridy = 0;
		mainDataPanel.add(textField_dni_letter, gbc_textField_dni_letter);
		textField_dni_letter.setColumns(2);

		JLabel lblName = new JLabel("Nombre");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 10, 5, 20);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		mainDataPanel.add(lblName, gbc_lblName);

		textField_name = new JTextField();
		textField_name.setForeground(Color.BLACK);
		textField_name.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_name.setColumns(10);
		GridBagConstraints gbc_textField_name = new GridBagConstraints();
		gbc_textField_name.gridwidth = 4;
		gbc_textField_name.insets = new Insets(0, 0, 5, 10);
		gbc_textField_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_name.gridx = 1;
		gbc_textField_name.gridy = 2;
		mainDataPanel.add(textField_name, gbc_textField_name);

		JLabel lblLastName = new JLabel("Apellidos");
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 10, 5, 20);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 4;
		mainDataPanel.add(lblLastName, gbc_lblLastName);

		textField_lastnames = new JTextField();
		textField_lastnames.setForeground(Color.BLACK);
		textField_lastnames.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_lastnames.setColumns(10);
		GridBagConstraints gbc_textField_lastnames = new GridBagConstraints();
		gbc_textField_lastnames.gridwidth = 4;
		gbc_textField_lastnames.insets = new Insets(0, 0, 5, 10);
		gbc_textField_lastnames.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_lastnames.gridx = 1;
		gbc_textField_lastnames.gridy = 4;
		mainDataPanel.add(textField_lastnames, gbc_textField_lastnames);

		JLabel lblBirthDate = new JLabel("Fecha de nacimiento");
		lblBirthDate.setForeground(Color.BLACK);
		lblBirthDate.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblBirthDate.anchor = GridBagConstraints.WEST;
		gbc_lblBirthDate.insets = new Insets(0, 10, 5, 20);
		gbc_lblBirthDate.gridx = 0;
		gbc_lblBirthDate.gridy = 6;
		mainDataPanel.add(lblBirthDate, gbc_lblBirthDate);

		lblSelectedDate = new JLabel("");
		lblSelectedDate.setForeground(Color.BLACK);
		lblSelectedDate.setFont(new Font("Calibri", Font.ITALIC, 16));
		GridBagConstraints gbc_lblSelectedDate = new GridBagConstraints();
		gbc_lblSelectedDate.anchor = GridBagConstraints.WEST;
		gbc_lblSelectedDate.insets = new Insets(0, 10, 5, 5);
		gbc_lblSelectedDate.gridx = 2;
		gbc_lblSelectedDate.gridy = 6;
		mainDataPanel.add(lblSelectedDate, gbc_lblSelectedDate);

		JButton btnCalendar = new JButton(
		new ImageIcon("img/calendar-icon.png"));
		btnCalendar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JCalendar calendar = new JCalendar();
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

				String[] options = {Lang.getLine("accept_option"),
				Lang.getLine("cancel_option")};

				JOptionPane pane = new JOptionPane(calendar,
				JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
				options, options[1]);

				final JDialog dialog = pane.createDialog(Lang
				.getLine("preferences"));
				dialog.setLocationRelativeTo(Window.getInstance());
				dialog.setResizable(false);
				dialog.pack();
				dialog.setIconImage(new ImageIcon("img/calendar-icon.png")
				.getImage());
				dialog.setVisible(true);

				if (pane.getValue() == options[0])
				{
					lblSelectedDate.setText(df.format(calendar.getDate()));
				}
				dialog.dispose();
			}
		});
		GridBagConstraints gbc_btnCalendar = new GridBagConstraints();
		gbc_btnCalendar.anchor = GridBagConstraints.WEST;
		gbc_btnCalendar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalendar.gridx = 1;
		gbc_btnCalendar.gridy = 6;
		mainDataPanel.add(btnCalendar, gbc_btnCalendar);

		JLabel lblAddress = new JLabel("Dirección");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 10, 5, 20);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 8;
		mainDataPanel.add(lblAddress, gbc_lblAddress);

		textField_address = new JTextField();
		textField_address.setForeground(Color.BLACK);
		textField_address.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_address.setColumns(10);
		GridBagConstraints gbc_textField_address = new GridBagConstraints();
		gbc_textField_address.gridwidth = 4;
		gbc_textField_address.insets = new Insets(0, 0, 5, 10);
		gbc_textField_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_address.gridx = 1;
		gbc_textField_address.gridy = 8;
		mainDataPanel.add(textField_address, gbc_textField_address);

		JLabel lblTelephone = new JLabel("Teléfono");
		lblTelephone.setForeground(Color.BLACK);
		lblTelephone.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblTelephone = new GridBagConstraints();
		gbc_lblTelephone.anchor = GridBagConstraints.WEST;
		gbc_lblTelephone.insets = new Insets(0, 10, 5, 20);
		gbc_lblTelephone.gridx = 0;
		gbc_lblTelephone.gridy = 10;
		mainDataPanel.add(lblTelephone, gbc_lblTelephone);

		textField_telephone = new JTextField();
		textField_telephone.setForeground(Color.BLACK);
		textField_telephone.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_telephone.setColumns(10);
		GridBagConstraints gbc_textField_telephone = new GridBagConstraints();
		gbc_textField_telephone.gridwidth = 4;
		gbc_textField_telephone.insets = new Insets(0, 0, 5, 10);
		gbc_textField_telephone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_telephone.gridx = 1;
		gbc_textField_telephone.gridy = 10;
		mainDataPanel.add(textField_telephone, gbc_textField_telephone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 10, 5, 20);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 12;
		mainDataPanel.add(lblEmail, gbc_lblEmail);

		textField_email = new JTextField();
		textField_email.setForeground(Color.BLACK);
		textField_email.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_email.setColumns(10);
		GridBagConstraints gbc_textField_email = new GridBagConstraints();
		gbc_textField_email.gridwidth = 4;
		gbc_textField_email.insets = new Insets(0, 0, 5, 10);
		gbc_textField_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_email.gridx = 1;
		gbc_textField_email.gridy = 12;
		mainDataPanel.add(textField_email, gbc_textField_email);

		JLabel lblIp = new JLabel("Dirección Ip");
		lblIp.setForeground(Color.BLACK);
		lblIp.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.WEST;
		gbc_lblIp.insets = new Insets(0, 10, 5, 20);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 14;
		mainDataPanel.add(lblIp, gbc_lblIp);

		IPPanel ipPanel = new IPPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.gridwidth = 4;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 14;
		mainDataPanel.add(ipPanel, gbc_panel);

		JLabel lblPort = new JLabel("Puerto");
		lblPort.setForeground(Color.BLACK);
		lblPort.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.WEST;
		gbc_lblPort.insets = new Insets(0, 10, 5, 20);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 16;
		mainDataPanel.add(lblPort, gbc_lblPort);

		textField_port = new JTextField();
		textField_port.setForeground(Color.BLACK);
		textField_port.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_port.setColumns(10);
		GridBagConstraints gbc_textField_port = new GridBagConstraints();
		gbc_textField_port.gridwidth = 4;
		gbc_textField_port.insets = new Insets(0, 0, 5, 10);
		gbc_textField_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_port.gridx = 1;
		gbc_textField_port.gridy = 16;
		mainDataPanel.add(textField_port, gbc_textField_port);

		JPanel rightPanel = new JPanel();
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.insets = new Insets(10, 5, 10, 10);
		gbc_rightPanel.fill = GridBagConstraints.VERTICAL;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 0;
		add(rightPanel, gbc_rightPanel);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] {0, 0};
		gbl_rightPanel.rowHeights = new int[] {0, 0, 0};
		gbl_rightPanel.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
		rightPanel.setLayout(gbl_rightPanel);

		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(new TitledBorder(null, "Imagen",
		TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_imagePanel = new GridBagConstraints();
		gbc_imagePanel.insets = new Insets(0, 0, 5, 0);
		gbc_imagePanel.gridx = 0;
		gbc_imagePanel.gridy = 0;
		rightPanel.add(imagePanel, gbc_imagePanel);
		GridBagLayout gbl_imagePanel = new GridBagLayout();
		gbl_imagePanel.columnWidths = new int[] {0, 0};
		gbl_imagePanel.rowHeights = new int[] {200, 0, 0};
		gbl_imagePanel.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_imagePanel.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
		imagePanel.setLayout(gbl_imagePanel);

		JLabel lblImage = new JLabel(Utilities.getScaledImage(200, 200,
		new ImageIcon("img/no-photo-icon.png")));
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.fill = GridBagConstraints.BOTH;
		gbc_lblImage.insets = new Insets(5, 5, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		imagePanel.add(lblImage, gbc_lblImage);

		JButton btnSeleccionarFoto = new JButton("Seleccionar foto");
		btnSeleccionarFoto.setForeground(Color.BLACK);
		btnSeleccionarFoto.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_btnSeleccionarFoto = new GridBagConstraints();
		gbc_btnSeleccionarFoto.fill = GridBagConstraints.BOTH;
		gbc_btnSeleccionarFoto.insets = new Insets(0, 10, 5, 10);
		gbc_btnSeleccionarFoto.gridx = 0;
		gbc_btnSeleccionarFoto.gridy = 1;
		imagePanel.add(btnSeleccionarFoto, gbc_btnSeleccionarFoto);

		JPanel btnsPanel = new JPanel();
		btnsPanel.setBorder(new TitledBorder(UIManager
		.getBorder("TitledBorder.border"), "Menu", TitledBorder.LEADING,
		TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_btnsPanel = new GridBagConstraints();
		gbc_btnsPanel.insets = new Insets(5, 0, 0, 0);
		gbc_btnsPanel.fill = GridBagConstraints.BOTH;
		gbc_btnsPanel.gridx = 0;
		gbc_btnsPanel.gridy = 1;
		rightPanel.add(btnsPanel, gbc_btnsPanel);
	}
}
