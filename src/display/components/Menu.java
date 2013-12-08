package display.components;

import interfaces.Loadable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import utils.Lang;
import utils.Patient;
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
		//@formatter:off
		for (int i = 0; i<sensorsItems.length; i++)
		{
			if (sensorsItems[i] == e.getSource())
			{
				Window.getInstance().setContentPane(new SensorPanel(vSensors.get(i), SensorPanel.tableMode));
				((JPanel) Window.getInstance().getContentPane()).updateUI();
			}
		}
		//@formatter:on
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
	}
}