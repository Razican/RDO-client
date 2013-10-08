package entities;

import java.io.Serializable;

/**
 * @author Jordan Aranda Tejada
 */
public class Server implements Serializable {

	private static final long	serialVersionUID	= - 7186367905563055140L;

	private String				ip;
	private int					port;
	private String				user;
	private String				password;

	/**
	 * Default contructor
	 */
	public Server()
	{

	}

	/**
	 * @param ip Server ip
	 * @param port Server port
	 * @param user Server user
	 * @param password Server password
	 */
	public Server(String ip, int port, String user, String password)
	{
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	/**
	 * @return Server ip
	 */
	public String getIP()
	{
		return ip;
	}

	/**
	 * @param ip Server new ip
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * @return Server port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port Server new port
	 */
	public void setPort(int port)
	{
		this.port = port;
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
		return ip + ":" + port + " - " + user;
	}

}