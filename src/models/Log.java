package models;

import java.util.*;

/**
 * 
 */
public class Log {
	/**
     * 
     */
	private String name;
	private List<String> payloads;
    private int variantDetailError;
    /**
     * Default constructor
     */
    public Log() {
    	payloads = new ArrayList<>();
    	variantDetailError = 0;
    }
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVariantDetailError() {
		return variantDetailError;
	}

	public void setVariantDetailError(String status) {
		this.variantDetailError = status.equalsIgnoreCase("passed") ? 1:0;
	}
    public List<String> getPayLoads() {
		return payloads;
	}

	public void setPayLoads(String payLoad) {
		payloads.add(payLoad);
	}

	
}