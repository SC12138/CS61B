import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class testFlik {

    @Test
    public void testEqual(){
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        Integer c = new Integer(1);
        boolean compExp1 = false;
        boolean compExp2 = true;
        assertEquals(compExp1, Flik.isSameNumber(a, b));
        assertEquals(compExp2, Flik.isSameNumber(a, c));

    }
}
