package ru.avalon.java.ocpjp.labs.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    
    private Path source;
    private Path dest;
    private BufferedReader in;
    
    public FileMoveAction () throws IOException {
        System.out.print("Исходное местоположение файла/директории > ");
        in = new BufferedReader(new InputStreamReader(System.in));        
        source = Paths.get(in.readLine());
        if (source.toFile().exists()) {
            System.out.print("Новое местоположение файла/директории > ");
            dest = Paths.get(in.readLine());
            start();
        } else {
            System.out.println("Такого файла/директории не существует!");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            Files.move(source, dest, REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        in.close();
        in = null;
        source = null;
        dest = null;        
    }

}
