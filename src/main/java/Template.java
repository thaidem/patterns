//Шаблонный метод (англ. Template method) — поведенческий шаблон проектирования,
// определяющий основу алгоритма и позволяющий наследникам переопределять некоторые шаги алгоритма,
// не изменяя его структуру в целом.

//Применимость: Однократное использование инвариантной части алгоритма, с оставлением изменяющейся части
// на усмотрение наследникам.
//Локализация и вычленение общего для нескольких классов кода для избегания дублирования.
//Разрешение расширения кода наследниками только в определенных местах.

class MainTemplate {
  public static void main(String[] args) {
    Beverage beverage = new Coffee();
    Beverage beverage2 = new Tea();
    beverage.makeBeverage();
    beverage2.makeBeverage();

  }
}

class Coffee extends Beverage{
  @Override
  void addBeverage() {
    System.out.println("add coffee");
  }

  @Override
  void hook() {
    System.out.println("add syrop");
  }

  @Override
  void addCondiment() {
    System.out.println("add milk");
  }
}

class Tea extends Beverage {
  @Override
  void addBeverage() {
    System.out.println("add tea");
  }
  @Override
  void addCondiment() {
    System.out.println("add lemon");
  }
}

abstract class Beverage {
  private void boilWater() {
    System.out.println("boiled water");
  }
  abstract void addBeverage();

  private void addSugar() {
    System.out.println("add sugar");
  }
  abstract void addCondiment();

  public void makeBeverage() {
    boilWater();
    addBeverage();
    addSugar();
    addCondiment();
    hook();
  }
  void hook() {};
}