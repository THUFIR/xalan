package xalan;

import java.util.Properties;
import java.util.logging.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    private Transforms x;// = new XalanTransform();
    private Properties properties = new Properties();

    public static void main(String[] args) throws Exception {
        new App().transform();
    }

    private void transform() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/xalan.xml"));
        x = new Transforms(properties);
        //  x.saxonTransform();
        x.withJAXP();
    }

}
