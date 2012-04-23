package com.stu.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.stu.dao.impl.StuDaoImpl;

public class StuTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Vector<Vector<String>> rowsData = new Vector<Vector<String>>();
	private Vector<String> colsData = new Vector<String>();

	@Override
	public String getColumnName(int column) {
		// System.out.println("�����˲�ѯ��������");
		return colsData.get(column);
	}

	public int getColumnCount() {
		// System.out.println("�����˲�ѯ��������");
		return colsData.size();
	}

	public int getRowCount() {
		// System.out.println("�����˲�ѯ��������");
		return rowsData.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// System.out.println("�����˲�ѯ���ݷ���");
		return rowsData.get(rowIndex).get(columnIndex);
	}

	public StuTableModel() {
		colsData.add("ѧ��");
		colsData.add("����");
		colsData.add("����");
		colsData.add("�Ա�");
		colsData.add("��ͥסַ");
		colsData.add("ϵ��");

		StuDaoImpl stuImpl = new StuDaoImpl();
		List<StuInfo> stuInfos = stuImpl.queryAllStuInfo();
		for (int i = 0, len = stuInfos.size(); i < len; i++) {
			StuInfo stu = stuInfos.get(i);
			Vector<String> rowsTemp = new Vector<String>();

			rowsTemp.add(stu.getStuId() + "");
			rowsTemp.add(stu.getStuName());
			rowsTemp.add(stu.getStuAge() + "");
			rowsTemp.add(stu.getStuSex());
			rowsTemp.add(stu.getStuAddress());
			rowsTemp.add(stu.getStuDept());

			rowsData.add(rowsTemp);
		}
	}

	public StuTableModel(StuInfo stu) {
		colsData.add("ѧ��");
		colsData.add("����");
		colsData.add("����");
		colsData.add("�Ա�");
		colsData.add("��ͥסַ");
		colsData.add("ϵ��");

		Vector<String> rowsTemp = new Vector<String>();

		rowsTemp.add(stu.getStuId() + "");
		rowsTemp.add(stu.getStuName());
		rowsTemp.add(stu.getStuAge() + "");
		rowsTemp.add(stu.getStuSex());
		rowsTemp.add(stu.getStuAddress());
		rowsTemp.add(stu.getStuDept());

		rowsData.add(rowsTemp);

	}
}
