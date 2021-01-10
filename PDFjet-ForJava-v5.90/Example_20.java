import java.io.*;
import java.util.*;
import java.util.zip.*;

import com.pdfjet.*;


/**
 *  Example_20.java
 *
 */
class Example_20 {

    public Example_20() throws Exception {

        PDF pdf = new PDF(new BufferedOutputStream(
                new FileOutputStream("Example_20.pdf")));

        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("data/testPDFs/PDFjetLogo.pdf"));
        Map<Integer, PDFobj> objects = pdf.read(bis);
        bis.close();

        pdf.addResourceObjects(objects);

        Font f1 = new Font(pdf,
                getClass().getResourceAsStream(
                        "fonts/OpenSans/OpenSans-Regular.ttf.stream"), Font.STREAM);
        f1.setSize(18f);

        List<PDFobj> pages = pdf.getPageObjects(objects);
        PDFobj contents = pages.get(0).getContentsObject(objects);

        Page page = new Page(pdf, Letter.PORTRAIT);

        float height = 105f;    // The logo height in points.
        float x = 50f;
        float y = 50f;
        float xScale = 0.5f;
        float yScale = 0.5f;

        page.drawContents(
                contents.getData(),
                height,
                x,
                y,
                xScale,
                yScale);

        page.setPenColor(Color.darkblue);
        page.setPenWidth(0f);
        page.drawRect(0f, 0f, 50f, 50f);

        page = new Page(pdf, Letter.PORTRAIT);

        TextLine line = new TextLine(f1, "Hello, World!");
        line.setLocation(50f, 50f);
        line.drawOn(page);

        pdf.close();
    }


    public static void main(String[] args) throws Exception {
        long time0 = System.currentTimeMillis();
        new Example_20();
        long time1 = System.currentTimeMillis();
        // System.out.println(time1 - time0);
    }

}   // End of Example_20.java
