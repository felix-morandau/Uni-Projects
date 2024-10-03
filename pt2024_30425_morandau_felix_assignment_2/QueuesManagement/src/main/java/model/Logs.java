package model;

import java.io.*;

public class Logs {

    private static final String file = "logs.txt";

    public static void addToFile(String content) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFile() {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
            System.out.println("Contents of file " + file + " have been cleared.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
