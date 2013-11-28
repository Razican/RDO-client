package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;

import entities.User;

/**
 * @author Jordan Aranda Tejada
 */

public class Properties implements Serializable {

	private static final long	serialVersionUID	= 4780308659003926926L;
	private static Properties	properties;
	private Locale				locale;
	private String				version;
	private User				rememberedUser;

	private Properties(Locale locale, User rememberedUser, String version)
	{
		this.locale = locale;
		this.rememberedUser = rememberedUser;
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
			properties = new Properties(Locale.getDefault(), null, "1.0");
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
			properties = new Properties(Locale.getDefault(), null, "1.0");
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
	 * @return Remembered User
	 */
	public static User getRememberedUser()
	{
		if (properties == null)
		{
			init();
		}
		return properties.rememberedUser;
	}

	/**
	 * @param user User to set as remembered
	 */
	public static void setRememberedUser(final User user)
	{
		if (properties == null)
		{
			init();
		}
		properties.rememberedUser = user;
		properties.update();
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
		System.out.println("VERSION = " + Properties.getVersion());

		// System.out.println("\nMODIFICACION DE DATOS");
		// Server s = new Server("127.0.0.1", 3000, "Jordan", "1234",
		// "Servidor del paciente Jordan Aranda");
		// Properties.addServer(s);
	}
}
