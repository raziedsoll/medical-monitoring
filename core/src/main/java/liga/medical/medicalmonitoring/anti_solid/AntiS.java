package liga.medical.medicalmonitoring.anti_solid;

// Single Responsibility
// Хранит информацию о сотруднике
// Может посчитать арифметические операции
// Может раздвоить каждый символ строки

public class AntiS {

    private String nameEmployee;
    private String surnameEmployee;
    private String department;
    private int salaryEmployee;

    public int addition(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int multiplication(int a, int b) {
        return a * b;
    }

    public int division(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    public String bifurcationOfLetters(String str) {
        char[] chars = str.toCharArray();
        StringBuilder result = new StringBuilder("");
        for (char aChar : chars) {
            result.append(aChar).append(aChar);
        }
        return result.toString();
    }
}
