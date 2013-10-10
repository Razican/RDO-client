package database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import utilities.FileUtils;

/**
 * @author Jordan Aranda Tejada
 */
public class DataBase {

	private static DataBase	instance;
	private Connection		connection;

	private DataBase()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager
			.getConnection("jdbc:sqlite:data/patients.sqlite3");
			this.update("PRAGMA encoding = \"UTF-8\";");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if (numberOfTables() == 0)
		{
			create_tables();
		}
	}

	/**
	 * Disconnect database
	 */
	public void disconnect()
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void create_tables()
	{
		try
		{
			this.update(FileUtils.toString("data/createDB.sql"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		(new File("createDB.sql")).delete();
	}

	private Statement create_statement()
	{
		Statement statement = null;
		try
		{
			statement = this.connection.createStatement();
		}
		catch (SQLException e)
		{
			System.out.println("Error creating statement.");
		}
		return statement;
	}

	/**
	 * @param consult The consult to database
	 * @return resultSet with the data of database
	 */
	public ResultSet consult(String consult)
	{
		Statement statement = this.create_statement();
		try
		{
			return statement.executeQuery(consult);
		}
		catch (SQLException e)
		{
			System.out.println("Error consulting database.");
			return null;
		}
	}

	/**
	 * @param consult The consult to database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int update(String consult)
	{
		Statement statement = this.create_statement();
		try
		{
			return statement.executeUpdate(consult);
		}
		catch (SQLException e)
		{
			System.out.println("Error updating database.\n " + e.toString());
			return 0;
		}
	}

	/**
	 * @param table The table
	 * @param condition The condition
	 * @return The number of rows
	 */
	public int count(String table, String condition)
	{
		int number = 0;
		String where = condition == null ? "" : " WHERE " + condition;
		String consult = "SELECT COUNT(*) as number FROM " + table + where
		+ ";";
		ResultSet result = consult(consult);
		try
		{
			while (result.next())
			{
				number = result.getInt("number");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error counting tables.");
		}
		return number;
	}

	private int numberOfTables()
	{
		return count("sqlite_master", "type='table'");
	}

	/**
	 * @return instance of database
	 */
	public static DataBase getInstance()
	{
		if (instance == null)
		{
			instance = new DataBase();
		}
		return instance;
	}

	/**
	 * @param args Arguments
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings ("deprecation")
		String update = "INSERT INTO PATIENT VALUES (22756155, '127.0.0.1', 3000, 'Jordan', 'Aranda Tejada', "
		+ (new Date(1992, 12, 10).getTime() / 1000)
		+ ", 'Luis de castresana nº9, 13ºF, Barakaldo', 676909011, 'jordan.aranda@me.com')";
		DataBase.getInstance().update(update);
	}
}