//Шаблон фасад (англ. Facade) — структурный шаблон проектирования,
// позволяющий скрыть сложность системы путём сведения всех возможных внешних вызовов к одному объекту,
// делегирующему их соответствующим объектам системы.


class MainFacade {
  public static void main(String[] args) {
    LivingRoom livingRoom = new LivingRoom();
    livingRoom.pressButton("5", "24");
    BedRoom bedRoom = new BedRoom();
    bedRoom.pressButton("69", "22");

  }
}

class Tv {
  void playChannel(String channel) {System.out.println("play channel " + channel);}
}

class AirConditioning {
  void setTemperature(String temperature) {System.out.println("set temperature " + temperature);}
}

class Lights {
  void turnLight() {
    System.out.println("turn light");}
}

class RoomFacade {
  Tv tv = new Tv();
  AirConditioning airConditioning = new AirConditioning();
  Lights lights = new Lights();

  public void pressButton(String channel, String temperature) {
    tv.playChannel(channel);
    airConditioning.setTemperature(temperature);
    lights.turnLight();
  };

}


class LivingRoom {
  RoomFacade roomFacade = new RoomFacade();

  public void pressButton(String channel, String temperature) {
    roomFacade.pressButton(channel, temperature);
  }
}

class BedRoom {
  RoomFacade roomFacade = new RoomFacade();

  public void pressButton(String channel, String temperature) {
    roomFacade.pressButton(channel, temperature);
  }
}

