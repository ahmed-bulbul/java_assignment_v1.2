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


