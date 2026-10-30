
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        assertEquals(10, calculator.add(5, 5));
    }

    @Test
    public void testSubtraction() {
        assertEquals(5, calculator.subtract(10, 5));
    }

    @Test
    public void testMultiplication() {
        assertEquals(50, calculator.multiply(10, 5));
    }
}

