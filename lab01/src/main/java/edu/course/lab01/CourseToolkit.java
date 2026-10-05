package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }
    
    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        if (number == 2) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }
        
        for (int i = 3; i <= Math.sqrt(number); i+= 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Строка не должна быть пустой");
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static double average(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        else {
            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }
            return (double) sum / numbers.length;
        }
    }

    public static int min(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        else {
            int minValue = numbers[0];
            for (int number : numbers) {
                if (number < minValue) {
                    minValue = number;
                }
            }
            return minValue;
        }
    }

    public static int max(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        else {
            int maxValue = numbers[0];
            for (int number : numbers) {
                if (number > maxValue) {
                    maxValue = number;
                }
            }
            return maxValue;
        }
    }
}
