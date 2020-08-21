//Компоновщик (англ. Composite pattern) — структурный шаблон проектирования,
// объединяющий объекты в древовидную структуру для представления иерархии от частного к целому.
// Компоновщик позволяет клиентам обращаться к отдельным объектам и к группам объектов одинаково.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class MainComposite {
  public static void main(String[] args) {
    Folder users = new Folder("users");
    Folder etc = new Folder("etc");
    Folder root = new Folder("root");
    root.addFolder(users, etc);

    Folder mike = new Folder("Mike");
    Folder ken = new Folder("Ken");
    Folder max = new Folder("Max");
    users.addFolder(mike, ken, max);

    Folder one = new Folder("one");
    Folder two = new Folder("two");
    Folder three = new Folder("three");
    mike.addFolder(one, two, three);

    users.printFolders();
  }
}

class Folder {
  String name;

  public Folder(String name) {
    this.name = name;
  }

  List<Folder> list = new ArrayList<>();

  public void addFolder(Folder folder) {
    list.add(folder);
  }

  public void addFolder(Folder... folders) {
    list.addAll(Arrays.asList(folders));
  }

  public void printFolders() {
    for (Folder folder: list) {
      System.out.println(folder.name);
      folder.printFolders();

    }

  }
}