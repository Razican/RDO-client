package entities;

import utilities.StringUtils;

/**
 * @author Jordan Aranda Tejada
 */
public class User {

	private static User	user;

	private int			id;
	private String		username;
	private String		password;

	private User(int id, String username, String password)
	{
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return User id.
	 */
	public int getId()
	{
		return id;
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
	 * @return User password in SHA-1
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password New user password.
	 */
	public void setPassword(String password)
	{
		this.password = StringUtils.sha1(password);
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}