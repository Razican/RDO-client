package entities;

import java.io.Serializable;

/**
 * @author Jordan Aranda Tejada
 */
public class Server implements Serializable {

	private static final long	serialVersionUID	= - 7186367905563055140L;

	private String				IP;
	private int					PORT;
	private String				user;
	private String				password;

	/**
	 * Default contructor
	 */
	public Server()
	{

	}

	/**
	 * @param IP Server ip
	 * @param PORT Server port
	 * @param user Server user
	 * @param password Server password
	 */
	public Server(String IP, int PORT, String user, String password)
	{
		this.IP = IP;
		this.PORT = PORT;
		this.user = user;
		this.password = password;
	}

	/**
	 * @return Server ip
	 */
	public String getIP()
	{
		return IP;
	}

	/**
	 * @param IP Server new ip
	 */
	public void setIP(String IP)
	{
		this.IP = IP;
	}

	/**
	 * @return Server port
	 */
	public int getPORT()
	{
		return PORT;
	}

	/**
	 * @param PORT Server new port
	 */
	public void setPORT(int PORT)
	{
		this.PORT = PORT;
	}

	/**
	 * @return Server user
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * @param user Server new user
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	 * @return Server user's password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password Server user's new password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return IP + ":" + PORT + " - " + user;
	}

}