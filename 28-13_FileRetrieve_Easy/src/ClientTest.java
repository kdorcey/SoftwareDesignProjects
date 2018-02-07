import javax.swing.*;
import java.util.Scanner;

/***
 * Driver class for FileRetrieveClient. Responsible for constructing the FileRetrieveClient class
 * as well as passing the server's IP address to the network
 */
public class ClientTest {
    public static void main(String[] args){
        FileRetrieveClient clientTest;

        if(args.length ==0){
            clientTest = new FileRetrieveClient("127.0.0.1");
        }
        else{
            clientTest = new FileRetrieveClient (args[0]);
        }

        clientTest.runClient();

    }
}
