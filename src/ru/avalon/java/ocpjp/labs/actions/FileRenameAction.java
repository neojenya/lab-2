/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.ocpjp.labs.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Fox
 */
public class FileRenameAction implements Action {
    
    private Path source;
    private BufferedReader in;
    private String name;
    
    public FileRenameAction () throws IOException {
        System.out.print("Файл/директория для переименования > ");
        in = new BufferedReader(new InputStreamReader(System.in));        
        source = Paths.get(in.readLine());
        if (source.toFile().exists()) {
            System.out.print("Новое имя > ");
            name = in.readLine();
            start();
        } else {
            System.out.println("Такого файла/директории не существует!");
        }         
    }
    
    @Override
    public void run() {
        try {
            Files.move(source, source.resolveSibling(name));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        in.close();
        in = null;
        source = null;
        name = null;
    }
    
}
