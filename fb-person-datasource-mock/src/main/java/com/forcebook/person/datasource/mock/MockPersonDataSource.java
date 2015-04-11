package com.forcebook.person.datasource.mock;

import com.forcebook.person.biz.Person;
import com.forcebook.person.datasource.PersonDataSource;
import com.forcebook.person.datasource.PersonMetaData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A Mock implementation of a {@link PersonDataSource} that holds persons in
 * memory.
 * 
 * @author Jason W. Thompson -
 *         https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public class MockPersonDataSource implements PersonDataSource {
	private static Map<String, Person> personsByUID = new HashMap<>();
	private static Map<String, Person> deletedPersonsByUID = new HashMap<>();

	@Override
	/** Creates UID and person */
	public void createPerson(Person person) {
		String myUUID = UUID.randomUUID().toString();
		SimplePerson myPerson = new SimplePerson();
		myPerson.setUID(myUUID);
		myPerson.setName(person.getName());

		personsByUID.put(myUUID, myPerson);
	}

	@Override
	/** Updates UID with given Person */
	public void updatePerson(String uid, Person person) {
		personsByUID.put(uid, person);
	}

	@Override
	/** Moves person into "deleted" bin; can be restored.  */
	public void dropPersonByUID(String uid) {
		deletedPersonsByUID.put(uid, personsByUID.get(uid));
		personsByUID.remove(uid);
	}

	@Override
	/** Restore a deleted person */
	public void restorePersonByUID(String uid) {
		personsByUID.put(uid, deletedPersonsByUID.get(uid));
		deletedPersonsByUID.remove(uid);

	}

	@Override
	/** Get a Person. */
	public Person retrievePersonByUID(String uid) {
		return personsByUID.get(uid);
	}

	@Override
	public PersonMetaData retrievePersonMetaDataByUID(String UID) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
