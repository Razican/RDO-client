package entities;

/**
 * @author Jordan Aranda Tejada
 */
public class Sensor {

	private int		id;
	private String	description;
	private boolean	connected;

	/**
	 * Default constructor
	 */
	public Sensor()
	{

	}

	/**
	 * @param id Sensor's id
	 * @param description Sensor's description
	 * @param connected if sensor is connected
	 */
	public Sensor(int id, String description, boolean connected)
	{
		super();
		this.id = id;
		this.description = description;
		this.connected = connected;
	}

	/**
	 * @return Sensor's id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id New sensor's id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return Sensor's description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description Sensor's new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return if sensor is connected
	 */
	public boolean isConnected()
	{
		return connected;
	}

	/**
	 * @param connected The state of the sensor
	 */
	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}
}