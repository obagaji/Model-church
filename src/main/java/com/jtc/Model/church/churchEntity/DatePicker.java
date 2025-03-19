package com.jtc.Model.church.churchEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker {
    private int month = Calendar.getInstance().get(Calendar.MONTH);

    private final JDialog d = new JDialog();

    private final String day = "";

    private final JButton[] button = new JButton[49];

    private final JLabel l = new JLabel("", SwingConstants.HORIZONTAL);

    private final int year = Calendar.getInstance().get(Calendar.YEAR);

    private int selection;

    private String formatedDate;

    private final JPanel pl = new JPanel(new GridLayout(7, 7));

    public DatePicker(JFrame parent)
    {
        this.d.setModal(true);

        String[] header = new String[]{"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

        this.pl.setPreferredSize(new Dimension(430, 201));

        ButtonHandler hand = new ButtonHandler();

        int x;

        for (x = 0; x < this.button.length; ++x)
        {
            this.button[x] = new JButton();

            this.button[x].setFocusPainted(false);

            this.button[x].setBackground(Color.white);
        }

        this.button[7].addActionListener(hand);

        this.button[8].addActionListener(hand);

        this.button[9].addActionListener(hand);

        this.button[10].addActionListener(hand);

        this.button[11].addActionListener(hand);

        this.button[12].addActionListener(hand);

        this.button[13].addActionListener(hand);

        this.button[14].addActionListener(hand);

        this.button[15].addActionListener(hand);

        this.button[16].addActionListener(hand);

        this.button[17].addActionListener(hand);

        this.button[18].addActionListener(hand);

        this.button[19].addActionListener(hand);

        this.button[20].addActionListener(hand);

        this.button[21].addActionListener(hand);

        this.button[22].addActionListener(hand);

        this.button[23].addActionListener(hand);

        this.button[24].addActionListener(hand);

        this.button[25].addActionListener(hand);

        this.button[26].addActionListener(hand);

        this.button[27].addActionListener(hand);

        this.button[28].addActionListener(hand);

        this.button[29].addActionListener(hand);

        this.button[30].addActionListener(hand);

        this.button[31].addActionListener(hand);

        this.button[32].addActionListener(hand);

        this.button[33].addActionListener(hand);

        this.button[34].addActionListener(hand);

        this.button[35].addActionListener(hand);

        this.button[36].addActionListener(hand);

        this.button[37].addActionListener(hand);

        this.button[38].addActionListener(hand);

        this.button[39].addActionListener(hand);

        this.button[40].addActionListener(hand);

        this.button[41].addActionListener(hand);

        this.button[42].addActionListener(hand);

        this.button[43].addActionListener(hand);

        this.button[44].addActionListener(hand);

        this.button[45].addActionListener(hand);

        this.button[46].addActionListener(hand);

        this.button[47].addActionListener(hand);

        this.button[48].addActionListener(hand);

        for (x = 0; x < this.button.length; ++x)
        {
            if (x < 7)
            {
                for (x = 0; x < 7; ++x)
                {
                    this.button[x].setText(header[x]);

                    this.button[x].setForeground(Color.red);

                    this.pl.add(this.button[x]);
                }
            }
        }
        JPanel p2 = new JPanel(new GridLayout(1, 3));

        JButton previous = new JButton("<< Previous");

        previous.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                DatePicker.this.month--;

                DatePicker.this.displayDate();

            }
        });

        p2.add(previous);

        p2.add(this.l);

        JButton next = new JButton("Next>>");

        next.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                DatePicker.this.month++;

                DatePicker.this.displayDate();

            }
        });

        p2.add(next);

        this.d.add(this.pl, "Center");

        this.d.add(p2, "South");

        this.d.pack();

        this.d.setLocationRelativeTo(parent);
    }

    public String getDefaultDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar cal = Calendar.getInstance();

        String DefaultDate = sdf.format(cal.getTime());

        this.d.dispose();

        return DefaultDate;
    }

    public void displayDate()
    {
        for (int x = 7; x < this.button.length; ++x)
        {
            this.button[x].setText("");

            this.pl.add(this.button[x]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar cal = Calendar.getInstance();

        cal.set(this.year, this.month, 1);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        int x = 6 + dayOfWeek;

        for (int day = 1; day <= daysInMonth; ++day)
        {
            this.button[x].setText("" + day);

            ++x;
        }

        this.l.setText(sdf.format(cal.getTime()));

        this.d.setTitle("Date Picker");

        this.d.setVisible(true);
    }

    public void setPickedDate(String da)
    {
        if (!(da.equalsIgnoreCase("")))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            Calendar con = Calendar.getInstance();

            con.set(this.year, this.month, Integer.parseInt(da));

            this.formatedDate = sdf.format(con.getTime());
        }

    }

    public String getPickedDate()
    {
        this.d.dispose();

        return this.formatedDate;
    }



    private class ButtonHandler implements ActionListener
    {

        public void actionPerformed(ActionEvent event)
        {
            String days = event.getActionCommand();

            DatePicker.this.setPickedDate(days);

            DatePicker.this.d.dispose();

        }
    }

}




