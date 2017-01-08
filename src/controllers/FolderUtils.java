package controllers;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.*;

/** Some Stream-based static methods for using with folders and paths.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, JavaScript, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, Spring MVC,
 *  servlets, JSP, Java 8 lambdas and streams (for those that know Java already), 
 *  and Java 8 programming (for those new to Java)</a>.
 */

public class FolderUtils {
  
  public static List<String> strPaths;
  
  public static List<String> findPathsInTree(String rootFolder, BiPredicate<Path,BasicFileAttributes> test) {
	  strPaths= new ArrayList<>();
    try(Stream<Path> paths = Files.find(Paths.get(rootFolder), 10, test)) {
      paths.forEach(x->strPaths.add(x.toString()));
    } catch(IOException ioe) {
      System.err.println("IOException: " + ioe);
    }
	return strPaths;
  }
  /**
  *
  */
 public static void makeDirectory(String directoryPath)
 {
     File campaign = new File(directoryPath);
     if(!campaign.exists())
     {
         if(campaign.mkdir())
         {
             System.out.println("Directory " + directoryPath + " is created!");
         }
         else
         {
             System.out.println("Failed to create directory!");
         }
     }
     else
     {
         System.out.println("Directory " + directoryPath + " exists!");
     }
 }
  private FolderUtils() {} // Uninstantiatable class
}
