package ru.avalon.java.ocpjp.labs.actions;

/**
 *
 * @author Ella
 */
public class HelpAction implements Action {

    @Override
    public void run() {
        System.out.println("Exapmels");
        System.out.println("copy\tpath\\from path\\to");
        System.out.println("move\tpath\\from path\\to");
        System.out.println("delete\tpath\\to\\file");
        System.out.println("read\tpath\\to\\file");
        System.out.println("write\tpath\\to\\file word word word");
    }

    @Override
    public void close() throws Exception {
        
    }
    
}
