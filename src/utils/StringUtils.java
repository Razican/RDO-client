package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Razican (Iban Eguia)
 */
public final class StringUtils {

	private StringUtils()
	{
	}

	/**
	 * @param data Data to convert to hexadecimal
	 * @return String in hexadecimal
	 */
	private static String toHex(final byte[] data)
	{
		final StringBuffer buf = new StringBuffer();
		for (final byte element: data)
		{
			int halfbyte = (element >>> 4) & 0x0F;
			int two_halfs = 0;
			do
			{
				if ((0 <= halfbyte) && (halfbyte <= 9))
				{
					buf.append((char) ('0' + halfbyte));
				}
				else
				{
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = element & 0x0F;
			}
			while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	/**
	 * @return empty string coded in sha1
	 */
	public static String sha1()
	{
		return sha1("");
	}

	/**
	 * @param str Text to crypt in sha1
	 * @return Generated sha1 hash
	 */
	public static String sha1(final String str)
	{
		byte[] sha1hash = new byte[40];
		try
		{
			final MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes("UTF-8"), 0, str.length());
			sha1hash = md.digest();
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (final UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return toHex(sha1hash);
	}

	/**
	 * @param s String to convert
	 * @return The string with the first letter in uppercase
	 */
	public static String firstToUpper(final String s)
	{
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}