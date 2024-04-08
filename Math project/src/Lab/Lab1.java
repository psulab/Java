/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab;

import java.awt.Color;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Math.log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Gesser
 */
public class Lab1 extends javax.swing.JFrame {

    public ArrayList<RecIntegral> arrRecIntegral = new ArrayList<>(8);
    public int numberLength;
    public boolean isPoint;
    
//    public static Semaphore sem = new Semaphore(1);
//    public static int index;
//    public static double upperBound;
//    public static double bottomLine;
//    public static double step;
//    public static double res;

    private void initializationArrayList() {
        for (int i = 0; i < 8; i++) {
            arrRecIntegral.add(null);
        }
    }

    /**
     * Creates new form Lab1
     */
    public Lab1() {
        initComponents();
        initializationArrayList();
        jLabel6.setVisible(false);
        jPanel6.setVisible(false);
        jPanel9.setVisible(false);
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // запрет на выделение интервала в таблице
    }

///////////////////////////// My functions /////////////////////////////////////
    /* Записать данные в таблицу */
    private void addtoTable(RecIntegral ri, int row) {
        jTable2.setValueAt(ri.getUpperBound(), row, 0);
        jTable2.setValueAt(ri.getBottomLine(), row, 1);
        jTable2.setValueAt(ri.getStep(), row, 2);
    }
    
    /* Удалить данные из таблицы */
    private void deleteRow() {
        for (int i = 0; i < 4; i++) {
            jTable2.setValueAt(null, jTable2.getSelectedRow(), i);
        }
    }
    
    /* Форматирование текстовых полей */
    private void formattingTextFields() {
        jTextField5.setText(null);
        jTextField1.setText(null);
        jTextField4.setText(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel7.setLocation(50, 111);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel8.setLocation(50, 161);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel9.setLocation(50, 211);
    }
    
    /* Обработка ввода из файла */
    private boolean fileInputProcessing(int symbol) {
        if (8 == numberLength) {
            if (48 <= symbol && symbol <= 57) {
                numberLength -= 2;
                return true;
            } else if ('+' == (char) symbol) {
                numberLength--;
                return true;
            }
        } else if (0 != numberLength) {
            if (48 <= symbol && symbol <= 57) {
                numberLength--;
                return true;
            } else if (isPoint && '.' == (char) symbol) {
                numberLength = 6;
                isPoint = false;
                return true;
            }
        } else {
            if (isPoint && '.' == (char) symbol) {
                numberLength = 6;
                isPoint = false;
                return true;
            }
        }
        return false;
    }
    
    /* Запись данных в таблицу */
    private boolean writeDataTable(String upperBound, String bottomLine, String step) {
        for (int i = 0; i < 8; i++) {
            if (arrRecIntegral.get(i) == null) {
                RecIntegral ri = new RecIntegral();
                try {
                    ri.setInputRow(upperBound, bottomLine, step); //инициализация класса
                    //arrRecIntegral.add(i, ri); //добавление данных в колекцию
                    arrRecIntegral.set(i, ri); //добавление данных в колекцию
                    addtoTable(arrRecIntegral.get(i), i); //запись данных в таблицу из колекции
                    formattingTextFields();
                    return false;
                } catch (RecIntegralException rie) {
                    jLabel10.setText(rie.getMessage());
                    jPanel6.setVisible(true);
                    formattingTextFields();
                    return false;
                }
            }
        }
        return true;
    }
////////////////////////////////////////////////////////////////////////////////    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton_copy = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(56, 59, 90));
        jPanel3.setPreferredSize(new java.awt.Dimension(752, 50));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(56, 59, 90));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/copyg.png"))); // NOI18N
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setOpaque(true);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 10, 30, 30));

        jButton2.setBackground(new java.awt.Color(56, 59, 90));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/cancelg.png"))); // NOI18N
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setOpaque(true);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 10, 30, 30));

        jButton_copy.setBackground(new java.awt.Color(17, 191, 159));
        jButton_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/cancel.png"))); // NOI18N
        jButton_copy.setAlignmentY(0.0F);
        jButton_copy.setBorder(null);
        jButton_copy.setContentAreaFilled(false);
        jButton_copy.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel3.add(jButton_copy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        jLabel12.setBackground(new java.awt.Color(56, 59, 90));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(242, 234, 223));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("ath project");
        jLabel12.setFocusable(false);
        jLabel12.setOpaque(true);
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 10, 100, 30));

        jLabel13.setBackground(new java.awt.Color(56, 59, 90));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(242, 234, 223));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/wattpad.png"))); // NOI18N
        jLabel13.setFocusable(false);
        jLabel13.setOpaque(true);
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 30, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 712, 50));

        jPanel1.setBackground(new java.awt.Color(56, 59, 90));
        jPanel1.setPreferredSize(new java.awt.Dimension(752, 372));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(56, 59, 90));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 78, 115)));
        jPanel6.setLayout(null);

        jLabel10.setBackground(new java.awt.Color(56, 59, 90));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(242, 234, 223));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setFocusable(false);
        jPanel6.add(jLabel10);
        jLabel10.setBounds(45, 0, 245, 60);

        jLabel11.setBackground(new java.awt.Color(217, 78, 115));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(242, 234, 223));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/cancel.png"))); // NOI18N
        jLabel11.setFocusable(false);
        jLabel11.setOpaque(true);
        jPanel6.add(jLabel11);
        jLabel11.setBounds(15, 15, 30, 30);

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 277, 290, 60));

        jPanel4.setBackground(new java.awt.Color(17, 191, 159));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setBackground(new java.awt.Color(56, 59, 90));
        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(242, 234, 223));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setFocusable(false);
        jTable2.setGridColor(new java.awt.Color(17, 191, 159));
        jTable2.setOpaque(false);
        jTable2.setRowHeight(30);
        jTable2.setSelectionBackground(new java.awt.Color(74, 76, 105));
        jTable2.setSelectionForeground(new java.awt.Color(242, 234, 223));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jPanel4.add(jTable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 31, 400, 240));

        jLabel1.setBackground(new java.awt.Color(17, 191, 159));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 234, 223));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Upper bound");
        jLabel1.setFocusable(false);
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 100, 30));

        jLabel2.setBackground(new java.awt.Color(17, 191, 159));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 234, 223));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bottom line");
        jLabel2.setFocusable(false);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 1, 100, 30));

        jLabel3.setBackground(new java.awt.Color(17, 191, 159));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 234, 223));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Step");
        jLabel3.setToolTipText("");
        jLabel3.setFocusable(false);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 1, 100, 30));

        jLabel4.setBackground(new java.awt.Color(17, 191, 159));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 234, 223));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Result");
        jLabel4.setToolTipText("");
        jLabel4.setFocusable(false);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 1, 100, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 402, 272));

        jPanel5.setBackground(new java.awt.Color(56, 59, 90));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 350));
        jPanel5.setLayout(null);

        jLabel7.setBackground(new java.awt.Color(56, 59, 90));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(242, 234, 223));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Enter the upper bound");
        jLabel7.setFocusable(false);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel7);
        jLabel7.setBounds(50, 111, 160, 30);

        jTextField4.setBackground(new java.awt.Color(56, 59, 90));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(242, 234, 223));
        jTextField4.setAlignmentY(0.0F);
        jTextField4.setBorder(null);
        jTextField4.setCaretColor(new java.awt.Color(242, 234, 223));
        jTextField4.setDisabledTextColor(new java.awt.Color(242, 234, 223));
        jTextField4.setNextFocusableComponent(jTextField1);
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        jPanel5.add(jTextField4);
        jTextField4.setBounds(50, 111, 160, 30);

        jPanel7.setBackground(new java.awt.Color(17, 191, 159));
        jPanel7.setAlignmentX(0.0F);
        jPanel7.setAlignmentY(0.0F);
        jPanel7.setPreferredSize(new java.awt.Dimension(280, 2));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7);
        jPanel7.setBounds(50, 141, 160, 1);

        jLabel8.setBackground(new java.awt.Color(56, 59, 90));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(242, 234, 223));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Enter a lower bound");
        jLabel8.setFocusable(false);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8);
        jLabel8.setBounds(50, 161, 160, 30);

        jTextField1.setBackground(new java.awt.Color(56, 59, 90));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(242, 234, 223));
        jTextField1.setAlignmentY(0.0F);
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(242, 234, 223));
        jTextField1.setDisabledTextColor(new java.awt.Color(242, 234, 223));
        jTextField1.setNextFocusableComponent(jTextField5);
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel5.add(jTextField1);
        jTextField1.setBounds(50, 161, 160, 30);

        jPanel2.setBackground(new java.awt.Color(17, 191, 159));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setPreferredSize(new java.awt.Dimension(280, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel2);
        jPanel2.setBounds(50, 191, 160, 1);

        jLabel9.setBackground(new java.awt.Color(56, 59, 90));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(242, 234, 223));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Enter step");
        jLabel9.setFocusable(false);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel9);
        jLabel9.setBounds(50, 211, 160, 30);

        jTextField5.setBackground(new java.awt.Color(56, 59, 90));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(242, 234, 223));
        jTextField5.setAlignmentY(0.0F);
        jTextField5.setBorder(null);
        jTextField5.setCaretColor(new java.awt.Color(242, 234, 223));
        jTextField5.setDisabledTextColor(new java.awt.Color(242, 234, 223));
        jTextField5.setNextFocusableComponent(jTextField4);
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });
        jPanel5.add(jTextField5);
        jTextField5.setBounds(50, 211, 160, 30);

        jPanel8.setBackground(new java.awt.Color(17, 191, 159));
        jPanel8.setAlignmentX(0.0F);
        jPanel8.setAlignmentY(0.0F);
        jPanel8.setPreferredSize(new java.awt.Dimension(280, 2));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel8);
        jPanel8.setBounds(50, 241, 160, 1);

        jButton5.setBackground(new java.awt.Color(56, 59, 90));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/trashg.png"))); // NOI18N
        jButton5.setAlignmentY(0.0F);
        jButton5.setBorder(null);
        jButton5.setContentAreaFilled(false);
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.setOpaque(true);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(96, 251, 30, 30);

        jButton6.setBackground(new java.awt.Color(56, 59, 90));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/addg.png"))); // NOI18N
        jButton6.setAlignmentY(0.0F);
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.setOpaque(true);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(134, 251, 30, 30);

        jButton7.setBackground(new java.awt.Color(56, 59, 90));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/playbuttong.png"))); // NOI18N
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton7.setOpaque(true);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);
        jButton7.setBounds(172, 251, 30, 30);

        jPanel9.setBackground(new java.awt.Color(56, 59, 90));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 191, 159)));
        jPanel9.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(56, 59, 90));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 191, 159)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel10);
        jPanel10.setBounds(30, -1, 116, 32);

        jLabel14.setBackground(new java.awt.Color(56, 59, 90));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(242, 234, 223));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/folderbb.png"))); // NOI18N
        jLabel14.setFocusable(false);
        jPanel9.add(jLabel14);
        jLabel14.setBounds(0, 0, 30, 30);

        jButton9.setBackground(new java.awt.Color(56, 59, 90));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(242, 234, 223));
        jButton9.setText("    Open file...");
        jButton9.setAlignmentY(0.0F);
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.setOpaque(true);
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9);
        jButton9.setBounds(1, 39, 142, 30);

        jButton10.setBackground(new java.awt.Color(56, 59, 90));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(242, 234, 223));
        jButton10.setText("    Save file...");
        jButton10.setAlignmentY(0.0F);
        jButton10.setBorder(null);
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton10.setOpaque(true);
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton10);
        jButton10.setBounds(1, 69, 142, 30);

        jPanel5.add(jPanel9);
        jPanel9.setBounds(58, 251, 144, 107);

        jButton8.setBackground(new java.awt.Color(56, 59, 90));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab/images/folderg.png"))); // NOI18N
        jButton8.setAlignmentY(0.0F);
        jButton8.setBorder(null);
        jButton8.setContentAreaFilled(false);
        jButton8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton8.setOpaque(true);
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);
        jButton8.setBounds(58, 251, 30, 30);

        jLabel6.setBackground(new java.awt.Color(56, 59, 90));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(242, 234, 223));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 191, 159)));
        jLabel6.setFocusable(false);
        jPanel5.add(jLabel6);
        jLabel6.setBounds(58, 281, 144, 30);

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 0, 260, 372));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 712, 372));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /* Закрыть / свернуть */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setExtendedState(Frame.ICONIFIED);
    }//GEN-LAST:event_jButton1ActionPerformed

    /* Наведение на кнопку свернуть */
    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setOpaque(true);
        jButton1.setIcon(new ImageIcon(getClass().getResource("/Lab/images/copy.png")));
        jButton1.setBackground(new Color(17, 191, 159));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setOpaque(true);
        jButton1.setBackground(new Color(56, 59, 90));
        jButton1.setIcon(new ImageIcon(getClass().getResource("/Lab/images/copyg.png")));
    }//GEN-LAST:event_jButton1MouseExited

    /* Наведение на кнопку закрыть */
    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setOpaque(true);
        jButton2.setIcon(new ImageIcon(getClass().getResource("/Lab/images/cancel.png")));
        jButton2.setBackground(new Color(17, 191, 159));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setOpaque(true);
        jButton2.setBackground(new Color(56, 59, 90));
        jButton2.setIcon(new ImageIcon(getClass().getResource("/Lab/images/cancelg.png")));
    }//GEN-LAST:event_jButton2MouseExited

    /* В фокусе Textbox */
    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        numberLength = 8;
        isPoint = true;

        for (int i = 0; i < jTextField4.getText().length(); i++) {
            if ((0 == i) && (48 <= (int) jTextField4.getText().charAt(i) && (int) jTextField4.getText().charAt(i) <= 57)) {
                numberLength -= 2;
            } else if (jTextField4.getText().charAt(i) == '.') {
                numberLength = 6;
                isPoint = false;
            } else {
                numberLength--;
            }
        }

        jTable2.getSelectionModel().clearSelection();
        jPanel9.setVisible(false);
        jButton8.setVisible(true);
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel7.setLocation(50, 91);
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        numberLength = 8;
        isPoint = true;

        for (int i = 0; i < jTextField1.getText().length(); i++) {
            if ((0 == i) && (48 <= (int) jTextField1.getText().charAt(i) && (int) jTextField1.getText().charAt(i) <= 57)) {
                numberLength -= 2;
            } else if (jTextField1.getText().charAt(i) == '.') {
                numberLength = 6;
                isPoint = false;
            } else {
                numberLength--;
            }
        }

        jTable2.getSelectionModel().clearSelection();
        jPanel9.setVisible(false);
        jButton8.setVisible(true);
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel8.setLocation(50, 141);
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        numberLength = 8;
        isPoint = true;

        for (int i = 0; i < jTextField5.getText().length(); i++) {
            if ((0 == i) && (48 <= (int) jTextField5.getText().charAt(i) && (int) jTextField5.getText().charAt(i) <= 57)) {
                numberLength -= 2;
            } else if (jTextField5.getText().charAt(i) == '.') {
                numberLength = 6;
                isPoint = false;
            } else {
                numberLength--;
            }
        }

        jTable2.getSelectionModel().clearSelection();
        jPanel9.setVisible(false);
        jButton8.setVisible(true);
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel9.setLocation(50, 191);
    }//GEN-LAST:event_jTextField5FocusGained

    /* Вне фокуса Textbox */
    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        jPanel6.setVisible(false);
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));

        if (0 == jTextField4.getText().length()) {
            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14));
            jLabel7.setLocation(50, 111);
        }
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        jPanel6.setVisible(false);
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));

        if (0 == jTextField1.getText().length()) {
            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14));
            jLabel8.setLocation(50, 161);
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        jPanel6.setVisible(false);
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));

        if (0 == jTextField5.getText().length()) {
            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14));
            jLabel9.setLocation(50, 211);
        }
    }//GEN-LAST:event_jTextField5FocusLost

    /* Кнопка Play(вычислить) */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jTable2.getSelectionModel().clearSelection();
        boolean isEmpty = true;
        for (int i = 0; i < 8; i++) {
            if ((jTable2.getValueAt(i, 0) != null) && (jTable2.getValueAt(i, 3) == null)) {
                                
                /* Создание клиента */
                Thread udpThread = new Thread(new UdpClient(), "UdpClient");
                udpThread.start();
                
                try(DatagramSocket serverSocket = new DatagramSocket(50000)){
                    
                    for (int j = 0; j < 3; j++) {
                        /* Отправка данных клиенту */
                        String strValue = jTable2.getValueAt(i, j).toString();
                        DatagramPacket dp = new DatagramPacket(strValue.getBytes(),strValue.length(),InetAddress.getLocalHost(),50001);
                        serverSocket.send(dp);
                    }

                    /* Получение данных от клиента */
                    byte[] buffer = new byte[256];
                    DatagramPacket dp = new DatagramPacket(buffer, 0, buffer.length);
                    serverSocket.receive(dp);

                    String strValue = new String(dp.getData(), 0, dp.getLength());
                    jTable2.setValueAt(Double.parseDouble(strValue), i, 3);

                    serverSocket.close();
                    
                } catch (SocketException ex) {
                    Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //jTable2.setValueAt(res, i, 3);
                isEmpty = false;
            }
        }

        if (isEmpty) {
            jLabel10.setText("No suitable values found for calculation");
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setBackground(new Color(17, 191, 159));
        jButton7.setIcon(new ImageIcon(getClass().getResource("/Lab/images/playbutton.png")));
        jPanel9.setVisible(false);
        jButton8.setVisible(true);
        jLabel6.setText("Calculate values");
        jLabel6.setVisible(true);
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setBackground(new Color(56, 59, 90));
        jButton7.setIcon(new ImageIcon(getClass().getResource("/Lab/images/playbuttong.png")));
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));
        jLabel6.setVisible(false);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton7MouseExited

    /* Кнопка Add */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTable2.getSelectionModel().clearSelection();
        if (jTextField4.getText().isEmpty() || jTextField1.getText().isEmpty() || jTextField5.getText().isEmpty()) {
            jLabel10.setText("Text field is empty: please enter a value");
            jPanel6.setVisible(true);
        } else {

            boolean isFull = writeDataTable(jTextField4.getText(), jTextField1.getText(), jTextField5.getText());

            if (isFull) {
                jLabel10.setText("<html><p>The table is full: please delete unnecessary</p><p>data and click add again</p></html>");
                jPanel6.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setBackground(new Color(17, 191, 159));
        jButton6.setIcon(new ImageIcon(getClass().getResource("/Lab/images/add.png")));
        jPanel9.setVisible(false);
        jButton8.setVisible(true);
        jLabel6.setText("Add values");
        jLabel6.setVisible(true);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        jButton6.setBackground(new Color(56, 59, 90));
        jButton6.setIcon(new ImageIcon(getClass().getResource("/Lab/images/addg.png")));
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));
        jLabel6.setVisible(false);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton6MouseExited

    /* Кнопка Trash(удалить) */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            if (jTable2.getValueAt(jTable2.getSelectedRow(), 0) != null) {
                arrRecIntegral.set(jTable2.getSelectedRow(), null);
                deleteRow();
                jTable2.getSelectionModel().clearSelection();
            } else {
                jTable2.getSelectionModel().clearSelection();
                jLabel10.setText("<html><p>The selected row is empty: please select</p><p>the completed row and click delete</p></html>");
                jPanel6.setVisible(true);
            }
        } catch (Exception e) {
            jLabel10.setText("<html><p>Row not selected: please select a row</p><p>and click delete</p></html>");
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        jButton5.setBackground(new Color(17, 191, 159));
        jButton5.setIcon(new ImageIcon(getClass().getResource("/Lab/images/trash.png")));
        jPanel9.setVisible(false);
        jButton8.setVisible(true);
        jLabel6.setText("Delete values");
        jLabel6.setVisible(true);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setBackground(new Color(56, 59, 90));
        jButton5.setIcon(new ImageIcon(getClass().getResource("/Lab/images/trashg.png")));
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));
        jLabel6.setVisible(false);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton5MouseExited

    /* Фокус на TextBox */
    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel8.setLocation(50, 141);
        jTextField1.requestFocus();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel7.setLocation(50, 91);
        jTextField4.requestFocus();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel9.setLocation(50, 191);
        jTextField5.requestFocus();
    }//GEN-LAST:event_jLabel9MouseClicked

    /* Обработка ввода TextBox */
    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        jPanel6.setVisible(false);
        jPanel7.setBackground(new Color(17, 191, 159));

        if (8 == numberLength) {
            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
                numberLength -= 2;
            } else if ('+' == evt.getKeyChar() || '-' == evt.getKeyChar()) {
                numberLength--;
            } else {
                if (8 != (int) evt.getKeyChar()) {
                    jLabel10.setText("<html><p>Invalid input:</p><p>available characters - &#8243 [1 ~ 9] [ &#177 ] [ . ] &#8243</p></html>");
                    jPanel7.setBackground(new Color(217, 78, 115));
                    jPanel6.setVisible(true);
                }
                evt.consume();
            }
        } else if (0 < numberLength) {
            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
                numberLength--;
            } else if (isPoint && '.' == evt.getKeyChar()) {
                numberLength = 6;
                isPoint = false;
            } else {
                if (8 != (int) evt.getKeyChar()) {
                    jLabel10.setText("<html><p>Invalid input:</p><p>available characters - &#8243 [1 ~ 9] [ &#177 ] [ . ] &#8243</p></html>");
                    jPanel7.setBackground(new Color(217, 78, 115));
                    jPanel6.setVisible(true);
                }
                evt.consume();
            }
        } else {
            if (((jTextField4.getText().length() - jTextField4.getCaretPosition()) <= 6) && (isPoint && '.' == evt.getKeyChar())) {
                numberLength = 6;
                numberLength -= jTextField4.getText().length() - jTextField4.getCaretPosition();
                isPoint = false;
            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        jPanel6.setVisible(false);
        jPanel2.setBackground(new Color(17, 191, 159));

        if (8 == numberLength) {
            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
                numberLength -= 2;
            } else if ('+' == evt.getKeyChar() || '-' == evt.getKeyChar()) {
                numberLength--;
            } else {
                if (8 != (int) evt.getKeyChar()) {
                    jLabel10.setText("<html><p>Invalid input:</p><p>available characters - &#8243 [1 ~ 9] [ &#177 ] [ . ] &#8243</p></html>");
                    jPanel2.setBackground(new Color(217, 78, 115));
                    jPanel6.setVisible(true);
                }
                evt.consume();
            }
        } else if (0 < numberLength) {
            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
                numberLength--;
            } else if (isPoint && '.' == evt.getKeyChar()) {
                numberLength = 6;
                isPoint = false;
            } else {
                if (8 != (int) evt.getKeyChar()) {
                    jLabel10.setText("<html><p>Invalid input:</p><p>available characters - &#8243 [1 ~ 9] [ &#177 ] [ . ] &#8243</p></html>");
                    jPanel2.setBackground(new Color(217, 78, 115));
                    jPanel6.setVisible(true);
                }
                evt.consume();
            }
        } else {
            if (((jTextField1.getText().length() - jTextField1.getCaretPosition()) <= 6) && (isPoint && '.' == evt.getKeyChar())) {
                numberLength = 6;
                numberLength -= jTextField1.getText().length() - jTextField1.getCaretPosition();
                isPoint = false;
            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        jPanel6.setVisible(false);
        jPanel8.setBackground(new Color(17, 191, 159));

        if (8 == numberLength) {
            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
                numberLength -= 2;
            } else if ('+' == evt.getKeyChar() || '-' == evt.getKeyChar()) {
                numberLength--;
            } else {
                if (8 != (int) evt.getKeyChar()) {
                    jLabel10.setText("<html><p>Invalid input:</p><p>available characters - &#8243 [1 ~ 9] [ &#177 ] [ . ] &#8243</p></html>");
                    jPanel8.setBackground(new Color(217, 78, 115));
                    jPanel6.setVisible(true);
                }
                evt.consume();
            }
        } else if (0 < numberLength) {
            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
                numberLength--;
            } else if (isPoint && '.' == evt.getKeyChar()) {
                numberLength = 6;
                isPoint = false;
            } else {
                if (8 != (int) evt.getKeyChar()) {
                    jLabel10.setText("<html><p>Invalid input:</p><p>available characters - &#8243 [1 ~ 9] [ &#177 ] [ . ] &#8243</p></html>");
                    jPanel8.setBackground(new Color(217, 78, 115));
                    jPanel6.setVisible(true);
                }
                evt.consume();
            }
        } else {
            if (((jTextField5.getText().length() - jTextField5.getCaretPosition()) <= 6) && (isPoint && '.' == evt.getKeyChar())) {
                numberLength = 6;
                numberLength -= jTextField5.getText().length() - jTextField5.getCaretPosition();
                isPoint = false;
            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    /* Обработка ввода TextBox(удаление) */
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if (!jTextField4.getText().isEmpty() && 8 == (int) evt.getKeyChar()) {
            if ('.' == jTextField4.getText().charAt(jTextField4.getCaretPosition() - 1)) {
                jTextField4.setText(jTextField4.getText().substring(0, jTextField4.getCaretPosition()));
                numberLength = 8 - (jTextField4.getCaretPosition() - 1);

                if (!('+' == jTextField4.getText().charAt(0) || '-' == jTextField4.getText().charAt(0))) {
                    numberLength--;
                }

                isPoint = true;

            } else {
                if (jTextField4.getText().length() == 1) {
                    numberLength = 8;
                } else {
                    numberLength++;
                }
            }
        } else if (127 == (int) evt.getKeyChar()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (!jTextField1.getText().isEmpty() && 8 == (int) evt.getKeyChar()) {
            if ('.' == jTextField1.getText().charAt(jTextField1.getCaretPosition() - 1)) {
                jTextField1.setText(jTextField1.getText().substring(0, jTextField1.getCaretPosition()));
                numberLength = 8 - (jTextField1.getCaretPosition() - 1);

                if (!('+' == jTextField1.getText().charAt(0) || '-' == jTextField1.getText().charAt(0))) {
                    numberLength--;
                }

                isPoint = true;

            } else {
                if (jTextField1.getText().length() == 1) {
                    numberLength = 8;
                } else {
                    numberLength++;
                }
            }
        } else if (127 == (int) evt.getKeyChar()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if (!jTextField5.getText().isEmpty() && 8 == (int) evt.getKeyChar()) {
            if ('.' == jTextField5.getText().charAt(jTextField5.getCaretPosition() - 1)) {
                jTextField5.setText(jTextField5.getText().substring(0, jTextField5.getCaretPosition()));
                numberLength = 8 - (jTextField5.getCaretPosition() - 1);

                if (!('+' == jTextField5.getText().charAt(0) || '-' == jTextField5.getText().charAt(0))) {
                    numberLength--;
                }

                isPoint = true;

            } else {
                if (jTextField5.getText().length() == 1) {
                    numberLength = 8;
                } else {
                    numberLength++;
                }
            }
        } else if (127 == (int) evt.getKeyChar()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    /* Меню чтения/сохранения в фаил */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTable2.getSelectionModel().clearSelection();
        jPanel9.setVisible(true);
        jButton8.setVisible(false);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jButton8.setBackground(new Color(17, 191, 159));
        jButton8.setIcon(new ImageIcon(getClass().getResource("/Lab/images/folder.png")));
        jLabel6.setText("From file");
        jLabel6.setVisible(true);
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        jButton8.setBackground(new Color(56, 59, 90));
        jButton8.setIcon(new ImageIcon(getClass().getResource("/Lab/images/folderg.png")));
        jPanel2.setBackground(new Color(17, 191, 159));
        jPanel7.setBackground(new Color(17, 191, 159));
        jPanel8.setBackground(new Color(17, 191, 159));
        jLabel6.setVisible(false);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton8MouseExited

    /* Открыть фаил */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jTable2.getSelectionModel().clearSelection();
        jPanel9.setVisible(false);
        jButton8.setVisible(true);

        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { // выбор файла в диалоговом окне прошел успешно
            File file = fc.getSelectedFile();
            String fileName = file.getAbsolutePath();

            if (fileName.endsWith(".txt")) {
                try (FileReader fr = new FileReader(fileName)) {

                    numberLength = 8;
                    isPoint = true;

                    int buffer;
                    int index = 0;
                    String[] boundariesIntegration = new String[]{"", "", ""};

                    boolean isDelimiter = true;

                    while ((buffer = fr.read()) != -1) {

                        if (fileInputProcessing(buffer)) {
                            boundariesIntegration[index] += (char) buffer;
                            isDelimiter = true;
                        }

                        if (isDelimiter && (' ' == (char) buffer || '\n' == (char) buffer)) {
                            index++;
                            numberLength = 8;
                            isPoint = true;
                            isDelimiter = false;

                            if (!(boundariesIntegration[0].isEmpty() || boundariesIntegration[1].isEmpty() || boundariesIntegration[2].isEmpty())) {
                                boolean isFull = writeDataTable(boundariesIntegration[0], boundariesIntegration[1], boundariesIntegration[2]);

                                if (isFull) {
                                    jLabel10.setText("<html><p>The table is full: please delete unnecessary</p><p>data and click add again</p></html>");
                                    jPanel6.setVisible(true);
                                    break;
                                } else {
                                    index = 0;
                                    numberLength = 8;
                                    isPoint = true;
                                    boundariesIntegration = new String[]{"", "", ""};
                                }
                            }
                        }
                    }

                    if (!(boundariesIntegration[0].isEmpty() || boundariesIntegration[1].isEmpty() || boundariesIntegration[2].isEmpty())) {
                        boolean isFull = writeDataTable(boundariesIntegration[0], boundariesIntegration[1], boundariesIntegration[2]);

                        if (isFull) {
                            jLabel10.setText("<html><p>The table is full: please delete unnecessary</p><p>data and click add again</p></html>");
                            jPanel6.setVisible(true);
                        }
                    }

                } catch (Exception ex) {
                    jLabel10.setText("<html><p>An error occurred while opening the file</p></html>");
                    jPanel6.setVisible(true);
                }
            } else {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                    ArrayList<RecIntegral> arrRecIntegralCopy = (ArrayList<RecIntegral>) ois.readObject(); //запись обьета в новый список

                    for (int i = 0; i < 8; i++) {
                        if (arrRecIntegral.get(i) == null) {
                            for (int j = 0; j < 8; j++) {
                                if (arrRecIntegralCopy.get(j) != null) {
                                    arrRecIntegral.set(i, arrRecIntegralCopy.get(j)); //добавление данных в колекцию
                                    addtoTable(arrRecIntegral.get(i), i); //запись данных в таблицу из колекции
                                    arrRecIntegralCopy.set(j, null);
                                    formattingTextFields();
                                    break;
                                }
                            }
                        }
                    }

                } catch (Exception ex) {
                    jLabel10.setText("<html><p>An error occurred while opening the file</p></html>");
                    jPanel6.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        jButton9.setBackground(new Color(74, 76, 105));
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        jButton9.setBackground(new Color(56, 59, 90));
    }//GEN-LAST:event_jButton9MouseExited

    /* Сохранить фаил */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jTable2.getSelectionModel().clearSelection();
        jPanel9.setVisible(false);
        jButton8.setVisible(true);

        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { // выбор файла в диалоговом окне прошел успешно
            File file = fc.getSelectedFile();
            String fileName = file.getAbsolutePath();

            if (fileName.endsWith(".txt")) {
                try (FileWriter fw = new FileWriter(fileName)) {

                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (jTable2.getValueAt(i, j) != null) {
                                fw.write(jTable2.getValueAt(i, j) + " ");
                            }
                        }
                        fw.append('\n');
                        fw.flush();
                    }

                } catch (Exception ex) {
                    jLabel10.setText("<html><p>An error occurred while saving the file</p></html>");
                    jPanel6.setVisible(true);
                }
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(arrRecIntegral);
                } catch (Exception ex) {
                    jLabel10.setText("<html><p>An error occurred while saving the file</p></html>");
                    jPanel6.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        jButton10.setBackground(new Color(74, 76, 105));
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        jButton10.setBackground(new Color(56, 59, 90));
    }//GEN-LAST:event_jButton10MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Lab1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_copy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}

//jTable2.setValueAt(res, i, 3);
//                if ((!jTextField4.getText().isEmpty() && !jTextField1.getText().isEmpty() && !jTextField5.getText().isEmpty()) && (jTable2.getValueAt(i, 0) == null)) {
//                    RecIntegral ri = new RecIntegral();
//
//                    try {
//                        ri.setInputRow(jTextField4.getText(), jTextField1.getText(), jTextField5.getText());    //инициализация класса
//                        arrRecIntegral.add(i, ri);  //добавление данных в колекцию
//                        addtoTable(arrRecIntegral.get(i), i);   //запись данных в таблицу из колекции
//                        formattingTextFields();
//                        break;
//                    } catch (RecIntegralException rie) {
//                        jLabel10.setText(rie.getMessage());
//                        jPanel6.setVisible(true);
//                        formattingTextFields();
//                    }
//
//                }
//        if (8 == numberLength) {
//            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
//                numberLength -= 2;
//            } else if ('+' == evt.getKeyChar() || '-' == evt.getKeyChar()) {
//                numberLength--;
//            } else {
//                evt.consume();
//            }
//        } else if (0 != numberLength) {
//            if (48 <= (int) evt.getKeyChar() && (int) evt.getKeyChar() <= 57) {
//                numberLength--;
//            } else if (isPoint && '.' == evt.getKeyChar()) {
//                numberLength = 6;
//                isPoint = false;
//            } else {
//                evt.consume();
//            }
//        } else {
//            if (isPoint && '.' == evt.getKeyChar()) {
//                numberLength = 6;
//                isPoint = false;
//            } else {
//                evt.consume();
//            }
//        }
//                if ('.' == jTextField4.getText().charAt(jTextField4.getCaretPosition())) {
//                    jTextField4.setText(jTextField4.getText().substring(0, jTextField4.getCaretPosition()));
//                    numberLength = 8 - jTextField4.getCaretPosition();
//
//                    if (!('+' == jTextField4.getText().charAt(0) || '-' == jTextField4.getText().charAt(0))) {
//                        numberLength--;
//                    }
//
//                    isPoint = true;
//                } else {
//                    if (jTextField4.getText().length() == 1) {
//                        numberLength = 8;
//                    } else {
//                        numberLength++;
//                    }
//                }

/* Кнопка Add */
//for (int i = 0; i < 8; i++) {
//                if (jTable2.getValueAt(i, 0) == null) {
//                    RecIntegral ri = new RecIntegral();
//
//                    try {
//                        ri.setInputRow(jTextField4.getText(), jTextField1.getText(), jTextField5.getText());    //инициализация класса
//                        arrRecIntegral.add(i, ri);  //добавление данных в колекцию
//                        addtoTable(arrRecIntegral.get(i), i);   //запись данных в таблицу из колекции
//                        formattingTextFields();
//                        isFull = false;
//                        break;
//                    } catch (RecIntegralException rie) {
//                        jLabel10.setText(rie.getMessage());
//                        jPanel6.setVisible(true);
//                        formattingTextFields();
//                        isFull = false;
//                        break;
//                    }
//
//                }
//            }
//try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//                    numberLength = 8;
//                    isPoint = true;
//
//                    boolean isFull = true;
//                    boolean errorReading = true;
//                    int symbol;
//
//                    int index = 0;
//                    String[] boundariesIntegration = new String[]{"", "", ""};
//
//                    while ((symbol = br.read()) != -1) {
//                        fileInputProcessing(symbol, boundariesIntegration, index);
//                        
//                        if (' ' == (char) symbol || '\n' == (char) symbol) {
//                            if (!(boundariesIntegration[0].isEmpty() || boundariesIntegration[1].isEmpty() || boundariesIntegration[2].isEmpty())) {
//                                isFull = writeDataTable(boundariesIntegration[0], boundariesIntegration[1], boundariesIntegration[2]);
//
//                                index = 0;
//                                numberLength = 8;
//                                isPoint = true;
//                                errorReading = false;
//                                boundariesIntegration = new String[]{"", "", ""};
//                            } else {
//                                numberLength = 8;
//                                isPoint = true;
//                                index++;
//                            }
//                        }
//                    }
//
//                    if (!(boundariesIntegration[0].isEmpty() || boundariesIntegration[1].isEmpty() || boundariesIntegration[2].isEmpty())) {
//                        isFull = writeDataTable(boundariesIntegration[0], boundariesIntegration[1], boundariesIntegration[2]);
//                    }
//                    
//                    if (errorReading) {
//                        jLabel10.setText("<html><p>File is empty or input error, input format:</p><p>[ word word / word new line word ]</p></html>");
//                        jPanel6.setVisible(true);
//                    } else if (isFull) {
//                        jLabel10.setText("<html><p>The table is full: please delete unnecessary</p><p>data and click add again</p></html>");
//                        jPanel6.setVisible(true);
//                    }
//
//                } catch (IOException ex) {
//                    Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
//                }

/* Формула расчёта интегралла */
//double upperBound = arrRecIntegral.get(i).getUpperBound();
//                double bottomLine = arrRecIntegral.get(i).getBottomLine();
//                double step = arrRecIntegral.get(i).getStep();
//                double res = 0.0;
//
//                upperBound = (upperBound - bottomLine) / step;
//
//                for (int j = 0; j < (int) upperBound; j++, bottomLine += step) {
//                    res += (1 / log(bottomLine) + 1 / log(bottomLine + step)) * step / 2;
//                }

/* Кнопка Play(вычислить) */
//        jTable2.getSelectionModel().clearSelection();
//        boolean isEmpty = true;
//        for (int i = 0; i < 8; i++) {
//            if ((jTable2.getValueAt(i, 0) != null) && (jTable2.getValueAt(i, 3) == null)) {
//                double upperBound = arrRecIntegral.get(i).getUpperBound();
//                double bottomLine = arrRecIntegral.get(i).getBottomLine();
//                double step = arrRecIntegral.get(i).getStep();
//                double res = 0.0;
//
//                upperBound = (upperBound - bottomLine) / step;
//
//                for (int j = 0; j < (int) upperBound; j++, bottomLine += step) {
//                    res += (1 / log(bottomLine) + 1 / log(bottomLine + step)) * step / 2;
//                }
//
//                jTable2.setValueAt(res, i, 3);
//                isEmpty = false;
//            }
//        }
//
//        if (isEmpty) {
//            jLabel10.setText("No suitable values found for calculation");
//            jPanel6.setVisible(true);
//        }


/* Кнопка Play(вычислить) - создание потоков */
//                index = 0;
//                upperBound = arrRecIntegral.get(i).getUpperBound();
//                bottomLine = arrRecIntegral.get(i).getBottomLine();
//                step = arrRecIntegral.get(i).getStep();
//                res = 0.0;
//                                
//                upperBound = (upperBound - bottomLine) / step;
//                
//                Thread[] arrThread = new Thread[7];
//
//                for (int j = 0; j < 7; j++) {
//                    arrThread[j] = new Thread(new MyThread(), "MyThread");
//                    arrThread[j].start();
//                }
//
//                /* Ожидание завершения всех потоков */
//                for (int j = 0; j < 7; j++) {
//                    try {
//                        arrThread[j].join();
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }