package com.phonebook.main;

import java.util.Map;
import java.util.Set;

/**
 * Repository interface
 */
public interface InMemoryRepository {
    /**
     * @return all repository records "{name: [phone1, phone2]}"
     */
    Map<String, Set<String>> findAll();

    /**
     * @param name
     * @return all phone numbers associated with a name
     */
    Set<String> findAllPhonesByName(String name);

    /**
     * @param phone
     * @return name associated with a phone
     */
    String findNameByPhone(String phone);

    /**
     * add phone number for a name or create new record
     *
     * @param name
     * @param phone
     */
    void addPhone(String name, String phone);

    /**
     * removes a phone from set. If set becomes empty after deletion remove record "{name:[phone]}" completely
     *
     * @param phone
     * @throws IllegalArgumentException if there is no such phone in repo
     */
    void removePhone(String phone) throws IllegalArgumentException;

    String removePhone2(String user, String phoneNumber);
}
