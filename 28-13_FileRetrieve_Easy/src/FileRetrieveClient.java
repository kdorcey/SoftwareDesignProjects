import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/***
 * Class responsible for creating and implementing functionality into the client.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class FileRetrieveClient {
    /**ObjectOutputStream object used to send information to the Server**/
    private ObjectOutputStream output;
    /**ObjectInputStream object used to receive information from the server**/
    private ObjectInputStream input;
    /**message from the server shown as a String**/
    private String messageFromServer ="";
    /**IP address of the server the client will connect to**/
    private String serverName;
    /**Client's socket which will be used to connect to the Server's socket **/
    private Socket client;
    /**Scanner object used to read the desired filepath from the console**/
    private Scanner reader = new Scanner (System.in);

    /***
     * Class constructor. Sets the serverName equal to the constructors input parameter.
     * @param serverName String containing the IP address of the server to connect to
     */
    public FileRetrieveClient(String serverName){
        this.serverName = serverName;
    }

    /***
     * When this method is called it will attempt to connect to the server, if/when it does it will
     * connect the servers ObjectOutputStream to its own ObjectInputStream and vice-versa. It will also
     * call the processConnection() method
     */
    public void runClient(){
        try
        {
            connectToServer();
            getStreams();
            processConnection();
        }
        catch (IOException problemConecting){
            System.out.println("Client: Couldn't connect to server");
        }
        finally {
            closeConnection();
        }
    }

    /***
     * Attempts to connect the client's socket to the server socket. The port number is an arbitrary
     * integer chosen to match the servers port number.
     * @throws IOException throws in the event that client cannot connect to the specified IP-address
     */
    private void connectToServer() throws IOException{
        System.out.println("Client: Attempting to connect to server");

        client = new Socket(InetAddress.getByName(serverName),4444);

        System.out.println("Client: connect to: "+ client.getInetAddress().getHostName());
    }

    /***
     * attempts to connect the client's ObjectOutputStream with the server's ObjectInputStream and vice-versa.
     * @throws IOException throws in the event that the streams cannot properly connect
     */
    private void getStreams() throws IOException{
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush(); //sends information that is required for the server to deserialize the client's ObjectOutputStream

        input = new ObjectInputStream(client.getInputStream());
        System.out.println("Client: Caught stream");
    }

    /***
     * Contains the logic that processes the information the client recieves from the server. In this case
     * it reads in the file-path of the file the client wishes to receive the information from. It will continue
     * to do this until the user inputs "endit" which will exit the while-loop and close the connection with the server
     * @throws IOException throws in the event that it cannot deserialize the input.readObject()
     */
    private void processConnection() throws IOException{
        try{
            messageFromServer = (String) input.readObject();
        }
        catch (ClassNotFoundException noClassFound){
            System.out.println(noClassFound);
        }

        System.out.println("Connection Check: "+messageFromServer);

        do { //using do so that the program checks conditions at bottom of loop rather than top
            try {
                String toServer = "";
                System.out.println("Name of file to retrieve: ");
                toServer = reader.nextLine();


                sendData(toServer);
                messageFromServer = (String) input.readObject();
                System.out.println("Server: " + messageFromServer);
            }
            catch (ClassNotFoundException noClassFound) {
                System.out.println("Client: Weird object in Client Process Connection");
            }
        } while(!messageFromServer.equals("endit"));
    }

    /***
     * called when the message from the server is "endit" (which is triggered by the client sending
     * the server the same term). It closes the ObjectOutputStream, ObjectInputStream, and client socket.
     */
    private void closeConnection(){
        try {
            output.close();
            input.close();
            client.close();
            System.out.println("Client successfully closed ");
        }
        catch (IOException errorClosingStreams){
            System.out.println("Client: Couldn't close streams");
        }
    }

    /***
     * Sends data to the server
     * @param message String containing the message to send to the server
     */
    public void sendData(String message){
        try{
            output.writeObject(message);
            output.flush();
        }
        catch(IOException sendDataError){
            System.out.println("Client: Client can't send error");
        }
    }




}
