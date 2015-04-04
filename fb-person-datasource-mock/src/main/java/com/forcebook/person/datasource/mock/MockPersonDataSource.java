package com.forcebook.person.datasource.mock;

import com.forcebook.person.biz.Person;
import com.forcebook.person.datasource.PersonDataSource;
import com.forcebook.person.datasource.PersonMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * A Mock implementation of a {@link PersonDataSource} that holds persons in memory.
 * @author Jason W. Thompson - https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public class MockPersonDataSource implements PersonDataSource
{
    private static Map<String, Person> personsByUID = new HashMap<>();
    
    @Override
    public void createPerson(Person person)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updatePerson(Person person)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dropPersonByUID(String uid)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void restorePersonByUID(String uid) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person retrievePersonByUID(String uid)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PersonMetaData retrievePersonMetaDataByUID(String UID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
