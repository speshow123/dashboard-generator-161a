package models;

import java.util.*;

/**
 * 
 */
public class Variant {

    
	private String id;
	
    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String desc;

    /**
     * 
     */
    private String label;

    /**
     * 
     */
    private String browser;
    
    /**
     * Default constructor
     */
    public Variant() {
    }
    public String getID() {
    	return id;
    }
    
    public void setID(String id) {
    	this.id = id;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

}