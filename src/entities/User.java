package entities;

import network.Client;
import utils.Utils;

/**
 * @author Jordan Aranda Tejada
 */
public class User {

	private static User	user;

	private String		username;
	private Client		client;

	private User(String username, Client client)
	{
		this.username = username;
		this.client = client;
	}

	/**
	 * @return User name.
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username The new user name.
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return User client
	 */
	public Client getClient()
	{
		return client;
	}

	/**
	 * @param username The username
	 * @param client The user client
	 */
	public static void load(String username, Client client)
	{
		user = new User(username, client);
	}

	/**
	 * @return Server response data
	 */
	public static String checkUser()
	{
		user.client.sendData("USUARIO " + user.username);
		return user.client.getInputData();
	}

	/**
	 * @param password The user password
	 * @return Server response data
	 */
	public static String checkPassword(char[] password)
	{
		user.client.sendData("CLAVE " + Utils.sha1(password));
		return user.client.getInputData();
	}

	/**
	 * Method to logout user from server
	 */
	public void logout()
	{
		client.sendData("SALIR");
		if (client.getInputCode(client.getInputData()) == 318)
		{
			client.closeConnection();
			user = null;
		}
	}

	/**
	 * @return If user is loaded
	 */
	public static boolean isLoaded()
	{
		return user != null;
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}