package com.sist.model;

import java.util.List;
import java.io.*;
import java.net.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.DataBoardDAO;
import com.sist.dao.DataBoardReplyVO;
import com.sist.dao.DataBoardVO;

//자바, 태그형 => 분리(MV)
public class Model {
	public void databoard_list(HttpServletRequest request){
		//list.jsp 처리
		String page = request.getParameter("page");
		/*
		 * list.jsp?page= ====> "" > .equals("")
		 * list.jsp ==========> null
		 */
		if(page==null)
			page="1";
		
		int curpage = Integer.parseInt(page);
		//데이터 읽기
		DataBoardDAO dao = new DataBoardDAO();
		List<DataBoardVO> list = dao.databoardListData(curpage);
		int totalpage = dao.databoardTotalPage();
		
		//jsp에 결과값 전송
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		
	}
	
	public void databoard_insert_ok(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			String path = "c:\\upload"; //저장하는 폴더
			String enctype = "UTF-8";
			int size = 100*1024*1024; //100MB
			MultipartRequest mr = new MultipartRequest(request, path, size, enctype, new DefaultFileRenamePolicy());
			// new DefaultFileRenamePolicy() => 같은 파일명 => a.jpg => a1.jpg
			
			String name = mr.getParameter("name");
			String subject = mr.getParameter("subject");
			String content = mr.getParameter("content");
			String pwd = mr.getParameter("pwd");
			
			DataBoardVO vo = new DataBoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			String filename = mr.getOriginalFileName("upload");
			
			if(filename==null){ //업로드를 안 한 상태
				vo.setFilename("");
				vo.setFilesize(0);
			} else {
				File file = new File(path + "\\" + filename);
				vo.setFilename(filename);
				vo.setFilesize((int)file.length());
			}
			
			DataBoardDAO dao = new DataBoardDAO();
			dao.databoardInsert(vo);
			
		} catch (Exception e) {}
		
	}
	
	public void dataBoard_detail(HttpServletRequest request){
		//detail.jsp?no=${vo.no }
		String no = request.getParameter("no");
		String curpage = request.getParameter("page");
		//DAO 연결
		DataBoardDAO dao = new DataBoardDAO();
		DataBoardVO vo = dao.databoradDetailData(Integer.parseInt(no));
		//받은 데이터 => JSP 전송
		request.setAttribute("vo", vo);
		request.setAttribute("curpage", curpage);
		
		//댓글
		List<DataBoardReplyVO> list = dao.databoardReplyData(Integer.parseInt(no));
		request.setAttribute("list", list);
		request.setAttribute("len", list.size());
	}
	
	public void databoard_update_data(HttpServletRequest request){
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		//DAO 연동
		DataBoardDAO dao = new DataBoardDAO();
		DataBoardVO vo = dao.databoardUpdateData(Integer.parseInt(no));
		//request에 값을 담아준다 ==> JSP에서 request에 있는 데이터 출력
		request.setAttribute("vo", vo); //request가 초기화(X) , 필요한 데이터 추가 
		request.setAttribute("curpage", page);
		/*
		 * 빈 ==> 사용자 정의 데이터형 (읽기, 쓰기) ==> getter/setter
		 * 모델 ==> 요청 처리
		 * 뷰 ==> 화면 출력
		 */
	}
	
	public void databoard_update_ok(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		//데이터 받기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		
		DataBoardVO vo = new DataBoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//DAO로 전송 => 처리 => 결과값 => update_ok.jsp 전송
		DataBoardDAO dao = new DataBoardDAO();
		boolean bCheck = dao.databoardUpdate(vo);
		
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", page);
		request.setAttribute("no", no);
	}
	
	public void login(HttpServletRequest request){
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//DAO 연결
		DataBoardDAO dao = new DataBoardDAO();
		String result = dao.isLogin(id, pwd);
		
		if(!(result.equals("NOID")||result.equals("NOPWD"))){
			//세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", result);
		}
		
		request.setAttribute("result", result);
	}
	
	public void reply_insert(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		String msg = request.getParameter("msg");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		//request => session, cookie
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		DataBoardReplyVO vo = new DataBoardReplyVO();
		vo.setId(id);
		vo.setName(name);
		vo.setBno(Integer.parseInt(bno));
		vo.setMsg(msg);
		
		//DAO로 전송 => 오라클 연결
		DataBoardDAO dao = new DataBoardDAO();
		dao.replyInsert(vo);
		//이동 => 필요한 데이터 전달 ==> bno, page
		request.setAttribute("bno", bno);
		request.setAttribute("page", page);
	}
	
	public void reply_delete(HttpServletRequest request){
		String no = request.getParameter("no");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		
		//DAO => 댓글 삭제
		DataBoardDAO dao = new DataBoardDAO();
		dao.replyDelete(Integer.parseInt(no));
		//jsp => 필요한 데이터 전송
		request.setAttribute("no", bno);
		request.setAttribute("page", page);
	}
	
	public void reply_update(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		String no = request.getParameter("no");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		String msg = request.getParameter("msg");
		
		//DAO => 댓글 수정
		DataBoardDAO dao = new DataBoardDAO();
		dao.replyUpdate(Integer.parseInt(no), msg);
		//jsp => 필요한 데이터 전송
		request.setAttribute("no", bno);
		request.setAttribute("page", page);
	}
	
	//삭제
	public void databoard_delete(HttpServletRequest request){
		//detail.jsp => no, page => delete.jsp로 전송
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		
		//delete.jsp로 no, page를 전송
		request.setAttribute("no", no);
		request.setAttribute("page", page);
	}
	
	public void databoard_delete_ok(HttpServletRequest request){
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		String pwd = request.getParameter("pwd");
		
		//DB연동
		DataBoardDAO dao = new DataBoardDAO();
		DataBoardVO vo = dao.databoardFileInfo(Integer.parseInt(no));
		boolean bCheck = dao.databoard_delete(Integer.parseInt(no), pwd);
		if(bCheck == true){
			if(vo.getFilesize()>0){
				try {
					File file = new File("c:\\upload\\" + vo.getFilename());
					file.delete();
				} catch (Exception e) {}
			}
		}
		
		//JSP 전송
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", page); //list.jsp
	}
}
