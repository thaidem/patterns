//Посетитель (англ. visitor) — поведенческий шаблон проектирования, описывающий операцию,
// которая выполняется над объектами других классов. При изменении visitor нет необходимости
// изменять обслуживаемые классы.
//Шаблон демонстрирует классический приём восстановления информации о потерянных типах,
// не прибегая к понижающему приведению типов при помощи двойной диспетчеризации.

//Решаемая проблема: Необходимо сделать какие-то несвязные операции над рядом объектов,
// но нужно избежать загрязнения их кода. И нет возможности или желания запрашивать тип каждого узла
// и осуществлять приведение указателя к правильному типу, прежде чем выполнить нужную операцию

class MainVisitor {
  public static void main(String[] args) {
    Animal animal = new Dog();
    animal.accept(new SoundVisitor());
    animal.accept(new EatVisitor());

  }
}

interface Animal {
  void accept(AnimalVisitor animalVisitor);
}

class Dog implements Animal {
  @Override
  public void accept(AnimalVisitor animalVisitor) {
    animalVisitor.action(this);
  }
}

class Cat implements Animal {
  @Override
  public void accept(AnimalVisitor animalVisitor) {
    animalVisitor.action(this);
  }
}

interface AnimalVisitor {
  void action(Dog dog);
  void action(Cat cat);

}

class SoundVisitor implements AnimalVisitor {
  @Override
  public void action(Dog dog) {
    System.out.println("wof");
  }

  @Override
  public void action(Cat cat) {
    System.out.println("mau");
  }
}

class EatVisitor implements AnimalVisitor {
  @Override
  public void action(Dog dog) {
    System.out.println("eat meat");
  }

  @Override
  public void action(Cat cat) {
    System.out.println("eat fish");
  }
}

//interface Animal {
//  void makeSound();
//}
//
//class Dog implements Animal {
//  @Override
//  public void makeSound() {
//    System.out.println("wof");
//  }
//}
//
//class Cat implements Animal {
//  @Override
//  public void makeSound() {
//    System.out.println("mau");
//  }
//}
