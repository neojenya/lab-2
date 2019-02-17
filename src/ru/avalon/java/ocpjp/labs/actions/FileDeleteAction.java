package ru.avalon.java.ocpjp.labs.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Ella
 */
public class FileDeleteAction implements Action {

    private static String FILE_PATH;
    
    public FileDeleteAction(String[] args) {
        FILE_PATH = args[1];
    }

    @Override
    public synchronized void run() {
        Path filePath = Paths.get(FILE_PATH);
        try {
            Files.delete(filePath);
        } catch (NoSuchFileException nse) {
           nse.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void close() throws Exception {

    }

}
