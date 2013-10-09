package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Client {

	private String				ip		= "";
	private int					port	= 0;

	private Socket				clientSocket;
	private boolean				connected;
	private DataOutputStream	output;
	private BufferedReader		input;

	/**
	 * Default constructor
	 * 
	 * @param ip Server's ip
	 * @param port Server's port
	 */
	public Client(String ip, int port)
	{
		this.ip = ip;
		this.port = port;

		if (Utilities.internetConnection())
		{
			try
			{
				connectToServer();
				getStreams();
				this.connected = true;
			}
			catch (EOFException excepcionEOF)
			{
				excepcionEOF.printStackTrace();
			}
			catch (IOException excepcionES)
			{
				this.connected = false;
				JOptionPane.showMessageDialog(Window.getInstance(),
				Lang.getLine("connection_error_message") + " " + ip + ":"
				+ port, Lang.getLine("connection_error"),
				JOptionPane.ERROR_MESSAGE, new ImageIcon("img/error-icon.png"));
			}
		}
		else
		{
			JOptionPane.showMessageDialog(Window.getInstance(),
			Lang.getLine("network_error_message"),
			Lang.getLine("network_error"), JOptionPane.ERROR_MESSAGE,
			new ImageIcon("img/network-icon.png"));
		}
	}

	private void connectToServer() throws UnknownHostException, IOException
	{
		clientSocket = new Socket(ip, port);
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

	/**
	 * @return Client socket
	 */

	public Socket getClientSocket()
	{
		return clientSocket;
	}

	/**
	 * @return if socket is correctly connected
	 */
	public boolean isConnected()
	{
		return connected;
	}

	/**
	 * @param args Application arguments.
	 */
	public static void main(String ... args)
	{
		Client cliente = new Client("127.0.0.1", 3000);
		cliente.sendData("HELLO");
	}
}