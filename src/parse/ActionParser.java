package parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import models.Action;
import models.Operation;
import models.Value;

public abstract class ActionParser {
	private static List<Action> actionList;

    /**
     *
     */
    public static void initiateActionList()
    {
        actionList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<Action> getActionList()
    {
        return actionList;
    }

    /**
     *
     * @param fileName
     */
    public static void parseActionXmlFile(String fileName)
    {
        try
        {
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
                            case "step":
                                //action.setVariantList(PatternVariantsParser.getVariantList());
                                //action.setTestCaseList(TestCasesParser.getTestCaseList());
                            	action.setOperation(optList.get(0));
                            	if(optList.size() > 1) {
                            		for(int i=1;i<optList.size();i++)
                            			action.setSubOperation(optList.get(i));
                            	}
                            	optList.clear();
                                actionList.add(action);
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
