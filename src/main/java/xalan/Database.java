package xalan;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Set;
import org.basex.util.list.StringList;

public class Database {

    private static final Logger LOG = Logger.getLogger(Database.class.getName());
    private Properties properties = new Properties();

    private Database() {
    }

    public Database(Properties properties) {
        this.properties = properties;
    }

    void persist() throws Exception {
        URL url = new URL(properties.getProperty("xmlURL"));
        String databaseName = properties.getProperty("databaseName");
        Context context = new Context();
        LOG.info(new List().execute(context));
        new Set("parser", "xml").execute(context);
        new CreateDB(databaseName, url.toString()).execute(context);
        StringList foo = context.listDBs();
        
//        Databases databases = context.databases();
//    StringList stringListOfDatabases = databases.listDBs();
        //      String currentDatabaseName = null;
        //     Iterator<String> databaseIterator = stringListOfDatabases.iterator();
        //    while (databaseIterator.hasNext()) {
        //         currentDatabaseName=databaseIterator.next();
        //         LOG.info(currentDatabaseName);
        //not quite sure how to query a database...
    }
    //     new DropDB(databaseName).execute(context);
    //      context.close();
}
