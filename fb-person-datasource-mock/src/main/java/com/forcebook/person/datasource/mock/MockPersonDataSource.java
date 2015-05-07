package com.forcebook.person.datasource.mock;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.forcebook.person.biz.Person;
import com.forcebook.person.datasource.PersonDataSource;
import com.forcebook.person.datasource.PersonMetaData;

/**
 * A Mock implementation of a {@link PersonDataSource} that holds persons in
 * memory.
 *
 * @author Jason W. Thompson -
 *         https://google.com/+JasonWThompson_SoftwareDeveloper
 * @author David K. Norton david.k.norton@gmail.com
 */
public class MockPersonDataSource implements PersonDataSource {
    private static final Map<String, Person> PERSONS_BY_UID = new HashMap<>();
    private static final Map<String, Person> DELETED_PERSONS_BY_UID = new HashMap<>();
    private static final Map<String, PersonMetaData> PERSON_METADATA_BY_UID = new HashMap<>();
    private static final Map<String, PersonMetaData> DELETED_PERSON_METADATA_BY_UID = new HashMap<>();

    @Override public String createPerson(final Person person) {
        checkNotNull(person, "The parameter person is null.");
        final String uid = UUID.randomUUID().toString();
        final SimplePerson myPerson = new SimplePerson();
        myPerson.setUID(uid);
        myPerson.setName(person.getName());
        myPerson.setForceAlignment(person.getForceAlignment());
        final SimplePersonMetaData metadata = new SimplePersonMetaData();

        PERSONS_BY_UID.put(uid, myPerson);
        PERSON_METADATA_BY_UID.put(uid, metadata);
        return uid;
    }

    @Override public void updatePerson(final Person person) {
        checkNotNull(person, "The parameter person is null.");
        Optional.of(person.getName()).orElseThrow(() -> new IllegalArgumentException("Cannot update person if new person has no name"));
        Optional.of(person.getForceAlignment()).orElseThrow(() -> new IllegalArgumentException("Cannot update person if new person has no ForceAlignment"));
        final String personUID = person.getUID();
        // need previous person to be able to check for a force alignment change
        final Person existingPerson = Optional.ofNullable(PERSONS_BY_UID.get(person.getUID())).orElseThrow(() -> new IllegalArgumentException("Cannot update non-existent person identified by the UID ["
                + personUID + "]"));

        PERSONS_BY_UID.put(person.getUID(), person);

        final SimplePersonMetaData metadata = new SimplePersonMetaData();

        // if the force alignment status changed (LIGHT vs DARK)
        if (existingPerson.getForceAlignment().isPresent() != person.getForceAlignment().isPresent()
                || existingPerson.getForceAlignment().get() != person.getForceAlignment().get()) {
            metadata.setForceAlignmentUpdateTime();
        } else {
            // need to store previous metadata value
            final PersonMetaData existingMetadata = Optional.ofNullable(PERSON_METADATA_BY_UID.get(person.getUID())).orElseThrow(() -> new IllegalArgumentException("Cannot update non-existant person metadata identified by the UID ["
                    + personUID + "]"));
            metadata.setForceAlignmentUpdateTime(existingMetadata.getForceAlignmentUpdateTime());
        }
        PERSON_METADATA_BY_UID.put(person.getUID(), metadata);
    }

    @Override public void dropPersonByUID(final String uid) {
        final Optional<Person> deletedPerson = Optional.ofNullable(PERSONS_BY_UID.get(uid));
        final Optional<PersonMetaData> deletedMetaData = Optional.ofNullable(PERSON_METADATA_BY_UID.get(uid));
        if (deletedPerson.isPresent() && deletedMetaData.isPresent()) {
            DELETED_PERSONS_BY_UID.put(uid, deletedPerson.get());
            PERSONS_BY_UID.remove(uid);
            DELETED_PERSON_METADATA_BY_UID.put(uid, deletedMetaData.get());
            PERSON_METADATA_BY_UID.remove(uid);
        }
    }

    @Override public void restorePersonByUID(final String uid) {
        final Optional<Person> deletedPerson = Optional.ofNullable(DELETED_PERSONS_BY_UID.get(uid));
        final Optional<PersonMetaData> deletedMetaData = Optional.ofNullable(PERSON_METADATA_BY_UID.get(uid));
        if (deletedPerson.isPresent() && deletedMetaData.isPresent()) {
            PERSONS_BY_UID.put(uid, deletedPerson.get());
            DELETED_PERSONS_BY_UID.remove(uid);
            PERSON_METADATA_BY_UID.put(uid, deletedMetaData.get());
            DELETED_PERSON_METADATA_BY_UID.remove(uid);
        }
    }

    @Override public Optional<Person> retrievePersonByUID(final String uid) {
        return Optional.ofNullable(PERSONS_BY_UID.get(uid));
    }

    @Override public Optional<PersonMetaData> retrievePersonMetaDataByUID(
            final String uid) {
        return Optional.ofNullable(PERSON_METADATA_BY_UID.get(uid));
    }

    public static void reset() {
        PERSONS_BY_UID.clear();
        DELETED_PERSONS_BY_UID.clear();
        PERSON_METADATA_BY_UID.clear();
        DELETED_PERSON_METADATA_BY_UID.clear();
    }

}
