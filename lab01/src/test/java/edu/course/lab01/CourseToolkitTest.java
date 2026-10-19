package edu.course.lab01;

import org.junit.jupiter.api.Test;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        int number = -8;
        boolean result = CourseToolkit.isEven(number);

        assertTrue(result);
    }


    @Test
    void testIsPrimeLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(1));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(-5));
    }

    @Test
    void testIsPrimeTwoReturnsTrue() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void testIsPrimeValidPrimes() {
        assertTrue(CourseToolkit.isPrime(3));
        assertTrue(CourseToolkit.isPrime(5));
        assertTrue(CourseToolkit.isPrime(7));
        assertTrue(CourseToolkit.isPrime(11));
        assertTrue(CourseToolkit.isPrime(13));
    }

    @Test
    void testIsPrimeCompositesAndSquaresOfPrimes() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(6));
        assertFalse(CourseToolkit.isPrime(9));
        assertFalse(CourseToolkit.isPrime(15));
        assertFalse(CourseToolkit.isPrime(25));
        assertFalse(CourseToolkit.isPrime(49));
    }


    @Test
    void testIsPalindromeValidStrings() {
        assertTrue(CourseToolkit.isPalindrome(""));
        assertTrue(CourseToolkit.isPalindrome("a"));
        assertTrue(CourseToolkit.isPalindrome("racecar"));
        assertTrue(CourseToolkit.isPalindrome("топот"));
    }

    @Test
    void testIsPalindromeCaseAndSpaceSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Racecar"));
        assertFalse(CourseToolkit.isPalindrome("race car"));
        assertFalse(CourseToolkit.isPalindrome("abc"));
    }

    @Test
    void testIsPalindromeNullThrowsException() {
        boolean thrown = false;
        try {
            CourseToolkit.isPalindrome(null);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }


    @Test
    void testAverageNormalScenario() {
        int[] values = {1, 2, 3, 4};
        int[] copy = values.clone();

        double result = CourseToolkit.average(values);

        assertTrue(result == 2.5);
        assertTrue(Arrays.equals(copy, values));
    }

    @Test
    void testAverageNegativeValuesScenario() {
        int[] values = {-10, -20, -30};
        double result = CourseToolkit.average(values);

        assertTrue(result == -20.0);
    }

    @Test
    void testAverageNullOrEmptyThrowsException() {
        boolean thrownForNull = false;
        try {
            CourseToolkit.average(null);
        } catch (IllegalArgumentException e) {
            thrownForNull = true;
        }

        boolean thrownForEmpty = false;
        try {
            CourseToolkit.average(new int[]{});
        } catch (IllegalArgumentException e) {
            thrownForEmpty = true;
        }

        assertTrue(thrownForNull);
        assertTrue(thrownForEmpty);
    }


    @Test
    void testMinAndMaxNormalScenario() {
        int[] values = {3, 1, 4, 1, 5, 9};

        assertTrue(CourseToolkit.min(values) == 1);
        assertTrue(CourseToolkit.max(values) == 9);
    }
}
