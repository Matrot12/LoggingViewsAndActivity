package at.fh.mappdev.loggingviewsandactivity

import junit.framework.Assert.assertEquals
import org.junit.Test

class CalculatorTest {
    @Test
    fun testMultiply2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 * 2")
        assertEquals(4,result)
    }
    @Test
    fun testDivide2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 / 2")
        assertEquals(1,result)
    }
    @Test
    fun testAdd2to2() {
        val calculator = Calculator()
        val result = calculator.parse("2 + 2")
        assertEquals(1,result)
    }
    @Test
    fun testReduce2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 - 2")
        assertEquals(1,result)
    }
}

class Calculator {
    fun parse(s:String): Int {
        val (a, op, b) = s.split(" ")
        return when (op){
            "*" -> a.toInt() * b.toInt()
            "/" -> a.toInt() / b.toInt()
            "+" -> a.toInt() + b.toInt()
            "-" -> a.toInt() - b.toInt()

            else -> throw  IllegalArgumentException("Invalid operator")
        }
    }
}
