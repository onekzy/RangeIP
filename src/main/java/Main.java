
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

/* Задание
На вход программе задаются два IP адреса, программа перебирает все
допустимые адреса в введенном диапазоне и выдает их на экран.
Пример:
пользователь вводит с клавиатуры
192.168.0.1 и 192.168.0.5
Программа должна выдать
192.168.0.2
192.168.0.3
192.168.0.4 */


//Программа вывода диапазона IP-адресов сети класса "С" (изменяемые последний и предпоследний октеты)
public class Main {

    List<String> IP = new ArrayList();

    void printIpList() {
        for(String s : IP)
            System.out.println(s);
    }

    //Если различны только четвертые октеты
    public List<String> createSeqSimple(String firstIP, String lastIP) {
        String[] arr1 = firstIP.split("\\.");
        String[] arr2 = lastIP.split("\\.");

        for(int i = Integer.parseInt(arr1[arr1.length - 1]) + 1; i < Integer.parseInt(arr2[arr2.length - 1]); i++) {
            StringBuilder builder = new StringBuilder(firstIP);
            String template = builder.delete(10, builder.length()).toString();
            IP.add(template.concat(String.valueOf(i)));
        }
        return IP;
    }

    //Если различны третьи октеты
    public List<String> createSeq(String firstIP, String lastIP) {
        String[] arr1 = firstIP.split("\\.");
        String[] arr2 = lastIP.split("\\.");

        if(arr1[arr1.length - 2] != arr2[arr2.length - 2]) {
            for(int j = Integer.parseInt(arr1[arr1.length - 2]); j <= Integer.parseInt(arr2[arr2.length - 2]); j++) {
                StringBuilder builder = new StringBuilder(firstIP);
                String template = builder.delete(8, builder.length()).toString().concat(String.valueOf(j));
                String compareLine = template.substring(8);
                template = template.concat(".");

                //Является ли текущий третий отктет последним
                int x = compareLine.equals(arr2[arr2.length-2]) ? Integer.parseInt(arr2[arr2.length - 1])-1:255;

                    for(int i = Integer.parseInt(arr1[arr1.length - 1]) + 1; i <=  x; i++) {
                        String template2 = template.concat(String.valueOf(i));
                        IP.add(template2);
                    }
            }
        } else createSeqSimple(firstIP, lastIP);
        return IP;
    }

    public static void main(String[] args) {

        Main main = new Main();

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите диапазон IP-адресов в формате XXX.XXX.X.X");

        String firstIP = sc.nextLine();
        System.out.println("Первый IP-адрес: " + firstIP);

        String lastIP = sc.nextLine();
        System.out.println("Последний IP-адрес: " + lastIP);

        main.createSeq(firstIP, lastIP);
        main.printIpList();

    }
}
