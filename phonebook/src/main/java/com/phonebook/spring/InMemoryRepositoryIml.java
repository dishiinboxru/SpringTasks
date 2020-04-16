package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Keeps phoneBook data in memory in ordered in accordance to addition.
 */
@Repository
public class InMemoryRepositoryIml implements InMemoryRepository {

    private Map<String, Set<String>> data;

    /**
     * no args constructor
     */
    public InMemoryRepositoryIml() {
        // LinkedHashMap is chosen because usually iteration order matters
        this(new LinkedHashMap<>());
    }

    /**
     * this constructor allows to inject initial data to the repository
     *
     * @param data
     */
    public InMemoryRepositoryIml(Map<String, Set<String>> data) {
        this.data = new LinkedHashMap<>(data);
    }

    @Override
    public Map<String, Set<String>> findAll() {
        return new LinkedHashMap<>(this.data);
    }

    @Override
    public Set<String> findAllPhonesByName(String name) {
                return this.data.get(name);

    }

    @Override
    public String findNameByPhone(String phone) {
        Set <String> numbers = new HashSet<String>(Arrays.asList(phone.split(",")));
        try {
            for (Map.Entry<String, Set<String>> entry : this.data.entrySet()) {
                if (entry.getValue().contains(numbers)) {
                    System.out.println("Entry detected");
                    return entry.getKey();
                }
                return null;
                }
            } catch( Exception e ) {
                System.out.println("no name with such phone detected");
                return null;
            }

        return null;
    }


    @Override
    public void addPhone(String name, String phone) {
        Set <String> numbers = new HashSet<String>(Arrays.asList(phone.split(","))) ;

        data.put(name, numbers);
    }

    @Override
    public void removePhone(String phone) throws IllegalArgumentException {
        Set <String> numbers = new HashSet<String>(Arrays.asList(phone.split(",")));
        String nameForRemoval = findNameByPhone(phone);
        this.data.remove(nameForRemoval, numbers);

        System.out.println("Phone removed successfully");
    }

    public String removePhone2(String name , String phone) throws IllegalArgumentException {
        Set <String> numbers = new HashSet<String>(Arrays.asList(phone.split(",")));
        Set <String> entryNumbers = findAllPhonesByName(name);


        if (numbers.equals(entryNumbers)){
            this.data.remove(name, numbers);
            return "single phone for all entry, removing entry ";

        }

        else {
            System.out.println("several phones for an entry , removing just one of" + entryNumbers.toString());
            entryNumbers.remove(phone);
            this.data.replace(name,entryNumbers);
            return " now there are "+ entryNumbers.toString() + " for " + name;
        }
    }
}
