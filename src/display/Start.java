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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableRowSorter;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import utils.Lang;
import utils.Properties;

import components.IButton;
import components.ILabel;
import components.TableModel;
import components.Window;

import database.DataBase;
import entities.Patient;

/**
 * @author Jordan Aranda Tejada
 */
public class Start extends JPanel implements ActionListener, MouseListener {

	private static final long	serialVersionUID	= 4557875777913232705L;

	private JTable				table;
	private final TableModel	tableModel;

	private IButton				btnPreferences;
	private IButton				btnAdd;

	/**
	 * Create the panel.
	 */
	public Start()
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
		gbl_menu_panel.columnWidths = new int[] {70, 70, 70, 0};
		gbl_menu_panel.rowHeights = new int[] {27, 0};
		gbl_menu_panel.columnWeights = new double[] {0.0, 1.0, 0.0,
		Double.MIN_VALUE};
		gbl_menu_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		menu_panel.setLayout(gbl_menu_panel);

		btnPreferences = new IButton();
		btnPreferences.setFocusPainted(false);
		btnPreferences.addActionListener(this);
		btnPreferences.setForeground(Color.BLACK);
		btnPreferences.setFont(new Font("Calibri", Font.PLAIN, 18));
		Lang.setLine(btnPreferences, "preferences");
		GridBagConstraints gbc_btnPreferences = new GridBagConstraints();
		gbc_btnPreferences.fill = GridBagConstraints.BOTH;
		gbc_btnPreferences.insets = new Insets(0, 0, 0, 5);
		gbc_btnPreferences.gridx = 0;
		gbc_btnPreferences.gridy = 0;
		menu_panel.add(btnPreferences, gbc_btnPreferences);

		btnAdd = new IButton();
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(this);
		Lang.setLine(btnAdd, "btn_add_server");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.BOTH;
		gbc_btnInsert.gridx = 2;
		gbc_btnInsert.gridy = 0;
		menu_panel.add(btnAdd, gbc_btnInsert);

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
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);

		table.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 18));

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
				p = new Patient(rs.getInt("dni"), rs.getString("ip_address"),
				rs.getInt("port"), rs.getString("name"),
				rs.getString("lastname"), new Date(
				rs.getInt("birthdate") * 1000), rs.getString("address"),
				rs.getInt("telephone"), rs.getString("email"));

				content[i][0] = Integer.toString(p.getDni()) + "-"
				+ Patient.getDniLetter(p);
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
		if (e.getSource() == btnPreferences)
		{
			final Preferences p = new Preferences();

			final String[] options = {Lang.getLine("accept_option"),
			Lang.getLine("cancel_option")};
			JOptionPane pane = new JOptionPane(p, JOptionPane.PLAIN_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION, null, options, options[1]);
			final JDialog dialog = pane.createDialog(Lang
			.getLine("preferences"));
			dialog.setLocationRelativeTo(Window.getInstance());
			dialog.setResizable(false);
			dialog.pack();
			dialog.setIconImage(new ImageIcon("img/sett-icon.png").getImage());
			dialog.setVisible(true);

			if (pane.getValue() == options[0])
			{
				Properties.setLocale(Lang.getAvailableLocales().get(
				p.getLocaleIndex()));
				Lang
				.setLang(Lang.getAvailableLocales().get(p.getLocaleIndex()));

				Properties.setLookAndFeelClass(p.getLookAndFeel());
				if (Properties.getLookAndFeel().substring(0, 3).equals("org"))
				{
					SubstanceLookAndFeel.setSkin(Properties.getLookAndFeel());
				}
				else
				{
					try
					{
						UIManager.setLookAndFeel(Properties.getLookAndFeel());
					}
					catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1)
					{
						e1.printStackTrace();
					}
				}
				SwingUtilities.updateComponentTreeUI(Window.getInstance());
			}
			dialog.dispose();
		}
		else if (e.getSource() == btnAdd)
		{
			// Button ADD
			PatientEditorPanel pep = new PatientEditorPanel(null);
			Window.getInstance().setContentPane(pep);
			pep.getTextField_dni().requestFocus();
			((JPanel) Window.getInstance().getContentPane()).updateUI();
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
				p = new Patient(rs.getInt("dni"), rs.getString("ip_address"),
				rs.getInt("port"), rs.getString("name"),
				rs.getString("lastname"), new Date(
				rs.getInt("birthdate") * 1000), rs.getString("address"),
				rs.getInt("telephone"), rs.getString("email"));
			}
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		return p;
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