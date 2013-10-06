package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import components.Internationalizable;

/**
 * @author Razican (Iban Eguia)
 */
public class Lang 
{

	private static Vector<Locale>						locales;
	private static Lang									currentLang;
	private static HashMap<Internationalizable, String>	observed;
	private Locale										locale;
	private HashMap<String, String>						lines;

	private Lang()
	{
		Locale l = Properties.getLocale();
		if (locales.contains(l))
		{
			this.locale = l;
		}
		else
		{
			this.locale = getDefaultLocale();
		}

		loadLines();
	}

	@SuppressWarnings ("unchecked")
	private void loadLines()
	{
		ObjectInputStream st = null;

		try
		{
			st = new ObjectInputStream(new FileInputStream("lang/" + locale.getLanguage() + "_" + locale.getCountry() + ".lang"));
			lines = (HashMap<String, String>) st.readObject();
			st.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error loading language file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE,null);
		}
	}

	private HashMap<String, String> getLines()
	{
		return lines;
	}

	@SuppressWarnings ("unchecked")
	private void changeLocale(final Locale newLocale)
	{
		if (locales.contains(newLocale))
		{
			locale = newLocale;
			ObjectInputStream st;

			try
			{
				st = new ObjectInputStream(new FileInputStream("lang/" + newLocale.getLanguage() + "_" + newLocale.getCountry() + ".lang"));
				lines = (HashMap<String, String>) st.readObject();
				st.close();
			}
			catch (IOException | ClassNotFoundException e1)
			{
				System.err.println(e1.getMessage());
				JOptionPane.showMessageDialog(null,
				"Error loading language file: " + e1.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE, new ImageIcon("img/error.png"));
			}

			for (final Map.Entry<Internationalizable, String> e: observed
			.entrySet())
			{
				e.getKey().changeLanguage(getLine(e.getValue()));
			}
		}
	}

	private static void initializeLocales()
	{
		locales = new Vector<>();
		for (final File file: (new File("lang").listFiles()))
		{
			if (file.getName().matches("[a-z]{2}_[A-Z]{2}.lang"))
			{
				final String lang = file.getName().substring(0, 2);
				final String country = file.getName().substring(3, 5);
				locales.add(new Locale(lang, country));
			}
		}
	}

	/**
	 * @param c Component to change language. Should be an AbstractButton or
	 *            JLabel, since if it's not, this method will do nothing.
	 * @param key Language key
	 */
	public static void setLine(final Internationalizable c, final String key)
	{
		if (observed == null)
		{
			observed = new HashMap<>();
		}

		observed.put(c, key);
		c.changeLanguage(getLine(key));
	}

	/**
	 * @param key Key for the line
	 * @return String for the current language
	 */
	public static String getLine(final String key)
	{
		if (locales == null)
		{
			initializeLocales();
		}
		if (currentLang == null)
		{
			currentLang = new Lang();
		}
		return currentLang.getLines().get(key);
	}

	/**
	 * @return Currently available locales
	 */
	public static Vector<Locale> getAvailableLocales()
	{
		if (locales == null)
		{
			initializeLocales();
		}
		if (currentLang == null)
		{
			currentLang = new Lang();
		}
		return locales;
	}

	/**
	 * @return Currently available locales
	 */
	public static Vector<String> getCombableLocales()
	{
		if (locales == null)
		{
			initializeLocales();
		}
		if (currentLang == null)
		{
			currentLang = new Lang();
		}

		final Vector<String> combo = new Vector<>(locales.size());

		for (final Locale l: locales)
		{
			combo.add(StringUtils.firstToUpper(l.getDisplayLanguage()) + " ("
			+ l.getDisplayCountry() + ")");
		}

		return combo;
	}

	/**
	 * @return Default locale for the program
	 */
	public static Locale getDefaultLocale()
	{
		if (locales == null)
		{
			initializeLocales();
		}
		if (currentLang == null)
		{
			currentLang = new Lang();
		}

		return locales.contains(new Locale("es", "ES")) ? new Locale("es", "ES")
		: locales.get(0);
	}

	/**
	 * @return Index of the current locale
	 */
	public static int getCurrentLocaleKey()
	{
		return locales.indexOf(currentLang.locale);
	}

	/**
	 * @param newLocale New locale for the interface
	 */
	public static void setLang(final Locale newLocale)
	{
		if (locales == null)
		{
			initializeLocales();
		}
		if (currentLang == null)
		{
			currentLang = new Lang();
		}

		currentLang.changeLocale(newLocale);
	}
}
