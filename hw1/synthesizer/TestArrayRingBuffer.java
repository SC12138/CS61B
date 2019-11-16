package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        AbstractBoundedQueue<Double> arb = new ArrayRingBuffer(3);
        assertEquals(true, arb.isEmpty());
        arb.enqueue(5.0);
        arb.enqueue(6.0);
        arb.enqueue(128.0);
        for(Double k:arb){
            System.out.println(k);
        }
        assertEquals(true, arb.isFull());
        assertEquals(3, arb.fillCount());
        assertEquals(3, arb.capacity());
        Double expectDouble = 5.0;
        assertEquals(expectDouble, arb.peek());
        assertEquals(expectDouble, arb.dequeue());
        expectDouble = 6.0;
        assertEquals(expectDouble, arb.dequeue());
        arb.dequeue(); // empty now

        //arb.dequeue(); // StatusException






    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
