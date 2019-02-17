package ru.avalon.java.ocpjp.labs.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ella
 */
public class FileReaderAction implements Action {

    private static String FILE_PATH;

    public FileReaderAction(String[] args) {
        FILE_PATH = args[1];
    }

    @Override
    public void run() {
        File file = new File(FILE_PATH);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException io) {
            io.printStackTrace(System.err);
        }
    }

    @Override
    public void close() throws Exception {

    }

}
