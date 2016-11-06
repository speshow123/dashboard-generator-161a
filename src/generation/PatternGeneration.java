package generation;

import models.TestPattern;
import java.io.*;

/**
 * File:   PatternGeneration.java
 * @author Huynh Minh Duc
 * Created on 12/03/14 at 10:53
 */

public abstract class PatternGeneration
{
    private static String patternDirectory;
    private static String patternFileName;
    private static String patternName;
    private static final String header = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\"/>\n" +
            "\t<title>Test pattern</title>\n" +
            "\t<script src=\"../../js/script.js\"></script>\n" +
            "\t<link rel=\"stylesheet\" href=\"../../stylesheet/style.css\" type=\"text/css\"/>\n" +
            "\t<style type=\"text/css\">\n" +
            "\t</style>\n" +
            "</head>";

    /**
     *
     * @param patternDirectory
     */
    public static void setPatternDirectory(String patternDirectory)
    {
        PatternGeneration.patternDirectory = patternDirectory;
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
     * @param patternFileName
     */
    public static void setPatternFileName(String patternFileName)
    {
        PatternGeneration.patternFileName = patternFileName;
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
     * @param patternName
     */
    public static void setPatternName(String patternName)
    {
        PatternGeneration.patternName = patternName;
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
     * @param pattern
     */
    public static void generateTestPatternHtmlFile(TestPattern pattern)
    {
        try
        {
            String fileNamePrefix = pattern.getPatternID().replace('-', '_').toLowerCase();
            setPatternFileName(fileNamePrefix + "_pattern.html");
            String patternFilePath = patternDirectory + File.separator + "patterns" + File.separator + patternFileName;
            File htmlFile = new File(patternFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body onload=\"displayOrNotForRSSI()\">\n" +
                    "\t<div id=\"top-container\">\n" +
                    "\t\t<div id=\"top-row\">\n" +
                    "\t\t\t<div id=\"column-left\">\n" +
                    "\t\t\t\t<div class=\"column-in\">\n" +
                    "\t\t\t\t\t<a href=\"../../index.html\">DashBoard</a>\\" +
                    //"<a href=\"../campaign.html\">" + CampaignGeneration.getCampaignName() + "</a>\\" +
                    "<a href=\"" + patternFileName + "\">" + patternName + "</a>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div id=\"column-right\">\n" +
                    "\t\t\t\t<div class=\"column-in, ");
            if(pattern.getVulnerableTestcaseNumber() > 0)
            {
                htmlContent.append("detect_color\">\n\t\t\t\t\tVULNERABILITY DETECTED\n");
            }
            else
            {
                if(pattern.getErrorTestcaseNumber() > 0)
                {
                    htmlContent.append("inconclusive_color\">\n\t\t\t\t\tINCONCLUSIVE\n");
                }
                else
                {
                    if(pattern.getPassedTestcaseNumber() > 0)
                    {
                        htmlContent.append("notdetect_color\">\n\t\t\t\t\tVULNERABILITY NOT DETECTED\n");
                    }
                    else
                    {
                        htmlContent.append("notrun_color\">\n\t\t\t\t\tNOT RUN\n");
                    }
                }
            }
            htmlContent.append("\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t</div>\n" +
                    "\t<div id=\"overview-container\">\n" +
                    "\t\t<h1>Overview</h1>\n" +
                    "\t\t<div id=\"overview\">\n" +
                    "\t\t\t<div id=\"overview-image\">\n" +
                    "\t\t\t\t<canvas id=\"canvas\" height=\"180\" width=\"180\"></canvas>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<script>\n" +
                    "\t\t\t\tvar pieData = [\n" +
                    "\t\t\t\t\t{value : " + pattern.getVulnerableTestcaseNumber() + ", color : \"#00CC00\"},\n" +
                    "\t\t\t\t\t{value : " + pattern.getErrorTestcaseNumber() + ", color : \"#FF6600\"},\n" +
                    "\t\t\t\t\t{value : " + pattern.getPassedTestcaseNumber() + ", color : \"#FF0000\"}\n" +
                    "\t\t\t\t];\n" +
                    "\t\t\t\tvar myPie = new Chart(document.getElementById(\"canvas\").getContext(\"2d\")).Pie(pieData);\n" +
                    "\t\t\t</script>\n" +
                    "\t\t\t<div id=\"overview-content\" class=\"pattern_testcase\">\n" +
                    "\t\t\t\t<div id=\"overview-content-left\">\n" +
                    "\t\t\t\t\t<p class=\"bgcolor_01\">&nbsp;</p>\n" +
                    "\t\t\t\t\t<p class=\"bgcolor_02\">Total number of testcases</p>\n" +
                    "\t\t\t\t\t<p>Testcases revealing a vulnerability</p>\n" +
                    "\t\t\t\t\t<p class=\"bgcolor_02\">Testcases not revealing any vulnerability</p>\n" +
                    "\t\t\t\t\t<p>Inconclusive testcases (eg due to technical issue)</p>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t\t<div id=\"overview-content-right\">\n" +
                    "\t\t\t\t\t<p class=\"bgcolor_01\">Number of testcases</p>\n" +
                    "\t\t\t\t\t<p class=\"bgcolor_02\">");
            htmlContent.append(pattern.getVulnerableTestcaseNumber() + pattern.getErrorTestcaseNumber() + pattern.getPassedTestcaseNumber());
            htmlContent.append("</p>\n" +
                    "\t\t\t\t\t<p>" + pattern.getVulnerableTestcaseNumber() + "</p>\n" +
                    "\t\t\t\t\t<p class=\"bgcolor_02\">" + pattern.getPassedTestcaseNumber() + "</p>\n" +
                    "\t\t\t\t\t<p>" + pattern.getErrorTestcaseNumber() + "</p>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t\t<div id=\"bottom\"></div>\n" +
                    "\t</div>\n" +
                    "\t<div id=\"details-container\">\n" +
                    "\t\t<h1>Details</h1>\n" +
                    "\t\t<div id=\"details\">\n" +
                    "\t\t\t<div id=\"details-link\">\n");
            htmlContent.append("\t\t\t\t<p><h2><a href=\"" + fileNamePrefix + "_details.html" +
                    "\" target=\"iframe\">Test pattern</a></h2></p>\n" +
                    "\t\t\t\t<p><h2><a class=\"not4rssi\" href=\"" + fileNamePrefix + "_testcases.html" +
                    "\" target=\"iframe\">Testcases</a></h2></p>\n" +
                    "\t\t\t\t<p><h2><a class=\"not4rssi\" href=\"../variants/" + fileNamePrefix + "_variants.html" +
                    "\" target=\"iframe\">Variants</a></h2></p>\n" +
                    "\t\t\t\t<p><h2><a href=\"../logs/" + fileNamePrefix + "_logs.html" +
                    "\" target=\"iframe\">Logs</a></h2></p>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div id=\"details-main\">\n" +
                    "\t\t\t\t<iframe src=\"" + fileNamePrefix + "_details.html" +
                    "\" name=\"iframe\" width=\"100%\" height=\"100%\" scrolling=\"Auto\" id=\"iframe\"></iframe>\n");
            htmlContent.append("\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t\t<div id=\"bottom\"></div>\n" +
                    "\t</div>\n" +
                    "</body>\n" +
                    "</html>");
            printHtmlFile.print(htmlContent.toString());
            printHtmlFile.close();
            htmlFileStream.close();
            System.out.println("Test pattern generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}