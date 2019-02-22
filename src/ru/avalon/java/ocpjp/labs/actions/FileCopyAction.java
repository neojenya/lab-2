package ru.avalon.java.ocpjp.labs.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Действие, которое копирует файлы в пределах дискового
 * пространства.
 */
public class FileCopyAction implements Action {
    
    private Path source;
    private Path dest;
    private BufferedReader in;    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            Files.walkFileTree(source, new MyCopyVisitor(source, dest));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public FileCopyAction () throws IOException {
        System.out.print("Исходный файл/директория для копирования > ");
        in = new BufferedReader(new InputStreamReader(System.in));        
        source = Paths.get(in.readLine());
        if (source.toFile().exists()) {
            System.out.print("Укажите путь для копирования > ");
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
    public void close() throws Exception {
        in.close();
        in = null;
        source = null;
        dest = null;
    }
}
