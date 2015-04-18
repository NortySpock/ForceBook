package com.forcebook.person.datasource;

import com.forcebook.person.biz.Person;

import java.util.Optional;

/**
 * A data source for person data
 * @author Jason W. Thompson - https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public interface PersonDataSource
{
    /**
     * Creates a person in the data source
     * @param person The person to create. This must not be {@code null} and all
     * properties must be populated.
     * @return The UID of the newly created person.
     */
    String createPerson(Person person);

    /**
     * Retrieves a person by the person UID.
     * @param uid The person UID. Cannot be {@code null}
     * @return An {@link Optional} that contains the person if present.
     */
    Optional<Person> retrievePersonByUID(String uid);

    /**
     * Retrieves a person's metadata by UID.
     * @param UID The UID. Cannot be {@code null}
     * @return An {@link Optional} that contains the person's metadata if present.
     */
    Optional<PersonMetaData> retrievePersonMetaDataByUID(String UID);

    /**
     * Updates a person. Values that are not {@code null} will be updated. UID
     * is required, however.
     * @param person The {@code Person} with the updated values. Cannot be {@code null}
     * @throws Exception
     */
    void updatePerson(Person person) throws Exception;

    /**
     * Deletes the person with the given UID.
     * @param uid The UID of the person.
     */
    void dropPersonByUID(String uid);

    /**
     * Restores a deleted person. This may not be supported in all implementations.
     * @param uid The UID of the person to restore. Cannot be {@code null}
     * @throws UnsupportedOperationException if the operation is not supported in
     * this implementation.
     */
    void restorePersonByUID(String uid) throws UnsupportedOperationException;

}
