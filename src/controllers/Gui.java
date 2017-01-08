package controllers;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	
	private JLabel testCaseLabel;
    private JTextField testCaseText;
    private JButton testCaseBrowse;
    private JLabel testResultLabel;
    private JTextField testResultText;
    private JButton testResultBrowse;
    private JLabel campaignNameLabel;
    private JTextField campaignNameText;
    private JLabel htmlLabel;
    private JTextField htmlText;
    private JButton htmlBrowse;
    private JButton generate;
    private JButton view;
    private JButton exit;
    private JLabel result;
    private JFileChooser fileChooser;
    private File[] testCaseFileList, testResultFileList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Runnable r = () -> {
			try {
				Gui frame = new Gui();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		};
		EventQueue.invokeLater(r);
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		super("Dashboard Generation");
        this.setSize(750, 220);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        fileChooser = new JFileChooser();
        //
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //
        testCaseLabel = new JLabel("Testcase directory");
        constraints.insets = new Insets(5,5,5,5);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 0;
        textPanel.add(testCaseLabel, constraints);
        //
        testCaseText = new JTextField(50);
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        textPanel.add(testCaseText, constraints);
        //
        testCaseBrowse = new JButton( "Browse..." );
        testCaseBrowse.addActionListener(e -> openTestcaseDirectory());
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(testCaseBrowse, constraints);
        //
        testResultLabel = new JLabel("Test result directory");
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(testResultLabel, constraints);
        //
        testResultText = new JTextField(50);
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        textPanel.add(testResultText, constraints);
        //
        testResultBrowse = new JButton( "Browse..." );
        testResultBrowse.addActionListener(e -> openTestResultDirectory());
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(testResultBrowse, constraints);
        //
        campaignNameLabel = new JLabel("Test campaign name");
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(campaignNameLabel, constraints);
        //
        campaignNameText = new JTextField(50);
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        textPanel.add(campaignNameText, constraints);
        //
        JLabel label = new JLabel(" ");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(label, constraints);
        //
        htmlLabel = new JLabel("HTML output directory");
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(htmlLabel, constraints);
        //
        htmlText = new JTextField(50);
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        textPanel.add(htmlText, constraints);
        //
        htmlBrowse = new JButton( "Browse..." );
        htmlBrowse.addActionListener(e -> saveHtmlDirectory());
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        textPanel.add(htmlBrowse, constraints);
        //
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        result = new JLabel("Start generation ...     ");
        generate = new JButton("Generate");
        generate.addActionListener(e -> generateXmlHtml());
        view = new JButton("View");
        view.addActionListener(e -> viewIndexHtml());
        exit = new JButton("Exit");
        exit.addActionListener(e -> exit());
        buttonPanel.add(result);
        buttonPanel.add(generate);
        buttonPanel.add(view);
        buttonPanel.add(exit);
        this.add(textPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
	}
	/**
    *
    * @return
    */
	public File[] getFileArray(boolean isTestCase)
    {
        StringBuilder inFileListText = new StringBuilder();
        File[] inFileArray, outFileArray;
        inFileArray = fileChooser.getSelectedFiles();
        if(inFileArray.length == 1)
        {
            if(inFileArray[0].isDirectory())
            {
                outFileArray = inFileArray[0].listFiles();
            }
            else
            {
                outFileArray = inFileArray;
            }
        }
        else
        {
            outFileArray = inFileArray;
        }
        boolean isFirstFile = true;
        for(File file : inFileArray)
        {
            if(isFirstFile)
            {
                inFileListText.append(file.toString());
                isFirstFile = false;
            }
            else
            {
                inFileListText.append(", " + file.toString().substring(file.toString().lastIndexOf(File.separator) + 1));
            }
        }
        if(isTestCase)
        {
            testCaseText.setText(inFileListText.toString());
        }
        else
        {
            testResultText.setText(inFileListText.toString());
        }
        return outFileArray;
    }

   /**
    *
    */
   public void openTestcaseDirectory()
   {
       fileChooser.setCurrentDirectory(new File("."));
       fileChooser.setDialogTitle("Open testcase directory");
       fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       fileChooser.setMultiSelectionEnabled(true);
       int openTestcaseValue = fileChooser.showOpenDialog(this);
       if(openTestcaseValue == JFileChooser.APPROVE_OPTION)
       {
           testCaseFileList = this.getFileArray(true);
       }
   }

   /**
    *
    */
   public void openTestResultDirectory()
   {
       fileChooser.setCurrentDirectory(new File("."));
       fileChooser.setDialogTitle("Open test result directory");
       fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       fileChooser.setMultiSelectionEnabled(true);
       int openTestResultValue = fileChooser.showOpenDialog(this);
       if(openTestResultValue == JFileChooser.APPROVE_OPTION)
       {
           testResultFileList = this.getFileArray(false);
       }
   }
   /**
   *
   */
  public void saveHtmlDirectory()
  {
      fileChooser.setCurrentDirectory(new File("."));
      fileChooser.setDialogTitle("Save html directory");
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fileChooser.setAcceptAllFileFilterUsed(false);
      int saveHtmlValue = fileChooser.showSaveDialog(this);
      if(saveHtmlValue == JFileChooser.APPROVE_OPTION)
      {
          htmlText.setText(fileChooser.getSelectedFile().toString());
      }
  }
  /**
  *
  */
 public void generateXmlHtml()
 {
     List<String> errorList = new ArrayList<>();
     StringBuilder errorMessage = new StringBuilder();
     if(testCaseText.getText().equals(""))
     {
         errorList.add("Testcase directory path is empty!");
     }
     if(testResultText.getText().equals(""))
     {
         errorList.add("Test result directory path is empty!");
     }
     if(campaignNameText.getText().equals(""))
     {
         errorList.add("Campaign name is empty!");
     }
     if(htmlText.getText().equals(""))
     {
         errorList.add("HTML directory path is empty!");
     }
     if (errorList.size() > 0)
     {
         for (int i = 0; i < errorList.size(); i++)
         {
             errorMessage.append(errorList.get(i));
             if (i + 1 < errorList.size())
             {
                 errorMessage.append("\n");
             }
         }
         JOptionPane.showMessageDialog(this, errorMessage, "Invalid values", JOptionPane.WARNING_MESSAGE);
         return;
     }
     Controller controller = new Controller();
     controller.setTestCaseFileList(Arrays.asList(testCaseFileList));
     controller.setTestResultFileList(Arrays.asList(testResultFileList));
     controller.setCampaignName(campaignNameText.getText());
     controller.setHtmlExportRootDirectory(htmlText.getText());
     controller.execute();
     JOptionPane.showMessageDialog(this, "Generation done!", "Success", JOptionPane.INFORMATION_MESSAGE);
     result.setText("Generation done!         ");
 }

 /**
  *
  */
 public void viewIndexHtml()
 {
     String errorMessage = "HTML directory path is empty!";
     if(htmlText.getText().equals(""))
     {
         JOptionPane.showMessageDialog(this, errorMessage, "Invalid values", JOptionPane.WARNING_MESSAGE);
         return;
     }
     try
     {
         File htmlFile = new File(htmlText.getText() + File.separator + "index.html");
         if(!htmlFile.exists())
         {
             errorMessage = "Index html file does not exist!";
             JOptionPane.showMessageDialog(this, errorMessage, "Invalid values", JOptionPane.WARNING_MESSAGE);
             return;
         }
         Desktop.getDesktop().browse(htmlFile.toURI());
     }
     catch(IOException exception)
     {
         exception.printStackTrace();
     }
 }

 /**
  *
  */
 public void exit()
 {
     this.dispose();
 }
}
