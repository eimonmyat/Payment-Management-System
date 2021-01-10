import java.io.*;
import java.util.*;
import java.util.zip.*;

import com.pdfjet.*;


/**
 *  Example_19.java
 *
 */
class Example_19 {

    public Example_19(String fileNumber, String fileName) throws Exception {

        PDF pdf = new PDF(new BufferedOutputStream(
                new FileOutputStream("Example_19_" + fileNumber + ".pdf")));

        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("data/testPDFs/" + fileName));
        Map<Integer, PDFobj> objects = pdf.read(bis);
        bis.close();

        Image image = new Image(
                objects,
                getClass().getResourceAsStream("images/BARCODE.PNG"),
                ImageType.PNG);

        Font f1 = new Font(
                objects,
                getClass().getResourceAsStream("fonts/Droid/DroidSans.ttf.stream"),
                Font.STREAM);
        f1.setSize(12f);

        Font f2 = new Font(
                objects,
                getClass().getResourceAsStream("fonts/Droid/DroidSans-Bold.ttf.stream"),
                Font.STREAM);
        f2.setSize(12f);

        List<PDFobj> pages = pdf.getPageObjects(objects);

        Page page = new Page(pdf, objects, pages.get(0));

        page.addResource(image);
        page.addResource(f1);
        page.addResource(f2);
        Font f3 = page.addResource(CoreFont.HELVETICA).setSize(12f);

        image.setLocation(480f, 57f);
        image.scaleBy(0.40f);
        image.drawOn(page);

        float x = 23f;
        float y = 185f;
        float dx = 15f;
        float dy = 24f;

        page.setBrushColor(Color.blue);

        // First Name and Initial
        // page.drawString(f1, "John", x, y);
        page.drawString(f2, "Иван", x, y);

        // Last Name
        page.drawString(f3, "Jones", x + 258f, y);

        // Social Insurance Number
        page.drawString(f1, stripSpacesAndDashes("243-590-129"), x + 422f, y, dx);

        // Last Name at Birth
        page.drawString(f1, "Culverton", x, y += dy);

        // Mailing Address
        page.drawString(f1, "10 Elm Street", x, y += dy);

        // City
        page.drawString(f1, "Toronto", x, y + dy);

        // Province or Territory
        page.drawString(f1, "Ontario", x + 365f, y += dy);

        // Postal Code
        page.drawString(f1, stripSpacesAndDashes("L7B 2E9"), x + 467f, y, dx);

        // Home Address
        page.drawString(f1, "10 Oak Road", x, y += dy);

        // City
        y += dy;
        page.drawString(f1, "Toronto", x, y);

        // Previous Province or Territory
        page.drawString(f1, "Ontario", x + 365f, y);

        // Postal Code
        page.drawString(f1, stripSpacesAndDashes("L7B 2E9"), x + 467f, y, dx);

        // Home telephone number
        page.drawString(f1, "905-222-3333", x, y + dy);
        // Work telephone number
        page.drawString(f1, "416-567-9903", x + 279f, y += dy);

        // Previous province or territory
        page.drawString(f1, "British Columbia", x + 452f, y += dy);

        // Move date from previous province or territory
        y += dy;
        page.drawString(f1, stripSpacesAndDashes("2016-04-12"), x + 437f, y, dx);

        // Date new maritial status began
        page.drawString(f1, stripSpacesAndDashes("2014-11-02"), x + 437f, 467f, dx);

        // First name of spouse
        y = 521f;
        page.drawString(f1, "Melanie", x, y);
        // Last name of spouse
        page.drawString(f1, "Jones", x + 258f, y);

        // Social Insurance number of spouse
        page.drawString(f1, stripSpacesAndDashes("192-760-427"), x + 422f, y, dx);

        // Spouse or common-law partner's address
        page.drawString(f1, "12 Smithfield Drive", x, 554f);

        // Signature Date
        page.drawString(f1, "2016-08-07", x + 475f, 615f);

        // Signature Date of spouse
        page.drawString(f1, "2016-08-07", x + 475f, 651f);

        // Female Checkbox 1
        // xMarkCheckBox(page, 477.5f, 197.5f, 7f);

        // Male Checkbox 1
        xMarkCheckBox(page, 534.5f, 197.5f, 7f);

        // Married
        xMarkCheckBox(page, 34.5f, 424f, 7f);

        // Living common-law
        // xMarkCheckBox(page, 121.5f, 424f, 7f);

        // Widowed
        // xMarkCheckBox(page, 235.5f, 424f, 7f);

        // Divorced
        // xMarkCheckBox(page, 325.5f, 424f, 7f);

        // Separated
        // xMarkCheckBox(page, 415.5f, 424f, 7f);

        // Single
        // xMarkCheckBox(page, 505.5f, 424f, 7f);

        // Female Checkbox 2
        xMarkCheckBox(page, 478.5f, 536.5f, 7f);

        // Male Checkbox 2
        // xMarkCheckBox(page, 535.5f, 536.5f, 7f);

        page.complete();

        if (fileName.endsWith("rc65-16e.pdf")) {
            Set<Integer> set = new HashSet<Integer>();
            set.add(2);
            pdf.removePages(set, objects);
        }

        pdf.addObjects(objects);

        pdf.close();
    }


    private void xMarkCheckBox(Page page, float x, float y, float diagonal) throws Exception {
        page.setPenColor(Color.blue);
        page.setPenWidth(diagonal / 5);
        page.moveTo(x, y);
        page.lineTo(x + diagonal, y + diagonal);
        page.moveTo(x, y + diagonal);
        page.lineTo(x + diagonal, y);
        page.strokePath();
    }


    private String stripSpacesAndDashes(String str) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != ' ' && ch != '-') {
                buf.append(ch);
            }
        }
        return buf.toString();
    }


    public static void main(String[] args) throws Exception {
        new Example_19("00", "rc65-16e.pdf");
    }

}   // End of Example_19.java
