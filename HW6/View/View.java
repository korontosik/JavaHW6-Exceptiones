package HW6.View;

import java.util.Scanner;

public class View {
    
    public String enterData(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные через пробел:\n" + 
            "Фамилия, Имя, Отчество, Дата рождения(dd.mm.yyyy), Номер телефона, Пол (f/m).");
            String data = scanner.nextLine();
            return data;
        }
    }
}
