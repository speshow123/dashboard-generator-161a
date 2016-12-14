package controllers;

import generation.PatternMasterGeneration;
import generation.TestcaseLogGeneration;
import generation.TestcaseVariantsGeneration;
import generation.ActionsGeneration;
import generation.LogGeneration;
import generation.PatternDetailsGeneration;
import generation.TestcasesListGeneration;
import generation.TestcasesMasterGeneration;
import generation.dashboardGeneration;
import generation.patternVariantsGeneration;
import models.*;

public class ModelHtmlGeneration implements ModelGeneration {

	@Override
	public void generateCampaignToHtml(TestCampaign testCampaign, String directoryPath) {
		// TODO Auto-generated method stub
		dashboardGeneration.setDashboardName(testCampaign.getCampaignName());
		dashboardGeneration.setDashboardDirectory(directoryPath);
		dashboardGeneration.generateDashboardnHtmlFile(testCampaign);
	}
	
	@Override
	public void generatePatternToHtml(TestPattern testPattern, String directoryPath) {
		// TODO Auto-generated method stub
		PatternDetailsGeneration.setPatternDirectory(directoryPath);
		PatternDetailsGeneration.generateTestPatternHtmlFile(testPattern);
		patternVariantsGeneration.setPatternVariantsDirectory(directoryPath);
		patternVariantsGeneration.generatePatternVariantsHtmlFile(testPattern);
		LogGeneration.setLogDirectory(directoryPath);
		LogGeneration.generateLogHtmlFile(testPattern);
		TestcasesListGeneration.setTestcasesDirectory(directoryPath);
		TestcasesListGeneration.generateTestcasesHtmlFile(testPattern);
		PatternMasterGeneration.setPatternDirectory(directoryPath);
		PatternMasterGeneration.generateTestPatternHtmlFile(testPattern);
	}

	@Override
	public void generateTestcasesToHtml(TestCase testcase, String directoryPath) {
		// TODO Auto-generated method stub
		TestcasesMasterGeneration.setTestcaseDirectory(directoryPath);
		TestcasesMasterGeneration.generateTestcaseHtmlFile(testcase);
		TestcaseLogGeneration.setTestLogDirectory(directoryPath);
		TestcaseLogGeneration.generateTestcaseLogHtmlFile(testcase);
		TestcaseVariantsGeneration.setTestcaseVariantsDirectory(directoryPath);
		TestcaseVariantsGeneration.generateTestcaseVariantsHtmlFile(testcase);
		ActionsGeneration.setActionsDirectory(directoryPath);
		ActionsGeneration.generateActionsHtmlFile(testcase);
	}

	
	

}
