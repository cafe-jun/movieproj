package com.movie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBCPConn;
import com.util.MyUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieTicketServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	protected void forward(HttpServletRequest req, HttpServletResponse resp,String url) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//req.setCharacterEncoding("UTF-8");

		String cp = req.getContextPath();
		Connection conn=DBCPConn.getConnection();
		//----------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------

		MyUtil myutil=new MyUtil();
		String uri=req.getRequestURI();

		String url;
		MovieTicketDAO dao = new MovieTicketDAO(conn);


		// ȸ������
		if(uri.indexOf("signUp.do")!=-1) {		//indexOf�� ���� ��ã���� -1�� ��ȯ�Ѵ�.

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

			System.out.println(userId);	

			MovieTicketDTO dto = dao.getReadData(userId);

			if(dto==null||!dto.getUserPwd().equals(userPwd)) {

				req.setAttribute("message", "���̵� �Ǵ� �н����尡 ���� �ʽ��ϴ�. Ȯ�� �� �Է����ּ���.");

				url = "/jspProject/login/movie_login.jsp";
				forward(req,resp,url);					
				return;
			}

			//���ǿ� id �÷���
			HttpSession session = req.getSession(true);

			CustomInfo info = new CustomInfo();

			info.setUserId(dto.getUserId());

			System.out.println(info.getUserId());

			session.setAttribute("customInfo", info);

			url = "/study/movie/main.do";
			resp.sendRedirect(url);

		}


		// ���̵� ã�� ����
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

				req.setAttribute("message1", "���̵�");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "��й�ȣ ã��");
				req.setAttribute("message4", "findPwd.do';");
				req.setAttribute("message5", "�α���");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/Name �� Ʋ���� ���⼭ �����

			}

			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message1", "���̵�");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "��й�ȣ ã��");
				req.setAttribute("message4", "findPwd.do';");
				req.setAttribute("message5", "�α���");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/Birth �� Ʋ���� ���⼭ �����

			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// class���� jsp�� �ѱ涧 setAttribute�� �ѱ��.
				req.setAttribute("message1", "���̵�");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "��й�ȣ ã��");
				req.setAttribute("message4", "findPwd.do';");
				req.setAttribute("message5", "�α���");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/tel �� Ʋ���� ���⼭ �����
			}

			req.setAttribute("message1", "���̵�");
			req.setAttribute("message2", "Ȯ���Ͻ� ���̵�� [" + dto.getUserId() + "] �Դϴ�");
			req.setAttribute("message3", "��й�ȣ ã��");
			req.setAttribute("message4", "findPwd.do';");
			req.setAttribute("message5", "�α���");
			req.setAttribute("message6", "login.do';");

			url = "/movie/findIdConf.do";
			forward(req,resp,url);

		}

		else if(uri.indexOf("findIdConf.do")!=-1) {

			url = "/jspProject/movie_confirm.jsp";
			forward(req,resp,url);
		}


		// ��й�ȣ ã�� ����
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

				req.setAttribute("message1", "���̵�");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "���̵� ã��");
				req.setAttribute("message4", "findId.do';");
				req.setAttribute("message5", "�α���");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/Name �� Ʋ���� ���⼭ �����

			}

			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message1", "���̵�");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "���̵� ã��");
				req.setAttribute("message4", "findId.do';");
				req.setAttribute("message5", "�α���");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/Birth �� Ʋ���� ���⼭ �����

			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// class���� jsp�� �ѱ涧 setAttribute�� �ѱ��.
				req.setAttribute("message1", "���̵�");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "���̵� ã��");
				req.setAttribute("message4", "findId.do';");
				req.setAttribute("message5", "�α���");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/tel �� Ʋ���� ���⼭ �����
			}

			req.setAttribute("message1", "���̵�");
			req.setAttribute("message2", "Ȯ���Ͻ� ��й�ȣ�� [" + dto.getUserPwd() + "] �Դϴ�");
			req.setAttribute("message3", "���̵� ã��");
			req.setAttribute("message4", "findId.do';");
			req.setAttribute("message5", "�α���");
			req.setAttribute("message6", "login.do';");


			url = "/movie/findPwdConf.do";
			forward(req,resp,url);
		}


		else if(uri.indexOf("findPwdConf.do")!=-1) {

			url = "/jspProject/movie_confirm.jsp";
			forward(req,resp,url);
		}


		// ���Կ��� Ȯ��
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

				req.setAttribute("message1", "��������");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "����ȭ��");
				req.setAttribute("message4", "main.do';");
				req.setAttribute("message5", "ȸ����������");
				req.setAttribute("message6", "signUpGo.do';");

				url = "/movie/signUpConfirm.do";
				forward(req,resp,url);
				return;		// id/Name �� Ʋ���� ���⼭ �����
			}


			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message1", "��������");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "����ȭ��");
				req.setAttribute("message4", "main.do';");
				req.setAttribute("message5", "ȸ����������");
				req.setAttribute("message6", "signUpGo.do';");

				url = "/movie/signUpConfirm.do";
				forward(req,resp,url);

				return;		// id/Birth �� Ʋ���� ���⼭ �����
			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// class���� jsp�� �ѱ涧 setAttribute�� �ѱ��.
				req.setAttribute("message1", "��������");
				req.setAttribute("message2", "ȸ�������� �������� �ʽ��ϴ�.");
				req.setAttribute("message3", "����ȭ��");
				req.setAttribute("message4", "main.do';");
				req.setAttribute("message5", "ȸ����������");
				req.setAttribute("message6", "signUpGo.do';");

				url = "/movie/signUpConfirm.do";
				forward(req,resp,url);

				return;		// id/tel �� Ʋ���� ���⼭ �����
			}

			req.setAttribute("message1", "��������");
			req.setAttribute("message2", "Ȯ���Ͻ� ���̵�� [" + dto.getUserId() + "] �Դϴ�");
			req.setAttribute("message3", "����ȭ��");
			req.setAttribute("message4", "main.do';");
			req.setAttribute("message5", "ȸ����������");
			req.setAttribute("message6", "signUpGo.do';");

			url = "/movie/signUpConfirm.do";
			forward(req,resp,url);
		}

		else if(uri.indexOf("signUpConfirm.do")!=-1){

			url = "/jspProject/movie_confirm.jsp";
			forward(req,resp,url);
		}
		
		else if(uri.indexOf("signUpGo.do")!=-1){

			url = "/jspProject/signUpMember/signUpMember.jsp";
			forward(req,resp,url);
		}
		

		
		else if(uri.indexOf("main.do")!=-1) {

			url = "/jspProject/cgvMain.jsp";
			forward(req,resp,url);

		}


		// logout�۾�
		else if(uri.indexOf("logout.do")!=-1) {

			HttpSession session = req.getSession();
			session.removeAttribute("customInfo");		// customInfo�� �ִ� �� ���� 
			session.invalidate();						// customInfo��� ������ ����

			url = "/movie/cgvMain.jsp";
			forward(req,resp,url);
		}







		//��ȭ �¼� Ȯ��
		else if(uri.indexOf("sit.do")!=-1) {
			HttpSession session=req.getSession();

			String movietype=req.getParameter("movietype");
			String timetype=req.getParameter("timetype");
			String roomtypestring=req.getParameter("roomtype");
			int roomtype=Integer.valueOf(roomtypestring);
			System.out.println(movietype+"��ȭ"+timetype+"�ð�"+roomtype+"�󿵰� ���� ����");

			Movie_PaymentDAO msdao=new Movie_PaymentDAO(DBCPConn.getConnection());
			List<Movie_PaymentDTO> lists = msdao.getLists(movietype, timetype, roomtype);
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
			int currentPage=1;//ó�������ϴ� ������
			if(pageNum!=null){
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=madao.countData(movietype);//��ü ������ ���ϱ�
			req.setAttribute("dataCount",new Integer(dataCount));
			int numPerPage=10;//�� �������� ���ϱ�
			int totalPage=myutil.getPageCount(numPerPage, dataCount);
			//��ü������������ ǥ���� �������� �� ū ���
			if(currentPage>totalPage)
				currentPage=totalPage;
			//db���� ������ �������� ���۰� ��
			int start=(currentPage-1)*numPerPage+1;
			int end=currentPage*numPerPage;

			List<Movie_AppraisalDTO> lists=madao.getList(start, end, movietype);
			req.setAttribute("lists", lists);

			//System.out.println(lists.size());ũ�����
			//������ ó��
			String listUrl="/study/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			String pageIndexList=myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);

			url="/jspProject/movie/movie_evaluation.jsp";
			forward(req, resp, url);
		}

		//��ȭ ��� �ۼ�
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
		}


		//��ȭ ���� ����
		else if(uri.indexOf("movie_select.do")!=-1) {

			HttpSession session=req.getSession();

			if(session.getAttribute("customInfo")==null||((CustomInfo)session.getAttribute("customInfo")).getUserId().equals("")) {

				System.out.println("��α��ν�..");

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
		}

		//��ȭ ���� ��������.
		else if(uri.indexOf("sit_ok.do")!=-1) {

			//					boolean autocommit=true;
			//					try {
			//						if(conn.getAutoCommit()){
			//							conn.setAutoCommit(false);
			//						}
			//					} catch (SQLException e) {
			//						// TODO Auto-generated catch block
			//						e.printStackTrace();
			//					}


			HttpSession session=req.getSession();

			List<Movie_PaymentDTO> lists=new ArrayList<>();

			for(int i=0;i<10;i++){
				for(int j=1;j<=10;j++){

					if(Integer.valueOf(req.getParameter("h"+Integer.valueOf((i*10)+j)))==2){

						Movie_PaymentDTO dto=new Movie_PaymentDTO();

						dto.setMovietype(req.getParameter("movietype"));
						dto.setTimetype(req.getParameter("timetype"));
						dto.setRoomtype(Integer.valueOf(req.getParameter("roomtype")));

						System.out.println(((CustomInfo)session.getAttribute("customInfo")).getUserId());

						dto.setUserId(((CustomInfo)session.getAttribute("customInfo")).getUserId());
						//dto.setUserId("�ӽ� ����� ���̵�");
						dto.setSitnum(((i*10)+j));

						lists.add(dto);

						Movie_PaymentDAO mpdao=new Movie_PaymentDAO(conn);
						//							if(mpdao.insert(dto)!=1){//Ű�� ������
						//									autocommit=false;
						//								}
						//							
					}
				}
			}
			session.setAttribute("lists", lists);
			for(Movie_PaymentDTO dto:lists) {
				System.out.println(dto.getTimetype());
				System.out.println(dto.getSitnum());
			}
			System.out.println(lists.size());
			//					try {
			//					if(autocommit){
			//						conn.commit();
			//						conn.setAutoCommit(true);
			//					}else{
			//						conn.rollback();
			//						conn.setAutoCommit(true);
			//					}
			//					}catch (Exception e) {
			//						// TODO: handle exception
			//					}
		}
		
		
		else if(uri.indexOf("updated.do")!=-1) {
			
			HttpSession session = req.getSession();
			CustomInfo customInfo = (CustomInfo)session.getAttribute("customInfo");
			
			MovieTicketDTO dto = dao.getReadData(customInfo.getUserId());
			session.setAttribute("dto", dto);
			
			url = "/jspProject/signUpMember/updateMember.jsp";
			forward(req, resp, url);
		
		}
		
		
		else if(uri.indexOf("updated_ok.do")!=-1) {
			
			String userId = req.getParameter("userId");
			
			MovieTicketDTO dto = new MovieTicketDTO();
			
			dto.setUserId(userId);
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserName(req.getParameter("userName"));
			dto.setUserBirth(req.getParameter("userBirth"));
			dto.setUserTel(req.getParameter("userTel"));
			dto.setUserEmail(req.getParameter("userEmail"));
			
			dao.update(dto);
			
			url = "/movie/main.do";
			forward(req, resp, url);
					
			
		}
		

	}

}
