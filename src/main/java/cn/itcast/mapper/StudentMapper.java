package cn.itcast.mapper;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.itcast.entity.Student;

public class StudentMapper extends JdbcDaoSupport{
	public void insertStudent(Student stu) {
		String sql = "insert into student (name, age) values(?, ?)";
		this.getJdbcTemplate().update(sql, stu.getName(), stu.getAge());
	}
}
