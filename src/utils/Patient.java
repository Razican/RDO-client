package utils;

import interfaces.Loadable;
import interfaces.Loader;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Vector;

import entities.Sensor;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Patient implements Serializable, Loader {

	private static final long	serialVersionUID	= - 7186367905563055140L;
	private static Patient		currentPatient		= new Patient();

	private static boolean		transmitting		= false;

	private Patient()
	{
	}

	/**
	 * Gets the sensors from the server
	 * 
	 * @param loadable - The loadable component
	 */
	public synchronized void getSensors(final Loadable loadable)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				Vector<Sensor> vSensors = new Vector<Sensor>();

				transmitting = true;
				User.getCurrent().getClient().sendData("LISTSENSOR");
				String sensorString = User.getCurrent().getClient()
				.getInputData("322 OK Lista finalizada.");
				System.out.println(sensorString);
				transmitting = false;

				String[] sensors = sensorString.split("#");
				for (int i = 1; i < sensors.length; i++)
				{
					System.out.println("Sensor " + i + ":" + sensors[i]);
					String[] attributes = sensors[i].split(";");
					vSensors.add(new Sensor(Integer.parseInt(attributes[0]),
					attributes[1], attributes[2].equals("ON")));
				}
				notifyLoadables(loadable, vSensors);
			}
		}).start();
	}

	/**
	 * @param loadable - The loadable component
	 * @param idSensor - The sensor id
	 */
	public synchronized void getHistoric(final Loadable loadable,
	final int idSensor)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				Vector<String> vHistoric = new Vector<String>();

				transmitting = true;
				User.getCurrent().getClient().sendData("HISTORICO " + idSensor);
				String historicString = User.getCurrent().getClient()
				.getInputData("322 OK Lista finalizada.");
				System.out.println(historicString);
				transmitting = false;

				String[] historicLines = historicString.split("#");
				for (int i = 0; i < historicLines.length; i++)
				{
					System.out.println(i + ":" + historicLines[i]);
					vHistoric.add(historicLines[i]);
				}
				notifyLoadables(loadable, vHistoric);
			}
		}).start();
	}

	/**
	 * @param loadable - The loadable component
	 * @param idSensor - The sensor
	 * @param enable - The new status
	 */
	public synchronized void setSensorStatus(final Loadable loadable,
	final int idSensor, final boolean enable)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				transmitting = true;
				if (enable)
				{
					User.getCurrent().getClient().sendData("ON " + idSensor);
				}
				else
				{
					User.getCurrent().getClient().sendData("OFF " + idSensor);
				}
				String response = User.getCurrent().getClient().getInputData();
				System.out.println(response);
				int code = User.getCurrent().getClient().getInputCode(response);
				notifyLoadables(loadable, code);
				transmitting = false;
			}
		}).start();
	}

	/**
	 * @param enable - The new status
	 * @param loadable - Loadable component
	 */
	public synchronized void setGPSStatus(final boolean enable,
	final Loadable loadable)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				transmitting = true;
				if (enable)
				{
					User.getCurrent().getClient().sendData("ONGPS");
				}
				else
				{
					User.getCurrent().getClient().sendData("OFFGPS");
				}
				String response = User.getCurrent().getClient().getInputData();
				System.out.println(response);
				notifyLoadables(loadable, User.getCurrent().getClient()
				.getInputCode(response));
				transmitting = false;
			}
		}).start();
	}

	/**
	 * @param loadable - The loadable component
	 * @param idSensor - The sensor
	 */
	public synchronized void getSensorValue(final Loadable loadable,
	final int idSensor)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				transmitting = true;
				User.getCurrent().getClient()
				.sendData("GET_VALACT " + idSensor);
				String value = User.getCurrent().getClient().getInputData();
				notifyLoadables(loadable, value);
				transmitting = false;
			}
		}).start();
	}

	/**
	 * @param loadable - The loadable component
	 */
	public synchronized void getFoto(final Loadable loadable)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				transmitting = true;
				User.getCurrent().getClient().sendData("GET_FOTO");
				ByteArrayOutputStream imgStream = User.getCurrent().getClient()
				.getInputByteArray();
				notifyLoadables(loadable, imgStream);
				transmitting = false;
			}
		}).start();
	}

	/**
	 * @param loadable - The loadable component
	 */
	public synchronized void getCoordinates(final Loadable loadable)
	{
		(new Thread()
		{

			@Override
			public void run()
			{
				while (transmitting)
				{
					;
				}

				transmitting = true;
				User.getCurrent().getClient().sendData("GET_LOC");
				String response = User.getCurrent().getClient().getInputData();
				notifyLoadables(loadable, response);
				transmitting = false;
			}
		}).start();
	}

	/**
	 * @return The current patient
	 */
	public static Patient getCurrent()
	{
		return currentPatient;
	}

	@Override
	public synchronized void notifyLoadables(Loadable loadable, Object object)
	{
		loadable.update(object);
	}
}