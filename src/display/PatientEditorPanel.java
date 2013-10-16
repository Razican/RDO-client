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
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.freixas.jcalendar.JCalendar;

import utils.Lang;

import components.IPPanel;
import components.Window;

import database.DataBase;
import entities.Patient;

/**
 * @author Jordan Aranda Tejada
 */
public class PatientEditorPanel extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= 8017107363850239824L;
	private JTextField			textField_dni;
	private JTextField			textField_letter;
	private JTextField			textField_name;
	private JTextField			textField_lastnames;
	private JTextField			textField_address;
	private JTextField			textField_telephone;
	private JTextField			textField_email;
	private JLabel				lblSelectedDate;
	private IPPanel				ip_panel;
	private JTextField			textField_port;

	private JCalendar			calendar;

	private JButton				btnCalendar;
	private JButton				btnReturn;
	private JButton				btnCleanAll;
	private JButton				btnSave;

	private Patient				patient;

	/**
	 * Create the panel.
	 * 
	 * @param patient The patient to edit (null if its a new patient)
	 */
	public PatientEditorPanel(Patient patient)
	{
		this.patient = patient;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder(UIManager
		.getBorder("TitledBorder.border"), "Datos personales",
		TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(10, 10, 5, 10);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		add(leftPanel, gbc_leftPanel);
		GridBagLayout gbl_mainDataPanel = new GridBagLayout();
		gbl_mainDataPanel.columnWidths = new int[] {0, 0, 0, 0, 50, 0};
		gbl_mainDataPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainDataPanel.columnWeights = new double[] {0.0, 0.0, 1.0, 0.0,
		0.0, Double.MIN_VALUE};
		gbl_mainDataPanel.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0,
		1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_mainDataPanel);

		JLabel lblDni = new JLabel("Dni");
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.WEST;
		gbc_lblDni.insets = new Insets(5, 10, 5, 20);
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 0;
		leftPanel.add(lblDni, gbc_lblDni);

		textField_dni = new JTextField();
		textField_dni.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
				if (textField_dni.getText().length() == 8)
				{
					e.consume();
				}
			}
		});
		textField_dni.setForeground(Color.BLACK);
		textField_dni.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_dni = new GridBagConstraints();
		gbc_textField_dni.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_dni.gridwidth = 2;
		gbc_textField_dni.insets = new Insets(5, 0, 5, 5);
		gbc_textField_dni.gridx = 1;
		gbc_textField_dni.gridy = 0;
		leftPanel.add(textField_dni, gbc_textField_dni);
		textField_dni.setColumns(10);

		JLabel lbl = new JLabel("-");
		lbl.setForeground(Color.BLACK);
		lbl.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.EAST;
		gbc_lbl.insets = new Insets(5, 5, 5, 5);
		gbc_lbl.gridx = 3;
		gbc_lbl.gridy = 0;
		leftPanel.add(lbl, gbc_lbl);

		textField_letter = new JTextField();
		textField_letter.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < 'A' || car > 'Z') && (car < 'a' || car > 'z'))
				{
					e.consume();
				}
				if (textField_letter.getText().length() == 1)
				{
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car >= 'a' && car <= 'z'))
				{
					String mayus = textField_letter.getText().trim()
					.toUpperCase();
					textField_letter.setText(mayus);
				}
			}
		});
		textField_letter.setHorizontalAlignment(SwingConstants.CENTER);
		textField_letter.setForeground(Color.BLACK);
		textField_letter.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_letter = new GridBagConstraints();
		gbc_textField_letter.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_letter.insets = new Insets(5, 0, 5, 10);
		gbc_textField_letter.gridx = 4;
		gbc_textField_letter.gridy = 0;
		leftPanel.add(textField_letter, gbc_textField_letter);
		textField_letter.setColumns(2);

		JLabel lblName = new JLabel("Nombre");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 10, 0, 20);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		leftPanel.add(lblName, gbc_lblName);

		textField_name = new JTextField();
		textField_name.setForeground(Color.BLACK);
		textField_name.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_name.setColumns(10);
		GridBagConstraints gbc_textField_name = new GridBagConstraints();
		gbc_textField_name.gridwidth = 4;
		gbc_textField_name.insets = new Insets(0, 0, 0, 10);
		gbc_textField_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_name.gridx = 1;
		gbc_textField_name.gridy = 1;
		leftPanel.add(textField_name, gbc_textField_name);

		JLabel lblLastName = new JLabel("Apellidos");
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 10, 0, 20);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		leftPanel.add(lblLastName, gbc_lblLastName);

		textField_lastnames = new JTextField();
		textField_lastnames.setForeground(Color.BLACK);
		textField_lastnames.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_lastnames.setColumns(10);
		GridBagConstraints gbc_textField_lastnames = new GridBagConstraints();
		gbc_textField_lastnames.gridwidth = 4;
		gbc_textField_lastnames.insets = new Insets(0, 0, 0, 10);
		gbc_textField_lastnames.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_lastnames.gridx = 1;
		gbc_textField_lastnames.gridy = 2;
		leftPanel.add(textField_lastnames, gbc_textField_lastnames);

		JLabel lblBirthDate = new JLabel("Fecha de nacimiento");
		lblBirthDate.setForeground(Color.BLACK);
		lblBirthDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblBirthDate.anchor = GridBagConstraints.WEST;
		gbc_lblBirthDate.insets = new Insets(0, 10, 0, 20);
		gbc_lblBirthDate.gridx = 0;
		gbc_lblBirthDate.gridy = 3;
		leftPanel.add(lblBirthDate, gbc_lblBirthDate);

		lblSelectedDate = new JLabel("");
		lblSelectedDate.setForeground(Color.BLACK);
		lblSelectedDate.setFont(new Font("Calibri", Font.ITALIC, 16));
		GridBagConstraints gbc_lblSelectedDate = new GridBagConstraints();
		gbc_lblSelectedDate.anchor = GridBagConstraints.WEST;
		gbc_lblSelectedDate.insets = new Insets(0, 10, 0, 5);
		gbc_lblSelectedDate.gridx = 2;
		gbc_lblSelectedDate.gridy = 3;
		leftPanel.add(lblSelectedDate, gbc_lblSelectedDate);

		btnCalendar = new JButton(new ImageIcon("img/calendar-icon.png"));
		btnCalendar.addActionListener(this);
		GridBagConstraints gbc_btnCalendar = new GridBagConstraints();
		gbc_btnCalendar.anchor = GridBagConstraints.WEST;
		gbc_btnCalendar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCalendar.gridx = 1;
		gbc_btnCalendar.gridy = 3;
		leftPanel.add(btnCalendar, gbc_btnCalendar);

		JLabel lblAddress = new JLabel("Dirección");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 10, 0, 20);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 4;
		leftPanel.add(lblAddress, gbc_lblAddress);

		textField_address = new JTextField();
		textField_address.setForeground(Color.BLACK);
		textField_address.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_address.setColumns(10);
		GridBagConstraints gbc_textField_address = new GridBagConstraints();
		gbc_textField_address.gridwidth = 4;
		gbc_textField_address.insets = new Insets(0, 0, 0, 10);
		gbc_textField_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_address.gridx = 1;
		gbc_textField_address.gridy = 4;
		leftPanel.add(textField_address, gbc_textField_address);

		JLabel lblTelephone = new JLabel("Teléfono");
		lblTelephone.setForeground(Color.BLACK);
		lblTelephone.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelephone = new GridBagConstraints();
		gbc_lblTelephone.anchor = GridBagConstraints.WEST;
		gbc_lblTelephone.insets = new Insets(0, 10, 0, 20);
		gbc_lblTelephone.gridx = 0;
		gbc_lblTelephone.gridy = 5;
		leftPanel.add(lblTelephone, gbc_lblTelephone);

		textField_telephone = new JTextField();
		textField_telephone.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
				if (textField_telephone.getText().length() == 9)
				{
					e.consume();
				}
			}
		});
		textField_telephone.setForeground(Color.BLACK);
		textField_telephone.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_telephone.setColumns(10);
		GridBagConstraints gbc_textField_telephone = new GridBagConstraints();
		gbc_textField_telephone.gridwidth = 4;
		gbc_textField_telephone.insets = new Insets(0, 0, 0, 10);
		gbc_textField_telephone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_telephone.gridx = 1;
		gbc_textField_telephone.gridy = 5;
		leftPanel.add(textField_telephone, gbc_textField_telephone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 10, 0, 20);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 6;
		leftPanel.add(lblEmail, gbc_lblEmail);

		textField_email = new JTextField();
		textField_email.setForeground(Color.BLACK);
		textField_email.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_email.setColumns(10);
		GridBagConstraints gbc_textField_email = new GridBagConstraints();
		gbc_textField_email.gridwidth = 4;
		gbc_textField_email.insets = new Insets(0, 0, 0, 10);
		gbc_textField_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_email.gridx = 1;
		gbc_textField_email.gridy = 6;
		leftPanel.add(textField_email, gbc_textField_email);

		JLabel lblIp = new JLabel("Dirección Ip");
		lblIp.setForeground(Color.BLACK);
		lblIp.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.WEST;
		gbc_lblIp.insets = new Insets(0, 10, 0, 20);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 7;
		leftPanel.add(lblIp, gbc_lblIp);

		ip_panel = new IPPanel();
		GridBagConstraints gbc_ipPanel = new GridBagConstraints();
		gbc_ipPanel.anchor = GridBagConstraints.WEST;
		gbc_ipPanel.gridwidth = 4;
		gbc_ipPanel.gridx = 1;
		gbc_ipPanel.gridy = 7;
		leftPanel.add(ip_panel, gbc_ipPanel);

		JLabel lblPort = new JLabel("Puerto");
		lblPort.setForeground(Color.BLACK);
		lblPort.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.WEST;
		gbc_lblPort.insets = new Insets(0, 10, 5, 20);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 8;
		leftPanel.add(lblPort, gbc_lblPort);

		textField_port = new JTextField();
		textField_port.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
				if (textField_port.getText().length() > 0
				&& Integer.parseInt(textField_port.getText().trim()) > 65535)
				{
					textField_port.setText("65535");
				}
			}
		});
		textField_port.setForeground(Color.BLACK);
		textField_port.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_port.setColumns(15);
		GridBagConstraints gbc_textField_port = new GridBagConstraints();
		gbc_textField_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_port.gridwidth = 4;
		gbc_textField_port.insets = new Insets(0, 0, 5, 10);
		gbc_textField_port.gridx = 1;
		gbc_textField_port.gridy = 8;
		leftPanel.add(textField_port, gbc_textField_port);

		JPanel btnsPanel = new JPanel();
		GridBagConstraints gbc_btnsPanel = new GridBagConstraints();
		gbc_btnsPanel.gridwidth = 2;
		gbc_btnsPanel.insets = new Insets(0, 10, 10, 10);
		gbc_btnsPanel.fill = GridBagConstraints.BOTH;
		gbc_btnsPanel.gridx = 0;
		gbc_btnsPanel.gridy = 1;
		add(btnsPanel, gbc_btnsPanel);
		GridBagLayout gbl_btnsPanel = new GridBagLayout();
		gbl_btnsPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gbl_btnsPanel.rowHeights = new int[] {0, 0};
		gbl_btnsPanel.columnWeights = new double[] {0.0, 1.0, 0.0, 1.0, 0.0,
		Double.MIN_VALUE};
		gbl_btnsPanel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		btnsPanel.setLayout(gbl_btnsPanel);

		btnReturn = new JButton("Volver");
		btnReturn.addActionListener(this);
		btnReturn.setForeground(Color.BLACK);
		btnReturn.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		gbc_btnReturn.insets = new Insets(0, 0, 0, 5);
		gbc_btnReturn.gridx = 0;
		gbc_btnReturn.gridy = 0;
		btnsPanel.add(btnReturn, gbc_btnReturn);

		btnCleanAll = new JButton("Borrar todo");
		btnCleanAll.addActionListener(this);
		btnCleanAll.setForeground(Color.BLACK);
		btnCleanAll.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCleanAll = new GridBagConstraints();
		gbc_btnCleanAll.insets = new Insets(0, 0, 0, 5);
		gbc_btnCleanAll.gridx = 2;
		gbc_btnCleanAll.gridy = 0;
		btnsPanel.add(btnCleanAll, gbc_btnCleanAll);

		btnSave = new JButton("Guardar");
		btnSave.addActionListener(this);
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 4;
		gbc_btnSave.gridy = 0;
		btnsPanel.add(btnSave, gbc_btnSave);

		calendar = new JCalendar();

		if (patient != null)
		{
			textField_dni.setText(Integer.toString(patient.getDni()));
			textField_letter.setText(patient.getDniLetter() + "");
			textField_name.setText(patient.getName());
			textField_lastnames.setText(patient.getLastName());
			lblSelectedDate.setText(DateFormat.getDateInstance(DateFormat.LONG)
			.format(patient.getBirthdate()));
			calendar.setDate(patient.getBirthdate());

		}
	}

	/**
	 * @return Patient editor dni textfield
	 */
	public JTextField getTextField_dni()
	{
		return textField_dni;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (btnCalendar == e.getSource())
		{
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

			String[] options = {Lang.getLine("accept_option"),
			Lang.getLine("cancel_option")};

			JOptionPane pane = new JOptionPane(calendar,
			JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
			options, options[1]);

			final JDialog dialog = pane.createDialog("Selecciona la fecha");
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
		else if (btnCleanAll == e.getSource())
		{
			textField_dni.setText("");
			textField_letter.setText("");
			textField_name.setText("");
			textField_lastnames.setText("");
			textField_email.setText("");
			textField_telephone.setText("");
			textField_port.setText("");
			textField_address.setText("");
			lblSelectedDate.setText("");
			calendar.setDate(new Date());
		}
		else if (btnReturn == e.getSource())
		{
			Window.getInstance().setContentPane(new Start());
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
		else if (btnSave == e.getSource())
		{
			if (patient == null)
			{
				// New patient
				int dni = Integer.parseInt(textField_dni.getText());
				if (DataBase.getInstance().count("PATIENT", "dni=" + dni) == 0)
				{
					if (textField_letter.getText().equals(
					Patient.getDniLetter(dni)))
					{
						String update = "INSERT INTO PATIENT VALUES (" + dni
						+ ", '" + ip_panel.getIpAddress() + "' , "
						+ Integer.parseInt(textField_port.getText()) + ", '"
						+ textField_name.getText().trim() + "', '"
						+ textField_lastnames.getText().trim() + "', "
						+ calendar.getDate().getTime() + ", '"
						+ textField_address.getText().trim() + "', "
						+ Integer.parseInt(textField_telephone.getText())
						+ ", '" + textField_email.getText().trim() + "')";
						DataBase.getInstance().update(update);
						JOptionPane.showMessageDialog(Window.getInstance(),
						"El paciente: " + textField_name.getText().trim() + " "
						+ textField_lastnames.getText().trim()
						+ " se ha añadido correctamente.");
					}
					else
					{
						// El dni es incorrecto.
					}

				}
				else
				{
					// Existe el paciente con dni:...
				}

			}
		}

	}
}
