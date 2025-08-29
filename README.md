Q1) How can you suggest enhancement to this code snippet.
```java
public class ArrArgs {
    public static void main (String args[]) {
        try {
            int k=0;
            do {
                System.out.println("Value of input is" + k + "and arguments" + args[k++]);
            } while (true);
        } catch (ArrayIndexOutOfBoundsException errorOc) {
            System.err.println("Eror occured "+errorOc.toString());
        }
    }
}

```
Issues:

Infinite loop (do-while(true)) relies on exception to terminate → bad practice.

Message formatting is poor (no spacing).

Catching ArrayIndexOutOfBoundsException is not ideal for flow control.

Solution:
```java
public class ArrArgs {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided.");
            return;
        }

        for (int k = 0; k < args.length; k++) {
            System.out.println("Value of input index: " + k + ", argument: " + args[k]);
        }
    }
}

```
Q2) What is the o/p if java security manager is installed and below program is ran.
```java
public class PermisTest {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.home"));
        try {
            Scanner scObj = new Scanner(new File("input.txt"));
            int no1 = scObj.nextInt();
            int no2 = scObj.nextInt();
            System.out.println("The two nos are : " + no1 + ", " + no2);

            Formatter outObj = new Formatter(new File("output.txt"));
            int totalSum = no1 + no2;
            System.out.println("The total sum value is " + totalSum);
            outObj.format("%d", totalSum);
            outObj.close();
        } catch (Exception ee) {
            System.out.println("Error " + ee.toString());
        }
    }
}
```
Output :
<user_home_path>
Error java.security.AccessControlException: access denied ("java.io.FilePermission" "input.txt" "read")


Q3) Write a program to occur out of memory error using StringBuffer(while doing append) with
storing millions of records and how to resolve it with GC and without GC?

Code to cause OOM:
```java
public class OOMStringBuffer {
    public static void main(String[] args) {
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sb.append("Record-" + i);
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError occurred: " + e);
        }
    }
}

```
With GC help:
```java
StringBuffer sb = new StringBuffer();
for (int i = 0; i < 1_000_000; i++) {
    sb.append("Record-" + i);
    if (i % 100_000 == 0) {
        System.out.println("Flushing buffer at " + i);
        sb.setLength(0); // release memory
    }
}

```
Q4) Write an implementation to read list of items to be added into the restaurant menulist.
The items can be sorted naturally based on categories and also explicitly able to sort based on
price/name.

```java
import java.util.*;

class MenuItem implements Comparable<MenuItem> {
    String category;
    String name;
    double price;

    public MenuItem(String category, String name, double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(MenuItem other) {
        return this.category.compareTo(other.category); // natural sorting
    }

    @Override
    public String toString() {
        return category + " - " + name + " : $" + price;
    }
}

public class MenuList {
    public static void main(String[] args) {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Drinks", "Coke", 2.5));
        items.add(new MenuItem("Main", "Burger", 8.0));
        items.add(new MenuItem("Main", "Pizza", 10.0));
        items.add(new MenuItem("Dessert", "Cake", 5.0));

        System.out.println("Sorted by Category:");
        Collections.sort(items);
        items.forEach(System.out::println);

        System.out.println("\nSorted by Price:");
        items.sort(Comparator.comparingDouble(i -> i.price));
        items.forEach(System.out::println);

        System.out.println("\nSorted by Name:");
        items.sort(Comparator.comparing(i -> i.name));
        items.forEach(System.out::println);
    }
}
```

