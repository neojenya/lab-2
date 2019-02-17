package ru.avalon.java.ocpjp.labs.actions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Абстрактное представление о действии, как функциональном элементе приложения.
 * <p>
 * Действие является потоковым объектом, что позволяет исполнять несколько
 * действий параллельно и не блокировать основной поток исполнения.
 */
public interface Action extends Runnable, AutoCloseable {

    /**
     * Запускает потоковый объект на исполнение в отдельном потоке исполнения.
     */
    default void start() {
        /*
         * TODO №1 Реализуйте метод start интерфейса Action.
         */
        
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {             
                Action.this.run();
                System.out.print("> ");
                try {
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });
        thread.start();
    }

}
