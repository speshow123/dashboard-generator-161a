package models;

import java.util.*;

/**
 * 
 */
class TestCampaign {


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
    private TestCampaign() {
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

	public void setTestPatternList(List<TestPattern> testPatternList) {
		this.testPatternList = testPatternList;
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