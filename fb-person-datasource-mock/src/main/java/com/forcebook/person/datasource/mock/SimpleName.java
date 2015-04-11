package com.forcebook.person.datasource.mock;

import java.util.List;
import java.util.Optional;

import com.forcebook.person.biz.Name;

public class SimpleName implements Name {
	private String uuid;
	private Optional<String> prefix;
	private Optional<String> given;
	private List<String> middle;
	private String family;
	private Optional<String> suffix;

	@Override
	public Optional<String> getPrefix() {
		return prefix;
	}

	public void setPrefix(Optional<String> inPrefix) {
		prefix = inPrefix;
	}

	@Override
	public Optional<String> getGiven() {
		return given;
	}

	public void setGiven(Optional<String> inGiven) {
		given = inGiven;
	}

	@Override
	public List<String> getMiddle() {
		return middle;
	}

	public void setMiddle(List<String> inList) {
		middle = inList;
	}

	@Override
	public String getFamily() {
		return family;
	}

	@Override
	public Optional<String> getSuffix() {
		return suffix;
	}

	@Override
	public String getUID() {

		return uuid;
	}

}
