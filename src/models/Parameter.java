package models;

import java.util.*;

/**
 * 
 */
public class Parameter {

    

    /**
     * 
     */
    private String idParam;

    /**
     * 
     */
    private String nameParam;

    /**
     * 
     */
    private Value valueParam;

    /**
     * Default constructor
     */
    public Parameter() {
    }

	public String getIdParam() {
		return idParam;
	}

	public void setIdParam(String idParam) {
		this.idParam = idParam;
	}

	public String getNameParam() {
		return nameParam;
	}

	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}

	public Value getValueParam() {
		return valueParam;
	}

	public void setValueParam(Value valueParam) {
		this.valueParam = valueParam;
	}



}