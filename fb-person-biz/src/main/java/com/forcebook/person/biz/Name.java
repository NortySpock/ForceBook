package com.forcebook.person.biz;

import com.forcebook.common.Identifable;
import java.util.List;
import java.util.Optional;

/**
 * The name of a {@link Person}.
 * @author Jason W. Thompson - https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public interface Name extends Identifable
{
    /**
     * Gets the optional prefix of the {@code Name} such as Dr., Mr., Mrs., etc...
     * @return The name prefix
     */
    Optional<String> getPrefix();
    
    /**
     * Gets the optional given name. In some cultures, this is known as a "first name"
     * @return The given name.
     */
    Optional<String> getGiven();

    /**
     * Gets the middle names. This {@code Collection} may be empty.
     * @return The middle name.
     */
    List<String> getMiddle();

    /**
     * Gets the family name. In some cultures this is known as a last name.
     * @return The family name.
     */
    String getFamily();
    
    /**
     * Gets the name suffix such as Jr., III, etc...
     * @return The name suffix
     */
    Optional<String> getSuffix();
}
