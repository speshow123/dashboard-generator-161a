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

import models.Variant;

public abstract class PatternVariantsParser {
	private static List<Variant> patternVariantList;

    /**
     *
     */
    public static void initiatePatternVariantList()
    {
    	patternVariantList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<Variant> getVariantList()
    {
        return patternVariantList;
    }

    /**
     *
     * @param fileName
     */
    public static void parsePatternVariantXmlFile(String fileName)
    {
        try
        {
        	Variant patternVariant = null;
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
                        	case "attack":
                        		patternVariant = new Variant();
                        		break;
                            case "name":
                            	patternVariant.setName(streamReader.getElementText());
                                break;
                            case "code":
                            	patternVariant.setCode(streamReader.getElementText());
                                break;
                            case "desc":
                            	patternVariant.setDesc(streamReader.getElementText());
                                break;
                            case "label":
                            	patternVariant.setLabel(streamReader.getElementText());
                                break;
                            case "browser":
                            	patternVariant.setBrowser(streamReader.getElementText());
                                break;
                           
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        elementContent = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                            case "attack":
                                
                                patternVariantList.add(patternVariant);
                                break;
                            case "Name":
                            	patternVariant.setName(elementContent);
                                break;
                            case "code":
                            	patternVariant.setCode(elementContent);
                                break;
                            case "desc":
                            	patternVariant.setDesc(elementContent);
                                break;
                            case "label":
                            	patternVariant.setLabel(elementContent);
                                break;
                            case "browser":
                            	patternVariant.setBrowser(elementContent);
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
