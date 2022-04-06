package simple_problems.fib;

public class Fibonacci {
    public static int recurFib(int a){
        if(a==0)return 0;
        else if(a==1)return 1;
        else return recurFib(a-1)+ recurFib(a-2);
    }
    public static int fastFib(int a){
        int last = 0, next = 1;
        for (int i = 0; i < a; i++) {
            int oldLast = last;
            last = next;
            next = oldLast+next;
        }
        return last;
    }
}
