package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Client {

	private String				IP		= "";
	private int					PORT	= 10800;

	private Socket				clientSocket;
	private DataOutputStream	output;
	private BufferedReader		input;

	/**
	 * Default constructor
	 * 
	 * @param IP Server's ip
	 */
	public Client(String IP)
	{
		this.IP = IP;

		if (internetConnection())
		{
			try
			{
				connectToServer();
				getStreams();
			}
			catch (EOFException excepcionEOF)
			{
				excepcionEOF.printStackTrace();
			}
			catch (IOException excepcionES)
			{
				JOptionPane.showMessageDialog(Window.getInstance(),
				"Error al conectar con la dirección: " + IP,
				"Error en la conexión", JOptionPane.ERROR_MESSAGE,
				new ImageIcon("img/error-icon.png"));
			}
		}
		else
		{
			JOptionPane.showMessageDialog(Window.getInstance(),
			"No hay conexión a la red.", "Error de red",
			JOptionPane.ERROR_MESSAGE, new ImageIcon("img/red-icon.png"));
			System.out.println("No internet connection.");
		}
	}

	private void connectToServer() throws IOException
	{
		clientSocket = new Socket(IP, PORT);
	}

	private void getStreams() throws IOException
	{
		output = new DataOutputStream(clientSocket.getOutputStream());
		output.flush();
		input = new BufferedReader(new InputStreamReader(
		clientSocket.getInputStream()));
	}

	/**
	 * Method to send data to the server.
	 * 
	 * @param command The command.
	 */
	public void sendData(String command)
	{
		try
		{
			output.writeBytes(command);
		}
		catch (IOException excepcionES)
		{
			System.out
			.println("Error sending data: " + command + " to server.");
		}
	}

	/**
	 * Method to get data from the server.
	 * 
	 * @return Data from the server.
	 */
	public String getInputData()
	{
		String result = null;
		try
		{
			result = input.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return Input data code
	 */

	public int getInputCode()
	{
		return Integer.parseInt(getInputData().substring(0, 3));
	}

	/**
	 * @return Input data description
	 */

	public String getInputDescription()
	{
		return getInputData().substring(3, getInputData().length());
	}

	/**
	 * Method to close streams and socket.
	 */
	public void closeConnection()
	{
		try
		{
			output.close();
			input.close();
			clientSocket.close();
		}
		catch (IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}

	private boolean internetConnection()
	{
		try
		{
			URL url = new URL("http://www.google.es");
			URLConnection urlConnection = url.openConnection();
			urlConnection.connect();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * @return Client socket
	 */

	public Socket getClientSocket()
	{
		return clientSocket;
	}

	/**
	 * @param args Application arguments.
	 */
	public static void main(String ... args)
	{
		Client cliente = new Client("127.0.0.1");
		cliente.sendData("HELLO");
	}
}