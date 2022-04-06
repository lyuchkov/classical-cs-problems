package simple_problems.fibonacci;

import org.junit.jupiter.api.Test;
import simple_problems.fib.Fibonacci;
import simple_problems.fib.FibonacciStream;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void fibRecur0() {
        assertEquals(Fibonacci.recurFib(0),0);
    }
    @Test
    void fibRecur1() {
        assertEquals(Fibonacci.recurFib(1),1);
    }
    @Test
    void fibRecur2() {
        assertEquals(Fibonacci.recurFib(2),1);
    }
    @Test
    void fibRecur3() {
        assertEquals(Fibonacci.recurFib(3),2);
    }
    @Test
    void fibRecur4() {
        assertEquals(Fibonacci.recurFib(4),3);
    }
    @Test
    void fibRecur5() {
        assertEquals(Fibonacci.recurFib(5),5);
    }
    @Test
    void fibFast0() {
        assertEquals(Fibonacci.fastFib(0),0);
    }
    @Test
    void fibFast1() {
        assertEquals(Fibonacci.fastFib(1),1);
    }
    @Test
    void fibFast2() {
        assertEquals(Fibonacci.fastFib(2),1);
    }
    @Test
    void fibFast() {
        assertEquals(Fibonacci.fastFib(3),2);
    }
    @Test
    void fibFast4() {
        assertEquals(Fibonacci.fastFib(4),3);
    }
    @Test
    void fibFast5() {
        assertEquals(Fibonacci.fastFib(5),5);
    }
    @Test
    void fibStreamExecute(){
        FibonacciStream fib = new FibonacciStream();
        fib.stream().limit(20).forEachOrdered(System.out::println);
    }
}