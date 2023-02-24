package tr.com.referencetypes;

/**
 * @author serhat.ozkan
 */

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * **************
 * HARD REFERENCE
 * **************
 * Normal referencing is called hard-referencing. And they are convenient and the most convenient technique at most
 * of the circumstances.
 *
 * ****************
 * SOFT REFERENCE
 * ****************
 * softly reference objects are guaranteed to have been garbage collected before the OutOfMemoryError is thrown by the VM.
 * They are mostly used for implementing caches
 *
 * Baeldung: Soft references can be used to make our code more resilient to errors connected to insufficient memory.
 * For example, we could create a memory-sensitive cache that automatically evicts objects when memory is scarce.
 * We wouldn't need to manage the memory manually, as the garbage collector would do it for us.
 *
 * **************
 * WEAK REFERENCE
 * **************
 *
 * Objects referenced only by weak references aren't prevented from being collected.
 * From the perspective of garbage collection, they could not exist at all.
 * If a weakly referenced object should be protected from being cleared, it should also be referenced by some hard reference.
 *
 * Use case (Baeldung): Weak references are most often used to create canonicalizing mappings.
 * These are mappings that map only objects that can be reached. A great example is WeakHashMap,
 * which works like normal HashMap, but its keys are weakly referenced, and they are automatically removed when the
 * referent is cleared. Using WeakHashMap, we can create a short-living cache that clears objects that are no longer used by other
 * parts of the code. If we used a normal HashMap, the mere existence of the key in the map would prohibit it from being cleared
 * by the garbage collector.
 *
 */
public class ReferenceTypesExample {

    public static WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();

    public static void main(final String[] args) {
        SoftReference<List<String>> listReference = new SoftReference<>(new ArrayList<>());
        // We have to check before accessing
        List<String> list = listReference.get();
        if (list != null) {
            System.out.println("Soft reference isn't garbage collected since no memory problem");
            list.add("e");
        }

        WeakReference<List<String>> weakReference = new WeakReference<>(new ArrayList<>());

        // System.gc doesn't guarantee invocation of gc. So run again if sth went unexpected.
        for (int i = 0; i < 100; i++) {
            System.gc();
        }

        if (weakReference.get() == null) {
            System.out.println("Attemp to invoke gc -> weak reference is garbage collected");
        }

        demoWeakHashMap();

        // System.gc doesn't guarantee invocation of gc. So run again if sth went unexpected.
        for (int i = 0; i < 100; i++) {
            System.gc();
        }

        System.out.println("Size of weakhashmap must be zero. Size : " + weakHashMap.size());
    }

    public static void demoWeakHashMap() {
        final Object obj1Key = new Object();
        final Object obj2Key = new Object();
        final Object obj3Key = new Object();


        final Object obj1 = new Object();
        final Object obj2 = new Object();
        final Object obj3 = new Object();

        weakHashMap.put(obj1Key, obj1);
        weakHashMap.put(obj2Key, obj2);
        weakHashMap.put(obj3Key, obj3);

        System.out.println("Size of weakhashmap: All elements are referenced at scope: " + weakHashMap.size());
    }
}
