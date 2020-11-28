// JavaObjClient.java
// ObjecStream 사용하는 채팅 Client

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class JavaGameClientMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtIpAddress;
	private JTextField txtPortNumber;
	
	ImageIcon icon0;
	ImageIcon icon1;
	ImageIcon icon2;
	ImageIcon icon3;
	ImageIcon icon4;
	ImageIcon icon5;
	ImageIcon icon6;
	ImageIcon icon7;
	ImageIcon icon8;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaGameClientMain frame = new JavaGameClientMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JavaGameClientMain() {
		setBackground(new Color(255, 255, 255));
		
		icon0 = new ImageIcon("C:/Users/Network/JavaGameClient/images/aqua.jpg");
		icon1 = new ImageIcon("C:/Users/Network/JavaGameClient/images/main4.png");
		icon2 = new ImageIcon("C:/Users/Network/JavaGameClient/images/double.png");
		icon3 = new ImageIcon("C:/Users/Network/JavaGameClient/images/chara_shiro.png");
		icon4 = new ImageIcon("C:/Users/Network/JavaGameClient/images/image5.png");
		icon5 = new ImageIcon("C:/Users/Network/JavaGameClient/images/connect.png");
		icon6 = new ImageIcon("C:/Users/Network/JavaGameClient/images/username.png");
		icon7 = new ImageIcon("C:/Users/Network/JavaGameClient/images/ip.png");
		icon8 = new ImageIcon("C:/Users/Network/JavaGameClient/images/port.png");
		
		Image connect = icon5.getImage();
		Image changeconnect = connect.getScaledInstance(160, 40, Image.SCALE_SMOOTH);
		ImageIcon connect_ch = new ImageIcon(changeconnect);
		
		/*
		 * Image icon1 = icon[0].getImage(); Image changeImg1 =
		 * icon1.getScaledInstance(130, 130, Image.SCALE_SMOOTH); 
		 * ImageIcon changeIcon1 = new ImageIcon(changeImg1);
		 */
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 502);
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon1.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				 g.drawImage(icon0.getImage(), 0, 0, d.width, d.height, null); // get size
				
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
				
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel() {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon6.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				g.drawImage(icon6.getImage(), 0, 0, d.width, d.height, null); // get size
				
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		
		lblNewLabel.setBounds(158, 120, 120, 40);
		contentPane.add(lblNewLabel);
		
		txtUserName = new JTextField();
		txtUserName.setHorizontalAlignment(SwingConstants.CENTER);
		txtUserName.setBounds(297, 120, 116, 33);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblIpAddress = new JLabel() {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon6.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				g.drawImage(icon7.getImage(), 0, 0, d.width, d.height, null); // get size
				
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		lblIpAddress.setBounds(158, 178, 120, 40);
		contentPane.add(lblIpAddress);
		
		txtIpAddress = new JTextField();
		txtIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtIpAddress.setText("127.0.0.1");
		txtIpAddress.setColumns(10);
		txtIpAddress.setBounds(297, 182, 116, 33);
		contentPane.add(txtIpAddress);
		
		JLabel lblPortNumber = new JLabel() {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon6.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				g.drawImage(icon8.getImage(), 0, 0, d.width, d.height, null); // get size
				
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		lblPortNumber.setBounds(158, 240, 120, 40);
		contentPane.add(lblPortNumber);
		
		txtPortNumber = new JTextField();
		txtPortNumber.setText("30000");
		txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtPortNumber.setColumns(10);
		txtPortNumber.setBounds(297, 240, 116, 33);
		contentPane.add(txtPortNumber);
		
		JButton btnConnect = new JButton();
		btnConnect.setContentAreaFilled(false);
		btnConnect.setIcon(connect_ch);
		btnConnect.setBackground(new Color(255, 0, 0 , 0));
		btnConnect.setFont(new Font("굴림", Font.PLAIN, 14));
		btnConnect.setBounds(208, 295, 160, 40);
		
		contentPane.add(btnConnect);
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon1.getImage(), 0, 0, null); // full size
				Dimension d = getSize();
				 g.drawImage(icon1.getImage(), 0, 0, d.width, d.height, null); // get size
				
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		
		panel.setBounds(171, 10, 242, 100);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon2.getImage(), 0, 0, null); // full size
				//Dimension d = getSize();
				 //g.drawImage(icon2.getImage(), 0, 0, d.width, d.height, null); // get size
				
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		panel_1.setBounds(361, 273, 227, 190);
		contentPane.add(panel_1);
		Myaction action = new Myaction();
		btnConnect.addActionListener(action);
		txtUserName.addActionListener(action);
		txtIpAddress.addActionListener(action);
		txtPortNumber.addActionListener(action);
	}
	class Myaction implements ActionListener // 내부클래스로 액션 이벤트 처리 클래스
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = txtUserName.getText().trim();
			String ip_addr = txtIpAddress.getText().trim();
			String port_no = txtPortNumber.getText().trim();
			
			SelectIcon view = new SelectIcon(username, ip_addr, port_no);
			
			// JavaGameClientView view = new JavaGameClientView(username, ip_addr, port_no);
			setVisible(false);
		}
	}
}


