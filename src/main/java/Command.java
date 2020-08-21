//Команда (англ. Command) — поведенческий шаблон проектирования,
// используемый при объектно-ориентированном программировании,
// представляющий действие. Объект команды заключает в себе само действие и его параметры.

class MainCommand {
  public static void main(String[] args) {
    Command command = new LightCommand(new Light());
    Command command2 = new MusicPlayerCommand(new MusicPlayer());
    Command command3 = new CoffeeMachineCommand(new CoffeeMachine());
    Command command4 = new LightAndMusicCommand(new Light(), new MusicPlayer());
    Command command5 = new PhoneCommand(new Phone(), "John");
    Button button = new Button(command);
    Button button2 = new Button(command2);
    Button button3 = new Button(command3);
    Button button4 = new Button(command4);
    Button button5 = new Button(command5);
    button5.pressButton();
  }
}

class Button {
  Command command;

  public Button(Command command) {
    this.command = command;
  }

  void pressButton() {
    command.execute();
  }
}

class LightCommand implements Command {
  Light light;

  public LightCommand(Light light) {
    this.light = light;
  }

  @Override
  public void execute() {
    light.switchLight();
  }
}

class MusicPlayerCommand implements Command {
  MusicPlayer musicPlayer;

  public MusicPlayerCommand(MusicPlayer musicPlayer) {
    this.musicPlayer = musicPlayer;
  }

  @Override
  public void execute() {
    musicPlayer.playMusic();
  }
}

class LightAndMusicCommand implements Command {
  Light light;
  MusicPlayer musicPlayer;

  public LightAndMusicCommand(Light light, MusicPlayer musicPlayer) {
    this.light = light;
    this.musicPlayer = musicPlayer;
  }

  @Override
  public void execute() {
    light.switchLight();
    musicPlayer.playMusic();
  }
}

class CoffeeMachineCommand implements Command {
  CoffeeMachine coffeeMachine;

  public CoffeeMachineCommand(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  @Override
  public void execute() {
    coffeeMachine.makeCoffee();
  }
}

class PhoneCommand implements Command {
  Phone phone;
  String name;

  public PhoneCommand(Phone phone, String name) {
    this.phone = phone;
    this.name = name;
  }

  @Override
  public void execute() {
    phone.makeCall(name);
  }
}

interface Command {
  void execute();
}

class Light {
  boolean isOn;
  public void switchLight() {
    this.isOn = !this.isOn;
    System.out.println("Light is " + (isOn ? "on" : "off"));
  }
}

class MusicPlayer {
  public void playMusic() {
    System.out.println("Music is played");
  }
}

class CoffeeMachine {
  public void makeCoffee() {
    System.out.println("Make coffee");
  }
}

class Phone {
  public void makeCall(String name) {
    System.out.println("Make call to " + name);
  }
}
