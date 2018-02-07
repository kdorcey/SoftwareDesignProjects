import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/***
 * Responsible for starting and controlling all server functionality
 *
 * @author Kyle Dorcey
 * @version 1.8
 */

public class FileRetrieveServer {
    /**Defines the servers ServerSocket which will be used to link the server to the client**/
    private ServerSocket server;
    /**Defines the socket Object which is used to pass and receive the client and servers IP and port numbers  **/
    private Socket connection;
    /**Stream used to send data to the client **/
    private ObjectOutputStream output;
    /**Stream used to recieve data from the client**/
    private ObjectInputStream input;

    /**No argument class constructor**/
    public FileRetrieveServer(){ }

    /***
     * Method responsible for creating the server. As this is the class for the server the while-loop
     * will run until the program is closed. Assuming neither waitForClient(), getClientStream(), or processConnection()
     * throw an IOException the server will process information from a single client.
     */
    public void startServer(){
        try{
            server = new ServerSocket(4444,100);

            while(true){
                try{
                    waitForClient();
                    getClientsStream();
                    processConnection();
                }
                catch (IOException serverStreamProblem) //comes from
                {
                    System.out.println("Broke connection: "+serverStreamProblem);
                }
                finally{
                    closeConnection();
                }
            }
        }
        catch(IOException somethingWentWrong){
            System.out.println("Server: couldn't run server");
        }

    }

    /***
     * Waits for a cliet to connect to the server's socket. When the server accepts a connection it
     * defines the connection variable.
     * @throws IOException throws in the event the server's socket cannot connect to the client's socket properly
     */
    public void waitForClient() throws IOException{
        System.out.println("Server: Waiting for friend :(");
        connection = server.accept();
        System.out.println("Server: Found friend :) named: "+connection.getInetAddress().getHostName());
    }

    /***
     * When called (and connection has been previously defined), this method sets the ObjectOutputStream
     * to send data to the client
     * @throws IOException throws in the event that the client and servers streams do not connect properly
     */
    public void getClientsStream() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("Server: Got friends stream");
    }

    /***
     * This method contains the servers logic. In this case, the server will recieve a file-path from the
     * client. It will then check that the file exists. If it does, it will send the file's contents to
     * the client.
     * @throws IOException throws in the event that the method cannot properly deserialize the client's output (or server's input)
     */
    public void processConnection () throws IOException{
        System.out.println("Server: processing messages");
        String recievedMessage ="Server: Connection Worked";
        sendData(recievedMessage);
        do {
            try {
                recievedMessage = (String) input.readObject();
                System.out.println("searching for file named: "+recievedMessage+" ...");
                String fileCheck = checkForFile(recievedMessage);

                if(recievedMessage.equals("endit"))//condition used to disconnect client
                {
                    sendData("endit");
                }
                else {
                    sendData(fileCheck);
                }
            }

            catch(ClassNotFoundException e){ System.out.println("Client input object not found: "+e.getCause()); }
            catch(IOException f){ System.out.println("Problem with clients input/output stream: "+f.getMessage()); closeConnection();}
        }while(!recievedMessage.equals("endit"));

    }

    /***
     * Called by the processConnection() method, checks if the client requested file exists and returns a string
     * containing its contents if it finds one.
     * @param fileName  Path to the file to read from
     * @return  String foundFileInfo, contains the contents of the file that the client requested.
     */
    public String checkForFile(String fileName){
        String foundFileInfo = "";
        try {
            File fileToRetrieve = new File(fileName);
            Scanner reader = new Scanner(fileToRetrieve);

            while (reader.hasNextLine())
            {
                foundFileInfo +=  reader.nextLine();
            }
        }
        catch (IOException fileNotFound){
            foundFileInfo = "File Not Found";
        }

        return foundFileInfo;
    }

    /***
     * Closes the connection between the server and client by first closing the ObjectOutputStream, inputOutputStream,
     * and Socket
     */
    public void closeConnection(){
        System.out.println("Server: Ending connection");

        try{
            output.close();
            input.close();
            connection.close();
        }
        catch (IOException closeConnectionProblem){
            System.out.println("Server: problem in close connection");
        }
    }

    /**
     * Used to send data to the client
     * @param message String to send to client
     */
    public void sendData(String message){
        try{
            output.writeObject(message);
            output.flush(); //sends necessary information to deserialize the object sent in the ObjectOutputStream
            System.out.println("Server>>> "+message);
        }
        catch (IOException ioException){
            System.out.println("Error writing object");
        }
    }


}
