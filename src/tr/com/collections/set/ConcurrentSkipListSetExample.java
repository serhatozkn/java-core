package tr.com.collections.set;

/**
 * @author serhat.ozkan
 */

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ConcurrentSkipListSet and ConcurrentSkipListMap are useful when you need a sorted container
 * that will be accessed by multiple threads. These
 * are essentially the equivalents of TreeMap and TreeSet for concurrent code.
 */

/**
 * ConcurrentSkipListSet is  thread-safe because, by using SimpleImmutableEntry<K,V> internally,
 * it guarantees that none of its state (its keys and values) can be modified by currently executing threads,
 * because its state is immutable.
 */
public class ConcurrentSkipListSetExample {

    public static void main(final String[] args) {
        final ConcurrentSkipListSetExample example = new ConcurrentSkipListSetExample();
        example.demo();
    }

    final ExecutorService executorService = Executors.newFixedThreadPool(20);
    public void demo() {

        final SortedSet<Integer> sortedConcurrentSet = new ConcurrentSkipListSet<>();
        final SortedSet<Integer> sortedUsualSet = new TreeSet<>();

        System.out.println("Testing concurrent sorted set...");
        fillSet(sortedConcurrentSet);
        String collect = sortedConcurrentSet.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(collect);
        System.out.println("Testing concurrent sorted set finished...");
        try {
            fillSet(sortedUsualSet);
        }catch (Exception ex) {
            System.out.println("Tree set can fail due to concurrent access" + "" +
                    " Make Executors thread pools size 1 if you want to test the failure reason.");
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    private void fillSet(final SortedSet<Integer> sortedSet) {
        List<? extends Future> futures = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> {
                    final int seed = i * 1000;
                    Runnable task = () -> IntStream.iterate(seed, n -> n + 1)
                            .limit(1_000)
                            .forEach(n -> sortedSet.add(n));
                    return task;
                })
                .map(executorService::submit)
                .toList();

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException|ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
