package controllers;

import generation.ListingTestcaseGeneration;
import generation.LogGeneration;
import generation.PatternGeneration;
import generation.TestcasesGeneration;
import generation.patternVariantsGeneration;
import models.*;

public class ModelGenerationVistor implements ModelGeneration {

	@Override
	public void generatePatternToHtml(TestPattern testPattern, String directoryPath) {
		// TODO Auto-generated method stub
		PatternGeneration.setPatternDirectory(directoryPath);
		PatternGeneration.generateTestPatternHtmlFile(testPattern);
		patternVariantsGeneration.setPatternVariantsDirectory(directoryPath);
		patternVariantsGeneration.generatePatternVariantsHtmlFile(testPattern);
		LogGeneration.setLogDirectory(directoryPath);
		LogGeneration.generateLogHtmlFile(testPattern);
		TestcasesGeneration.setTestcasesDirectory(directoryPath);
		TestcasesGeneration.generateTestcasesHtmlFile(testPattern);
		ListingTestcaseGeneration.setPatternDirectory(directoryPath);
		ListingTestcaseGeneration.generateTestPatternHtmlFile(testPattern);
	}

	@Override
	public void generateTestcasesToHtml(TestCase testcase, String directoryPath) {
		// TODO Auto-generated method stub
		
	}
	

}
