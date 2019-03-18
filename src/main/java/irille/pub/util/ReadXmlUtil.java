package irille.pub.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ReadXmlUtil {
  public static List<String> getValues(String node, String xmlString)
      throws ParserConfigurationException, SAXException, IOException {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    db = dbf.newDocumentBuilder();
    Document document = null;
    document = db.parse(new InputSource(new StringReader(xmlString)));
    List<String> values = new ArrayList<String>();
    NodeList list = document.getElementsByTagName(node);
    for (int i = 0; i < list.getLength(); i++) {
      if (node.equals(list.item(i).getNodeName())) {
        values.add(list.item(i).getFirstChild().getNodeValue());
      }
    }
    return values;
  }
}
