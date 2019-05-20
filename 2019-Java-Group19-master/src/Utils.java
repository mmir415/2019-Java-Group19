import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    //This creates a bunch of helper functions to assist us anywhere in our game.

    //The StringBuilder allows you to add characters to a string very easily.
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        //we must catch the io.exception.
        //We load the file in the BufferedReader object.
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            //In whatever line of the file we are working on, as long as we haven't hit the end of the file,
            //append a new line character after every new line.
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    //This will take in a string with a number, and convert that number to an integer.
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            //the 0 is to prevent the code from crashing
            return 0;
        }
    }

}