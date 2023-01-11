package Cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    public static boolean createFile(String fileName) {
        try {
            File file = new File(fileName);
            return file.createNewFile();

        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
            return false;
        }
    }

    public static boolean createFolder(String folderName) {
        File folder = new File(folderName);
        boolean folderCreated = folder.mkdir();
        if(folderCreated){
            System.out.println("Folder created");
            return true;
        } else if (folder.exists()){
            System.out.println("Folder already exists");
            return false;
        } else {
            System.out.println("Folder not created");
            return false;
        }
    }

    public static String[] readFolder(String folderName) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        String[] filesNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            filesNames[i] = files[i].getName().replace(".txt", "");
        }
        return filesNames;
    }

    public static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String fileContent = "";
        if (file.exists()) {
            while (scanner.hasNextLine()) {
                fileContent += scanner.nextLine();
            }
        } else {
            System.out.println("File not found");
        }
        scanner.close();
        return fileContent;
    }

    public static boolean writeInFile(String fileName, String text) throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        FileWriter fileWriter = new FileWriter(fileName);
        if(file.exists()){
            fileWriter.write(text);
            fileWriter.close();
            return true;
        } else {
            System.out.println("File not found");
            return false;
        }
    }

    public static void deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println("File " + fileName + " deleted: " + file.delete());
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean existFile(String fileName){
        File file = new File(fileName);
        return file.exists();
    }
}
