package packagename

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import packagename.FactorialCalculator.computeFactorial

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FactorialCalculatorTest {

    @Test
    fun computeFactorial_withNegative_raiseException() {
        assertThrows(Exception::class.java) {
            computeFactorial(-1)
        }
    }

    @Test
    fun computeFactorial_forZero() {
        assertEquals(1, computeFactorial(0))
    }

    @Test
    fun computeFactorial_forFive() {
        assertEquals(120, computeFactorial(5))
    }
}