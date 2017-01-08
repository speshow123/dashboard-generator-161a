package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import models.TestCase;

public abstract class TestcasesMasterGeneration {
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
     * @param listingTestDirectory
     */
    public static void setTestcaseDirectory(String testDirectory)
    {
    	TestcasesMasterGeneration.testDirectory = testDirectory;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseDirectory()
    {
        return testDirectory;
    }

    /**
     *
     * @param listingTestFileName
     */
    public static void setTestcaseFileName(String testFileName)
    {
    	TestcasesMasterGeneration.testFileName = testFileName;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseFileName()
    {
        return testFileName;
    }

    /**
     *
     * @param listingTestName
     */
    public static void setTestcaseName(String testName)
    {
    	TestcasesMasterGeneration.testName = testName;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseName()
    {
        return testName;
    }

    /**
     *
     * @param listingTest
     */
    public static void generateTestcaseHtmlFile(TestCase test)
    {
        try
        {
        	long startTime = System.currentTimeMillis();
        	String fileNamePrefix = test.getTestName().replace(' ', '_').toLowerCase();
            setTestcaseFileName(fileNamePrefix + ".html");
            fileNamePrefix.replace('-', '_');
            //int index=fileNamePrefix.indexOf("instances");
            //String patternName = fileNamePrefix.substring(0, index-1);
            
            String testFilePath = testDirectory + File.separator + testFileName;
            File htmlFile = new File(testFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            int[] statusList = { test.getPassedVariantNumber(),
            		test.getVulnerableVariantNumber(), test.getErrorVariantNumber() };
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" + 
            				"<div class=\"container\">\n"
            				+ "\t<ol class=\"breadcrumb breadcrumb-arrow\">\n"
            				+ "\t\t<li><a href=\""+dashboardGeneration.getDashboardFileName()+"\">"+dashboardGeneration.getDashboardName()+"</a></li>\n"
            				
            				+ "\t\t<li><a href=\"pattern.html\">Testcases List</a></li>\n"
            				+ "\t\t<li class=\"active\"><span>"+test.getTestName()+"</span></li>\n"
            				+ "\t</ol>\n<br/>\n"
            				+ "\t<div class=\"content\">\n"
            				+ "\t\t<div class=\"container-fluid\">\n"
            				+ "\t\t\t<div class=\"row\">\n"
            				+ "\t\t\t\t<div class=\"col-md-4\">\n"
            				+ "\t\t\t\t\t<div class=\"card\">\n"
            				+ "\t\t\t\t\t\t<div class=\"header\">\n" +
            				"\t\t\t\t\t\t\t<h4 class=\"title\">Test Statistics</h4>\n" +
            				"\t\t\t\t\t\t\t<p class=\"category\">Last Testcase Performance</p>\n" +
            				"\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t\t<div class=\"content\">\n" +
            				"\t\t\t\t\t\t\t<div id=\"chartPreferences\" class=\"ct-chart ct-perfect-fourth\"></div>\n" +
            				
            				"\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t</div>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"col-md-8\">\n" +
            				"\t\t\t\t\t<div class=\"card\">\n" +
            				"\t\t\t\t\t\t<div class=\"header\">\n" +
            				"\t\t\t\t\t\t\t<h4 class=\"title\">Table Report</h4>\n" +
            				"\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t\t<div class=\"content table-responsive table-full-width\">\n" +
            				"\t\t\t\t\t\t\t<table class=\"table table-hover table-striped\">\n" +
            				"\t\t\t\t\t\t\t\t<thead>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<th></th>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<th style=\"text-align:center;\">Testcases</th>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t</thead>\n" +
            				"\t\t\t\t\t\t\t\t<tbody>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Total number of variants</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+test.getVariantDetailList().size()+"</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Variants revealing a vulnerability</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+statusList[1]+"</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Variants not revealing any vulnerability</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+statusList[0]+"</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Inconclusive Variants (eg due to technical issue)</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+statusList[2]+"</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t</tbody>\n" +
            				"\t\t\t\t\t\t\t</table>\n" +
            				"\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t</div>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t</div>\n" +
            				"\t\t\t<div class=\"row\">\n" +
            				"\t\t\t\t<div class=\"col-md-2 col-md-offset-3\">\n" +
            				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block\" onClick=\"myframe.location.href='"+fileNamePrefix+"_actions.html"+"'\">Actions</button>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"col-md-2\">\n" +
            				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block \"  onClick=\"myframe.location.href='"+fileNamePrefix + "_variants.html"+"'\"  >Variants</button>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"col-md-2\">\n" +
            				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block\" onClick=\"myframe.location.href='"+fileNamePrefix + "_logs.html"+"'\">Logs</button>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t</div>\n" +
            				"\t\t\t<br/>\n");
            
            htmlContent.append("<iframe src=\"" +fileNamePrefix+ "_actions.html"+"\" name=\"myframe\" width=\"100%\" height=\"600px\" scrolling=\"auto\" id=\"iframe\"></iframe>");
            htmlContent.append(
            		"\t\t</div>\n" +
            		"\t</div>\n" +
            		"</div>\n" +
            		"</body>\n\n" +
            		"<script src=\"assets/js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
                    "<script src=\"assets/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n" +
                    "<script src=\"assets/js/chartist.min.js\"></script>" + 
                    "<script src=\"assets/js/light-bootstrap-dashboard.js\"></script>\n" +
                    
                    "<script>\n"
                    + "\tvar optionsPreferences = {\n"
                    + "\t\tdonut: true,\n"
                    + "\t\tdonutWidth: 40,\n"
                    + "\t\tstartAngle: 0,\n"
                    + "\t\ttotal: 100,\n"
                    + "\t\tshowLabel: false,\n"
                    + "\t\taxisX: {\n"
                    + "\t\t\tshowGrid: false\n"
                    + "\t\t}\n"
                    + "\t};\n\n"
                    + "\tChartist.Pie('#chartPreferences', optionsPreferences);\n\n"
                    + "\tChartist.Pie('#chartPreferences', {\n"
                    + "\t\tlabels: [");
            for(int i=0;i<3;i++) {
            	if(statusList[i] != 0) {
            		htmlContent.append("'"+statusList[i]+"',");
            	} else {
            		htmlContent.append("'',");
            	}
            	
            }
            htmlContent.append("],\n");
            
            htmlContent.append("\t\tseries: ["+statusList[0]+","+statusList[1]+","+statusList[2]+"]\n"
                    + "\t});\n"
                    + "</script>\n" +
                    "</html>"
                    );
            
            printHtmlFile.print(htmlContent.toString());
            printHtmlFile.close();
            htmlFileStream.close();
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(test.getTestName() + " testcase generation done!"+ " Runtime is " + totalTime + " ms.");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
