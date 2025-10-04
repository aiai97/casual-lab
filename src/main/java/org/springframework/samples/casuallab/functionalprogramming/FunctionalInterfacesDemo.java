package org.springframework.samples.casuallab.functionalprogramming;

import java.util.concurrent.*;
import java.util.function.*;

public class FunctionalInterfacesDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("===== Functional Interfaces Examples =====");

        // 1️⃣ Consumer<T> → “I do something with what you give me.”
        Consumer<String> printer = s -> System.out.println("Got: " + s);
        printer.accept("Hello"); // Output: Got: Hello

        // 2️⃣ Supplier<T> → “I give you something, don’t ask me how.”
        Supplier<Integer> randomSupplier = () -> (int) (Math.random() * 100);
        System.out.println(randomSupplier.get()); // Output: some random int

        // 3️⃣ Function<T,R> → “I transform A into B.”
        Function<String, Integer> lengthFunc = s -> s.length();
        System.out.println(lengthFunc.apply("Hello")); // Output: 5

        // 4️⃣ Predicate<T> → “I tell you true or false.”
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4)); // Output: true

        // 5️⃣ Runnable → “I execute something, silently.”
        Runnable task = () -> System.out.println("Running in thread");
        new Thread(task).start();

        // Give some time for the thread to run
        Thread.sleep(100);

        // 6️⃣ Callable<V> → “I execute and promise a result.”
        Callable<String> callableTask = () -> "Result from Callable";
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callableTask);
        System.out.println(future.get()); // Output: Result from Callable
        executor.shutdown();

        // 7️⃣ Future<V> / CompletableFuture<V> → “I’m a promised result, coming later.”
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> "Async Result");
        futureAsync.thenAccept(System.out::println); // Output: Async Result (later)

        // Give some time for async task to complete
        Thread.sleep(100);

        System.out.println("===== Specialized Variants for Primitives =====");

        // IntConsumer / LongConsumer / DoubleConsumer → optimized Consumers for primitives
        IntConsumer intPrinter = i -> System.out.println("Int: " + i);
        intPrinter.accept(42); // Output: Int: 42

        // BiConsumer<T,U> → works on two inputs
        BiConsumer<String, Integer> biPrinter = (s, i) -> System.out.println(s + " -> " + i);
        biPrinter.accept("Age", 30); // Output: Age -> 30

        // BiFunction<T,U,R> → transform two inputs to one output
        BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;
        System.out.println(adder.apply(3, 5)); // Output: 8

        // BiPredicate<T,U> → test a condition on two inputs
        BiPredicate<Integer, Integer> bothEven = (a, b) -> a % 2 == 0 && b % 2 == 0;
        System.out.println(bothEven.test(4, 6)); // Output: true
    }
}

