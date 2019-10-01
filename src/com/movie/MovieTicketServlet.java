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
<<<<<<< HEAD
	
=======

>>>>>>> loadtest
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
<<<<<<< HEAD
		
		//req.setCharacterEncoding("UTF-8");
		
=======

		//req.setCharacterEncoding("UTF-8");

>>>>>>> loadtest
		String cp = req.getContextPath();
		Connection conn=DBCPConn.getConnection();
		//----------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------
<<<<<<< HEAD
		
		MyUtil myutil=new MyUtil();
		String uri=req.getRequestURI();
		
		String url;
		MovieTicketDAO dao = new MovieTicketDAO(conn);
		
		
		// 회원가입
				if(uri.indexOf("signUp.do")!=-1) {		//indexOf는 값을 못찾으면 -1을 반환한다.

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

						req.setAttribute("message", "아이디 또는 패스워드가 맞지 않습니다. 확인 후 입력해주세요.");
						
						url = "/jspProject/login/movie_login.jsp";
						forward(req,resp,url);					
						return;
					}

					//세션에 id 올려둠
					HttpSession session = req.getSession(true);

					CustomInfo info = new CustomInfo();

					info.setUserId(dto.getUserId());
					
					System.out.println(info.getUserId());

					session.setAttribute("customInfo", info);

					url = "/study/movie/main.do";
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

					url = cp;
					resp.sendRedirect(url);
				}

		//영화 좌석 확인
		else if(uri.indexOf("sit.do")!=-1) {
			HttpSession session=req.getSession();
				
=======

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

			url = "/jspProject/cgvMain.jsp";
			forward(req,resp,url);
		}







		//��ȭ �¼� Ȯ��
		else if(uri.indexOf("sit.do")!=-1) {
			HttpSession session=req.getSession();

>>>>>>> loadtest
			String movietype=req.getParameter("movietype");
			String timetype=req.getParameter("timetype");
			String roomtypestring=req.getParameter("roomtype");
			int roomtype=Integer.valueOf(roomtypestring);
<<<<<<< HEAD
			System.out.println(movietype+"영화"+timetype+"시간"+roomtype+"상영관 예매 시작");

			MovieSitDAO msdao=new MovieSitDAO(DBCPConn.getConnection());
			List<MovieSitDTO> lists=msdao.getLists(movietype, timetype, roomtype);
			req.setAttribute("lists", lists);
			
=======
			System.out.println(movietype+"��ȭ"+timetype+"�ð�"+roomtype+"�󿵰� ���� ����");

			Movie_PaymentDAO msdao=new Movie_PaymentDAO(DBCPConn.getConnection());
			List<Movie_PaymentDTO> lists = msdao.getLists(movietype, timetype, roomtype);
			req.setAttribute("lists", lists);

>>>>>>> loadtest

			url="/jspProject/movie/sit.jsp";
			forward(req, resp, url);
		}
<<<<<<< HEAD
		
		//영화 평가 선택
=======

		//��ȭ �� ����
>>>>>>> loadtest
		else if(uri.indexOf("movie_evaluation_select.do")!=-1) {
			url="/jspProject/movie/movie_evaluation_select.jsp";
			forward(req, resp, url);
		}
<<<<<<< HEAD
		
		//영화 평가 확인
=======

		//��ȭ �� Ȯ��
>>>>>>> loadtest
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
<<<<<<< HEAD
			System.out.println(movietype+"영화 타입으로 들어 온 값");
=======
			System.out.println(movietype+"��ȭ Ÿ������ ��� �� ��");
>>>>>>> loadtest
			//-----------------------------------------------------------------------
			Movie_AppraisalDAO madao=new Movie_AppraisalDAO(DBCPConn.getConnection());

			String pageNum=req.getParameter("pageNum");
<<<<<<< HEAD
			int currentPage=1;//처음시행하는 페이지
			if(pageNum!=null){
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=madao.countData(movietype);//전체 데이터 구하기
			req.setAttribute("dataCount",new Integer(dataCount));
			int numPerPage=10;//총 페이지수 구하기
			int totalPage=myutil.getPageCount(numPerPage, dataCount);
			//전체페이지수보다 표시할 페이지가 더 큰 경우
			if(currentPage>totalPage)
				currentPage=totalPage;
			//db에서 가져올 데이터의 시작과 끝
=======
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
>>>>>>> loadtest
			int start=(currentPage-1)*numPerPage+1;
			int end=currentPage*numPerPage;

			List<Movie_AppraisalDTO> lists=madao.getList(start, end, movietype);
			req.setAttribute("lists", lists);
<<<<<<< HEAD
			
			//System.out.println(lists.size());크기재기
			//페이지 처리
			String listUrl="/study/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			//String listUrl="/study/movie/movie_evaluation.do?movietype="+movietype;
			String pageIndexList=myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			
			url="/jspProject/movie/movie_evaluation.jsp";
			forward(req, resp, url);
		}
		
		//영화 평과 작성
=======

			//System.out.println(lists.size());ũ�����
			//������ ó��
			String listUrl="/study/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			String pageIndexList=myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);

			url="/jspProject/movie/movie_evaluation.jsp";
			forward(req, resp, url);
		}

		//��ȭ ��� �ۼ�
>>>>>>> loadtest
		else if(uri.indexOf("movie_evaluation_ok.do")!=-1) {

			HttpSession session=req.getSession();
			req.setCharacterEncoding("UTF-8");
<<<<<<< HEAD
			int star_select=Integer.valueOf(req.getParameter("star_select"));
			String content=req.getParameter("content");
			String movietype=(String)session.getAttribute("movietype");
			System.out.println(movietype+"이 값이 안넘어오는것 같아");
			//movietype="나쁜녀석들";
=======

			int star_select=Integer.valueOf(req.getParameter("star_select"));
			String content=req.getParameter("content");
			String movietype=(String)session.getAttribute("movietype");
			System.out.println(movietype+"�� ���� �ȳѾ���°� ����");

			//movietype="���۳༮��";
>>>>>>> loadtest
			Movie_AppraisalDTO dto=new Movie_AppraisalDTO();
			dto.setMovietype(movietype);
			dto.setMsg(content);
			dto.setStar_select(star_select);
			dto.setUserId(((CustomInfo)session.getAttribute("customInfo")).getUserId());
<<<<<<< HEAD
			Movie_AppraisalDAO madao=new Movie_AppraisalDAO(DBCPConn.getConnection());
			madao.insertData(dto);
			
=======

			Movie_AppraisalDAO madao=new Movie_AppraisalDAO(DBCPConn.getConnection());
			madao.insertData(dto);

>>>>>>> loadtest
			url=cp+"/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			System.out.println(url);

			resp.sendRedirect(url);
		}
<<<<<<< HEAD
		
		
		//영화 예매 선택
		else if(uri.indexOf("movie_select.do")!=-1) {
			HttpSession session=req.getSession();
			//로그인 하지 않았을때
			System.out.println("영화 예매 진입");
			if(session.getAttribute("customInfo")==null||((CustomInfo)session.getAttribute("customInfo")).getUserId().equals("")) {
				System.out.println("비로그인시..");
=======


		//��ȭ ���� ����
		else if(uri.indexOf("movie_select.do")!=-1) {

			HttpSession session=req.getSession();

			if(session.getAttribute("customInfo")==null||((CustomInfo)session.getAttribute("customInfo")).getUserId().equals("")) {

				System.out.println("��α��ν�..");

>>>>>>> loadtest
				url=cp+"/jspProject/cgvMain.jsp";
				resp.sendRedirect(url);
				return;
			}
<<<<<<< HEAD
			
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
		
		//영화 결제 페이지로.
		else if(uri.indexOf("sit_ok.do")!=-1) {
//			boolean autocommit=true;
//			if(conn.getAutoCommit()){
//				conn.setAutoCommit(false);
//			}
			HttpSession session=req.getSession();
			List<MovieSitDTO> lists=new ArrayList<>();
=======
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

>>>>>>> loadtest
			for(int i=0;i<10;i++){
				for(int j=1;j<=10;j++){

					if(Integer.valueOf(req.getParameter("h"+Integer.valueOf((i*10)+j)))==2){
<<<<<<< HEAD
					MovieSitDTO dto=new MovieSitDTO();
					dto.setMovietype(req.getParameter("movietype"));
					dto.setTimetype(req.getParameter("timetype"));
					dto.setRoomtype(Integer.valueOf(req.getParameter("roomtype")));
					System.out.println(((CustomInfo)session.getAttribute("customInfo")).getUserId());
					dto.setUserId(((CustomInfo)session.getAttribute("customInfo")).getUserId());
					//dto.setUserId("임시 사용자 아이디");
					dto.setSitnum(((i*10)+j));
					lists.add(dto);
//					MovieSitDAO dao=new MovieSitDAO(conn);
//					if(dao.insert(dto)!=1){//키값 오류시
//							autocommit=false;
//						}
=======

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
>>>>>>> loadtest
					}
				}
			}
			session.setAttribute("lists", lists);
<<<<<<< HEAD
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
		}
		
	}
		
}
=======
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
>>>>>>> loadtest
