package com.jtc.Model.church.churchUi;
/**
 *
 * @author Musa Daniel
 * @author JTC CONTRACTS SERVICE NIGERIA LIMITED
 * @version 1.2
 */

import com.jtc.Model.church.churchEntity.*;
import com.jtc.Model.church.churchRepo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.JTable.PrintMode;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static java.util.Arrays.stream;

/*
* The springframework stereotype annotation will help spring to pick the class and includes
* it by generating a bean for it.
* Since the application is a desktop application we have to extends the JFrame class
* to be able to make use of the feature available.
*
*
* */
@Service
 public class ChurchJFrame extends JFrame {

    private final JMenu searchMenu, attendanceG, about, admin, menu;

    private JMenuItem edit, allpeople,allLoginTime, menuName, addWork, newMenu, delete, login, displayBirth, workerItem, nonWorkerItem,
            attendans, wAttendance, mAttendance, official,reset, newTable,us,createNewTableUpdateImage,UpdateImageSize;

    private JMenuItem totalActivity, locateActivity, account, exit,acc,StaffOfice,wTimeAttendance,
            firstTimeResponse,addFirstTimer,firstTime;

    private JLabel welcomeLabel,display1,display2,display3,dONLabel,displayL,label;

    private JTable resultTable;

    private Font font;

    private String name;

    private static ArrayList<String> ARRAYLIST = new ArrayList();

    private static ArrayList<String> LOCALARRAY = new ArrayList();

    @Autowired
    QueryImplementation churchService;

    @Autowired
    private ImageRendererClass imageRendererClass;

    public ChurchJFrame()
    {
        super("                                  NEW COVENANT MODEL PARISH         ");
    //    super("                                       LEADING   EDGE   CHRISTAIN   CENTER         ");
        JMenuBar bar = new JMenuBar();

        bar.setOpaque(true);

        bar.setBackground(Color.BLUE);
        welcomeLabel = new JLabel();
// menuItem initialization
        exit = new JMenuItem("EXIT");
        firstTime = new JMenuItem("VIEW FIRST TIME");
        menuName = new JMenuItem("NAME");
        StaffOfice = new JMenuItem("STAFF");
        us = new JMenuItem("INFO");
        edit = new JMenuItem("EDIT");
        edit.setMnemonic('E');
        totalActivity = new JMenuItem("PROGRAM ACTIVITY");
        attendans = new JMenuItem("ATTENDANCE");
        mAttendance = new JMenuItem("MEMBER ATTENDANCE SEARCH");
        wAttendance = new JMenuItem("MEMBER LOGIN TIME");
        wTimeAttendance = new JMenuItem("WORKER LOGIN TIME");
        locateActivity = new JMenuItem("PROGRAM/SUNDAY SERVICE");
        account = new JMenuItem("MAINTENENCE");
        official = new JMenuItem("OFFICIAL");
        addWork = new JMenuItem(" ADD WORKER");
        displayBirth = new JMenuItem("DISPLAY BIRTHDAY");
        displayBirth.setMnemonic('D');
        createNewTableUpdateImage = new JMenuItem("RESET AND UPDATE");
        reset = new JMenuItem("RESET");
        UpdateImageSize = new JMenuItem("UPDATE IMAGE");
        newTable = new JMenuItem("DELETE Table ");
        delete = new JMenuItem(" DELETE");
        newMenu = new JMenuItem(" ADD MEMBER");
        menuName.setMnemonic('D');
        acc = new JMenuItem("VIEW MAINTENANCE");
        acc.setMnemonic('M');
        addFirstTimer = new JMenuItem("FIRST  TIMERS");
        workerItem = new JMenuItem("WORKER");
        workerItem.setMnemonic('W');
        allpeople = new JMenuItem("ALL");
        allpeople.setMnemonic('A');
        allLoginTime = new JMenuItem("LOGIN TIME");
        allLoginTime.setMnemonic('T');
        nonWorkerItem = new JMenuItem(" MEMBER - NON WORKERS");
        nonWorkerItem.setMnemonic('M');
        login = new JMenuItem("LOGIN");
        login.setMnemonic('L');
        firstTimeResponse = new JMenuItem("FIRST  TIMERS RESPONSE");

        menu = new JMenu("VIEW");
        menu.setMnemonic('V');
        font = new Font("SansSerif", 2, 12);
        menu.setFont(font);
        menu.setFocusPainted(false);
        menu.setText(" VIEW");
        menu.addSeparator();
        menu.add(edit);
        menu.addSeparator();
        menu.add(workerItem);
        menu.addSeparator();
        menu.add(allpeople);
        menu.addSeparator();
        menu.add(nonWorkerItem);
        menu.addSeparator();
        menu.add(acc);
        menu.addSeparator();
        menu.add(locateActivity);
       // menu.addSeparator();
       // menu.add(allLoginTime);

        about = new JMenu("ABOUT");
        about.setText("  ABOUT");
        about.setMnemonic('B');
        about.add(us);
        about.setFont(font);
        about.add(StaffOfice);
        StaffOfice.setMnemonic('S');

        attendanceG = new JMenu("  ATTENDANCE");
        attendanceG.add(attendans);
        attendanceG.addSeparator();
        attendanceG.add(mAttendance);
        attendanceG.addSeparator();
        attendanceG.add(wAttendance);
     //   attendanceG.addSeparator();
     //   attendanceG.add(wTimeAttendance);

        searchMenu = new JMenu();
        searchMenu.setText("LOGIN");
        searchMenu.setFont(font);
        searchMenu.setIcon(new ImageIcon("C:\\church\\rccgsmall.jpg"));
    //    searchMenu.setIcon(new javax.swing.ImageIcon("C:\\church\\LECCnew.png"));
        searchMenu.addSeparator();
        searchMenu.add(login);
        searchMenu.addSeparator();
        searchMenu.add(displayBirth);
        searchMenu.addSeparator();
        searchMenu.add(reset);
      //  searchMenu.addSeparator();
      //  searchMenu.add(newTable);
    //    searchMenu.addSeparator();
    //    searchMenu.add(createNewTableUpdateImage);
        searchMenu.addSeparator();
        searchMenu.add(UpdateImageSize);
        searchMenu.addSeparator();
        searchMenu.add(exit);

        admin = new JMenu();
        admin.setBorderPainted(true);
        font = new Font("SansSerif", 2, 12);
        admin.setFont(font);
        admin.setText(" ADMIN");
        admin.setMnemonic('M');
        admin.setBackground(Color.red);
        delete.setMnemonic('D');
        admin.add(delete);
        admin.addSeparator();
        addWork.setMnemonic('A');
        admin.add(addWork);
        admin.addSeparator();
        newMenu.setMnemonic('N');
        admin.add(newMenu);
        admin.addSeparator();
        admin.add(totalActivity);
        admin.addSeparator();
        admin.add(official);
        admin.addSeparator();
        admin.add(account);

        dONLabel = new JLabel();
        displayL = new JLabel(" ");
        displayL.setHorizontalAlignment(0);
        setLayout(new BorderLayout());
        label = new JLabel();

        dONLabel.setIcon(new ImageIcon("C:\\church\\NCMP1.jpg"));
       // dONLabel.setIcon(new javax.swing.ImageIcon("C:\\church\\LECC LOGO.png"));
        dONLabel.setHorizontalAlignment(0);
        dONLabel.setIgnoreRepaint(true);

        bar.add(searchMenu);
        bar.add(menu);
        bar.add(attendanceG);
        bar.add(admin);
        bar.add(about);
        bar.add(new JLabel("                                                                                                                                                                                                  "));
        bar.add(label);
        String myString = DateFormat.getDateTimeInstance(1, 1).format(new Date());
        label.setText(myString);
        setJMenuBar(bar);

        us.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                InformationDialog frame = new InformationDialog((JFrame) null);
                frame.setVisible(true);

            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();

            }
        });

         locateActivity.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent event) {
                 DateChurchAttendanceDialog Jrame = new DateChurchAttendanceDialog((JFrame) null);
                 Jrame.setVisible(true);
             }
         });

         account.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MaintananceDialog Jrame = new MaintananceDialog((JFrame) null);
                Jrame.setVisible(true);
            }
        });

         acc.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent event) {
                 ViewMaintenanceDialog Jrame = new ViewMaintenanceDialog((JFrame) null);
                 Jrame.setVisible(true);
             }
         });

         allpeople.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AllDialog allediting = new AllDialog((JFrame) null);
                allediting.setVisible(true);
            }
        });
        allLoginTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewLoginTimeDialog viewLoginTimeDialog = new ViewLoginTimeDialog((JFrame) null);
                viewLoginTimeDialog.setVisible(true);
            }
        });

         addWork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddWorkerDialog editing = new AddWorkerDialog((JFrame) null);
                editing.setVisible(true);

            }
        });

         edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditDialog editing = new EditDialog((JFrame) null);
                editing.setVisible(true);

            }
        });

        wAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WAttendanceDialog workerAttendance = new WAttendanceDialog((JFrame) null);
                workerAttendance.setVisible(true);
            }
        });
        wTimeAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WAttendanceTimeDialog workerAttendanceTime = new WAttendanceTimeDialog((JFrame) null);
                workerAttendanceTime.setVisible(true);
            }
        });

         workerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WDialog worker = new WDialog((JFrame) null);
                worker.setVisible(true);
            }
        });

         delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DelDialog del = new DelDialog((JFrame) null);
                del.setVisible(true);
            }
        });
        UpdateImageSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateImageDialog del = new UpdateImageDialog((JFrame) null);
                del.setVisible(true);
            }
        });
        createNewTableUpdateImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResetTableDialog del = new ResetTableDialog((JFrame) null);
                del.setVisible(true);
            }
        });


        // add to new table
        newTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                churchService.deleteSerialMember();

            }
        });


        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = 0;
                    for (DateClass dateClass1: churchService.getAllDateClass())
                    {
                        x = churchService.updateAllDateClass(dateClass1.getIdmember(),"ABSENCE");

                    }
                    if (x < 0) {
                        JOptionPane.showMessageDialog(null, "Reset Not Completed Retry", "RESET", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Reset Completed ", "RESET", JOptionPane.INFORMATION_MESSAGE);
            }
        });
         totalActivity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SundayActivityDialog frame = new SundayActivityDialog((JFrame) null);
                frame.setVisible(true);

            }
        });

         attendans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Attendance tend = new Attendance((JFrame) null);
                tend.setVisible(true);
            }
        });

         mAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateAttendanceMemberDialog tend = new DateAttendanceMemberDialog((JFrame) null);
                tend.setVisible(true);
            }
        });

         official.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Display frame = new Display((JFrame) null);
                frame.setVisible(true);

            }
        });

         newMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MemberDialog member = new MemberDialog((JFrame) null);
                member.setVisible(true);
            }
        });

         login.addActionListener(e -> {
            LogDialog lin = new LogDialog((JFrame) null);
            lin.setVisible(true);
        });

        nonWorkerItem.addActionListener(e -> {
            NonDialog non = new NonDialog((JFrame) null);
            non.setVisible(true);
        });

        displayBirth.addActionListener(e -> {
            DisplayTable dis = new DisplayTable((JFrame) null);
            dis.setVisible(true);
        });

    //    welcomeLabel = new JLabel();
    //    welcomeLabel.setBorder(BorderFactory.createTitledBorder(""));
        font = new Font("SansSerif", 2, 18);
        welcomeLabel.setFont(font);
        welcomeLabel.setText("                                        \n LECC :      \n LEADING     EDGE      \n   CHRISTAIN     CENTER        ");
        add(welcomeLabel, "South");
        add(dONLabel, "Center");
        add(displayL, "North");
        setDefaultCloseOperation(3);
        setSize(1600, 1000);
        setVisible(true);
    }

        private String getTimeString()
        {
            LocalTime timeNow = LocalTime.now();
            DateTimeFormatter form = DateTimeFormatter.ofPattern("H:m:s");
            String timeLog = timeNow.format(form);
            return timeLog;
        }

class DelDialog extends JDialog
{
    private JPanel pan;
    private JPanel pan2;
    private JLabel delField;
    private JTextField delsfield;

    public DelDialog(JFrame delFrame)
    {
        super(delFrame, "  DELETE   ", true);
        setLayout(new BorderLayout());
        pan = new JPanel();
        pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());
        pan.setLayout(new FlowLayout());
        pan.setBorder(BorderFactory.createTitledBorder(""));
        JLabel delFirst = new JLabel("     ");
        pan.add(delFirst);
        delField = new JLabel(       "");
        pan.add(delField);
        JLabel delSecond = new JLabel("     ID Number");
        pan.add(delSecond);
        delsfield = new JTextField(15);
        pan.add(delsfield);
        JButton subm = new JButton("SUBMIT");
        subm.setIcon(new ImageIcon("C:\\church\\windows-error.png"));
        pan2.add(subm);
        JButton canc = new JButton("CANCEL");
        add(pan, "Center");
        add(pan2, "South");
        final String value = JOptionPane.showInputDialog("Enter Worker OR Member as choice to delete");
        subm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (value.equalsIgnoreCase("Member"))
                {
                    var vb = churchService.deleteMember(delsfield.getText());
                    if (vb>0) {
                        JOptionPane.showMessageDialog((Component) null, "OPERATION  SUCCESSFUL");
                    }
                    else {
                        JOptionPane.showMessageDialog((Component) null, "OPERATION NOT  SUCCESSFUL");
                    }
                    delsfield.setText(" ");
                }

                else if (value.equalsIgnoreCase("worker"))
                {
                    int workValue = churchService.deleteWorker(delsfield.getText());
                    if (workValue >0) {
                        JOptionPane.showMessageDialog((Component) null, "OPERATION  SUCCESSFUL");
                    }
                    else {
                        JOptionPane.showMessageDialog((Component) null, "OPERATION NOT SUCCESSFUL");
                    }
                    delsfield.setText(" ");
                }
                else if(value.equalsIgnoreCase(" ")|| value==null)
                {
                    JOptionPane.showMessageDialog((Component) null, "OPERATION NOT SUCCESSFUL ENTER A VALUE");
                }
            }
        });
        canc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        setSize(600, 150);
        setLocation(300, 350);
    }
}
class Display extends JDialog {

 //   private JTable resultTable;
    private JPanel panel;
    private JPanel panel1;
    private JPanel dis;
    private JButton submit;
    private JList<String> searchList;
    private JTextArea displayArea;

    private String[] searchText = new String[]{"MALE", "FEMALE", "SINGLE", "MARRIED","New Table List","Old Table List"};
    private String[] sqlText = new String[]{"SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE SEX = 'MALE'",
            "SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE SEX = 'FEMALE'",
            " SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE STATUS = 'SINGLE'",
            "SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE STATUS = 'MARRIED'",
            "selection",
            "SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER"
    };

    public Display(JFrame frame) {
        super(frame, "  Enter SQL select Statement   ");
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel1 = new JPanel();
        dis = new JPanel();
        searchList = new JList<String>(searchText);
        displayArea = new JTextArea(10, 20);
        searchList.setEnabled(true);
        searchList.setVisibleRowCount(6);
        searchList.setFixedCellHeight(20);
        searchList.setFixedCellWidth(100);
        searchList.setSelectionMode(0);
        displayArea.setEditable(true);
        dis.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel1.setLayout(new FlowLayout());
        panel1.setSize(600,400);
        panel.setLayout(new FlowLayout());
        submit = new JButton("VIEW");
        panel.add(submit);
        dis.add(new JScrollPane(displayArea));
        dis.add(new JScrollPane(searchList));
        add(dis, "East");
        add(panel, "South");
        searchList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                displayArea.setText(sqlText[searchList.getSelectedIndex()]);
            }
        });
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (searchList.getSelectedIndex()==0) {
                    churchService.findAllMemberBySex(searchList.getSelectedValue());

                } else if (searchList.getSelectedIndex()==1) {
                    churchService.findAllMemberBySex(searchList.getSelectedValue());}
                else if (searchList.getSelectedIndex()==2) {
                    churchService.marriedSelection(searchList.getSelectedValue());
                }
                else if (searchList.getSelectedIndex()==3) {
                    churchService.marriedSelection(  searchList.getSelectedValue());
                }
                else if(searchList.getSelectedIndex()==4)
                {
                    churchService.MemberSelection();
                }
                else if(searchList.getSelectedIndex()==5)
                {
                    churchService.OldMemberSelection();
                }
                resultTable = new JTable(churchService);
                add(new JScrollPane(resultTable), "Center");
                TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                resultTable.setRowSorter(sorter);
                setSize(1200, 700);
                setLocation(100, 10);

            }
        });
        //  setSize(900, 150);
        setSize(1000, 700);
        setLocation(100, 10);
        setVisible(true);
    }
}
class SundayActivityDialog extends JDialog {

    private JTextField minister;
    private JTextField topic;
    private JTextField male;
    private JTextField female;
    private JTextField children;
    private JTextField total;
    private JLabel ministerLabel;
    private JLabel topicLabel;
    private JLabel maleLabel;
    private JLabel femaleLabel;
    private JLabel childrenLabel;
    private JLabel totalLabel;
    private JTextField deptText;
    private JTextField dateText;
    private JLabel submitLabel;
    private JButton submit;
    private JButton submitDate;
    private JPanel panel;
    private JPanel panes;
    private JPanel newPanel;

    public SundayActivityDialog(final JFrame frame) {
        super(frame, " Enter Sunday Activity and Topic", null);
        setLayout(new BorderLayout());
        minister = new JTextField(20);
        topic = new JTextField(20);
        dateText = new JTextField(15);
        submitDate = new JButton("DATE");
        ministerLabel = new JLabel("        MINISTER NAME");
        topicLabel = new JLabel("       TOPIC");
        totalLabel = new JLabel("        TOTAL");
        childrenLabel = new JLabel("        TOTAL CHILDREN");
        femaleLabel = new JLabel("        FEMALE TOTAL");
        maleLabel = new JLabel("        MALE  TOTAL");
        male = new JTextField();
        female = new JTextField();
        total = new JTextField();
        children = new JTextField();
        maleLabel.setBorder(BorderFactory.createTitledBorder(""));
        femaleLabel.setBorder(BorderFactory.createTitledBorder(""));
        childrenLabel.setBorder(BorderFactory.createTitledBorder(""));
        totalLabel.setBorder(BorderFactory.createTitledBorder(""));
        ministerLabel.setBorder(BorderFactory.createTitledBorder(""));
        topicLabel.setBorder(BorderFactory.createTitledBorder(""));
        submitLabel = new JLabel("    ");
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(7, 2, 1, 1));
        panes = new JPanel(new FlowLayout());
        newPanel = new JPanel(new BorderLayout());
        newPanel.setBorder(BorderFactory.createTitledBorder(""));
        panes.setBorder(BorderFactory.createTitledBorder(""));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.add(topicLabel);
        panel.add(topic);
        panel.add(ministerLabel);
        panel.add(minister);
        panel.add(maleLabel);
        panel.add(male);
        panel.add(femaleLabel);
        panel.add(female);
        panel.add(totalLabel);
        panel.add(total);
        panel.add(childrenLabel);
        panel.add(children);
        panel.add(submitDate);
        panel.add(dateText);
        panes.add(submitLabel);
        panes.add(submit);
        newPanel.add(panel, "Center");
        newPanel.add(panes, "South");
        add(newPanel, "Center");
        dateText.setText((new DatePicker(frame)).getDefaultDate());
        submitDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                DatePicker pick = new DatePicker(frame);
                pick.displayDate();
                dateText.setText(pick.getPickedDate());
            }
        });

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex)
            {
                if (minister.getText() != null && topic.getText() != null) {
                    String newDate = dateText.getText();
                    int m = Integer.parseInt(male.getText().trim());
                    int n = Integer.parseInt(female.getText().trim());
                    int c = Integer.parseInt(children.getText().trim());
                    int t = Integer.parseInt(total.getText().trim());
                    churchService.addChurchAttendance(new ServiceAttendance(newDate, minister.getText(), t ,topic.getText(),n, m,  c));
                }
            }
        });

        dateText.setText((new DatePicker(frame)).getDefaultDate());
        topic.setEditable(true);
        minister.setEditable(true);
        topic.setEditable(true);
        male.setEditable(true);
        female.setEditable(true);
        children.setEditable(true);
        total.setEditable(true);
        topic.setText("");
        minister.setText("");
        topic.setText("");
        male.setText("");
        female.setText("");
        children.setText("");
        total.getText();
        setSize(680, 400);
        setLocation(550, 150);
        setVisible(true);
    }
  }
    class AllDialog extends JDialog {
      //  private JTable resultTable;
        private JButton print;
        private Object[][] objects;
        private String [] columnObject;
        private JLabel image = new JLabel();
        private ImageObjectResize[] imageObjectResize;
        private MemberList memberList;
        public AllDialog(JFrame Noframe) {
            super(Noframe, " ALL MEMBER ", null);
            setLayout(new BorderLayout());
            JPanel pane = new JPanel();
            print = new JButton("PRINT");
            JButton search = new JButton("VIEW");
            pane.add(search);
            pane.add(print);
            add(pane, "South");
            search.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    List<Member> allMember = churchService.findAllMemberWithoutSerial();
                    int size = allMember.size();
                    objects = new Object[size][13];
                    columnObject = new String[]{"Serial_Number", "member_photo","id", "sex", "last_name", "first_name", "address", "date_born", "phone", "status",
                            "attendance", "register_date", "resent"};
                    int count = 1;
                    for(int i =0; i<size; i++)
                    {
                        JLabel image = new JLabel();
                        if(allMember.get(i).getMemberPhoto().equalsIgnoreCase("")||allMember.get(i).getMemberPhoto()==null) {
                            image.setIcon(new ImageIcon("C:\\church\\rccgsmall.jpg"));
                        }
                        //       image.setIcon(new ImageIcon(ImageIO.read()));
                        //   File file = new File(allMember.get(i).getMemberPhoto());
                        image.setIcon(new ImageIcon(allMember.get(i).getMemberPhoto()));
                        objects[i][0] = count++;
                        objects[i][1] = image;
                        objects[i][2] = allMember.get(i).getId();
                        objects[i][3] = allMember.get(i).getSex();
                        objects[i][4] = allMember.get(i).getFirstName();
                        objects[i][5] = allMember.get(i).getLastName();
                        objects[i][6] = allMember.get(i).getAddress();
                        objects[i][7] = allMember.get(i).getDateBorn();
                        objects[i][8] = allMember.get(i).getPhone();
                        objects[i][9] = allMember.get(i).getStatus();
                        objects[i][10] = allMember.get(i).getAttendance();
                        objects[i][11] = allMember.get(i).getRegisterDate();
                        objects[i][12] = allMember.get(i).getResent();
                    }
                    //    memberList = new MemberList(allMember);
                    //    memberList.setSizeMethod(size);
                    //    objects = memberList.getResultingObject();
                    //memberList.getResultingObject()
                    resultTable = new JTable(objects,columnObject);
                    imageRendererClass.setSetTable(resultTable);
                    imageRendererClass.setString("member_photo");
                    add(new JScrollPane(resultTable), "Center");
                    resultTable.setRowHeight(120);
                    resultTable.getColumn("member_photo").setCellRenderer( imageRendererClass);
                    resultTable.setCellSelectionEnabled(true);
                    resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
                    setSize(1200, 550);
                    setLocation(100, 100);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true,
                                (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(250, 150);
            setLocation(550, 300);
        }
    }
    class UpdateImageDialog extends JDialog {

        private JPanel pan;
        private JPanel pan2;
        private JButton nextlField;
        private JButton delsfield;

        public UpdateImageDialog(JFrame delFrame)
        {
            super(delFrame, "  UPDATE IMAGE SIZE AND LOCATION   ", true);
            setLayout(new BorderLayout());
            pan = new JPanel();
            pan.setLayout(new FlowLayout());
            pan.setBorder(BorderFactory.createTitledBorder(""));
            delsfield = new JButton(" NEXT  ");
            //   nextlField = new JButton(" NEXT  ");
            delsfield.setContentAreaFilled(true);
            pan.add(delsfield);
            JButton canc = new JButton("CANCEL");
            add(pan, "Center");
            add(canc, "South");
            //add(pan2, "South");
            List<Member> serial = churchService.findAllMemberWithoutSerial();
            Iterator<Member> memberIterator = serial.iterator();
            delsfield.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String fileType ="";
                    if (memberIterator.hasNext()){
                        Member ImageMember = memberIterator.next();
                        if (ImageMember.getMemberPhoto().endsWith("jpg")) {
                            fileType = "jpg";
                        } else if (ImageMember.getMemberPhoto().endsWith("jif")) {
                            fileType = "jif";
                        } else if (ImageMember.getMemberPhoto().endsWith("jpeg")) {
                            fileType = "jpeg";
                        } else if (ImageMember.getMemberPhoto().endsWith("png")) {
                            fileType = "png";
                        }
                        BufferedImage bufferedImage = null;
                        BufferedImage saveBufferedImage = null;
                        try {
                            bufferedImage = ImageIO.read(new File(ImageMember.getMemberPhoto()));

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        String fileName = "";

                        fileName = ImageMember.getMemberPhoto();
                        String[] fileNameArray = fileName.split("\\\\");
                        int lens = fileNameArray.length;

                        //    Image images = bufferedImage.getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                        File saveFile = new File("C:\\church\\" + fileNameArray[lens - 1]);
                        try {
                            ImageIO.write(ImageConversion.convertToBufferedImage(bufferedImage), fileType, saveFile);

                            ImageMember.setMemberPhoto("C:\\church\\" + fileNameArray[lens - 1]);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
/// new addition for picture update
                        int v = churchService.updateMemberInfo(ImageMember.getId(), ImageMember.getAddress(), ImageMember.getDateBorn(),
                                ImageMember.getFirstName(), ImageMember.getLastName(), ImageMember.getStatus(),
                                ImageMember.getPhone(), ImageMember.getSex(),
                                ImageMember.getMemberPhoto(),
                                ImageMember.getId());
                        if (v > 0) {
                            JOptionPane.showMessageDialog(null, "Successful");
                        } else{
                            JOptionPane.showMessageDialog(null, " Not Successful");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "All image formated to Size 150 X 140");
                    }
                }
            });
            canc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();

                }
            });
            setSize(400, 100);
            setLocation(300, 350);
        }
    }

    @Transactional
    class LogDialog extends JDialog {

        private Timer animationTimer;
        private Member displayNames;
     //   private JLabel welcomeLabel,display1,display2,display3,dONLabel,label;

        private JTextField lofield;

        private String name;

        public LogDialog(JFrame Lframe) {
            super(Lframe, "         WELCOME TO RCCG NEW CONVENENT MODEL PARISH        LOG IN ", true);
            //     super(Lframe, "         WELCOME TO LEADING EDGE CHRISTAIN CENTER        LOG IN ", true);

            setLayout(new BorderLayout());
            setBackground(Color.blue);
            JButton log = new JButton("Login");
            JPanel pane = new JPanel();
            JPanel addPanel = new JPanel();
            JPanel showPanel = new JPanel(new FlowLayout());
            addPanel.setLayout(new BorderLayout());
            pane.setMaximumSize(new Dimension(5, 5));
            display1 = new JLabel("");
            display2 = new JLabel("");
            display3 = new JLabel("");
        //    displayL = new JLabel( "  ");
            pane.setLayout(new BorderLayout());
            log.setIcon(new ImageIcon("C:\\church\\rccgsmall.jpg"));
            //log.setIcon(new javax.swing.ImageIcon("C:\\church\\LECCnew.png"));
            showPanel.add(display1);
            showPanel.add(display2);
            showPanel.add(display3);
            lofield = new JTextField(5);
            lofield.setFont(new Font("Serif", 1, 200));
            pane.add(lofield, "Center");
            pane.add(new JLabel("   "), "East");
            pane.add(new JLabel("   "), "West");
            pane.add(new JLabel("   "), "North");
            pane.add(new JLabel(""), "South");
            addPanel.add(log, "Center");
            addPanel.add(showPanel, "South");
            addPanel.add(new JLabel("  "), "North");
            addPanel.add(new JLabel("                     "), "East");
            addPanel.add(new JLabel("                      "), "West");
            add(addPanel, "South");
            add(pane, "Center");
            add(new JLabel("  "), "North");
            add(new JLabel("  "), "East");
            add(new JLabel("  "), "West");
            log.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
                    String dateString = dt.format(new Date());
                    String loginTimeWorker = getTimeString();
                    name = lofield.getText().trim();// name here is the login id of the user.
                    try {

                        if (churchService.displayName(name)) {

                            List<Member> checkMember = new ArrayList<>();
                            checkMember = churchService.findByIdValue(name);
                            if (churchService.getCurrentDate(name).equalsIgnoreCase(dateString)) {
                                JOptionPane.showMessageDialog(null, "You have Logged In Before");
                                lofield.setText("");
                            }
                            else {
                                churchService.updateCurrentDateInfo(dateString, name);
                                churchService.updateAttendanceOfMembers(name);
                                if (!churchService.getCurrentTime(name))
                                {
                                   int added= churchService.addWorkerAttendance(new WorkersLogin(name, loginTimeWorker,"NONWORKER" , dateString));
                                }
                                else{
                                    String member_status = churchService.getAttendanceIdMember(name);
                                    churchService.updateWorkerAttendance(loginTimeWorker,name);
                                }
                                churchService.doLoginTmeUpdate(name, loginTimeWorker, dateString);
                                churchService.updateAttendance(name);
                                churchService.updateInchurchValue(name);
                                lofield.setText("");
                                if (animationTimer==null) {
                                    displayNames = checkMember.get(0);
                                    if (displayNames != null) {
                                        display1.setText(displayNames.getFirstName());
                                        display2.setText(displayNames.getLastName());
                                    }
                                    display3.setText(" ");
                                    displayL.setFont(new Font("Serif", 1, 36));
                                    // displayL.setText("WELCOME TO LEADING EDGE CHRISTAIN CENTER");
                                    displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                    animationTimer = new Timer(1500, new TimerClass());
                                    animationTimer.start();
                                } else {
                                    if (animationTimer.isRunning()) {
                                    //    animationTimer.restart();
                                        displayNames = checkMember.get(0);
                                        if (displayNames != null) {
                                            display1.setText(displayNames.getFirstName());
                                            display2.setText(displayNames.getLastName());
                                        }
                                        display3.setText(" ");
                                        displayL.setFont(new Font("Serif", 1, 36));
                                        //displayL.setText("WELCOME TO LEADING EDGE CHRISTAIN CENTER");
                                        displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                        animationTimer.restart();
                                    }
                                }
                            }
                        }
// search the data base if the id of the user exist
                        else {
                            JOptionPane.showMessageDialog((Component) null, "User Not Registered   : Please Register");
                            lofield.setText("");
                        }
                    } catch (IllegalStateException sql) {
                        sql.toString();
                    }


                }
            });
            lofield.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (10 == e.getKeyCode()) {
                        DateFormat dt = new SimpleDateFormat("dd-MM-YYYY");
                        String dateString = dt.format(new Date());
                        String loginTimeWorker = getTimeString();
                        name = lofield.getText();
                        try {
                            if (churchService.displayName(name)) {

                                List<Member> checkMember = new ArrayList<>();
                                checkMember = churchService.findByIdValue(name);
                                if (churchService.getCurrentDate(name).equalsIgnoreCase(dateString)) {
                                    JOptionPane.showMessageDialog(null, "You have Logged In Before");
                                    lofield.setText("");
                                } else {
                                    churchService.updateCurrentDateInfo(dateString, name);
                                    churchService.updateAttendanceOfMembers(name);
                                    if (!churchService.getCurrentTime(name))
                                    {
                                        int added= churchService.addWorkerAttendance(new WorkersLogin(name, loginTimeWorker,"NONWORKER" , dateString));
                                    }
                                    else{
                                        String member_status = churchService.getAttendanceIdMember(name);
                                        churchService.updateWorkerAttendance(loginTimeWorker,name);
                                    }
                                    churchService.doLoginTmeUpdate(name, loginTimeWorker, dateString);
                                    churchService.updateAttendance(name);
                                    lofield.setText("");
                                    if (animationTimer == null) {
                                        //  try {
                                        displayNames = checkMember.get(0);
                                        if (displayNames != null) {
                                            display1.setText(displayNames.getFirstName());
                                            display2.setText(displayNames.getLastName());
                                        }
                                        display3.setText(" ");
                                        displayL.setFont(new Font("Serif", 1, 36));
                                        // displayL.setText("WELCOME TO LEADING EDGE CHRISTAIN CENTER");
                                        displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                        animationTimer = new Timer(1500, new TimerClass());
                                        animationTimer.start();

                                    } else {
                                        if (animationTimer.isRunning()) {
                                      //      animationTimer.restart();
                                            displayNames = checkMember.get(0);
                                            if (displayNames != null) {
                                                display1.setText(displayNames.getFirstName());
                                                display2.setText(displayNames.getLastName());

                                            }
                                            display3.setText(" ");
                                            displayL.setFont(new Font("Serif", 1, 36));

                                            // displayL.setText("WELCOME TO LEADING EDGE CHRISTAIN CENTER");
                                            displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                            animationTimer.restart();
                                        }
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "User Not Registered  : Please Register ");
                                lofield.setText("");
                            }
                        } catch (IllegalStateException sql) {
                            sql.toString();
                        }

                    }

                }
            });
            setSize(850, 350);
            setLocation(300, 300);
        }
        private  class TimerClass  implements ActionListener {

            //  private JLabel welcomeLabel,display1,display2,display3,dONLabel,displayL,label;
            public TimerClass() {
                /*display1 = new JLabel("");
                display2= new JLabel("");
                display3= new JLabel("");*/
            }
            public void actionPerformed(ActionEvent action) {
                display1.setText(" ");
                display2.setText(" ");
                display3.setText(" ");
                displayL.setText(" ");

            }
        }
    }
    /*private String getTimeString()
    {
        LocalTime timeNow = LocalTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("H:m:s");
        String timeLog = timeNow.format(form);
        return timeLog;
    }*/



    class WAttendanceDialog extends JDialog {

        private JButton print;

        private JPanel onePanel;
        private JTextField text;
        private JLabel label;
        private String []columnlen;
        private Object[][] objects;

        public WAttendanceDialog(JFrame wFrame) {
            super(wFrame, "    VIEW ALL ATTENDANCE TIME FOR THE WEEK           ", null);
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JPanel panels = new JPanel();
            print = new JButton("PRINT");
            onePanel = new JPanel();
            onePanel.setLayout(new BorderLayout());
            panel.setLayout(new GridLayout(1, 2, 1, 1));
            JButton enter = new JButton("VIEW ATTENDANCE TIME");
            panel.setBorder(BorderFactory.createTitledBorder(""));
            text = new JTextField(10);
            panel.add(enter);
            panel.add(print);
            onePanel.add(panel, "South");
            add(onePanel, "South");
            enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //   churchService.getAllWorkers();
                    List<AllWorkersLogin> memberLoginTime = churchService.getAllMembersAttendanceTime();
                    var size = memberLoginTime.size();
                    objects = new Object[size][7];
                    columnlen = new String[]{"Serial Number","last_name","first_name","address" ,"phone","attendance ","login_Time"};
                    var numbs = 1;
                    for (int i = 0; i < size; i++) {
                        objects[i][0] =numbs++;
                        objects[i][1] = memberLoginTime.get(i).last_name();
                        objects[i][2] = memberLoginTime.get(i).first_name();
                        objects[i][3] = memberLoginTime.get(i).address();
                        objects[i][4] = memberLoginTime.get(i).phone();
                        objects[i][5] = memberLoginTime.get(i).attendance();
                        objects[i][6] = memberLoginTime.get(i).login_Time();
                    }

                    resultTable = new JTable(objects,columnlen);
                    add(new JScrollPane(resultTable), "Center");
                 //   TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                 //   resultTable.setRowSorter(sorter);
                    setSize(1100, 600);
                    setLocation(50, 80);

                }
            });

            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }

                }
            });
            setSize(500, 100);
            setLocation(520, 410);
        }
    }
    class WAttendanceTimeDialog extends JDialog {

        private JButton print;

        private JPanel onePanel;
        private JTextField text;
        private JLabel label;
        private String[] columnSize;
        private Object[][] objects;

        public WAttendanceTimeDialog(JFrame wFrame) {
            super(wFrame, "    VIEW ALL WORKERS ATTENDANCE TIME FOR THE WEEK           ", null);
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JPanel panels = new JPanel();
            print = new JButton("PRINT");
            onePanel = new JPanel();
            onePanel.setLayout(new BorderLayout());
            panel.setLayout(new GridLayout(1, 2, 1, 1));
            JButton enter = new JButton("VIEW WORKERS ATTENDANCE TIME");
            panel.setBorder(BorderFactory.createTitledBorder(""));
            text = new JTextField(10);
            panel.add(enter);
            panel.add(print);
            onePanel.add(panel, "South");
            add(onePanel, "South");
            enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    List<AllWorkersLogin> workersLogins=churchService.getAllWorkersLoginTime();
                    columnSize = new String[]{"Serial Number", "last_name"," first_name","address" ,"phone" ,"attendance","login_Time"};
                    var size = workersLogins.size();
                    objects = new Object[size][7];
                    var numbs = 1;
                    //   churchService.getAllWorkers();
                    for (int i = 0; i < size; i++) {
                        objects[i][0] = numbs++;
                        objects[i][1] = workersLogins.get(i).last_name();
                        objects[i][2] = workersLogins.get(i).first_name();
                        objects[i][3] = workersLogins.get(i).address();
                        objects[i][4] = workersLogins.get(i).phone();
                        objects[i][5] = workersLogins.get(i).attendance();
                        objects[i][6] = workersLogins.get(i).login_Time();

                    }


                    resultTable = new JTable(objects,columnSize);
                    add(new JScrollPane(resultTable), "Center");
                    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    resultTable.setRowSorter(sorter);
                    setSize(1200, 600);
                    setLocation(50, 80);

                }
            });

            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }

                }
            });
            setSize(500, 100);
            setLocation(520, 410);
        }
    }

    class SearchDialog extends JDialog
    {

        private JPanel panel, display, total;
        private JLabel loginLabel;
        private JButton submit;
        private JTextField firstField;
        private JTable resultTable;
        private JTextField enterId;
        private JLabel sunN;
        private JLabel surField;
        private Object[][] searchObjects;
        private String [] columnSearch;
        private JLabel image = new JLabel();
        public SearchDialog(JFrame parent) {
            super(parent, "                 SEARCH BY NAME OR ID NUMBER              ", null);
            setLayout(new BorderLayout());
            JLabel name1Label = new JLabel();
            panel = new JPanel();
            display = new JPanel();
            total = new JPanel();
            enterId = new JTextField(6);
            total.setLayout(new BorderLayout());
            display.setLayout(new GridLayout(3, 2, 3, 1));
            display.setBorder(BorderFactory.createTitledBorder(""));
            panel.setLayout(new FlowLayout());
            name1Label.setText("     FIRSTNAME");
            surField = new JLabel();
            sunN = new JLabel("      ");
            firstField = new JTextField();
            display.add(sunN);
            display.add(surField);
            display.add(name1Label);
            display.add(firstField);
            loginLabel = new JLabel();
            loginLabel.setText("     ID NUMBER");
            display.add(loginLabel);
            enterId.setText(" ");
            enterId.setEditable(true);
            display.add(enterId);
            submit = new JButton();
            submit.setText("SEARCH.....");
            panel.add(submit);
            total.add(display, "Center");
            total.add(panel, "South");
            add(total, "South");
            submit.addActionListener(new ActionListener() {
                Member idMember ;
                public void actionPerformed(ActionEvent e) {
                    List<Member> searchMember = new ArrayList<>();

                    if (enterId.getText().equalsIgnoreCase("") &&
                            (firstField.getText() != null && !firstField.getText().equalsIgnoreCase("")) ) {
                        searchMember = churchService.findByFirstName(firstField.getText());


                    } else if ((firstField.getText()).isEmpty() && !(enterId.getText()).isEmpty()) {

                        idMember = churchService.displayMemberMethod(enterId.getText());


                    }
                    columnSearch = new String[]{ "member_photo","id", "sex", "last_name", "first_name", "address", "date_born", "phone", "status",
                            "attendance", "register_date", "resent"};
                    //    columnSearch = new String[]{ "id", "sex", "last_name", "first_name", "address", "date_born", "phone", "status",
                    //            "attendance", "register_date", "resent"};
                    searchObjects = new Object[1][12];
                    BufferedImage bufferedImage = null;
                    try {
                        //   bufferedImage = ImageIO.read(new File(idMember.getMemberPhoto()));
                        // Image images = bufferedImage.getScaledInstance(150,120,Image.SCALE_SMOOTH);
                        image.setIcon(new ImageIcon(idMember.getMemberPhoto()));
                        //   searchObjects[0][0] = idMember.getSerialNumber();
                        searchObjects[0][0] = image;
                        searchObjects[0][1] = idMember.getId();
                        searchObjects[0][2] = idMember.getSex();
                        searchObjects[0][3] = idMember.getLastName();
                        searchObjects[0][4] = idMember.getFirstName();
                        searchObjects[0][5] = idMember.getAddress();
                        searchObjects[0][6] = idMember.getDateBorn();
                        searchObjects[0][7] = idMember.getPhone();
                        searchObjects[0][8] = idMember.getStatus();
                        searchObjects[0][9] = idMember.getAttendance();
                        searchObjects[0][10] = idMember.getRegisterDate();
                        searchObjects[0][11] = idMember.getResent();
                        resultTable = new JTable(searchObjects,columnSearch);
                        add(new JScrollPane(resultTable), "Center");
                        imageRendererClass.setSetTable(resultTable);
                        imageRendererClass.setString("member_photo");
                        resultTable.getColumn("member_photo").setCellRenderer(imageRendererClass);

                    } catch (IllegalStateException sql) {
                        sql.printStackTrace();
                    }

                    setSize(1600, 550);
                    setLocation(10, 100);

                }
            });
            setSize(530, 170);
            setLocation(600, 350);
        }
    }

    class ResetTableDialog extends JDialog {
        private JPanel pan;
        private JPanel pan2;
        private JLabel delField;
        private JButton delsfield;


        public ResetTableDialog(JFrame delFrame)
        {
            super(delFrame, "  RESET TABLE   ", true);
            setLayout(new BorderLayout());
            int number =0;
            pan = new JPanel();
            //  pan2 = new JPanel();
            //   pan2.setLayout(new FlowLayout());
            pan.setLayout(new FlowLayout());
            pan.setBorder(BorderFactory.createTitledBorder(""));
            JLabel delFirst = new JLabel("     ");
            pan.add(delFirst);
            delField = new JLabel(       "");
            pan.add(delField);
            JLabel delSecond = new JLabel();
            pan.add(delSecond);
            delsfield = new JButton("  INSERT INTO NEW TABLE  ");
            delsfield.setContentAreaFilled(true);
            pan.add(delsfield);
            JButton canc = new JButton("CANCEL");
            add(pan, "Center");

            delsfield.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    java.util.List<Member> mem = churchService.findAllMemberWithoutSerial();
                    List<SerialMember> serial = churchService.findAllMember();
                    Set<SerialMember> memberSet = new HashSet<>();
                    SerialMember sM = new SerialMember();
                    Iterator<Member> memberIterator = mem.iterator();
                    int sizeMember = mem.size();
                    int number = 1;

                    int serialSize = serial.size();
                    if (!(serialSize== sizeMember)|| serial.isEmpty()) {
                        if (!serial.isEmpty())
                        {

                        }
                        while (memberIterator.hasNext()) {

                            Member addMember = memberIterator.next();
                            churchService.newAddMember(addMember);
                            delSecond.setText("Total Old Table :" + sizeMember + ", Total Inserted  :" + number);
                            number++;

                        }
                    }
                    else {
                        delsfield.setEnabled(false);
                    }
                    if (serial.isEmpty() || serialSize < sizeMember)
                    {
                        delsfield.setEnabled(true);
                    }


                }
            });
            canc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();

                }
            });
            setSize(400, 100);
            setLocation(300, 350);
        }
    }

    class NonDialog extends JDialog {
        private JButton print;
        private String []columnSearch;
        private Object[][] nonObject;
        public NonDialog(JFrame Noframe) {
            super(Noframe, " NoN Workers - MEMBER ", null);
            setLayout(new BorderLayout());
            JPanel pane = new JPanel();
            print = new JButton("PRINT");
            JButton search = new JButton("VIEW");
            pane.add(search);
            pane.add(print);
            add(pane, "South");
            search.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    List<NonMember> mapList= churchService.getAllNonWorker();
                    nonObject = new Object[mapList.size()][11];
                    columnSearch = new String[]{"Serial Num","id","sex","last_name","first_name","status","address","phone," +
                            "  attendance" ,"register_date","resent"};
                    var nums = 1;
                    for (int i = 0; i < mapList.size(); i++) {
                        nonObject[i][0]=nums++;
                        nonObject[i][1] = mapList.get(i).id();
                        nonObject[i][2] = mapList.get(i).sex();
                        nonObject[i][3] = mapList.get(i).last_name();
                        nonObject[i][4] = mapList.get(i).first_name();
                            nonObject[i][5] = mapList.get(i).status();
                            nonObject[i][6] = mapList.get(i).address();
                            nonObject[i][7] = mapList.get(i).phone();
                            nonObject[i][8] = mapList.get(i).attendance();
                            nonObject[i][9] = mapList.get(i).register_date();
                            nonObject[i][10] = mapList.get(i).resent();
                    }
                    resultTable = new JTable(nonObject,columnSearch);
                    add(new JScrollPane(resultTable), "Center");
          //          TableRowSorter<TableModel> sorter = new TableRowSorter(resultTable);
            //        resultTable.setRowSorter(sorter);

                    setSize(1200, 550);
                    setLocation(100, 100);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true,
                                (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(200, 150);
            setLocation(550, 300);
        }
    }

    @Transactional
    class MemberDialog extends JDialog
    {
        private JLabel workerFlabel;
        private JLabel workerFirstLabel;
        private JLabel workerPlabel;
        private JLabel workerAlabel;
        private JLabel workerIdlabel;
        private JLabel workersexlabel;

        private JLabel workerStatusLabel;
        private Member member;
        private JPanel pan;
        private final JLabel workerStreetLabel;
        private final JButton workerEmail;
        private final JTextField streetField;
        private final JTextField emailField;
        private JComboBox workerStatus;
        private JComboBox workerSex;
        private String[] sex = new String[]{"CHOOSE", "MALE", "FEMALE"};
        private String[] status = new String[]{"CHOOSE", "SINGLE", "MARRIED"};
        private String mf;
        private String sm;
        private JButton workerDlabel;
        private int present = 1;
        private int absent = 0;
        private String start = "Yes";
        private NonWorker nonWorker;
        private JTextField  workerIdfield,workerAfield,workerPfield, workerFirstfield,workerdateofbirthfield;
        private JTextField workerFfield;
        private DateClass dateClass;
        private WorkersLogin wAttendance;
        public MemberDialog(final JFrame memFrame)
        {
            super(memFrame, "    ADD MEMBERSHIP          ", true);
            setLayout(new BorderLayout());
            pan = new JPanel();
            pan.setBorder(BorderFactory.createTitledBorder(""));
            pan.setLayout(new GridLayout(10, 2, 5, 5));
            workerFlabel = new JLabel();
            workerFlabel.setText("          LASTNAME");
            workerFfield = new JTextField(15);
            workerFirstLabel = new JLabel("          FIRSTNAME");
            workerFirstfield = new JTextField(20);
            workerDlabel = new JButton();
            workerDlabel.setText("DAY OF BIRTH");
            workerdateofbirthfield = new JTextField(15);
            workerdateofbirthfield.setEditable(true);
            workerPlabel = new JLabel();
            workerPlabel.setText("          PHONE");
            workerdateofbirthfield.setText((new DatePicker(memFrame)).getDefaultDate());
            workerPfield = new JTextField(15);
            workerAlabel = new JLabel();
            workerAlabel.setText("          ADDRESS");
            workerAfield = new JTextField(20);
            workerIdlabel = new JLabel();
            workerIdlabel.setText("          ID");
            workerIdfield = new JTextField(5);
            workerStatusLabel = new JLabel("           MARITAL STATUS");
            workerStatus = new JComboBox(status);
            workersexlabel = new JLabel();
            workersexlabel.setText("          SEX");
            workerSex = new JComboBox(sex);
            workerStreetLabel = new JLabel("           ");
            streetField = new JTextField(15);
            streetField.enable(false);
            streetField.enableInputMethods(false);
            workerEmail = new JButton("          MEMBER PHOTO");
            emailField = new JTextField(20);
            workerPlabel.setBorder(BorderFactory.createTitledBorder(""));
            workerIdlabel.setBorder(BorderFactory.createTitledBorder(""));
            workerFirstLabel.setBorder(BorderFactory.createTitledBorder(""));
            workerAlabel.setBorder(BorderFactory.createTitledBorder(""));
            workerFlabel.setBorder(BorderFactory.createTitledBorder(""));
            workersexlabel.setBorder(BorderFactory.createTitledBorder(""));
            workerEmail.setBorder(BorderFactory.createTitledBorder(""));
            workerStatusLabel.setBorder(BorderFactory.createTitledBorder(""));
            JButton sub = new JButton("SUBMIT");
            sub.setIcon(new javax.swing.ImageIcon("C:\\church\\Save16.gif"));
            //    sub.setIcon(new ImageIcon("C:\\church\\Save16.gif"));
            JButton cant = new JButton("CANCEL");
            pan.add(workerFlabel);
            pan.add(workerFfield);
            pan.add(workerFirstLabel);
            pan.add(workerFirstfield);
            pan.add(workerDlabel);
            pan.add(workerdateofbirthfield);
            pan.add(workerPlabel);
            pan.add(workerPfield);
            pan.add(workerAlabel);
            pan.add(workerAfield);
            pan.add(workerIdlabel);
            pan.add(workerIdfield);
            pan.add(workerStatusLabel);
            pan.add(workerStatus);
            pan.add(workersexlabel);
            pan.add(workerSex);
            pan.add(workerEmail);
            pan.add(emailField);
            pan.add(sub);
            pan.add(cant);
            workerIdfield.setText("");
            workerIdfield.setEditable(true);
            add(pan, "Center");

            workerEmail.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String fileType = "";
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
                    int result = jFileChooser.showOpenDialog(null);
                    File file = jFileChooser.getSelectedFile();

                    if((Objects.isNull(file)) || file.getName().equals(""))
                    {
                        emailField.setText("C:\\church\\rccgsmall1.jpg");
                    }
                    try {
                        emailField.setText(file.getAbsolutePath());

                    }
                    // set a default file name
                    catch (NullPointerException nullPointerException)
                    {
                        emailField.setText("C:\\church\\rccgsmall1.jpg");
                    }

                    /*
                     * This block of code gets the file type from the string that was passed to it.
                     * */

                    if (emailField.getText().endsWith("jpg")) {
                        fileType = "jpg";
                    }
                    else if (emailField.getText().endsWith("jif")) {
                        fileType = "jif";
                    }
                    else if (emailField.getText().endsWith("jpeg")) {
                        fileType = "jpeg";
                    }
                    else if (emailField.getText().endsWith("png")) {
                        fileType = "png";
                    }

                    //  BufferedImage bufferedImage = null;
                    try {
                        BufferedImage bufferedImage = ImageIO.read(new File(emailField.getText()));
                        String fi = emailField.getText();
                        String[] fileNemeString = fi.split("\\\\");
                        int lenst = fileNemeString.length;
// the image is sized and save to file. the file location is provided.
                        //    Image images = bufferedImage.getScaledInstance(150, 120, Image.SCALE_FAST);
                        File saveFile = new File("C:\\church\\" + fileNemeString[lenst-1]);

                        ImageIO.write(ImageConversion.convertToBufferedImage(bufferedImage),fileType,saveFile);
                        emailField.setText("C:\\church\\" + fileNemeString[lenst-1]);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
            workerDlabel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DatePicker pickDate = new DatePicker(memFrame);
                    pickDate.displayDate();
                    workerdateofbirthfield.setText(pickDate.getPickedDate());
                }
            });
            workerStatus.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == 1) {
                        sm = status[workerStatus.getSelectedIndex()];// for the status field the variable  sm is used
                    }
                }
            });
            workerSex.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == 1) {
                        mf = sex[workerSex.getSelectedIndex()];// for the sex field. the variable mf is used
                    }
                }
            });
            sub.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String formatedDate = workerdateofbirthfield.getText().substring(0, 5);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String loginDates = dateFormat.format(new Date());
                    /*member = new Member(workerIdfield.getText(), mf, workerFfield.getText(), workerFirstfield.getText(),
                            workerAfield.getText(),formatedDate,workerPfield.getText(),   sm,
                            1, loginDates, loginDates, emailField.getText());
                    */
                    member = new Member(workerIdfield.getText().toUpperCase(), mf, workerFfield.getText(), workerFirstfield.getText(),
                            workerAfield.getText(),formatedDate,workerPfield.getText(),   sm,
                            1, loginDates, loginDates, emailField.getText());
                    String stringID = workerIdfield.getText().toUpperCase();
                    if (!churchService.displayName(stringID)) {
                        nonWorker = new NonWorker(stringID, loginDates);
                        var x = churchService.addMemberInfo(member);
                        var x1 =churchService.newAddMember(member);

                        if(x>0 && x1>0)
                        {
                            churchService.addNonWorkers(nonWorker);
                            dateClass = new DateClass(stringID,loginDates, "PRESENT");
                            churchService.addDateClass( dateClass);
                            LocalTime timeNow = LocalTime.now();
                            DateTimeFormatter form = DateTimeFormatter.ofPattern("H:m:s");
                            String timeLog = timeNow.format(form);
                            wAttendance = new WorkersLogin(stringID,timeLog,"NONWORKER",loginDates);
                            churchService.addWorkerAttendance(wAttendance);
                            JOptionPane.showMessageDialog(null, "Registration is Successful");

                        }

                    } else {
                        JOptionPane.showMessageDialog((Component) null, "YOU HAVE BEEN REGISTERED BEFORE");
                    }

                    workerIdfield.setText("");
                    workerFfield.setText("");
                    workerAfield.setText("");
                    workerPfield.setText("");
                    workerdateofbirthfield.setText((new DatePicker(memFrame)).getDefaultDate());
                    workerStatus.setEnabled(true);
                    workerSex.setEnabled(true);
                    workerFirstfield.setText("");
                    streetField.setText("");
                    emailField.setText("");
                    workerSex.setSelectedIndex(0);
                    workerStatus.setSelectedIndex(0);
                    workerFfield.setEditable(true);
                    workerAfield.setEditable(true);
                    workerPfield.setEditable(true);
                    workerFirstfield.setEditable(true);

                }
            });
            cant.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    dispose();
                }
            });
            workerIdfield.setText("NC");
            workerFfield.setText("");
            workerAfield.setText("");
            workerPfield.setText("");
            workerdateofbirthfield.setText((new DatePicker(memFrame)).getDefaultDate());
            workerStatus.setEnabled(true);
            workerSex.setEnabled(true);
            workerFirstfield.setText("");
            streetField.setText("");
            emailField.setText("");
            workerFfield.setEditable(true);
            workerAfield.setEditable(true);
            workerPfield.setEditable(true);
            workerFirstfield.setEditable(true);
            emailField.setEditable(true);
            setSize(400, 500);
            setLocation(580, 220);
        }
    }

    class ViewLoginTimeDialog extends JDialog {


        private JButton print;

        private JPanel onePanel;
        private JTextField text;
        private JLabel label;
        private String [] columnSize;
        private Object[][] objectLogin;

        public ViewLoginTimeDialog(JFrame wFrame) {
            super(wFrame, "      Login In Time           ", null);
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JPanel panels = new JPanel();
            print = new JButton("PRINT");
            onePanel = new JPanel();
            onePanel.setLayout(new BorderLayout());
            panel.setLayout(new GridLayout(1, 2, 1, 1));
            JButton enter = new JButton("VIEW Login Time ");
            panel.setBorder(BorderFactory.createTitledBorder(""));
            text = new JTextField(10);
            panel.add(enter);
            panel.add(print);
            onePanel.add(panel, "South");
            add(onePanel, "South");

            enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                   List<AllLoginTime> loginTime = churchService.getAllLoginTime();
                   var size =loginTime.size();
                    objectLogin = new Object[size][5];
                    columnSize = new String[]{"Serial Number","last_name","first_name", "login_Time","login_Date"};
                    var nums = 1;
                    //    churchService.getAllWorkersAttendanceTime();
                    for (int i = 0; i < size; i++) {
                        objectLogin[i][0] = nums++;
                        objectLogin[i][1] = loginTime.get(i).last_name();
                        objectLogin[i][2] = loginTime.get(i).first_name();
                        objectLogin[i][3] = loginTime.get(i).login_Time();
                        objectLogin[i][4] = loginTime.get(i).login_Date();
                    }
                    resultTable = new JTable(objectLogin,columnSize);
                    add(new JScrollPane(resultTable), "Center");
                 //   TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                 //   resultTable.setRowSorter(sorter);
                    //    churchService.getAllWorkers();
                    setSize(1200, 600);
                    setLocation(50, 80);

                }
            });

            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }

                }
            });
            setSize(500, 100);
            setLocation(520, 410);
        }
    }

    class ViewMaintenanceDialog extends JDialog {

        private JPanel viewPanel;
        private JButton submit;

        private JButton print;

        public ViewMaintenanceDialog(JFrame frame) {
            super(frame, "VIEW  MAINTENANCE ACTIVITY", null);
            setLayout(new BorderLayout());
            submit = new JButton("VIEW MAINTENANCE");
            print = new JButton("PRINT");
            viewPanel = new JPanel();
            viewPanel.setLayout(new FlowLayout());
            viewPanel.add(submit);
            add(viewPanel, "South");
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {

                    churchService.getAllMaintenanceInfo();
                    resultTable = new JTable(churchService);
                    add(new JScrollPane(resultTable), "Center");
                    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    resultTable.setRowSorter(sorter);
                    setSize(1000, 550);
                    setLocation(100, 100);

                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(250, 150);
            setLocation(580, 300);
        }
    }

    class DisplayTable extends JDialog {


        private JButton print;

        private JPanel panel;
        private JLabel displayBirthDay;
        private JButton submit;
        private JLabel noting;
        private JButton printNumber;
        private Object[][] objects;
        private String [] columnObject;
        private List<Member> birthDayList;
        private JTable resultTable;
        private CreatePhoneTxtClass createPhoneTxtClass;

        public DisplayTable(JFrame frame) {
            super(frame, "", null);
            setLayout(new BorderLayout());

            createPhoneTxtClass = new CreatePhoneTxtClass();
            panel = new JPanel();
            noting = new JLabel();
            submit = new JButton("VIEW");
            print = new JButton("PRINT");
            displayBirthDay = new JLabel("Click to View Birthday Of Members");
            printNumber = new JButton("SAVE NUMBER");
            printNumber.setEnabled(false);
            panel.setLayout(new FlowLayout());
            panel.add(displayBirthDay);
            panel.add(noting);
            panel.add(submit);
            panel.add(printNumber);
            add(panel, "South");
            add(new JScrollPane(resultTable), "Center");
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {

                    birthDayList =churchService.getBirthDaysWorkerList();
                    int size = birthDayList.size();
                    objects = new Object[size][7];
                    for(int i =0; i<size; i++)
                    {
                        objects[i][0] = birthDayList.get(i).getFirstName();
                        objects[i][1] = birthDayList.get(i).getLastName();
                        objects[i][2] = birthDayList.get(i).getAddress();
                        objects[i][3] = birthDayList.get(i).getDateBorn();
                        objects[i][4] = birthDayList.get(i).getStatus();
                        objects[i][5] = birthDayList.get(i).getPhone();
                        objects[i][6] = birthDayList.get(i).getSex();

                    }
                    columnObject = new String[]{ "first_name", "last_name", "address", "date_born",
                            "status", "phone","sex" };
                    resultTable = new JTable(objects,columnObject);
                    resultTable.setCellSelectionEnabled(true);
                    printNumber.setEnabled(true);
                }
            });
            printNumber.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    List<String> stringList = new ArrayList<>();
                    List<Member> list = new ArrayList<>();
                    String[] birthArray = new String[list.size()];
                    list = churchService.getBirthDaysWorkerList();
                    for (int x = 0; x < list.size(); x++) {
                        stringList.add(list.get(x).getPhone());
                    }
                    birthArray = stringList.toArray(birthArray);
                    createPhoneTxtClass.createPhoneTxtFile(birthArray);
                    JOptionPane.showMessageDialog((Component) null, "Operation Successful ");
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");
                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true,
                                (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(1200, 650);
            setLocation(50, 50);
        }
    }

    class AddWorkerDialog extends JDialog {
        private Workers dataWorkers;
        private NonWorker nonWorker;
        private JPanel panel;
        private JLabel positionLabel;
        private JTextField  position;
        private JPanel workers;
        private JButton submit;
        private JTextField depart1;
        private JTextField depart2;
        private JTextField idText;
        private JLabel idLabel;
        private JTextField depart;

        private JButton close;
        // when creating a department object, the parameters required are String departHead, String departName,
// int total member. therefor all this information shall be retried from the data base. A condition arises
// where the department does not have any object/information in the data base, an initial value will be passed as default
        private ArrayList<String> departmentSet = new ArrayList<>();
        private String[] departArray = new String[departmentSet.size()];

        private JLabel departLabel, depart1Label, depart2Label;

        public AddWorkerDialog(JFrame frame)
        {
            super(frame, "    ADD TO WORK FORCE        ", null);
            setLayout(new BorderLayout());
            panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2, 4, 2));
            depart1 = new JTextField(10);
            idText = new JTextField(5);
            workers = new JPanel();
            idLabel = new JLabel("         ID Number");
            departLabel = new JLabel("       Department");
            depart1Label = new JLabel("       Department");
            depart2Label = new JLabel("       Department");
            workers.setLayout(new FlowLayout());
            submit = new JButton("SUBMIT");
            depart2 = new JTextField(10);
            submit.setIcon(new javax.swing.ImageIcon("C:\\church\\Save16.gif"));
            close = new JButton("CLOSE");
            positionLabel = new JLabel("        Position");
            position = new JTextField(10);
            depart = new JTextField(10);
            depart1 = new JTextField(10);
            departLabel.setBorder(BorderFactory.createTitledBorder(""));
            depart2Label.setBorder(BorderFactory.createTitledBorder(""));
            idLabel.setBorder(BorderFactory.createTitledBorder(""));
            positionLabel.setBorder(BorderFactory.createTitledBorder(""));
            depart1Label.setBorder(BorderFactory.createTitledBorder(""));
            depart2.setBorder(BorderFactory.createTitledBorder(""));
            depart.setBorder(BorderFactory.createTitledBorder(""));
            depart1.setBorder(BorderFactory.createTitledBorder(""));
            panel.add(idLabel);
            panel.add(idText);
            panel.add(positionLabel);
            panel.add(position);
            panel.add(departLabel);
            panel.add(depart);
            panel.add(depart1Label);
            panel.add(depart1);
            panel.add(depart2Label);
            panel.add(depart2);
            workers.add(submit);
            workers.add(close);
            add(panel, "Center");
            add(workers, "South");
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (depart.getText() != null || !depart.getText().equalsIgnoreCase(""))
                    {
                        departmentSet.add(depart.getText());
                    }

                    if (depart1.getText() != null || !depart1.getText().equalsIgnoreCase(""))
                    {
                        departmentSet.add(depart1.getText());
                    }

                    if (depart2.getText() != null || !depart2.getText().equalsIgnoreCase(""))
                    {
                        departmentSet.add(depart2.getText());
                    }

                    departArray = departmentSet.toArray(departArray);
                    StringBuffer arrayBuffer = new StringBuffer();
                    nonWorker = churchService.findNonWorkerById(idText.getText());
                    for (int i = 0; i < departArray.length; i++)
                    {
                        arrayBuffer.append(departArray[i]);
                    }
// create a worker object, and then added to the database by calling the method addWorker of QueryImplementation class
                    dataWorkers = new Workers(idText.getText(), position.getText(), nonWorker.getJoinDate(), arrayBuffer.toString());
                    churchService.addWorker(dataWorkers);
//  the member that has been added to the worker table will be deleted from the member table automatically.
                    churchService.updateLoginMemberStatus("Worker",idText.getText());
                    churchService.deleteNonMember(idText.getText());

                    idText.setText("");
                    idText.setEditable(true);
                    position.setText("");
                    position.setEditable(true);
                    depart.setText("");
                    depart.setEditable(true);
                }
            });
            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    dispose();
                }
            });
            setSize(400, 350);
            setLocation(560, 150);
        }
    }
    class EditDialog extends JDialog {

        private JButton searchButton;
        private Member searchMember;
        private JPanel enter = new JPanel();
        private JPanel panel = new JPanel();
        private JTextField name1,sfield, ffield,name2, Adfield,idNumber,photoImage,
                sex, datebirth,phoneNum,names,idfield;
        private JLabel  name1Label,  name2Label, areaLabel, idNumberLabel, datebirthLabel, firstName, status, id, address, pho, sexLabel, street;
        private JButton submit,photoLabel;

        public EditDialog(JFrame Eframe) {
            super(Eframe, " EDIT INFORMATION", null);
            enter.setLayout(new GridLayout(1, 4, 3, 1));
            searchButton = new JButton("SEARCH .....");
            panel.setLayout(new GridLayout(6, 4, 2, 1));
            enter.setBorder(BorderFactory.createTitledBorder("ENTER ID-NUMBER FOR INFORMATION TO EDIT"));
            setLayout(new BorderLayout());
            sfield = new JTextField(15);
            idfield = new JTextField(5);
            names = new JTextField(10);
            submit = new JButton("SUBMIT");
            name1 = new JTextField(15);
            name2 = new JTextField(15);
            street = new JLabel("");
            Adfield = new JTextField(15);
            phoneNum = new JTextField(10);
            idNumber = new JTextField(10);
            datebirth = new JTextField(10);
            photoImage = new JTextField( "  ");
            sex = new JTextField(10);
            name1Label = new JLabel("              FIRSTNAME");
            areaLabel = new JLabel("              ");
            firstName = new JLabel("              LASTNAME");
            status = new JLabel("              STATUS");
            id = new JLabel("              ID NUMBER");
            address = new JLabel("              ADDRESS");
            pho = new JLabel("              PHONE");
            idNumberLabel = new JLabel("              ID NUMBER");
            datebirthLabel = new JLabel("              MONTH OF BIRTH");
            photoLabel = new JButton( " Picture Search... ");
            sexLabel = new JLabel("              SEX");
            ffield = new JTextField(15);
            panel.add(firstName);
            panel.add(ffield);
            panel.add(name1Label);
            panel.add(name1);
            panel.add(status);
            panel.add(sfield);
            panel.add(address);
            panel.add(Adfield);
            panel.add(pho);
            panel.add(phoneNum);
            panel.add(idNumberLabel);
            panel.add(idNumber);
            panel.add(datebirthLabel);
            panel.add(datebirth);
            panel.add(sexLabel);
            panel.add(sex);
            panel.add(photoLabel);
            panel.add(photoImage);
            panel.add(areaLabel);
            panel.add(street);
            panel.add(new JLabel("  "));
            panel.add(new JLabel("  "));
            panel.add(new JLabel("  "));
            panel.add(submit);
            enter.add(id);
            enter.add(idfield);
            enter.add(new JLabel(""));
            enter.add(searchButton);
            submit.setIcon(new javax.swing.ImageIcon("C:\\church\\Save16.gif"));
            add(panel, "Center");
            add(enter, "South");
            photoLabel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fileType = "";
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
                    int result = jFileChooser.showOpenDialog(null);
                    if (result==JFileChooser.CANCEL_OPTION)
                    {
                        System.exit(1);
                    }
                    File file = jFileChooser.getSelectedFile();
                    if(file.getName().equals(""))
                    {
                        photoImage.setText("src/main/resources/picture/churchone.jpg");
                    }
                    try {
                        file.getAbsolutePath();
                        if(file==null)
                            photoImage.setText("src/main/resources/picture/churchone.jpg");

                    }
                    catch (NullPointerException nullPointerException)
                    {
                        photoImage.setText("src/main/resources/picture/churchone.jpg");
                    }
                      photoImage.setText(file.getAbsolutePath());
                    if (photoImage.getText().endsWith("jpg")) {
                         fileType = "jpg";
                    }
                    else if (photoImage.getText().endsWith("jif")) {
                         fileType = "jif";
                    }
                    else if (photoImage.getText().endsWith("jpeg")) {
                         fileType = "jpeg";
                    }
                    else if (photoImage.getText().endsWith("png")) {
                         fileType = "png";
                    }
                    BufferedImage bufferedImage = null;
                    BufferedImage saveBufferedImage = null;
                    try {
                            bufferedImage = ImageIO.read(new File(photoImage.getText()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                //    Image images = bufferedImage.getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                    File saveFile = new File("C:\\church\\" + file.getName() + "1." + fileType);
                    try {
                        ImageIO.write(ImageConversion.convertToBufferedImage(bufferedImage),fileType,saveFile);
                        photoImage.setText("C:\\church\\" + file.getName()+"1." + fileType);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String no = idfield.getText();
                    String fileTypes = "";
                    searchMember = churchService.displayMemberMethod(no);
                    if (searchMember != null) {
                        photoImage.setText(searchMember.getMemberPhoto());
                        //

                        if (photoImage.getText().endsWith("jpg")) {
                            fileTypes = "jpg";
                        } else if (photoImage.getText().endsWith("jif")) {
                            fileTypes = "jif";
                        } else if (photoImage.getText().endsWith("jpeg")) {
                            fileTypes = "jpeg";
                        } else if (photoImage.getText().endsWith("png")) {
                            fileTypes = "png";
                        }
                  //  }
                    //  BufferedImage bufferedImage = null;
                    try {
                        BufferedImage bufferedImage = ImageIO.read(new File(photoImage.getText()));
                        String fi = photoImage.getText();
                        String[] fileNemeString = fi.split("\\\\");
                        int lenst = fileNemeString.length;
// the image is sized and save to file. the file location is provided.
                        //    Image images = bufferedImage.getScaledInstance(150, 120, Image.SCALE_FAST);
                        File saveFile = new File("C:\\church\\" + fileNemeString[lenst - 1]);

                        ImageIO.write(ImageConversion.convertToBufferedImage(bufferedImage), fileTypes, saveFile);
                        photoImage.setText("C:\\church\\" + fileNemeString[lenst - 1]);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error Reading File");
                        throw new RuntimeException(ex);
                    }
                    //

                        Optional<String> fnameO = Optional.ofNullable(searchMember.getFirstName());
                        Optional<String> lnameO = Optional.ofNullable(searchMember.getLastName());
                        Optional<String> statusO = Optional.ofNullable(searchMember.getStatus());
                        Optional<String> addressO = Optional.ofNullable(searchMember.getAddress());
                        Optional<String> phoneO = Optional.ofNullable(searchMember.getPhone());
                        Optional<String> sexsO = Optional.ofNullable(searchMember.getSex());
                        Optional<String> dateBO = Optional.ofNullable(searchMember.getDateBorn());

                    //
                    String fname = searchMember.getFirstName();
                    String lname = searchMember.getLastName();
                    String status = searchMember.getStatus();
                    String address = searchMember.getAddress();
                    String phone = searchMember.getPhone();
                    String sexs = searchMember.getSex();
                    String dateB = searchMember.getDateBorn();

                    if ((fname.equalsIgnoreCase("")||fnameO.isEmpty()) && (lname.equalsIgnoreCase("")|| lnameO.isEmpty()) &&
                            (status.equalsIgnoreCase("")||statusO.isEmpty()) && (address.equalsIgnoreCase("")|| addressO.isEmpty()) &&
                            (phone.equalsIgnoreCase("")|| phoneO.isEmpty()) && (sexs.equalsIgnoreCase("")||sexsO.isEmpty()) &&
                            (dateB.equalsIgnoreCase("")||dateBO.isEmpty())) {
                        SerialMember serialMember = churchService.displayMethod(no);
                        churchService.updateMemberInfo(serialMember.getId(), serialMember.getAddress(), serialMember.getDateBorn(),
                                serialMember.getFirstName(), serialMember.getLastName(), serialMember.getStatus(),
                                serialMember.getPhone(), serialMember.getSex(), serialMember.getMemberPhoto(), no);
                    }
                        searchMember = churchService.displayMemberMethod(no);
                    ffield.setText(searchMember.getFirstName());
                    ffield.setEditable(true);
                    name1.setText(searchMember.getLastName());
                    name1.setEditable(true);
                    sfield.setText(searchMember.getStatus());
                    sfield.setEditable(true);
                    Adfield.setText(searchMember.getAddress());
                    Adfield.setEditable(true);
                    phoneNum.setText(searchMember.getPhone());
                    phoneNum.setEditable(true);
                    sex.setText(searchMember.getSex());
                    sex.setEditable(true);
                    datebirth.setText(searchMember.getDateBorn());
                    datebirth.setEditable(true);
                }
                    else
                {
                    sfield.setText("");
                    ffield.setText("");
                    idfield.setText("");
                    names.setText("");
                    name1.setText("");
                    name2.setText("");
                    street.setText("");
                    Adfield.setText("");
                    phoneNum.setText("");
                    idNumber.setText("");
                    datebirth.setText("");
                    sex.setText("");
                }
             }
            });
            submit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    String fileTypes = "";
                    if (searchMember != null) {
                        photoImage.setText(searchMember.getMemberPhoto());
                        //

                        if (photoImage.getText().endsWith("jpg")) {
                            fileTypes = "jpg";
                        } else if (photoImage.getText().endsWith("jif")) {
                            fileTypes = "jif";
                        } else if (photoImage.getText().endsWith("jpeg")) {
                            fileTypes = "jpeg";
                        } else if (photoImage.getText().endsWith("png")) {
                            fileTypes = "png";
                        }

                        //  BufferedImage bufferedImage = null;
                        try {
                            BufferedImage bufferedImage = ImageIO.read(new File(photoImage.getText()));
                            String fi = photoImage.getText();
                            String[] fileNemeString = fi.split("\\\\");
                            int lenst = fileNemeString.length;
// the image is sized and save to file. the file location is provided.
                            //    Image images = bufferedImage.getScaledInstance(150, 120, Image.SCALE_FAST);
                            File saveFile = new File("C:\\church\\" + fileNemeString[lenst - 1]);

                            ImageIO.write(ImageConversion.convertToBufferedImage(bufferedImage), fileTypes, saveFile);
                            photoImage.setText("C:\\church\\" + fileNemeString[lenst - 1]);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error Reading File");
                            throw new RuntimeException(ex);
                        }
                        churchService.updateMemberInfo(idfield.getText(), Adfield.getText(), datebirth.getText(),
                                ffield.getText(), name1.getText(), sfield.getText(),
                                phoneNum.getText(), sex.getText(), photoImage.getText(), idfield.getText());
                        sfield.setEditable(true);
                        ffield.setEditable(true);
                        idfield.setEditable(true);
                        names.setEditable(true);
                        name1.setEditable(true);
                        name2.setEditable(true);
                        Adfield.setEditable(true);
                        phoneNum.setEditable(true);
                        idNumber.setEditable(true);
                        datebirth.setEditable(true);
                        sex.setEditable(true);
                        sfield.setText("");
                        ffield.setText("");
                        idfield.setText("");
                        names.setText("");
                        name1.setText("");
                        name2.setText("");
                        street.setText("");
                        Adfield.setText("");
                        phoneNum.setText("");
                        idNumber.setText("");
                        datebirth.setText("");
                        sex.setText("");
                        photoImage.setText("");
                    }
                }
            });
            setSize(700, 300);
            setLocation(300, 300);
        }
    }
    class WDialog extends JDialog {

        private JButton print;

        private JPanel onePanel;
        private JTextField text;
        private JLabel label;
        private String []columnSize;
        private Object[][] objects;
        public WDialog(JFrame wFrame) {
            super(wFrame, "      WORK FORCE            ", null);
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JPanel panels = new JPanel();
            print = new JButton("PRINT");
            onePanel = new JPanel();
            onePanel.setLayout(new BorderLayout());
            panel.setLayout(new GridLayout(1, 2, 1, 1));
            JButton enter = new JButton("VIEW WORKER");
            panel.setBorder(BorderFactory.createTitledBorder(""));
            text = new JTextField(10);
            panel.add(enter);
            panel.add(print);
            onePanel.add(panel, "South");
            add(onePanel, "South");
            enter.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                   List<AllWorkIng> worker = churchService.getAllWorkers();
                   List<String>workerId = churchService.getWorkerIdWorkerLogin();//get all id with nonmember status

                    for (String id: workerId)
                    {
                        List<AllWorkIng>idList = worker.stream().filter(x -> x.id().equalsIgnoreCase(id)).toList();

                        if (!idList.isEmpty())
                        {
                            churchService.updateLoginMemberStatus("Worker",id);

                        }
                    }
                    List<AllWorkIng> workers = churchService.getAllWorkers();
                   var size = workers.size();
                    var numbs =1;
                   objects = new Object[size][13];
                    columnSize = new String []{"Serial Number", "id", "sex", "last_name", "first_name", "status", "address", "phone", "attendance", "register_date","resent"," depart_names" ,"worker_position"};
                    for (int i = 0; i < size; i++) {
                        objects[i][0] = numbs++;
                        objects[i][1] = workers.get(i).id();
                        objects[i][2] = workers.get(i).sex();
                        objects[i][3] = workers.get(i).last_name();
                        objects[i][4] = workers.get(i).first_name();
                        objects[i][5] = workers.get(i).status();
                        objects[i][6] = workers.get(i).address();
                        objects[i][7] = workers.get(i).phone();
                        objects[i][8] = workers.get(i).attendance();
                        objects[i][9] = workers.get(i).register_date();
                        objects[i][10] = workers.get(i).resent();
                        objects[i][11] = workers.get(i).depart_names();
                        objects[i][12] = workers.get(i).worker_position();
                    }
                    //    churchService.getAllWorkersAttendanceTime();
                    resultTable = new JTable(objects,columnSize);
                    add(new JScrollPane(resultTable), "Center");
               //     TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
               //     resultTable.setRowSorter(sorter);
                    //    churchService.getAllWorkers();
                    setSize(1100, 600);
                    setLocation(50, 80);

                }
            });

            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }

                }
            });
            setSize(500, 100);
            setLocation(520, 410);
        }
    }
    class DateChurchAttendanceDialog extends JDialog {
        private JPanel pans;
        private JPanel pan1;
        private JPanel pan2;
        private JTextField locates;
        private JButton locat;
        private JButton submit;
        private JButton print;

        public DateChurchAttendanceDialog(final JFrame frame) {
            super(frame, " All Church Activity By Date", null);
            setLayout(new BorderLayout());
            pans = new JPanel();
            pans.setLayout(new GridLayout(2, 1, 3, 1));
            pan1 = new JPanel();
            pan1.setLayout(new FlowLayout());
            pan2 = new JPanel();
            pan2.setLayout(new FlowLayout());
            locates = new JTextField(10);
            locat = new JButton("Enter date");
            print = new JButton("PRINT");
            submit = new JButton("SEARCH.....");
            pan1.add(locat);
            pan1.add(locates);
            pan2.add(submit);
            pan2.add(print);
            pans.add(pan1);
            pans.add(pan2);
            add(pans, "South");
            locates.setText("");
            locat.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    DatePicker picker = new DatePicker(frame);
                    picker.displayDate();
                    locates.setText(picker.getPickedDate());
                }
            });
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if ((locates.getText().equalsIgnoreCase("")))
                    {
                        churchService.findAllAttendance();
                    }
                    else if (!locates.getText().equalsIgnoreCase(""))
                    {
                        churchService.findAllAttendanceByDate(locates.getText());
                    }
                    resultTable = new JTable(churchService);
                    add(new JScrollPane(resultTable), "Center");
                    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    resultTable.setRowSorter(sorter);
                    setSize(1300, 550);
                    setLocation(100, 100);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(350, 150);
            setLocation(650, 400);
        }
    }

    class Attendance extends JDialog {
        private JPanel panel;
        private JButton submit;
        private JButton close;
        private JButton locat;
        private JButton print;
        private JLabel locate2;
        private JTextField dateText;
        private DatePicker pickDate;
        private String [] columnSize;
        private Object[][] object;
        public Attendance(final JFrame frame) {
            super(frame, "      VIEW  ATTENDANCE PRESENT/  ABSENCE  IN CHURCH       ", null);
            setLayout(new BorderLayout());
            panel = new JPanel();
            dateText = new JTextField(10);
            panel.setLayout(new GridLayout(2, 2, 1, 1));
            submit = new JButton("VIEW ALL");
            submit.setToolTipText("CLICK TO VIEW ALL");
            print = new JButton("PRINT");
            panel.add(new JLabel(" PRESENT/ABSENCE"));
            panel.add(dateText);
            panel.add(submit);
            panel.add(print);
            add(panel, "South");
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (dateText.getText() != null || !dateText.getText().equalsIgnoreCase("")) {
                       List<InOutSunday>listInOut= churchService.selectOutSunday(dateText.getText());
                       var size = listInOut.size();
                       object = new Object[size][9];
                        columnSize = new String[]{"Serial Number","id","sex","last_name","first_name","address","phone","attendance","resent"};
                        var numbs = 1;
                        for (int i = 0; i < size; i++) {
                            object[i][0] = numbs++;
                            object[i][1] = listInOut.get(i).id();
                            object[i][2] = listInOut.get(i).sex();
                            object[i][3] = listInOut.get(i).last_name();
                            object[i][4] = listInOut.get(i).first_name();
                            object[i][5] = listInOut.get(i).address();
                            object[i][6] = listInOut.get(i).phone();
                            object[i][7] = listInOut.get(i).attendance();
                            object[i][8] = listInOut.get(i).resent();
                        }
                        resultTable = new JTable(object,columnSize);
                        add(new JScrollPane(resultTable), "Center");
                    //    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    //    resultTable.setRowSorter(sorter);
                    }
                    setSize(1200, 750);
                    setLocation(100, 100);

                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");
                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat,
                                true, null, true);
                    } catch (HeadlessException | PrinterException sql) {
                        sql.printStackTrace();
                    }

                }
            });
            setSize(500, 100);
            setLocation(650, 450);
        }
    }

    class DateAttendanceMemberDialog extends JDialog {
        private JPanel pans;
        private JPanel pan1;
        private JPanel pan2;
        private JTextField locates;
        private JButton locat;
        private JButton submit;
        private JButton print;
        private String []columnSize;
        private Object[][] objects;
        public DateAttendanceMemberDialog(final JFrame frame) {
            super(frame, " Attendance By Date", null);
            setLayout(new BorderLayout());
            pans = new JPanel(new GridLayout(2, 1, 3, 1));
            pan1 = new JPanel(new FlowLayout());
            pan2 = new JPanel(new FlowLayout());
            locates = new JTextField(10);
            locat = new JButton("Enter date");
            print = new JButton("PRINT");
            submit = new JButton("SEARCH.....");
            pan1.add(locat);
            pan1.add(locates);
            pan2.add(submit);
            pan2.add(print);
            pans.add(pan1);
            pans.add(pan2);
            locates.setText((new DatePicker(frame)).getDefaultDate());
            add(pans, "South");
            locat.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    DatePicker picker = new DatePicker(frame);
                    picker.displayDate();
                    locates.setText(picker.getPickedDate());
                }
            });
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {

                    if (locates.getText().isEmpty()) {
                      //  churchService.memberAttendanceTime( locates.getText());
                    }
                    else {
                        List<AttendMember> attendMemberList = churchService.memberAttendanceTime( locates.getText());
                        var size = attendMemberList.size();
                        objects = new Object[size][5];
                        columnSize = new String[]{"Serial Number","first_name","last_name","phone","address"};
                        var nums = 1;
                        for (int i = 0; i < size; i++) {
                            objects[i][0]= nums++;
                            objects[i][1] = attendMemberList.get(i).first_name();
                            objects[i][2] = attendMemberList.get(i).last_name();
                            objects[i][3] = attendMemberList.get(i).phone();
                            objects[i][4] = attendMemberList.get(i).address();
                            resultTable = new JTable(objects,columnSize);
                            add(new JScrollPane(resultTable), "Center");
                        }

                    }
                  //  resultTable = new JTable(objects,columnSize);
                  //  add(new JScrollPane(resultTable), "Center");
                   // TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                   // resultTable.setRowSorter(sorter);

                    setSize(1000, 650);
                    setLocation(50, 50);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");
                    try {
                        resultTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(350, 150);
            setLocation(350, 300);
        }
    }
    class MaintananceDialog extends JDialog {

        private double costValue;
        private JTextField worknumber, accStartDate, accEnddate, description, workName, costText, supervisorText, statusText, remarkText;
        private JButton startDate, endDate;
        private JLabel worknumberLabel, descripLabel, workLabel, costLabel, supervisorLabel, statusLabel, remarkLabel;
        private JButton submit;
        private JButton cancel;
        private JPanel submitPanel;
        private JPanel dataPanel;
        private Maintenance maintenance = new Maintenance();

        public MaintananceDialog(final JFrame frame) {
            super(frame, "ENTER  MAINTENANCE ACTIVITY ", null);
            setLayout(new BorderLayout());
            submitPanel = new JPanel();
            dataPanel = new JPanel();
            submitPanel.setLayout(new FlowLayout());
            dataPanel.setLayout(new GridLayout(9, 2, 3, 1));
            //   accStartLabel = new JLabel("        OFFERING");
            startDate = new JButton("ENTER START DATE");
            endDate = new JButton("ENTER END DATE");
            //  accEndLabel = new JLabel("        TITHE");
            descripLabel = new JLabel("               DESCRIPTION");
            workLabel = new JLabel("               WORK NAME");
            costLabel = new JLabel("               COST");
            supervisorLabel = new JLabel("               WORKER NAME");
            statusLabel = new JLabel("               STATUS");
            remarkLabel = new JLabel("               REMARK");
            descripLabel.setBorder(BorderFactory.createTitledBorder(""));
            workLabel.setBorder(BorderFactory.createTitledBorder(""));
            costLabel.setBorder(BorderFactory.createTitledBorder(""));
            statusLabel.setBorder(BorderFactory.createTitledBorder(""));
            supervisorLabel.setBorder(BorderFactory.createTitledBorder(""));
            remarkLabel.setBorder(BorderFactory.createTitledBorder(""));
            accStartDate = new JTextField();
            accEnddate = new JTextField();
            description = new JTextField();
            workName = new JTextField();
            costText = new JTextField();
            worknumber = new JTextField();
            worknumberLabel = new JLabel( "               WORK NUMBER");
            worknumberLabel.setBorder(BorderFactory.createTitledBorder(""));
            supervisorText = new JTextField();
            statusText = new JTextField();
            remarkText = new JTextField();
            submit = new JButton("SUBMIT");
            cancel = new JButton(" CANCEL");
            submitPanel.add(submit);
            submitPanel.add(cancel);
            dataPanel.add(startDate);
            dataPanel.add(accStartDate);
            dataPanel.add(endDate);
            dataPanel.add(accEnddate);
            dataPanel.add(descripLabel);
            dataPanel.add(description);
            dataPanel.add(costLabel);
            dataPanel.add(costText);
            dataPanel.add(workLabel);
            dataPanel.add(workName);
            dataPanel.add(statusLabel);
            dataPanel.add(statusText);
            dataPanel.add(remarkLabel);
            dataPanel.add(remarkText);
            dataPanel.add(supervisorLabel);
            dataPanel.add(supervisorText);
            dataPanel.add(worknumberLabel);
            dataPanel.add(worknumber);
            add(dataPanel, "Center");
            add(submitPanel, "South");
            accStartDate.setText((new DatePicker(frame)).getDefaultDate());
            startDate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    DatePicker picker = new DatePicker(frame);
                    picker.displayDate();
                    accStartDate.setText(picker.getPickedDate());
                }
            });
            accEnddate.setText((new DatePicker(frame)).getDefaultDate());
            endDate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    DatePicker picker = new DatePicker(frame);
                    picker.displayDate();
                    accEnddate.setText(picker.getPickedDate());
                }
            });
            cancel.addActionListener((event)->{

                statusText.setEditable(true);
                remarkText.setEditable(true);
                description.setEditable(true);
                supervisorText.setEditable(true);
                workName.setEditable(true);
                costText.setEditable(true);
                costText.setText("0.00");
                statusText.setText("");
                remarkText.setText("");
                description.setText("");
                worknumber.setText("");
                accStartDate.setText((new DatePicker(frame)).getDefaultDate());
                accEnddate.setText((new DatePicker(frame)).getDefaultDate());
                supervisorText.setText("");
                workName.setText("");
            });
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //    try {
                    if (description.getText() != null || workName.getText() != null ||
                            costText.getText() != null || supervisorText.getText() != null || statusText.getText() != null ||
                            remarkText.getText() != null||worknumber.getText()!=null) ;
                    {
                        costValue = Double.parseDouble(costText.getText());

                        maintenance.setCost(costValue);
                        maintenance.setWorkNumber(Integer.parseInt(worknumber.getText()));
                        maintenance.setStatus(statusText.getText());
                        maintenance.setRemark(remarkText.getText());
                        maintenance.setDescription(description.getText());
                        maintenance.setCompletionDate(accEnddate.getText());
                        maintenance.setActivityStartDate(accStartDate.getText());
                        maintenance.setSupervisorId(supervisorText.getText());
                        maintenance.setWork_name(workName.getText());
                        churchService.addMaintenanceActivity(maintenance);
                    }
                    statusText.setText("");
                    remarkText.setText("");
                    description.setText("");
                    costText.setText("0.00");
                    accStartDate.setText((new DatePicker(frame)).getDefaultDate());
                    accEnddate.setText((new DatePicker(frame)).getDefaultDate());
                    supervisorText.setText("");
                    workName.setText("");
                    worknumber.setText("");
                }
            });
            statusText.setEditable(true);
            remarkText.setEditable(true);
            description.setEditable(true);
            supervisorText.setEditable(true);
            workName.setEditable(true);
            worknumber.setEditable(true);
            statusText.setText("");
            remarkText.setText("");
            description.setText("");
            accStartDate.setText((new DatePicker(frame)).getDefaultDate());
            accEnddate.setText((new DatePicker(frame)).getDefaultDate());
            supervisorText.setText("");
            workName.setText("");
            worknumber.setText("");
            setSize(400, 500);
            setLocation(500, 250);

        }
    }
}


