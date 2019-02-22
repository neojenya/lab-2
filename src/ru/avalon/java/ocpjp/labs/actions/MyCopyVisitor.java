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
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Fox
 */
public class MyCopyVisitor extends SimpleFileVisitor<Path> {

    private Path source;
    private Path dest;
    
    public MyCopyVisitor(Path source, Path dest) {
        this.source = source;
        this.dest = dest;
    }
    
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) { 
        Path newDest = dest.resolve(source.relativize(path)); 
        try { 
            Files.copy(path, newDest, REPLACE_EXISTING); 
        } catch (IOException e) { 
            System.out.println(e.getMessage()); 
        } 
        return FileVisitResult.CONTINUE; 
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) { 
        Path newDest = dest.resolve(source.relativize(path)); 
        try { 
            Files.copy(path, newDest, REPLACE_EXISTING); 
        } catch (IOException e) { 
            System.out.println(e.getMessage()); 
        } 
        return FileVisitResult.CONTINUE; 
    }
}
