package models;

import java.util.*;

/**
 * 
 */
class TestCase {
	
	/**
     * 
     */
    private String testID;

    /**
     * 
     */
    private String testName;

    /**
     * 
     */
    private int vulnerableVariantNumber;

    /**
     * 
     */
    private int errorVariantNumber;

    /**
     * 
     */
    private int passVariantNumber;

    /**
     * 
     */
    private List<VariantDetail> variantDetailList;


    /**
     * 
     */
    private List<Action> actionList;
    
    /**
     * Default constructor
     */
    private TestCase() {
    }

    public String getTestID() {
		return testID;
	}


	public void setTestID(String testID) {
		this.testID = testID;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public int getVulnerableVariantNumber() {
		return vulnerableVariantNumber;
	}


	public void setVulnerableVariantNumber(int vulnerableVariantNumber) {
		this.vulnerableVariantNumber = vulnerableVariantNumber;
	}


	public int getErrorVariantNumber() {
		return errorVariantNumber;
	}


	public void setErrorVariantNumber(int errorVariantNumber) {
		this.errorVariantNumber = errorVariantNumber;
	}


	public int getPassVariantNumber() {
		return passVariantNumber;
	}


	public void setPassVariantNumber(int passVariantNumber) {
		this.passVariantNumber = passVariantNumber;
	}


	public List<VariantDetail> getVariantDetailList() {
		return variantDetailList;
	}


	public void setVariantDetailList(List<VariantDetail> variantDetailList) {
		this.variantDetailList = variantDetailList;
	}


	public List<Action> getActionList() {
		return actionList;
	}


	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	

}