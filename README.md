# Лабораторная работа №2

_по курсу: "DEV-OCPJP. Подготовка к сдаче 
сертификационных экзаменов серии Oracle Certified 
Professional Java Programmer"_

## Рассматриваемые темы

1. Потоки
2. Файловая система
3. Autoclosable-объекты
4. Перечисления
5. Обобщения

Лабораторная работа представляет собой макет проекта 
на языке программирования Java, в котором поставлены
задания в виде TODO-комментариев. Ход работы состоит 
в том, чтобы выполнить все предложенные в исходном 
коде задания.


## Запуск потока

Для запуска потока необходимо описать потоковую сущность, на основе 
которой будет выполнен запуск потока. Потоковую сущность можно 
ассоциировать с представлением о задаче. В стандартной библиотеке Java
существует два вида потоковых сущностей: `Runnable` и `Callable`.

```
class Task implements Runnable {

    @Override
    public void run() {
        // потоковая процедура
        throw new UnsupportedOperationException("Not implemented!");
    }
}
``` 
Сущьности типа `Callable` отличаются тем, что предполагают получение 
некоторого результата, в случае успешного выполнения метода `call`.

```
class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        // реализация
        throw new UnsupportedOperationException("Not implemented!");
    }
}
```
На основе описанного класса можно создать объект типа `Thread`, 
позволяющий запустить параллельный поток исполнения.
```
// создаём экземпляр потокового объекта
Runnable runnable = new Task();

// создаём поток
Thread thread = new Thread(runnable);

// если требуется, определяем приоритет потока
thread.setPriority(Thread.NORM_PRIORITY);

// если необходимо, делаем поток фоновым
thread.setDaemon(true);

// запускаем поток
thread.start();
```
### Executor Service

Рекомендованным подходом к запуску потоков считают использование 
Исполнителей. Исполнитель может быть создан с использованием фабричных
методов класса `Executors` пакета `java.lang.concurrent`.
```
// создаём Executor с использованием фабричного метода
ExecutorService service = Executors.newSingleThreadExecutor();

// создаём задачу
Callable<String> callable = new Task();

// отправляем задачу на исполнение
Future<String> future = service.submit(runnable);

// получаем результат с использованием экземпляра типа Future
String string = future.get();
```

## Синхронизация

Взаимодействие потоков требует некоторого внимания со стороны 
программиста. Меры по урегулированию порядка взаимодействия потоков 
принято называть синхронизацией.

### Критические секции

Базовый инструмент синхронизации работы потоков состоит в создании 
критических секций. Таких блоков кода, в которые будет невозможен
одновременный доступ сразу нескольких потоковых сущностей.

Критическая секция объявляется с использованием ключевого слова 
`synchronized` и определения объекта, который будет выступать в роли 
"монитора" критической секции.
```
class Task implements Runnable {
    
    private static finale Object MONITOR = new Object();

    @Override
    public void run() {
        synchronized(MONITOR) {
            // критическая секция
        }
    }
}
```
В многих случаях, монитором критической секции является сама потоковая
сущность.
```
class Task implements Runnable {
    
    @Override
    public void run() {
        synchronized(this) {
            // критическая секция
        }
    }
}
```
Такой код может быть заменён синхронизированным методом, дающим 
эквивалентный результат.
```
class Task implements Runnable {
    
    @Override
    public sybchronized void run() {
        // синхронизированный метод
    }
}
```
Для синхронизации работы потоков можно использовать методы класса 
`Object`: `wait` и `notify`. Описанные методы должны вызываться только 
в пределах критических секций. Кроме того, метод `wait` может привести
к возникновению исключительной ситуации `InterruptedException`, которую
необходимо контролировать.
```
class Task implements Runnable {
    
    private static finale Object MONITOR = new Object();

    @Override
    public void run() {
        try {
            synchronized(MONITOR) {
                // критическая секция
                MONITOR.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }
}
```
### Семафоры

Для упрощения процесса синхронизации используют примитивы синхронизации.
Основным примитивом синхронизации является класс `Semaphore` из пакета
`java.util.concurrent`. Семафор позволяет определить, какое количество
потоков сможет одновременно выполнять код, находящийся между вызовами 
методов `acquire` и `release`. 

Параметр конструктора семафора определяет количество разрешённых потоков.
Метод `acquire` запрашивает некоторое количество токенов. Если в семафоре
не осталось свободных токенов, то вызвавший метод поток будет ожидать,
пока в семафоре не появятся свободные токены. Метод `release` освобождает
токены, которые могут в дальнейшем быть заняты ожидающими потоками.

Семафор на один токен называют "Мьютексом" (от английского выражения
Mutual Exclusion - Взаимное Исключение), поскольку только один поток 
разрешён к исполнению.
```
class Task implements Runnable {

    private static final Semaphore SEMAPHORE = new Semaphore(1);

    @Override
    public void run() {
        try {
            synchronized(SEMAPHORE) {
                SEMAPHORE.acquire();
                // критическая секция
                SEMAPHORE.release();
            }
        } catch(InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }
}
```

## Дополнительная информация

Более подробная информация может быть найдена на сайте 
[Oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html).
