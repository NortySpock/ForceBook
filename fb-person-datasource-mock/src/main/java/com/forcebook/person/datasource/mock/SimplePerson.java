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

	public void setForceAlignment(Optional<ForceAlignment> inForceAlignment) {
		forceAlignment = inForceAlignment;
	}

	@Override
	public Name getName() {
		return name;
	}

	public void setName(Name _name) {
		this.name = _name;
	}

	@Override
	public String getUID() {
		return uuid;
	}

	public void setUID(String _UUID) {
		this.uuid = _UUID;
	}

}
