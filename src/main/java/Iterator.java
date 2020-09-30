//Iterator — поведенческий шаблон проектирования. Представляет собой объект,
// позволяющий получить последовательный доступ к элементам объекта-агрегата без использования описаний
// каждого из агрегированных объектов.
//Например, такие элементы как дерево, связанный список, хеш-таблица и массив могут быть пролистаны
// (и модифицированы) с помощью объекта Итератор.
//Перебор элементов выполняется объектом итератора, а не самой коллекцией.
// Это упрощает интерфейс и реализацию коллекции, а также способствует более логичному разделению обязанностей.
//Особенностью полноценно реализованного итератора является то, что код, использующий итератор,
// может ничего не знать о типе итерируемого агрегата.


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

class MainIterator {
  public static void main(String[] args) {
//    Menu menu = new Menu();
////    MyIterator<String> myIterator = menu.getIterator();
//    Iterator<String> myIterator = menu.getIterator();
//    while (myIterator.hasNext()) {
//      System.out.println(myIterator.next());
//    }
//
//    for(String str : new Menu()) {
//      System.out.println(str);
//    }
//    new Menu().forEach(str -> System.out.println(str));

//    List<String> list = new ArrayList<>();
    List<String> list = new CopyOnWriteArrayList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    list.add("four");
//    list.forEach(str -> {
//      System.out.println(str);
//      list.remove(str);
////      list.add(str);
//      System.out.println(list.size());
//    });
//    Iterator<String> iterator = list.iterator();
//    while (iterator.hasNext()) {
//      String next = iterator.next();
//      System.out.println(next);
//      list.remove(next);
////      list.add(next);
//      System.out.println(list.size());
//    }

    HashMap<String, Integer> hashMap = new HashMap<>();
    ConcurrentHashMap<String, Integer>  concurrentHashMap = new ConcurrentHashMap<>();
    hashMap.put("one", 10);
    hashMap.put("two", 20);
    hashMap.put("three", 30);
    concurrentHashMap.put("one", 10);
    concurrentHashMap.put("two", 20);
    concurrentHashMap.put("three", 30);
    Iterator<String> iteratorHashMap = hashMap.keySet().iterator();
    Iterator<String> iteratorConcurrentHashMap = concurrentHashMap.keySet().iterator();

//    while (iteratorHashMap.hasNext()) {
//      System.out.println(hashMap.get(iteratorHashMap.next()));
//    }

    while (iteratorConcurrentHashMap.hasNext()) {
      System.out.println(concurrentHashMap.size());
      String next = iteratorConcurrentHashMap.next();
      System.out.println(concurrentHashMap.get(next));
      System.out.println(next);
      concurrentHashMap.put("four", 40);

    }


  }
}

class Menu implements Iterable<String> {
//  String[] items = {"one", "two"};
  List<String> items = new ArrayList<>();
  public Menu() {
    items.add("one");
    items.add("two");
  }
  Iterator<String> getIterator() {
    return items.iterator();
  }

  @Override
  public Iterator iterator() {
    return items.iterator();
  }

//  MyIterator<String> getIterator() {
//    return new MyIterator<String>() {
//      int i;
//      @Override
//      public boolean hasNext() {
////        return i < items.length;
//        return i < items.size();
//      }
//
//      @Override
//      public String next() {
////        return items[i++];
//        return items.get(i++);
//      }
//    };
//  }
}

interface MyIterator<T> {
  boolean hasNext();
  T next();
}
