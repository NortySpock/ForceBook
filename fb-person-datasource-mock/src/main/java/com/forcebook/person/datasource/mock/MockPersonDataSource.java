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
 * @author David K. Norton 
 *         david.k.norton@gmail.com
 */
public class MockPersonDataSource implements PersonDataSource {
    private static final Map<String, Person> PERSONS_BY_UID = new HashMap<>();
    private static final Map<String, Person> DELETED_PERSONS_BY_UID = new HashMap<>();
    private static final Map<String, PersonMetaData> PERSON_METADATA_BY_UID = new HashMap<>();
    private static final Map<String, PersonMetaData> DELETED_PERSON_METADATA_BY_UID = new HashMap<>();

    @Override
    public String createPerson(Person person) {
        checkNotNull(person, "The parameter person is null.");
        final String myUUID = UUID.randomUUID().toString();
        SimplePerson myPerson = new SimplePerson();
        myPerson.setUID(myUUID);
        myPerson.setName(person.getName());        
        SimplePersonMetaData metadata = new SimplePersonMetaData(myUUID);

        PERSONS_BY_UID.put(myUUID, myPerson);
        PERSON_METADATA_BY_UID.put(myUUID, metadata);
        return myUUID;
    }

    @Override
    public void updatePerson(Person person) {
        checkNotNull(person, "The parameter person is null.");
        PERSONS_BY_UID.put(person.getUID(), person);
    }
    
    @Override
    public void updateMetadata(PersonMetaData metadata) {
        checkNotNull(metadata, "The parameter metadata is null.");
        PERSON_METADATA_BY_UID.put(metadata.getUID(), metadata);
    }

    @Override
    public void dropPersonByUID(String uid) {
        final Person deletedPerson = PERSONS_BY_UID.get(uid);
        final PersonMetaData deletedMetaData = PERSON_METADATA_BY_UID.get(uid);
        if (deletedPerson != null) {
            DELETED_PERSONS_BY_UID.put(uid, deletedPerson);
            PERSONS_BY_UID.remove(uid);
            DELETED_PERSON_METADATA_BY_UID.put(uid,deletedMetaData);
            PERSON_METADATA_BY_UID.remove(uid);
        }
    }

    @Override
    public void restorePersonByUID(String uid) {
        final Person deletedPerson = DELETED_PERSONS_BY_UID.get(uid);
        final PersonMetaData deletedMetaData = PERSON_METADATA_BY_UID.get(uid);
        if (deletedPerson != null) {
            PERSONS_BY_UID.put(uid, deletedPerson);
            DELETED_PERSONS_BY_UID.remove(uid);
            PERSON_METADATA_BY_UID.put(uid, deletedMetaData);
            DELETED_PERSON_METADATA_BY_UID.remove(uid);
        }
    }

    @Override
    public Optional<Person> retrievePersonByUID(String uid) {
        return Optional.ofNullable(PERSONS_BY_UID.get(uid));
    }    

    @Override
    public Optional<PersonMetaData> retrievePersonMetaDataByUID(String uid) {
        return Optional.ofNullable(PERSON_METADATA_BY_UID.get(uid));
    }

    public static void clearMock() {
        PERSONS_BY_UID.clear();
        DELETED_PERSONS_BY_UID.clear();
        PERSON_METADATA_BY_UID.clear();
        DELETED_PERSON_METADATA_BY_UID.clear();
    }

}
