//Интерпретатор (англ. Interpreter) — поведенческий шаблон проектирования,
// решающий часто встречающуюся, но подверженную изменениям, задачу.
// Также известен как Little (Small) Language

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MainInterpreter {
  public static void main(String[] args) {
    String exp = "a - b";
    Expr parse = parse(exp);
    Map<String, Integer> context = new HashMap<>();
    context.put("a", 10);
    context.put("b", 20);
    int result = parse.interpret(context);
    System.out.println(result);
  }

  private static Expr parse(String exp) {
    ArrayDeque<Expr> stack = new ArrayDeque<>();
    Arrays.stream(exp.split(" ")).filter(token -> Character.isLetter(token.charAt(0)))
            .forEach(token -> {stack.push(parseToken(token, stack));});

    Arrays.stream(exp.split(" ")).filter(token -> !Character.isLetter(token.charAt(0)))
            .forEach(token -> {stack.push(parseToken(token, stack));});
    return stack.pop();
  }

  private static Expr parseToken(String token, ArrayDeque<Expr> stack) {
    Expr left, right;
    switch (token) {
      case "+":
        right =stack.pop();
        left = stack.pop();
        return Expr.plus(left, right);
      case "-":
        right =stack.pop();
        left = stack.pop();
        return Expr.minus(left, right);
      default:
        return Expr.variable(token);
    }
  }
}

interface Expr {
  int interpret(Map<String, Integer> context);

  static Expr plus(final Expr left, final Expr right) {
    return context -> left.interpret(context) + right.interpret(context);
  }

  static Expr minus(final Expr left, final Expr right) {
    return context -> left.interpret(context) - right.interpret(context);
  }

  static Expr variable(String name) {
    return context -> context.getOrDefault(name, 0);
  }
}
