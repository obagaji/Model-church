package com.jtc.Model.church.churchUi;

import com.jtc.Model.church.churchEntity.Member;
import com.jtc.Model.church.churchEntity.SerialMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageObjectResize {
   // private ImageRendererClass imageRendererClass;
    private Object[] object;
    private JLabel jLabel;
    private String imageAddress;
    private final SerialMember member;
    private static final Logger LOGGER  = LoggerFactory.getLogger(ImageObjectResize.class);

    public ImageObjectResize(SerialMember member,  ImageConversion imageConversion)
    {
        object = new Object[13];
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
        jLabel= new JLabel(new ImageIcon(im.getScaledInstance(150,120, Image.SCALE_DEFAULT)));
    }

    public Object[] getObject() {
        object[1] = jLabel;

        return object;
    }

    public void setObject(SerialMember  objects ) {

        object[0] = objects.getSerialNumber();
        object[1] = objects.getMemberPhoto();
        object[2] = objects.getId();
        object[3] = objects.getSex();
        object[4] = objects.getFirstName();
        object[5] = objects.getLastName();
        object[6] = objects.getAddress();
        object[7] = objects.getDateBorn();
        object[8] = objects.getPhone();
        object[9] = objects.getStatus();
        object[10] = objects.getAttendance();
        object[11] = objects.getRegisterDate();
        object[12] = objects.getResent();

    }




}
