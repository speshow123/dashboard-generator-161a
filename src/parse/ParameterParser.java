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

import models.Parameter;

public abstract class ParameterParser {
	private static List<Parameter> parameterList;

    /**
     *
     */
	private ParameterParser() { }
	
    public static void initiateParameterList()
    {
    	parameterList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<Parameter> getParameterList()
    {
        return parameterList;
    }

    /**
     *
     * @param fileName
     */
    public static void parseParameterXmlFile(String fileName)
    {
        try
        {
        	Parameter parameter = null;
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
                        	case "parameter":
                        		parameter = new Parameter();
                        		parameter.setIdParam(streamReader.getAttributeValue(0));
                        		parameter.setNameParam(streamReader.getAttributeValue(1));
                        		break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        elementContent = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                            case "parameter":

                                parameterList.add(parameter);
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
