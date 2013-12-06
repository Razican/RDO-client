package network;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import utils.Lang;
import display.components.Window;

/**
 * @author Jordan Aranda Tejada
 */
public class Client {

	private String				ip;
	private int					port;

	private Socket				clientSocket;
	private DataOutputStream	output;
	private BufferedReader		input;
	private boolean				connected;

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
				"No se ha podido conectar con el servidor " + ip + ":" + port,
				"Fallo en la conexión", JOptionPane.ERROR_MESSAGE,
				new ImageIcon("img/error-icon.png"));
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
		System.out.println("Conecting to server: " + ip + ":" + port);
		clientSocket = new Socket(ip, port);
		this.connected = true;
		System.out.println("Correctly connected to server: " + ip + ":" + port);
		clientSocket.setSoTimeout(2000);
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
		System.out.println("Client sending --> " + command);
		if (clientSocket != null)
		{
			try
			{
				output.writeBytes(command + "\n");
			}
			catch (IOException excepcionES)
			{
				System.out.println("Error sending data: " + command
				+ " to server.");
			}
		}
	}

	/**
	 * Method to get data from the server.
	 * 
	 * @return Data from the server.
	 */
	public synchronized String getInputData()
	{
		String result = null;
		try
		{
			result = input.readLine();
			System.out.println("Client receiving --> " + result);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method to get data from the server.
	 * 
	 * @param endmark The end mark
	 * @return Data from the server.
	 */
	public String getInputData(String endmark)
	{
		String result = null;
		try
		{
			String line = "";
			while ( ! (line = input.readLine()).equals(endmark))
			{
				result += line + "#";
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method to get files from server.
	 * 
	 * @return A file with photo from server.
	 */
	public File getInputFile()
	{
		try
		{
			String line = input.readLine();
			int length = Integer.parseInt(line.split(" ")[1]);
			System.out.println("Tamaño: " + length);

			InputStream iStream = clientSocket.getInputStream();
			ByteArrayOutputStream imgStream = new ByteArrayOutputStream(length);

			byte buffer[] = new byte[4092];
			int read_count = 0;
			int i = 0;

			while (i < length - 1
			&& (read_count = iStream.read(buffer, 0, buffer.length)) != - 1)
			{
				imgStream.write(buffer, 0, read_count);
				i += read_count;
			}

			if (i < length - 1)
			{
				// TODO reenviar foto
				System.out.println("Foto a la mitad :(");
			}

			FileOutputStream fos = new FileOutputStream("downloads/test.png");
			fos.write(imgStream.toByteArray());
			fos.close();
			imgStream.close();

			return new File("downloads/photo.png");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param data The imput data
	 * @return Input data code
	 */

	public int getInputCode(String data)
	{
		return Integer.parseInt(data.substring(0, 3));
	}

	/**
	 * @param data The imput data
	 * @return Input data description
	 */

	public String getInputDescription(String data)
	{
		return data.substring(3, data.length());
	}

	/**
	 * Method to close streams and socket.
	 */
	public void closeConnection()
	{
		try
		{
			this.connected = false;
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
	 * @return if client is connected to server
	 */
	public boolean isConnected()
	{
		return connected;
	}

	/**
	 * @return if there is connected to internet
	 */
	public boolean internetConnection()
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
	 * @param args Application arguments.
	 */
	public static void main(String ... args)
	{
		Client client = new Client("88.2.185.40", 1099);
		client.sendData("USUARIO ");
		String result = client.getInputData();
		System.out.println("Respuesta: " + result);

		client.sendData("SALIR");
		String result2 = client.getInputData();
		System.out.println("Respuesta: " + result2);
	}
}