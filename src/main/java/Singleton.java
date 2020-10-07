//Одиночка (англ. Singleton) — порождающий шаблон проектирования, гарантирующий,
// что в однопоточном приложении будет единственный экземпляр некоторого класса,
// и предоставляющий глобальную точку доступа к этому экземпляру.

import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class MainSingleton {
//public class Main {
  public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, InterruptedException {

    SingletonWrapper singletonWrapper = new SingletonWrapper();
    SingletonWrapper singletonWrapper2 = new SingletonWrapper();

    Thread thread = new Thread(() -> {
      singletonWrapper.singleton = Singleton.getInstance();
      singletonWrapper.enumSingleton = EnumSingleton.INSTANCE;
    });

    Thread thread1 = new Thread(() -> {
      singletonWrapper2.singleton = Singleton.getInstance();
      singletonWrapper2.enumSingleton = EnumSingleton.INSTANCE;
    });

    thread.start();
    thread1.start();
    thread.join();
    thread1.join();

    System.out.println(singletonWrapper.singleton == singletonWrapper2.singleton);
    System.out.println(singletonWrapper.enumSingleton == singletonWrapper2.enumSingleton);

//    Singleton singleton = Singleton.getInstance();
//    Singleton singleton2 = Singleton.getInstance();
//    System.out.println(singleton == singleton2);
//
//    EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
//    EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE;
//    System.out.println(enumSingleton == enumSingleton2);

//    try(FileOutputStream fileOutputStream = new FileOutputStream("test.doc");
//        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
//        FileInputStream fileInputStream = new FileInputStream("test.doc");
//        ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
//
//      out.writeObject(singleton);
//      singleton = (Singleton)in.readObject();
//      System.out.println(singleton == singleton2);
//
//      out.writeObject(enumSingleton);
//      enumSingleton = (EnumSingleton) in.readObject();
//      System.out.println(enumSingleton == enumSingleton2);
//
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    }
//
//    Constructor <Singleton> constructor = (Constructor <Singleton>)Singleton.class.getDeclaredConstructors()[0];
//    constructor.setAccessible(true);
//    Singleton singleton1 = constructor.newInstance();
//    System.out.println(singleton2 == singleton1);
//
//    Constructor <EnumSingleton> constructor2 = (Constructor <EnumSingleton>)EnumSingleton.class.getDeclaredConstructors()[0];
//    constructor2.setAccessible(true);
//    EnumSingleton enumSingleton1 = constructor2.newInstance();
//    System.out.println(enumSingleton1 == enumSingleton);

//    ClassLoader classLoader = Singleton.class.getClassLoader();
//    Class<?> loadClass = classLoader.loadClass("singleton.Main");
//    Constructor <Singleton> constructor3 = (Constructor <Singleton>)loadClass.getDeclaredConstructors()[0];
//    constructor3.setAccessible(true);
//    Singleton singleton3 = constructor3.newInstance();
//    System.out.println(singleton == singleton3);
//
//    ClassLoader classLoader1 = EnumSingleton.class.getClassLoader();
//    Class<?> loadClass1 = classLoader1.loadClass("singleton.EnumSingleton");
//    EnumSingleton enumSingleton1 = (EnumSingleton) loadClass1.getEnumConstants()[0];
//    System.out.println(enumSingleton1 == enumSingleton);

//    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//    theUnsafe.setAccessible(true);
//    Unsafe unsafe = (Unsafe) theUnsafe.get(null);
//    Singleton singleton4 = (Singleton)unsafe.allocateInstance(Singleton.class);
//    System.out.println(singleton == singleton4);
//
//    EnumSingleton enumSingleton1 = (EnumSingleton)unsafe.allocateInstance(EnumSingleton.class);
//    System.out.println(enumSingleton == enumSingleton1);

  }
}

class SingletonWrapper {
  Singleton singleton;
  EnumSingleton enumSingleton;
}


class Singleton implements Serializable {
  private int i;
  public int getI() {
    return i;
  }

  private volatile static Singleton instance;

  private Singleton() {}

  public static Singleton getInstance() {
    if (instance == null) {
      synchronized (Singleton.class) {
        if (instance == null) {
          System.out.println(Thread.currentThread().getName());
          if (Thread.currentThread().getName().equals("Thread-0")) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          instance = new Singleton();
        }
      }
    }
    return instance;
  }
}

enum EnumSingleton implements Serializable {
  INSTANCE;
  private int i;

  public int getI() {
    return i;
  }
}
