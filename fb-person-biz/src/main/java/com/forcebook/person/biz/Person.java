package com.forcebook.person.biz;

import com.forcebook.common.Identifable;
import java.util.Optional;

/**
 * A person
 * 
 * @author Jason W. Thompson -
 *         https://google.com/+JasonWThompson_SoftwareDeveloper
 */
public interface Person extends Identifable {
	/**
	 * The alignment of the force for the person.
	 * 
	 * @return The force alignment.
	 */
	Optional<ForceAlignment> getForceAlignment();

	/**
	 * The name of the person.
	 * 
	 * @return The name of the person.
	 */
	Name getName();

}
