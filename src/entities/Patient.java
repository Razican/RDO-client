package entities;

import java.io.Serializable;
import java.util.Date;

import database.DataBase;

/**
 * @author Jordan Aranda Tejada
 */
public class Patient implements Serializable {

	private static final long	serialVersionUID	= - 7186367905563055140L;

	private int					id;
	private int					dni;
	private String				ip;
	private int					port;
	private String				name;
	private String				lastName;
	private Date				birthdate;
	private String				address;
	private int					telephone;
	private String				email;
	private int					idUser;

	/**
	 * Default contructor
	 */
	public Patient()
	{

	}

	/**
	 * @param id Patient id
	 * @param dni Patient identification number
	 * @param ip Patient ip
	 * @param port Patient port
	 * @param name Patient name
	 * @param lastName Patient last name
	 * @param birthdate Patient birth date
	 * @param address Patient address
	 * @param zipCode Patient zip code
	 * @param telephone Patient telephone number
	 * @param email Patient email
	 * @param idUser Users id
	 */
	public Patient(int id, int dni, String ip, int port, String name,
	String lastName, Date birthdate, String address, int telephone,
	String email, int idUser)
	{
		this.id = id;
		this.dni = dni;
		this.ip = ip;
		this.port = port;
		this.name = name;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.idUser = idUser;
	}

	/**
	 * @return Patient id.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id New patient id.
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return Patient identification number
	 */
	public int getDni()
	{
		return dni;
	}

	/**
	 * @param dni Patient new identification number
	 */
	public void setDni(int dni)
	{
		this.dni = dni;
	}

	/**
	 * @return Patient ip
	 */
	public String getIp()
	{
		return ip;
	}

	/**
	 * @param ip Patient new ip
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * @return Patient port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port Patient new port
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return Patient name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name Patient new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return Patient last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName Patient new last name
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return Patient birth date
	 */
	public Date getBirthdate()
	{
		return birthdate;
	}

	/**
	 * @param birthdate Patient new birth date
	 */
	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	/**
	 * @return Patient address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address New patient address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return telephone Patient telephone number
	 */
	public int getTelephone()
	{
		return telephone;
	}

	/**
	 * @param telephone New patient telephone number
	 */
	public void setTelephone(int telephone)
	{
		this.telephone = telephone;
	}

	/**
	 * @return email Patient email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email New patient email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return Patient user id.
	 */
	public int getIdUser()
	{
		return idUser;
	}

	/**
	 * @param idUser New id user.
	 */
	public void setIdUser(int idUser)
	{
		this.idUser = idUser;
	}

	/**
	 * @return The patient dni letter
	 */
	public char getDniLetter()
	{
		String characters = "TRWAGMYFPDXBNJZSQVHLCKET";
		int module = dni % 23;
		return characters.charAt(module);
	}

	/**
	 * @param dni The patient dni number.
	 * @return The letter of the patient dni
	 */
	public static char getDniLetter(int dni)
	{
		String characters = "TRWAGMYFPDXBNJZSQVHLCKET";
		int module = dni % 23;
		return characters.charAt(module);
	}

	/**
	 * @param dni Patient dni.
	 * @param name Patient name.
	 * @param lastname Patient lastname.
	 * @param birthdate Patient birthdate.
	 * @param address Patient address.
	 * @param telephone Patient telephone.
	 * @param email Patient email.
	 * @param ipAddress Patient ip address.
	 * @param port Patient port.
	 * @param idUser Patient user id.
	 */
	public static void create(final int dni, final String name,
	final String lastname, final Date birthdate, final String address,
	final int telephone, final String email, final String ipAddress,
	final int port, final int idUser)
	{
		if ( ! existsPatient(dni, idUser))
		{

		}
	}

	/**
	 * Save the patient data.
	 */
	public void save()
	{
		String update = "UPDATE PATIENT SET dni=" + dni + ", name='" + name
		+ "', lastname='" + lastName + "', birthdate=" + birthdate.getTime()
		+ ", address='" + address + "', telephone=" + telephone + ", email='"
		+ email + ", ip_address='" + ip + "', port='" + port + ", id_user="
		+ idUser + "WHERE id=" + id;
		DataBase.getInstance().update(update);
	}

	private static boolean existsPatient(final int dni, final int idUser)
	{
		return DataBase.getInstance().count("PATIENT",
		"dni=" + dni + ", AND id_user=" + idUser) != 0;
	}
}