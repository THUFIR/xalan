package xalan;

import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.transform.Result;

public class Database {

    private static final Logger LOG = Logger.getLogger(Database.class.getName());
    private Properties properties = new Properties();

    private Database() {
    }

    public Database(Properties properties) {
        this.properties = properties;
    }

    void persist(Result r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
