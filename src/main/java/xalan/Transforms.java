package xalan;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

public class Transforms {

    private static final Logger LOG = Logger.getLogger(Transforms.class.getName());
    private Properties properties = new Properties();

    private Transforms() {
    }

    public Transforms(Properties properties) {
        this.properties = properties;
    }

    public void saxonTransform() throws Exception {
        URI xml = new URI(properties.getProperty("xml"));
        URI xsl = new URI(properties.getProperty("xsl"));
        URI output = new URI(properties.getProperty("output"));
        Processor proc = new Processor(false);
        XsltCompiler comp = proc.newXsltCompiler();
        XsltExecutable exp = comp.compile(new StreamSource(new File(xsl)));
        XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File(xml)));
        Serializer out = proc.newSerializer(new File(output));
        out.setOutputProperty(Serializer.Property.METHOD, "html");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        XsltTransformer trans = exp.load();
        trans.setInitialContextNode(source);
        trans.setDestination(out);
        trans.transform();
        LOG.info("done");
    }

    public void xalanTransform() throws Exception {
        URI xml = new URI(properties.getProperty("xml"));
        URI xsl = new URI(properties.getProperty("xsl"));
        URI output = new URI(properties.getProperty("output"));
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsl)));
        transformer.transform(new StreamSource(new File(xml)), new StreamResult(new FileOutputStream(new File(output))));
    }

}