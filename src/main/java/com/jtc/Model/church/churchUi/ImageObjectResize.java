package com.jtc.Model.church.churchUi;

import com.jtc.Model.church.churchEntity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


import static com.jtc.Model.church.churchUi.ImageConversion.convertToBufferedImage;

public class ImageObjectResize {
   // private ImageRendererClass imageRendererClass;
    private Object[] object;
    private JLabel jLabel;
    private String imageAddress;
    private final Member member;
    private static final Logger LOGGER  = LoggerFactory.getLogger(ImageObjectResize.class);

    public ImageObjectResize(Member member, ImageConversion imageConversion)
    {
        object = new Object[12];
        this.member = member;
        setObject(member);
        imageResize(imageConversion, member.getMemberPhoto());
        LOGGER.info("Inside the constructor ImageObjectResize");

    }
    private void imageResize(ImageConversion image, String address) {
        File file = new File(address);

        BufferedImage im;
        try {
            im = ImageConversion.convertToBufferedImage(ImageIO.read((File) file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info(address);
        jLabel= new JLabel(new ImageIcon(im.getScaledInstance(110,140, Image.SCALE_DEFAULT)));
    }

    public Object[] getObject() {
        object[0] = jLabel;

        return object;
    }

    public void setObject(Member  objects) {

        object[0] = objects.getMemberPhoto();
        object[1] = objects.getId();
        object[2] = objects.getSex();
        object[3] = objects.getFirstName();
        object[4] = objects.getLastName();
        object[5] = objects.getAddress();
        object[6] = objects.getDateBorn();
        object[7] = objects.getPhone();
        object[8] = objects.getStatus();
        object[9] = objects.getAttendance();
        object[10] = objects.getRegisterDate();
        object[11] = objects.getResent();
        System.out.println(Arrays.deepToString(object));
    }




}
