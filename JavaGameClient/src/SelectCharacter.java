
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.Icon;

public class SelectCharacter extends JFrame {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private String UserName;
   private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
   private Socket socket; // 연결소켓

   private ObjectInputStream ois;
   private ObjectOutputStream oos;

   private JLabel lblUserName;
   private JLabel myinfo;
   // 캐릭터 이미지를 저장하기 위한 array지정
   private ImageIcon[] icon = new ImageIcon[10];
   
   ImageIcon darkbackground;
   ImageIcon face;
   ImageIcon button;
   ImageIcon titleImg;
   ImageIcon select;
   ImageIcon main;
   
   private JPanel SelectImage;
   private JButton btnEnterButton;
   private JButton btnImageButton_1;
   private JButton btnImageButton_2;
   private JButton btnImageButton_3;
   private JButton btnImageButton_4;
   private JButton btnImageButton_5;
   private JButton btnImageButton_6;
   private JButton btnImageButton_7;
   private JButton btnImageButton_8;
   
   public int num = 0; 
   /**
    * Create the frame.
    * @throws BadLocationException N
    */
   public SelectCharacter(String username, String ip_addr, String port_no)  {
	  
	  
	   
      darkbackground = new ImageIcon("C:/Users/Network/JavaGameClient/images/aqua.jpg");
      face = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_mizu.png");
      button = new ImageIcon("C:/Users/Network/JavaGameClient/images/blue.jpg");
      titleImg = new ImageIcon("C:/Users/Network/JavaGameClient/images/title.png");
      select = new ImageIcon("C:/Users/Network/JavaGameClient/images/select.png");
      main = new ImageIcon("C:/Users/Network/JavaGameClient/images/main2.png");
      
      icon[0] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_aka.png");
      icon[1] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_ao.png");
      icon[2] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_ki.png");
      icon[3] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_midori.png");
      icon[4] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_mizu.png");
      icon[5] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_pink.png");
      icon[6] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_shiro.png");
      icon[7] = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_shiro2.png");
      
      // icon 크기 변경 -JLabel과 크기 일치하기 위해
      Image icon1 = icon[0].getImage();
      Image changeImg1 = icon1.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon1 = new ImageIcon(changeImg1);
      
      Image icon2 = icon[1].getImage();
      Image changeImg2 = icon2.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon2 = new ImageIcon(changeImg2);
      
      Image icon3 = icon[2].getImage();
      Image changeImg3 = icon3.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon3 = new ImageIcon(changeImg3);
      
      Image icon4 = icon[3].getImage();
      Image changeImg4 = icon4.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon4 = new ImageIcon(changeImg4);
      
      Image icon5 = icon[4].getImage();
      Image changeImg5 = icon5.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon5 = new ImageIcon(changeImg5);
      
      Image icon6 = icon[5].getImage();
      Image changeImg6 = icon1.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon6 = new ImageIcon(changeImg6);
      
      Image icon7 = icon[6].getImage();
      Image changeImg7 = icon7.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon7 = new ImageIcon(changeImg7);
      
      Image icon8 = icon[7].getImage();
      Image changeImg8 = icon1.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon changeIcon8 = new ImageIcon(changeImg8);
      
      
      // 여기서 부터 시작
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 645, 408);
      contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
               Dimension d = getSize();
                g.drawImage(darkbackground.getImage(), 0, 0, d.width, d.height, null); // get size
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
      };
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      myinfo = new JLabel();
      myinfo.setBackground(new Color(255, 0, 0, 0));
      myinfo.setBounds(13, 111, 130, 130);
      contentPane.add(myinfo);
      setVisible(true);

      AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
      UserName = username;
      
      JPanel Name = new JPanel();
      Name.setBackground(new Color(255, 0, 0, 0));
      Name.setBounds(13, 253, 130, 27);
      contentPane.add(Name);
      
      lblUserName = new JLabel("Name");
      Name.add(lblUserName);
      lblUserName.setBackground(Color.WHITE);
      lblUserName.setFont(new Font("Arial", Font.BOLD, 14));
      lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
      lblUserName.setText("[" + username + "]");
      
      btnImageButton_1 = new JButton(icon[0]);
      btnImageButton_1.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon1);
      		num = 0;
      	}
      });
      btnImageButton_1.setBounds(184, 111, 100, 105);
      contentPane.add(btnImageButton_1);
      
      btnImageButton_2 = new JButton(icon[1]);
      btnImageButton_2.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon2);
      		num = 1;
      	}
      });
      btnImageButton_2.setBounds(296, 111, 100, 105);
      contentPane.add(btnImageButton_2);
      
      btnImageButton_3 = new JButton(icon[2]);
      btnImageButton_3.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon3);
      		num = 2;
      	}
      });
      btnImageButton_3.setBounds(405, 111, 100, 105);
      contentPane.add(btnImageButton_3);
      
      btnImageButton_4 = new JButton(icon[3]);
      btnImageButton_4.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon4);
      		num = 3;
      	}
      });
      btnImageButton_4.setBounds(517, 111, 100, 105);
      contentPane.add(btnImageButton_4);
      
      btnImageButton_5 = new JButton(icon[4]);
      btnImageButton_5.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon5);
      		num = 4;
      	}
      });
      btnImageButton_5.setBounds(184, 226, 100, 105);
      contentPane.add(btnImageButton_5);
      
      btnImageButton_6 = new JButton(icon[5]);
      btnImageButton_6.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon6);
      		num = 5;
      	}
      });
      btnImageButton_6.setBounds(296, 226, 100, 105);
      contentPane.add(btnImageButton_6);
      
      btnImageButton_7 = new JButton(icon[6]);
      btnImageButton_7.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon7);
      		num = 6;
      	}
      });
      btnImageButton_7.setBounds(405, 226, 100, 105);
      contentPane.add(btnImageButton_7);
      
      btnImageButton_8 = new JButton(icon[7]);
      btnImageButton_8.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		myinfo.setIcon(changeIcon8);
      		num = 7;
      	}
      });
      btnImageButton_8.setBounds(517, 226, 100, 105);
      contentPane.add(btnImageButton_8);
      
      btnEnterButton = new JButton("입장");
      btnEnterButton.setFont(new Font("굴림", Font.BOLD, 14));
      btnEnterButton.setBackground(new Color(255, 255, 255));
      btnEnterButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		JavaGameClientView view = new JavaGameClientView(username, ip_addr, port_no, num);
      	}
      });
      btnEnterButton.setBounds(13, 290, 130, 40);
      contentPane.add(btnEnterButton);
      
      SelectImage = new JPanel() {
    	  public void paintComponent(Graphics g) {
              Dimension d = getSize();
              g.drawImage(select.getImage(), 0, 0, d.width, d.height, null); // get size
              setOpaque(false); //그림을 표시하게 설정,투명하게 조절
              super.paintComponent(g);
          }
      };
      SelectImage.setBounds(184, 10, 433, 90);
      contentPane.add(SelectImage);
      
      try {
         socket = new Socket(ip_addr, Integer.parseInt(port_no));

         oos = new ObjectOutputStream(socket.getOutputStream());
         oos.flush();
         ois = new ObjectInputStream(socket.getInputStream());
         
         ChatMsg obcm = new ChatMsg(UserName, "110", "JoinRoom");
         
         SendObject(obcm);
         
         ListenNetwork net = new ListenNetwork();
         net.start();
         
      } catch (NumberFormatException | IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         AppendText("connect error");
      }
   }

   // Server Message를 수신해서 화면에 표시
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
               } // catch문 끝
            } // 바깥 catch문끝

         }
      }
   }
   
   // 화면에 출력
   public void AppendText(String msg) {
      msg = msg.trim();
      SimpleAttributeSet left = new SimpleAttributeSet();
      StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
      StyleConstants.setForeground(left, Color.BLACK);
   }
   
   // Windows 처럼 message 제외한 나머지 부분은 NULL 로 만들기 위한 함수
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

   // Server에게 network으로 전송
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

   public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
      try {
         oos.writeObject(ob);
      } catch (IOException e) {
         // textArea.append("메세지 송신 에러!!\n");
         AppendText("SendObject Error");
      }
   }
}