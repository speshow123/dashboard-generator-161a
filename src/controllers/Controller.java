package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.TestCampaign;
import parse.LogParser;
import parse.OperationParser;
import parse.PatternParser;
import parse.PatternVariantsParser;
import parse.TestCaseParser;

public class Controller {
	private List<File> testCaseFileList;
    private List<File> testResultFileList;
    private String htmlExportRootDirectory;
    private String campaignName;
    private TestCampaign testCampaign;
    
    public Controller() {
    	testCampaign = TestCampaign.getInstance();
    }
    
    /**
    *
    * @param fileList
    */
   public void setTestCaseFileList(List<File> fileList)
   {
       testCaseFileList = fileList;
   }

   /**
    *
    * @return
    */
   public List<String> getTestCaseFileList()
   {
	   List<String> paths = new ArrayList<>();
	   for(File f:testCaseFileList) {
		   paths.add(f.getPath());
	   }
       return paths;
   }

   /**
    *
    * @param fileList
    */
   public void setTestResultFileList(List<File> fileList)
   {
       testResultFileList = fileList;
   }

   /**
    *
    * @return
    */
   public List<String> getTestResultFileList()
   {
	   List<String> paths = new ArrayList<>();
	   for(File f:testResultFileList) {
		   paths.add(f.getPath());
	   }
       return paths;
   }

   /**
    *
    * @param path
    */
   public void setHtmlExportRootDirectory(String path)
   {
       htmlExportRootDirectory = path;
   }

   /**
    *
    * @return
    */
   public String getHtmlExportRootDirectory()
   {
       return htmlExportRootDirectory;
   }

   /**
    *
    * @param name
    */
   public void setCampaignName(String name)
   {
	   
       campaignName = name;
       testCampaign.setCampaignID("D001");
       testCampaign.setCampaignName(name);
   }

   /**
    *
    * @return
    */
   public String getCampaignName()
   {
       return campaignName;
   }
   
   public void parser() {
	   List<String> testcasePath = getTestCaseFileList(),
			   testresultPath = getTestResultFileList();
	   List<String> patternsPath = FolderUtils.findPathsInTree("./patterns/", 
		        (path,attrs) -> path.toString().endsWith(".xml"));
	   OperationParser.initiateOperationList();
	   for(String path:testcasePath) {
		   OperationParser.parseOperationXmlFile(path);
	   }
	   LogParser.initiateLogList();
	   for(String path:testresultPath) {
		   LogParser.parseLogXmlFile(path);
	   }
	   TestCaseParser.initiateTestCaseList();
	   for(String path:testcasePath) {
		   TestCaseParser.parseTestCaseXmlFile(path);
	   }
	   
	   PatternVariantsParser.initiatePatternVariantList();
		patternsPath.stream().filter(x->x.contains("_variants")).forEach(path -> {
			PatternVariantsParser.parsePatternVariantXmlFile(path);
		});
		
		PatternParser.initiateTestPatternList();
		patternsPath.stream().filter(x->x.contains("_pattern")).forEach(path -> {
			PatternParser.parseTestPatternXmlFile(path);
		});
	   
   }
   public void execute()
   {
	   this.parser();
	   testCampaign.setTestPatternList(PatternParser.getTestPatternList());
	   testCampaign.toHtml(new ModelHtmlGeneration(), this.getHtmlExportRootDirectory());
   }
}
