package com.example.main_app;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Activity_26 extends Activity {

    String string = "---------------------------------- data_26.xml ----------------------------------\n" +
            "<data>\n" +
            "    <phone>\n" +
            "        <company>Samsung</company>\n" +
            "        <model>Galaxy</model>\n" +
            "        <price>18000</price>\n" +
            "        <screen multitouch=\"yes\" resolution=\"320x480\">3</screen>\n" +
            "        <colors>\n" +
            "            <color>black</color>\n" +
            "            <color>white</color>\n" +
            "        </colors>\n" +
            "    </phone>\n" +
            "</data>" + "\n\n" +
            "------------------------------ PARSE XML FILE ------------------------------\n";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_26);
        textView = findViewById(R.id.Act26_textView);

        String tmp = "";

        try {
            XmlPullParser xpp = prepareXpp();

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
                    case XmlPullParser.START_DOCUMENT:
                        string = string.concat("START_DOCUMENT" + "\n");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        string = string.concat("START_TAG: name = " + xpp.getName()
                                + ", depth = " + xpp.getDepth() + ", attrCount = "
                                + xpp.getAttributeCount() + "\n");
                        tmp = "";
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(tmp))
                            string = string.concat("Attributes: " + tmp + "\n");
                        break;
                    // конец тэга
                    case XmlPullParser.END_TAG:
                        string = string.concat("END_TAG: name = " + xpp.getName() + "\n");
                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        string = string.concat("text = " + xpp.getText() + "\n");
                        break;

                    default:
                        break;
                }
                // следующий элемент
                xpp.next();
            }string = string.concat("END_DOCUMENT" + "\n");


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView.setText(string);
    }

    XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data_26);
    }
}