package com.movie;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBCPConn;
import com.util.Myutil;

public class MovieTicketServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	// forwarding용 메소드
	protected void forward(HttpServletRequest req,HttpServletResponse resp, String url) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@SuppressWarnings("null")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Connection conn = DBCPConn.getConnection();

		MovieTicketDAO dao = new MovieTicketDAO(conn);
		Myutil myutil=new Myutil();
		String cp = req.getContextPath();
		String uri = req.getRequestURI();

		String url;	//forwading용
		// 회원가입
		if(uri.indexOf("signUp.do")!=-1) {		
			//indexOf는 값을 못찾으면 -1을 반환한다.

			url = "/jspProject/signUpMember/signUpMember.jsp";
			forward(req,resp,url);
		}

		else if(uri.indexOf("signUp_ok.do")!=-1) {

			MovieTicketDTO dto = new MovieTicketDTO();

			dto.setUserName(req.getParameter("userName"));
			dto.setUserId(req.getParameter("userId"));
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserBirth(req.getParameter("userBirth"));
			dto.setUserTel(req.getParameter("userTel"));
			dto.setUserEmail(req.getParameter("userEmail"));
			dao.insertData(dto);

			url = cp + "/movie/login.do";	
			resp.sendRedirect(url);
		}

		else if(uri.indexOf("login.do")!=-1) {

			url = "/jspProject/login/movie_login.jsp";
			forward(req,resp,url);
		}

		else if (uri.indexOf("login_ok.do")!=-1){

			String userId = req.getParameter("userId");
			String userPwd = req.getParameter("userPwd");

			MovieTicketDTO dto = dao.getReadData(userId);

			if(dto==null|| !dto.getUserPwd().equals(userPwd)) {
				req.setAttribute("message", "아이디 또는 패스워드가 맞지 않습니다. 확인 후 입력해주세요.");		
				url = "/jspProject/login/movie_login.jsp";
				forward(req,resp,url);					
				return;
			}
			//세션에 id 올려둠
			HttpSession session = req.getSession(true);
			CustomInfo custominfo = new CustomInfo();
			custominfo.setUserId(dto.getUserId());
			System.out.println(custominfo.getUserId());
			session.setAttribute("customInfo", custominfo);
			url = cp+"/movie/main.do";
			resp.sendRedirect(url);
		}


		// 아이디 찾기 구역
		else if(uri.indexOf("findId.do")!=-1) {

			url = "/jspProject/findId/movie_fidId.jsp";
			forward(req,resp,url);
		}

		else if(uri.indexOf("findId_ok.do")!=-1) {

			String userName = req.getParameter("userName");
			String userBirth = req.getParameter("userBirth");
			String userTel = req.getParameter("userTel");

			MovieTicketDTO dto = dao.getReadDataByName(userName);

			if(dto==null||!dto.getUserName().equals(userName)) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/Name 가 틀리면 여기서 멈춰라

			}

			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/Birth 가 틀리면 여기서 멈춰라

			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// class에서 jsp로 넘길때 setAttribute로 넘긴다.
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/tel 가 틀리면 여기서 멈춰라
			}

			req.setAttribute("message", "확인하신 아이디는 [" + dto.getUserId() + "] 입니다");

			url = "/movie/findIdConf.do";
			forward(req,resp,url);

		}

		else if(uri.indexOf("findIdConf.do")!=-1) {

			url = "/jspProject/findId/movie_fidIdConf.jsp";
			forward(req,resp,url);
		}

		// 비밀번호 찾기 구역
		else if(uri.indexOf("findPwd.do")!=-1) {

			url = "/jspProject/findPwd/movie_fidPwd.jsp";
			forward(req,resp,url);
		}


		else if(uri.indexOf("findPwd_ok.do")!=-1) {

			String userId = req.getParameter("userId");
			String userBirth = req.getParameter("userBirth");
			String userTel = req.getParameter("userTel");

			MovieTicketDTO dto = dao.getReadData(userId);

			if(dto==null||!dto.getUserId().equals(userId)) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/Name 가 틀리면 여기서 멈춰라

			}

			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/Birth 가 틀리면 여기서 멈춰라

			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// class에서 jsp로 넘길때 setAttribute로 넘긴다.
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/tel 가 틀리면 여기서 멈춰라
			}

			req.setAttribute("message", "확인하신 비밀번호는 [" + dto.getUserPwd() + "] 입니다");

			url = "/movie/findPwdConf.do";
			forward(req,resp,url);

		}


		else if(uri.indexOf("findPwdConf.do")!=-1) {

			url = "/jspProject/findPwd/movie_fidPwdConf.jsp";
			forward(req,resp,url);
		}


		// 가입여부 확인
		else if(uri.indexOf("signConfirm.do")!=-1){

			url = "/jspProject/signUpMember/checkUpMember.jsp";
			forward(req,resp,url);
		}

		else if(uri.indexOf("signConfirm_ok.do")!=-1) {

			String userName = req.getParameter("userName");
			String userBirth = req.getParameter("userBirth");
			String userTel = req.getParameter("userTel");

			MovieTicketDTO dto = dao.getReadDataByName(userName);

			if(dto==null||!dto.getUserName().equals(userName)) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/signConfirm.do";
				forward(req,resp,url);
				return;		// id/Name 가 틀리면 여기서 멈춰라
			}


			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/signConfirm.do";
				forward(req,resp,url);

				return;		// id/Birth 가 틀리면 여기서 멈춰라
			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// class에서 jsp로 넘길때 setAttribute로 넘긴다.
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/movie/signConfirm.do";
				forward(req,resp,url);

				return;		// id/tel 가 틀리면 여기서 멈춰라
			}

			req.setAttribute("message", "확인하신 아이디는 [" + dto.getUserId() + "] 입니다");

			url = "/movie/signConfirm.do";
			forward(req,resp,url);
		}
		
		else if(uri.indexOf("main.do")!=-1) {	
			url = "/jspProject/cgvMain.jsp";
			forward(req,resp,url);
		}
		// logout작업
		else if(uri.indexOf("logout.do")!=-1) {
			HttpSession session = req.getSession();
			session.removeAttribute("customInfo");		// customInfo에 있는 값 삭제 
			session.invalidate();						// customInfo라는 변수도 삭제
			url = cp+"/movie/main.do";
			resp.sendRedirect(url);
		}else if(uri.indexOf("event.do") != -1) {	
			url = cp+"/store/list.do";
			resp.sendRedirect(url);
		}else if(uri.indexOf("sit.do")!=-1) {
			HttpSession session=req.getSession();		
			String movietype=req.getParameter("movietype");
			String timetype=req.getParameter("timetype");
			String roomtypestring=req.getParameter("roomtype");
			int roomtype=Integer.valueOf(roomtypestring);
			System.out.println(movietype+"��ȭ"+timetype+"�ð�"+roomtype+"�󿵰� ���� ����");
			MovieSitDAO msdao=new MovieSitDAO(DBCPConn.getConnection());
			List<MovieSitDTO> lists=msdao.getLists(movietype, timetype, roomtype);
			req.setAttribute("lists", lists);
			url="/jspProject/movie/sit.jsp";
			forward(req, resp, url);
		}
		
		//��ȭ �� ����
		else if(uri.indexOf("movie_evaluation_select.do")!=-1) {
			url="/jspProject/movie/movie_evaluation_select.jsp";
			forward(req, resp, url);
		}
		
		//��ȭ �� Ȯ��
		else if(uri.indexOf("movie_evaluation.do")!=-1) {
			HttpSession session=req.getSession();
			req.setCharacterEncoding("UTF-8");
			//-----------------------------------------------------------------------
			String movietype=req.getParameter("movietype");
			req.setAttribute("movietype", movietype);
			session.setAttribute("movietype", movietype);
			String userid;
			if(session.getAttribute("customInfo")!=null) {
				userid=((CustomInfo)session.getAttribute("customInfo")).getUserId();
			}else {
			}
			System.out.println(movietype+"��ȭ Ÿ������ ��� �� ��");
			//-----------------------------------------------------------------------
			Movie_AppraisalDAO madao=new Movie_AppraisalDAO(DBCPConn.getConnection());

			String pageNum=req.getParameter("pageNum");
			int currentPage=1;//ó
			if(pageNum!=null){
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=madao.countData(movietype);//
			req.setAttribute("dataCount",new Integer(dataCount));
			int numPerPage=10;//
			int totalPage=myutil.getPageCount(numPerPage, dataCount);

			if(currentPage>totalPage)
				currentPage=totalPage;

			int start=(currentPage-1)*numPerPage+1;
			int end=currentPage*numPerPage;

			List<Movie_AppraisalDTO> lists=madao.getList(start, end, movietype);
			req.setAttribute("lists", lists);
			
			//System.out.println(lists.size());ũ
	
			String listUrl="/study/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			String pageIndexList=myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			
			url="/jspProject/movie/movie_evaluation.jsp";
			forward(req, resp, url);
		}
		
	
		else if(uri.indexOf("movie_evaluation_ok.do")!=-1) {
			HttpSession session=req.getSession();
			req.setCharacterEncoding("UTF-8");
			int star_select=Integer.valueOf(req.getParameter("star_select"));
			String content=req.getParameter("content");
			String movietype=(String)session.getAttribute("movietype");
			System.out.println(movietype+"�� ���� �ȳѾ���°� ����");
			//movietype="���۳༮��";
			Movie_AppraisalDTO dto=new Movie_AppraisalDTO();
			dto.setMovietype(movietype);
			dto.setMsg(content);
			dto.setStar_select(star_select);
			dto.setUserId(((CustomInfo)session.getAttribute("customInfo")).getUserId());
			Movie_AppraisalDAO madao=new Movie_AppraisalDAO(DBCPConn.getConnection());
			madao.insertData(dto);
			url=cp+"/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			System.out.println(url);
			resp.sendRedirect(url);
		}else if(uri.indexOf("movie_select.do")!=-1) {
			HttpSession session=req.getSession();

			if(session.getAttribute("customInfo")==null||((CustomInfo)session.getAttribute("customInfo")).getUserId().equals("")) {
				url=cp+"/jspProject/cgvMain.jsp";
				resp.sendRedirect(url);
				return;
			}
			System.out.println(((CustomInfo)session.getAttribute("customInfo")).getUserId());
			List<Movie_selectDTO> lists=new ArrayList<Movie_selectDTO>();
			Movie_selectDAO msdao=new Movie_selectDAO(DBCPConn.getConnection());
			lists=msdao.getDataList();
			req.setAttribute("lists", lists);
			List<Movie_countDTO> listscount=new ArrayList<Movie_countDTO>();
			listscount=msdao.getDataCount();
			req.setAttribute("listscount", listscount);
			url="/jspProject/movie/movie_select.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("sit_ok.do")!=-1) {
//			boolean autocommit=true;
//			if(conn.getAutoCommit()){
//				conn.setAutoCommit(false);
//			}
			HttpSession session=req.getSession();
			List<MovieSitDTO> lists=new ArrayList<>();
			for(int i=0;i<10;i++){
				for(int j=1;j<=10;j++){
					if(Integer.valueOf(req.getParameter("h"+Integer.valueOf((i*10)+j)))==2){
					MovieSitDTO dto=new MovieSitDTO();
					dto.setMovietype(req.getParameter("movietype"));
					dto.setTimetype(req.getParameter("timetype"));
					dto.setRoomtype(Integer.valueOf(req.getParameter("roomtype")));
					System.out.println(((CustomInfo)session.getAttribute("customInfo")).getUserId());
					dto.setUserId(((CustomInfo)session.getAttribute("customInfo")).getUserId());
					//dto.setUserId("�ӽ� ����� ���̵�");
					dto.setSitnum(((i*10)+j));
					lists.add(dto);
//					MovieSitDAO dao=new MovieSitDAO(conn);
//					if(dao.insert(dto)!=1){//
//							autocommit=false;
//						}
					}
				}
			}
			session.setAttribute("lists", lists);
			for(MovieSitDTO dto:lists) {
			System.out.println(dto.getTimetype());
			System.out.println(dto.getSitnum());
			}
			System.out.println(lists.size());
//			if(autocommit){
//				conn.commit();
//				conn.setAutoCommit(true);
//			}else{
//				conn.rollback();
//				conn.setAutoCommit(true);
//			}
			/*url = cp+"/movie/buy.do";
			resp.sendRedirect(url);
			*/
			url = "/buy/buy.jsp";
			forward(req, resp, url);
		}
		
	}
}

