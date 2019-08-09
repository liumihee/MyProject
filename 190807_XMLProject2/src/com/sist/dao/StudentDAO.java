package com.sist.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class StudentDAO {
	private static SqlSessionFactory ssf;
	static{
		try {
			//XML을 읽어 온다 => SRC
			Reader reader = Resources.getResourceAsReader("config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
			/*
			 * map.put("studentAllData", "SELECT * FROM student_view")
			 */
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<StudentVO> studentAllData(){
		return ssf.openSession().selectList("studentAllData");
	}
}
