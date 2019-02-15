package cn.itcast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.entity.Student;
import cn.itcast.mapper.StudentMapper;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	@Transactional
	public void insertStudent(Student stu) {
		studentMapper.insertStudent(stu);
		// 演示一下发生异常以后，会不会事务回滚
		//System.out.println(10/0);
	}
	
	
}
