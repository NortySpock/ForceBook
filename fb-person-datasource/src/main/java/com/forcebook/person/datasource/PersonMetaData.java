package com.forcebook.person.datasource;

import java.time.Instant;

/**
 * Provides metadata about the person data.
 * @author Jason W. Thompson -
 * https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public interface PersonMetaData
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
