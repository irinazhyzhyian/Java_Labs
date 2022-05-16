import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WhiteList {
    public ArrayList<String> allowedClients;

    public WhiteList() {

        File myObj = new File( "/Users/irina/Desktop/Java_Lab1/src/main/resources/white_list");

        try {
            Scanner myReader = new Scanner(myObj);
            allowedClients = new ArrayList<String>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allowedClients.add(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
