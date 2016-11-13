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

import models.Value;

public abstract class ValueParser {
	private static List<Value> valueList;

    /**
     *
     */
	private ValueParser() { }
	
    public static void initiateValueList()
    {
    	valueList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<Value> getValueList()
    {
        return valueList;
    }

    /**
     *
     * @param fileName
     */
    public static void parseValueXmlFile(String fileName)
    {
        try
        {
        	Value value = null;
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
                        	case "value":
                        		value = new Value();
                        		value.setIdValue(streamReader.getAttributeValue(0));
                        		value.setNameValue(streamReader.getAttributeValue(1));
                        		break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        elementContent = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                            case "value":

                                valueList.add(value);
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
