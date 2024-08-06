package HW6.Controller;


import java.util.Objects;
import java.util.regex.Pattern;

import HW6.Exceptions.BirthDayException;
import HW6.Exceptions.LanguageException;
import HW6.Exceptions.NameFirstException;
import HW6.Exceptions.NameLastException;
import HW6.Exceptions.NameSecondException;
import HW6.Exceptions.PhoneNumberException;
import HW6.Exceptions.SexException;

public class Checks {
    public String[] getArrayEnteredData(String data) {
        String[] dataArray = data.split(" ");
        return dataArray;
    }

    public int checkQuantityData(String data) {
        String[] dataArray = getArrayEnteredData(data);
        int result = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray.length > 6) {
                result = -1;
            } else if (dataArray.length < 6) {
                result = 1;
            } else if (dataArray.length == 6) {
                result = 0;
            }
        }
        return result;
    }

    public void checkDate(String birthDate) throws BirthDayException{
        String[] date = birthDate.split("\\.");
        if (date.length != 3) {
            throw new BirthDayException("Поставьте разделитель '.'");
        }

        String day = date[0];
        String month = date[1];
        String year = date[2];
        
        boolean isDay = day.matches("-?\\d+");
        boolean isMonth = month.matches("-?\\d+");
        boolean isYear = year.matches("-?\\d+");
        
        int dayNum;
        int monthNum;
        int yearNum;

        if (isDay && isMonth && isYear) {
            dayNum = Integer.parseInt(day);
            monthNum = Integer.parseInt(month);
            yearNum = Integer.parseInt(year);
        }else{
            throw new BirthDayException(year);
        }

        if (day.length() != 2) {
            throw new BirthDayException("День рождения необходимо указать в формате 'dd'");
        } else if (month.length() != 2) {
            throw new BirthDayException("Месяц рождения необходимо указать в формате 'MM'");
        } else if (year.length() != 4) {
            throw new BirthDayException("Год рождения необходимо указать в формате 'yyyy'");
        } else if (dayNum < 1 || dayNum > 31) {
            throw new BirthDayException("Укажите дату с 1 по 31");
        } else if (monthNum < 1 || monthNum > 12) {
            throw new BirthDayException("Укажите месяц с 1 по 12");
        } else if (monthNum == 02 && dayNum == 29) {
            if (yearNum % 4 != 0 || yearNum % 400 != 0 && yearNum % 100 == 0) {
                throw new BirthDayException("В феврале этого года 28 дней");
            }
        } else if (monthNum == 02 && dayNum > 28) {
            throw new BirthDayException("Укажите дату с 1 по 28");
        } else if (dayNum > 30) {
            if (monthNum == 04 || monthNum == 06 || monthNum == 9 || monthNum == 11) {
                throw new BirthDayException("В этом месяце 30 дней");
            }
        }
    }

    public boolean checkName(String name){
        char charName = name.charAt(0);
        String firstName = Character.toString(charName);
        String secondName = "";
        for (int i = 0; i < name.length(); i++) {
            secondName += name.charAt(i);
        }
        boolean isName = Pattern.matches("[A-Z]+", firstName) && Pattern.matches("[a-z]", secondName) ||
        Pattern.matches("[А-Я]+", firstName) && Pattern.matches("[а-я]", secondName);
        return isName;
    }

    public boolean checkLanguage(String lastName, String firstName, String secondName){
        boolean isLanguage = Pattern.matches("[a-zA-Z]+", lastName)
                && Pattern.matches("[a-zA-Z]+", firstName) &&
                Pattern.matches("[a-zA-Z]+", secondName) ||
                Pattern.matches("[а-яА-Я]+", lastName) &&
                        Pattern.matches("[а-яА-Я]+", firstName) &&
                        Pattern.matches("[а-яА-Я]+", secondName);
        return isLanguage;
    }

    public void checkLanguageName(String lastName, String firstName, String secondName) throws LanguageException {
        if (!checkLanguage(lastName, firstName, secondName)){
            throw new LanguageException("Языки ввода фамилии, имени и отчества отличаются");
        }
    }

    public void checkLastName(String lastName) throws NameLastException {
        if(!checkName(lastName)){
            throw new NameLastException("Неверный формат фамилии");
        }
    }

    public void checkFirstName(String firstName) throws NameFirstException{
        if(!checkName(firstName)){
            throw new NameFirstException("Неверный формат имени");
        }
    }

    public void checkSecondName(String secondName) throws NameSecondException {
        if(!checkName(secondName)){
            throw new NameSecondException("Неверный формат отчества"); 
        }
    }

    public void checkPhoneNumber(String telephoneNumber) throws PhoneNumberException {
        boolean isTelNum = telephoneNumber.matches("-?\\d+");
        if (!isTelNum) {
            throw new PhoneNumberException();
        }
    }

    public void checkSex(String gender) throws SexException {
        if (!Objects.equals(gender, "m") && !Objects.equals(gender, "f")) {
            throw new SexException();
        }
    }

}
