package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import models.TestCase;
import models.TestPattern;

public abstract class TestcasesGeneration {
	static int testID = 0;
	private static String testDirectory;
    private static String testFileName;
    private static String testName;
    private static final String header = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\"/>\n"
            + "\t<link rel=\"icon\" type=\"image/png\" href=\"assets/img/favicon.ico\">\n"
            + "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" 
            + "\t<title>Test Campaign</title>\n"
            + "\t<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />\n"
            + "\t<meta name=\"viewport\" content=\"width=device-width\" />\n"
            + "\t<link href=\"assets/css/bootstrap.min.css\" rel=\"stylesheet\" />\n"
            + "\t<link href=\"assets/css/light-bootstrap-dashboard.css\" rel=\"stylesheet\"/>\n"
            + "\t<link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n"
            + "\t<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>\n"
            + "\t<link href=\"assets/css/pe-icon-7-stroke.css\" rel=\"stylesheet\" />\n"
            + "</head>";

    /**
     *
     * @param testDirectory
     */
    public static void setTestcasesDirectory(String testDirectory)
    {
    	TestcasesGeneration.testDirectory = testDirectory;
    }

    /**
     *
     * @return
     */
    public static String getTestcasesDirectory()
    {
        return testDirectory;
    }

    /**
     *
     * @param testFileName
     */
    public static void setTestcasesFileName(String testFilename)
    {
    	TestcasesGeneration.testFileName = testFilename;
    }

    /**
     *
     * @return
     */
    public static String getTestcasesFileName()
    {
        return testFileName;
    }

    /**
     *
     * @param PatternVariantName
     */
    public static void setTestcasesName(String testName)
    {
    	TestcasesGeneration.testName = testName;
    }

    /**
     *
     * @return
     */
    public static String getTestcasesName()
    {
        return testName;
    }
    
    public static void generateTestcasesHtmlFile(TestPattern pattern)
    {
        try
        {
        	
            String fileNamePrefix = pattern.getPatternID().replace('-', '_').toLowerCase();
            setTestcasesFileName(fileNamePrefix + "_testcases.html");
            String patternFilePath = testDirectory + File.separator + testFileName;
            File htmlFile = new File(patternFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" + 
            				
            				"<div class=\"content\">\n" +
            				"\t<div class=\"row\">\n" +
            				"\t\t<div class=\"col-md-12\">\n" +
            				"\t\t\t<div class=\"card\">\n" +
            				"\t\t\t\t<div class=\"header\">\n" +
            				"\t\t\t\t\t<h4 class=\"title\">Testcases</h4>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"content table-responsive table-full-width\">\n" +
            				"\t\t\t\t\t<table class=\"table table-hover\">\n"
                    );
            htmlContent.append(
      			  "\t\t\t\t\t\t<thead>\n"
      			+ "\t\t\t\t\t\t\t<tr>\n"
      			+ "\t\t\t\t\t\t\t\t<th style=\"width:5%\">No</th>\n"
      			+ "\t\t\t\t\t\t\t\t<th>Testcase name</th>\n"
      			+ "\t\t\t\t\t\t\t\t<th style=\"width:60%\">Combination</th>\n"
      			+ "\t\t\t\t\t\t\t\t<th>Vulnerability</th>\n"
      			+ "\t\t\t\t\t\t\t</tr>\n"
      			+ "\t\t\t\t\t\t</thead>\n"
      			+ "\t\t\t\t\t\t<tbody>\n");
            for(TestCase test: pattern.getTestCaseList()) {
            	
            	int sum = test.getErrorVariantNumber() + test.getPassedVariantNumber() + test.getVulnerableVariantNumber();
            	htmlContent.append("\t\t\t\t\t\t\t<tr>\n"
          			+ "\t\t\t\t\t\t\t\t<td style=\"width:5%\">"+(++testID)+"</td>\n"
          			+ "\t\t\t\t\t\t\t\t<td><a href=\"child_testcase_action.html\">"+ test.getTestName() +"</a></td>\n"
          			+ "\t\t\t\t\t\t\t\t<td style=\"width:60%\">The combination of "+sum+" variants</td>\n");
          		if(test.getPassedVariantNumber() != sum) {
          			htmlContent.append("\t\t\t\t\t\t\t\t<td class=\"detected\">DETECTED </td>\n");
          		} else {
          			htmlContent.append("\t\t\t\t\t\t\t\t<td class=\"nodetected\">NO DETECTED </td>\n");
          		}
          		htmlContent.append("\t\t\t\t\t\t\t</tr>\n");
          
            }
            	
            
            
            htmlContent.append("\t\t\t\t\t\t</tbody>\n"+
            		"\t\t\t\t\t</table>\n" +
            		"\t\t\t\t</div>\n" +
            		"\t\t\t</div>\n" +
            		"\t\t</div>\n" +
            		"\t</div>\n" +
            		"</div>\n" +
            		"</body>\n\n" +
            		"<script src=\"assets/js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
                    "<script src=\"assets/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n" +
                    
                    "<script src=\"assets/js/light-bootstrap-dashboard.js\"></script>\n" +
                   
                    "</html>"
                    );
            
            printHtmlFile.print(htmlContent.toString());
            printHtmlFile.close();
            htmlFileStream.close();
            System.out.println("Test cases list generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
