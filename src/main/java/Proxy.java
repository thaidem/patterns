//Заместитель (англ. Proxy) — структурный шаблон проектирования, предоставляющий объект,
// который контролирует доступ к другому объекту, перехватывая все вызовы (выполняет функцию контейнера).

//Проблема: Необходимо контролировать доступ к объекту, не изменяя при этом поведение клиента.
//Необходимо иметь доступ к объекту так, чтобы не создавать реальные объекты непосредственно,
// а через другой объект, который может иметь дополнительную функциональность.

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class MainProxy {
  public static void main(String[] args) {
//    Reader reader = new ProxyReader();
//    reader.read("hello");

    InvocationHandler invocationHandler = new ReaderInvocationHandler();
    Object proxyInstance = Proxy.newProxyInstance(MainProxy.class.getClassLoader(), new Class[]{Reader.class}, invocationHandler);
    ((Reader)proxyInstance).read("hello");

  }
}

interface Reader {
  String read(String str);
}

class MyReader implements Reader {

  @Override
  public String read(String str) {
    return str + " world";
  }
}

class ReaderInvocationHandler implements InvocationHandler {
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println(args[0]);
    Object result = method.invoke(new MyReader(), args);
    System.out.println(result);
    return result;
  }
}


class ProxyReader extends MyReader {
  @Override
  public String read(String str) {
    System.out.println(str);
    String read = super.read(str);
    System.out.println(read);
    return read;
  }
}
