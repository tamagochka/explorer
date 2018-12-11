package my.tamagochka.utilities.XMLResourcesLoader.loader;

import my.tamagochka.utilities.XMLResourcesLoader.reflection.ReflectionHelper;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    private static final String CLASSNAME = "class";

    private String element = null;
    private Object object = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(!qName.equals(CLASSNAME)) {
            element = qName;
        } else {
            String className = attributes.getValue(0);
            object = ReflectionHelper.createInstance(className);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        element = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if(element != null) {
            String value = new String(ch, start, length);
            ReflectionHelper.setFieldValue(object, element, value);
        }
    }

    public Object getObject() {
        return object;
    }

}
