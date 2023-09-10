package sqlutil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class IconHelp {
    public static Icon StringtoImage(String ss) throws IOException, IOException {
        BASE64Decoder decoder=new BASE64Decoder();
        byte[]imagebyte=decoder.decodeBuffer(ss);
        BufferedImage imag= ImageIO.read(new ByteArrayInputStream(imagebyte));
        if(imag!=null){
            Image resultingImage = imag.getScaledInstance(100, 130, Image.SCALE_AREA_AVERAGING);
            BufferedImage outputImage = new BufferedImage(100, 130, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
            ImageIcon icon=new ImageIcon(outputImage);
            return icon;
        }
        else return null;
    }

    public static String ImagetoString(String path) throws IOException {
        FileInputStream fis=new FileInputStream(path);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ByteArrayOutputStream bos= new ByteArrayOutputStream();
        byte[]buff=new byte[1024];
        int len=0;
        while((len=fis.read(buff))!=-1)bos.write(buff,0,len);

        byte[]rs=bos.toByteArray();
        BASE64Encoder encoder=new BASE64Encoder();
        String str=encoder.encode(rs).trim();
        return str;
    }
}
