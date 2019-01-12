package xalan;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class X {

    public X() {
    }

    public void transform(URI xml, URI xsl, URI out) throws Exception {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsl)));
        transformer.transform(new StreamSource(new File(xml)), new StreamResult(new FileOutputStream(new File(out))));
    }

}
