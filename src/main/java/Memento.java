//Хранитель (англ. Memento) — поведенческий шаблон проектирования, позволяющий, не нарушая инкапсуляцию,
// зафиксировать и сохранить внутреннее состояние объекта так, чтобы позднее восстановить его в это состояние.
//Существует два возможных варианта реализации данного шаблона: классический, описанный в книге Design Patterns,
// и реже встречающийся нестандартный вариант.
//Шаблон Хранитель используется, когда:
//1.необходимо сохранить снимок состояния объекта (или его части) для последующего восстановления
//2.прямой интерфейс получения состояния объекта раскрывает детали реализации и нарушает инкапсуляцию объекта

import java.util.ArrayList;
import java.util.List;

class MainMemento {
  public static void main(String[] args) {
    List<Originator.Memento> list = new ArrayList<>();
    Originator originator = new Originator();
    originator.setState("one");
    list.add(originator.saveState());
    originator.setState("two");
    list.add(originator.saveState());
    originator.setState("three");
    System.out.println(originator.state);
    originator.restoreFromMemento(list.get(0));
    System.out.println(originator.state);

  }
}

class Originator {
  String state;

  public void setState(String state) {
    this.state = state;
  }

  public Originator.Memento saveState() {
    return new Memento(this.state);
  }

  public void restoreFromMemento(Memento memento) {
    this.state = memento.state;
  }

  static class Memento {
    String state;

    public Memento(String state) {
      this.state = state;
    }

    public String getState() {
      return state;
    }
  }
}
