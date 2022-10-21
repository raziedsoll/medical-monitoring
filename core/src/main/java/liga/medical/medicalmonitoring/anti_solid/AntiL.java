package liga.medical.medicalmonitoring.anti_solid;

//Liskov Substitution
public class AntiL {

    //Класс, метод которого удваивает каждый символ строки
    class StringOperation {
        public String bifurcationOfLetters(String str) {
            char[] chars = str.toCharArray();
            StringBuilder result = new StringBuilder("");
            for (char aChar : chars) {
                result.append(aChar).append(aChar);
            }
            return result.toString();
        }
    }

    //Дочерний класс класса StringOperation, который должен обрабатывать
    //те же запросы, что и родитель, и выдывать тот же результат (или
    //относиться к тому же типу).
    //В данном случае происходит удвоение строки и возвращается длина
    //новой строки, хотя в результате должна быть именно удвоенная строка.
    class TextOperation extends StringOperation {
        @Override
        public String bifurcationOfLetters(String str) {
            String doubleString = str + str;
            return doubleString.length() + "";
        }
    }
}
