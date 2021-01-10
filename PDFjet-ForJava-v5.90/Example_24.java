import java.io.*;

import com.pdfjet.*;


/**
 *  Example_24.java
 *
 */
public class Example_24 {

    public Example_24() throws Exception {

        PDF pdf = new PDF(
                new BufferedOutputStream(
                        new FileOutputStream("Example_24.pdf")));

        Font f1 = new Font(pdf, CoreFont.HELVETICA);

        Image image1 = new Image(
                pdf, new FileInputStream("images/fruit.jpg"), ImageType.JPG);

        Image image2 = new Image(
                pdf, new FileInputStream("images/linux-logo.jet"), ImageType.JET);

        for (int i = 0; i < 500; i++) {
            Page page = new Page(pdf, Letter.PORTRAIT);

            TextLine text = new TextLine(
                    f1, "We are currently generating page = " + i);
            text.setTextDirection(0);
            text.setLocation(100f, 100f);
            text.drawOn(page);

            image1.setLocation(100f, 120f);
            image1.drawOn(page);

            image2.setLocation(100f, 400f);
            image2.drawOn(page);
        }

        pdf.close();
    }


    public static void main(String[] args) {
        try {
            if (args.length > 0 && args[0].equals("time")) {
                long time0 = System.currentTimeMillis();
                new Example_24();
                long time1 = System.currentTimeMillis();
                System.out.println(time1 - time0);
            }
            else {
                new Example_24();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_24.java
