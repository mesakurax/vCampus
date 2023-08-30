package module;

import entity.StudentRoll;
import entityModel.StudentRollModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 学生学籍信息Controller
 * 
 *
 */
public class StudentRollController {
	/**
	 * 学生学籍信息Model
	 */
	private StudentRollModel model;
	
	public StudentRollController() {
		this.model = new StudentRollModel();
	}
	
	/**
	 * 查找学生信息响应函数
	 * 根据提供的不同key（学号，姓名）返回结果
	 * 
	 * @param info 查找的key
	 * @return 所查询学生详细信息
	 */
	public StudentRoll query_ID(StudentRoll info) {
		try {
			ResultSet rs = (ResultSet)model.Search(info,1);
			if (rs.next()) {
				System.out.println("JIANCHA SEX"+rs.getString("stuSex"));
				return new StudentRoll(rs.getString("stuId"), rs.getString("stuName"), rs.getString("stuSex"), rs.getString("stuGrade"),
					rs.getString("stuPlace"), rs.getString("stuDepart"), rs.getString("stuProf"));
			}
			
			
			return null;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}

	public StudentRoll query_Name(StudentRoll info) {
		try {
			ResultSet rs = (ResultSet)model.Search(info,2);

			if (rs.next()) {System.out.println("JIANCHA SEX"+rs.getString("stuSex"));
				return new StudentRoll(rs.getString("stuId"), rs.getString("stuName"), rs.getString("stuSex"), rs.getString("stuGrade"),
						rs.getString("stuPlace"), rs.getString("stuDepart"), rs.getString("stuProf"));
			}


			return null;

		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 查询所有学生信息响应函数
	 * 
	 * @return 所有学生详细信息
	 */
	public Vector<StudentRoll> queryAll() {
		try {
			ResultSet rs = (ResultSet)model.Search(null,3);
			Vector<StudentRoll> v = new Vector<StudentRoll>();
			
			while (rs.next()) {
				StudentRoll temp =new StudentRoll(rs.getString("stuId"), rs.getString("stuName"), rs.getString("stuSex"), rs.getString("stuGrade"),
						rs.getString("stuPlace"), rs.getString("stuDepart"), rs.getString("stuProf"));
				v.add(temp);
			}
			
			return ((Vector<StudentRoll>) v);
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}

	/**
	 * 添加学生信息响应函数
	 * 
	 * @param info 要添加的学生
	 * @return 是否添加成功
	 */
	public boolean addInfo(StudentRoll info) {
		return model.Insert(info);
	}
	
	/**
	 * 修改学生信息响应函数
	 * 
	 * @param info 要修改的学生
	 * @return 是否修改成功
	 */
	public boolean modifyInfo(StudentRoll info) {
		return model.Modify(info);
	}
	
	/**
	 * 删除学生信息响应函数
	 * 
	 * @param info 要删除的学生
	 * @return 是否删除成功
	 */
	public boolean deleteInfo(StudentRoll info) {
		return model.Delete(info);
	}
	
}
