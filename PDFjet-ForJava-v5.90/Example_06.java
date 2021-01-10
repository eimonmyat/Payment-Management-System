import java.io.*;
import java.util.*;

import com.pdfjet.*;


/**
 *  Example_06.java
 *
 */
class Example_06 {

    public Example_06(String mode) throws Exception {

        PDF pdf = new PDF(
                new BufferedOutputStream(
                        new FileOutputStream("Example_06.pdf")));

        Font f1 = null;
        Font f2 = null;
        if (mode.equals("stream")) {
            // Use .ttf.stream fonts
            f1 = new Font(pdf,
                    getClass().getResourceAsStream("fonts/OpenSans/OpenSans-Regular.ttf.stream"),
                    Font.STREAM);
            f2 = new Font(pdf,
                    getClass().getResourceAsStream("fonts/Droid/DroidSansFallback.ttf.stream"),
                    Font.STREAM);
        }
        else {
            // Use standard TTF fonts
            f1 = new Font(pdf,
                    getClass().getResourceAsStream("fonts/OpenSans/OpenSans-Regular.ttf"));
            f2 = new Font(pdf,
                    getClass().getResourceAsStream("fonts/Droid/DroidSansFallback.ttf"));
        }
        f1.setSize(12f);
        f2.setSize(12f);

        Page page = new Page(pdf, Letter.PORTRAIT);

        List<Paragraph> paragraphs = new ArrayList<Paragraph>();
        Paragraph paragraph = null;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("data/LCG.txt"), "UTF-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            paragraph = new Paragraph();
            TextLine textLine = new TextLine(f1, line);
            textLine.setFallbackFont(f2);
            paragraph.add(textLine);
            paragraphs.add(paragraph);
        }
        Text text = new Text(paragraphs);
        text.setLocation(50f, 50f);
        text.setWidth(500f);
        text.drawOn(page);


        page = new Page(pdf, Letter.PORTRAIT);

        paragraphs = new ArrayList<Paragraph>();

        reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("data/CJK.txt"), "UTF-8"));
        while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            paragraph = new Paragraph();
            TextLine textLine = new TextLine(f2, line);
            textLine.setFallbackFont(f1);
            paragraph.add(textLine);
            paragraphs.add(paragraph);
        }
        text = new Text(paragraphs);
        text.setLocation(50f, 50f);
        text.setWidth(500f);
        text.drawOn(page);

/*
        List<Paragraph> paragraphs = new ArrayList<Paragraph>();
        Paragraph paragraph = new Paragraph();

        TextLine textLine = new TextLine(f1, "Happy New Year!");
        textLine.setFallbackFont(f2);
        textLine.setLocation(70f, 70f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        textLine = new TextLine(f1, "С Новым Годом!");
        textLine.setFallbackFont(f2);
        textLine.setLocation(70f, 100f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        textLine = new TextLine(f1, "Ευτυχισμένο το Νέο Έτος!");
        textLine.setFallbackFont(f2);
        textLine.setLocation(70f, 130f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        textLine = new TextLine(f1, "新年快樂！");
        textLine.setFallbackFont(f2);
        textLine.setLocation(300f, 70f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        textLine = new TextLine(f1, "新年快乐！");
        textLine.setFallbackFont(f2);
        textLine.setLocation(300f, 100f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        textLine = new TextLine(f1, "明けましておめでとうございます！");
        textLine.setFallbackFont(f2);
        textLine.setLocation(300f, 130f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        textLine = new TextLine(f1, "새해 복 많이 받으세요!");
        textLine.setFallbackFont(f2);
        textLine.setLocation(300f, 160f);
        textLine.drawOn(page);

        paragraph.add(textLine);

        paragraphs.add(paragraph);

        Text text = new Text(paragraphs);
        text.setLocation(70f, 200f);
        text.setWidth(500f);
        text.drawOn(page);
*/

        pdf.close();
    }


    public static void main(String[] args) throws Exception {
        long time0 = System.currentTimeMillis();
        if (args.length > 0 && args[0].equals("stream")) {
            new Example_06("stream");
        }
        else {
            new Example_06("TTF");
        }
        long time1 = System.currentTimeMillis();
        // System.out.println(time1 - time0);
    }

}   // End of Example_06.java
