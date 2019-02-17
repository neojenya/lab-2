package ru.avalon.java.ocpjp.labs.actions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 *
 * @author Ella
 */
public class FileWriterAction implements Action {

    private static String FILE_PATH;
    private String line;

    public FileWriterAction(String[] args) {
        FILE_PATH = args[1];
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(" ");
        }
        sb.setLength(sb.length() - 1);
        line = sb.toString();
    }

    @Override
    public synchronized void run() {
        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            bw.write(line);
            bw.flush();
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
