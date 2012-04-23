package com.stu.view;



import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.stu.dao.impl.StuDaoImpl;
import com.stu.model.StuInfo;


public class StuAddDialog extends JDialog implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	private JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	private JButton jb1,jb2;
	private JPanel jp1,jp2,jp3,jp4;
	
	
	private StuDaoImpl stuImpl = new StuDaoImpl();
	
	public StuAddDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		//�в���2��panel 
		jp4 = new JPanel(new GridLayout(1,2));
		//�в� ---> ����	
		jp1 = new JPanel(new GridLayout(6,1));
		jl1 = new JLabel("ѧ��(ϵͳ�Զ�����)",JLabel.CENTER);
		jl1.setForeground(Color.RED);
		jl2 = new JLabel("����",JLabel.CENTER);
		jl3 = new JLabel("����",JLabel.CENTER);
		jl4 = new JLabel("�Ա�",JLabel.CENTER);
		jl5 = new JLabel("ϵ��",JLabel.CENTER);
		jl6 = new JLabel("��ͥסַ",JLabel.CENTER);
		//�в� ---> ����
		jp2 = new JPanel(new GridLayout(6,1));
		jtf1 = new JTextField(10);
		jtf1.setEnabled(false);
		jtf2 = new JTextField(10);
		jtf3 = new JTextField(10);
		jtf4 = new JTextField(10);
		jtf5 = new JTextField(10);
		jtf6 = new JTextField(10);
		
		jp1.add(jl1);jp1.add(jl2);jp1.add(jl3);jp1.add(jl4);jp1.add(jl5);jp1.add(jl6);
		jp2.add(jtf1);jp2.add(jtf2);jp2.add(jtf3);jp2.add(jtf4);jp2.add(jtf5);jp2.add(jtf6);
		jp4.add(jp1);jp4.add(jp2);
		
		//�ϲ�
		jp3 = new JPanel();
		jb1 = new JButton("ȷ��");
		jb1.setActionCommand("fix");
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
		jb2.setActionCommand("cancle");
		jb2.addActionListener(this);
		
		jp3.add(jb1);jp3.add(jb2);
		
		
		
		this.add(jp4);
		this.add(jp3,"South");
		this.setBounds(350, 350, 300, 180);
		this.setVisible(true);
//		this.setResizable(false);
	
	}

	public StuAddDialog() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("fix")){
			
//			String stuId = this.jtf1.getText().trim();
			String stuName = this.jtf2.getText().trim();
			String stuAge = this.jtf3.getText().trim();
			String stuSex = this.jtf4.getText().trim();
			System.out.println(stuSex);
			String stuDept = this.jtf5.getText().trim();
			String stuAddress = this.jtf6.getText().trim();
			
			if(stuName==null||stuDept==null||stuAge==null||stuSex==null||stuAddress==null
					||stuName.equals("")||stuDept.equals("")||stuAge.equals("")||stuSex.equals("")||stuAddress.equals("")){
				JOptionPane.showMessageDialog(this, "�����������Ϣ������,����дȫ����Ϣ!","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(!stuSex.equals("��")&&!stuSex.equals("Ů")){
				JOptionPane.showMessageDialog(this, "�Ա����Ϊ�л���Ů,���޸ĸ�ѡ���ֵ!","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				if(stuImpl.addUser(new StuInfo(-1,stuName,Integer.parseInt(stuAge),stuSex,stuAddress,stuDept))){
					JOptionPane.showMessageDialog(this, "���ѧ����Ϣ�ɹ�!","��Ϣ��ʾ",JOptionPane.OK_OPTION);
					this.dispose();
			
				}else{
					JOptionPane.showMessageDialog(this, "���ѧ��ʧ��,������������Ƿ�����!���ȡ����ť�������ѧУ��Ϣ!","��Ϣ��ʾ",JOptionPane.WARNING_MESSAGE);
//					this.dispose();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "��,���������ѧ�Ż���������,����������!����������Ŷ","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(this, "ϵͳ����δ֪����,ϵͳ������������!","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);
				this.dispose();
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("cancle")){
			System.out.println("ȡ�����ѧ��");
			this.dispose();
		}
	}
}
