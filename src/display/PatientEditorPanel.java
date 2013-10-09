package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Jordan Aranda Tejada
 */
public class PatientEditorPanel extends JPanel {

	private static final long	serialVersionUID	= 8017107363850239824L;
	private JTextField			textField_dni;
	private JTextField			textField_name;
	private JTextField			textField_lastName;
	private JTextField			textField_address;
	private JTextField			textField_city;

	/**
	 * Create the panel.
	 */
	public PatientEditorPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 1.0, 1.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 1.0, 1.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[] {0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 0;
		panel.add(lblDni, gbc_lblDni);

		textField_dni = new JTextField();
		textField_dni.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_dni = new GridBagConstraints();
		gbc_textField_dni.gridwidth = 2;
		gbc_textField_dni.insets = new Insets(0, 0, 5, 0);
		gbc_textField_dni.fill = GridBagConstraints.BOTH;
		gbc_textField_dni.gridx = 1;
		gbc_textField_dni.gridy = 0;
		panel.add(textField_dni, gbc_textField_dni);
		textField_dni.setColumns(10);

		JLabel lblName = new JLabel("Nombre");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(5, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);

		textField_name = new JTextField();
		textField_name.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_name.setColumns(10);
		GridBagConstraints gbc_textField_name = new GridBagConstraints();
		gbc_textField_name.gridwidth = 2;
		gbc_textField_name.insets = new Insets(5, 0, 5, 0);
		gbc_textField_name.fill = GridBagConstraints.BOTH;
		gbc_textField_name.gridx = 1;
		gbc_textField_name.gridy = 1;
		panel.add(textField_name, gbc_textField_name);

		JLabel lblLastName = new JLabel("Apellidos");
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLastName.insets = new Insets(5, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		panel.add(lblLastName, gbc_lblLastName);

		textField_lastName = new JTextField();
		textField_lastName.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_lastName.setColumns(10);
		GridBagConstraints gbc_textField_lastName = new GridBagConstraints();
		gbc_textField_lastName.gridwidth = 2;
		gbc_textField_lastName.insets = new Insets(5, 0, 5, 0);
		gbc_textField_lastName.fill = GridBagConstraints.BOTH;
		gbc_textField_lastName.gridx = 1;
		gbc_textField_lastName.gridy = 2;
		panel.add(textField_lastName, gbc_textField_lastName);

		JLabel lblBirthDate = new JLabel("Fecha nacimiento");
		lblBirthDate.setForeground(Color.BLACK);
		lblBirthDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.fill = GridBagConstraints.BOTH;
		gbc_lblBirthDate.gridwidth = 2;
		gbc_lblBirthDate.insets = new Insets(5, 0, 5, 5);
		gbc_lblBirthDate.gridx = 0;
		gbc_lblBirthDate.gridy = 3;
		panel.add(lblBirthDate, gbc_lblBirthDate);

		JLabel lblAddress = new JLabel("Dirección");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.EAST;
		gbc_lblAddress.insets = new Insets(5, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 4;
		panel.add(lblAddress, gbc_lblAddress);

		textField_address = new JTextField();
		textField_address.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_address.setColumns(10);
		GridBagConstraints gbc_textField_address = new GridBagConstraints();
		gbc_textField_address.gridwidth = 2;
		gbc_textField_address.insets = new Insets(5, 0, 5, 0);
		gbc_textField_address.fill = GridBagConstraints.BOTH;
		gbc_textField_address.gridx = 1;
		gbc_textField_address.gridy = 4;
		panel.add(textField_address, gbc_textField_address);

		JLabel lblCity = new JLabel("Ciudad");
		lblCity.setForeground(Color.BLACK);
		lblCity.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCity.insets = new Insets(5, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 5;
		panel.add(lblCity, gbc_lblCity);

		textField_city = new JTextField();
		textField_city.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_city.setColumns(10);
		GridBagConstraints gbc_textField_city = new GridBagConstraints();
		gbc_textField_city.insets = new Insets(5, 0, 5, 0);
		gbc_textField_city.gridwidth = 2;
		gbc_textField_city.fill = GridBagConstraints.BOTH;
		gbc_textField_city.gridx = 1;
		gbc_textField_city.gridy = 5;
		panel.add(textField_city, gbc_textField_city);
	}
}
