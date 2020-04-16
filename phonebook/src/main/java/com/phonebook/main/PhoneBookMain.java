package com.phonebook.main;

import com.phonebook.spring.ApplicationConfig;
import com.phonebook.spring.PhoneBook;
import com.phonebook.spring.PhoneBookFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * PhoneBook entry point
 */
public class PhoneBookMain {

    public static void main(String[] args) {
        ApplicationContext context = newApplicationContext(args);

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.getProperty("line.separator"));

        PhoneBook phoneBook = context.getBean("phoneBook", PhoneBook.class);
        PhoneBookFormatter renderer = (PhoneBookFormatter) context.getBean("phoneBookFormatter");

        renderer.info("type 'exit' to quit.");
        System.out.println(phoneBook);
        System.out.println(phoneBook.findAll());
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("exit")) {
                renderer.info("Have a good day...");
                break;
            }
            try {// TODO: add your code here
                if (line.contains("ADD")) {

                    //splitting input into blocks
                    String name = line.split(" ")[1] ;
                    String phoneNumbers = line.split(" ")[2] ;

                    renderer.info(phoneBook.addEntry(name,phoneNumbers));

                }
                if (line.contains("REMOVE")) {

                    //splitting input into blocks
                    String phoneNumber = line.split(" ")[1] ;

                    try {
                        renderer.info(phoneBook.removePhone(phoneNumber));
                    }catch ( IllegalArgumentException e ){
                        renderer.error(e);
                    }

                }
                if (line.contains("SHOW")) {

                    phoneBook.showAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
                renderer.error(e);
            }
        }
    }

    static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equals("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }

}
