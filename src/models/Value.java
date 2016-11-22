package models;

import java.util.*;

/**
 * 
 */
public class Value {

    /**
     * 
     */
    private String idValue;

    /**
     * 
     */
    private String nameValue;


    /**
     * Default constructor
     */
    public Value() {
    }
    
    public Value(String nameValue) {
    	this.idValue = UUID.randomUUID().toString();
    	this.nameValue = nameValue;
    }

	public String getIdValue() {
		return idValue;
	}


	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}


	public String getNameValue() {
		return nameValue;
	}


	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}


}