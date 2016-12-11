package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import models.Log;
import models.TestPattern;

public abstract class LogGeneration {
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
    public static void setLogDirectory(String PatternVariantDirectory)
    {
    	LogGeneration.logDirectory = PatternVariantDirectory;
    }

    /**
     *
     * @return
     */
    public static String getLogDirectory()
    {
        return logDirectory;
    }

    /**
     *
     * @param PatternVariantFileName
     */
    public static void setLogFileName(String logFileName)
    {
    	LogGeneration.logFileName = logFileName;
    }

    /**
     *
     * @return
     */
    public static String getLogFileName()
    {
        return logFileName;
    }

    /**
     *
     * @param PatternVariantName
     */
    public static void setLogName(String logName)
    {
    	LogGeneration.logName = logName;
    }

    /**
     *
     * @return
     */
    public static String getLogName()
    {
        return logName;
    }
    
    public static void generateLogHtmlFile(TestPattern pattern)
    {
        try
        {
        	
            String fileNamePrefix = pattern.getPatternID().replace('-', '_').toLowerCase();
            setLogFileName(fileNamePrefix + "_logs.html");
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
            				"\t\t\t\t\t\t\t\t<h4 class=\"title\">Logs</h4>\n" +
            				"\t\t\t\t\t\t\t</div>\n" +
            				"\t\t\t\t\t\t\t<div class=\"content table-responsive table-full-width\">\n"
            				
                    );
            pattern.getTestCaseList().stream()
    		.flatMap(x -> x.getVariantDetailList().stream())
    		.forEach((log) -> {
    			
    			htmlContent.append("\t\t\t\t\t\t\t\t<table class=\"table table-hover table-striped\">\n" +
            			"\t\t\t\t\t\t\t\t\t<tr>\n" +
            			"\t\t\t\t\t\t\t\t\t\t<td>Info: test variant "+log.getName()+" ] has run duration in "+ log.getRuntime() +" and its vulnerability is ");
    			if(log.getVariantDetailError().equals("passed")) {
    				htmlContent.append("<span class=\"notdetected\">not detect</span></td>\n");
    			} else {
    				htmlContent.append("<span class=\"detected\">detect</span></td>\n");
    			}
    			htmlContent.append("\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:right;\"><button class=\"btn btn-info btn-fill btn-sm\" data-toggle=\"collapse\" data-target=\"#log_"+ (++logID) +"\">View Detail</button></td>\n" +
            			"\t\t\t\t\t\t\t\t\t</tr>\n" +
            			"\t\t\t\t\t\t\t\t\t<table id=\"log_"+ logID +"\"class=\"collapse table detail\">\n");
            	for(int i=0;i<log.getPayLoads().size();i++) {
            		htmlContent.append("\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t\t\t\t<td>" + log.getPayLoads().get(i) +
            				"</td>\n\t\t\t\t\t\t\t\t\t\t</tr>\n");
            	}
						
    			htmlContent.append("\t\t\t\t\t\t\t\t\t</table>\n" +
    					"\t\t\t\t\t\t\t\t</table>\n");
    		});
            	
            
            /*for(Log log: pattern.) {
            	htmlContent.append("\t\t\t\t\t\t\t\t<table class=\"table table-hover\">\n" +
            			"\t\t\t\t\t\t\t\t\t<tr>\n" +
            			"\t\t\t\t\t\t\t\t\t\t<td>Variant "+v.getName()+"</td>\n" +
            			"\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:right;\"><button class=\"btn btn-info btn-fill btn-sm\" data-toggle=\"collapse\" data-target=\"#"+ v.getID() +"\">View Detail</button></td>\n" +
            			"\t\t\t\t\t\t\t\t\t</tr>\n" +
            			"\t\t\t\t\t\t\t\t\t<table id=\""+ v.getID() +"\"class=\"collapse table detail\">\n" +
            			"\t\t\t\t\t\t\t\t\t\t<thead>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<th>Label</th>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<th>Code</th>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<th>Description</th>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<th>Browser</th>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
            			"\t\t\t\t\t\t\t\t\t\t</thead>\n" +
            			"\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<td>"+ v.getLabel() +"</td>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<td>"+ v.getCode() +"</td>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<td>"+ v.getDesc() +"</td>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t\t<td>"+ v.getBrowser() +"</td>\n" +
            			"\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
            			"\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
            			"\t\t\t\t\t\t\t\t\t</table>\n" +
            			"\t\t\t\t\t\t\t\t</table>\n");
            }*/
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
            System.out.println("Test logs generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
    
}
