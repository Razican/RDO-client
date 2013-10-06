package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author Jordan Aranda Tejada
 */

public class Properties implements Serializable {

	private static final long	serialVersionUID	= 4780308659003926926L;

	private static Properties	properties;

	private String				serverIP;
	private int					serverPORT;
	private String				tableUsersName;
	private int					maxUsersConnected;
	private int					totalUsers;
	private int					updateTime;
	private String				dataBasePath;
	private boolean				logFile;
	private Locale				locale;
	private String				lookAndFeelClass;
	private String				version;

	private Properties(String serverIP, int serverPORT, String tableUsersName,
	int maxUsersConnected, int totalUsers, int updateTime, String dataBasePath,
	boolean logFile, Locale locale, String lookAndFeelClass, String version)
	{
		this.serverIP = serverIP;
		this.serverPORT = serverPORT;
		this.tableUsersName = tableUsersName;
		this.maxUsersConnected = maxUsersConnected;
		this.totalUsers = totalUsers;
		this.updateTime = updateTime;
		this.dataBasePath = dataBasePath;
		this.logFile = logFile;
		this.locale = locale;
		this.lookAndFeelClass = lookAndFeelClass;
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
			properties = new Properties("25.9.212.247", 5000, "USERS", 10, 50,
			1, "data/autoescuela.sqlite3", true, Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), "1.0");
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

			properties = new Properties("25.9.212.247", 5000, "USERS", 10, 50,
			1, "data/autoescuela.sqlite3", true, Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), "1.0");
			properties.update();
		}
	}

	/**
	 * @return Server IP
	 */
	public static String getServerIP()
	{
		if (properties == null)
		{
			init();
		}

		return properties.serverIP;
	}

	/**
	 * @param IP New server IP
	 */
	public static void setServerIP(final String IP)
	{
		if (properties == null)
		{
			init();
		}

		properties.serverIP = IP;
		properties.update();
	}

	/**
	 * @return Server PORT
	 */
	public static int getServerPORT()
	{
		if (properties == null)
		{
			init();
		}

		return properties.serverPORT;
	}

	/**
	 * @param PORT New server PORT
	 */
	public static void setServerPORT(final int PORT)
	{
		if (properties == null)
		{
			init();
		}

		properties.serverPORT = PORT;
		properties.update();
	}

	/**
	 * @return Table users name
	 */
	public static String getTableUsersName()
	{
		if (properties == null)
		{
			init();
		}

		return properties.tableUsersName;
	}

	/**
	 * @param tableUsersName New table users name
	 */
	public static void setTableUsersName(final String tableUsersName)
	{
		if (properties == null)
		{
			init();
		}

		properties.tableUsersName = tableUsersName;
		properties.update();
	}

	/**
	 * @return Max users connected
	 */
	public static int getMaxUsersConnected()
	{
		if (properties == null)
		{
			init();
		}

		return properties.maxUsersConnected;
	}

	/**
	 * @param maxUsersConnected New max users connected value
	 */
	public static void setMaxUsersConnected(final int maxUsersConnected)
	{
		if (properties == null)
		{
			init();
		}

		properties.maxUsersConnected = maxUsersConnected;
		properties.update();
	}

	/**
	 * @return Total users
	 */
	public static int getTotalUsers()
	{
		if (properties == null)
		{
			init();
		}

		return properties.totalUsers;
	}

	/**
	 * @param totalUsers New total users value
	 */
	public static void setTotalUsers(final int totalUsers)
	{
		if (properties == null)
		{
			init();
		}

		properties.totalUsers = totalUsers;
		properties.update();
	}

	/**
	 * @return Update time
	 */
	public static int getUpdateTime()
	{
		if (properties == null)
		{
			init();
		}

		return properties.updateTime;
	}

	/**
	 * @param updateTime New update time value
	 */
	public static void setUpdateTime(final int updateTime)
	{
		if (properties == null)
		{
			init();
		}

		properties.updateTime = updateTime;
		properties.update();
	}

	/**
	 * @return Database path
	 */
	public static String getDataBasePath()
	{
		if (properties == null)
		{
			init();
		}

		return properties.dataBasePath;
	}

	/**
	 * @param dataBasePath New database path
	 */
	public static void setDataBasePath(final String dataBasePath)
	{
		if (properties == null)
		{
			init();
		}

		properties.dataBasePath = dataBasePath;
		properties.update();
	}

	/**
	 * @return If log file is enable
	 */
	public static boolean isLogFileEnable()
	{
		if (properties == null)
		{
			init();
		}

		return properties.logFile;
	}

	/**
	 * @param logFile Enables logfile (true)
	 */
	public static void setLogFileEnable(final boolean logFile)
	{
		if (properties == null)
		{
			init();
		}

		properties.logFile = logFile;
		properties.update();
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

		if (isLFAvailable(lf))
		{
			properties.lookAndFeelClass = lf;
		}
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

	private static boolean isLFAvailable(final String lf)
	{
		final LookAndFeelInfo lfs[] = UIManager.getInstalledLookAndFeels();
		for (final LookAndFeelInfo lf2: lfs)
		{
			if (lf2.getClassName().equals(lf))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param args Arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("LECTURA DE DATOS");
		System.out.println("IP = " + Properties.getServerIP());
		System.out.println("PORT = " + Properties.getServerPORT());
		System.out.println("LANGUAGE = " + Properties.getLocale().toString());
		System.out.println("VERSION = " + Properties.getVersion());
		System.out.println("\nMODIFICACION DE DATOS");

		Properties.setServerIP("85.84.249.117");
		Properties.setServerPORT(80);

	}
}
