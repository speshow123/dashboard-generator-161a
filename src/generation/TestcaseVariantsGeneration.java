package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import models.TestCase;

public abstract class TestcaseVariantsGeneration {
	static int VariantID = 0;
	private static String variantsDirectory;
    private static String variantsFileName;
    private static String variantsName;
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
    public static void setTestcaseVariantsDirectory(String PatternVariantDirectory)
    {
    	TestcaseVariantsGeneration.variantsDirectory = PatternVariantDirectory;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseVariantsDirectory()
    {
        return variantsDirectory;
    }

    /**
     *
     * @param PatternVariantFileName
     */
    public static void setTestcaseVariantsFileName(String variantsFileName)
    {
    	TestcaseVariantsGeneration.variantsFileName = variantsFileName;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseVariantsFileName()
    {
        return variantsFileName;
    }

    /**
     *
     * @param PatternVariantName
     */
    public static void setTestcaseVariantsName(String variantsName)
    {
    	TestcaseVariantsGeneration.variantsName = variantsName;
    }

    /**
     *
     * @return
     */
    public static String getTestcaseVariantsName()
    {
        return variantsName;
    }
    
    public static void generateTestcaseVariantsHtmlFile(TestCase test)
    {
        try
        {
        	VariantID = 0;
        	String fileNamePrefix = test.getTestName().replace(' ', '_').toLowerCase();
            setTestcaseVariantsFileName(fileNamePrefix + "_variants.html");
            fileNamePrefix.replace('-', '_');
            String patternFilePath = variantsDirectory + File.separator + variantsFileName;
            File htmlFile = new File(patternFilePath);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" + 
            				
            				"<div class=\"content\">\n" +
            				"\t<div class=\"pull-right\">\n" +
            				"\t\t<div class=\"btn-group\">\n" +
            				"t\t\t<button type=\"button\" class=\"btn btn-success btn-fill filter\" data-target=\"notdetected\">Not detected</button>\n" +
            				"t\t\t<button type=\"button\" class=\"btn btn-warning btn-fill filter\" data-target=\"inconclusive\">Inconclusive</button>\n" +
            				"t\t\t<button type=\"button\" class=\"btn btn-danger btn-fill filter\" data-target=\"detected\">Detected</button>\n" +
            				"t\t\t<button type=\"button\" class=\"btn btn-default btn-fill filter\" data-target=\"all\">All</button>\n" +
            				"\t\t</div>\n" +
            				"\t</div>\n" +
            				"\t<div class=\"row\">\n" +
            				"\t\t<div class=\"col-md-12\">\n" +
            				"\t\t\t<div class=\"card\">\n" +
            				"\t\t\t\t<div class=\"header\">\n" +
            				"\t\t\t\t\t<h4 class=\"title\">Testcase Variants</h4>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"content table-responsive table-full-width\">\n" +
            				"\t\t\t\t\t<table class=\"table table-hover table-striped\">\n" +
            				"\t\t\t\t\t\t<thead>\n" +
                			"\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t<th style=\"width:5%\">No</th>\n" +
            				"\t\t\t\t\t\t\t\t<th style=\"width:60%\">Payload</th>\n" +
            				"\t\t\t\t\t\t\t\t<th>Associated observation</th>\n" +
            				"\t\t\t\t\t\t\t\t<th>Vulnerability</th>\n" + 
            				"\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t<thead>\n" +
            				"\t\t\t\t\t\t<tbody class=\"table-striped\">\n"
                    );
            test.getVariantDetailList().stream()
            .forEach(log -> {
            	if(log.getVariantDetailError().equals("passed"))
            		htmlContent.append("\t\t\t\t\t\t\t<tr data-status=\"notdetected\">\n");
            	else if(log.getVariantDetailError().equals("failed"))
            		htmlContent.append("\t\t\t\t\t\t\t<tr data-status=\"detected\">\n");
            	else
            		htmlContent.append("\t\t\t\t\t\t\t<tr data-status=\"inconclusive\">\n");
            	
            	htmlContent.append("\t\t\t\t\t\t\t\t<td>"+(++VariantID)+"</td>\n");
            	if(log.getPayLoads().size() > 0)
	    			htmlContent.append("\t\t\t\t\t\t\t\t<td>" + log.getPayLoads().get(0) +"</td>\n");
    			else
    				htmlContent.append("\t\t\t\t\t\t\t\t<td>" + "N/A" +"</td>\n");
            	htmlContent.append("\t\t\t\t\t\t\t\t<td></td>\n");
    			if(log.getVariantDetailError().equals("passed")) {
    				htmlContent.append("\t\t\t\t\t\t\t\t<td class=\"notdetected\">No detected</td>\n");
    			} else if(log.getVariantDetailError().equals("failed")) {
    				htmlContent.append("\t\t\t\t\t\t\t\t<td class=\"detected\">Detected</td>\n");
    			} else {
    				htmlContent.append("\t\t\t\t\t\t\t\t<td class=\"inconclusive\">Inconclusive</td>\n");
    			}
    			htmlContent.append("\t\t\t\t\t\t\t</tr>\n");
    			
            });
            
            htmlContent.append(
            		"\t\t\t\t\t\t</tbody>\n" +
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
            System.out.println(test.getTestName() + " testcase variants generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
