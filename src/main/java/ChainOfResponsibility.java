//Цепочка обязанностей (англ. Chain of responsibility) — поведенческий шаблон проектирования,
// предназначенный для организации в системе уровней ответственности.

class MainChainOfResponsibility {

  public static void main(String[] args) {
    MessageHandler messageHandler = new MessageAddEndMarkHandler(
            new MessageVerifyHandler(
                    new MessagePrintHandler(null)));
    messageHandler.handle("Hello, world");

//    MessageSender messageSender = new MessageSender(new MessagePrinter());
//    messageSender.sendMessage("Hello, world");

  }
}

abstract class MessageHandler {
  MessageHandler messageHandler;

  public MessageHandler(MessageHandler messageHandler) {
    this.messageHandler = messageHandler;
  }

  abstract void handle(String msg);
}

class MessagePrintHandler extends MessageHandler {
  public MessagePrintHandler(MessageHandler messageHandler) {
    super(messageHandler);
  }

  @Override
  void handle(String msg) {
    System.out.println(msg);
  }
}

class MessageVerifyHandler extends MessageHandler {
  public MessageVerifyHandler(MessageHandler messageHandler) {
    super(messageHandler);
  }

  @Override
  void handle(String msg) {
    if(!msg.matches(".*\\d.*")) {
      messageHandler.handle(msg);
    }
  }
}

class MessageAddEndMarkHandler extends MessageHandler {
  public MessageAddEndMarkHandler(MessageHandler messageHandler) {
    super(messageHandler);
  }

  @Override
  void handle(String msg) {
      messageHandler.handle(msg + "!");
  }
}

//class MessageSender {
//  MessagePrinter messagePrinter;
//
//  public MessageSender(MessagePrinter messagePrinter) {
//    this.messagePrinter = messagePrinter;
//  }
//
//  public void sendMessage(String message) {
//    messagePrinter.printMessage(message);
//  }
//}
//
//class MessagePrinter {
//  public void printMessage(String message) {
//    if (!message.matches(".*\\d.*"))
//      System.out.println(message + "!");
//  }
//}