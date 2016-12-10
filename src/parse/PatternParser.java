package parse;

import models.TestPattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

public abstract class PatternParser
{
    private static List<TestPattern> testPatternList;

    /**
     *
     */
    public static void initiateTestPatternList()
    {
        testPatternList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static List<TestPattern> getTestPatternList()
    {
        return testPatternList;
    }

    /**
     *
     * @param fileName
     */
    public static void parseTestPatternXmlFile(String fileName)
    {
        try
        {
            TestPattern testPattern = null;
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
                            case "TestPattern":
                                testPattern = new TestPattern();
                                testPattern.setPatternID(streamReader.getAttributeValue(0));
                                break;
                            case "Description":
                                testPattern.setPatternDescription(streamReader.getElementText());
                                break;
                            case "Prerequisites":
                                testPattern.setPrerequisites(streamReader.getElementText());
                                break;
                            case "Procedure":
                                testPattern.setPatternProcedure(StringEscapeUtils.escapeXml11(streamReader.getElementText()));
                                break;
                            case "Observation":
                                testPattern.setPatternObservation(streamReader.getElementText());
                                break;
                            case "Variant":
                                testPattern.setVariantDescriptionList(streamReader.getElementText());
                                break;
                            case "RelatedPattern":
                                testPattern.setRelatedPatterns(streamReader.getAttributeValue(0));
                                break;
                            case "Reference":
                                testPattern.setReferences(streamReader.getAttributeValue(0), streamReader.getAttributeValue(1));
                                break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        elementContent = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch(streamReader.getLocalName())
                        {
                            case "TestPattern":
                                testPattern.setVariantList(PatternVariantsParser.getVariantList());
                                //testPattern.setTestCaseList(TestCaseParser.getTestCaseList());
                                testPattern.setLog(LogParser.getLogList());
                                testPatternList.add(testPattern);
                                break;
                            case "Name":
                                testPattern.setPatternName(elementContent);
                                break;
                            case "Preconditions":
                                testPattern.setPreConditions(elementContent);
                                break;
                            case "Postconditions":
                                testPattern.setPostConditions(elementContent);
                                break;
                            case "TestObjective":
                                testPattern.setTestObjective(elementContent);
                                break;
                            case "KnownUses":
                                testPattern.setKnownUses(elementContent);
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