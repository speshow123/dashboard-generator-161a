package parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import models.TestCase;
import models.Action;
import models.Operation;
import models.Value;

public abstract class TestCaseParser {
	private static List<TestCase> testCaseList;

    /**
     *
     */
    public static void initiateTestCaseList()
    {
        testCaseList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<TestCase> getTestCaseList()
    {
        return testCaseList;
    }

    /**
     *
     * @param fileName
     */
    public static void parseTestCaseXmlFile(String fileName)
    {
        try
        {
        	TestCase test = null;
            Action action = null;
            List<Operation> optList = new ArrayList<>();
            String elementContent = null;
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                        InputStream inputStream = new FileInputStream(fileName);

                        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new InputStreamReader(inputStream));
            while(streamReader.hasNext())
            {
                int event = streamReader.next();
                switch(event)
                {
                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                        	case "test":
                        		test = new TestCase();
                        		test.setTestID(streamReader.getAttributeValue(0));
                        		test.setTestName(streamReader.getAttributeValue(1));
                        		
                        	break;
                            case "step":
                                action = new Action();
                                action.setActionKind(streamReader.getAttributeValue(0));
                                break;
                            case "call":
                            	optList.add(OperationParser.getOperation(streamReader.getAttributeValue(1)));
                            	break;
                            case "argument":
                            	Value val = ValueParser.getValue(streamReader.getAttributeValue(1)) != null ? ValueParser.getValue(streamReader.getAttributeValue(1)):new Value(streamReader.getAttributeValue(1));
                            	optList.get(optList.size()-1).setValue(streamReader.getAttributeValue(0),val);
                            	break;
                            
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        elementContent = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                        	case "test":
                        		testCaseList.add(test);
                        	break;
                            case "step":
                            	action.setOperation(optList.get(0));
                            	if(optList.size() > 1) {
                            		for(int i=1;i<optList.size();i++)
                            			action.setSubOperation(optList.get(i));
                            	}
                            	optList.clear();
                                test.setActionList(action);
                        		String name = test.getTestName().replaceAll("[(|)| |-]", "_").toLowerCase();
                                test.setVariantDetailList(LogParser.getLogList().stream()
                                		.filter((x) -> name.equals(x.getName().substring(0, name.length()).toLowerCase()))
                                		.collect(Collectors.toList()));
                                break;
                            case "call":
                            	System.out.println("doc duoc");
                            	break;
                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        break;
                }
            }
            
        }
        catch(XMLStreamException exception)
        {
            exception.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
