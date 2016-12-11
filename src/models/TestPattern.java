package models;

import java.util.*;

/**
 * 
 */
public class TestPattern {
	private String patternID;
    private String patternName;
    private String preConditions;
    private String postConditions;
    private String patternDescription;
    private String testObjective;
    private String prerequisites;
    private String patternProcedure;
    private String patternObservation;
    private String knownUses;
    private List<String> variantDescriptionList;
    private List<String> relatedPatterns;
    private Map<String, String> references;

    private int vulnerableTestcaseNumber;
    private int errorTestcaseNumber;
    private int passedTestcaseNumber;

    private List<TestCase> testCaseList;
    private List<Variant> variantList;
    
    /**
     * Default constructor
     */
    public TestPattern() {
    	variantDescriptionList = new ArrayList<>();
    	relatedPatterns = new ArrayList<>();
    	references = new HashMap<>();
    	testCaseList = new ArrayList<>();
    	variantList = new ArrayList<>();
    	
    	testCaseList = new ArrayList<>();
    	variantList = new ArrayList<>();
    	
    }

	public String getPatternID() {
		return patternID;
	}

	public void setPatternID(String patternID) {
		this.patternID = patternID;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public String getPreConditions() {
		return preConditions;
	}

	public void setPreConditions(String preConditions) {
		this.preConditions = preConditions;
	}

	public String getPostConditions() {
		return postConditions;
	}

	public void setPostConditions(String postConditions) {
		this.postConditions = postConditions;
	}

	public String getPatternDescription() {
		return patternDescription;
	}

	public void setPatternDescription(String patternDescription) {
		this.patternDescription = patternDescription;
	}

	public String getTestObjective() {
		return testObjective;
	}

	public void setTestObjective(String testObjective) {
		this.testObjective = testObjective;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getPatternProcedure() {
		return patternProcedure;
	}

	public void setPatternProcedure(String patternProcedure) {
		this.patternProcedure = patternProcedure;
	}

	public String getPatternObservation() {
		return patternObservation;
	}

	public void setPatternObservation(String patternObservation) {
		this.patternObservation = patternObservation;
	}

	public String getKnownUses() {
		return knownUses;
	}

	public void setKnownUses(String knownUses) {
		this.knownUses = knownUses;
	}

	public List<String> getVariantDescriptionList() {
		return variantDescriptionList;
	}

	public void setVariantDescriptionList(String variantDescription) {
		this.variantDescriptionList.add(variantDescription);
	}

	public List<String> getRelatedPatterns() {
		return relatedPatterns;
	}

	public void setRelatedPatterns(String relatedPattern) {
		this.relatedPatterns.add(relatedPattern);
	}

	public Map<String, String> getReferences() {
		return references;
	}

	public void setReferences(String title, String url) {
		this.references.put(title, url);
	}

	public int getVulnerableTestcaseNumber() {
		return vulnerableTestcaseNumber;
	}

	public void setVulnerableTestcaseNumber() {
		this.vulnerableTestcaseNumber++;
	}

	public int getErrorTestcaseNumber() {
		return errorTestcaseNumber;
	}

	public void setErrorTestcaseNumber() {
		this.errorTestcaseNumber++;
	}

	public int getPassedTestcaseNumber() {
		return passedTestcaseNumber;
	}

	public void setPassedTestcaseNumber() {
		this.passedTestcaseNumber++;
	}
	
	
	
	public List<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(List<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
		for(TestCase testCase : testCaseList)
        {
            if(testCase.getVulnerableVariantNumber() > 0)
            {
                this.setVulnerableTestcaseNumber();
            }
            else
            {
                if(testCase.getErrorVariantNumber() > 0)
                {
                    this.setErrorTestcaseNumber();
                }
                else
                {
                    if(testCase.getPassedVariantNumber() > 0)
                    {
                        this.setPassedTestcaseNumber();
                    }
                }
            }
        }
	}

	public List<Variant> getVariantList() {
		return variantList;
	}

	public void setVariantList(List<Variant> variantList) {
		this.variantList = variantList;
	}

    






}