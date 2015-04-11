package com.forcebook.person.datasource;

import java.time.Instant;

import com.forcebook.common.Identifable;

/**
 * Provides metadata about the person data.
 * @author Jason W. Thompson -
 * https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public interface PersonMetaData extends Identifable
{
    /**
     * The last time the person was updated.
     * @return The last time the person was updated.
     */
    public Instant getUpdateTime();
    
    /**
     * The last time the person's force alignment was changed.
     * @return The last time the person's force alignment was changed.
     */
    public Instant getForceAlignmentUpdateTime();
}
