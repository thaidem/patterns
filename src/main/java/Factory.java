//Фабричный метод (англ. Factory Method), или виртуальный конструктор (англ. Virtual Constructor)
// — порождающий шаблон проектирования, предоставляющий подклассам (дочерним классам) интерфейс для создания
// экземпляров некоторого класса. В момент создания наследники могут определить, какой класс создавать.
// Иными словами, данный шаблон делегирует создание объектов наследникам родительского класса.
// Это позволяет использовать в коде программы не специфические классы, а манипулировать абстрактными объектами
// на более высоком уровне.

class MainFactory {
  public static void main(String[] args) {
    AbstractFactory abstractFactory = new MercedesFactory();
    CarF car = abstractFactory.getCar();
    Bike bike = abstractFactory.getBike();
    car.drive();
    bike.drive();
  }
}

interface CarF {
  void drive();
}

interface Bike {
  void drive();
}

class MercedesCar implements CarF {
  public void drive() { System.out.println("drive mercedes car"); }
}

class BmwCar implements CarF {
  public void drive() { System.out.println("drive BMW car"); }
}

class MercedesBike implements Bike {
  public void drive() { System.out.println("drive mercedes bike"); }
}

class BmwBike implements Bike {
  public void drive() { System.out.println("drive BMW bike"); }
}

interface AbstractFactory {
  CarF getCar();
  Bike getBike();
}

class MercedesFactory implements AbstractFactory {
  @Override
  public CarF getCar() {
    return new MercedesCar();
  }

  @Override
  public Bike getBike() {
    return new MercedesBike();
  }
}

class BmwFactory implements AbstractFactory {
  @Override
  public CarF getCar() {
    return new BmwCar();
  }

  @Override
  public Bike getBike() {
    return new BmwBike();
  }
}

//abstract class CarFactory {
//  public void createCar() {
//    CarF car = getCar();
//    car.drive();
//    // more code...
//  }
//  abstract CarF getCar();
//}
//
//class MercedesFactory extends CarFactory {
//  @Override
//  CarF getCar() {
//    return new MercedesCar();
//  }
//}
//
//class BmwFactory extends CarFactory {
//  @Override
//  CarF getCar() {
//    return new BmwCar();
//  }
//}

