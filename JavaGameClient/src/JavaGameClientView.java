
//�������� ä�� â
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class JavaGameClientView extends JFrame {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField txtInput;
   private String UserName;
   private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
   private Socket socket; // �������

   private ObjectInputStream ois;
   private ObjectOutputStream oos;

   private JLabel lblUserName;
   private JTextPane textArea;
   private JPanel myinfo;

   private JButton btnRoomButton_1;
   private JButton btnRoomButton_2;
   private JButton btnRoomButton_3;
   private JButton btnRoomButton_4;
   private JButton btnRoomButton_5;
   private JButton btnRoomButton_6;

   private RoomManager roomManager = new RoomManager();
   // private RoomManager roomManager;

   ImageIcon darkbackground;
   ImageIcon face;
   ImageIcon button;
   ImageIcon titleImg;

   DefaultListModel<String> model;
   // ����Ʈ ����
   private JList list;
   private JPanel panel_1;
   
   // �ű� �߰� 11.22
   private ImageIcon[] icon = new ImageIcon[10];
   
   /**
    * Create the frame.
    * 
    * @throws BadLocationException
    */
   public JavaGameClientView(String username, String ip_addr, String port_no, int num) {
	   
      darkbackground = new ImageIcon("C:/Users/Network/JavaGameClient/images/aqua.jpg");
      face = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_mizu.png");
      button = new ImageIcon("C:/Users/Network/JavaGameClient/images/blue.jpg");
      titleImg = new ImageIcon("C:/Users/Network/JavaGameClient/images/title.png");
      
      // 11.22
      icon[0] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_aka.png");
      icon[1] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_ao.png");
      icon[2] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_ki.png");
      icon[3] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_midori.png");
      icon[4] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_mizu.png");
      icon[5] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_pink.png");
      icon[6] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_shiro.png");
      icon[7] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_shiro2.png");
      
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 645, 614);
      contentPane = new JPanel() {
         public void paintComponent(Graphics g) {
            // g.drawImage(icon4.getImage(), 0, 0, null); // full size
            Dimension d = getSize();
            g.drawImage(darkbackground.getImage(), 0, 0, d.width, d.height, null); // get size

            setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
            super.paintComponent(g);
         }
      };
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(208, 340, 400, 146);
      contentPane.add(scrollPane);

      textArea = new JTextPane();
      textArea.setEditable(true);
      textArea.setFont(new Font("����ü", Font.PLAIN, 14));
      scrollPane.setViewportView(textArea);

      myinfo = new JPanel() {
         public void paintComponent(Graphics g) {
            // g.drawImage(icon4.getImage(), 0, 0, null); // full size
            Dimension d = getSize();
            g.drawImage(icon[num].getImage(), 0, 0, d.width, d.height, null); // get size

            setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
            super.paintComponent(g);
         }
      };
      myinfo.setBackground(Color.WHITE);
      myinfo.setBounds(27, 10, 157, 146);
      contentPane.add(myinfo);

      txtInput = new JTextField();
      txtInput.setBounds(208, 505, 278, 40);
      contentPane.add(txtInput);
      txtInput.setColumns(10);
      setVisible(true);

      AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
      UserName = username;

      
      JButton btnExitButton = new JButton("������");
      btnExitButton.setBackground(new Color(255, 255, 255));
      btnExitButton.setFont(new Font("����", Font.PLAIN, 14));
      btnExitButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
            SendObject(msg);
            System.exit(0);
         }
      });
      btnExitButton.setBounds(498, 504, 110, 40);
      contentPane.add(btnExitButton);

      btnRoomButton_1 = new JButton(button);
      btnRoomButton_1.setBackground(Color.WHITE);
      btnRoomButton_1.setBorderPainted(true);
      btnRoomButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (btnRoomButton_1.getIcon() != null) {
               while (true) 
               {
                  String title = JOptionPane.showInputDialog(null, "�������� �Է����ּ���.", "������",
                        JOptionPane.PLAIN_MESSAGE);
                  if (title == null)
                     break;

                  else {
                     // cm.roomManager[0] = roomManager;
                     // cm.roomManager[0].title = title;
                     ChatMsg cm = new ChatMsg(UserName, "160", title);
                     cm.roomNum = 0;
                     SendObject(cm);
                     
                     room view = new room(username, ip_addr, port_no, 0);
                     setVisible(false);
                     break;
                  }
               }
            }

            else 
            {   
               ChatMsg cm = new ChatMsg(UserName, "180", "JoinRoom");
               cm.roomNum = 0;
               room view = new room(username, ip_addr, port_no, 0);
               setVisible(false);
            }
         }
      });
      btnRoomButton_1.setBounds(208, 85, 192, 71);
      contentPane.add(btnRoomButton_1);

      btnRoomButton_2 = new JButton(button);
      btnRoomButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnRoomButton_2.setBounds(416, 85, 192, 71);
      contentPane.add(btnRoomButton_2);

      btnRoomButton_3 = new JButton(button);
      btnRoomButton_3.setBounds(416, 165, 192, 71);
      contentPane.add(btnRoomButton_3);

      btnRoomButton_4 = new JButton(button);
      btnRoomButton_4.setBounds(208, 165, 192, 71);
      contentPane.add(btnRoomButton_4);

      btnRoomButton_5 = new JButton(button);
      btnRoomButton_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnRoomButton_5.setBounds(416, 246, 192, 71);
      contentPane.add(btnRoomButton_5);

      btnRoomButton_6 = new JButton(button);
      btnRoomButton_6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnRoomButton_6.setBounds(208, 246, 192, 71);
      contentPane.add(btnRoomButton_6);

      JPanel title = new JPanel() {
         public void paintComponent(Graphics g) {
            // g.drawImage(icon4.getImage(), 0, 0, null); // full size
            Dimension d = getSize();
            g.drawImage(titleImg.getImage(), 0, 0, d.width, d.height, null); // get size

            setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
            super.paintComponent(g);
         }

      };

      title.setBackground(Color.WHITE);
      title.setBounds(208, 10, 400, 65);
      contentPane.add(title);

      model = new DefaultListModel<String>();

      list = new JList(model);
      // list.setBorder(null);

      list.setBackground(new Color(0, 191, 255));
      list.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
      getContentPane().add(list);
      list.setBounds(27, 246, 157, 299);
      contentPane.add(list);
      
      JPanel panel = new JPanel();
      panel.setBackground(new Color(255, 0, 0 ,0));
      panel.setBounds(27, 164, 157, 29);
      contentPane.add(panel);
      
            lblUserName = new JLabel("Name");
            panel.add(lblUserName);
            lblUserName.setBackground(Color.WHITE);
            lblUserName.setFont(new Font("Arial", Font.BOLD, 14));
            lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
            lblUserName.setText("[" + username + "]");
            
            panel_1 = new JPanel();
            panel_1.setBounds(27, 217, 157, 29);
            contentPane.add(panel_1);
            
            JLabel lblNewLabel = new JLabel("\uD50C\uB808\uC774\uC5B4");
            panel_1.add(lblNewLabel);

      try {
         socket = new Socket(ip_addr, Integer.parseInt(port_no));
//         is = socket.getInputStream();
//         dis = new DataInputStream(is);
//         os = socket.getOutputStream();
//         dos = new DataOutputStream(os);

         oos = new ObjectOutputStream(socket.getOutputStream());
         oos.flush();
         ois = new ObjectInputStream(socket.getInputStream());

         // SendMessage("/login " + UserName);
         ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");

         SendObject(obcm);

         ListenNetwork net = new ListenNetwork();
         net.start();
         TextSendAction action = new TextSendAction();
         txtInput.addActionListener(action);
         txtInput.requestFocus();
         
      } catch (NumberFormatException | IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         AppendText("connect error");
      }

   }

   // Server Message�� �����ؼ� ȭ�鿡 ǥ��
   class ListenNetwork extends Thread {
      public void run() {
         while (true) {
            try {

               Object obcm = null;
               String msg = null;
               ChatMsg cm;
               try {
                  obcm = ois.readObject();
               } catch (ClassNotFoundException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  break;
               }
               if (obcm == null)
                  break;
               if (obcm instanceof ChatMsg) {
                  cm = (ChatMsg) obcm;
                  msg = String.format("[%s]\n%s", cm.UserName, cm.data);
               } else
                  continue;
               switch (cm.code) {
               case "120": // chat message
                  model.addElement(cm.UserName);

               case "200": // chat message
                  if (cm.UserName.equals(UserName))
                     AppendTextR(msg); // �� �޼����� ������
                  else
                     AppendText(msg);
                  break;

               case "160": // �����
                  btnRoomButton_1.setIcon(null);
                  btnRoomButton_1.setText(cm.data);
                  break;

               case "170": // �����
                  btnRoomButton_1.setIcon(button);
                  btnRoomButton_1.setText(null);
                  cm = new ChatMsg(UserName, "170", "delete");
                  SendObject(cm);
                  break;

               /*
                * case "300": // Image ÷�� if (cm.UserName.equals(UserName)) AppendTextR("[" +
                * cm.UserName + "]"); else AppendText("[" + cm.UserName + "]");
                * AppendImage(cm.img); break; case "500": // Mouse Event ���� DoMouseEvent(cm);
                * break;
                */
               }
            } catch (IOException e) {
               AppendText("ois.readObject() error");
               try {
//                  dos.close();
//                  dis.close();
                  ois.close();
                  oos.close();
                  socket.close();

                  break;
               } catch (Exception ee) {
                  break;
               } // catch�� ��
            } // �ٱ� catch����

         }
      }
   }

// keyboard enter key ġ�� ������ ����
   class TextSendAction implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // Send button�� �����ų� �޽��� �Է��ϰ� Enter key ġ��
         if (e.getSource() == txtInput) {
            String msg = null;
            // msg = String.format("[%s] %s\n", UserName, txtInput.getText());
            msg = txtInput.getText();
            SendMessage(msg);
            txtInput.setText(""); // �޼����� ������ ���� �޼��� ����â�� ����.
            txtInput.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
            if (msg.contains("/exit")) // ���� ó��
               System.exit(0);
         }
      }
   }

   public void AppendIcon(ImageIcon icon) {
      int len = textArea.getDocument().getLength();
      // ������ �̵�
      textArea.setCaretPosition(len);
      textArea.insertIcon(icon);
   }

   // ȭ�鿡 ���
   public void AppendText(String msg) {
      // textArea.append(msg + "\n");
      // AppendIcon(icon1);
      msg = msg.trim(); // �յ� blank�� \n�� �����Ѵ�.
      int len = textArea.getDocument().getLength();
      // ������ �̵�
      // textArea.setCaretPosition(len);
      // textArea.replaceSelection(msg + "\n");

      StyledDocument doc = textArea.getStyledDocument();
      SimpleAttributeSet left = new SimpleAttributeSet();
      StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
      StyleConstants.setForeground(left, Color.BLACK);
      doc.setParagraphAttributes(doc.getLength(), 1, left, false);
      try {
         doc.insertString(doc.getLength(), msg + "\n", left);
      } catch (BadLocationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   // ȭ�� ������ ���
   public void AppendTextR(String msg) {
      msg = msg.trim(); // �յ� blank�� \n�� �����Ѵ�.
      StyledDocument doc = textArea.getStyledDocument();
      SimpleAttributeSet right = new SimpleAttributeSet();
      StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
      StyleConstants.setForeground(right, Color.BLUE);
      doc.setParagraphAttributes(doc.getLength(), 1, right, false);
      try {
         doc.insertString(doc.getLength(), msg + "\n", right);
      } catch (BadLocationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   // Windows ó�� message ������ ������ �κ��� NULL �� ����� ���� �Լ�
   public byte[] MakePacket(String msg) {
      byte[] packet = new byte[BUF_LEN];
      byte[] bb = null;
      int i;
      for (i = 0; i < BUF_LEN; i++)
         packet[i] = 0;
      try {
         bb = msg.getBytes("euc-kr");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         System.exit(0);
      }
      for (i = 0; i < bb.length; i++)
         packet[i] = bb[i];
      return packet;
   }

   // Server���� network���� ����
   public void SendMessage(String msg) {
      try {
         // dos.writeUTF(msg);
//         byte[] bb;
//         bb = MakePacket(msg);
//         dos.write(bb, 0, bb.length);
         ChatMsg obcm = new ChatMsg(UserName, "200", msg);
         oos.writeObject(obcm);
      } catch (IOException e) {
         // AppendText("dos.write() error");
         AppendText("oos.writeObject() error");
         try {
//            dos.close();
//            dis.close();
            ois.close();
            oos.close();
            socket.close();
         } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.exit(0);
         }
      }
   }

   public void SendObject(Object ob) { // ������ �޼����� ������ �޼ҵ�
      try {
         oos.writeObject(ob);
      } catch (IOException e) {
         // textArea.append("�޼��� �۽� ����!!\n");
         AppendText("SendObject Error");
      }
   }
}