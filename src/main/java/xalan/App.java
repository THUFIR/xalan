package xalan;

import java.net.URI;
import java.util.Properties;
import java.util.logging.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    private X x = new X();
    private Properties properties = new Properties();

    public static void main(String[] args) throws Exception {
        new App().transform();
    }

    private void transform() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/xalan.xml"));
        URI xmlFileURI = new URI(properties.getProperty("xml"));
        URI xsdFileURI = new URI(properties.getProperty("xsd"));
        URI xslFileURI = new URI(properties.getProperty("xsl"));
        URI outputFileURI = new URI(properties.getProperty("output"));
        x.transform(xmlFileURI, xslFileURI, outputFileURI);
    }

}
