package liga.medical.medicalmonitoring.anti_solid;

//Dependency Inversion
public class AntiD {

    interface Operation {
        int execute(int a, int b);
    }

    class Addition implements Operation {
        @Override
        public int execute(int a, int b) {
            return a + b;
        }
    }

    class Subtraction implements Operation {
        @Override
        public int execute(int a, int b) {
            return a - b;
        }
    }

    class Multiplication implements Operation {
        @Override
        public int execute(int a, int b) {
            return a * b;
        }
    }

    class Division implements Operation {
        @Override
        public int execute(int a, int b) {
            return a / b;
        }
    }

    //Прямое взаимодействие с классом Addition, вместо использования интерфейса Operation
    class Calculator {
        public void calculate(int a, int b) {
            Addition addition = new Addition();
            addition.execute(a, b);
        }
    }

}
