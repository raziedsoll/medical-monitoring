package liga.medical.medicalmonitoring.anti_solid;

import lombok.Getter;
import lombok.Setter;

//Open-Closed
public class AntiO {

    interface CalculatorOperation {
    }

    //Сложение
    @Getter
    @Setter
    class Addition implements CalculatorOperation {
        private double left;
        private double right;
        private double result;

        Addition(double left, double right) {
            this.left = left;
            this.right = right;
        }
    }

    //Вычитание
    @Setter
    @Getter
    class Subtraction implements CalculatorOperation {
        private double left;
        private double right;
        private double result;

        Subtraction(double left, double right) {
            this.left = left;
            this.right = right;
        }
    }

    //Применение сложения и вычитания.
    //Изменять метод calculate придется тогда, когда мы решим
    //добавить операции умножения и деления.
    class Calculator {
        public void calculate(CalculatorOperation operation) {
            if (operation instanceof Addition) {
                Addition addition = (Addition) operation;
                addition.setResult(addition.getLeft() + addition.getRight());
            } else if (operation instanceof Subtraction) {
                Subtraction subtraction = (Subtraction) operation;
                subtraction.setResult(subtraction.getLeft() - subtraction.getRight());
            }
        }
    }

}
