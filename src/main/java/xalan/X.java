package xalan;

import java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class X {

    public X() {
    }

    public void transform() throws Exception {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource("/home/thufir/jaxb/xalan/foo.xsl"));
        transformer.transform(new StreamSource("/home/thufir/jaxb/xalan/foo.xml"), new StreamResult(new FileOutputStream("/home/thufir/jaxb/xalan/foo.out")));
    }

}
