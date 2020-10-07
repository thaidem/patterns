//Состояние (англ. State) — поведенческий шаблон проектирования. Используется в тех случаях,
// когда во время выполнения программы объект должен менять своё поведение в зависимости от
// своего состояния.

//Паттерн состоит из 3 блоков:

//Widget — класс, объекты которого должны менять своё поведение в зависимости от состояния.

//IState — интерфейс, который должен реализовать каждое из конкретных состояний.
// Через этот интерфейс объект Widget взаимодействует с состоянием, делегируя ему вызовы методов.
// Интерфейс должен содержать средства для обратной связи с объектом, поведение которого нужно изменить.
// Для этого используется событие (паттерн Publisher — Subscriber). Это необходимо для того,
// чтобы в процессе выполнения программы заменять объект состояния при появлении событий.
// Возможны случаи, когда сам Widget периодически опрашивает объект состояние на наличие перехода.

//StateA … StateZ — классы конкретных состояний. Должны содержать информацию о том, при каких условиях
// и в какие состояния может переходить объект из текущего состояния. Например, из StateA объект может
// переходить в состояние StateB и StateC, а из StateB — обратно в StateA и так далее.
// Объект одного из них должен содержать Widget при создании.

//Применение данного паттерна может быть затруднено, если состояния должны обмениваться данными,
// или одно состояние настраивает свойства другого. В этом случае понадобится глобальный объект,
// что не очень хорошее архитектурное решение.

import java.util.Random;

class MainState {
  public static void main(String[] args) {
    GumMachine gumMachine = new GumMachine();
    gumMachine.insertQuarter();
    gumMachine.turnCrank();


  }
}

//enum  GumMachineState {
//  SOLD_OUT, NO_QUARTER, HAS_QUARTER
//  ,WINNER;
//}

abstract class State {
  int count = 10;

  abstract public void insertQuarter(GumMachine gumMachine);
  abstract public void turnCrank(GumMachine gumMachine);

}

class GumMachine {
  State state = new NoQuarter();

  public void insertQuarter() {
    state.insertQuarter(this);

  }

  public void turnCrank() {
    state.turnCrank(this);

  }

}

class SoldOut extends State {
  @Override
  public void insertQuarter(GumMachine gumMachine) {
    System.out.println("no gums");
  }

  @Override
  public void turnCrank(GumMachine gumMachine) {
    System.out.println("no gums");
  }
}

class HasQuarter extends State {
  @Override
  public void insertQuarter(GumMachine gumMachine) {
    System.out.println("you have set quarter already");
  }

  @Override
  public void turnCrank(GumMachine gumMachine) {
    if (count <= 0) {
      gumMachine.state = new SoldOut();
    } else {
      System.out.println("selling...");
      count--;
      gumMachine.state = new NoQuarter();
    }
  }
}

class NoQuarter extends State {
  @Override
  public void insertQuarter(GumMachine gumMachine) {
    if(new Random().nextBoolean()) {
      System.out.println("you are winner");
      gumMachine.state = new Winner();
    } else {
      gumMachine.state = new HasQuarter();
    }
  }

  @Override
  public void turnCrank(GumMachine gumMachine) {
    System.out.println("no quarter");
  }
}

class Winner extends State {
  @Override
  public void insertQuarter(GumMachine gumMachine) {
    System.out.println("you are winner");
  }

  @Override
  public void turnCrank(GumMachine gumMachine) {
    System.out.println("get gum");
    gumMachine.state = new NoQuarter();
  }
}

//class GumMachine {
//  int count = 10;
//  GumMachineState state = GumMachineState.NO_QUARTER;
//
//  public void insertQuarter() {
//    if (state.equals(GumMachineState.HAS_QUARTER)) {
//      System.out.println("you can't insert another quarter");
//    } else if (state.equals(GumMachineState.NO_QUARTER)) {
//      state = GumMachineState.HAS_QUARTER;
//    } else if (state.equals(GumMachineState.SOLD_OUT)) {
//      System.out.println("no gums");
//    } else if (state.equals(GumMachineState.WINNER)) {
//      System.out.println("you are winner");
//    }
//  }
//
//  public void turnCrank() {
//    if (state.equals(GumMachineState.HAS_QUARTER)) {
//      if (count <= 0) {
//        state = GumMachineState.SOLD_OUT;
//      } else {
//        System.out.println("selling...");
//        state = GumMachineState.NO_QUARTER;
//        count--;
//      }
//    } else if (state.equals(GumMachineState.NO_QUARTER)) {
//      System.out.println("you didn't insert quarter");
//    } else if (state.equals(GumMachineState.WINNER)) {
//      System.out.println("give gum");
//      count++;
//      state = GumMachineState.NO_QUARTER;
//    }
//  }
//}
