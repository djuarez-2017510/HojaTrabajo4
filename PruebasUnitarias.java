import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PruebasUnitarias{

    private StackArrayList<Integer> stack;

    @Before
    public void setUp() {
        stack = new StackArrayList<>();
    }

    @Test
    public void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
    }


    @Test
    public void testInfixToPostfix() {
        assertEquals("2 3 +", Convert.infixToPostfix("2+3"));
        assertEquals("2 3 + 4 *", Convert.infixToPostfix("(2+3)*4"));
        assertEquals("5 3 4 * +", Convert.infixToPostfix("5+3*4"));
        assertEquals("3 4 + 2 1 - *", Convert.infixToPostfix("(3+4)*(2-1)"));
        assertEquals("8 3 + 2 1 - *", Convert.infixToPostfix("(8+3)*(2-1)"));
    }
   
}