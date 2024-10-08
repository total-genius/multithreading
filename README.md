# Multithreading
Многопоточность - это принцип построения программы, при котором
несколько блоков кода могут выполняться одновременно.

**Основные цели:**
- Повышение производительности
- Concurrency - одновременное выполнение нескольких задач

**Context switch** - когда все потоки работают на одном ядре
и ядро периодически переходит от одной задачи к другой.

### Способы создания потоков
Создать потоки можно несколькими способами:

1. Наследование нашего класса от класса Thread:

Пример:
- [MyThreadExample1](com/angubaidullin/_2_thread_creating_methods/_1_thread_extending/MyThreadExample1.java)
- [MyThreadExample2](java/com/angubaidullin/_2_thread_creating_methods/_1_thread_extending/MyThreadExample2.java)
- [ThreadCreatingMethodExample](java/com/angubaidullin/_2_thread_creating_methods/_1_thread_extending/ThreadCreatingMethodExample.java)

2. Реализация интерфейса Runnable (Thread - класс тоже его реализует)

Пример:
- [MyRunnableThreadExample1](java/com/angubaidullin/_2_thread_creating_methods/_2_runnable_using/MyRunnableThreadExample1.java)
- [RunnableUsingExample](java/com/angubaidullin/_2_thread_creating_methods/_2_runnable_using/RunnableUsingExample.java)

Затем создаем объекты типа Thread и передаем в их аргументы наши объекты типа Runnable

!!!Когда мы запускаем приложение, у нас уже создается одни поток - поток `main`. Когда мы запускаем
новые потоки, эти потоки отпачковываются от потока main и потом работают независимо от него.


### Имена потоков и их приоритет
При создании потоков им назначаются имена следующим образом `Thread-0, Thread-1, .... , Thread-n`
Мы можем задать свои имена потокам через метод `thread1.setName("first thread");`

Также у потоков имеется приоритет от 1 до 10 (по умолчанию стоит 5).
Можно поменять приоритет у потока с помощью следующих методов:
- `thread2.setPriority(8);` - ставим любое интовое значение от 1 до 10
- `thread1.setPriority(Thread.MIN_PRIORITY);` - минимальный приоритет (1)
- `thread1.setPriority(Thread.NORM_PRIORITY);` - максимальный приоритет (10)
- `thread1.setPriority(Thread.MAX_PRIORITY);` - средний приоритет (5)

Нет никаких гарантий, что поток с более высоким приоритетом выполнится быстрее, чем поток
с более низким приоритетом.

Пример:
- [MyThreadNamePriorityExample](java/com/angubaidullin/_3_thread_name_and_thread_priority/MyThreadNamePriorityExample.java)
- [ThreadNameThreadPriorityExample](java/com/angubaidullin/_3_thread_name_and_thread_priority/ThreadNameThreadPriorityExample.java)

### Состояние потока
1. new - поток создан, но еще не запущен до вызова метода `start()`
2. runnable - после вызова метода `start()` - состояние выполнения
    - ready - готов. Ждет процессорное время
    - running - поток выполняется
3. terminated - работа потока завершена

```java

public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " - in this thread has just started main method");
        System.out.println(Thread.currentThread().getName() + " - state: " + Thread.currentThread().getState());

        Thread t = new Thread(() -> {
            System.out.println("Some work has just begun in thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Some work has just ended in thread " + Thread.currentThread().getName());
        });

        System.out.println(t.getName() + " - state: " + t.getState());

        t.start();
        t.join();

        System.out.println(t.getName() + " - state: " + t.getState());
        System.out.println(Thread.currentThread().getName() + " - in this thread has just ended main method");
    }
}

```

### Методы `sleep()` и `join()`
Метод `sleep(millis)` - метод отправляет поток, в котором, он вызван в "спячку" на указанное в аргументах время

Примеры:
- [SleepMethodExample](java/com/angubaidullin/_4_sleep_join_methods_thread_states/_1_sleep_method/SleepMethodExample.java)
- [SleepMethodExample2](java/com/angubaidullin/_4_sleep_join_methods_thread_states/_1_sleep_method/SleepMethodExample2.java)


Метод `join()` - поток, в котором вызван метод `join()` будет ждать поток или потоки
на которых вызван этот метод

Примеры:
- [JoinMethodExample](java/com/angubaidullin/_4_sleep_join_methods_thread_states/_2_join_method/JoinMethodExample.java)
- [JoinMethodExample2](java/com/angubaidullin/_4_sleep_join_methods_thread_states/_2_join_method/JoinMethodExample2.java)

### Concurrency, Parallelism, Synchronous, Asynchronous

1. Concurrency - согласованность - работа происходит согласованно но не параллельно
например, при наличии лишь одного ядра у процессора, параллельная работа происходит
с помощью технологии context switch
2. Parallelism - работа происходит параллельно (одновременно выполняются 2 задачи)
3. Synchronous - последовательное выполнение работы (одна строка кода за другой). Сначала ждем, когда выполнится
одна задачу, потом переходим ко второй.
4. Asynchronous - не нужно ждать пока выполнится одна задача, чтобы перейти ко второй. Можно сразу переходить
ко второй задаче

Синхронное программирование позволяет достичь Concurrency

Асинхронное программирование (при наличии более одного ядра в процессоре) помогает достичь праллельности

### Volatile
Создадим класс реализующий Runnable. Внутри метода `run()`  напишем
цикл while, который будет контролироваться перменной flag (пока flag == true
цикл работает). Создадим и запустим поток. Изначально flag = true. Затем попоробуем
изменит состояние флага извне, чтобы остановить цикл.

```java

public class MyVolatileThreadExample implements Runnable {
    private boolean flag = true;
    private int counter;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started it's work");
        while (flag) {
            counter++;
        }
        System.out.println(Thread.currentThread().getName() + " finished it's work");
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main start");
        MyVolatileThreadExample myVolatileThreadExample = new MyVolatileThreadExample();
        Thread t1 = new Thread(myVolatileThreadExample);
        t1.start();
        Thread.currentThread().sleep(1500);
        myVolatileThreadExample.setFlag(false); - меняем флаг и цикл должен пректратить работу
        t1.join();
        System.out.println("Main end");
    }
}

```

Однако цикл работу свою не прекратит. Почему?

В многопоточный программах, каждый поток может скопировать значение переменной из общей памяти (main memory)
в кеш (cache cpu). Это сделано для улучшение производительности.

У нас имеется два потока - t1 и main. В потоке main мы изменили значение переменной флага на false.
Это значение хранится в кеше потока main. Неизвестно, когда это значение из кеша потока попадет в общую
память и второй поток прочтет ее оттуда.

`private volatile boolean flag ;` - говорит о том, что значение этой переменной будет хранится только
в общей памяти.

- [MyVolatileThreadExample](java/com/angubaidullin/_6_volatile/MyVolatileThreadExample.java)
- [VolatileExample](java/com/angubaidullin/_6_volatile/VolatileExample.java)

Для синзронизации значения переменной между потоками volatile используется тогда,
когда только один поток может изменять значение этой переменной, а остальные потоки
могут его только читать.

Почему не стоит использовать volatile для переменной, которая будет изменяться двумя потоками?
Например, у нас имеется переменная, помеченная как volatile. Два потока совершают над ней
операцию инкремента. Это операция не является атомарной, а состоит из:
1. прочесть значение
2. увеличить значение на 1
3. записать значение в общую память

Оба потока выполняют эту операцию. Может возникнуть ситуация, когда переменная уже стала равна 5 и один
из потоков прочел это значение. Второй поток тоже читает это значение. Первый поток увеличил 5 на 1 и второй
поток увеличил 5 на 1. Значение равно 6, должно было получится 7. Здесь нужно использовать другие механизмы
синхронизации.

### Data race и synchronized методы
Создадим класс Counter. Класс будет содержать статическую переменную, хранящую число и статические
методы для получения числа и его увеличение на значение, переданное в парметры.

```java

public class Counter {
    private volatile static int count;
    public static int getCount(){
        return count;
    }
    public static void setCount(int n) {
        Counter.count += n;
    }
}

```
Создадим DataRaceAndSynchronizedThread implements Runnable. В его методе run значение из класса Counter
в цикле увеличивается на 1.

```java

public class DataRaceAndSynchronizedThread implements Runnable {

    public void increment(int n) {
        Counter.setCount(n);
        System.out.print(Counter.getCount()+" ");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            increment(1);
        }
    }
}

```

Создадим 3 потока и в каждый передадим объект типа class DataRaceAndSynchronizedThread implements Runnable.

```java

public class DataRaceAndSynchronizedThreadExample {
    public static void main(String[] args) {
        DataRaceAndSynchronizedThread runnable = new DataRaceAndSynchronizedThread();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();

    }
}

```

На выходе получим непредсказуемый результат

Это говорит о том, что потоки работают не синхронно. Значения записанные одним потоком, могут
в дальнейшем перезаписаться другим потоком, что приводит к непредсказуемым результатам, особенно,
когда несколько потоков работают с одними, общими, данными. volatile здесь не подходит.

Это ситуация называется Data Race - гонка данных. Это проблема, которая может возникнуть
когда два и более потоков обращаются к одной и той же переменной и как минимум 2 потока ее изменяет.

Как это исправить и добиться синхронной работы над данными несколькими потоками?
Используем ключевое слово synchronized - это говорит о том, что с методом, помеченным как synchronized
в один момент времени может работать лишь один поток.

Пример:
- [DataRaceAndSynchronizedThread](java/com/angubaidullin/_7_data_race_and_synchronized_methods/DataRaceAndSynchronizedThread.java)

Теперь всегда будет выводится следующий результат: 1 2 3 4 5 6 7 8 9

### Monitor и синхронизированные блоки
Монитор - специальный механизм, с помощью которого достигается корректная работа синхронизации.
У каждого класса и объекта имеется монитор. У него есть 2 состояния:
- свободен
- занят

Когда один поток заходит в область кода, помеченный как синхронизированный, монитор
объекта или класса принимает состояние "занят". Монитор в один момент времени может
быть занят лишь одним потоком. Остальные потоки ждут. Происходит Lock.

Любая блокировка с помощью синхронизации идет на объекте или классе.

Синхронизированне блоки - синхронизирует не весь код в методе, а лишь какую-то его часть.

Если метод нестатический - идет синхронизация на объекте (this)
Если метод статический - на классе

Синхронизируем работу нескольких методов на одном мониторе, чтобы в один момент времени мог
работать лишь один метод:
- [CallMethods](java/com/angubaidullin/_8_monitor_and_synchronized_blocks/_2_synchronized_block_example_2/CallMethods.java)
- [SynchronizedBlockExample2](java/com/angubaidullin/_8_monitor_and_synchronized_blocks/_2_synchronized_block_example_2/SynchronizedBlockExample2.java)

Нельзя синхронизировать конструкторы. JVM гарантирует, что в один момент времени
контруктор будет выполняться лишь в одном потоке

### Методы wait() и notify()
Иногда, при работе с несколькими потоками, встает вопрос об извещении одних потоков
о завершении работы других потоков.

- `wait()` - освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор, пока
другой поток не вызовет метод `notify()`
- `notify()` - НЕ освобождает монитор, но будит поток, у которого ранее был вызван метод `wait();`
- `notifyAll()` - НЕ освобождает монитор, но будит все потоки, у которых ранее был вызван метод
`wait();`

Пример:
- [Market](java/com/angubaidullin/_9_wait_and_notify_methods/Market.java)
- [WaitNotifyMethodsExample](java/com/angubaidullin/_9_wait_and_notify_methods/WaitNotifyMethodsExample.java)


  Нужно помнить о том, что данные методы используются в контексте синхронизированных блоков кода
  или методов. Они вызываются на объекте лока. Например, если бы мы использовали лок объект для
  синхронизации, то мы вызывали бы методы на нем. Вот так:

```java

public class Market {
    private static final Object lock = new Object();
    private int breadCount;

    public void getBread() {
        synchronized (lock) {
            while (breadCount < 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            breadCount--;
            System.out.println("Client bought 1 bread");
            System.out.println("bread left: " + breadCount);
            lock.notify();
        }

    }
}


 while (breadCount < 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
 }
 
```

метод `wait()` рекомендуется вызывать внутри цикла while() и не рекомендуется использовать
условные операторы, например `if`, потому что в редких случая поток может "проснуться" не
дождавшись метода `notify()`

Также метод `wait(millis);` принимает в параметры время, которое поток будет находится в спящем
состоянии. Он заново "проснется" либо при вызове метода `notify()`, либо по истичению указанного
в агрументах времени.

### Deadlock & Livelock
DeadLock - ситуация, когда 2 или более потоков залочены навсегда,
ожидают друг друга и ничего не делают

Пример кода, где может произойти deadlock:

```java

public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {

        Runnable thread1 = new Runnable() {

            @Override
            public void run() {
                System.out.println("thread1 : try to get lock1");
                synchronized (lock1) {
                    System.out.println("thread1 :  got lock1");
                    System.out.println("thread1 : try to get lock2");
                    synchronized (lock2) {
                        System.out.println("thread1 : got lock2 and lock1");
                    }
                    System.out.println("thread1 : exit lock2");
                }
                System.out.println("thread1 : exit lock1");
            }
        };

        Runnable thread2 = new Runnable() {

            @Override
            public void run() {
                System.out.println("thread2 : try to get lock2");
                synchronized (lock2) {
                    System.out.println("thread2 : got lock2");
                    System.out.println("thread2 : try to get lock1");
                    synchronized (lock1) {
                        System.out.println("thread2 : got lock1 and lock2");
                    }
                    System.out.println("thread2 : exi t lock1");
                }
                System.out.println("thread2 : exit lock2");
            }
        };

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        t1.start();
        t2.start();

    }
    
```

Причина: мы используем 2 объекта для локов в двух потоках, но используем их не в одинаковом порядке.
Это может привести в к тому, что поток1 захватит сначала лок1, а поток2 - лок2. При этом потоку1 нужен
для дальнешей работы лок2 потоку2 - лок1. Пока они не получат эти локи, захваченные локи они освободить
не могут. Выходит что программа "встает" и потоки ждут пока другой освободит лок.

Как избежать? Использовать одинаковый порядок захвата локов во всех потоках

Пример:
- [DeadLockExample](java/com/angubaidullin/_10_deadlock_livelock_lock_starvation/_1_deadlock_example/DeadLockExample.java)

Livelock - ситуация, когда 2 или более потоков залочены навсегда, ожидают друг друга, проделывают
какую-то работу, но без какого-либо прогресса

Lock starvation - ситуация, когда менее приоритетные потоки долгое время или все время ждут того,
когда можно будет запуститься (более приоритетные потоки постоянно перехватывают процессорное
время)

### Lock & ReentrantLock
**Lock** - интерфейс, который имплементируется классом ReentrantLock. Так же,
как и ключевое слово synchronized, Lock нужен для достижения синхронизации
между потоками.

Пример:
- [LockExample](java/com/angubaidullin/_11_lock_reentrant_lock/_1_lock_example/LockExample.java)
- [PhoneCall](java/com/angubaidullin/_11_lock_reentrant_lock/_1_lock_example/PhoneCall.java)

```java

finally {
            lock.unlock();  
        }

```
открываем лок в finally для избежания случаев, когда выбросилось исключение и до строки кода,
где происходит открытие лока дело не дошло. Тогда лок будет закрыт и другие потоки не смогут воспользоваться
залоченным методом. (при использовании synchronized об открытии локов заботиться не нужно)

#### Преимущества Lock
- метод `tryLock()` - если замок открыт, сделается лок. Если замок закрыт он продолжает выполнять последующий код
   (тот, который вне `lock.lock() .... lock.unlock()`)

Пример:
- [Employee](java/com/angubaidullin/classes_for_demonstrate_examples/Employee.java)
- [ATM](java/com/angubaidullin/_11_lock_reentrant_lock/_2_lock_example/ATM.java)

### Daemon threads
Daemon потоки - (до этого все потоки, которые мы создавали, назывались user threads)
предназначены для выполнения фоновых задач и оказания сервисов user потокам.
При завершении работы последнего user потока программа завершает свое выполнение,
не дожидаясь окончания работы daemon потоков.

#### Как создать?
- `isDaemon();` - метод проверяет, является ли поток демон потоком или нет
- `setDaemon(true);` - с помощью данного метода мы указываем, поток является демон потоком.

!!! Важно объявлять поток демон потоком после его создания, но перед его запуском иначе
получим исключение.

Пример:
- [DaemonThreadsExample](java/com/angubaidullin/_12_daemon_threads/DaemonThreadsExample.java)

### Прерывание потоков
`interruptedThread.interrupt();` - данный метод не прервет поток. Однако внутри самого потока, можно проверить, хотят ли
его прервать во внешних потоках. Если внутри потока мы произведем такую проверку и при этом вешний поток вызовет
вышеописанный метод, то поток увидит, что его хотят прервать.

Пример:
- [InterruptionExample](java/com/angubaidullin/_13_threads_interrupting/InterruptionExample.java)

Когда мы получаем информацию с помощью метода `isInterrupted()` о том, что нас хотят прервать, мы вольны решать, что
делать дальше: можно прервать работу потока, можно проигнорировать сигнал или выполнить другие действия,  отличные от
тех, которые выполняются когда поток прерывать не хотят.

Такие методы как `sleep() и wait()` выбрасывают исключение `InterruptedException`. Это исключение выбрасывает тогда, когда
наш поток находит в ожидании или спящем состоянии и его в этот момент хотят прервать. Это сделано для того, чтобы сразу
отреагировать тем или иным способом на сигнал о том, что спящий поток хотят прервать, а не ждать когда он "проснется"

### ThreadPool and ExecutorService
Thread Pool - множество потоков, каждый из которых предназначен для выполнения
той или иной задачи. Можно предствить это как контейнер, где находятся потоки.
И мы отправляем задачи на выполнение к этим потокам. Thread Pool внутри
себя сам решает, как распределить задачи по потокам. После выполнения задачи,
поток в контейнере не закрывается, он ждет когда получит новое задание и выполняет его.

В Java мы работаем с Thread Pool через объекты типа Executor

Thread Pool - удобно создавать, используя фабричные методы класса Executors

Пример:
- [ThreadPoolExample1](java/com/angubaidullin/_14_threadpool_and_executorservice/_1_thread_pool_executor_service_example/ThreadPoolExample1.java)

`execute()` - отправляет задачу на выполнение в thread pool. После выполнения всех задач Thread Pool
не завершит свою работу автоматически. Он будет ждать новых задач.
Для того, чтобы указать Thread Pool, что новых задач не будет и нужно завершить работу, используется
метод `shutdown()`

`awaitTermination(5, TimeUnit.SECONDS);` - работает как метод `join()`, он заставляет поток в котором
он вызван, ждать окончания работы Thread Pool, или не пройдет указанное время.


ScheduledExecutorService мы используем тогда, когда хотим установить расписание на запуск потоков
из пула.

Сервис выполнит переданное ему задание через 3 секунды

```java

    executorService.schedule(task, 3, TimeUnit.SECONDS);
    executorService.shutdown();

```

Задача начнет выпонение через 3 секунды и будет выполнятся с периодичность в 1 секунду.
```java

executorService.scheduleAtFixedRate(task, 3, 1, TimeUnit.SECONDS);
Thread.sleep(8000);
executorService.shutdown();

```
Период (который мы установили в 1 секунду) это промежуток времени мужду началом выполнения одной задачи и
началом выполнения последующей задачи

```java

executorService.scheduleWithFixedDelay(task, 3,1,TimeUnit.SECONDS);
        Thread.sleep(8000);
        executorService.shutdown();
        
```

Принимает такие же параметры как и метод выше, но период (1 секунда) - это время между концом одной задачи и началом
выполнения последующей.

Пример:
- [ScheduledExecutorServiceExample](java/com/angubaidullin/_14_threadpool_and_executorservice/_2_scheduled_executor_service/ScheduledExecutorServiceExample.java)

`ExecutorService service = Executors.newCachedThreadPool();` - кешированный пул потоков. Он будет создавать
в себе потоки по надобности. Например, пришло задание, пул создал поток для этого задания. Пришло еще одно задание
но первый поток не завершил работу. Тогда создастя новый поток. Пришло третье задание и первый поток освободился. Тогда
это задание пердастся первому потоку. Пришло четвертое задание, но первые два потока еще заняты. Тогда создастся третий
поток для выполнения этого задания. Если новые задания не поступают и потоки простаивают, то по истечению определенного
времени они удаляются.

### Callable and Future
Метод run() из интерфейса Runnable ничего нем не возвращает. А что, если мы хотим получить результат
работы нашего потока? 

**Для этого есть интерфейс Callable:**
```java
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
```

С помощью его метода мы можем вернуть какой-то результат из потока:
- [CallableFactorial](java/com/angubaidullin/_15_callable_and_future/_1_callable_future_factorial_example/CallableFactorial.java)
- [CallableFutureExample](java/com/angubaidullin/_15_callable_and_future/_1_callable_future_factorial_example/CallableFutureExample.java)
- [Factorial](java/com/angubaidullin/_15_callable_and_future/_1_callable_future_factorial_example/Factorial.java)

У ExecutorService имеется специальный метод `<T> Future<T> submit(Callable<T> task)`, который позволяет нам
работать с объектами типа Callable. На выходе мы получим объект типа Future, который внутри себя
хранит результат работы наго потока. Это результат можно получить с помощью метода `get()`, который мы вызываем
на объекте Future.

Могут возникнуть ситуаци что поток еще не завершил свою работу и не может пока вернуть результат работы, а другом
потоке в этот момент вызывается метод `get()` для получения результата из первого потока. В таком случае метод поток, в
котором вызван метод `get()` будет ждать окончания работы того потока, для которого был вызван метод `get()`, чтобы получить
результат работы этого потока и дальше продолжить свою работу (одновременно сработает как `join()`)

### Semaphore
Semaphore - это синхронизатор, позволяющий ограничить доступ
к какому-то ресурсу. В конструктор Semaphore нужно передавать
количество потоков, которым Semaphore будет разрешать одновременно
использовать этот ресурс.

- [Person](java/com/angubaidullin/_16_semaphore/Person.java)
- [SemaphoreExample1](java/com/angubaidullin/_16_semaphore/SemaphoreExample1.java)

### CountDownLatch
CountDownLatch - это синхронизатор, позволяющий любому количеству
потоков ждать пока не завершится опеределенное количество операций.
В конструктор CountDownLatch нужно передать количество операций,
которые должны завершиться, чтобы потоки продолжили свою работу.

- [Client](java/com/angubaidullin/_17_count_down_latch/Client.java)
- [CountDownLatchExample](java/com/angubaidullin/_17_count_down_latch/CountDownLatchExample.java)

### Exchanger
Exchanger - это синхронизатор, позволяющий обмениваться
данными между двумя потоками, обеспечивает то, что оба потока
получат информацию друг от друга одновременно

- [Action](java/com/angubaidullin/_18_exchanger/Action.java)
- [ExchangerExample](java/com/angubaidullin/_18_exchanger/ExchangerExample.java)
- [Player](java/com/angubaidullin/_18_exchanger/Player.java)

### AtomicInteger
AtomicInteger - это класс, который предоставляет возможность
работать с целочисленным значением int используя атомарные операции

- [AtomicIntegerExample](java/com/angubaidullin/_19_atomic_integer/AtomicIntegerExample.java)

Методы класса AtomicInteger:
- `incrementAndGet();`
- `getAndIncrement();`
- `addAndGet();`
- `getAndAdd();`
- `decrementAndGet();`


### Синхронизированные коллекции для работы с многопоточностью
Synchronized collections - получаются из традиционных коллекций
благодаря их обертыванию:
- `Collections.synchronized{XYZ}(some collection)`

Вместо {XYZ} будет Map, Set, List в зависимости от типа коллекции

Работа с несинхронизированными коллекциями в разных потоках:
```java

public static void main(String[] args) throws InterruptedException {
        List<Integer> source = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            source.add(i);
        }

        List<Integer> target = new ArrayList<Integer>();

        Runnable runnable = ()->target.addAll(source);

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(target);
        //результаты вывода:
        //1. [0, 1, 2, 3, 4, 0, 1, 2, 3, 4]
        //2. [0, 1, 2, 3, 4]
        //Получается, что при множественном выполнении одного и того кода, результат может быть разный
}

```

Теперь синхронизируем нашу коллекцию:
- [SynchronizedCollectionExample1](java/com/angubaidullin/_20_synchronized_and_concurrent_collections/_1_synchronized_collection_example/SynchronizedCollectionExample1.java)


```java

public class SynchronizedCollectionExample2 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        Runnable runnable = ()->{
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            list.remove(20);
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(list);

        //Здесь легко получить исключение
        //Потому один поток итерируется по листу, а другой производит в нем изменения
        //путем удаления элемента. А изменения коллекции нельзя производить при итерации
        //по ней fail-fast итератором


    }
}

```
Синхронизируем и этот код:

```java

public class SynchronizedCollectionExample2 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        List<Integer> syncList =
                Collections.synchronizedList(list);

        Runnable runnable = ()->{
            Iterator<Integer> iterator = syncList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            syncList.remove(20);
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(syncList);

        //Все равно выскочит исключение
        //Итераторы подвержены сбоям при работах в многопоточном приложении
        //Если один поток перебирает лист и другой его изменяет
        //то при вызове метода итератора после изменения коллекци не методом итератора в потоке, где
        //происходт итерация, выскочит исключение (fail-fast итераторы)
        //Чтобы обезопасить приложение от исключения нужно целиком блокировать коллекцию
        //на время ее перебора


    }
}

```
Исправляем:
```java

Runnable runnable = ()->{
            synchronized (syncList) {
                Iterator<Integer> iterator = syncList.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
};


```
- [SynchronizedCollectionExample2](java/com/angubaidullin/_20_synchronized_and_concurrent_collections/_2_synchronized_collection_example/SynchronizedCollectionExample2.java)

### Concurrent collections
Concurrent collections - изначально созданы для работы с многопоточностью

1. ConcurrentHashMap - имплементирует интерфейс ConcurrentMap, который
   в свою очередь происходит от Map. В ConcurrentHashMap любое количество потоков может читать
   информацию без каких-либо локов. Также ConcurrentHashMap делит множество элементов
   которые он хранит на сегменты. Сегменты - бакеты. Одновременно несколько потоков могут
   производить изменение данных в разных сегментах, НО! не в одних и тех же. Лок происходит по сегментам.
   При использовании SynchronizedHashMap блокироваться будет вся коллекция целиком.

Пример неправильной работы с мапой в многопоточности:

```java

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");

        Runnable r1 = ()-> {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Integer key = iterator.next();
                System.out.println(key + ": " + map.get(key));

            }
        };

        Runnable r2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.put(8, "eight");

        };

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    //Получим исключение
    //причина та же, что и в примере с List
}

```

Исправляем:

```java

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");

        Runnable r1 = ()-> {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Integer key = iterator.next();
                System.out.println(key + ": " + map.get(key));

            }
        };

        Runnable r2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.put(8, "eight");

        };

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

```

ConcurrentHashMap в Java использует fail-safe итератор.

Объяснение:
Fail-safe означает, что итератор не выбрасывает исключение ConcurrentModificationException,
если структура данных изменяется (добавляются, удаляются, или изменяются элементы) во время итерации.
Это достигается тем, что итератор работает с "снимком" (snapshot) состояния структуры на момент
начала итерации.

В случае с ConcurrentHashMap, итератор не видит структурных изменений, сделанных другими
потоками после начала итерации, но может видеть изменения в значениях уже существующих ключей.
Это позволяет избежать проблем при многопоточном доступе, делая ConcurrentHashMap подходящей для
использования в конкурентной среде.

Таким образом, ConcurrentHashMap обеспечивает безопасное и предсказуемое поведение при итерации в
многопоточном окружении, используя fail-safe итератор.

В ConcurrentHashMap ни key, ни value не могут быть null

2. CopyOnWriteArrayList - реализует List. Его следует использовать тогда, когда нужно
   добиться потокбезопасности при этом количество оперций по изменению немного, а операций по чтению
   много.

Тоже имеет fail-safe итератор

При каждом изменении коллекции создается клон копии листа нового вида.

```java

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        System.out.println(list);

        Runnable runnable = ()->{
            Iterator<String> iterator = list.iterator();
            while(iterator.hasNext()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = ()->{
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.remove(2);
            list.add("H");
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread2.join();
        thread1.join();
        System.out.println(list);
    }
}

```

3. ArrayBlockingQueue - потокобезопасная очередь с ограниченным размером (capacity restricted)

Обычно один или несколько потоков добавляют элементы в конец очереди, а другой или другие потоки
забирают элементы из начала очереди

```java

 ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        queue.add(5); при таком добавлении может быть исключение когда очередь полностью заполнена
        //а мы пытаемся добавить туда еще элементы

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);//при добавление с помощью этого метода исключения не будет
        //просто элемент, который уже не влезает, не добавится

```

у ArrayBlockingQueue есть два интересных метода:
- `put()` - кладет данные в очередь, но если очередь полная, ждет, когда появится место, чтобы положить
туда данные.
- `take()` - забирает данные из очереди, если очередь пустая, ждет, когда там появятся данные.

```java

public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);

        //Producer
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    queue.put(++i);
                    System.out.println("Producing: " + i);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        //Consumer
        new Thread(() -> {

            while (true) {
                try {
                    Integer i = queue.take();
                    System.out.println("Consuming: " + i);
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

```













