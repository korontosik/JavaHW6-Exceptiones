package HW6.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import HW6.Exceptions.BirthDayException;
import HW6.Exceptions.LanguageException;
import HW6.Exceptions.NameFirstException;
import HW6.Exceptions.NameLastException;
import HW6.Exceptions.NameSecondException;
import HW6.Exceptions.PhoneNumberException;
import HW6.Exceptions.SexException;
import HW6.View.View;

public class CreateFile {
    private Checks checks;
    private View view;

    public CreateFile(Checks checks, View view) {
        this.checks = checks;
        this.view = view;
    }

    public void createFile(String data) throws SexException, BirthDayException,
            PhoneNumberException, NameFirstException, NameSecondException, NameLastException, LanguageException {
        try {
            String lastName = checks.getArrayEnteredData(data)[0];
            checks.checkLastName(lastName);
            String firstName = checks.getArrayEnteredData(data)[1];
            checks.checkFirstName(firstName);
            String secondName = checks.getArrayEnteredData(data)[2];
            checks.checkSecondName(secondName);
            checks.checkLanguageName(lastName, firstName, secondName);
            String birthDay = checks.getArrayEnteredData(data)[3];
            checks.checkDate(birthDay);
            String phoneNumber = checks.getArrayEnteredData(data)[4];
            checks.checkPhoneNumber(phoneNumber);
            String sex = checks.getArrayEnteredData(data)[5];
            checks.checkSex(sex);

            String contactData = " " + lastName + " " + " " + firstName + " " + " " + secondName + " " 
            + " " + birthDay + " " + " " + phoneNumber + " " + " " + sex + " ";


            File file = new File(lastName);
            FileWriter writer = new FileWriter(file, true);
            boolean check = Files.lines(Paths.get(lastName), StandardCharsets.UTF_8).anyMatch(contactData::equals);
            if (!check) {
                writer.write(contactData + "\n");
            } else {
                System.out.println("Такая запись уже существует");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        } catch (BirthDayException e) {
            System.out.println(e.getMessage());
        } catch (SexException e) {
            System.out.println("Неверный формат пола (Введите 'm' или 'f')");
        } catch (PhoneNumberException e) {
            System.out.println("Неверный формат номера телефона (Вводите только цифры)");
        } catch (NameFirstException e) {
            System.out.println(e.getMessage());
        } catch (NameSecondException e) {
            System.out.println(e.getMessage());
        } catch (NameLastException e) {
            System.out.println(e.getMessage());
        } catch (LanguageException e) {
            System.out.println(e.getMessage());
        }
    }

        public void start(){
        String data = view.enterData();
            try {
                int result = checks.checkQuantityData(data);
                if (result == 1) {
                    createFile(data);
                } else if (result == -1) {
                    System.out.println("Вы ввели больше данных");
                } else if (result == -2) {
                    System.out.println("Вы ввели меньше данных");
                }
            } catch (BirthDayException e) {
                System.out.println(e.getMessage());
            } catch (SexException e) {
                System.out.println(e.getMessage());
            } catch (PhoneNumberException e) {
                System.out.println(e.getMessage());
            } catch (NameFirstException e) {
                System.out.println(e.getMessage());
            } catch (NameSecondException e) {
                System.out.println(e.getMessage());
            } catch (NameLastException e) {
                System.out.println(e.getMessage());
            } catch (LanguageException e) {
                System.out.println(e.getMessage());
            }
        }
    }
