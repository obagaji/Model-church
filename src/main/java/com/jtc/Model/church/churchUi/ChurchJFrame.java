package com.jtc.Model.church.churchUi;
/**
 *
 * @author Musa Daniel
 * @author JTC CONTRACTS SERVICE NIGERIA LIMITED
 * @version 1.2
 */
import com.jtc.Model.church.churchEntity.*;
import com.jtc.Model.church.churchRepo.QueryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.JTable.PrintMode;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* The springframework stereotype annotation will help spring to pick the class and includes it by generating a bean for it.
* Since the application is a desktop application we have to extends the JFrame class to be able to make use of the feature available.
*
*
* */
@Service
 public class ChurchJFrame extends JFrame {

    private JMenu searchMenu, attendanceG, about, admin, menu;
    private JTextField sfield, delsfield, firstField;
    private JTextField ffield;
    private JTextField Adfield;
    private JTextField idfield;
    private JTextField EnterID;
    private JTextField phoneNum;
    private JTextField names;
    private JMenuItem Edit, Allpeople, Name, addWork, newMenu, Delete, Login, display, WorkerItem, NonWorkerItem,
            attendans, WAttendance, MAttendance, official,reset, us;
    private JMenuItem TotalActivity, locateActivity, account, exit;
    private JMenuItem firstTimeResponse;
    private JMenuItem firstTime;
    private JMenuItem addFirstTimer;
    private JMenuItem acc;
    private JMenuItem StaffOfice;
    private JTextField lofield;
    private JLabel welcomeLabel;
    private JLabel DONLabel;
    private JLabel loginLabel;
    private JLabel displayL;
    private JLabel workerFlabel;
    private JLabel workerFirstLabel;
    private JLabel workerPlabel;
    private JLabel workerAlabel;
    private JLabel workerIdlabel;
    private JLabel workersexlabel;
    private JLabel workerStatusLabel;
    private JTextField workerffield,workerIdfield, workerAfield, workerPfield, workerdateofbirthfield, depart, position, idText,
             workerFirstfield, depart1, depart2;
    private JLabel idLabel;
    private JLabel positionLabel;
    private JLabel departLabel, depart1Label, depart2Label;
    private Member searchMember;
    private JTable resultTable;
    private JButton submit;
    private JButton searchButton;
    private Font font;
    private JTextArea displayArea;
    private JLabel label;
    private JButton print;
    private Timer animationTimer;
    private final int ANIMATION_DELAY = 1500;
    private String name;

    private JLabel display1,display2,display3;
    private static ArrayList<String> ARRAYLIST = new ArrayList();
    private static ArrayList<String> LOCALARRAY = new ArrayList();
    private static JList<String> LISTSTREET;
    private static JList<String> LISTAREA;
    private static JPasswordField TEXTPASSWORD;

//    private ImageDisplayClass imageDisplayClass;

    @Autowired
    QueryImplementation churchService;
    private ImageRendererClass imageRendererClass;

    private NonWorker nonWorker;
    private Member member;
    private Workers dataWorkers;
    private DateClass dateClass;

    public ChurchJFrame()  {
        super("                                  RCCG NEW CONVENENT MODEL PARISH         ");

        JMenuBar bar = new JMenuBar();
        bar.setOpaque(true);
        bar.setBackground(Color.BLUE);
        menu = new JMenu("VIEW");
        menu.setMnemonic('V');
        about = new JMenu("ABOUT");
        firstTime = new JMenuItem("VIEW FIRST TIME");
        StaffOfice = new JMenuItem("STAFF");
        attendanceG = new JMenu("  ATTENDANCE");
        Font var10003 = font;
        Font var10004 = font;
        font = new Font("SansSerif", 2, 12);
        menu.setFont(font);
        menu.setFocusPainted(false);
        print = new JButton("PRINT");
        menu.setText(" VIEW");
        about.setText("  ABOUT");
        about.setMnemonic('B');
        us = new JMenuItem("INFO");
        about.add(us);
        about.setFont(font);
        about.add(StaffOfice);
        StaffOfice.setMnemonic('S');
        Name = new JMenuItem("NAME");
        Name.setMnemonic('N');
      //  menu.add(Name);
        menu.addSeparator();
        Edit = new JMenuItem("EDIT");
        Edit.setMnemonic('E');
        menu.add(Edit);
        menu.addSeparator();
        acc = new JMenuItem("VIEW MAINTENANCE");
        acc.setMnemonic('M');
        addFirstTimer = new JMenuItem("FIRST  TIMERS");
        WorkerItem = new JMenuItem("WORKER");
        firstTimeResponse = new JMenuItem("FIRST  TIMERS RESPONSE");
        WorkerItem.setMnemonic('W');
        menu.add(WorkerItem);
        menu.addSeparator();
        Allpeople = new JMenuItem("ALL");
        Allpeople.setMnemonic('L');
        menu.add(Allpeople);
        menu.addSeparator();
        NonWorkerItem = new JMenuItem(" MEMBER - NON WORKERS");
        NonWorkerItem.setMnemonic('M');
        menu.add(NonWorkerItem);
        menu.addSeparator();
        displayArea = new JTextArea(3, 10);
        menu.add(acc);
        menu.addSeparator();
        DONLabel = new JLabel();
        displayL = new JLabel();
        displayL.setHorizontalAlignment(0);
        setLayout(new BorderLayout());
        label = new JLabel();
        searchMenu = new JMenu();
        searchMenu.setText("LOGIN");
        searchMenu.setFont(font);
        exit = new JMenuItem("EXIT");
        searchMenu.setIcon(new javax.swing.ImageIcon("C:\\church\\rccgsmall.jpg"));
        searchMenu.addSeparator();
        DONLabel.setIcon(new javax.swing.ImageIcon("C:\\church\\NCMP1.jpg"));
        DONLabel.setHorizontalAlignment(0);
        DONLabel.setIgnoreRepaint(true);
        Login = new JMenuItem("LOGIN");
        Login.setMnemonic('L');
        searchMenu.add(Login);
        searchMenu.addSeparator();
        display = new JMenuItem("DISPLAY BIRTHDAY");
        display.setMnemonic('D');
        searchMenu.add(display);
        searchMenu.addSeparator();
        reset = new JMenuItem("RESET");
        searchMenu.add(reset);
        searchMenu.addSeparator();
        searchMenu.add(exit);
        bar.add(searchMenu);
        bar.add(menu);
        admin = new JMenu();
        admin.isBorderPainted();
        admin.setBorderPainted(true);
        var10003 = font;
        var10004 = font;
        font = new Font("SansSerif", 2, 12);
        admin.setFont(font);
        admin.setText(" ADMIN");
        admin.setMnemonic('M');
        admin.setBackground(Color.red);
        Delete = new JMenuItem(" DELETE");
        Delete.setMnemonic('D');
        admin.add(Delete);
        admin.addSeparator();
        TotalActivity = new JMenuItem("PROGRAM ACTIVITY");
        attendans = new JMenuItem("ATTENDANCE");
        MAttendance = new JMenuItem("MEMBER ATTENDANCE");
        WAttendance = new JMenuItem("WORKER ATTENDANCE");
        locateActivity = new JMenuItem("PROGRAM/SUNDAY SERVICE");
        menu.add(locateActivity);
        account = new JMenuItem("MAINTENENCE");
        attendanceG.add(attendans);
        attendanceG.addSeparator();
        attendanceG.add(MAttendance);
        addWork = new JMenuItem(" ADD WORKER");
        addWork.setMnemonic('A');
        admin.add(addWork);
        admin.addSeparator();
        newMenu = new JMenuItem(" ADD MEMBER");
        newMenu.setMnemonic('N');
        admin.add(newMenu);
        admin.addSeparator();
        admin.add(TotalActivity);
        admin.addSeparator();
        official = new JMenuItem("OFFICIAL");
        admin.add(official);
        admin.addSeparator();
        admin.add(account);
        bar.add(attendanceG);
        bar.add(admin);
        bar.add(about);
        bar.add(new JLabel("                                                                                                                                                                                                  "));
        bar.add(label);
        String myString = DateFormat.getDateTimeInstance(1, 1).format(new Date());
        label.setText(myString);
        setJMenuBar(bar);
        EnterID = new JTextField(6);
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
     /*   Name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                SearchDialog searching = new SearchDialog((JFrame) null);
                searching.setVisible(true);
            }
        });*/
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
        Allpeople.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AllDialog allediting = new AllDialog((JFrame) null);
                allediting.setVisible(true);
            }
        });
        addWork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddWorkerDialog editing = new AddWorkerDialog((JFrame) null);
                editing.setVisible(true);
            }
        });
        Edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditDialog editing = new EditDialog((JFrame) null);
                editing.setVisible(true);
            }
        });
        WorkerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WDialog worker = new WDialog((JFrame) null);
                worker.setVisible(true);
            }
        });
        Delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DelDialog del = new DelDialog((JFrame) null);
                del.setVisible(true);
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (DateClass dateClass1: churchService.getAllDateClass())
                {
                    churchService.updateAllDateClass(dateClass1.getIdMember());
                }
                JOptionPane.showMessageDialog(null,"OK","RESET",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        TotalActivity.addActionListener(new ActionListener() {
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
        MAttendance.addActionListener(new ActionListener() {
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
        Login.addActionListener(e -> {
            LogDialog lin = new LogDialog((JFrame) null);
            lin.setVisible(true);
        });
        NonWorkerItem.addActionListener(e -> {
            NonDialog non = new NonDialog((JFrame) null);
            non.setVisible(true);
        });
        display.addActionListener(e -> {
            DisplayTable dis = new DisplayTable((JFrame) null);
            dis.setVisible(true);
        });
        idText = new JTextField(5);
        position = new JTextField(5);
        depart = new JTextField(10);
        idLabel = new JLabel("         ID Number");
        positionLabel = new JLabel("        Position");
        departLabel = new JLabel("       Department");
        depart1 = new JTextField(10);
        depart1Label = new JLabel("       Department");
        depart2 = new JTextField(10);
        depart2Label = new JLabel("       Department");
        welcomeLabel = new JLabel();
        departLabel.setBorder(BorderFactory.createTitledBorder(""));
        idLabel.setBorder(BorderFactory.createTitledBorder(""));
        positionLabel.setBorder(BorderFactory.createTitledBorder(""));
        depart2Label.setBorder(BorderFactory.createTitledBorder(""));
        depart1Label.setBorder(BorderFactory.createTitledBorder(""));
        welcomeLabel.setBorder(BorderFactory.createTitledBorder(""));
        var10003 = font;
        var10004 = font;
        font = new Font("SansSerif", 2, 18);
        welcomeLabel.setFont(font);
        welcomeLabel.setText("                                        \n RCCG      \n NEW     COVENANT      \n   MODEL     PARISH        ");
        add(welcomeLabel, "South");
        add(DONLabel, "Center");
        add(displayL, "North");
        setDefaultCloseOperation(3);
        setSize(1600, 1000);
        setVisible(true);
    }
    public void createPhoneTxtFile(String[] phone) {
        Filephone writer = new Filephone();
        writer.writeUsingBufferedWriter(phone);
    }
    static {
        LISTSTREET = new JList(ARRAYLIST.toArray());
        LISTAREA = new JList(LOCALARRAY.toArray());
        JTable jTable;
        TEXTPASSWORD = new JPasswordField(10);
    }
    private class TimerClass implements ActionListener {
        private TimerClass() {
        }
        public void actionPerformed(ActionEvent action) {
            display1.setText("");
            display2.setText("");
            display3.setText("");
            displayL.setText("");
        }
    }
    private class ImageTimer {
        private int indexPostion;
        private ImageTimer() {
        }
        public int ArrayLocationIndex(int index) {
            return indexPostion = index;
        }
    }
    class DelDialog extends JDialog {
        private JPanel pan;
        private JPanel pan2;
        private JLabel delField;
        public DelDialog(JFrame delFrame) {
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
            subm.setIcon(new javax.swing.ImageIcon("C:\\church\\windows-error.png"));
            pan2.add(subm);
            JButton canc = new JButton("CANCEL");
            add(pan, "Center");
            add(pan2, "South");
            final String value = JOptionPane.showInputDialog("Enter Worker OR Member as choice to delete");
            subm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (value.equalsIgnoreCase("Member")) {
                        churchService.deleteMember(delsfield.getText());
                        JOptionPane.showMessageDialog((Component) null, "OERATION  SUCCESSFUL");
                        delsfield.setText(" ");
                    } else if (value.equalsIgnoreCase("worker")) {
                        churchService.deleteWorker(delsfield.getText());
                        JOptionPane.showMessageDialog((Component) null, "OERATION  SUCCESSFUL");
                        delsfield.setText(" ");
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
    class MemberDialog extends JDialog {
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
        private DateClass dateClass;
        public MemberDialog(final JFrame memFrame) {
            super(memFrame, "    ADD MEMBERSHIP          ", true);
            setLayout(new BorderLayout());
            pan = new JPanel();
            pan.setBorder(BorderFactory.createTitledBorder(""));
            pan.setLayout(new GridLayout(10, 2, 5, 5));
            workerFlabel = new JLabel();
            workerFlabel.setText("          LASTNAME");
            workerffield = new JTextField(15);
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
            JButton cant = new JButton("CANCEL");
            pan.add(workerFlabel);
            pan.add(workerffield);
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
/*            pan.add(workerStreetLabel);
            pan.add(streetField);*/
            pan.add(workerEmail);
            pan.add(emailField);
            pan.add(sub);
            pan.add(cant);
            workerIdfield.setText("NC");
            workerIdfield.setEditable(true);
            add(pan, "Center");

            workerEmail.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
                    int result = jFileChooser.showOpenDialog(null);
                    if (result==JFileChooser.CANCEL_OPTION)
                    {
                        System.exit(1);
                    }
                    File file = jFileChooser.getSelectedFile();
                    if((file==null) || file.getName().equals(""))
                    {
                        emailField.setText("C:\\church\\rccgsmall.jpg");
                    }
                    emailField.setText(file.getAbsolutePath());

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
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                    String loginDates = dateFormat.format(new Date());
                    member = new Member(workerIdfield.getText(), mf, workerffield.getText(), workerFirstfield.getText(),
                            workerAfield.getText(),formatedDate,workerPfield.getText(),   sm,
                            1, loginDates, loginDates, emailField.getText());
                    String stringID = workerIdfield.getText();
/*
                    List<Member> compareMember = new ArrayList<>();
                    compareMember = churchService.findByIdValue(stringID);*/
                    if (!churchService.displayName(name)) {
                        nonWorker = new NonWorker(stringID, loginDates);
                       int x = churchService.addMemberInfo(member);
                        churchService.addNonWorkers(nonWorker);
                        dateClass = new DateClass(stringID,loginDates, "PRESENT");
                        churchService.addDateClass( dateClass);

                    } else {
                        JOptionPane.showMessageDialog((Component) null, "YOU ARE REGISTERED");
                    }

                    workerIdfield.setText("NC");
                    workerffield.setText("");
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
                    workerffield.setEditable(true);
                    workerAfield.setEditable(true);
                    workerPfield.setEditable(true);
                    workerFirstfield.setEditable(true);
                    //streetField.setEditable(true);
                }
            });
            cant.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    dispose();
                }
            });
            workerIdfield.setText("NC");
            workerffield.setText("");
            workerAfield.setText("");
            workerPfield.setText("");
            workerdateofbirthfield.setText((new DatePicker(memFrame)).getDefaultDate());
            workerStatus.setEnabled(true);
            workerSex.setEnabled(true);
            workerFirstfield.setText("");
            streetField.setText("");
            emailField.setText("");
            workerffield.setEditable(true);
            workerAfield.setEditable(true);
            workerPfield.setEditable(true);
            workerFirstfield.setEditable(true);
            //  streetField.setEditable(true);
            emailField.setEditable(true);
            setSize(400, 500);
            setLocation(580, 280);
        }
    }

    class WDialog extends JDialog {
        private JPanel onePanel;
        private JTextField text;
        private JLabel label;

        public WDialog(JFrame wFrame) {
            super(wFrame, "      WORK FORCE             ", null);
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            JPanel panels = new JPanel();
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
            enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    churchService.getAllWorkers();
                    resultTable = new JTable(churchService);
                    add(new JScrollPane(resultTable), "Center");
                    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    resultTable.setRowSorter(sorter);
                //    churchService.getAllWorkers();
                    setSize(1400, 600);
                    setLocation(50, 80);
                }
            });

            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, null, true);
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

    class AddWorkerDialog extends JDialog {
        private JPanel panel;
        private JPanel workers;
        private JButton submit;
        private JButton close;
// when creating a department object, the parameters required are String departHead, String departName,
// int total member. therefor all this information shall be retrived from the data base. A condition araises
// where the department does not have any object/information in the data base, an initail value will be passed as default

        private ArrayList<String> departmentSet = new ArrayList<>();
        private String[] departArray = new String[departmentSet.size()];

        public AddWorkerDialog(JFrame frame) {
            super(frame, "    ADD TO WORK FORCE        ", null);
            setLayout(new BorderLayout());
            panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2, 4, 2));
            workers = new JPanel();
            workers.setLayout(new FlowLayout());
            submit = new JButton("SUBMIT");
            submit.setIcon(new javax.swing.ImageIcon("C:\\church\\Save16.gif"));
            close = new JButton("CLOSE");
            departLabel.setBorder(BorderFactory.createTitledBorder(""));
            depart2Label.setBorder(BorderFactory.createTitledBorder(""));
            idLabel.setBorder(BorderFactory.createTitledBorder(""));
            positionLabel.setBorder(BorderFactory.createTitledBorder(""));
            depart1Label.setBorder(BorderFactory.createTitledBorder(""));
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
                    if (depart.getText() != null || !depart.getText().equalsIgnoreCase("")) {
                        departmentSet.add(depart.getText());
                    }
                    if (depart1.getText() != null || !depart1.getText().equalsIgnoreCase("")) {
                        departmentSet.add(depart1.getText());
                    }

                    if (depart2.getText() != null || !depart2.getText().equalsIgnoreCase("")) {
                        departmentSet.add(depart2.getText());
                    }

                    departArray = departmentSet.toArray(departArray);
                    StringBuffer arrayBuffer = new StringBuffer();
                    nonWorker = churchService.findNonWorkerById(idText.getText());
                    for (int i = 0; i < departArray.length; i++) {
                        arrayBuffer.append(departArray[i]);

                    }
// create a worker object, and then added to the database by calling the method addWorker of QueryImplementation class
                    dataWorkers = new Workers(idText.getText(), position.getText(), nonWorker.getJoinDate(), arrayBuffer.toString());
                    churchService.addWorker(dataWorkers);
//  the member that has been added to the worker table will be deleted from the member table automatically.
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
            setSize(400, 200);
            setLocation(560, 450);
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

        public Attendance(final JFrame frame) {
            super(frame, "      VIEW  ATTENDANCE PRESENT/ ABSENCE IN CHURCH       ", null);
            setLayout(new BorderLayout());
            panel = new JPanel();
            //locat = new JButton("  ENTER DATE");
            // locat.setToolTipText("enter the date in the format dd-MM-YYYY");
            dateText = new JTextField(10);
            // locate2 = new JLabel("");
            panel.setLayout(new GridLayout(2, 2, 1, 1));
            submit = new JButton("VIEW ALL");
            submit.setToolTipText("CLICK TO VIEW ALL");
            // close = new JButton("MEMBER");
            //lose.setToolTipText("ENTER DATE THEN CLICK TO VIEW");
            print = new JButton("PRINT");
            //   panel.add(locat);

            panel.add(new JLabel(" PRESENT/ABSENCE"));
            panel.add(dateText);
            panel.add(submit);
            //panel.add(close);
            panel.add(print);
            add(panel, "South");
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (dateText.getText() != null || !dateText.getText().equalsIgnoreCase("")) {
                    //    if(dateText.getText().equalsIgnoreCase("presence"))
                        churchService.selectOutSunday(dateText.getText());
                        resultTable = new JTable(churchService);
                        add(new JScrollPane(resultTable), "Center");
                        TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                        resultTable.setRowSorter(sorter);
                    }
                    // to display the return valued.
                    setSize(1200, 750);
                    setLocation(100, 100);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");
                    try {
                        resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat,
                                true, null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(500, 100);
            setLocation(650, 450);
        }
    }

    class LogDialog extends JDialog {

        private Member displayNames;

        public LogDialog(JFrame Lframe) {
            super(Lframe, "         WELCOME TO RCCG NEW CONVENENT MODEL PARISH        LOG IN ", true);
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
            pane.setLayout(new BorderLayout());
            log.setIcon(new javax.swing.ImageIcon("C:\\church\\rccgsmall.jpg"));
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
                    DateFormat dt = new SimpleDateFormat("dd-MM-YYYY");
                    String dateString = dt.format(new Date());
                    name = lofield.getText().trim();// name here is the login id of the user.
                    try {

                        List<Member> checkMember = new ArrayList<>() ;
                        checkMember = churchService.findByIdValue(name);
                        System.out.println(checkMember.get(0).getResent() + " checking");

                        if (!churchService.displayName(name)) {
                            if (churchService.getCurrentDate(name).equalsIgnoreCase(dateString))
                            {
                                JOptionPane.showMessageDialog(null, "You have Logged In Before");
                            }
                            //  logMember = true;
                            else// if (!churchService.getCurrentDate(nameId).equalsIgnoreCase(dateString))
                            {
                                churchService.updateCurrentDateInfo(dateString,name);
                                churchService.updateAttendanceOfMembers(name);
                                churchService.updateAttendance(name);
                                churchService.updateInchurchValue(name);
                                lofield.setText("");
                                if (animationTimer == null) {
                                    //  try {

                                    displayNames = checkMember.get(0);
                         /*} catch (NoValueFound ex) {
                             throw new RuntimeException(ex);
                         }*/

                                    if (displayNames != null) {
                                        display1.setText(displayNames.getFirstName());
                                        display2.setText(displayNames.getLastName());
                                    }
                                    display3.setText("");
                                    displayL.setFont(new Font("Serif", 1, 36));
                                    displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                    animationTimer = new Timer(1500, new TimerClass());
                                    animationTimer.start();
                                } else if (animationTimer.isRunning()) {
                                    //   try {
                                    displayNames = checkMember.get(0);
                         /*} catch (NoValueFound ex) {
                             throw new RuntimeException(ex);
                         }*/
                                    if (displayNames != null) {
                                        display1.setText(displayNames.getFirstName());
                                        display2.setText(displayNames.getLastName());
                                    }
                                    display3.setText("");
                                    displayL.setFont(new Font("Serif", 1, 36));
                                    displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                    animationTimer.restart();
                                }
                            }
                        }
// search the data base if the id of the user exist
                        else  {
                            JOptionPane.showMessageDialog((Component) null, "User Not Registered   : Please Register");
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
                        name = lofield.getText();
                        try {
                            List<Member> checkMember = new ArrayList<>() ;
                            checkMember = churchService.findByIdValue(name);

                                if (!churchService.displayName(name)) {
                                    if (churchService.getCurrentDate(name).equalsIgnoreCase(dateString))
                                {
                                    JOptionPane.showMessageDialog(null, "You have Logged In Before");
                                }

                                else// if (!churchService.getCurrentDate(nameId).equalsIgnoreCase(dateString))
                                {
                                    churchService.updateCurrentDateInfo(dateString,name);
                                    churchService.updateAttendanceOfMembers(name);
                                    churchService.updateAttendance(name);
                                    lofield.setText("");
                                    if (animationTimer == null) {
                                        //  try {
                                        displayNames = checkMember.get(0);
                             /*} catch (NoValueFound ex) {
                                 throw new RuntimeException(ex);
                             }*/
                                        if (displayNames != null) {
                                            display1.setText(displayNames.getFirstName());
                                            display2.setText(displayNames.getLastName());
                                        }
                                        display3.setText("");
                                        displayL.setFont(new Font("Serif", 1, 36));
                                        displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                        animationTimer = new Timer(1500, new TimerClass());
                                        animationTimer.start();

                                    } else if (animationTimer.isRunning()) {
                                        //   try {
                                        displayNames = checkMember.get(0);
                             /*} catch (NoValueFound ex) {
                                 throw new RuntimeException(ex);
                             }*/
                                        if (displayNames != null) {
                                            display1.setText(displayNames.getFirstName());
                                            display2.setText(displayNames.getLastName());
                                        }
                                        display3.setText("");
                                        displayL.setFont(new Font("Serif", 1, 36));
                                        displayL.setText("WELCOME TO RCCG NEW CONVENENT MODEL PARISH");
                                        animationTimer.restart();
                                    }
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "User Not Registered  : Please Register ");
                            }/* else if (!churchService.getName(name)) {
                                 JOptionPane.showMessageDialog( null, "Please Register ");
                             }*/
                        } catch (IllegalStateException sql) {
                            sql.toString();
                        }

                    }

                }
            });
            setSize(700, 350);
            setLocation(300, 300);
        }
    }

    class EditDialog extends JDialog {
        private JPanel enter = new JPanel();
        private JPanel panel = new JPanel();
        private JTextField name1, name2, idNumber, sex, datebirth, photoImage;
        private JLabel name1Label, name2Label, areaLabel, idNumberLabel, datebirthLabel, firstName, status, id, address, pho, sexLabel, street;
        private JButton submit, photoLabel;


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
            photoImage = new JTextField(10);
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
            photoLabel = new JButton("              ENTER PHOTO");
            sexLabel = new JLabel("              SEX");
            ffield = new JTextField(15);
            panel.add(firstName);
            panel.add(ffield);
            panel.add(name1Label);
            panel.add(name1);
            panel.add(status);
            panel.add(sfield);
            panel.add(areaLabel);
            panel.add(street);
            panel.add(address);
            panel.add(Adfield);
            panel.add(pho);
            panel.add(phoneNum);
            panel.add(idNumberLabel);
            panel.add(idNumber);
            panel.add(photoLabel);
            panel.add(photoImage);
            panel.add(datebirthLabel);
            panel.add(datebirth);
            panel.add(sexLabel);
            panel.add(sex);
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
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
                    int result = jFileChooser.showOpenDialog(null);
                    if (result==JFileChooser.CANCEL_OPTION)
                    {
                        System.exit(1);
                    }
                    File file = jFileChooser.getSelectedFile();
                    if((file==null) || file.getName().equals(""))
                    {
                        photoImage.setText("C:\\church\\rccgsmall.jpg");
                    }
                    photoImage.setText(file.getAbsolutePath());

                }
            });
            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String no = idfield.getText();
                    //    try {
                    searchMember = churchService.displayMethod(no);
                  /*   } catch (NoValueFound e) {
                         throw new RuntimeException(e);
                     }*/
                    if (searchMember != null) {
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
                        // street.setText(searchMember.getStreet());
                        // street.setEditable(true);
                        sex.setText(searchMember.getSex());
                        sex.setEditable(true);
                        photoImage.setText(searchMember.getMemberPhoto());
                        photoImage.setEditable(true);
                        datebirth.setText(searchMember.getDateBorn());
                        datebirth.setEditable(true);
                    } else {
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
                        photoImage.setText("");
                        sex.setText("");
                    }
                }
            });
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    churchService.updateMemberInfo(idfield.getText(), Adfield.getText(), datebirth.getText(),
                             ffield.getText(), name1.getText(), sfield.getText(),
                            phoneNum.getText(), sex.getText(),photoImage.getText(), idfield.getText());
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
                    photoImage.setEditable(true);
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
                    photoImage.setText("");
                    sex.setText("");
                }
            });
            setSize(600, 250);
            setLocation(550, 450);
        }
    }

    class SearchDialog extends JDialog {
        private JPanel panel, display, total;
        private JLabel otherN;
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
            EnterID.setText(" ");
            EnterID.setEditable(true);
            display.add(EnterID);
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

                        if (EnterID.getText().equalsIgnoreCase("") &&
                                (firstField.getText() != null && !firstField.getText().equalsIgnoreCase("")) ) {
                            searchMember = churchService.findByFirstName(firstField.getText());
                            System.out.println(EnterID.getText() + " name search");

                        } else if ((firstField.getText()).isEmpty() && !(EnterID.getText()).isEmpty()) {
                            System.out.println(EnterID.getText() + " id search 0");
                             idMember = churchService.displayMethod(EnterID.getText());
                           // searchMember.add(idMember);
                            System.out.println(EnterID.getText() + " id search 1");

                        }
                        columnSearch = new String[]{ "member_photo","id", "sex", "last_name", "first_name", "address", "date_born", "phone", "status",
                                "attendance", "register_date", "resent"};
                        searchObjects = new Object[1][12];
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(new File(idMember.getMemberPhoto()));
                        Image images = bufferedImage.getScaledInstance(150,120,Image.SCALE_SMOOTH);
                        image.setIcon( new ImageIcon(images,""));
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
                        imageRendererClass = new ImageRendererClass();
                        imageRendererClass.setSetTable(resultTable);
                        imageRendererClass.setString("member_photo");
                        resultTable.getColumn("member_photo").setCellRenderer(imageRendererClass);
/*
                        TableRowSorter<TableModel> sorter = new TableRowSorter((TableModel) resultTable);
                        resultTable.setRowSorter(sorter);*/

                    } catch (IllegalStateException sql) {
                        sql.printStackTrace();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    setSize(1600, 550);
                    setLocation(10, 100);
                }
            });
            setSize(530, 170);
            setLocation(600, 350);
        }
    }

    class InformationDialog extends JDialog {
        private JPanel panel;
        private JTextArea area;

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

    class NonDialog extends JDialog {
        public NonDialog(JFrame Noframe) {
            super(Noframe, " NoN Workers - MEMBER ", null);
            setLayout(new BorderLayout());
            JPanel pane = new JPanel();
            JButton search = new JButton("VIEW");
            pane.add(search);
            pane.add(print);
            add(pane, "South");
            search.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    churchService.getAllNonWorker();
                    resultTable = new JTable(churchService);
                    add(new JScrollPane(resultTable), "Center");
                    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    resultTable.setRowSorter(sorter);

                    setSize(1400, 550);
                    setLocation(100, 100);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true,
                                (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }

                }
            });
            setSize(300, 250);
            setLocation(650, 450);
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
        private JLabel deptLabel;
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
            //deptLabel = new JLabel("        SERVICE DATE");
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
            //  deptText = new JTextField(20);
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
/*            panel.add(deptLabel);
            panel.add(deptText);*/
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
                public void actionPerformed(ActionEvent ex) {
                    //    try {
                    if (minister.getText() != null && topic.getText() != null) {
                        String newDate = dateText.getText();
                        int m = Integer.parseInt(male.getText().trim());
                        int n = Integer.parseInt(female.getText().trim());
                        int c = Integer.parseInt(children.getText().trim());
                        int t = Integer.parseInt(total.getText().trim());
                        churchService.addChurchAttendance(new ServiceAttendance(newDate, minister.getText(), t ,topic.getText(),n, m,  c));
                        //    }
                    }
                }
            });
            dateText.setText((new DatePicker(frame)).getDefaultDate());
            topic.setEditable(true);
            //       deptText.setEditable(true);
            minister.setEditable(true);
            topic.setEditable(true);
            male.setEditable(true);
            female.setEditable(true);
            children.setEditable(true);
            total.setEditable(true);
            topic.setText("");
            //     deptText.setText("");
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


     class DateAttendanceMemberDialog extends JDialog {
         private JPanel pans;
         private JPanel pan1;
         private JPanel pan2;
         private JTextField locates;
         private JButton locat;
         private JButton submit;
         private JButton print;

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
                     resultTable = new JTable(churchService);
                     add(new JScrollPane(resultTable), "Center");
                     TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                     resultTable.setRowSorter(sorter);
                     if (locates.getText() != null) {
                         churchService.memberAttendanceTime( locates.getText());
                     }
                     setSize(1000, 650);
                     setLocation(50, 50);
                 }
             });
             print.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent event) {
                     MessageFormat headerFormat = new MessageFormat("");
                     MessageFormat footerFormat = new MessageFormat("Page-{0}");

                     try {
                         resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, (PrintRequestAttributeSet) null, true);
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

     class DateChurchAttendanceDialog extends JDialog {
         private JPanel pans;
         private JPanel pan1;
         private JPanel pan2;
         private JTextField locates;
         private JButton locat;
         private JButton submit;
         private JButton print;

         public DateChurchAttendanceDialog(final JFrame frame) {
             super(frame, " All Church Activity Or By Date", null);
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
                    // if (locates.getText() != null) {

                   //  }

                     setSize(1300, 550);
                     setLocation(100, 100);
                 }
             });
             print.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent event) {
                     MessageFormat headerFormat = new MessageFormat("");
                     MessageFormat footerFormat = new MessageFormat("Page-{0}");

                     try {
                         resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, (PrintRequestAttributeSet) null, true);
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

                     /*} catch (SQLException sql) {
                         sql.printStackTrace();
                     }*/
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

    class ViewMaintenanceDialog extends JDialog {
        private JPanel viewPanel;
        private JButton submit;

        public ViewMaintenanceDialog(JFrame frame) {
            super(frame, "VIEW  MAINTENANCE ACTIVITY", null);
            setLayout(new BorderLayout());
            submit = new JButton("VIEW MAINTENANCE");
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
                        resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, (PrintRequestAttributeSet) null, true);
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


    class AllDialog extends JDialog {


        private Object[][] objects;
        private String [] columnObject;
        //private JLabel image = new JLabel();
        public AllDialog(JFrame Noframe) {
            super(Noframe, " ALL MEMBER ", null);
            setLayout(new BorderLayout());
            JPanel pane = new JPanel();
            JButton search = new JButton("VIEW");
            pane.add(search);
            pane.add(print);
            add(pane, "South");
            search.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
// the use of defaulttablemode to supply the parameter fot jtable object.

                   List<Member> allMember = churchService.findAllMember();
                    int size = allMember.size(); //imageDisplayClass.findAllMember().size();
                    objects = new Object[size][12];
                    columnObject = new String[]{ "member_photo","id", "sex", "last_name", "first_name", "address", "date_born", "phone", "status",
                            "attendance", "register_date", "resent"};
                    for(int i =0; i<size; i++)
                    {
                        BufferedImage bufferedImage = null;
                        try {
                            bufferedImage = ImageIO.read(new File(allMember.get(i).getMemberPhoto()));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Image images = bufferedImage.getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                        JLabel image = new JLabel();
                        image.setIcon( new ImageIcon(images, ""));
                     //   }
                        objects[i][0] = image;
                        objects[i][1] = allMember.get(i).getId();
                        objects[i][2] = allMember.get(i).getSex();
                        objects[i][3] = allMember.get(i).getFirstName();
                        objects[i][4] = allMember.get(i).getLastName();
                        objects[i][5] = allMember.get(i).getAddress();
                        objects[i][6] = allMember.get(i).getDateBorn();
                        objects[i][7] = allMember.get(i).getPhone();
                        objects[i][8] = allMember.get(i).getStatus();
                        objects[i][9] = allMember.get(i).getAttendance();
                        objects[i][10] = allMember.get(i).getRegisterDate();
                        objects[i][11] = allMember.get(i).getResent();


                    }
                //    imageDisplayClass = new ImageDisplayClass(objects,columnObject);
                   // defaultTableModel.setDataVector();
                    resultTable = new JTable(objects,columnObject);
                    imageRendererClass = new ImageRendererClass();
                    imageRendererClass.setSetTable(resultTable);
                    imageRendererClass.setString("member_photo");

                 //   resultTable = new JTable(churchService);
                    add(new JScrollPane(resultTable), "Center");
                 //   TableRowSorter<TableModel> sorter = new TableRowSorter(imageDisplayClass);
                    resultTable.setRowHeight(120);
                    resultTable.getColumn("member_photo").setCellRenderer( imageRendererClass);
                    resultTable.setCellSelectionEnabled(true);
                    resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
                //    resultTable.setRowSorter(sorter);
                //    imageDisplayClass.findAllMember();
                    setSize(1300, 550);
                    setLocation(200, 100);
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true,
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

    class Display extends JDialog {
        private JPanel panel;
        private JPanel panel1;
        private JPanel dis;
        private JButton submit;
        private JList<String> searchList;
        private String[] searchText = new String[]{"MALE", "FEMALE", "SINGLE", "MARRIED"};
        private String[] sqlText = new String[]{"SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE SEX = 'MALE'",
                "SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE SEX = 'FEMALE'",
                " SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE STATUS = 'SINGLE'",
                "SELECT FIRST_NAME,LAST_NAME,ADDRESS,Phone FROM MEMBER WHERE STATUS = 'MARRIED'",
        };

        public Display(JFrame frame) {
            super(frame, "  Enter SQL select Statement   ");
            setLayout(new BorderLayout());
            panel = new JPanel();
            panel1 = new JPanel();
            dis = new JPanel();
            searchList = new JList<String>(searchText);
            searchList.setEnabled(true);
            searchList.setVisibleRowCount(4);
            searchList.setSelectionMode(0);
            displayArea.setEditable(true);
            dis.setLayout(new FlowLayout());
            panel.setBorder(BorderFactory.createTitledBorder(""));
            panel1.setLayout(new FlowLayout());
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
                    resultTable = new JTable(churchService);

                    add(new JScrollPane(resultTable), "Center");
                    TableRowSorter<TableModel> sorter = new TableRowSorter(churchService);
                    resultTable.setRowSorter(sorter);
                     /*} catch (SQLException sql) {
                         sql.printStackTrace();*/
                    // }

                    setSize(1300, 600);
                    setLocation(100, 10);
                }
            });
            setSize(900, 150);
            setLocation(100, 300);
            setVisible(true);
        }
    }

    class DisplayTable extends JDialog {
        private JPanel panel;
        private JLabel display;
        private JButton submit;
        private JLabel noting;
        private JButton printNumber;
        private Object[][] objects;
        private String [] columnObject;
        private List<BirthDayMember> birthDayList;

        public DisplayTable(JFrame frame) {
            super(frame, "", null);
            setLayout(new BorderLayout());
            panel = new JPanel();
            noting = new JLabel();
            submit = new JButton("VIEW");
            display = new JLabel("Click to View Birthday Of Workers");
            printNumber = new JButton("SAVE NUMBER");
            printNumber.setEnabled(false);
            panel.setLayout(new FlowLayout());
            panel.add(display);
            panel.add(noting);
            panel.add(submit);
            panel.add(printNumber);
            add(panel, "South");
            add(new JScrollPane(resultTable), "Center");
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    birthDayList =churchService.getBirthDaysWorkerList();
                    int size = birthDayList.size();
                    objects = new Object[size][8];
                    for (int i=0; i< size; i++)
                    {
                        if (birthDayList.get(i).MemberPhoto() !=null)
                        {
                            ImageIcon imageIcon = new ImageIcon(new ImageIcon(birthDayList.get(i).MemberPhoto()).getImage()
                                    .getScaledInstance(150,120,Image.SCALE_SMOOTH));
                            objects[i][0] = imageIcon;
                        }
                        else {
                        objects[i][0] = null;}
                        objects[i][1] = birthDayList.get(i).firstName();
                        objects[i][2] = birthDayList.get(i).lastName();
                        objects[i][3] = birthDayList.get(i).address();
                        objects[i][4] = birthDayList.get(i).dateBorn();
                        objects[i][5] = birthDayList.get(i).status();
                        objects[i][6] = birthDayList.get(i).phone();
                        objects[i][7] = birthDayList.get(i).sex();
                    }
                    columnObject = new String[]{"MemberPhoto", "FirstName", "LastName", "Address", "DateBorn",
                            "Status", "Phone","SEX" };
                //    imageDisplayClass = new ImageDisplayClass(objects,columnObject);
               //     imageDisplayClass.getBirthDaysWorkerList();
                //    resultTable = new JTable(imageDisplayClass);
                    resultTable.setRowHeight(120);
                    resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
                    add(new JScrollPane(resultTable), "Center");
                //    TableRowSorter<TableModel> sorter = new TableRowSorter(imageDisplayClass);
                //    resultTable.setRowSorter(sorter);




                    printNumber.setEnabled(true);
                }
            });
            printNumber.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    List<String> stringList = new ArrayList<>();
                    List<BirthDayMember> list = new ArrayList<>();
                    String[] birthArray = new String[list.size()];
                    list = churchService.getBirthDaysWorkerList();
                    for (int x = 0; x < list.size(); x++) {
                        stringList.add(list.get(x).phone());
                    }
                    birthArray = stringList.toArray(birthArray);
                    createPhoneTxtFile(birthArray);
                    JOptionPane.showMessageDialog((Component) null, "Operation Successful ");
                }
            });
            print.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    MessageFormat headerFormat = new MessageFormat("");
                    MessageFormat footerFormat = new MessageFormat("Page-{0}");

                    try {
                        resultTable.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat, true,
                                (PrintRequestAttributeSet) null, true);
                    } catch (HeadlessException sql) {
                        sql.printStackTrace();
                    } catch (PrinterException sql) {
                        sql.printStackTrace();
                    }
                }
            });
            setSize(1400, 550);
            setLocation(50, 150);
        }
    }

}

