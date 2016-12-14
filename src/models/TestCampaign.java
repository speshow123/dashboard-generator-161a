package models;

import java.util.*;

import controllers.ModelGeneration;

/**
 * 
 */
public class TestCampaign implements Model{


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
    	testPatternList = new ArrayList<>();
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

	public void setTestPatternList(List<TestPattern> list) {
		this.testPatternList = list;
		for(TestPattern pattern : testPatternList)
        {
            if(pattern.getVulnerableTestcaseNumber() > 0)
            {
                this.setVulnerableTestPatternNumber();
            }
            else
            {
                if(pattern.getErrorTestcaseNumber() > 0)
                {
                    this.setErrorTestPatternNumber();
                }
                else
                {
                    if(pattern.getPassedTestcaseNumber() > 0)
                    {
                        this.setPassTestPatternNumber();
                    }
                }
            }
        }
	}

	public int getVulnerableTestPatternNumber() {
		return vulnerableTestPatternNumber;
	}

	public void setVulnerableTestPatternNumber() {
		this.vulnerableTestPatternNumber++;
	}

	public int getErrorTestPatternNumber() {
		return errorTestPatternNumber;
	}

	public void setErrorTestPatternNumber() {
		this.errorTestPatternNumber++;
	}

	public int getPassTestPatternNumber() {
		return passTestPatternNumber;
	}

	public void setPassTestPatternNumber() {
		this.passTestPatternNumber++;
	}

	@Override
	public void toHtml(ModelGeneration generation, String directoryPath) {
		// TODO Auto-generated method stub
		generation.generateCampaignToHtml(this, directoryPath);
        for(TestPattern pattern : this.getTestPatternList())
        {
            pattern.toHtml(generation, directoryPath);
        }
	}


}