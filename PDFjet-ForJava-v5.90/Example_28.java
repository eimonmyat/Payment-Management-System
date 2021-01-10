import java.io.*;
import java.util.*;

import com.pdfjet.*;


/**
 *  Example_28.java
 *
 */
public class Example_28 {

    public Example_28() throws Exception {

        PDF pdf = new PDF(
                new BufferedOutputStream(
                        new FileOutputStream("Example_28.pdf")));

        Font f1 = new Font(pdf,
                getClass().getResourceAsStream("fonts/Droid/DroidSans.ttf"));


        Font f2 = new Font(pdf,
                getClass().getResourceAsStream("fonts/Droid/DroidSansFallback.ttf.stream"),
                Font.STREAM);

        Font f3 = new Font(pdf,
                getClass().getResourceAsStream("fonts/Noto/NotoSansSymbols-Regular-Subsetted.ttf.stream"),
                Font.STREAM);

        f1.setSize(11f);
        f2.setSize(11f);
        f3.setSize(11f);

        Page page = new Page(pdf, Letter.LANDSCAPE);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("data/report.csv"), "UTF-8"));

        float y = 40f;
        String str = null;
        while ((str = reader.readLine()) != null) {
            new TextLine(f1, str)
                    .setFallbackFont(f2)
                    .setLocation(50f, y += 20f)
                    .drawOn(page);
        }
        reader.close();

        float x = 50f;
        y = 210f;
        float dy = 22f;

        TextLine text = new TextLine(f3);
        StringBuilder buf = new StringBuilder();
        int count = 0;
        for (int i = 0x2200; i <= 0x22FF; i++) {
            // Draw the Math Symbols
            if (count % 80 == 0) {
                text.setText(buf.toString());
                text.setLocation(x, y += dy);
                text.drawOn(page);
                buf.setLength(0);
            }
            buf.append((char) i);
            count++;
        }
        text.setText(buf.toString());
        text.setLocation(x, y += dy);
        text.drawOn(page);
        buf.setLength(0);

        count = 0;
        for (int i = 0x25A0; i <= 0x25FF; i++) {
            // Draw the Geometric Shapes
            if (count % 80 == 0) {
                text.setText(buf.toString());
                text.setLocation(x, y += dy);
                text.drawOn(page);
                buf.setLength(0);
            }
            buf.append((char) i);
            count++;
        }
        text.setText(buf.toString());
        text.setLocation(x, y += dy);
        text.drawOn(page);
        buf.setLength(0);

        count = 0;
        for (int i = 0x2701; i <= 0x27ff; i++) {
            // Draw the Dingbats
            if (count % 80 == 0) {
                text.setText(buf.toString());
                text.setLocation(x, y += dy);
                text.drawOn(page);
                buf.setLength(0);
            }
            buf.append((char) i);
            count++;
        }
        text.setText(buf.toString());
        text.setLocation(x, y += dy);
        text.drawOn(page);
        buf.setLength(0);

        count = 0;
        for (int i = 0x2800; i <= 0x28FF; i++) {
            // Draw the Braille Patterns
            if (count % 80 == 0) {
                text.setText(buf.toString());
                text.setLocation(x, y += dy);
                text.drawOn(page);
                buf.setLength(0);
            }
            buf.append((char) i);
            count++;
        }
        text.setText(buf.toString());
        text.setLocation(x, y);
        text.drawOn(page);

        pdf.close();
    }


    public static void main(String[] args) throws Exception {
        new Example_28();
    }

}   // End of Example_28.java
