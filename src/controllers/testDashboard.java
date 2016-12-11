package controllers;

import generation.dashboardGeneration;
import models.TestCampaign;
import parse.PatternParser;

public class testDashboard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModelParser.parser();
		TestCampaign dashboard = new TestCampaign();
		dashboard.setCampaignID("D001");
		dashboard.setCampaignName("Test01");
		dashboard.setTestPatternList(PatternParser.getTestPatternList());
		dashboardGeneration.setDashboardFileName("dashboard.html");
		dashboardGeneration.generateDashboardnHtmlFile(dashboard);
		PatternParser.getTestPatternList().get(0).toHtml(new ModelGeneration(), "./dashboard");
	}

}
