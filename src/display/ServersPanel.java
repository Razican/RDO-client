package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utils.Lang;
import utils.Properties;

import components.IButton;
import components.TableModel;
import components.Window;

import entities.Server;

/**
 * @author Jordan Aranda Tejada
 */
public class ServersPanel extends JPanel {

	private static final long	serialVersionUID	= 4557875777913232705L;

	private JTable				table;
	private final TableModel	tableModel;

	/**
	 * Create the panel.
	 */
	public ServersPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(20, 20, 10, 20);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		final String[] header = {"IP", Lang.getLine("lbl_server_port"),
		Lang.getLine("lbl_user"), Lang.getLine("lbl_password")};
		final String[][] content = new String[Properties.getServers().size()][header.length];
		loadContent(content);

		tableModel = new TableModel();
		tableModel.setDataVector(content, header);

		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					Window.getInstance().setContentPane(
					new ServerEditorPanel(Properties.getServers().get(
					table.getSelectedRow())));
					((JPanel) Window.getInstance().getContentPane()).updateUI();
				}
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setDragEnabled(false);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		JPanel menu_panel = new JPanel();
		GridBagConstraints gbc_menu_panel = new GridBagConstraints();
		gbc_menu_panel.insets = new Insets(0, 20, 10, 20);
		gbc_menu_panel.fill = GridBagConstraints.BOTH;
		gbc_menu_panel.gridx = 0;
		gbc_menu_panel.gridy = 1;
		add(menu_panel, gbc_menu_panel);
		GridBagLayout gbl_menu_panel = new GridBagLayout();
		gbl_menu_panel.columnWidths = new int[] {70, 70, 70, 73, 70, 0};
		gbl_menu_panel.rowHeights = new int[] {27, 0};
		gbl_menu_panel.columnWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0,
		Double.MIN_VALUE};
		gbl_menu_panel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		menu_panel.setLayout(gbl_menu_panel);

		IButton btnInsert = new IButton();
		btnInsert.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Window.getInstance()
				.setContentPane(new ServerEditorPanel(null));
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		});
		Lang.setLine(btnInsert, "btn_add_server");
		btnInsert.setForeground(Color.BLACK);
		btnInsert.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnInsert.setFocusPainted(false);
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.BOTH;
		gbc_btnInsert.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 0;
		menu_panel.add(btnInsert, gbc_btnInsert);

		IButton btnModify = new IButton();
		btnModify.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selRow = table.getSelectedRow();
				System.out.println("Fila seleccionada: " + selRow);
				if (selRow > - 1)
				{
					Window.getInstance().setContentPane(
					new ServerEditorPanel(Properties.getServers().get(selRow)));
					((JPanel) Window.getInstance().getContentPane()).updateUI();

				}
				else
				{
					JOptionPane.showMessageDialog(Window.getInstance(),
					"Selecciona el servidor que desees modificar.", "Error",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon(
					"img/error-icon.png"));
				}
			}
		});
		Lang.setLine(btnModify, "btn_edit_server");
		btnModify.setForeground(Color.BLACK);
		btnModify.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnModify.setFocusPainted(false);
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.fill = GridBagConstraints.BOTH;
		gbc_btnModify.insets = new Insets(0, 0, 0, 5);
		gbc_btnModify.gridx = 1;
		gbc_btnModify.gridy = 0;
		menu_panel.add(btnModify, gbc_btnModify);

		IButton btnDelete = new IButton();
		btnDelete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selRow = table.getSelectedRow();
				System.out.println("Fila seleccionada: " + selRow);
				if (selRow > - 1)
				{
					Properties.removeServer(selRow);
					tableModel.removeRow(selRow);
					JOptionPane.showMessageDialog(Window.getInstance(),
					"El servidor ha sido eliminado correctamente.",
					"Servidor eliminado", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("img/ok-icon.png"));
				}
				else
				{
					JOptionPane.showMessageDialog(Window.getInstance(),
					"Selecciona el servidor que desees eliminar.", "Error",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon(
					"img/error-icon.png"));
				}
			}
		});
		Lang.setLine(btnDelete, "btn_remove_server");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnDelete.setFocusPainted(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		menu_panel.add(btnDelete, gbc_btnDelete);

		IButton btnReturn = new IButton();
		Lang.setLine(btnReturn, "btn_return");

		btnReturn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Window.getInstance().setContentPane(new Start());
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		});
		btnReturn.setForeground(Color.BLACK);
		btnReturn.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnReturn.setFocusPainted(false);
		GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		gbc_btnReturn.fill = GridBagConstraints.BOTH;
		gbc_btnReturn.gridx = 4;
		gbc_btnReturn.gridy = 0;
		menu_panel.add(btnReturn, gbc_btnReturn);
	}

	private void loadContent(final String[][] content)
	{
		for (int i = 0; i < Properties.getServers().size(); i++)
		{
			Server s = Properties.getServers().get(i);
			content[i][0] = s.getIP();
			content[i][1] = Integer.toString(s.getPort());
			content[i][2] = s.getUser();
			content[i][3] = s.getPassword();
		}
	}
}
