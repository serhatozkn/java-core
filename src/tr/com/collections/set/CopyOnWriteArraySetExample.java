package tr.com.collections.set;

/**
 * @author serhat.ozkan
 */

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

/**
 * Java doc notes:
 *
 * A Set that uses an internal CopyOnWriteArrayList for all of its operations.
 * Thus, it shares the same basic properties:
 * It is best suited for applications in which set sizes generally stay small, read-only operations vastly outnumber
 * mutative operations, and you need to prevent interference among threads during traversal.
 * It is thread-safe.
 * Mutative operations (add, set, remove, etc.) are expensive since they usually entail copying the entire underlying array.
 * Iterators do not support the mutative remove operation.
 * Traversal via iterators is fast and cannot encounter interference from other threads.
 * Iterators rely on unchanging snapshots of the array at the time the iterators were constructed.
 */

/**
 * In this class I've compared performance of ConcurrentSkipList, HashSet, ConcurrentSkipListExample
 */
public class CopyOnWriteArraySetExample {

    public static void main(final String[] args) {
        CopyOnWriteArraySetExample copyOnWriteArraySetExample = new CopyOnWriteArraySetExample();
        copyOnWriteArraySetExample.demo();
    }

    private final int MILLIS_TO_NAN0 = 1_000_000;
    private final CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

    private final ConcurrentSkipListSet<String> concurrentSkipListSet = new ConcurrentSkipListSet<>();

    private final HashSet<String> hashSet = new HashSet<>();

    private void demo() {

        Instant now = Instant.now();
        IntStream.rangeClosed(0, 50_000)
                .mapToObj(String::valueOf)
                .forEach(str -> hashSet.add(str));

        Duration duration = Duration.between(now, Instant.now());
        System.out.println(String.format("Hash Set took: %d milliseconds", duration.getNano() / MILLIS_TO_NAN0));

        now = Instant.now();
        IntStream.rangeClosed(0, 50_000)
                .mapToObj(String::valueOf)
                .forEach(str -> copyOnWriteArraySet.add(str));

        duration = Duration.between(now, Instant.now());
        System.out.println(String.format("Copy on Write Array Set took: %d milliseconds", duration.getNano() / MILLIS_TO_NAN0));


        now = Instant.now();
        IntStream.rangeClosed(0, 50_000)
                .mapToObj(String::valueOf)
                .forEach(str -> concurrentSkipListSet.add(str));

        duration = Duration.between(now, Instant.now());
        System.out.println(String.format("ConcurrentSkipListSet took: %d milliseconds", duration.getNano() / MILLIS_TO_NAN0));
    }
}
