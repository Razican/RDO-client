package entities;

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
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}