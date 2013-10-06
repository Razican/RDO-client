package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Jordan Aranda Tejada
 */

public class Utilities {

	/**
	 * @return The current date
	 */
	@SuppressWarnings ("deprecation")
	public static Date getCurrentDate()
	{
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);

		return new Date(year, month, day, hour, minutes, seconds);
	}

	/**
	 * @return The string with date and time
	 */
	public static String getDateAndTimeString()
	{
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);

		String day2 = dF(day);
		String month2 = dF(month);
		String year2 = dF(year);

		String hour2 = dF(hour);
		String minutes2 = dF(minutes);
		String seconds2 = dF(seconds);

		return "(" + day2 + "/" + month2 + "/" + year2 + " - " + hour2 + ":"
		+ minutes2 + ":" + seconds2 + ")";
	}

	private static String dF(int num)
	{
		if (num < 10)
		{
			return "0" + num;
		}
		else
		{
			return "" + num;
		}
	}

	/**
	 * @param args Arguments
	 */
	public static void main(String[] args)
	{
		Date fechaActual = getCurrentDate();
		long time = fechaActual.getTime();
		fechaActual = new Date(time);
		System.out.println("Tiempo: " + fechaActual.getTime());

		System.out.println("Fecha actual: " + getDateAndTimeString());
	}
}
