package com.example.demo;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.tomcat.util.json.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class temp {

	public static void main(String[] args) throws IOException, ParseException, SAXException {
		
		String inline="",s="https://reststop.randomhouse.com/resources/authors?firstName=Dan&lastName=Brown";
		
		URL url = new URL(s);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode(); 
		if(responsecode==HttpURLConnection.HTTP_OK)
		{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document d=builder.parse(url.openStream());
		
			Element ele=d.getDocumentElement();
			
			NodeList nList = d.getElementsByTagName("author");
			
			System.out.println("Number of Authors with this name :"+nList.getLength());
			System.out.println("");
			for(int i=0;i<nList.getLength();i++)
			{
		
				Node node=nList.item(i);
					Element eElement = (Element) node;
					
					System.out.println("Id Number is : "+eElement.getElementsByTagName("authorid").item(0).getTextContent());
					
					System.out.println("First Name :"+eElement.getElementsByTagName("authorfirst").item(0).getTextContent()+"Last Name :"
					+eElement.getElementsByTagName("authorlast").item(0).getTextContent());
					
					System.out.println("Number of titles: "+eElement.getElementsByTagName("isbn").getLength());
					System.out.println("");
					
			}
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		}
	}
}
