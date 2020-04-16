package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * PhoneBook service implementation
 */
@Service
public class PhoneBook {

    @Autowired
    private InMemoryRepository repository;

    public PhoneBook() {
        // be careful this.repository will not be initialised if injection on setter is chosen
    }

    /**
     * injection is supported on constructor level.
     *
     * @param repository
     */
    // @Autowired
    public PhoneBook(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * injection is supported on setter level
     *
     * @param repository
     */
    public void setRepository(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * @return all pairs of type {name: [phone1, phone2]}
     */
    public Map<String, Set<String>> findAll() {
        return repository.findAll();
    }

    /**
     * TODO: please add required methods here
     */
    public String addEntry( String name, String phoneNumbers) {

        //duplicates validation skipped according to task description

        repository.addPhone(name, phoneNumbers);

        return ("Added successfully");
    }

    public void showAll(){
        for (Map.Entry<String, Set<String>> entry : repository.findAll().entrySet()){
            System.out.println( "Name = " + entry.getKey() + " has numbers = " + entry.getValue());
        }
    }

    public String removePhone(String phoneNumber) throws IllegalArgumentException{

        String user = null;
        //checking if the phone exists
        for (Map.Entry<String, Set<String>> entry : repository.findAll().entrySet()){
            if (entry.getValue().contains(phoneNumber)) {
                System.out.println("Entry detected");
                user = entry.getKey();

            }

        } 
        if (user == null){
            throw new IllegalArgumentException ("No such phone number in the book detected");
        }
        else{
            return repository.removePhone2(user, phoneNumber);

        }
    }
}
