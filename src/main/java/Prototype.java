//Прототип, (англ. Prototype) — порождающий шаблон проектирования.

//Назначение: Задаёт виды создаваемых объектов с помощью экземпляра-прототипа и создаёт
// новые объекты путём копирования этого прототипа. Он позволяет уйти от реализации и позволяет
// следовать принципу «программирование через интерфейсы». В качестве возвращающего типа указывается
// интерфейс/абстрактный класс на вершине иерархии, а классы-наследники могут подставить туда наследника,
// реализующего этот тип.
//Проще говоря, это паттерн создания объекта через клонирование другого объекта вместо создания через конструктор.

class MainPrototype {
  public static void main(String[] args) throws CloneNotSupportedException {
    Address address = new Address("Lenina", 43);
    PersonProto person = new PersonProto("Mike", 25, address);
//    PersonProto person2 = person.clone();
    PersonProto person2 = new PersonProto(person);
    System.out.println(person != person2);
    System.out.println(person.name == person2.name);
    System.out.println(person.address.street == person2.address.street);

    person.name = "Max";
    System.out.println(person2.name);

    person.address.street = "New";
    System.out.println(person2.address.street);


  }
}

class Address implements Cloneable {
  String street;
  int number;

  public Address(String street, int number) {
    this.street = street;
    this.number = number;
  }

  public Address(Address address) {
    this.street = address.street;
    this.number = address.number;
  }

  @Override
  protected Address clone() throws CloneNotSupportedException {
    return (Address) super.clone();
  }
}

class PersonProto implements Cloneable {
  String name;
  int age;
  final Address address;

  public PersonProto(String name, int age, Address address) {
    System.out.println("constructor1");
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public PersonProto(PersonProto person) {
    System.out.println("constructor2");
    this.name = person.name;
    this.age = person.age;
    this.address = new Address(person.address);
  }

//  @Override
//  protected PersonProto clone() throws CloneNotSupportedException {
//    PersonProto person = (PersonProto) super.clone();
//    person.address = this.address.clone();
//    return person;
//  }
  //  public PersonProto copy(PersonProto person) {
//    return new PersonProto(person.name, person.age);
//  }
}
