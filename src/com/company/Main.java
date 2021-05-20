package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {
//      Pictures picture = new Pictures();
//      picture.pictures();


        try {
            String cros1="unned.jpg";
            File file = new File(cros1);
            BufferedImage source = ImageIO.read(file);
            /*BufferedImage imagePixelated = ImageUtil.pixelate(source, 40);
            ImageIO.write(imagePixelated, "jpg", new File("image-pixelated.jpg"));
*/

            CreatCrossword creatCrossword = new CreatCrossword(source);
            creatCrossword.creatCross();
            System.out.println("Width " + source.getWidth() + " Height " + source.getHeight() );

            FileInputStream fileInputStream = new FileInputStream("cross.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            SevedCros seve = (SevedCros) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(seve);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла :"+ e);
            e.printStackTrace();
        }


    }
}