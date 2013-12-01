package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import utils.Lang;

/**
 * @author Jordan Aranda Tejada
 */
public class Menu extends JMenuBar implements ActionListener {

	private static final long	serialVersionUID	= - 2674054941368737779L;

	private Color				color;
	private JMenu				user, language, sensors, devices, window;
	private JMenuItem			print, logout, camera, gps;
	private JMenuItem[]			langItems;

	/**
	 * Create the menu.
	 * 
	 * @param color The background color
	 */
	public Menu(Color color)
	{
		super();
		this.color = color;
		this.langItems = new JMenuItem[Lang.getAvailableLocales().size()];

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

		window = new JMenu("Ventana");
		window.setFont(new Font("Calibri", Font.PLAIN, 18));
		window.setForeground(Color.GREEN);
		window.setMargin(new Insets(5, 5, 5, 10));

		add(user);
		add(sensors);
		add(devices);
		add(window);
	}

	@Override
	public void actionPerformed(final ActionEvent e)
	{

	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 1);

	}
}