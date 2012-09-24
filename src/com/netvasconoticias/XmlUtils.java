package com.netvasconoticias;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XmlUtils {
	
	public static String getXML(String url){
		String line = null;

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			//HttpPost httpPost = new HttpPost("http://feeds.feedburner.com/Netvasco-Noticias");

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			line = EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			line = "<results status=\"error\"><msg>Can't connect to server</msg></results>";
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			line = "<results status=\"error\"><msg>Can't connect to server</msg></results>";
		} catch (IOException e) {
			System.out.println(e.getMessage());
			line = "<results status=\"error\"><msg>Can't connect to server</msg></results>";
		}

		return line;

	}
	
	public static Document XMLfromString(String xml){

		Document doc = null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
			//InputSource is = new InputSource(new ByteArrayInputStream(
				//    xml.getBytes("ISO-8859-1")));
		        doc = db.parse(is); 

			} catch (ParserConfigurationException e) {
				System.out.println("XML parse error: " + e.getMessage());
				return null;
			} catch (SAXException e) {
				System.out.println("Wrong XML file structure: " + e.getMessage());
	            return null;
			} catch (IOException e) {
				System.out.println("I/O exeption: " + e.getMessage());
				return null;
			}

	        return doc;

		}
	public static String getValue(Element item, String str) {		

		NodeList n = item.getElementsByTagName(str);		
		return XmlUtils.getElementValue(n.item(0));
	}
	public final static String getElementValue( Node elem ) {
	     Node kid;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() ){
	                 if( kid.getNodeType() == Node.TEXT_NODE  ){
	                     return kid.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }

}
