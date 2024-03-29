package crud.example.json.springbootdemojson.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    private static final String path = "src\\main\\resources\\";

    public static String readFile(String fileName) {
        String fileContent = "";
        try(FileReader fr = new FileReader(getPath(fileName))){
            while(fr.ready()) {
                fileContent += (char)fr.read();
            }
        }catch (IOException e) {
            System.out.println("Error while file reading: " + e);
        }
        return fileContent;
    }

    public static void writeToFile(String in, String fileName) {
        readFile(fileName);
        try(FileWriter fw = new FileWriter(getPath(fileName))) {
            fw.write(in);
        }catch (IOException e) {
            System.out.println("Error while writing to the file: " + e);
        }
    }

    public static String getPath(String fileName) {
        File f = new File(path + "\\" + fileName);
        return f.getAbsolutePath();
    }
}
