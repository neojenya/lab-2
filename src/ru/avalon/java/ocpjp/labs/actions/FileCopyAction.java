package ru.avalon.java.ocpjp.labs.actions;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
/**
 * Действие, которое копирует файлы в пределах дискового пространства.
 */
public class FileCopyAction implements Action {

    private static String PATH_FROM;
    private static String PATH_TO;
    public FileCopyAction(String[] args) {
        PATH_FROM = args[1];
        PATH_TO = args[2];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №2 Реализуйте метод run класса FileCopyAction
         */
        Path filePath = Paths.get(PATH_FROM);
        Path dirPath = Paths.get(PATH_TO);
        try {
            Files.copy(filePath, dirPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException nse) {
            nse.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        /*
         * TODO №3 Реализуйте метод close класса FileCopyAction
         */
       
    }
}
