package examples;

import org.apache.tika.Tika;
import java.io.File;
import java.io.IOException;

public class FindFileType {

    public static void main(String[] args) {
        File file = new File("C:\\...");
        Tika tika = new Tika();
        try{
            String fileType = tika.detect(file);
            System.out.println("file type"+fileType);
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
