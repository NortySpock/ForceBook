package com.forcebook.person.biz.manager;

import com.forcebook.person.biz.Person;

/**
 * Manages a person.
 * @author Jason W. Thompson (google.com/+JasonWThompson_SoftwareDeveloper)
 */
public interface PersonManager
{
    /**
     * Adds or updates the person.
     * <p>
     * If the UID exists, this will replace all of
     * the non null properties with the properties of this person. Otherwise, a
     * new person will be added.
     * </p>
     * <p>
     * If the 
     * 
     * @param person The person to store.
     * @return The UID of the person after stored.
     */
    String storePerson(Person person);
    
    /**
     * Finds a {@code Person} by UID
     * @param uid The unique identifier of the person
     * @return The person identified by the {@code uid}
     */
    Person findPersonByUID(String uid);
}
