package com.forcebook.person.datasource.mock;

import java.time.Instant;

import com.forcebook.person.datasource.PersonMetaData;

public class SimplePersonMetaData implements PersonMetaData {
    private final Instant updateTime;
    private Instant updateForceAlignmentTime;

    /**
     * Creates a {@code PersonMetaData} object for UID
     */
    public SimplePersonMetaData() {
        updateTime = Instant.now();
    }

    @Override public Instant getUpdateTime() {
        return updateTime;
    }

    @Override public Instant getForceAlignmentUpdateTime() {
        return updateForceAlignmentTime;
    }

    public void setForceAlignmentUpdateTime() {
        updateForceAlignmentTime = updateTime;
    }

    public void setForceAlignmentUpdateTime(
            final Instant updateForceAlignmentTime) {
        this.updateForceAlignmentTime = updateForceAlignmentTime;
    }

}
