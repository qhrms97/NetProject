
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JList;
import java.awt.Canvas;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Button;

public class JavaGameClientView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel lblUserName;
	private JTextPane textArea;

	private Frame frame;
	private FileDialog fd;

	private JPanel myinfo;
	private Graphics gc;
	
	private JPanel title;
	
	private JButton btnRoomButton_1;
	private JButton btnRoomButton_2;
	private JButton btnRoomButton_3;
	private JButton btnRoomButton_4;
	private JButton btnRoomButton_5;
	private JButton btnRoomButton_6;
	
	ImageIcon darkbackground;
	ImageIcon face;
	ImageIcon button;
	ImageIcon titleImg;
	
	
	DefaultListModel<String> model;
	// 리스트 선언
	private JList list;
	
	/**
	 * Create the frame.
	 * @throws BadLocationException 
	 */
	public JavaGameClientView(String username, String ip_addr, String port_no)  {
		
		darkbackground = new ImageIcon("C:/Users/qhrms.DESKTOP-6KE9FVU/git/repository/JavaGameClient/images/aqua.jpg");
		face = new ImageIcon("C:/Users/qhrms.DESKTOP-6KE9FVU/git/repository/JavaGameClient/images/chara_mizu.png");
		button = new ImageIcon("C:/Users/qhrms.DESKTOP-6KE9FVU/git/repository/JavaGameClient/images/blue.jpg");
		titleImg = new ImageIcon("C:/Users/qhrms.DESKTOP-6KE9FVU/git/repository/JavaGameClient/images/title.png");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 614);
		contentPane = new JPanel() {
				public void paintComponent(Graphics g) {
					//g.drawImage(icon4.getImage(), 0, 0, null); // full size
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 340, 400, 146);
		contentPane.add(scrollPane);

		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		scrollPane.setRowHeaderView(textArea);
		
		myinfo = new JPanel(){
			public void paintComponent(Graphics g) {
				//g.drawImage(icon4.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				g.drawImage(face.getImage(), 0, 0, d.width, d.height, null); // get size
				
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		myinfo.setBackground(Color.WHITE);
		myinfo.setBounds(27, 10, 157, 146);
		contentPane.add(myinfo);
		
		txtInput = new JTextField();
		txtInput.setBounds(208, 505, 258, 40);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setFont(new Font("굴림", Font.PLAIN, 14));
		btnSend.setBounds(539, 504, 69, 40);
		contentPane.add(btnSend);
		setVisible(true);

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;

		JButton btnNewButton = new JButton("종 료");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
				System.exit(0);
			}
		});
		btnNewButton.setBounds(469, 504, 69, 40);
		contentPane.add(btnNewButton);
		
		lblUserName = new JLabel("Name");
		lblUserName.setBounds(70, 166, 78, 19);
		contentPane.add(lblUserName);
		//lblUserName.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setFont(new Font("Arial", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setText(username);
		
		btnRoomButton_1 = new JButton(button);
		btnRoomButton_1.setBackground(Color.WHITE);
		btnRoomButton_1.setBorderPainted(true);
		btnRoomButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnRoomButton_6.setBounds(208, 246, 192, 71);
		contentPane.add(btnRoomButton_6);
		
		JPanel title = new JPanel() {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon4.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				g.drawImage(titleImg.getImage(), 0, 0, d.width, d.height, null); // get size
				
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		
		title.setBackground(Color.WHITE);
		title.setBounds(208, 10, 400, 65);
		contentPane.add(title);
		
		model = new DefaultListModel<String>();
		model.addElement(username);
		
		list = new JList(model);
		list.setBorder(null);
		
		list.setBackground(new Color(0, 191, 255));
		list.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		getContentPane().add(list);
		list.setBounds(27, 203, 157, 342);
		contentPane.add(list);

		
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));
//			is = socket.getInputStream();
//			dis = new DataInputStream(is);
//			os = socket.getOutputStream();
//			dos = new DataOutputStream(os);

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			// SendMessage("/login " + UserName);
			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);

			ListenNetwork net = new ListenNetwork();
			net.start();
			TextSendAction action = new TextSendAction();
			btnSend.addActionListener(action);
			txtInput.addActionListener(action);
			txtInput.requestFocus();
			//MyMouseEvent mouse = new MyMouseEvent();
			//panel.addMouseMotionListener(mouse);
			//panel.addMouseListener(mouse);
			//MyMouseWheelEvent wheel = new MyMouseWheelEvent();
			//panel.addMouseWheelListener(wheel);
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
					switch (cm.code) {
					case "200": // chat message
						if (cm.UserName.equals(UserName))
							AppendTextR(msg); // 내 메세지는 우측에
						else
							AppendText(msg);
						break;
					/* case "300": // Image 첨부
						if (cm.UserName.equals(UserName))
							AppendTextR("[" + cm.UserName + "]");
						else
							AppendText("[" + cm.UserName + "]");
						AppendImage(cm.img);
						break;
					case "500": // Mouse Event 수신
						DoMouseEvent(cm);
						break; */
					}
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
//						dos.close();
//						dis.close();
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

	// Mouse Event 수신 처리 - 미사용
	/* public void DoMouseEvent(ChatMsg cm) {
		Color c;
		if (cm.UserName.matches(UserName)) // 본인 것은 이미 Local 로 그렸다.
			return;
		c = new Color(255, 0, 0); // 다른 사람 것은 Red
		gc.setColor(c);
		gc.fillOval(cm.mouse_e.getX() - pen_size/2, cm.mouse_e.getY() - cm.pen_size/2, cm.pen_size, cm.pen_size);
	} */

	/* 미사용
	public void SendMouseEvent(MouseEvent e) {
		ChatMsg cm = new ChatMsg(UserName, "500", "MOUSE");
		cm.mouse_e = e;
		cm.pen_size = pen_size;
		SendObject(cm);
	} */

	/*class MyMouseWheelEvent implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// TODO Auto-generated method stub
			if (e.getWheelRotation() < 0) { // 위로 올리는 경우 pen_size 증가
				if (pen_size < 20)
					pen_size++;
			} else {
				if (pen_size > 2)
					pen_size--;
			}
			// 사용 안함
			//lblMouseEvent.setText("mouseWheelMoved Rotation=" + e.getWheelRotation() 
			//	+ " pen_size = " + pen_size + " " + e.getX() + "," + e.getY());

		}
		
	}
	*/
	// Mouse Event Handler
	/*class MyMouseEvent implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			lblMouseEvent.setText(e.getButton() + " mouseClicked " + e.getX() + "," + e.getY());
			Color c = new Color(0,0,255);
			gc.setColor(c);
			gc.fillOval(e.getX()-pen_size/2, e.getY()-pen_size/2, pen_size, pen_size);
			SendMouseEvent(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			lblMouseEvent.setText(e.getButton() + " mouseEntered " + e.getX() + "," + e.getY());
			// panel.setBackground(Color.YELLOW);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			lblMouseEvent.setText(e.getButton() + " mouseExited " + e.getX() + "," + e.getY());
			// panel.setBackground(Color.CYAN);

		}

		@Override
		public void mousePressed(MouseEvent e) {
			lblMouseEvent.setText(e.getButton() + " mousePressed " + e.getX() + "," + e.getY());

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			lblMouseEvent.setText(e.getButton() + " mouseReleased " + e.getX() + "," + e.getY());
			// 드래그중 멈출시 보임

		}
	}
	*/

	// keyboard enter key 치면 서버로 전송
	class TextSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button을 누르거나 메시지 입력하고 Enter key 치면
			if (e.getSource() == btnSend || e.getSource() == txtInput) {
				String msg = null;
				// msg = String.format("[%s] %s\n", UserName, txtInput.getText());
				msg = txtInput.getText();
				SendMessage(msg);
				txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
				if (msg.contains("/exit")) // 종료 처리
					System.exit(0);
			}
		}
	}

	ImageIcon icon1 = new ImageIcon("src/icon1.jpg");

	public void AppendIcon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		textArea.setCaretPosition(len);
		textArea.insertIcon(icon);
	}

	// 화면에 출력
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		// AppendIcon(icon1);
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		//textArea.setCaretPosition(len);
		//textArea.replaceSelection(msg + "\n");
		
		StyledDocument doc = textArea.getStyledDocument();
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(left, Color.BLACK);
	    doc.setParagraphAttributes(doc.getLength(), 1, left, false);
		try {
			doc.insertString(doc.getLength(), msg+"\n", left );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// 화면 우측에 출력
	public void AppendTextR(String msg) {
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.	
		StyledDocument doc = textArea.getStyledDocument();
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setForeground(right, Color.BLUE);	
	    doc.setParagraphAttributes(doc.getLength(), 1, right, false);
		try {
			doc.insertString(doc.getLength(),msg+"\n", right );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//			byte[] bb;
//			bb = MakePacket(msg);
//			dos.write(bb, 0, bb.length);
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			// AppendText("dos.write() error");
			AppendText("oos.writeObject() error");
			try {
//				dos.close();
//				dis.close();
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
