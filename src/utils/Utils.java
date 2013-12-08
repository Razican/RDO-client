package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Jordan Aranda Tejada
 */
public class Utils {

	/**
	 * Method to create SHA1 from char array
	 * 
	 * @param array Characters to combert in sha1
	 * @return Sha1 string
	 */
	public static String sha1(char[] array)
	{
		String sha1 = "";
		String s = new String(array);
		try
		{
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(s.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return sha1;
	}

	/**
	 * Method to create Hexadecimal string from byte array
	 * 
	 * @param hash Byte array to combert to hexadecimal
	 * @return Hexadecimal string
	 */
	private static String byteToHex(final byte[] hash)
	{
		Formatter formatter = new Formatter();
		for (byte b: hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * Method to format string
	 * 
	 * @param string String to change the first letter to uppercase
	 * @return The string formatted
	 */
	public static String formatString(final String string)
	{
		String firstCharacter = string.substring(0, 1).toUpperCase();
		return firstCharacter + string.substring(1);
	}

	/**
	 * Opens a file with a file chooser
	 * 
	 * @param description - The description of the file
	 * @param extensions - The extension of the file. Could be multiple
	 *            extensions.
	 * @return The file loaded
	 */
	public static File openFile(final String description,
	final String ... extensions)
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter(description,
		extensions));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		String path = "";
		File file = null;
		try
		{
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getSelectedFile().getAbsolutePath();
				file = new File(path);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * Saves a file choosen by a file chooser
	 * 
	 * @param content - The object to save
	 * @param description - The description of the file
	 * @param extension - The extension of the file
	 * @param file - The file in where to save
	 * @return The path of the saved file
	 */
	public static String saveObjectFile(final Object content,
	final String description, final String extension, final File file)
	{
		final JFileChooser fileChooser = new JFileChooser();
		final FileNameExtensionFilter langFilter = new FileNameExtensionFilter(
		description, extension);
		fileChooser.setFileFilter(langFilter);
		fileChooser.setSelectedFile(file);
		String path = "";
		try
		{
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getSelectedFile().getAbsolutePath();
				if ( ! path.endsWith("." + extension))
				{
					path += "." + extension;
				}
				final File file2 = new File(path);
				if ((file2.exists() && JOptionPane.OK_OPTION == JOptionPane
				.showConfirmDialog(null,
				"The file exists, do you want to replace it?", "File Exists",
				JOptionPane.YES_NO_OPTION))
				|| ! file2.exists())
				{
					final ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(path));
					oos.writeObject(content);
					oos.close();
				}
			}
			return path;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return path;
		}
	}
}