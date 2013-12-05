package display.components;

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
import display.SensorPanel;
import display.Start;
import entities.Patient;
import entities.Sensor;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Menu extends JMenuBar implements ActionListener, Loadable {

	private static final long	serialVersionUID	= - 2674054941368737779L;

	private Color				color;
	private Vector<Sensor>		vSensors;
	private JMenu				user, language, sensors, devices;
	private JMenuItem			print, logout, camera, gps;
	private JMenuItem[]			langItems, sensorsItems;

	/**
	 * Create the menu.
	 * 
	 * @param color The background color
	 */
	public Menu(Color color)
	{
		super();
		this.color = color;

		// Patient.getCurrent().addLoadable(this);
		Patient.getCurrent().getSensors(this);

		this.langItems = new JMenuItem[Lang.getAvailableLocales().size()];
		this.sensorsItems = new JMenuItem[vSensors.size()];

		user = new JMenu("Admin");
		user.setFont(new Font("Calibri", Font.PLAIN, 18));
		user.setForeground(Color.GREEN);
		user.setMargin(new Insets(5, 5, 5, 10));

		language = new JMenu("Idioma");
		language.addActionListener(this);
		language.setMargin(new Insets(5, 5, 5, 5));
		user.add(language);

		for (int i = 0; i < Lang.getAvailableLocales().size(); i++)
		{
			JMenuItem langItem = new JMenuItem(Lang.getCombableLocales().get(i));
			langItem.addActionListener(this);
			langItem.setMargin(new Insets(5, 5, 5, 5));
			langItems[i] = langItem;
			language.add(langItem);
		}

		print = new JMenuItem("Imprimir");
		print.addActionListener(this);
		print.setMargin(new Insets(5, 5, 5, 5));
		user.add(print);

		logout = new JMenuItem("Cerrar sesión");
		logout.addActionListener(this);
		logout.setMargin(new Insets(5, 5, 5, 5));
		user.add(logout);

		sensors = new JMenu("Sensores");
		sensors.setFont(new Font("Calibri", Font.PLAIN, 18));
		sensors.setForeground(Color.GREEN);
		sensors.setMargin(new Insets(5, 5, 5, 10));

		devices = new JMenu("Dispositivos");
		devices.setFont(new Font("Calibri", Font.PLAIN, 18));
		devices.setForeground(Color.GREEN);
		devices.setMargin(new Insets(5, 5, 5, 10));

		gps = new JMenuItem("GPS");
		gps.addActionListener(this);
		gps.setMargin(new Insets(5, 5, 5, 5));
		devices.add(gps);

		camera = new JMenuItem("Cámara");
		camera.addActionListener(this);
		camera.setMargin(new Insets(5, 5, 5, 5));
		devices.add(camera);

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
			start.updateUI();
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
		else if (camera == e.getSource())
		{
			Patient.getFoto();
			// Window.getInstance().setContentPane(new CameraPanel());
			// ((JPanel) Window.getInstance().getContentPane()).updateUI();
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

	/**
	 * Notifies the new sensors
	 * 
	 * @param vSensors - Sensor list
	 */
	public void notify(Vector<Sensor> vSensors)
	{
		this.vSensors = vSensors;
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

	@Override
	public void update(Object object)
	{
		//@formatter:off
		if (object instanceof Vector<?> && ((Vector<?>) object).elementAt(0) instanceof Sensor)
		{
			System.out.println("Actualizada lista sensores");
			
		}
		//@formatter:on
	}
}