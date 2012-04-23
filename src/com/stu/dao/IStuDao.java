package com.stu.dao;

import java.util.List;
import com.stu.model.*;

/**
 * ����dao�ӿ�
 * @author hsz
 *
 */
public interface IStuDao {
	/**
	 * ���һ���û�
	 * @param StuInfo ����
	 * @return boolean
	 */
	public boolean addUser(StuInfo stu);
	/**
	 * ɾ��һ���û�
	 * @param stuId (����id)
	 * @return boolean
	 */
	public boolean delUser(Integer stuId);
	/**
	 * �޸��û�
	 * @param StuInfo ����
	 * @return boolean
	 */
	public boolean upUser(StuInfo stu);
	/**
	 * ��ѯ�����û�
	 * @return List<StuModel> ���󼯺�
	 */
	public List<StuInfo> queryAllStuInfo();
	/**
	 * ��ѯһ���û�
	 * @param stuId
	 * @return StuInfo
	 */
	public StuInfo queryOneStuById(int stuId);
}
