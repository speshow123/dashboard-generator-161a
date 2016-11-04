package models;

import java.util.*;

/**
 * 
 */
public class VariantDetail {
	
	/**
     * 
     */
    private String variantID;

    /**
     * 
     */
    private String payLoad;

    /**
     * 
     */
    private boolean isVulnerability;

    /**
     * Default constructor
     */
    public VariantDetail() {
    }

	public String getVariantID() {
		return variantID;
	}

	public void setVariantID(String variantID) {
		this.variantID = variantID;
	}

	public String getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}

	public boolean isVulnerability() {
		return isVulnerability;
	}

	public void setVulnerability(boolean isVulnerability) {
		this.isVulnerability = isVulnerability;
	}

    

}