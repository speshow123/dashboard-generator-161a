package models;

import java.util.*;
import java.util.stream.Collectors;

import controllers.ModelGeneration;
import controllers.ModelGenerationVistor;

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
		Map<String, Long> statusClassify = this.variantDetailList.parallelStream()
				.collect(
					Collectors.groupingBy(Log::getVariantDetailError,
								Collectors.counting())
						);
			
		for(String status: statusClassify.keySet()) {
			int value = statusClassify.get(status).intValue();
			if(status.equals("passed"))
				setPassedVariantNumber(value);
			else if(status.equals("failed"))
				setVulnerableVariantNumber(value);
			else
				setErrorVariantNumber(value);
		}
		//System.out.println(getPassedVariantNumber()+", " + getVulnerableVariantNumber()+", " + getErrorVariantNumber());
		
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

	public void toHtml(ModelGeneration generation, String string) {
		// TODO Auto-generated method stub
		
	}

	

}