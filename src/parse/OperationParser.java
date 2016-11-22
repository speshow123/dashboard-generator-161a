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

import models.Operation;
import models.Parameter;

public abstract class OperationParser {
	private static List<Operation> operationList;

    /**
     *
     */
	private OperationParser() { }
	
    public static void initiateOperationList()
    {
    	operationList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<Operation> getOperationList()
    {
        return operationList;
    }
    
    public static Operation getOperation(String operationID) {
    	for(Operation operation:operationList) {
    		if(operation.getIdOperation().equals(operationID))
    			return operation;
    	}
    	return null;
    }
    /**
     *
     * @param fileName
     */
    public static void parseOperationXmlFile(String fileName)
    {
        try
        {
        	Operation operation = null;
        	Parameter parameter = null;
        	int count = 0;
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
                        	case "operation":
                        		operation = new Operation();
                        		operation.setIdOperation(streamReader.getAttributeValue(0));
                        		operation.setNameOperation(streamReader.getAttributeValue(1));
                        		break;
                        	case "parameter":
                        		parameter = new Parameter();
                        		parameter.setIdParam(streamReader.getAttributeValue(0));
                        		parameter.setNameParam(streamReader.getAttributeValue(1));
                        		
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        elementContent = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                            case "operation":
                            	
                                operationList.add(operation);
                                break;
                            case "parameter":
                                operation.setParameter(parameter);
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
