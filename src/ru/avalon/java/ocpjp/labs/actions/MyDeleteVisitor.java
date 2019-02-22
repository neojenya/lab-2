/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.ocpjp.labs.actions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Fox
 */
public class MyDeleteVisitor extends SimpleFileVisitor<Path> {

    private Path source;
    
    public MyDeleteVisitor(Path source) {
        this.source = source;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Path path = source.resolve(source.relativize(dir));
        try { 
            Files.delete(path);
        } catch (IOException e) { 
            System.out.println(e.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path path = source.resolve(source.relativize(file));
        try { 
            Files.delete(path);
        } catch (IOException e) { 
            System.out.println(e.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }    
}
