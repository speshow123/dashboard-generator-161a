package models;

import java.util.*;

/**
 * 
 */
public class TestCampaign {


    /**
     * 
     */
    private String campaignID;

    /**
     * 
     */
    private String campaignName;

    /**
     * 
     */
    private List<TestPattern> testPatternList;

    /**
     * 
     */
    private int vulnerableTestPatternNumber;

    /**
     * 
     */
    private int errorTestPatternNumber;

    /**
     * 
     */
    private int passTestPatternNumber;

    /**
     * Default constructor
     */
    public TestCampaign() {
    	testPatternList = new ArrayList<TestPattern>();
    	vulnerableTestPatternNumber = 0;
    	errorTestPatternNumber = 0;
    	passTestPatternNumber = 0;
    }

	public String getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public List<TestPattern> getTestPatternList() {
		return testPatternList;
	}

	public void setTestPatternList(TestPattern testPattern) {
		this.testPatternList.add(testPattern);
	}

	public int getVulnerableTestPatternNumber() {
		return vulnerableTestPatternNumber;
	}

	public void setVulnerableTestPatternNumber(int vulnerableTestPatternNumber) {
		this.vulnerableTestPatternNumber = vulnerableTestPatternNumber;
	}

	public int getErrorTestPatternNumber() {
		return errorTestPatternNumber;
	}

	public void setErrorTestPatternNumber(int errorTestPatternNumber) {
		this.errorTestPatternNumber = errorTestPatternNumber;
	}

	public int getPassTestPatternNumber() {
		return passTestPatternNumber;
	}

	public void setPassTestPatternNumber(int passTestPatternNumber) {
		this.passTestPatternNumber = passTestPatternNumber;
	}


}