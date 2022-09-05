package Test;
/**
 * @autor Nurbolot Gulamidinov
 * @version 1.0.0
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите :");
        String userInput = scanner.nextLine();
        String result = calc(userInput);
        System.out.println(result);
    }

    /**
     * выполнять операции сложения, вычитания, умножения и деления с двумя числами: a и b
     *
     * @param input - Введенный пользователем арифметическая операция
     * @return результат арифметической операции в строковом виде
     * @throws Exception бросает исключение
     */
    public static String calc(String input) throws Exception {
        String[] userInput = input.split(" ");
        if (userInput.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (userInput.length < 3) {
            throw new Exception("строка не является математической операцией");
        }
        if (isNumber(userInput[0]) && isNumber(userInput[2])) {
            int num1 = Integer.parseInt(userInput[0]), num2 = Integer.parseInt(userInput[2]);
            return arabicCalc(userInput[1], num1, num2);
        } else if (isRomanNumber(userInput[0]) && isRomanNumber(userInput[2])) {
            return romanCalc(userInput);
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }
    }

    /**
     * выполнять операции сложения, вычитания, умножения и деления с двумя римскими числами: a и b
     *
     * @param userInput - Введенный пользователем арифметическая операция
     * @return результат арифметической операции в строковом виде
     * @throws Exception бросает исключение
     */

    private static String romanCalc(String[] userInput) throws Exception {
        int res = Integer.parseInt(arabicCalc(userInput[1], romanToInt(userInput[0]), romanToInt(userInput[2])));
        if (res <= 0) {
            throw new Exception("в римской системе нет 0 и отрицательных чисел");
        }
        return IntegerToRomanNumeral(res);
    }

    private static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

    private static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        int output = 0;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return map.get(String.valueOf(s.charAt(0)));

        for (int i = 0; i < (s.length() - 1); i++) {
            if (map.get(String.valueOf(s.charAt(i))) >=
                    map.get(String.valueOf(s.charAt(i + 1)))) {
                output = output + map.get(String.valueOf(s.charAt(i)));
            } else {
                output = output - map.get(String.valueOf(s.charAt(i)));
            }

        }
        output = output + map.get(String.valueOf(s.charAt(s.length() - 1)));
        return output;
    }

    /**
     * выполнять операции сложения, вычитания, умножения и деления с двумя числами: a и b
     *
     * @param arithmeticSymbol - арифметическая операция
     * @param num1             - первое число
     * @param num2             - второе число
     * @return результат арифметической операции в строковом виде
     */
    private static String arabicCalc(String arithmeticSymbol, int num1, int num2) {
        if ((num1 >= 1 && num1 <= 10) && (num2 >= 1 && num2 <= 10)) {
            switch (arithmeticSymbol) {
                case "+":
                    return String.valueOf(num1 + num2);
                case "-":
                    return String.valueOf(num1 - num2);
                case "/":
                    return String.valueOf(num1 / num2);
                case "*":
                    return String.valueOf(num1 * num2);
                default:
                    return "такой операции не существует!";
            }
        } else {
            return "Калькулятор работает только с числами от 1 до 10!";
        }
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isRomanNumber(String str) {
        for (int i = 1; i <= 101; i++) {
            if (str.equalsIgnoreCase(IntegerToRomanNumeral(i))) {
                return true;
            }
        }
        return false;
    }
}
