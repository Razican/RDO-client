package entities;

import utilities.StringUtils;
import utils.Client;

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
	 * @return Server response code
	 */
	public static int checkUser()
	{
		user.client.sendData("USUARIO " + user.username);
		String response = user.client.getInputData();
		return user.client.getInputCode(response);
	}

	/**
	 * @param password The user password
	 * @return Server response code
	 */
	public static int checkPassword(char[] password)
	{
		user.client.sendData("CLAVE " + StringUtils.sha1(password));
		String response = user.client.getInputData();
		return user.client.getInputCode(response);
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}