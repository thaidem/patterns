//Наблюдатель (англ. Observer) — поведенческий шаблон проектирования. Также известен как «подчинённые»
// (Dependents). Реализует у класса механизм, который позволяет объекту этого класса получать оповещения
// об изменении состояния других объектов и тем самым наблюдать за ними.

//Классы, на события которых другие классы подписываются, называются субъектами (Subjects),
// а подписывающиеся классы называются наблюдателями (Observers).

//Назначение: Определяет зависимость типа один ко многим между объектами таким образом,
// что при изменении состояния одного объекта все зависящие от него оповещаются об этом событии.



import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

class MainObserver {
  public static void main(String[] args) {
    Subject subject = new Subject();
    Subscriber1 subscriber1 = new Subscriber1();
    subject.subscribe(subscriber1);
    subject.subscribe(new Subscriber2());
    subject.notifySubscribers("hello world");
    subject.unsubscribe(subscriber1);
    subject.notifySubscribers("hello world");

    Subject2 subject2 = new Subject2();
    subject2.addObserver(new Subscriber3());
    subject2.addObserver(new Subscriber4());
    subject2.setChanged();
    subject2.notifyObservers("hi people");
  }
}

class Subject2 extends Observable {
  @Override
  protected synchronized void setChanged() {
    super.setChanged();
  }
}

class Subscriber3 implements Observer {
  @Override
  public void update(Observable o, Object arg) {
    System.out.println("s3 " + arg);
  }
}

class Subscriber4 implements Observer {
  @Override
  public void update(Observable o, Object arg) {
    System.out.println("s4 " + arg);
  }
}

interface MyObservable {
  void callMe(String msg);
}

class Subscriber1 implements MyObservable {
  @Override
  public void callMe(String msg) {
    System.out.println("s1 " + msg);
  }
}

class Subscriber2 implements MyObservable {
  @Override
  public void callMe(String msg) {
    System.out.println("s2 " + msg);
  }
}


class Subject {
  List<MyObservable> list = new ArrayList<>();

  public void subscribe(MyObservable myObservable) {
    list.add(myObservable);
  }

  public void unsubscribe(MyObservable myObservable) {
    list.remove(myObservable);
  }

  public void notifySubscribers(String msg) {
    list.forEach(item -> item.callMe(msg));
  }

}
