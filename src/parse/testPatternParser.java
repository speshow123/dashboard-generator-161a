package parse;

import java.util.*;
import models.TestPattern;

public class testPatternParser {
	public static void main(String[] args) {
		PatternParser.initiateTestPatternList();
		PatternParser.parseTestPatternXmlFile("./FilesDB/patterns/MS_XSS_pattern.xml");
		List<TestPattern> temp = PatternParser.getTestPatternList();
		System.out.println("Name: " + temp.get(0).getPatternName()
							+ "\nDescription: " + temp.get(0).getPatternDescription()
							+ "\nObservation: " + temp.get(0).getPatternObservation()
							+ "KnownUses: " + temp.get(0).getKnownUses());
	}
}
