package com.forcebook.person.biz.datasource;

import com.forcebook.person.biz.Person;
import com.forcebook.person.biz.manager.PersonManager;
import com.forcebook.person.datasource.PersonDataSource;

/**
 * An implementation of PersonManager that utilizes a {@link PersonDataSource}
 * to provide information.
 * @author Jason W. Thompson -
 * https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public class DataSourcePersonManager implements PersonManager
{
    @Override
    public void storePerson(Person person)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person findPersonByUID(String uid)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
