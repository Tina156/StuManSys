package com.stu.view;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.stu.dao.impl.StuDaoImpl;
import com.stu.model.StuInfo;
import com.stu.model.StuTableModel;

public class StuMainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton jb1, jb2, jb3, jb4;
	private JLabel jl;
	private JTextField jtf;
	private JTable jt;
	private JPanel jp1, jp2;
	private JScrollPane jsp;
	StuTableModel stm;

	public StuMainFrame() {
		jb1 = new JButton("��ѯ");
		jb1.setActionCommand("query");
		jb1.addActionListener(this);
		jtf = new JTextField(10);
		jl = new JLabel("������Ҫ��ѯ��ѧ��ID:");
		jb2 = new JButton("���");
		jb2.setActionCommand("addStu");
		jb2.addActionListener(this);
		jb3 = new JButton("�޸�");
		jb3.setActionCommand("upStu");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
//		jb4.setBackground(Color.RED);
		jb4.setActionCommand("delStu");
		jb4.addActionListener(this);
		jp1 = new JPanel();
		jp2 = new JPanel();
		stm = new StuTableModel();
		jt = new JTable(stm);
		jsp = new JScrollPane(jt);

		// �������
		jp1.add(jl);
		jp1.add(jtf);
		jp1.add(jb1);
		this.add(jp1, "North");

		// �в����
		this.add(jsp);

		// �ϲ����
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		this.add(jp2, "South");

		this.setTitle("ѧ������ϵͳ");
		Toolkit toolkit = this.getToolkit();
		Image myImage = toolkit.getImage("image/1.jpg");
		this.setIconImage(myImage);
		this.setBounds(300, 300, 400, 280);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("query")) {
			if (this.jtf.getText().equals("") || this.jtf.getText() == null) {
				System.out.println("������Ҫ��ѯ��ѧ��������");
				JOptionPane.showMessageDialog(this, "��������Ҫ��ѯ��ѧ������!", "��Ϣ��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				try {
					System.out.println("��ѯѧ����Ϣ");
					int stuId = Integer.parseInt(this.jtf.getText());
					StuInfo stu = new StuDaoImpl().queryOneStuById(stuId);
					stm = new StuTableModel(stu);
					jt.setModel(stm);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "��,���������ѧ�Ż���������,����������!����������Ŷ","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		} else if (e.getActionCommand().equals("addStu")) {
			System.out.println("���ѧ����Ϣ");
			new StuAddDialog(this, "���ѧ��", true);
			stm = new StuTableModel();
			jt.setModel(stm);
		} else if(e.getActionCommand().equals("delStu")){
			try {
				System.out.println("ɾ��ѧ����Ϣ");
				stm = new StuTableModel();
				int row = jt.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(this, "��ѡ����Ҫɾ����ѧ�����ڵ���!","��Ϣ��ʾ",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
//				int [] rows = jt.getSelectedRows();
//				System.out.println(rows[0]+""+rows[1]);
//			    int col = jt.getSelectedColumn();
				int stuId = Integer.parseInt((String)stm.getValueAt(row, 0));
				String stuName = (String)stm.getValueAt(row, 1);
				System.out.println("Ҫɾ����ѧ��ID��:"+stuId+"ѧ��������:"+stuName);
				if(JOptionPane.showConfirmDialog(this, "��ȷ��ɾ����ѧ����Ϣ��?", "��Ϣ��ʾ",
						JOptionPane.YES_NO_OPTION)==0){
					if(new StuDaoImpl().delUser(stuId)){
						Thread.sleep(1000);
						JOptionPane.showMessageDialog(this, "ɾ��ѧ��["+stuName+"]��Ϣ�ɹ�!","��Ϣ��ʾ",JOptionPane.OK_OPTION);
						stm = new StuTableModel();
						jt.setModel(stm);
					}else {
						Thread.sleep(1000);
						JOptionPane.showMessageDialog(this, "ɾ��ѧ��["+stuName+"]��Ϣʧ��!","��Ϣ��ʾ",JOptionPane.WARNING_MESSAGE);
					}
					
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("upStu")){
			System.out.println("�޸�ѧ����Ϣ����");
			try {
				
				stm = new StuTableModel();
				int row = jt.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�޸ĵ�ѧ�����ڵ���!","��Ϣ��ʾ",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
//				int [] rows = jt.getSelectedRows();
//				System.out.println(rows[0]+""+rows[1]);
//			    int col = jt.getSelectedColumn();
				int stuId = Integer.parseInt((String)stm.getValueAt(row, 0));
				StuDaoImpl stuImpl = new StuDaoImpl();
				
				new UpStuDialog(this,"�޸�ѧ��",true,stuImpl.queryOneStuById(stuId));
				stm = new StuTableModel();
				jt.setModel(stm);
				
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			}
		}
	}
}
