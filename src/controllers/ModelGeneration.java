package controllers;

import models.TestCampaign;
import models.TestCase;
import models.TestPattern;

public interface ModelGeneration {
	void generatePatternToHtml(TestPattern testPattern, String directoryPath);
	void generateTestcasesToHtml(TestCase testcase, String directoryPath);
	void generateCampaignToHtml(TestCampaign testCampaign, String directoryPath);
}
