package app;

import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class App extends JFrame {
	Connection connect = null; // Mysq的连接对象
	int current=0;

	public void InitDataBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/GradeManager?serverTimezone=GMT", "root", "wcf12345");
			// 时区错误???
			System.out.println("Success connect Mysql server!");
			this.connect = connect;
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	private void DeleteData(String id) {
		try {
			String sql = "delete from grade where id='" + id + "'";
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Successfully delete data of selected row");
		} catch (Exception e) {
			System.out.println("delete error! please select a row");
		}
	}

	private Vector GetData() {
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from grade");
			Vector str = new Vector();
			Vector st1 = new Vector();
			int j;
			for (j = 0; rs.next(); j++) {
				st1 = new Vector();
				for (int i = 1; i < 9; i++) {
					st1.add(rs.getString(i));
				}
				str.add(st1);
			}
			stmt.close();
			System.out.println("Successfully refresh data!");
			return str;
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
			return null;
		}
	}

	private void ChangeData(String sid, String id, String name, String sex, String age, String phone, String chinese,
			String math, String english) {
		try {
			String sql = "update grade set id='" + id + "', name='" + name + "', sex='" + sex + "', age='" + age
					+ "', phone='" + phone + "', chinese='" + chinese + "', math='" + math + "', english='" + english
					+ "' where id='" + sid + "'";
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Successfully change data in selected row");
			System.out.println();
		} catch (Exception e) {
			System.out.print("change data error! please check your value");
		}
	}

	private void AddData(String id, String name, String sex, String age, String phone, String chinese, String math,
			String english) {
		try {
			Statement stmt = connect.createStatement();
			String sql = "insert into grade (id,name,sex,age,phone,chinese,math,english) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, sex);
			ps.setString(4, age);
			ps.setString(5, phone);
			ps.setString(6, chinese);
			ps.setString(7, math);
			ps.setString(8, english);
			ps.executeUpdate();
			stmt.close();
			System.out.println("Successfully insert data!");
		} catch (Exception e) {
			System.out.print("insert data error! please check your value");
		}
	}

	public App(String title) {
		super(title);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(20, 20));
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout(10, 10));
		JLabel k1 = new JLabel();
		JLabel k2 = new JLabel();
		JLabel k3 = new JLabel();
		JLabel k4 = new JLabel();
		DefaultTableModel model = new DefaultTableModel();
		Vector TableHeader = new Vector();
		TableHeader.add("学号");
		TableHeader.add("姓名");
		TableHeader.add("性别");
		TableHeader.add("年龄");
		TableHeader.add("电话");
		TableHeader.add("语文");
		TableHeader.add("数学");
		TableHeader.add("英语");
		Vector data = new Vector();
		model.setDataVector(data, TableHeader);
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);// 设置只能选中一行
		JScrollPane scrollPane = new JScrollPane(table);
		// JTextArea t=new JTextArea();

		Bottom b1 = new Bottom("查询");
		Bottom b2 = new Bottom("增加");
		Bottom b3 = new Bottom("删除");
		Bottom b4 = new Bottom("更改");

		JLabel l1 = new JLabel("学号:", JLabel.CENTER);
		JTextPane t1 = new JTextPane();
		t1.setPreferredSize(new Dimension(100, 20));
		JLabel l2 = new JLabel("姓名:", JLabel.CENTER);
		JTextPane t2 = new JTextPane();
		t2.setPreferredSize(new Dimension(100, 20));
		JLabel l3 = new JLabel("性别", JLabel.CENTER);
		String s[] = { "", "男", "女" };
		JComboBox t3 = new JComboBox(s);
		JLabel l4 = new JLabel("年龄", JLabel.CENTER);
		JTextPane t4 = new JTextPane();
		t4.setPreferredSize(new Dimension(100, 20));
		JLabel l5 = new JLabel("电话", JLabel.CENTER);
		JTextPane t5 = new JTextPane();
		t5.setPreferredSize(new Dimension(100, 20));
		JLabel l6 = new JLabel("语文", JLabel.CENTER);
		JTextPane t6 = new JTextPane();
		t6.setPreferredSize(new Dimension(100, 20));
		JLabel l7 = new JLabel("数学", JLabel.CENTER);
		JTextPane t7 = new JTextPane();
		t7.setPreferredSize(new Dimension(100, 20));
		JLabel l8 = new JLabel("外语", JLabel.CENTER);
		JTextPane t8 = new JTextPane();
		t8.setPreferredSize(new Dimension(100, 20));
		JLabel l9 = new JLabel("", JLabel.CENTER);
		JLabel l10 = new JLabel("", JLabel.CENTER);

		JPanel p0 = new JPanel();
		p0.setLayout(new GridLayout(3, 6, 10, 10));
		p0.add(l1);
		p0.add(t1);
		p0.add(l2);
		p0.add(t2);
		p0.add(l3);
		p0.add(t3);
		p0.add(l4);
		p0.add(t4);
		p0.add(l5);
		p0.add(t5);
		p0.add(l9);
		p0.add(l10);
		p0.add(l6);
		p0.add(t6);
		p0.add(l7);
		p0.add(t7);
		p0.add(l8);
		p0.add(t8);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 1, 10, 10));

		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);

		p.add(new JLabel("学生信息管理系统", JLabel.CENTER), BorderLayout.NORTH);
		p.add(scrollPane, BorderLayout.CENTER);
		p.add(p1, BorderLayout.EAST);
		p.add(p0, BorderLayout.SOUTH);

		contentPane.add(p, BorderLayout.CENTER);
		contentPane.add(k1, BorderLayout.EAST);
		contentPane.add(k2, BorderLayout.WEST);
		contentPane.add(k3, BorderLayout.NORTH);
		contentPane.add(k4, BorderLayout.SOUTH);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector str = GetData();
				model.setDataVector(str, TableHeader);
				System.out.println();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddData(t1.getText(), t2.getText(), t3.getSelectedItem().toString(), t4.getText(), t5.getText(),
						t6.getText(), t7.getText(), t8.getText());
				Vector str = GetData();
				model.setDataVector(str, TableHeader);
				table.setRowSelectionInterval(current, current);
				System.out.println();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {
					System.out.println("please select a row!");
					System.out.println();
				} else {
					DeleteData(table.getValueAt(table.getSelectedRow(), 0).toString());
					Vector str = GetData();
					model.setDataVector(str, TableHeader);
					System.out.println();
				}
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {
					System.out.println("please select a row!");
					System.out.println();
				} else {
					ChangeData(table.getValueAt(table.getSelectedRow(), 0).toString(), t1.getText(), t2.getText(),
							t3.getSelectedItem().toString(), t4.getText(), t5.getText(), t6.getText(), t7.getText(),
							t8.getText());
					Vector str = GetData();
					model.setDataVector(str, TableHeader);
					table.setRowSelectionInterval(current, current);
					System.out.println();
				}
			}
		});
		// 设置表格选中监听
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int i = table.getSelectedRow();
				if(i==-1) return;
				current=table.getSelectedRow();
				t1.setText(table.getValueAt(i, 0).toString());
				t2.setText(table.getValueAt(i, 1).toString());
				t3.setSelectedItem(table.getValueAt(i, 2));
				t4.setText(table.getValueAt(i, 3).toString());
				t5.setText(table.getValueAt(i, 4).toString());
				t6.setText(table.getValueAt(i, 5).toString());
				t7.setText(table.getValueAt(i, 6).toString());
				t8.setText(table.getValueAt(i, 7).toString());
			}
		});
	}

	public static void main(String[] args) {
		App window = new App("学生信息管理系统");
		window.InitDataBase();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 400);
		window.setVisible(true);
	}
}