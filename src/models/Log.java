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
	private String runtime;
	private List<String> payloads;
    private String variantDetailError;
    
    /**
     * Default constructor
     */
    public Log() {
    	payloads = new ArrayList<>();
    	variantDetailError = "";
    }
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getVariantDetailError() {
		return variantDetailError;
	}

	public void setVariantDetailError(String status) {
		this.variantDetailError = status;
	}
    public List<String> getPayLoads() {
		return payloads;
	}

	public void setPayLoads(String payLoad) {
		payloads.add(payLoad);
	}

	
}