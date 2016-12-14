package controllers;

import parse.LogParser;
import parse.OperationParser;
import parse.PatternParser;
import parse.PatternVariantsParser;
import parse.TestCaseParser;


public class ModelParser {

	public static void parser() {
		OperationParser.initiateOperationList();
		OperationParser.parseOperationXmlFile(FilePath.testcasesFile);
		LogParser.initiateLogList();
		LogParser.parseLogXmlFile(FilePath.resultFile);
		TestCaseParser.initiateTestCaseList();
		TestCaseParser.parseTestCaseXmlFile(FilePath.testcasesFile);
		PatternVariantsParser.initiatePatternVariantList();
		PatternVariantsParser.parsePatternVariantXmlFile(FilePath.variantsFile);
		PatternParser.initiateTestPatternList();
		PatternParser.parseTestPatternXmlFile(FilePath.patternFile);
		
	}
}
