package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map.Entry;

import models.TestPattern;
import models.Variant;

public abstract class PatternMasterGeneration {
	private static String patternDirectory;
    private static String patternFileName;
    private static String patternName;
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
    public static void setPatternDirectory(String patternDirectory)
    {
    	PatternMasterGeneration.patternDirectory = patternDirectory;
    }

    /**
     *
     * @return
     */
    public static String getPatternDirectory()
    {
        return patternDirectory;
    }

    /**
     *
     * @param listingTestFileName
     */
    public static void setPatternFileName(String patternFileName)
    {
    	PatternMasterGeneration.patternFileName = patternFileName;
    }

    /**
     *
     * @return
     */
    public static String getPatternFileName()
    {
        return patternFileName;
    }

    /**
     *
     * @param listingTestName
     */
    public static void setPatternName(String patternName)
    {
    	PatternMasterGeneration.patternName = patternName;
    }

    /**
     *
     * @return
     */
    public static String getPatternName()
    {
        return patternName;
    }

    /**
     *
     * @param listingTest
     */
    public static void generateTestPatternHtmlFile(TestPattern pattern)
    {
        try
        {
        	String fileNamePrefix = pattern.getPatternID().replace('-', '_').toLowerCase();
            setPatternFileName("pattern.html");
            String patternFilePath = patternDirectory + File.separator + patternFileName;
            File htmlFile = new File(patternFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" + 
            				"<div class=\"container\">\n"
            				+ "\t<ol class=\"breadcrumb breadcrumb-arrow\">\n"
            				+ "\t\t<li><a href=\"dashboard.html\">Home</a></li>\n"
            				+ "\t\t<li><a href=\""+ fileNamePrefix + "_pattern.html" +"\">"+pattern.getPatternName()+"</a></li>\n"
            				+ "\t\t<li class=\"active\"><span>Testcases</span></li>\n"
            				+ "\t</ol>\n<br/>\n"
            				+ "\t<div class=\"content\">\n"
            				+ "\t\t<div class=\"container-fluid\">\n"
            				+ "\t\t\t<div class=\"row\">\n"
            				+ "\t\t\t\t<div class=\"col-md-4\">\n"
            				+ "\t\t\t\t\t<div class=\"card\">\n"
            				+ "\t\t\t\t\t\t<div class=\"header\">\n" +
            				"\t\t\t\t\t\t\t<h4 class=\"title\">Test Statistics</h4>\n" +
            				"\t\t\t\t\t\t\t<p class=\"category\">Last Campaign Performance</p>\n" +
            				"\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t\t<div class=\"content\">\n" +
            				"\t\t\t\t\t\t\t<div id=\"chartPreferences\" class=\"ct-chart ct-perfect-fourth\"></div>\n" +
            				"\t\t\t\t\t\t\t<div class=\"footer\">\n" +
            				"\t\t\t\t\t\t\t\t<div class=\"legend\"> <i class=\"fa fa-circle text-info\"></i> Unrisk <i class=\"fa fa-circle text-danger\"></i> Risk </div>\n" +
            				"\t\t\t\t\t\t\t</div>\n" +
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
            				"\t\t\t\t\t\t\t\t\t\t<td>Total number of testcases</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">1</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Testcases revealing a vulnerability</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">1</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Test cases not revealing any vulnerability</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">0</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td>Inconclusive testcases (eg due to technical issue)</td>\n" +
            				"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">0</td>\n" +
            				"\t\t\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t\t\t</tbody>\n" +
            				"\t\t\t\t\t\t\t</table>\n" +
            				"\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t</div>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t</div>\n" +
            				"\t\t\t<div class=\"row\">\n" +
            				"\t\t\t\t<div class=\"col-md-2 col-md-offset-3\">\n" +
            				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block\" onClick=\"myframe.location.href='"+fileNamePrefix+"_testcases.html"+"'\">Testcases</button>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"col-md-2\">\n" +
            				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block \"  onClick=\"myframe.location.href='"+fileNamePrefix + "_variants.html"+"'\"  >Variants</button>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"col-md-2\">\n" +
            				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block\" onClick=\"myframe.location.href='"+fileNamePrefix + "_logs.html"+"'\">Logs</button>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t</div>\n" +
            				"\t\t\t<br/>\n");
            htmlContent.append("<iframe src=\"" +fileNamePrefix+ "_testcases.html"+"\" name=\"myframe\" width=\"100%\" height=\"600px\" scrolling=\"auto\" id=\"iframe\"></iframe>");
            htmlContent.append(
            		"\t\t</div>\n" +
            		"\t</div>\n" +
            		"</div>\n" +
            		"</body>\n\n" +
            		"<script src=\"assets/js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
                    "<script src=\"assets/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n" +
                    "<script src=\"assets/js/chartist.min.js\"></script>" + 
                    "<script src=\"assets/js/light-bootstrap-dashboard.js\"></script>\n" +
                    "<script src=\"assets/js/demo.js\"></script>\n" +
                    "<script type=\"text/javascript\">\n" +
                    "\t$(document).ready(function(){demo.initChartist();});\n" +
                    "</script>\n" +
                    "</html>"
                    );
            
            printHtmlFile.print(htmlContent.toString());
            printHtmlFile.close();
            htmlFileStream.close();
            System.out.println("pattern generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
