//Декоратор (англ. Decorator) — структурный шаблон проектирования,
// предназначенный для динамического подключения дополнительного поведения к объекту.
// Шаблон Декоратор предоставляет гибкую альтернативу
// практике создания подклассов с целью расширения функциональности.

class MainDecorator {
  public static void main(String[] args) {
    Pizza cheesePizza = new CheesePizza(new DoughPizza());
    Pizza pepperoniPizza = new PepperoniPizza(cheesePizza);
    Pizza meatPepperoniCheesePizza = new MeatPizza(pepperoniPizza);
    System.out.println(meatPepperoniCheesePizza.makePizza());

  }
}

interface Pizza {
  String makePizza();
}

class DoughPizza implements Pizza {
  @Override
  public String makePizza() {
    return "with ";
  }
}

class CheesePizza implements Pizza {
  Pizza pizza;

  public CheesePizza(Pizza pizza) {
    this.pizza = pizza;
  }

  @Override
  public String makePizza() {
    return pizza.makePizza() + " cheese";
  }
}

class PepperoniPizza implements Pizza {
  Pizza pizza;

  public PepperoniPizza(Pizza pizza) {
    this.pizza = pizza;
  }

  @Override
  public String makePizza() {
    return pizza.makePizza() + " pepperoni";
  }
}

class MeatPizza implements Pizza {
  Pizza pizza;

  public MeatPizza(Pizza pizza) {
    this.pizza = pizza;
  }

  @Override
  public String makePizza() {
    return pizza.makePizza() + " meat";
  }
}

