package com.forcebook.person.datasource.mock;

import java.time.Instant;

import com.forcebook.person.datasource.PersonMetaData;

public class SimplePersonMetaData implements PersonMetaData{
    private Instant updateTime;
    private Instant updateForceAlignmentTime;
    private String uuid;

    /**
     * Creates a {@code PersonMetaData} object for UID
     * @param aUID UID to attach meta data to
     */
    public SimplePersonMetaData(String aUID) {
        uuid = aUID;
        updateTime = Instant.now();
    }

    @Override
    public Instant getUpdateTime() {
        return updateTime;
    }

    @Override
    public Instant getForceAlignmentUpdateTime() {
        return updateForceAlignmentTime;
    }

    public void setForceAlignmentUpdateTime() {
        updateForceAlignmentTime = updateTime;
    }

    @Override
    public String getUID() {
        return uuid;
    }
}
