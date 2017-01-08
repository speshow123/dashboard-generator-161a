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

import models.Log;

public abstract class LogParser {
	private static List<Log> logList;

    /**
     *
     */
    public static void initiateLogList()
    {
        logList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<Log> getLogList()
    {
        return logList;
    }

    /**
     *
     * @param fileName
     */
    public static void parseLogXmlFile(String fileName)
    {
        try
        {
            Log log = null;

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
                                log = new Log();
                                log.setRuntime(streamReader.getAttributeValue(0));
                                log.setVariantDetailError(streamReader.getAttributeValue(1));
                                log.setName(streamReader.getAttributeValue(2));
                                break;
                            case "output":
                            	log.setPayLoads(streamReader.getElementText());
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
                            	logList.add(log);
                            	break;
                            case "output":
                            	log.setPayLoads(elementContent);
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
