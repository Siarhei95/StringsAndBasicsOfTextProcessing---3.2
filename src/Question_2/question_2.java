package Question_2;//Напиcать анализатор, позволяющий последовательно возвращать содержимое узлов xml-документа и его тип  (открывающий тег, закрывающий тег, содержимое тега, тег без тела)

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class question_2 {
    public static void main(String[] args) {
        String teg = "<notes>" +
                "<note id = \"1\">" +
                "<to>Вася</to>" +
                "<from>Света</from>" +
                "<heading>Напоминание</heading>" +
                "<body>Позвони мне завтра!</body>" +
                "</note>" +
                "<note id = \"2\">" +
                "<to>Петя</to>" +
                "<from>Маша</from>" +
                "<heading>Важное напоминание</heading>" +
                "<body/>" +
                "</note>" +
                "</notes>";

        System.out.println("Opening tag: ");
        openingTag(teg);
        System.out.println();
        System.out.println("Closing tag: ");
        closingTag(teg);
        System.out.println();
        System.out.println("Tag content: ");
        tagContent(teg);
        System.out.println();
        System.out.println("Bodyless tag: ");
        bodylessTag(teg);
    }

        static void openingTag(String teg) {
            Pattern p = Pattern.compile("<\\w+[\\s\\w+\\s=\\s\"\\d\"]>");  //для <p>
            Matcher m = p.matcher(teg);
            while (m.find()) {
                System.out.println(m.start() + " " + m.group());
            }
        }
        static void closingTag(String teg) {
            Pattern p1 = Pattern.compile("<\\x2F\\w+>"); //для </p>, \x2F, который является шестнадцатеричным представлением /
            Matcher m1 = p1.matcher(teg);
            while (m1.find()) {
                System.out.println(m1.start() + " " + m1.group());
            }
            Pattern p12 = Pattern.compile("<\\w+[\\x2F]>"); //для <p/>
            Matcher m12 = p12.matcher(teg);
            while (m12.find()) {
                System.out.println(m12.start() + " " + m12.group());
            }
        }
        static void tagContent(String teg) {
            Pattern p2 = Pattern.compile("[^<\\w+>\\d\\s\\\"\\=\\x2F]"); //<p>содержимое тега</p>
            Matcher m2 = p2.matcher(teg);
            while (m2.find()) {
                System.out.println(m2.start() + " " + Arrays.toString(new String[]{m2.group()}));
            }
        }
        static void bodylessTag(String teg){
            Pattern p3 = Pattern.compile("[\\<\\x2F\\?\\x2F\\?\\>]"); //выводит </ />
            Matcher m3 = p3.matcher(teg);
            while (m3.find()){
                System.out.println(m3.start() + " " + m3.group());
        }
    }
}

