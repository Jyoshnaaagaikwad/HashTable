import java.util.*;

class MyMapNode<K, V> {
    K key;
    V value;
    MyMapNode<K, V> next;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class MyHashMap<K, V> {
    private ArrayList<LinkedList<MyMapNode<K, V>>> bucketArray;
    private int numBuckets;

    public MyHashMap(int numBuckets) {
        this.numBuckets = numBuckets;
        this.bucketArray = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; i++) {
            this.bucketArray.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % numBuckets);
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> bucket = bucketArray.get(bucketIndex);

        if (bucket == null) {
            return null;
        }

        for (MyMapNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> bucket = bucketArray.get(bucketIndex);

        if (bucket == null) {
            bucket = new LinkedList<>();
            bucketArray.set(bucketIndex, bucket);
        }

        for (MyMapNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        MyMapNode<K, V> newNode = new MyMapNode<>(key, value);
        bucket.add(newNode);
    }
}

 class WordFrequencyCounter {
    public static void main(String[] args) {
        java.lang.String paragraph = "Paranoids are not paranoid because they are paranoid but " + "because they keep putting themselves deliberately into paranoid avoidable situations";
        String[] words = paragraph.toLowerCase().split("\\s+");

        MyHashMap<String, Integer> wordFrequencyMap = new MyHashMap<>(10);

        for (String word : words) {
            Integer frequency = wordFrequencyMap.get(word);
            if (frequency == null) {
                wordFrequencyMap.put(word, 1);
            } else {
                wordFrequencyMap.put(word, frequency + 1);
            }
        }

        // Display word frequencies
        for (String word : wordFrequencyMap.keySet()) {
            System.out.println("Word: " + word + ", Frequency: " + wordFrequencyMap.get(word));
        }
    }
}
