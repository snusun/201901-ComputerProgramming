import java.util.*;

public class Question1 {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(factorial(n));
    }
    public static int factorial(int n){
        int fac = 1;
        int i = 1;
        for( ; i<=n; i++) {
            fac = fac * i;
        }
        return fac;
    }
}

