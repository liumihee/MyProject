package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;

public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		List<StudentVO> list = StudentDAO.studentAllData();
		request.setAttribute("list", list);
		return "main/list.jsp";
	}

}
