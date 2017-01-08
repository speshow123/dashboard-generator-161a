package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import models.TestCase;

public abstract class TestcaseLogGeneration {
	static int logID = 0;
	private static String logDirectory;
    private static String logFileName;
    private static String logName;
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
     * @param PatternVariantDirectory
     */
    public static void setTestLogDirectory(String PatternVariantDirectory)
    {
    	TestcaseLogGeneration.logDirectory = PatternVariantDirectory;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseLogDirectory()
    {
        return logDirectory;
    }

    /**
     *
     * @param PatternVariantFileName
     */
    public static void setTestcaseLogFileName(String logFileName)
    {
    	TestcaseLogGeneration.logFileName = logFileName;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseLogFileName()
    {
        return logFileName;
    }

    /**
     *
     * @param PatternVariantName
     */
    public static void setTestcaseLogName(String logName)
    {
    	TestcaseLogGeneration.logName = logName;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseLogName()
    {
        return logName;
    }
    
    public static void generateTestcaseLogHtmlFile(TestCase test)
    {
        try
        {
        	long startTime = System.currentTimeMillis();
        	String fileNamePrefix = test.getTestName().replace(' ', '_').toLowerCase();
            setTestcaseLogFileName(fileNamePrefix + "_logs.html");
            fileNamePrefix.replace('-', '_');
            String patternFilePath = logDirectory + File.separator + logFileName;
            File htmlFile = new File(patternFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" + 
            				
            				"\t\t\t<div class=\"content\">\n" +
            				"\t\t\t\t<div class=\"row\">\n" +
            				"\t\t\t\t\t<div class=\"col-md-12\">\n" +
            				"\t\t\t\t\t\t<div class=\"card\">\n" +
            				"\t\t\t\t\t\t\t<div class=\"header\">\n" +
            				"\t\t\t\t\t\t\t\t<h4 class=\"title\">Testcase Logs</h4>\n" +
            				"\t\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t\t\t<div class=\"content table-responsive table-full-width\">\n"
            				
                    );
            test.getVariantDetailList().stream()
            .forEach(log -> {
            	htmlContent.append("\t\t\t\t\t\t\t\t<table class=\"table table-hover table-striped\">\n\t\t\t\t\t\t\t\t\t<tr>\n")
            			.append("\t\t\t\t\t\t\t\t\t\t<td>Info: test variant ").append(log.getName()).append(" ] has run duration in ")
            			.append(log.getRuntime()).append(" and its vulnerability is ");
    			if(log.getVariantDetailError().equals("passed")) {
    				htmlContent.append("<span class=\"notdetected\">not detect</span></td>\n");
    			} else {
    				htmlContent.append("<span class=\"detected\">detect</span></td>\n");
    			}
    			htmlContent.append("\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:right;\"><button class=\"btn btn-info btn-fill btn-sm\" data-toggle=\"collapse\" data-target=\"#log_")
    			.append(++logID).append("\">View Detail</button></td>\n")
    			.append("\t\t\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t\t\t\t<table id=\"log_")
    			.append(logID).append("\"class=\"collapse table detail\">\n");
            	for(int i=0;i<log.getPayLoads().size();i++) {
            		htmlContent.append("\t\t\t\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t\t\t\t<td>")
            		.append(log.getPayLoads().get(i)).append("</td>\n\t\t\t\t\t\t\t\t\t\t</tr>\n");
            	}
						
    			htmlContent.append("\t\t\t\t\t\t\t\t\t</table>\n\t\t\t\t\t\t\t\t</table>\n");
            });
            
            htmlContent.append(
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
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(test.getTestName() + " testcase logs generation done!"+ " Runtime is " + totalTime + " ms.");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
