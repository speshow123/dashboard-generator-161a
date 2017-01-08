package controllers;

import java.util.List;

import parse.LogParser;
import parse.OperationParser;
import parse.PatternParser;
import parse.PatternVariantsParser;
import parse.TestCaseParser;


public class ModelParser {

	public static void parser() {
		List<String> testcasePath = FolderUtils.findPathsInTree("./testcases/", 
		        (path,attrs) -> path.toString().endsWith(".xml"));
		List<String> patternsPath = FolderUtils.findPathsInTree("./patterns/", 
		        (path,attrs) -> path.toString().endsWith(".xml"));
		List<String> resultsPath = FolderUtils.findPathsInTree("./testresults/", 
		        (path,attrs) -> path.toString().endsWith(".xml"));
		
		OperationParser.initiateOperationList();
		testcasePath.stream().forEach(path -> {
			OperationParser.parseOperationXmlFile(path);
		});
		
		LogParser.initiateLogList();
		resultsPath.stream().forEach(path -> {
			LogParser.parseLogXmlFile(path);
		});
		
		
		TestCaseParser.initiateTestCaseList();
		testcasePath.stream().forEach(path -> {
			TestCaseParser.parseTestCaseXmlFile(path);
		});
		
		PatternVariantsParser.initiatePatternVariantList();
		patternsPath.stream().filter(x->x.contains("_variants")).forEach(path -> {
			PatternVariantsParser.parsePatternVariantXmlFile(path);
		});
		
		PatternParser.initiateTestPatternList();
		patternsPath.stream().filter(x->x.contains("_pattern")).forEach(path -> {
			PatternParser.parseTestPatternXmlFile(path);
		});
		
		
	}
}
