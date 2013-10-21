package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import utilities.StringUtils;
import utils.Lang;

import components.IButton;
import components.ILabel;
import components.TableModel;
import components.Window;

import database.DataBase;
import entities.Patient;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class MainPanel extends JPanel implements ActionListener, MouseListener {

	private static final long	serialVersionUID	= 4557875777913232705L;

	private JTable				table;
	private final TableModel	tableModel;

	private IButton				btnOptions;
	private IButton				btnAdd;
	private IButton				btnExit;

	/**
	 * Create the panel.
	 */
	public MainPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel menu_panel = new JPanel();
		GridBagConstraints gbc_menu_panel = new GridBagConstraints();
		gbc_menu_panel.insets = new Insets(0, 20, 10, 20);
		gbc_menu_panel.fill = GridBagConstraints.BOTH;
		gbc_menu_panel.gridx = 0;
		gbc_menu_panel.gridy = 1;
		add(menu_panel, gbc_menu_panel);
		GridBagLayout gbl_menu_panel = new GridBagLayout();
		gbl_menu_panel.columnWidths = new int[] {70, 70, 0, 0};
		gbl_menu_panel.rowHeights = new int[] {27, 0};
		gbl_menu_panel.columnWeights = new double[] {0.0, 1.0, 0.0,
		Double.MIN_VALUE};
		gbl_menu_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		menu_panel.setLayout(gbl_menu_panel);

		btnOptions = new IButton("Opciones");
		btnOptions.setFocusPainted(false);
		btnOptions.addActionListener(this);
		btnOptions.setForeground(Color.BLACK);
		btnOptions.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnOptions = new GridBagConstraints();
		gbc_btnOptions.fill = GridBagConstraints.BOTH;
		gbc_btnOptions.insets = new Insets(0, 0, 0, 5);
		gbc_btnOptions.gridx = 0;
		gbc_btnOptions.gridy = 0;
		menu_panel.add(btnOptions, gbc_btnOptions);

		btnAdd = new IButton();
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(this);
		Lang.setLine(btnAdd, "btn_add_server");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsert.fill = GridBagConstraints.VERTICAL;
		gbc_btnInsert.gridx = 1;
		gbc_btnInsert.gridy = 0;
		menu_panel.add(btnAdd, gbc_btnInsert);

		btnExit = new IButton();
		btnExit.addActionListener(this);
		btnExit.setText("Salir");
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnExit.setFocusPainted(false);
		GridBagConstraints gbc_btnExist = new GridBagConstraints();
		gbc_btnExist.fill = GridBagConstraints.BOTH;
		gbc_btnExist.gridx = 2;
		gbc_btnExist.gridy = 0;
		menu_panel.add(btnExit, gbc_btnExist);

		final String[] header = {"DNI", "Nombre", "Apellidos", "Telefono",
		"Email"};
		final String[][] content = new String[DataBase.getInstance().count(
		"PATIENT", null)][header.length];
		loadContent(content);

		tableModel = new TableModel();
		tableModel.setDataVector(content, header);

		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setDragEnabled(false);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);

		table.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 16));

		table.getColumnModel().getColumn(0).setMinWidth(120);
		table.getColumnModel().getColumn(0).setMaxWidth(120);

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(
		tableModel);
		table.setRowSorter(rowSorter);

		table.addMouseListener(this);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(20, 20, 10, 20);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;

		if (DataBase.getInstance().count("PATIENT", null) != 0)
		{
			add(new JScrollPane(table), gbc_scrollPane);
		}
		else
		{
			ILabel lblNoPatients = new ILabel();
			Lang.setLine(lblNoPatients, "lbl_no_patients");
			lblNoPatients.setFont(new Font("Calibri", Font.ITALIC, 16));
			add(lblNoPatients);
		}
	}

	private void loadContent(final String[][] content)
	{
		ResultSet rs = DataBase.getInstance().consult("SELECT * FROM PATIENT");
		int i = 0;
		try
		{
			while (rs.next())
			{
				Patient p;
				p = new Patient(rs.getInt("id"), rs.getInt("dni"),
				rs.getString("ip_address"), rs.getInt("port"),
				rs.getString("name"), rs.getString("lastname"), new Date(
				rs.getLong("birthdate")), rs.getString("address"),
				rs.getInt("telephone"), rs.getString("email"), User
				.getCurrent().getId());

				content[i][0] = Integer.toString(p.getDni()) + "-"
				+ p.getDniLetter();
				content[i][1] = p.getName();
				content[i][2] = p.getLastName();
				content[i][3] = Integer.toString(p.getTelephone());
				content[i][4] = p.getEmail();
				i++;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnOptions)
		{
			boolean correct = false;
			String value = "";
			final String[] options = {Lang.getLine("accept_option"),
			Lang.getLine("cancel_option")};

			do
			{
				InputPasswordPanel ipd = new InputPasswordPanel("Contraseña");

				JOptionPane pane = new JOptionPane(ipd,
				JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
				options, options[1]);
				final JDialog dialog = pane.createDialog("Opciones");
				dialog.setLocationRelativeTo(Window.getInstance());
				dialog.setResizable(false);
				dialog.pack();
				dialog.setIconImage(new ImageIcon("img/sett-icon.png")
				.getImage());
				dialog.setVisible(true);

				value = (String) pane.getValue();

				if (value == options[0])
				{
					if (StringUtils.sha1(ipd.getPassword()).equals(
					User.getCurrent().getPassword()))
					{
						correct = true;
					}
					else
					{
						JOptionPane.showMessageDialog(Window.getInstance(),
						"Contraseña incorrecta", "Error",
						JOptionPane.ERROR_MESSAGE, new ImageIcon(
						"img/error-icon.png"));
					}
				}
				dialog.dispose();
			}
			while ( ! correct && value == options[0]);

			if (correct)
			{
				final UserOptions p = new UserOptions();

				JOptionPane pane = new JOptionPane(p,
				JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
				options, options[1]);
				final JDialog dialog = pane.createDialog("Opciones");
				dialog.setLocationRelativeTo(Window.getInstance());
				dialog.setResizable(false);
				dialog.pack();
				dialog.setIconImage(new ImageIcon("img/sett-icon.png")
				.getImage());
				dialog.setVisible(true);

				if (pane.getValue() == options[0])
				{

				}
				dialog.dispose();
			}

		}
		else if (e.getSource() == btnAdd)
		{
			PatientEditorPanel pep = new PatientEditorPanel(null);
			Window.getInstance().setContentPane(pep);
			pep.getTextField_dni().requestFocus();
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
		else if (e.getSource() == btnExit)
		{
			Window.getInstance().setContentPane(new Login());
			((JPanel) Window.getInstance().getContentPane()).updateUI();
			User.getCurrent().setRemember(false);
			User.getCurrent().save();
		}
	}

	private Patient getTableSelectedPatient()
	{
		Patient p = null;
		String selectedPatient = ((String) table.getValueAt(
		table.getSelectedRow(), 0)).substring(0, 8);
		ResultSet rs = DataBase.getInstance().consult(
		"SELECT * FROM PATIENT WHERE DNI=" + Integer.parseInt(selectedPatient));
		try
		{
			while (rs.next())
			{
				p = new Patient(rs.getInt("id"), rs.getInt("dni"),
				rs.getString("ip_address"), rs.getInt("port"),
				rs.getString("name"), rs.getString("lastname"), new Date(
				rs.getLong("birthdate")), rs.getString("address"),
				rs.getInt("telephone"), rs.getString("email"), User
				.getCurrent().getId());
			}
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		return p;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource() == table)
		{
			int r = table.rowAtPoint(e.getPoint());
			table.getSelectionModel().setSelectionInterval(r, r);
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				PatientTablePopup popup = new PatientTablePopup(
				getTableSelectedPatient());
				popup.show(table, e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}