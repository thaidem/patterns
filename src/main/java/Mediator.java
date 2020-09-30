//Посредник (англ. Mediator) — поведенческий шаблон проектирования, обеспечивающий взаимодействие
// множества объектов, формируя при этом слабую связанность и избавляя объекты от необходимости
// явно ссылаться друг на друга.

//"Посредник" определяет интерфейс для обмена информацией с объектами "Коллеги", "Конкретный посредник"
// координирует действия объектов "Коллеги". Каждый класс "Коллеги" знает о своем объекте "Посредник",
// все "Коллеги" обмениваются информацией только с посредником, при его отсутствии им пришлось бы обмениваться
// информацией напрямую. "Коллеги" посылают запросы посреднику и получают запросы от него.
// "Посредник" реализует кооперативное поведение, пересылая каждый запрос одному или нескольким "Коллегам".

import java.util.ArrayList;
import java.util.List;

class MainMediator {
  public static void main(String[] args) {
    Chat chat = new ChatMediator();
    ColleagueImpl mike = new ColleagueImpl(chat, "Mike");
    chat.addColleague(mike);
    chat.addColleague(new ColleagueImpl(chat, "John"));
    chat.addColleague(new ColleagueImpl(chat, "Alex"));
    mike.sendMessage("Hello World!");

  }
}

interface Chat {
  void sendMessage(String message, Colleague colleague);
  void addColleague(Colleague colleague);
}

class ChatMediator implements Chat {
  List<Colleague> list = new ArrayList<>();

  @Override
  public void sendMessage(String message, Colleague me) {
    list.forEach(colleague -> {
      if (colleague!= me) {
        colleague.printMessage(message);
      }
    });
  }

  @Override
  public void addColleague(Colleague colleague) {
    list.add(colleague);
  }
}

abstract class Colleague {
  Chat chat;
  String name;

  public Colleague(Chat chat, String name) {
    this.chat = chat;
    this.name = name;
  }

  abstract void sendMessage(String message);
  abstract void printMessage(String message);
}

class ColleagueImpl extends Colleague {
  public ColleagueImpl(Chat chat, String name) {
    super(chat, name);
  }

  @Override
  void sendMessage(String message) {
    chat.sendMessage(message, this);
  }

  @Override
  void printMessage(String message) {
    System.out.println(name + " recieve message " + message);
  }
}
