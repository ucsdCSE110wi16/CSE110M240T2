package com.project.cse110.geometryapp;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Abhishek on 2/21/16.
 */
public class DocParser {

    private Document document;
    private InputStream in;
    private XPath xPath;

    public DocParser(Context context) {

        this.in = context.getResources().openRawResource(
                context.getResources().getIdentifier("chapters", "raw", context.getPackageName()));

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        this.document = null;
        try {
            this.document = builder.parse(in);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.xPath =  XPathFactory.newInstance().newXPath();
    }

    public Document getDocument() {
        return document;
    }

    public void closeStream() {
        try {
            in.close();
        } catch (IOException e) {

        }
    }

    public Node getNode(String location) {
        Node node = null;
        try {
            node = (Node) this.xPath.compile(location).evaluate(this.document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return node;
    }

    public NodeList getNodeList(String location) {
        NodeList list = null;
        try {
            list = (NodeList) this.xPath.compile(location).evaluate(this.document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateXMLFile() {
//        try {
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(this.document);
//            StreamResult result = new StreamResult(this.in);
//            transformer.transform(source, result);
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
    }
}
