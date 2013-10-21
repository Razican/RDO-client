package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import utilities.StringUtils;
import database.DataBase;

/**
 * @author Jordan Aranda Tejada
 */
public class User {

	private static User		user;

	private int				id;
	private String			username;
	private String			password;
	private String			email;
	private String			emailPassword;
	private boolean			remember;
	private Vector<Patient>	patients;

	private User(int id, String username, String password, String email,
	String emailPassword, boolean remember)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.emailPassword = emailPassword;
		this.remember = remember;
		this.patients = new Vector<Patient>();
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
		save();
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
		save();
	}

	/**
	 * @return User email.
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email New user email.
	 */
	public void setEmail(String email)
	{
		this.email = email;
		save();
	}

	/**
	 * @return User email password.
	 */
	public String getEmailPassword()
	{
		return emailPassword;
	}

	/**
	 * @param emailPassword User new email password.
	 */
	public void setEmailPassword(String emailPassword)
	{
		this.emailPassword = emailPassword;
		save();
	}

	/**
	 * @return If the user is remembered.
	 */
	public boolean isRemember()
	{
		return remember;
	}

	/**
	 * @param remember Set remember the user.
	 */
	public void setRemember(boolean remember)
	{
		this.remember = remember;
	}

	/**
	 * @return User patients.
	 */
	public Vector<Patient> getPatients()
	{
		return patients;
	}

	/**
	 * @param username - The username
	 * @param password - The password of the user
	 * @param email - The email of the user
	 * @param emailPassword - The email password of the user
	 */
	public static void login(final String username, final String password,
	final String email, final String emailPassword)
	{
		if ( ! existsUser(username))
		{
			String update = "INSERT INTO USER (username, password, email, email_password, remember) VALUES "
			+ "('"
			+ username
			+ "', '"
			+ StringUtils.sha1(password)
			+ "', '"
			+ email + "', '" + emailPassword + "', 0);";

			DataBase.getInstance().update(update);
		}
		load(username, password);
	}

	/**
	 * @param username The user name.
	 * @param password The user password.
	 */
	public static void load(final String username, final String password)
	{
		user = null;
		if (DataBase.getInstance().count(
		"USER",
		"username='" + username + "' AND password='"
		+ StringUtils.sha1(password) + "'") == 1)
		{
			String query = "SELECT * FROM USER WHERE username='" + username
			+ "' AND password='" + StringUtils.sha1(password) + "';";
			ResultSet rs = DataBase.getInstance().consult(query);

			try
			{
				while (rs.next())
				{
					user = new User(rs.getInt("id"), rs.getString("username"),
					rs.getString("password"), rs.getString("email"),
					rs.getString("email_password"), false);
					if (rs.getInt("remember") == 1)
					{
						user.setRemember(true);
					}

					String query2 = "SELECT * FROM PATIENT WHERE id_user="
					+ user.id;
					ResultSet rs2 = DataBase.getInstance().consult(query2);
					while (rs2.next())
					{
						Patient p = new Patient();
						p.setId(rs2.getInt("id"));
						p.setDni(rs2.getInt("dni"));
						p.setName(rs2.getString("name"));
						p.setLastName(rs2.getString("lastname"));
						p.setBirthdate(new Date(rs2.getInt("birthdate")));
						p.setAddress(rs2.getString("address"));
						p.setTelephone(rs2.getInt("telephone"));
						p.setEmail(rs2.getString("email"));
						p.setIp(rs2.getString("ip_address"));
						p.setPort(rs2.getInt("port"));
						p.setIdUser(user.getId());
						user.getPatients().add(p);
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to save the user's data.
	 */
	public void save()
	{
		String update = "";
		if (user.remember)
		{
			update = "UPDATE USER SET username='" + username + "', password='"
			+ password + "', email='" + email + "', email_password='"
			+ emailPassword + "', remember=1 WHERE id = " + id;
		}
		else
		{
			update = "UPDATE USER SET username='" + username + "', password='"
			+ password + "', email='" + email + "', email_password='"
			+ emailPassword + "', remember=0 WHERE id = " + id;
		}

		DataBase.getInstance().update(update);

		for (int i = 0; i < patients.size(); i++)
		{
			patients.get(i).save();
		}
	}

	/**
	 * @param username The user name.
	 * @return if user exist.
	 */
	public static boolean existsUser(final String username)
	{
		return DataBase.getInstance().count("USER",
		"username='" + username + "'") != 0;
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}

	/**
	 * Load the remembered user
	 */
	public static void loadRememberUser()
	{
		String query = "SELECT * FROM USER WHERE REMEMBER=1";
		ResultSet rs = DataBase.getInstance().consult(query);
		try
		{
			while (rs.next())
			{
				user = new User(rs.getInt("id"), rs.getString("username"),
				rs.getString("password"), rs.getString("email"),
				rs.getString("email_password"), true);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}