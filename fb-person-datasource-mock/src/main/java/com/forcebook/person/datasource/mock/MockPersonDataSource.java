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

        //need previous person to be able to check for a force alignment change
        final Optional<Person> previousPerson = Optional.ofNullable(PERSONS_BY_UID.get(person.getUID()));
        PERSONS_BY_UID.put(person.getUID(), person);

        SimplePersonMetaData metadata = new SimplePersonMetaData(person.getUID());

        //if the force alignment changed
        if(previousPerson.isPresent()
                && previousPerson.get().getForceAlignment().isPresent()
                && person.getForceAlignment().isPresent()
                && previousPerson.get().getForceAlignment().get() != person.getForceAlignment().get()){

            //TODO: Throw exception if within 30 days
            metadata.setForceAlignmentUpdateTime();
        }
        PERSON_METADATA_BY_UID.put(person.getUID(), metadata);
    }



    @Override
    public void dropPersonByUID(String uid) {
        final Optional<Person> deletedPerson = Optional.ofNullable(PERSONS_BY_UID.get(uid));
        final Optional<PersonMetaData> deletedMetaData = Optional.ofNullable(PERSON_METADATA_BY_UID.get(uid));
        if (deletedPerson.isPresent() && deletedMetaData.isPresent()) {
            DELETED_PERSONS_BY_UID.put(uid, deletedPerson.get());
            PERSONS_BY_UID.remove(uid);
            DELETED_PERSON_METADATA_BY_UID.put(uid,deletedMetaData.get());
            PERSON_METADATA_BY_UID.remove(uid);
        }
    }

    @Override
    public void restorePersonByUID(String uid) {
        final Optional<Person> deletedPerson = Optional.ofNullable(DELETED_PERSONS_BY_UID.get(uid));
        final Optional<PersonMetaData> deletedMetaData = Optional.ofNullable(PERSON_METADATA_BY_UID.get(uid));
        if (deletedPerson.isPresent() && deletedMetaData.isPresent()) {
            PERSONS_BY_UID.put(uid, deletedPerson.get());
            DELETED_PERSONS_BY_UID.remove(uid);
            PERSON_METADATA_BY_UID.put(uid, deletedMetaData.get());
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

    public static void reset() {
        PERSONS_BY_UID.clear();
        DELETED_PERSONS_BY_UID.clear();
        PERSON_METADATA_BY_UID.clear();
        DELETED_PERSON_METADATA_BY_UID.clear();
    }

}
