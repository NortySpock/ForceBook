package com.forcebook.person.datasource.mock;

import com.forcebook.person.biz.Person;
import com.forcebook.person.datasource.PersonDataSource;
import com.forcebook.person.datasource.PersonMetaData;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * A Mock implementation of a {@link PersonDataSource} that holds persons in
 * memory.
 * 
 * @author Jason W. Thompson -
 *         https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public class MockPersonDataSource implements PersonDataSource {
    private static final Map<String, Person> PERSONS_BY_UID = new HashMap<>();
    private static final Map<String, Person> DELETED_PERSONS_BY_UID = new HashMap<>();
    private static final Map<String, Person> PERSON_METADATA_BY_UID = new HashMap<>();

    @Override
    public String createPerson(Person person) {
        checkNotNull(person, "The parameter person cannot be null.");
        final String myUUID = UUID.randomUUID().toString();
        SimplePerson myPerson = new SimplePerson();
        myPerson.setUID(myUUID);
        myPerson.setName(person.getName());

        PERSONS_BY_UID.put(myUUID, myPerson);
        return myUUID;
    }

    @Override
    public void updatePerson(Person person) {
        checkNotNull(person, "The parameter person cannot be null.");
        PERSONS_BY_UID.put(person.getUID(), person);
    }

    @Override
    public void dropPersonByUID(String uid) {
        final Person temp = PERSONS_BY_UID.get(uid);
        if (temp != null) {
            DELETED_PERSONS_BY_UID.put(uid, temp);
            PERSONS_BY_UID.remove(uid);
        }
    }

    @Override
    public void restorePersonByUID(String uid) {
        final Person temp = DELETED_PERSONS_BY_UID.get(uid);
        if (temp != null) {
            PERSONS_BY_UID.put(uid, temp);
            DELETED_PERSONS_BY_UID.remove(uid);
        }
    }

    @Override
    public Optional<Person> retrievePersonByUID(String uid) {
        return Optional.ofNullable(PERSONS_BY_UID.get(uid));
    }

    @Override
    public Optional<PersonMetaData> retrievePersonMetaDataByUID(String UID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void clearMock() {
        PERSONS_BY_UID.clear();
        DELETED_PERSONS_BY_UID.clear();
        PERSON_METADATA_BY_UID.clear();
    }

}
