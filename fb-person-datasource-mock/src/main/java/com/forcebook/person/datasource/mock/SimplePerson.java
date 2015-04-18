package com.forcebook.person.datasource.mock;

import java.util.Optional;

import com.forcebook.person.biz.ForceAlignment;
import com.forcebook.person.biz.Name;
import com.forcebook.person.biz.Person;

public class SimplePerson implements Person {
    private String uuid;
    private Name name;
    private Optional<ForceAlignment> forceAlignment;

    @Override
    public Optional<ForceAlignment> getForceAlignment() {
        return forceAlignment;
    }

    /**
     * The enum of the {@code ForceAlignment} (LIGHT or DARK)
     * @param aForceAlignment
     */
    public void setForceAlignment(Optional<ForceAlignment> forceAlignment) {
        this.forceAlignment = forceAlignment;
    }

    @Override
    public Name getName() {
        return name;
    }

    /**
     * The Name of the {@code Person}
     * @param aName
     */
    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String getUID() {
        return uuid;
    }

    /**
     * The UUID of the {@code Person}
     * @param aUUID
     */
    public void setUID(String uid) {
        this.uuid = uid;
    }

}
