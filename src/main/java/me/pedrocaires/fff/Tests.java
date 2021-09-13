// package me.pedrocaires.fff;

// import org.springframework.beans.factory.config.ConfigurableBeanFactory;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import java.time.*;
// import java.util.*;
// import java.util.concurrent.*;
// import java.util.function.BiConsumer;
// import java.util.function.BiFunction;
// import java.util.function.Function;
// import java.util.stream.Collectors;
// import javax.annotation.security.RunAs;
// import javax.swing.JOptionPane;

// public class Tests {



//     public static void main(String[] args) {
// //       var list =  Arrays.asList(new Student(10), new Student(1), new Student(2), new Student(8), new Student(6), new Student(1), new Student(5));
// //       Collections.sort(list);
// //       System.out.println(list);
// //       var a = JOptionPane.showInputDialog( "Hello moto");
// //       xD((z, b, c, t, y) -> "");
//         var list = Arrays.asList("a", "b");
//         //Set, Map, List, Queue, Stack
//         var map = new HashMap<>();
//         map.put(1, 2);
//         var set = new HashSet<String>();
//         set.stream();
//         var a =  list.stream().filter(s -> s.equals("abc"));
//         Comparable<String> TESTS = (o) -> 0;
//         var student = new Student(1);


//         Pedro pedro = TESTS::compareTo;
//         pedro.printAString("xD");
//     }

//     static void xD(String s1, String s2){

//     }



// }

// @FunctionalInterface
// interface Pedro {
//     void printAString(String xD);
// }


// class Student implements Comparable<Student> {

//     private final int yearOfStart;

//     public Student(int yearOfStart) {
//         this.yearOfStart = yearOfStart;
//     }

//     public int compareTo(Student student) {
//         return -1;
//     }

//     public String toString(String a) {
//         return "Student{" +
//                 "yearOfStart=" + yearOfStart +
//                 '}';
//     }


// }


// class MyCallable implements Callable<Integer> {

//     @Override
//     public Integer call() throws Exception {
//             return 1;
//     }

//     public static void main(String[] args) throws ExecutionException, InterruptedException {
//         a();
//     }

//     static void a() throws ExecutionException, InterruptedException {
// //        new Order();
// //        new Order();
//         var a = Order.pedro;
// //        Date date = new Date();
// //        var localDate = LocalDateTime.now(ZoneId.of("UTC"));ZoneId.getAvailableZoneIds().add();
// //        System.out.println(localDate);
// //        Callable
// //        var a = Executors.newCachedThreadPool();
// //        var future = a.submit(() -> System.out.println("xD"));
// //        var future2 = a.submit(() -> 1);
// //        var b = future.get();
// //
// //        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
// //        var th = new Thread(futureTask);
// //        th.start();
// //        th.start();
// //        System.out.println(futureTask.get());
// ////        System.out.println(futureTask.get());
// //            var a = Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3);
// //            var sum =  a.stream().parallel().mapToInt(o -> o).peek(i -> System.out.println(Thread.currentThread().getName())).sum();
// //        System.out.println(sum);
// //        new Thread().stop();
// //        Runnable runnable = () -> System.out.println("a");
// //        var z =  CompletableFuture.runAsync(() -> System.out.println("a"));
// //         var x = z.get();

//     }
// }

// class Order {

//     static int pedro = 0;

//     static {
//         System.out.println("static block ");
//     }

//     {
//         System.out.println("block");
//     }

//     public Order() {
// //        LinkedList
//         System.out.println("constructor");
//     }
// }

// class QuickSortAlgorithm {

//     public static void main(String[] args) {

//         var arr = new int[]{1, -10, 126, -32, 320, 1,3};
//         quickSort(arr, 0, arr.length - 1);
//         printArray(arr);
//     }

//     static void quickSort(int [] arr, int left, int right) {
//         if(left < right) {
//             int pivot = partition(arr, left, right);
//             quickSort(arr, left, pivot);
//             quickSort(arr, pivot + 1, right);
//         }
//     }

//     static int partition(int [] arr, int left, int right){
//         int pivot = arr[(left + right) / 2];
//         int i = left - 1;
//         int j = right + 1;
//         while(true){
//             do {
//                 i++;
//             } while (arr[i] < pivot);
//             do {
//                 j--;
//             } while (arr[j] < pivot);
//             if (i >= j) {
//               return j;
//             }
//             int aux = arr[i];
//             arr[i] = arr[j];
//             arr[j] = aux;
//         }
//     }

//     private static void printArray(int[] array){
//         for (int item : array) {
//             System.out.println(item);
//         }
//     }

// }


// class EaseSortAlgorithm {
//     public static void main(String[] args) {
// //        printArray(mergeSort(new int[]{3, 2, 3}));
//         var arr = new int[]{1, -10,6, 4, 65, 712};
//         for (int i = 0; i < arr.length; i++)
//         {
//             for (int j = i + 1; j < arr.length; j++)
//             {
//                 int tmp = 0;
//                 if (arr[i] > arr[j])
//                 {
//                     tmp = arr[i];
//                     arr[i] = arr[j];
//                     arr[j] = tmp;
//                 }
//             }
// //prints the sorted element of the array
//             System.out.println(arr[i]);
//         }
//     }


//     private static void printArray(int[] array){
//         for (int item : array) {
//             System.out.println(item);
//         }
//     }
// }

// class MergeSortAlgorithm {

//     public static void main(String[] args) {
//         printArray(mergeSort(new int[]{3, 2, 3}));
//     }

//     public static int[] mergeSort(int[] array){
//         if(array.length <= 1){
//             return array;
//         }
//         int midpoint = array.length / 2;

//         int[] left = new int[midpoint];
//         int[] right = new int[array.length % 2 == 0 ? midpoint : midpoint + 1];

//         for(int i =0; i < midpoint; i++){
//             left[i] = array[i];
//         }

//         for (int j = 0; j < right.length; j++){
//             right[j] = array[midpoint + j];
//         }

//         left = mergeSort(left);
//         right = mergeSort(right);
//         return merge(left, right);
//     }

//     private static int[] merge(int[] left, int[] right){
//         int[] result = new int[left.length + right.length];

//         int leftPointer, rightPointer, resultPointer;
//         leftPointer = rightPointer = resultPointer = 0;

//         while(leftPointer < left.length || rightPointer < right.length) {

//             if(leftPointer < left.length && rightPointer < right.length){

//                 if(left[leftPointer] < right[rightPointer]) {
//                     result[resultPointer++] = left[leftPointer++];
//                 } else {
//                     result[resultPointer++] = right[rightPointer++];
//                 }

//             } else if(leftPointer < left.length){
//                 result[resultPointer++] = left[leftPointer++];
//             } else if(rightPointer < right.length) {
//                 result[resultPointer++] = right[rightPointer++];
//             }

//         }

//         return result;
//     }

//     private static void printArray(int[] array){
//         for (int item : array) {
//             System.out.println(item);
//         }
//     }
// }


// class BinaryTree {

//     Node root;

//     public void addNode(int key, String name) {

//         // Create a new Node and initialize it

//         Node newNode = new Node(key, name);

//         // If there is no root this becomes root

//         if (root == null) {

//             root = newNode;

//         } else {

//             // Set root as the Node we will start
//             // with as we traverse the tree

//             Node focusNode = root;

//             // Future parent for our new Node

//             Node parent;

//             while (true) {

//                 // root is the top parent so we start
//                 // there

//                 parent = focusNode;

//                 // Check if the new node should go on
//                 // the left side of the parent node

//                 if (key < focusNode.key) {

//                     // Switch focus to the left child

//                     focusNode = focusNode.leftChild;

//                     // If the left child has no children

//                     if (focusNode == null) {

//                         // then place the new node on the left of it

//                         parent.leftChild = newNode;
//                         break; // All Done

//                     }

//                 } else { // If we get here put the node on the right

//                     focusNode = focusNode.rightChild;

//                     // If the right child has no children

//                     if (focusNode == null) {

//                         // then place the new node on the right of it

//                         parent.rightChild = newNode;
//                         break; // All Done

//                     }

//                 }

//             }
//         }

//     }

//     // All nodes are visited in ascending order
//     // Recursion is used to go to one node and
//     // then go to its child nodes and so forth

//     public void inOrderTraverseTree(Node focusNode) {

//         if (focusNode != null) {

//             // Traverse the left node

//             inOrderTraverseTree(focusNode.leftChild);

//             // Visit the currently focused on node

//             System.out.println(focusNode);

//             // Traverse the right node

//             inOrderTraverseTree(focusNode.rightChild);

//         }

//     }

//     public void preorderTraverseTree(Node focusNode) {

//         if (focusNode != null) {

//             System.out.println(focusNode);

//             preorderTraverseTree(focusNode.leftChild);
//             preorderTraverseTree(focusNode.rightChild);

//         }

//     }

//     public void postOrderTraverseTree(Node focusNode) {

//         if (focusNode != null) {

//             postOrderTraverseTree(focusNode.leftChild);
//             postOrderTraverseTree(focusNode.rightChild);

//             System.out.println(focusNode);

//         }

//     }

//     public Node findNode(int key) {

//         // Start at the top of the tree

//         Node focusNode = root;

//         // While we haven't found the Node
//         // keep looking

//         while (focusNode.key != key) {

//             // If we should search to the left

//             if (key < focusNode.key) {

//                 // Shift the focus Node to the left child

//                 focusNode = focusNode.leftChild;

//             } else {

//                 // Shift the focus Node to the right child

//                 focusNode = focusNode.rightChild;

//             }

//             // The node wasn't found

//             if (focusNode == null)
//                 return null;

//         }

//         return focusNode;

//     }

//     public static void main(String[] args) {

//         BinaryTree theTree = new BinaryTree();

//         theTree.addNode(50, "Boss");

//         theTree.addNode(25, "Vice President");

//         theTree.addNode(15, "Office Manager");

//         theTree.addNode(30, "Secretary");

//         theTree.addNode(75, "Sales Manager");

//         theTree.addNode(85, "Salesman 1");

//         // Different ways to traverse binary trees

//         // theTree.inOrderTraverseTree(theTree.root);

//         // theTree.preorderTraverseTree(theTree.root);

//         // theTree.postOrderTraverseTree(theTree.root);

//         // Find the node with key 75

//         System.out.println("\nNode with the key 75");

//         System.out.println(theTree.findNode(75));

//     }
// }

// class Node {

//     int key;
//     String name;

//     Node leftChild;
//     Node rightChild;

//     Node(int key, String name) {

//         this.key = key;
//         this.name = name;
//         List<Integer> aux = new ArrayList<>();
//         aux.remove(1);

//     }

//     public String toString() {

//         return name + " has the key " + key;

//         /*
//          * return name + " has the key " + key + "\nLeft Child: " + leftChild +
//          * "\nRight Child: " + rightChild + "\n";
//          */

//     }

// }