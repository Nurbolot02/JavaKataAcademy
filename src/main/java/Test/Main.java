package Test;
/**
 * @autor Nurbolot Gulamidinov
 * @version 1.0.0
 */

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
        RomanNumberEnum romanNumberEnum1 = RomanNumberEnum.valueOf(userInput[0]);
        RomanNumberEnum romanNumberEnum2 = RomanNumberEnum.valueOf(userInput[2]);
        int res = Integer.parseInt(arabicCalc(userInput[1], romanNumberEnum1.getTranslation(), romanNumberEnum2.getTranslation()));
        if (res < 0) {
            throw new Exception("в римской системе нет отрицательных чисел");
        }
        for (RomanNumberEnum rm : RomanNumberEnum.values()) {
            if (rm.getTranslation() == res) {
                return rm.toString();
            }
        }
        return "";
    }

    /**
     *  выполнять операции сложения, вычитания, умножения и деления с двумя числами: a и b
     * @param arithmeticSymbol - арифметическая операция
     * @param num1 - первое число
     * @param num2 - второе число
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
        try {
            RomanNumberEnum romanNumberEnum = RomanNumberEnum.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
