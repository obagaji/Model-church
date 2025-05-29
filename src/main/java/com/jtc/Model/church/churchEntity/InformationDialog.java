package com.jtc.Model.church.churchEntity;

import javax.swing.*;
import java.awt.*;

 public class InformationDialog extends JDialog {
    private JPanel panel;

    public InformationDialog(JFrame frame) {
        super(frame, "ABOUT US", null);
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JLabel label1 = new JLabel("                    JTC CONTRACT SERVICE NIGERIA LTD");
        JLabel label2 = new JLabel("                   Phone: 08036127998, 08086748357");
        panel.add(label1);
        panel.add(label2);
        add(panel, "Center");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLocation(350, 250);
    }
}
