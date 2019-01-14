package xalan;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Set;

public class ScraperForXML {

    private static final Logger LOG = Logger.getLogger(ScraperForXML.class.getName());
    private Properties properties = new Properties();

    private ScraperForXML() {
    }

    public ScraperForXML(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }


    public void fetch(String databaseName) throws BaseXException, MalformedURLException {
        String stringURL = properties.getProperty(databaseName);
        URL url = new URL(properties.getProperty(databaseName));

        Context context = new Context();
        LOG.info(new List().execute(context));

        new Set("parser", "xml").execute(context);
        new CreateDB(databaseName, url.toString()).execute(context);

     //   Databases databases = context.databases();
     //   StringList stringListOfDatabases = databases.listDBs();
      //  String currentDatabaseName = null;

     //   Iterator<String> databaseIterator = stringListOfDatabases.iterator();

   //     while (databaseIterator.hasNext()) {
      //      currentDatabaseName = databaseIterator.next();
    //        LOG.info(currentDatabaseName);
            //not quite sure how to query a database...
    //    }

   //     new DropDB(databaseName).execute(context);
  //      context.close();
    }
}