package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import components.IPanel;
import components.TableModel;
import components.Window;

import entities.Patient;
import entities.Sensor;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class SensorPanel extends IPanel implements ActionListener {

	private static final long	serialVersionUID	= - 3523156860414714504L;

	private Sensor				sensor;
	private TableModel			modelTable;
	private JTable				table;
	private JButton				btnGetValue;
	private JButton				btnPrint;
	private JButton				btnChangeMode;
	private int					mode;

	public static int			tableMode			= - 1;
	public static int			graphMode			= 1;

	/**
	 * Create the panel.
	 * 
	 * @param sensor The sensor
	 */
	public SensorPanel(Sensor sensor, int mode)
	{
		this.sensor = sensor;
		this.mode = mode;
		setOpaque(false);
		setBackgroundImage(new ImageIcon("img/background.jpg"));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 0.0, 0.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblDescription = new JLabel(sensor.getDescription());
		lblDescription.setForeground(Color.GREEN);
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 50));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescription.insets = new Insets(20, 20, 20, 10);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 0;
		add(lblDescription, gbc_lblDescription);

		btnGetValue = new JButton("Consultar");
		btnGetValue.addActionListener(this);
		btnGetValue.setBorderPainted(false);
		btnGetValue.setBackground(Color.BLACK);
		btnGetValue.setForeground(Color.GREEN);
		btnGetValue.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_btnGetValue = new GridBagConstraints();
		gbc_btnGetValue.anchor = GridBagConstraints.SOUTH;
		gbc_btnGetValue.insets = new Insets(0, 0, 20, 5);
		gbc_btnGetValue.gridx = 1;
		gbc_btnGetValue.gridy = 0;
		add(btnGetValue, gbc_btnGetValue);

		btnChangeMode = new JButton("Gráfica");
		btnChangeMode.addActionListener(this);
		btnChangeMode.setForeground(Color.GREEN);
		btnChangeMode.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChangeMode.setBorderPainted(false);
		btnChangeMode.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnGraph = new GridBagConstraints();
		gbc_btnGraph.anchor = GridBagConstraints.SOUTH;
		gbc_btnGraph.insets = new Insets(0, 0, 20, 5);
		gbc_btnGraph.gridx = 2;
		gbc_btnGraph.gridy = 0;
		add(btnChangeMode, gbc_btnGraph);
		if (this.mode == SensorPanel.graphMode)
		{
			btnChangeMode.setText("Tabla");
		}

		btnPrint = new JButton("Imprimir");
		btnPrint.addActionListener(this);
		btnPrint.setForeground(Color.GREEN);
		btnPrint.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnPrint.setBorderPainted(false);
		btnPrint.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.anchor = GridBagConstraints.SOUTH;
		gbc_btnPrint.insets = new Insets(0, 0, 20, 20);
		gbc_btnPrint.gridx = 3;
		gbc_btnPrint.gridy = 0;
		add(btnPrint, gbc_btnPrint);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 20, 20, 20);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		modelTable = new TableModel();
		loadTable();

		table = new JTable(modelTable);
		table.setRowSelectionAllowed(false);
		table.setRequestFocusEnabled(false);
		table.setFocusTraversalKeysEnabled(false);
		table.setFocusable(false);
		table.setEnabled(false);
		table.setGridColor(new Color(135, 206, 250));
		table.getTableHeader().setReorderingAllowed(false);
		table.setDragEnabled(false);
		table.setOpaque(false);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		table.setRowHeight(22);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		// table.getColumnModel().getColumn(0).setMinWidth(100);
		// table.getColumnModel().getColumn(0).setMaxWidth(100);
	}

	private void loadTable()
	{
		Vector<String> historic = Patient.getHistoric(sensor.getId());

		String[] header = {"Fecha", "Hora", "Latitud", "Longitud", "Valor"};
		String[][] content = new String[historic.size() - 1][5];

		for (int i = 1; i < historic.size(); i++)
		{
			String line = historic.get(i);
			String[] fields = line.split(";");
			// Fecha
			content[i - 1][0] = fields[0];
			// Hora
			content[i - 1][1] = fields[1];
			// Latitud
			content[i - 1][2] = fields[2];
			// Longitud
			content[i - 1][3] = fields[3];
			// Valor
			content[i - 1][4] = fields[4];
		}
		modelTable.setDataVector(content, header);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (btnGetValue == e.getSource())
		{
			String response = Patient.getSensorValue(sensor.getId());
			int code = User.getCurrent().getClient().getInputCode(response);
			if (code != 224)
			{
				JOptionPane.showMessageDialog(Window.getInstance(), response,
				"Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				String content = User.getCurrent().getClient()
				.getInputDescription(response);
				content = content.substring(3, content.length());

				String[] valArray = content.split(";");
				//@formatter:off
				String values = "Fecha: \t\t" + valArray[0] + "\n" +
								"Hora: \t\t" + valArray[1]+"\n" +
								"Latitud: \t\t" +valArray[2]+"\n" +
								"Longitud:  \t\t" +valArray[3]+"\n" +
								"Valor:  \t\t"+valArray[4];
				//@formatter:on

				Window.getInstance().setContentPane(
				new SensorPanel(Patient.getSensors().get(sensor.getId() - 1),
				mode));
				((JPanel) Window.getInstance().getContentPane()).updateUI();

				JOptionPane.showMessageDialog(Window.getInstance(), values,
				"Sensor " + sensor.getDescription(), JOptionPane.PLAIN_MESSAGE);
			}

		}
		else if (btnChangeMode == e.getSource())
		{
			mode *= - 1;
			if (mode == SensorPanel.graphMode)
			{
				Window.getInstance().setContentPane(
				new SensorPanel(Patient.getSensors().get(sensor.getId() - 1),
				SensorPanel.graphMode));
			}
			else if (mode == SensorPanel.tableMode)
			{
				Window.getInstance().setContentPane(
				new SensorPanel(Patient.getSensors().get(sensor.getId() - 1),
				SensorPanel.tableMode));
			}
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
		else if (btnPrint == e.getSource())
		{
			try
			{
				table.print();
			}
			catch (PrinterException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
