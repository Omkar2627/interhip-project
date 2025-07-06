package com.app.filehandlingutility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtility {

    // Method to read a file and print its contents
    public static void readFile(String filename) {
        System.out.println("Reading file: " + filename);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to write data to a file (overwrites existing)
    public static void writeFile(String filename, List<String> lines) {
        System.out.println("Writing to file: " + filename);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for(String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Write complete.");
        } catch(IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Method to append data to a file
    public static void appendFile(String filename, String data) {
        System.out.println("Appending to file: " + filename);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(data);
            bw.newLine();
            System.out.println("Append complete.");
        } catch(IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }

    // Method to modify a file by replacing a word with another word
    public static void modifyFile(String filename, String oldWord, String newWord) {
        System.out.println("Modifying file: " + filename);
        try {
            Path path = Paths.get(filename);
            Charset charset = StandardCharsets.UTF_8;

            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(oldWord, newWord);

            Files.write(path, content.getBytes(charset));
            System.out.println("Modification complete.");
        } catch(IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        String filename = "testfile.txt";

        // Write example
        List<String> lines = Arrays.asList("Hello World", "This is a test file.", "File Handling Utility Example.");
        writeFile(filename, lines);

        // Read file
        readFile(filename);

        // Append example
        appendFile(filename, "Appending this line to file.");

        // Read again after append
        readFile(filename);

        // Modify example
        modifyFile(filename, "test", "sample");

        // Read again after modification
        readFile(filename);
    }
}

