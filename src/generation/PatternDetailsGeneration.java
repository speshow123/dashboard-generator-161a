package generation;

import models.TestPattern;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map.Entry;

/**
 * File:   PatternDetailsGeneration.java
 * @author Huynh Minh Duc
 * Created on 12/03/14 at 10:54
 */

public abstract class PatternDetailsGeneration
{
    private static final String header = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\"/>\n" +
            "\t<title>Pattern details</title>\n" +
            "\t<link rel=\"stylesheet\" href=\"../../stylesheet/style.css\" type=\"text/css\"/>\n" +
            "\t<style type=\"text/css\">\n" +
            "\t<!--\n" +
            "\t\ttable {border-collapse: collapse;}\n" +
            "\t-->\n" +
            "\t</style>\n" +
            "</head>";

    /**
     *
     * @param pattern
     */
    public static void generatePatternDetailsHtmlFile(TestPattern pattern)
    {
        try
        {
            String patternDetailsFileName = PatternGeneration.getPatternDirectory() + File.separator + "patterns" + File.separator +
                                            pattern.getPatternID().replace('-', '_').toLowerCase() + "_details.html";
            File htmlFile = new File(patternDetailsFileName);
            OutputStream htmlFileStream = new FileOutputStream(htmlFile);
            PrintStream printHtmlFile = new PrintStream(htmlFileStream);
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(header);
            htmlContent.append("\n<body>\n" +
                    "<p><h2>Test pattern details</h2></p>\n" +
                    "<table width=\"100%\" border=\"0\">\n" +
                    "<tr>\n" +
                    "\t<td width=\"5%\">&nbsp;</td>\n" +
                    "\t<td width=\"95%\">\n" +
                    "\t<table width=\"100%\" border=\"1\" bordercolor=\"#000000\">\n" +
                    "\t<tr>\n" +
                    "\t\t<td width=\"18%\" height=\"33\"><h4>Pattern name</h4></td>\n" +
                    "\t\t<td width=\"82%\">" + pattern.getPatternName() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Preconditions</h4></td>\n" +
                    "\t\t<td>" + pattern.getPreConditions() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Postconditions</h4></td>\n" +
                    "\t\t<td>" + pattern.getPostConditions() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Description</h4></td>\n" +
                    "\t\t<td>" + pattern.getPatternDescription() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Test objective</h4></td>\n" +
                    "\t\t<td>" + pattern.getTestObjective() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Prerequisites</h4></td>\n" +
                    "\t\t<td>" + pattern.getPrerequisites() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Procedure</h4></td>\n" +
                    "\t\t<td>" + pattern.getPatternProcedure() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Observation</h4></td>\n" +
                    "\t\t<td>" + pattern.getPatternObservation() + "</td>\n" +
                    "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td><h4>Variants</h4></td>\n" + "\t\t<td>\n" +
                    "\t\t<table width=\"100%\" border=\"0\">\n");
            for(String variantDescription : pattern.getVariantDescriptionList())
            {
                htmlContent.append("\t\t<tr>\n" + "\t\t\t<td>" + variantDescription + "</td>\n" + "\t\t</tr>\n");
            }
            htmlContent.append("\t\t</table>\n" + "\t\t</td>\n" + "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Known uses</h4></td>\n" +
                    "\t\t<td>" + pattern.getKnownUses() + "</td>\n" + "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td height=\"33\"><h4>Related patterns</h4></td>\n" + "\t\t<td>");
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
            htmlContent.append("</td>\n" + "\t</tr>\n" + "\t<tr>\n" +
                    "\t\t<td><h4>References</h4></td>\n" + "\t\t<td>\n" +
                    "\t\t<table width=\"100%\" border=\"0\">\n");
            for(Entry<String, String> entry : pattern.getReferences().entrySet())
            {
                htmlContent.append("\t\t<tr>\n" + "\t\t\t<td>" + entry.getKey() + " : " + entry.getValue() + "</td>\n" + "\t\t</tr>\n");
            }
            htmlContent.append("\t\t</table>\n" + "\t\t</td>\n" + "\t</tr>\n" + "\t</table>\n" +
                    "\t</td>\n" + "</tr>\n" + "</table>\n" + "</body>\n" + "</html>");
            printHtmlFile.print(htmlContent.toString());
            printHtmlFile.close();
            htmlFileStream.close();
            System.out.println("Test pattern details generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}