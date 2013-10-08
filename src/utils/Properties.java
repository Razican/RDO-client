package utils;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Vector;

import javax.swing.UIManager;

import entities.Server;

/**
 * @author Jordan Aranda Tejada
 */

public class Properties implements Serializable {

	private static final long	serialVersionUID	= 4780308659003926926L;

	private static Properties	properties;

	private Locale				locale;
	private String				lookAndFeelClass;
	private Vector<Server>		servers;
	private boolean				showSavedServers;
	private Dimension			windowSize;
	private String				version;

	private Properties(Locale locale, String lookAndFeelClass,
	Vector<Server> servers, boolean showSavedServers, Dimension windowSize,
	String version)
	{
		this.locale = locale;
		this.lookAndFeelClass = lookAndFeelClass;
		this.servers = servers;
		this.showSavedServers = showSavedServers;
		this.windowSize = windowSize;
		this.version = version;
	}

	private void update()
	{
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(new FileOutputStream(
			"data/config.properties"));
			oos.writeObject(properties);
			oos.close();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			properties = new Properties(Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), new Vector<Server>(),
			false, new Dimension(350, 350), "1.0");
		}
	}

	private static void init()
	{
		ObjectInputStream ois;
		try
		{
			ois = new ObjectInputStream(new FileInputStream(
			"data/config.properties"));
			properties = (Properties) ois.readObject();
			ois.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			if ( ! (e instanceof FileNotFoundException))
			{
				e.printStackTrace();
			}

			properties = new Properties(Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), new Vector<Server>(),
			false, new Dimension(350, 350), "1.0");
			properties.update();
		}
	}

	/**
	 * @return Current locale
	 */
	public static Locale getLocale()
	{
		if (properties == null)
		{
			init();
		}

		return properties.locale;
	}

	/**
	 * @param l Locale to set as default
	 */
	public static void setLocale(final Locale l)
	{
		if (properties == null)
		{
			init();
		}

		if (Lang.getAvailableLocales().contains(l))
		{
			properties.locale = l;
		}
		else
		{
			properties.locale = Lang.getDefaultLocale();
		}

		properties.update();
	}

	/**
	 * @return Current look and feel
	 */
	public static String getLookAndFeel()
	{
		if (properties == null)
		{
			init();
		}

		return properties.lookAndFeelClass;
	}

	/**
	 * @param lf The new Look and feel to set
	 */
	public static void setLookAndFeelClass(final String lf)
	{
		if (properties == null)
		{
			init();
		}

		properties.lookAndFeelClass = lf;
		properties.update();
	}

	/**
	 * @return Saved servers
	 */
	public static Vector<Server> getServers()
	{
		if (properties == null)
		{
			init();
		}
		return properties.servers;
	}

	/**
	 * @param servers New vector of servers
	 */
	public static void setServers(Vector<Server> servers)
	{
		if (properties == null)
		{
			init();
		}
		properties.servers = servers;
		properties.update();
	}

	/**
	 * @param server The new server.
	 */
	public static void addServer(Server server)
	{
		if (properties == null)
		{
			init();
		}
		properties.servers.add(server);
		properties.update();
	}

	/**
	 * @param index The server index to remove.
	 */
	public static void removeServer(int index)
	{
		if (properties == null)
		{
			init();
		}
		properties.servers.remove(index);
		properties.update();
	}

	/**
	 * @return if saved servers are visible
	 */
	public static boolean isShowSavedServers()
	{
		if (properties == null)
		{
			init();
		}
		return properties.showSavedServers;
	}

	/**
	 * @param showSavedServers Sets visible saved servers
	 */
	public static void setShowSavedServers(boolean showSavedServers)
	{
		if (properties == null)
		{
			init();
		}
		properties.showSavedServers = showSavedServers;
		properties.update();
	}

	/**
	 * @return Window size
	 */
	public static Dimension getWindowSize()
	{
		if (properties == null)
		{
			init();
		}
		return properties.windowSize;
	}

	/**
	 * @param windowSize New window's size
	 */
	public static void setWindowSize(Dimension windowSize)
	{
		if (properties == null)
		{
			init();
		}
		properties.windowSize = windowSize;
		properties.update();
		;
	}

	/**
	 * @return Version of application
	 */
	public static String getVersion()
	{
		if (properties == null)
		{
			init();
		}

		return properties.version;
	}

	/**
	 * @param version New version of application
	 */
	public static void setVersion(final String version)
	{
		if (properties == null)
		{
			init();
		}

		properties.version = version;

		properties.update();
	}

	/**
	 * @param args Arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("LECTURA DE DATOS");
		System.out.println("LANGUAGE = " + Properties.getLocale().toString());
		System.out.println("APPEARANCE = "
		+ Properties.getLookAndFeel().toString());
		System.out.println("SERVERS " + Properties.getServers().size());
		for (int i = 0; i < Properties.getServers().size(); i++)
		{
			System.out.println(Properties.getServers().get(i).toString());
		}
		if (Properties.isShowSavedServers())
		{
			System.out.println("SERVERS VISIBLE");
		}
		else
		{
			System.out.println("SERVERS INVISIBLE");
		}
		System.out.println("VERSION = " + Properties.getVersion());

		System.out.println("\nMODIFICACION DE DATOS");
		// Server s = new Server("127.0.0.1", 3000, "Jordan", "1234");
		// Properties.addServer(s);
	}
}
