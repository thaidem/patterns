//Стратегия (англ. Strategy) — поведенческий шаблон проектирования,
// предназначенный для определения семейства алгоритмов,
// инкапсуляции каждого из них и обеспечения их взаимозаменяемости.
// Это позволяет выбирать алгоритм путём определения соответствующего класса.
// Шаблон Strategy позволяет менять выбранный алгоритм независимо от объектов-клиентов,
// которые его используют.

//Задача: По типу клиента (или по типу обрабатываемых данных) выбрать подходящий алгоритм,
// который следует применить. Если используется правило, которое не подвержено изменениям,
// нет необходимости обращаться к шаблону «стратегия».


class MainStrategy {
  public static void main(String[] args) {
    CarSt car = new Honda(new FlyCar());
    CarSt car2 = new Tractor(new NoFly());
    car.fly();
    car2.fly();

  }
}

interface Flyble {
  void fly();
}

class FlyCar implements Flyble {
  @Override
  public void fly() {
    System.out.println("fly");
  }
}

class NoFly implements Flyble {
  @Override
  public void fly() {
    System.out.println("no fly");
  }
}

abstract class CarSt {
  Flyble flyble;

  public CarSt(Flyble flyble) {
    this.flyble = flyble;
  }
  abstract void run();
  public void stop() {
    System.out.println("stop");
  }
  public void fly() {
    flyble.fly();
  }
}

class Honda extends CarSt {
  public Honda(Flyble flyble) {
    super(flyble);
  }
  @Override
  void run() {
    System.out.println("run honda");
  }
}

class Jeep extends CarSt {
  public Jeep(Flyble flyble) {
    super(flyble);
  }
  @Override
  void run() {
    System.out.println("run jeep");
  }
}

class Tractor extends CarSt {
  public Tractor(NoFly noFly) {
    super(noFly);
  }

  @Override
  void run() {
    System.out.println("run tractor");
  }
}

