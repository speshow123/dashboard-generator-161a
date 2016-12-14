package generation;

import java.io.*;

import models.TestCampaign;;

public abstract class dashboardGeneration {
	private static String dashboardirectory;
    private static String dashboardFileName;
    private static String dashboardName;
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
	public static void setDashboardName(String name) {
		dashboardGeneration.dashboardName = name;
	}
	
	public static String getDashboardName() {
		return dashboardName;
	}
    public static void setDashboardDirectory(String path) {
		dashboardGeneration.dashboardirectory = path;
	}
    public static String getDashboardDirectory() {
		return dashboardirectory;
	}
    public static void setDashboardFileName(String name) {
		dashboardGeneration.dashboardFileName = name;
	}
    public static String getDashboardFileName() {
		return dashboardFileName;
	}
    public static String getHeader() {
		return header;
	}
	public static void generateDashboardnHtmlFile(TestCampaign dashboard) {
		
		try
        {
			
			
			dashboardGeneration.setDashboardFileName(dashboard.getCampaignName().replace(' ', '_') + ".html");
			String patternFilePath = dashboardirectory+File.separator+dashboardFileName;
			File htmlFile = new File(patternFilePath);
			OutputStream htmlFileStream = new FileOutputStream(htmlFile);
	        PrintStream printHtmlFile = new PrintStream(htmlFileStream);
			StringBuilder htmlContent = new StringBuilder();
			int[] statusList = { dashboard.getPassTestPatternNumber(),
					dashboard.getVulnerableTestPatternNumber(), dashboard.getErrorTestPatternNumber()};
			htmlContent.append(header);
			htmlContent.append("\n<body>\n" +
	                "\t<div class=\"wrapper\">\n" +
	                "\t\t<div class=\"sidebar\" data-color=\"purple\" data-image=\"assets/img/sidebar-5.jpg\">\n" +
	                "\t\t\t<div class=\"sidebar-wrapper\">\n" +
	                "\t\t\t\t<div class=\"logo\"> <a class=\"simple-text\"> Test Campaign </a> </div>\n" +
	                "\t\t\t\t<ul class=\"nav\">\n" +
	                "\t\t\t\t\t<li class=\"active\">\n" +
	                "\t\t\t\t\t<a href=\"#\"> <i class=\"pe-7s-graph\"></i> <p>Dashboard <b class=\"caret\"></b></p> </a>\n" +
	                "\t\t\t\t\t\t<ul class=\"sidebar-submenu\">\n" +
	                "\t\t\t\t\t\t\t<li><a href=\"#\"><i class=\"\"></i> Dashboard "+ dashboard.getCampaignName() +"</a></li>\n" +
	                "\t\t\t\t\t\t</ul>\n" +
	                "\t\t\t\t\t</li>\n" +
	                "\t\t\t\t</ul>\n" +
	                "\t\t\t</div>\n" +
	                "\t\t</div>\n" + 
	                "\t\t<div class=\"main-panel\">\n" + 
	                "\t\t\t<nav class=\"navbar navbar-default navbar-fixed\">\n" + 
	                "\t\t\t\t<div class=\"container-fluid\">\n" + 
	                "\t\t\t\t\t<div class=\"navbar-header\">\n" + 
	                "\t\t\t\t\t\t<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navigation-example-2\"> <span class=\"sr-only\">Toggle navigation</span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> </button>\n"
	                + "\t\t\t\t\t\t<a class=\"navbar-brand\">Dashboard</a> </div>\n"
	                + "\t\t\t\t\t<div class=\"collapse navbar-collapse\">\n"
	                + "\t\t\t\t\t\t<ul class=\"nav navbar-nav navbar-right\"></ul>\n"
	                + "\t\t\t\t\t</div>\n"
	                + "\t\t\t\t</div>\n"
	                + "\t\t\t</nav>\n"
	                + "\t\t\t<div class=\"content\">\n" + 
	                "\t\t\t<div class=\"container-fluid\">\n" +
	                "\t\t\t<div class=\"row\">\n" + 
	                "\t\t\t\t<div class=\"col-md-4\">\n" + 
	                "\t\t\t\t\t<div class=\"card\">\n" + 
	                "\t\t\t\t\t\t<div class=\"header\">\n" + 
	                "\t\t\t\t\t\t\t<h4 class=\"title\">Test Statistics</h4>\n" + 
	                "\t\t\t\t\t\t\t<p class=\"category\">Last Campaign Performance</p>\n" + 
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
	                "\t\t\t\t\t\t\t<table class=\"table table-hover table-striped\"s>\n" + 
	                "\t\t\t\t\t\t\t\t<thead><tr><th></th><th style=\"text-align:center;\">Number of test patterns</th></tr></thead>\n" + 
	                "\t\t\t\t\t\t\t\t<tbody>\n" + 
	                "\t\t\t\t\t\t\t\t\t<tr><td>Total number of run test patterns</td>\n" +
	                "\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+dashboard.getTestPatternList().size()+"</td></tr>\n" + 
	                "\t\t\t\t\t\t\t\t\t<tr><td>Test patterns revealing a vulnerability</td>\n"
	                + "\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+dashboard.getVulnerableTestPatternNumber()+"</td></tr>\n" + 
	                "\t\t\t\t\t\t\t\t\t<tr><td>Test patterns not revealing any vulnerability</td>\n"
	                + "\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+dashboard.getPassTestPatternNumber()+"</td></tr>\n" + 
	                "\t\t\t\t\t\t\t\t\t<tr><td>Inconclusive test patterns (eg due to technical issue)</td>\n"
	                + "\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+dashboard.getErrorTestPatternNumber()+"</td></tr>\n" + 
	                "\t\t\t\t\t\t\t\t\t<tr><td>Test patterns not run</td>\n"
	                + "\t\t\t\t\t\t\t\t\t<td style=\"text-align:center;\">"+dashboard.getErrorTestPatternNumber()+"</td></tr>\n" + 
	                "\t\t\t\t\t\t\t\t</tbody>\n" +
	                "\t\t\t\t\t\t\t</table>\n" +
	                "\t\t\t\t\t\t</div>\n" + 
	                "\t\t\t\t\t</div>\n" + 
	                "\t\t\t\t</div>\n" + 
	                "\t\t\t</div>\n" +
	                "\t\t\t<div class=\"row\">\n" + 
	                "\t\t\t\t<div class=\"col-md-10\">\n" + 
	                "\t\t\t\t\t<div class=\"card\">\n" + 
	                "\t\t\t\t\t\t<div class=\"header\">\n" + 
	                "\t\t\t\t\t\t\t<h4 class=\"title\">Table Report</h4>\n" + 
	                "\t\t\t\t\t\t\t<p class=\"category\">Test pattern list</p>\n" + 
	                "\t\t\t\t\t\t</div>\n" + 
	                "\t\t\t\t\t\t<div class=\"content table-responsive table-full-width\">\n" + 
	                "\t\t\t\t\t\t\t<table class=\"table table-hover table-striped\"s>\n" + 
	                "\t\t\t\t\t\t\t\t<thead><tr><th>ID</th><th>Vulnerability pattern</th><th>Vulnerability</th></tr></thead>\n" + 
	                "\t\t\t\t\t\t\t\t<tbody>\n");
					for(int i=0;i<dashboard.getTestPatternList().size();i++) {
						htmlContent.append("\t\t\t\t\t\t\t\t\t<tr><td>1</td><td><a href=\"")
						.append(dashboard.getTestPatternList().get(i).getPatternID().replace('-', '_').toLowerCase() + "_pattern.html")
						.append("\">").append(dashboard.getTestPatternList().get(i).getPatternName())
						.append("</a></td><td class=\"detected\">DETECTED</td></tr>\n");
					}
	                
	                htmlContent.append("\t\t\t\t\t\t\t\t</tbody>\n" +
	                "\t\t\t\t\t\t\t</table>\n" +
	                "\t\t\t\t\t\t</div>\n" + 
	                "\t\t\t\t\t</div>\n" + 
	                "\t\t\t\t</div>\n" + 
	                "\t\t\t</div>\n" +
	                "</body>\n\n" +
	                "<script src=\"assets/js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
	                "<script src=\"assets/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n" +
	                "<script src=\"assets/js/chartist.min.js\"></script>\n" +
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
	        System.out.println("Dashboard generation done!");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
	}


    
}
