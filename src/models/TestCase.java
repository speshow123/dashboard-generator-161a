package models;

import java.util.*;

/**
 * 
 */
public class TestCase {
	
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
    private List<Log> variantDetailList;


    /**
     * 
     */
    private List<Action> actionList;
    
    /**
     * Default constructor
     */
    public TestCase() {
    	vulnerableVariantNumber = 0;
    	errorVariantNumber = 0;
    	passVariantNumber = 0;
    	
    	variantDetailList = new ArrayList<>();
    	actionList = new ArrayList<>();
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


	public List<Log> getVariantDetailList() {
		return variantDetailList;
	}


	public void setVariantDetailList(List<Log> variantDetailList) {
		this.variantDetailList = variantDetailList;
	}


	public List<Action> getActionList() {
		return actionList;
	}


	public void setActionList(Action act) {
		this.actionList.add(act);
	}

	

}