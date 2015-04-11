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

    /**
     * The optional prefix of the {@code Name} such as Dr., Mr., Mrs., Ms. etc...
     * @param aString The name prefix
     */
    public void setPrefix(Optional<String> aString) {
        prefix = aString;
    }

    @Override
    public Optional<String> getGiven() {
        return given;
    }

    /**
     * The optional given name of the {@code Name} such as Alice, Bob, Prakash, Dalal, etc. 
     * @param aString
     */
    public void setGiven(Optional<String> aString) {
        given = aString;
    }

    @Override
    public List<String> getMiddle() {
        return middle;
    }

    /**
     * The list of middle name(s) of the {@code Name} 
     * @param aListOfStrings
     */
    public void setMiddle(List<String> aListOfStrings) {
        middle = aListOfStrings;
    }

    @Override
    public String getFamily() {
        return family;
    }
    
    /**
     * The family name of the {@code Name}} such as Smith, Barker, O'Malley, Hou, etc...
     * @param aString
     */
    public void setFamily(String aString){
        family = aString;
    }

    @Override
    public Optional<String> getSuffix() {
        return suffix;
    }
    
    /**
     * The suffix of the {@code Name}} such as Sr., Jr., III
     * @param aString
     */
    public void setSuffix(Optional<String> aString){
        suffix = aString;
    }

    @Override
    public String getUID() {
        return uuid;
    }

}
