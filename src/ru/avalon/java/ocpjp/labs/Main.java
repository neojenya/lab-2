package ru.avalon.java.ocpjp.labs;

import ru.avalon.java.ocpjp.labs.console.ConsoleUI;

import java.io.IOException;
import ru.avalon.java.ocpjp.labs.actions.Action;
import ru.avalon.java.ocpjp.labs.actions.FileCopyAction;
import ru.avalon.java.ocpjp.labs.actions.FileDeleteAction;
import ru.avalon.java.ocpjp.labs.actions.FileMoveAction;
import ru.avalon.java.ocpjp.labs.actions.FileReaderAction;
import ru.avalon.java.ocpjp.labs.actions.FileWriterAction;
import ru.avalon.java.ocpjp.labs.actions.HelpAction;

/**
 * Лабораторная работа №2
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle
 * Certified Professional Java Programmer"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность"
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main extends ConsoleUI<Commands> {

    /**
     * Точка входа в приложение.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием перечисления
     * {@link Commands}.
     */
    Main() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        switch (command) {
            case copy:
                /*
                 * TODO №6 Обработайте команду copy
                 */
                Action fileCopy = new FileCopyAction(getArgs());
                fileCopy.start();
                break;
            case move:
                /*
                 * TODO №7 Обработайте команду move
                 */
                Action fileMove = new FileMoveAction(getArgs());
                fileMove.start();
                break;
            case exit:
                close();
                break;
            /*
                 * TODO №9 Обработайте необработанные команды
             */
            case read:
                Action fileReader = new FileReaderAction(getArgs());
                fileReader.start();
                break;
            case delete:
                Action fileDelete = new FileDeleteAction(getArgs());
                fileDelete.start();
                break;
            case write:
                Action fileWrite = new FileWriterAction(getArgs());
                fileWrite.start();
                break;
            case help:
                Action help = new HelpAction();
                help.start();
                break;
        }
    }

}
