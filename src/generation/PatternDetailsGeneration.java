package generation;

import models.TestPattern;
import java.io.*;
import java.util.Map.Entry;


public abstract class PatternDetailsGeneration
{
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
     * @param patternDirectory
     */
    public static void setPatternDirectory(String patternDirectory)
    {
        PatternDetailsGeneration.patternDirectory = patternDirectory;
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
        PatternDetailsGeneration.patternFileName = patternFileName;
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
        PatternDetailsGeneration.patternName = patternName;
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
        	long startTime = System.currentTimeMillis();
            String fileNamePrefix = pattern.getPatternID().replace('-', '_').toLowerCase();
            setPatternFileName(fileNamePrefix + "_pattern.html");
            String patternFilePath = patternDirectory + File.separator + patternFileName;
            File htmlFile = new File(patternFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" +
	                "\t<div class=\"container\">\n" +
	                "\t\t<ol class=\"breadcrumb breadcrumb-arrow\">\n" +
	                "\t\t<li><a href=\""+dashboardGeneration.getDashboardFileName()+"\">"+dashboardGeneration.getDashboardName()+"</a></li>\n" +
	                "\t\t\t<li class=\"active\"><span>"+pattern.getPatternName()+"</span></li>\n" +
	                "\t\t</ol><br/>\n" +
	                "\t\t<div class=\"row\">\n" + 
	                "\t\t\t<div class=\"col-md-2 col-md-offset-3\">\n" +
	                
    				"\t\t\t\t</div>\n" +
    				"\t\t\t\t<div class=\"col-md-2\">\n" +
    				"\t\t\t\t\t<button class=\"btn btn-info btn-fill btn-block \"  onClick=\"location.href='"+"pattern.html"+"'\"  >Testcases</button>\n" +
    				"\t\t\t\t</div>\n" +
    				"\t\t\t\t<div class=\"col-md-2\">\n" +
    				
	                "\t\t\t</div>\n" +
	                "\t\t</div>\n" +
	                "\t\t<div class=\"content\">\n" + 
	                "\t\t\t<div class=\"row\">\n" + 
	                "\t\t\t\t<div class=\"col-md-10\">\n" +
	                "\t\t\t\t\t<div class=\"card card-plain\">\n" + 
	                "\t\t\t\t\t\t<div class=\"header\">\n" + 
	                "\t\t\t\t\t\t\t<h4 class=\"title\">Test pattern detail</h4>\n" +
	                "\t\t\t\t\t\t</div>\n" + 
	                "\t\t\t\t\t\t<div class=\"content table-responsive table-full-width\">\n" +
	                "\t\t\t\t\t\t\t<table class=\"table table-hover\">\n" +
	                "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Pattern name</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPatternName() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Preconditions</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPreConditions() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Postconditions</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPostConditions() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Description</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPatternDescription() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Test objective</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getTestObjective() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Prerequisites</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPrerequisites() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Procedure</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPatternProcedure() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Observation</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getPatternObservation() + "</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n"+
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Variants</td>\n"+
                    "\t\t\t\t\t\t\t\t\t<td>");
            for(String variantDescription : pattern.getVariantDescriptionList())
            {
                htmlContent.append( variantDescription + "<br/>\n");
            }
            htmlContent.append("\t\t\t\t\t\t\t\t\t</td>\n" + "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Known uses</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td>" + pattern.getKnownUses() + "</td>\n" + "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">Related patterns</td>\n" + "\t\t\t\t\t\t\t\t\t<td>");
            for(int i = 0; i < pattern.getRelatedPatterns().size(); i++)
            {
                if(i < (pattern.getRelatedPatterns().size() - 1))
                {
                    htmlContent.append(pattern.getRelatedPatterns().get(i) + ", ");
                }
                else
                {
                    htmlContent.append(pattern.getRelatedPatterns().get(i));
                }
            }
            htmlContent.append("</td>\n" + "\t\t\t\t\t\t\t\t</tr>\n" + "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td style=\"width:20%\">References</td>\n" + "\t\t\t\t\t\t\t\t\t<td>\n");
            for(Entry<String, String> entry : pattern.getReferences().entrySet())
            {
                htmlContent.append(entry.getKey() + " : " + entry.getValue() + "<br/>\n");
            }
            htmlContent.append("\t\t\t\t\t\t\t\t\t</td>\n" + "\t\t\t\t\t\t\t\t</tr>\n" + 
            "\t\t\t\t\t\t\t</table>\n" + "\t\t\t\t\t\t</div>\n" + "\t\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" + "\t\t</div>\n" + "\t</div>\n" + "\t</div>\n" +
            "</body>\n\n" +
            "<script src=\"assets/js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
            "<script src=\"assets/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n" +
            
            "<script src=\"assets/js/light-bootstrap-dashboard.js\"></script>\n" +
            "</html>"
            );
            
            printHtmlFile.print(htmlContent.toString());
            printHtmlFile.close();
            htmlFileStream.close();
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Test pattern generation done!"+ " Runtime is " + totalTime + " ms.");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}