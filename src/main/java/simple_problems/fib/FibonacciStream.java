package simple_problems.fib;

import java.util.stream.IntStream;

public class FibonacciStream {
    private int last = 0, next = 1;
    public IntStream stream(){
        return IntStream.generate(()->{
            int oldLast = last;
            last = next;
            next = oldLast + last;
            return oldLast;
        });
    }
}
