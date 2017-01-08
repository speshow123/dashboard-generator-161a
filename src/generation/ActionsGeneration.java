package generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import models.Operation;
import models.Parameter;
import models.TestCase;
import models.Value;

public abstract class ActionsGeneration {
	static int ActionID = 0;
	private static String actionsDirectory;
    private static String actionsFileName;
    private static String actionsName;
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
    public static void setActionsDirectory(String PatternVariantDirectory)
    {
    	ActionsGeneration.actionsDirectory = PatternVariantDirectory;
    }

    /**
     *
     * @return
     */
    public static String getActionsDirectory()
    {
        return actionsDirectory;
    }

    /**
     *
     * @param PatternVariantFileName
     */
    public static void setActionsFileName(String actionsFileName)
    {
    	ActionsGeneration.actionsFileName = actionsFileName;
    }

    /**
     *
     * @return
     */
    public static String getActionsFileName()
    {
        return actionsFileName;
    }

    /**
     *
     * @param PatternVariantName
     */
    public static void setActionsName(String actionsName)
    {
    	ActionsGeneration.actionsName = actionsName;
    }

    /**
     *
     * @return
     */
    public static String getActionsName()
    {
        return actionsName;
    }
    
    public static void generateActionsHtmlFile(TestCase test)
    {
        try
        {
        	long startTime = System.currentTimeMillis();
        	ActionID = 0;
        	String fileNamePrefix = test.getTestName().replace(' ', '_').toLowerCase();
            setActionsFileName(fileNamePrefix + "_actions.html");
            fileNamePrefix.replace('-', '_');
            String patternFilePath = actionsDirectory + File.separator + actionsFileName;
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
            				"\t\t\t\t\t<h4 class=\"title\">Test Sequence</h4>\n" +
            				"\t\t\t\t</div>\n" +
            				"\t\t\t\t<div class=\"content table-responsive table-full-width\">\n" +
            				"\t\t\t\t\t<table class=\"table table-hover table-striped\">\n" +
            				"\t\t\t\t\t\t<thead>\n" +
                			"\t\t\t\t\t\t\t<tr>\n" +
            				"\t\t\t\t\t\t\t\t<th style=\"width:5%\">Step</th>\n" +
            				"\t\t\t\t\t\t\t\t<th style=\"width:50%\">Actions</th>\n" +
            				"\t\t\t\t\t\t\t\t<th>Observations</th>\n" +
            				"\t\t\t\t\t\t\t</tr>\n" +
            				"\t\t\t\t\t\t<thead>\n" +
            				"\t\t\t\t\t\t<tbody class=\"table-striped\">\n"
                    );
            test.getActionList().stream().forEach(action -> {
            	htmlContent.append("\t\t\t\t\t\t\t<tr>\n");
            	htmlContent.append("\t\t\t\t\t\t\t\t<td>").append(++ActionID).append("</td>\n");
            	if(action.getSubOperationList().size() == 0) {
            		htmlContent.append("\t\t\t\t\t\t\t\t<td>").append(action.getOperation().getNameOperation()).append("()</td>\n")
            		.append("\t\t\t\t\t\t\t\t<td colspan=\"2\"></td>\n")
            		.append("\t\t\t\t\t\t\t</tr>\n");
            	}
            	else {
            		htmlContent.append("\t\t\t\t\t\t\t\t<td>")
            		.append(action.getOperation().getNameOperation()).append("(");
            		StringBuilder temp1 = new StringBuilder();
            		for(Parameter param:action.getOperation().getArgsList().keySet()) {
        				
        				Value val = action.getOperation().getArgsList().get(param);
        				temp1.append(param.getNameParam()).append(" = ").append(val.getNameValue()).append(", ");
        			}
        			if(temp1.length() != 0) {
        				temp1.replace(temp1.length()-2, temp1.length()+1, "");
        				htmlContent.append(temp1);
        			}
            		htmlContent.append(")</td>\n"
            				+ "\t\t\t\t\t\t\t\t<td></td>\n"
            				+ "\t\t\t\t\t\t\t\t<td><button class=\"btn btn-info btn-fill btn-sm\" data-toggle=\"collapse\" data-target=\"#"+ActionID+"_action\">View Detail</button></td>"
            				+ "\t\t\t\t\t\t\t</tr>\n"
            				+ "\t\t\t\t\t\t\t<tr id=\""+ActionID+"_action\" class=\"detail collapse\">\n"
            				+ "\t\t\t\t\t\t\t\t<td colspan=\"3\"><table class=\"table\">\n"
            				+ "\t\t\t\t\t\t\t\t\t<tr>\n"
            				+ "\t\t\t\t\t\t\t\t\t\t<td colspan=\"3\" class=\"subaction\">Subactions</td>\n"
            				+ "\t\t\t\t\t\t\t\t\t</tr>\n");
            		int subId = 0;
            		for(Operation subAction : action.getSubOperationList()) {
            			htmlContent.append("\t\t\t\t\t\t\t\t\t<tr>\n")
            			.append("\t\t\t\t\t\t\t\t\t\t<td style=\"text-align:center\">" + (++subId) +"</td>\n")
            			.append("\t\t\t\t\t\t\t\t\t\t<td colspan=\"2\">" + subAction.getNameOperation() +"(");
            			StringBuilder temp = new StringBuilder();
            			for(Parameter param:subAction.getArgsList().keySet()) {
            				
            				Value val = subAction.getArgsList().get(param);
            				temp.append(param.getNameParam()+" = " + val.getNameValue()+", ");
            			}
            			if(temp.length() != 0) {
            				temp.replace(temp.length()-2, temp.length()+1, "");
            				htmlContent.append(temp);
            			}
            			
            			htmlContent.append(")</td>\n"+
            					"\t\t\t\t\t\t\t\t\t</tr>\n");
            		}
            		htmlContent.append("\t\t\t\t\t\t\t\t</table></td>\n" +
            							"\t\t\t\t\t\t\t</tr>\n");
            	}
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
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(test.getTestName() + " actions generation done! "+"Runtime is "+totalTime+" ms.");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
