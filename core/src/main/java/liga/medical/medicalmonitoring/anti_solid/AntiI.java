package liga.medical.medicalmonitoring.anti_solid;

//Interface Segregation
public class AntiI {

    interface Flying {
        void fly();
    }

    interface Running {
        void run();
    }

    //Самолет бегать не может, но интерфейс Running имплементирует.
    class Airplane implements Flying, Running {
        @Override
        public void fly() {
        }

        @Override
        public void run() {
        }
    }
}
