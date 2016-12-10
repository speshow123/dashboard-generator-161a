package models;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;
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
    private int passedVariantNumber;

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
    	passedVariantNumber = 0;
    	
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


	public void setVulnerableVariantNumber(int vulnerableVariants) {
		this.vulnerableVariantNumber = vulnerableVariants;
	}


	public int getErrorVariantNumber() {
		return errorVariantNumber;
	}


	public void setErrorVariantNumber(int errorVariants) {
		this.errorVariantNumber = errorVariants;
	}


	


	public void setPassedVariantNumber(int passedVariants) {
		this.passedVariantNumber = passedVariants;
	}


	public List<Log> getVariantDetailList() {
		return variantDetailList;
	}


	public void setVariantDetailList(List<Log> variantDetailList) {
		this.variantDetailList = variantDetailList;
		Map<String, Long> statusClassify = this.variantDetailList.stream()
				.collect(
					Collectors.groupingBy(Log::getVariantDetailError,
								Collectors.counting())
						);
			
		for(Map.Entry<String, Long> status: statusClassify.entrySet()) {
			if(status.getKey().equals("passed"))
				setPassedVariantNumber(status.getValue().intValue());
			else if(status.getKey().equals("failed"))
				setVulnerableVariantNumber(status.getValue().intValue());
			else
				setErrorVariantNumber(status.getValue().intValue());
		}
		System.out.println(getPassedVariantNumber()+", " + getVulnerableVariantNumber()+", " + getErrorVariantNumber());
		
	}


	public List<Action> getActionList() {
		return actionList;
	}


	public void setActionList(Action act) {
		this.actionList.add(act);
	}

	public int getPassedVariantNumber() {
		// TODO Auto-generated method stub
		return passedVariantNumber;
	}

	

}