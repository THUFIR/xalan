package xalan;

import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.transform.Result;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    private Transforms x;
    private Database d;
    private Properties properties = new Properties();

    public static void main(String[] args) throws Exception {
        new App().transform();
    }

    private void transform() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/xalan.xml"));
        x = new Transforms(properties);
        d = new Database(properties);
        Result r = x.withJAXP();
        d.persist(r        );
    }

}
