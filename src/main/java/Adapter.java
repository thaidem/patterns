//Адаптер (англ. Adapter) — структурный шаблон проектирования,
// предназначенный для организации использования функций объекта,
// недоступного для модификации, через специально созданный интерфейс.
// Другими словами — это структурный паттерн проектирования,
// который позволяет объектам с несовместимыми интерфейсами работать вместе.

class MainAdapter {

  public static void main(String[] args) {
    AmericanSocket socket = new SimpleAmericanSocket();
    Radio radio = new Radio();
    EuroSocket euroSocket = new SocketAdapter(socket);
    radio.listenMusic(euroSocket);

  }
}

interface EuroSocket {
  void getPower();

}

class SocketAdapter implements EuroSocket {

  public SocketAdapter(AmericanSocket americanSocket) {
    this.americanSocket = americanSocket;
  }

  AmericanSocket americanSocket;

  @Override
  public void getPower() {
    americanSocket.getPower();
  }
}

interface AmericanSocket {
  void getPower();

}

class SimpleAmericanSocket implements AmericanSocket {

  @Override
  public void getPower() {
    System.out.println("get 110 volt");
  }
}

class Radio {
  public void listenMusic(EuroSocket euroSocket) {
    euroSocket.getPower();
  }
}
