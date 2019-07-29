import java.util.ArrayList;

// A map for entries with string keys and string values, implemented
// using a binary search tree.
public class TreeMapForStrings {
   
   private static final boolean test = false;
   
   // A node in the tree, with a key, value, and pointers to
   // two children nodes, left and right.
   private class Node{
   
      private String key;
      
      private String value;
      
      private Node left;
      
      private Node right;
    
      private Node(String key, String value) {
         this.key = key;
         this.value = value;
         left = null;
         right = null;
      }
   }
   private Node root;
    
   //Constructor
   public TreeMapForStrings(){
      root = null;
   }
   
   //Adds a new entry with the given key and value;
   //If an entry with given key already exists, its
   //value is replaced by new value
   public void put(String key, String value){
      root = put(key, value, root);
   }
   
   public Node put(String key, String value, Node subroot){
      if (subroot==null){
         subroot = new Node(key, value);
      } else if (subroot.key.equals(key)){
         subroot.key = key;
         subroot.value = value;
      } else if (subroot.key.compareTo(key) > 0){
         if (test)
            System.out.println(key + " < " + subroot.key);
         subroot.left = put(key,value,subroot.left);
      } else {
         if (test)
            System.out.println(key + " > " + subroot.key);
         subroot.right = put(key,value,subroot.right);
      }
      return subroot;
   }
   
   //Gets the value of the entry corresponding to the given key.
   //If no entry with given key exists, returns null.
   public String getValue(String key){
      return getValue(key,root);
   }
   
   public String getValue(String key, Node subroot){
      if (subroot==null){
         return null;
      } else if (subroot.key.equals(key)){
         return subroot.value;
      } else if (subroot.key.compareTo(key) > 0){
         return getValue(key,subroot.left);
      } else {
         return getValue(key,subroot.right);
      }
   }
   
   // Outputs an array of keys that in the map that start
   // with the given prefix.
   public ArrayList<String> getKeysForPrefix(String prefix){
      ArrayList<String> list = new ArrayList<String>();
      list = getKeys(prefix, list, root);
      return list;
   }
   
   public ArrayList<String> 
         getKeys(String prefix, ArrayList<String> list, Node subroot){
      if (subroot==null){
         return list;
      } else if (subroot.key.length() >= prefix.length() && 
            subroot.key.substring(0,prefix.length()).equals(prefix)){
         list = getKeys(prefix, list, subroot.left);
         list.add(subroot.key);
         list = getKeys(prefix, list, subroot.right);
         return list;
      } else if (subroot.key.compareTo(prefix) > 0){
         return getKeys(prefix, list, subroot.left);
      } else {
         return getKeys(prefix, list, subroot.right);
      }
   }
   
   //For testing.
   public static void main(String[] args){
      TreeMapForStrings testTree = new TreeMapForStrings();
      String p1 = "value1";
      String p2 = "value2";
      String p3 = "value3";
      String p4 = "value4";
      testTree.put("1", p1);
      testTree.put("2", p2);
      testTree.put("3", p3);
      System.out.println(testTree.getValue("1"));
      System.out.println(testTree.getValue("2"));
      System.out.println(testTree.getValue("3"));
      System.out.println(testTree.getValue("4"));
      p1 = "new1";
      p2 = "new2";
      p3 = "new3";
      p4 = "new4";
      testTree.put("the1", p1);
      testTree.put("start", p2);
      testTree.put("the3", p3);
      testTree.put("trick", p4);
      testTree.put("the", "something");
      System.out.println(testTree.getValue("the1"));
      System.out.println(testTree.getValue("start"));
      System.out.println(testTree.getValue("the3"));
      System.out.println(testTree.getValue("trick")); 
      System.out.println(testTree.getKeysForPrefix("the"));
   }
   
   
}