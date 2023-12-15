package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K and values of type V.
 * Associative Arrays store key/value pairs and permit you to look up values by key.
 *
 * @author Gabriela Roznawska
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({"unchecked"})
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(), DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> clonedArray = new AssociativeArray<>();
    for (int i = 0; i < this.size; i++) {
      clonedArray.set(pairs[i].key, pairs[i].value);
    } // for
    return clonedArray;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    String temp = "";
    if (this.size == 0){
      return "{}";
    } // if
    for (int i = 0; i < this.size; i++) {
      if(i+1 == this.size){
        temp += pairs[i].key + ": " + pairs[i].value;
      } // if
      else{
      temp += pairs[i].key + ": " + pairs[i].value + ", ";
      } // else
    } // for
    return "{ " + temp + " }";
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to get(key) will return value.
   */
  public void set(K key, V value) {
    //if the key is found then update its value
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        pairs[i].value = value;
        return;
      } // ifr
    } // for
    //if not and the array is full
    if (size == pairs.length) {
      expand();
    } // if
    KVPair<K, V> newPair = new KVPair<K, V>(key, value);
    pairs[size++] = newPair;
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException when the key does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    //return the value associated with a key, if not found then return an error
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return pairs[i].value;
      } // if
    } // for
    throw new KeyNotFoundException();
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    //if the key is found return true, if not return 
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return true;
      } // if
    } // for
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls to get(key) will throw an
   * exception. If the key does not appear in the associative array, does nothing.
   */
  public void remove(K key) {
    int j = 0;
    //go through the array and when the key is found move the array one forward.
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        for (j = i; j + 1 < size; j++) {
          pairs[j] = pairs[j + 1];
        } // for
        pairs[j] = null;
      } // if
    } // for
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()


  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key. If no such entry is found,
   * throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    //if the key is found then return its index, else throw error
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      } // if
    } // fpr
    throw new KeyNotFoundException();
  } // find(K)
} // class AssociativeArray
