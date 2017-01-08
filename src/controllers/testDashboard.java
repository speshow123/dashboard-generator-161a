package controllers;


import models.TestCampaign;
import parse.PatternParser;

public class testDashboard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String directory = "./dashboard";
		ModelParser.parser();
		TestCampaign dashboard = TestCampaign.getInstance();
		dashboard.setCampaignID("D001");
		dashboard.setCampaignName("Test01");
		dashboard.setTestPatternList(PatternParser.getTestPatternList());
		
		dashboard.toHtml(new ModelHtmlGeneration(), directory);
		
		 
	}

}
