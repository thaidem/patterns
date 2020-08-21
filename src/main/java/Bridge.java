//Шаблон мост (англ. Bridge) — структурный шаблон проектирования,
// используемый в проектировании программного обеспечения чтобы «разделять абстракцию и реализацию так,
// чтобы они могли изменяться независимо».
// Шаблон мост использует инкапсуляцию, агрегирование и может использовать наследование для того,
// чтобы разделить ответственность между классами.

class MainBridge {

  public static void main(String[] args) {
    Vehicle vehicle = new Car(new Toyota());
    vehicle.drive();
    Vehicle vehicle2 = new Track(new Audi());
    vehicle2.drive();

  }
}

abstract class Vehicle {

  Model model;

  public Vehicle(Model model) {
    this.model = model;
  }
  abstract void  drive();
}

class Car extends Vehicle {
  public Car(Model model) {
    super(model);
  }

  @Override
  void drive() {
    model.drive("drive car");
  }
}

class Track extends Vehicle {
  public Track(Model model) {
    super(model);
  }

  @Override
  void drive() {
    model.drive("drive track");
  }
}

class Audi implements Model {
  @Override
  public void drive(String str) {
    System.out.println(str + " audi");
  }
}

class Toyota implements Model {
  @Override
  public void drive(String str) {
    System.out.println(str + " toyota");
  }
}

interface Model {

  void drive(String str);
}

