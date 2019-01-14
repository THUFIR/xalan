package xalan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Transforms {

    private static final Logger LOG = Logger.getLogger(Transforms.class.getName());
    private Properties properties = new Properties();

    private Transforms() {
    }

    public Transforms(Properties properties) {
        this.properties = properties;
    }

    public Result withJAXP() throws Exception {
        URL url = new URL(properties.getProperty("url"));
        InputStream inputStream = url.openStream();
        InputSource inputSource = new InputSource(inputStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, inputSource);
        Result result = new StreamResult(System.out);
        factory.newTransformer().transform(source, result);
        return result;
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

    public Document createDocumentFromURL(URL url) throws SAXException, IOException, TransformerException, ParserConfigurationException {
        LOG.info(url.toString());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, new InputSource(url.toString()));

        DOMResult domResult = new DOMResult();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, domResult);  //how do I find the result of this operation?

        LOG.info(domResult.toString());  //traverse or iterate how?

        javax.xml.parsers.DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        Document document = documentBuilder.parse();   ///bzzzt, wrong
        Document fdf = documentBuilder.parse(new InputSource(url.openStream()));

        //  Document document = (Document) domResult.getNode();
        //   LOG.info(document.getDocumentElement().getTagName());
        return fdf;
    }

}
