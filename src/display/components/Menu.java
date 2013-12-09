package display.components;

import interfaces.Loadable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utils.Lang;
import utils.Patient;
import utils.Utils;
import display.CameraPanel;
import display.GPSPanel;
import display.SensorPanel;
import display.Start;
import entities.Sensor;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Menu extends JMenuBar implements ActionListener, Loadable {

	private static final long	serialVersionUID	= - 2674054941368737779L;

	private Color				color;
	private Vector<Sensor>		vSensors;
	private JMenu				user, sensors, devices, camera;
	private JMenuItem			logout, gps, takePhoto;
	private JMenuItem[]			sensorsItems;

	/**
	 * Create the menu.
	 * 
	 * @param color The background color
	 */
	public Menu(Color color)
	{
		super();
		this.color = color;

		Patient.getCurrent().getSensors(this);

		user = new JMenu(User.getCurrent().getUsername());
		user.setFont(new Font("Calibri", Font.PLAIN, 18));
		user.setForeground(Color.GREEN);
		user.setMargin(new Insets(10, 10, 10, 10));

		logout = new JMenuItem(Lang.getLine("menu_logout"));
		logout.addActionListener(this);
		logout.setMargin(new Insets(10, 10, 10, 10));
		user.add(logout);

		sensors = new JMenu(Lang.getLine("menu_sensors"));
		sensors.setFont(new Font("Calibri", Font.PLAIN, 18));
		sensors.setForeground(Color.GREEN);
		sensors.setMargin(new Insets(10, 10, 10, 10));

		devices = new JMenu(Lang.getLine("menu_devices"));
		devices.setFont(new Font("Calibri", Font.PLAIN, 18));
		devices.setForeground(Color.GREEN);
		devices.setMargin(new Insets(10, 10, 10, 10));

		gps = new JMenuItem(Lang.getLine("menu_gps"));
		gps.addActionListener(this);
		gps.setMargin(new Insets(10, 10, 10, 10));
		devices.add(gps);

		camera = new JMenu(Lang.getLine("menu_camera"));
		camera.setMargin(new Insets(10, 10, 10, 10));
		devices.add(camera);

		takePhoto = new JMenuItem(Lang.getLine("menu_take_photo"));
		takePhoto.addActionListener(this);
		takePhoto.setMargin(new Insets(10, 10, 10, 10));
		camera.add(takePhoto);

		add(user);
		add(sensors);
		add(devices);
	}

	@Override
	public void actionPerformed(final ActionEvent e)
	{
		for (int i = 0; i < sensorsItems.length; i++)
		{
			if (sensorsItems[i] == e.getSource())
			{
				Window.getInstance().setContentPane(
				new SensorPanel(vSensors.get(i), SensorPanel.tableMode));
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		}
		if (logout == e.getSource())
		{
			User.getCurrent().logout();
			Window.getInstance().getJMenuBar().setVisible(false);
			Start start = new Start();
			Window.getInstance().setContentPane(start);
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
		else if (takePhoto == e.getSource())
		{
			Patient.getCurrent().getFoto(this);
		}
		else if (gps == e.getSource())
		{
			Window.getInstance().setContentPane(new GPSPanel());
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 1);
	}

	@SuppressWarnings ("unchecked")
	@Override
	public void update(Object object)
	{
		if (object instanceof Vector<?>
		&& ((Vector<?>) object).elementAt(0) instanceof Sensor)
		{
			vSensors = (Vector<Sensor>) object;
			this.sensorsItems = new JMenuItem[vSensors.size()];
			for (int i = 0; i < vSensors.size(); i++)
			{
				JMenuItem sensorItem = new JMenuItem(vSensors.get(i)
				.getDescription());
				sensorItem.addActionListener(this);
				sensorItem.setMargin(new Insets(5, 5, 5, 5));
				sensorsItems[i] = sensorItem;
				sensors.add(sensorItem);
			}
		}
		else if (object instanceof ByteArrayOutputStream)
		{
			ByteArrayOutputStream imgStream = (ByteArrayOutputStream) object;
			final CameraPanel panel = new CameraPanel();
			panel.getLblImage().setIcon(new ImageIcon(imgStream.toByteArray()));

			final String[] options = {Lang.getLine("dialog_camera_exit"),
			Lang.getLine("dialog_camera_save")};
			final JOptionPane pane = new JOptionPane(panel,
			JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
			options, options[1]);
			final JDialog dialog = pane.createDialog(Lang
			.getLine("dialog_camera_title"));
			dialog.setLocationRelativeTo(Window.getInstance());
			dialog.setVisible(true);

			if (pane.getValue() == options[1])
			{
				Utils.saveByteArrayFile(imgStream, "PNG", "png");
			}

			dialog.dispose();

			String[] options2 = {Lang.getLine("yes_option"),
			Lang.getLine("no_option")};

			int selection = JOptionPane.showOptionDialog(Window.getInstance(),
			Lang.getLine("obtain_coordinates_question"),
			Lang.getLine("obtain_coordinates_title"),
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			options2, options2[0]);

			if (selection == 0)
			{
				Patient.getCurrent().getCoordinates(this);
			}
		}
		else if (object instanceof String)
		{
			String coordinates = (String) object;
			coordinates = coordinates.substring(7, coordinates.length());
			String[] coord = coordinates.split(";");

			JOptionPane.showMessageDialog(
			Window.getInstance(),
			Lang.getLine("longitude") + ": " + coord[0] + "\n"
			+ Lang.getLine("latitude") + ":" + coord[1],
			Lang.getLine("coordinates_title"), JOptionPane.INFORMATION_MESSAGE);
		}
	}
}