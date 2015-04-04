package com.forcebook.person.datasource;

import com.forcebook.person.biz.Person;

/**
 * A data source for person data
 * @author Jason W. Thompson - https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public interface PersonDataSource
{
   /**
     * Creates a person in the data source
     * @param person The person to create. All properties must be populated.
     */
    void createPerson(Person person);
    
    /**
     * Retrieves a person by the person UID
     * @param uid The person UID
     */
    void retrievePersonByUID(String uid);
    
    /**
     * Updates a person. Values that are not {@code null} will be updated. UID
     * is required, however.
     * @param person The {@code Person} with the updated values.
     */
    void updatePerson(Person person);
    
    /**
     * Deletes the person with the given UID.
     * @param uid The UID of the person.
     */
    void dropPersonByUID(String uid);
    
    /**
     * Restores a deleted person. This may not be supported in all implementations.
     * @param uid The UID of the person to restore.
     * @throws UnsupportedOperationException if the operation is not supported in
     * this implementation.
     */
    void restorePersonByUID(String uid) throws UnsupportedOperationException;
}
