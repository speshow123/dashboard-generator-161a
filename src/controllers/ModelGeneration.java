package controllers;

import generation.LogGeneration;
import generation.PatternGeneration;
import generation.patternVariantsGeneration;
import models.*;

public class ModelGeneration {

	public void generatePatternToHtml(TestPattern testPattern, String directoryPath) {
		// TODO Auto-generated method stub
		PatternGeneration.setPatternDirectory(directoryPath);
		PatternGeneration.generateTestPatternHtmlFile(testPattern);
		patternVariantsGeneration.setPatternVariantsDirectory(directoryPath);
		patternVariantsGeneration.generatePatternVariantsHtmlFile(testPattern);
		LogGeneration.setLogDirectory(directoryPath);
		LogGeneration.generateLogHtmlFile(testPattern);
	}
	
	
	

}
