package com.flannery.parse;

import com.android.utils.XmlUtils;
import com.android.xml.AndroidManifest;
import com.sun.org.apache.xpath.internal.operations.And;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
//https://www.cnblogs.com/shenliang123/archive/2012/05/11/2495252.html
/*
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.joyy.android_project">
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.READ_PRIVILEGED_PHONE_STATE" />
    <uses-permission android:name="android.permission.CALL_PRIVILEGED"/>
    <!--存储权限-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <!--电话权限-->
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <!--相机权限-->
    <uses-permission android:name="android.permission.CAMERA" />
    <!--定位权限-->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Android_project">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
 */
public class ParseManifest {
    public static void main(String[] args) throws IOException, SAXException {
        Document document = XmlUtils.parseUtfXmlFile(new File("/Users/zhaojian/Desktop/AndroidParser/android_project/app/src/main/bak-AndroidManifest.xml"), false);
        System.out.println(document);
        NodeList manifestNode = document.getElementsByTagName(AndroidManifest.NODE_MANIFEST);
        for (int i = 0; i < manifestNode.getLength(); i++) {
            printTag(AndroidManifest.NODE_MANIFEST);
            Node item = manifestNode.item(i);
            printManifest(item);

            NodeList childNodes = item.getChildNodes();
            System.out.println(childNodes.getLength());
            for (int s = 0; s < childNodes.getLength(); s++) {
                Node childNode = childNodes.item(s);
                //printNode(childNode);
                String nodeName = childNode.getNodeName();
                if(AndroidManifest.NODE_USES_PERMISSION.equals(nodeName)) {
                    printUsesPermission(childNode);
                } else if(AndroidManifest.NODE_APPLICATION.equals(nodeName)) {
                    printApplication(childNode);
                } else {
                    //System.out.println(childNode.getNodeValue());
                }
            }
        }
    }

    private static void printActivity(Node item) {
        StringBuilder sb = new StringBuilder();
        String nodeName = item.getNodeName();
        sb.append(nodeName).append("\t");
        NamedNodeMap attributes = item.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node nodeAttr = attributes.item(i);
            sb.append(nodeAttr.getNodeName()).append(":").append(nodeAttr.getNodeValue()).append("\t");
        }
        System.out.println(sb);

        NodeList childNodes = item.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (AndroidManifest.NODE_INTENT.equals(childNodes.item(i).getNodeName())) {
                System.out.println(childNodes.item(i).getNodeName());
            }
        }
    }

    private static void printApplication(Node item) {
        StringBuilder sb = new StringBuilder();
        String nodeName = item.getNodeName();
        sb.append(nodeName).append("\t");
        NamedNodeMap attributes = item.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node nodeAttr = attributes.item(i);
            sb.append(nodeAttr.getNodeName()).append(":").append(nodeAttr.getNodeValue()).append("\t");
        }
        System.out.println(sb);

        NodeList applicationNodes = item.getChildNodes();
        for (int j = 0; j < applicationNodes.getLength(); j++) {
            Node childApplicationNode = applicationNodes.item(j);
            if(AndroidManifest.NODE_ACTIVITY.equals(childApplicationNode.getNodeName())) {
                printActivity(childApplicationNode);
            }
        }
    }

    private static void printUsesPermission(Node childNode) {
        StringBuilder sb = new StringBuilder();
        String nodeName = childNode.getNodeName();
        sb.append(nodeName).append("\t");
        NamedNodeMap attributes = childNode.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node item = attributes.item(i);
            sb.append(item.getNodeName()).append(item.getNodeValue());
        }
        System.out.println(sb);
    }


    private static void printManifest(Node item) {
        StringBuilder sb = new StringBuilder();
        String nodeName = item.getNodeName();
        sb.append(nodeName).append("\t");
        NamedNodeMap attributes = item.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node nodeAttr = attributes.item(i);
            sb.append(nodeAttr.getNodeName()).append(":").append(nodeAttr.getNodeValue()).append("\t");
        }
        System.out.println(sb);
    }
    private static void printNode(Node node) {
        String nodeName = node.getNodeName();
        System.out.println(nodeName);
    }

    private static final void printTag(String name) {
        System.out.println("-------" + name + "---------");
    }


}
