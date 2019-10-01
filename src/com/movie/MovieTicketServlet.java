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
		
		
		// ÌöåÏõêÍ∞ÄÏûÖ
				if(uri.indexOf("signUp.do")!=-1) {		//indexOfÎäî Í∞íÏùÑ Î™ªÏ∞æÏúºÎ©¥ -1ÏùÑ Î∞òÌôòÌïúÎã§.

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

						req.setAttribute("message", "ÏïÑÏù¥Îîî ÎòêÎäî Ìå®Ïä§ÏõåÎìúÍ∞Ä ÎßûÏßÄ ÏïäÏäµÎãàÎã§. ÌôïÏù∏ ÌõÑ ÏûÖÎ†•Ìï¥Ï£ºÏÑ∏Ïöî.");
						
						url = "/jspProject/login/movie_login.jsp";
						forward(req,resp,url);					
						return;
					}

					//ÏÑ∏ÏÖòÏóê id Ïò¨Î†§Îë†
					HttpSession session = req.getSession(true);

					CustomInfo info = new CustomInfo();

					info.setUserId(dto.getUserId());
					
					System.out.println(info.getUserId());

					session.setAttribute("customInfo", info);

					url = "/study/movie/main.do";
					resp.sendRedirect(url);

				}


				// ÏïÑÏù¥Îîî Ï∞æÍ∏∞ Íµ¨Ïó≠
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

						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/findIdConf.do";
						forward(req,resp,url);

						return;		// id/Name Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº

					}

					if(dto==null||!dto.getUserBirth().equals(userBirth)) {

						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/findIdConf.do";
						forward(req,resp,url);

						return;		// id/Birth Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº

					}

					if(dto==null||!dto.getUserTel().equals(userTel)) {

						// classÏóêÏÑú jspÎ°ú ÎÑòÍ∏∏Îïå setAttributeÎ°ú ÎÑòÍ∏¥Îã§.
						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/findIdConf.do";
						forward(req,resp,url);

						return;		// id/tel Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº
					}

					req.setAttribute("message", "ÌôïÏù∏ÌïòÏã† ÏïÑÏù¥ÎîîÎäî [" + dto.getUserId() + "] ÏûÖÎãàÎã§");

					url = "/movie/findIdConf.do";
					forward(req,resp,url);

				}

				else if(uri.indexOf("findIdConf.do")!=-1) {

					url = "/jspProject/findId/movie_fidIdConf.jsp";
					forward(req,resp,url);
				}

				// ÎπÑÎ∞ÄÎ≤àÌò∏ Ï∞æÍ∏∞ Íµ¨Ïó≠
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

						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/findPwdConf.do";
						forward(req,resp,url);

						return;		// id/Name Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº

					}

					if(dto==null||!dto.getUserBirth().equals(userBirth)) {

						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/findPwdConf.do";
						forward(req,resp,url);

						return;		// id/Birth Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº

					}

					if(dto==null||!dto.getUserTel().equals(userTel)) {

						// classÏóêÏÑú jspÎ°ú ÎÑòÍ∏∏Îïå setAttributeÎ°ú ÎÑòÍ∏¥Îã§.
						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/findPwdConf.do";
						forward(req,resp,url);

						return;		// id/tel Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº
					}

					req.setAttribute("message", "ÌôïÏù∏ÌïòÏã† ÎπÑÎ∞ÄÎ≤àÌò∏Îäî [" + dto.getUserPwd() + "] ÏûÖÎãàÎã§");

					url = "/movie/findPwdConf.do";
					forward(req,resp,url);

				}


				else if(uri.indexOf("findPwdConf.do")!=-1) {

					url = "/jspProject/findPwd/movie_fidPwdConf.jsp";
					forward(req,resp,url);
				}


				// Í∞ÄÏûÖÏó¨Î∂Ä ÌôïÏù∏
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

						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/signConfirm.do";
						forward(req,resp,url);
						return;		// id/Name Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº
					}


					if(dto==null||!dto.getUserBirth().equals(userBirth)) {

						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/signConfirm.do";
						forward(req,resp,url);

						return;		// id/Birth Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº
					}

					if(dto==null||!dto.getUserTel().equals(userTel)) {

						// classÏóêÏÑú jspÎ°ú ÎÑòÍ∏∏Îïå setAttributeÎ°ú ÎÑòÍ∏¥Îã§.
						req.setAttribute("message", "ÌöåÏõêÏ†ïÎ≥¥Í∞Ä Ï°¥Ïû¨ÌïòÏßÄ ÏïäÏäµÎãàÎã§.");

						url = "/movie/signConfirm.do";
						forward(req,resp,url);

						return;		// id/tel Í∞Ä ÌãÄÎ¶¨Î©¥ Ïó¨Í∏∞ÏÑú Î©àÏ∂∞Îùº
					}

					req.setAttribute("message", "ÌôïÏù∏ÌïòÏã† ÏïÑÏù¥ÎîîÎäî [" + dto.getUserId() + "] ÏûÖÎãàÎã§");

					url = "/movie/signConfirm.do";
					forward(req,resp,url);
				}
				
				else if(uri.indexOf("main.do")!=-1) {
					
					url = "/jspProject/cgvMain.jsp";
					forward(req,resp,url);
					
				}
				

				// logoutÏûëÏóÖ
				else if(uri.indexOf("logout.do")!=-1) {

					HttpSession session = req.getSession();

					session.removeAttribute("customInfo");		// customInfoÏóê ÏûàÎäî Í∞í ÏÇ≠Ï†ú 
					session.invalidate();						// customInfoÎùºÎäî Î≥ÄÏàòÎèÑ ÏÇ≠Ï†ú

					url = cp;
					resp.sendRedirect(url);
				}

		//ÏòÅÌôî Ï¢åÏÑù ÌôïÏù∏
		else if(uri.indexOf("sit.do")!=-1) {
			HttpSession session=req.getSession();
				
=======

		MyUtil myutil=new MyUtil();
		String uri=req.getRequestURI();

		String url;
		MovieTicketDAO dao = new MovieTicketDAO(conn);


		// »∏ø¯∞°¿‘
		if(uri.indexOf("signUp.do")!=-1) {		//indexOf¥¬ ∞™¿ª ∏¯√£¿∏∏È -1¿ª π›»Ø«—¥Ÿ.

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

				req.setAttribute("message", "æ∆¿Ãµ ∂«¥¬ ∆–Ω∫øˆµÂ∞° ∏¬¡ˆ æ Ω¿¥œ¥Ÿ. »Æ¿Œ »ƒ ¿‘∑¬«ÿ¡÷ººø‰.");

				url = "/jspProject/login/movie_login.jsp";
				forward(req,resp,url);					
				return;
			}

			//ººº«ø° id ø√∑¡µ“
			HttpSession session = req.getSession(true);

			CustomInfo info = new CustomInfo();

			info.setUserId(dto.getUserId());

			System.out.println(info.getUserId());

			session.setAttribute("customInfo", info);

			url = "/study/movie/main.do";
			resp.sendRedirect(url);

		}


		// æ∆¿Ãµ √£±‚ ±∏ø™
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

				req.setAttribute("message1", "æ∆¿Ãµ");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "∫Òπ–π¯»£ √£±‚");
				req.setAttribute("message4", "findPwd.do';");
				req.setAttribute("message5", "∑Œ±◊¿Œ");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/Name ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û

			}

			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message1", "æ∆¿Ãµ");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "∫Òπ–π¯»£ √£±‚");
				req.setAttribute("message4", "findPwd.do';");
				req.setAttribute("message5", "∑Œ±◊¿Œ");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/Birth ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û

			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// classø°º≠ jsp∑Œ ≥—±Ê∂ß setAttribute∑Œ ≥—±‰¥Ÿ.
				req.setAttribute("message1", "æ∆¿Ãµ");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "∫Òπ–π¯»£ √£±‚");
				req.setAttribute("message4", "findPwd.do';");
				req.setAttribute("message5", "∑Œ±◊¿Œ");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findIdConf.do";
				forward(req,resp,url);

				return;		// id/tel ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û
			}

			req.setAttribute("message1", "æ∆¿Ãµ");
			req.setAttribute("message2", "»Æ¿Œ«œΩ≈ æ∆¿Ãµ¥¬ [" + dto.getUserId() + "] ¿‘¥œ¥Ÿ");
			req.setAttribute("message3", "∫Òπ–π¯»£ √£±‚");
			req.setAttribute("message4", "findPwd.do';");
			req.setAttribute("message5", "∑Œ±◊¿Œ");
			req.setAttribute("message6", "login.do';");

			url = "/movie/findIdConf.do";
			forward(req,resp,url);

		}

		else if(uri.indexOf("findIdConf.do")!=-1) {

			url = "/jspProject/movie_confirm.jsp";
			forward(req,resp,url);
		}


		// ∫Òπ–π¯»£ √£±‚ ±∏ø™
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

				req.setAttribute("message1", "æ∆¿Ãµ");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "æ∆¿Ãµ √£±‚");
				req.setAttribute("message4", "findId.do';");
				req.setAttribute("message5", "∑Œ±◊¿Œ");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/Name ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û

			}

			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message1", "æ∆¿Ãµ");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "æ∆¿Ãµ √£±‚");
				req.setAttribute("message4", "findId.do';");
				req.setAttribute("message5", "∑Œ±◊¿Œ");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/Birth ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û

			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// classø°º≠ jsp∑Œ ≥—±Ê∂ß setAttribute∑Œ ≥—±‰¥Ÿ.
				req.setAttribute("message1", "æ∆¿Ãµ");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "æ∆¿Ãµ √£±‚");
				req.setAttribute("message4", "findId.do';");
				req.setAttribute("message5", "∑Œ±◊¿Œ");
				req.setAttribute("message6", "login.do';");

				url = "/movie/findPwdConf.do";
				forward(req,resp,url);

				return;		// id/tel ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û
			}

			req.setAttribute("message1", "æ∆¿Ãµ");
			req.setAttribute("message2", "»Æ¿Œ«œΩ≈ ∫Òπ–π¯»£¥¬ [" + dto.getUserPwd() + "] ¿‘¥œ¥Ÿ");
			req.setAttribute("message3", "æ∆¿Ãµ √£±‚");
			req.setAttribute("message4", "findId.do';");
			req.setAttribute("message5", "∑Œ±◊¿Œ");
			req.setAttribute("message6", "login.do';");


			url = "/movie/findPwdConf.do";
			forward(req,resp,url);
		}


		else if(uri.indexOf("findPwdConf.do")!=-1) {

			url = "/jspProject/movie_confirm.jsp";
			forward(req,resp,url);
		}


		// ∞°¿‘ø©∫Œ »Æ¿Œ
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

				req.setAttribute("message1", "∞°¿‘¡§∫∏");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "∏ﬁ¿Œ»≠∏È");
				req.setAttribute("message4", "main.do';");
				req.setAttribute("message5", "»∏ø¯∞°¿‘¡¯«‡");
				req.setAttribute("message6", "signUpGo.do';");

				url = "/movie/signUpConfirm.do";
				forward(req,resp,url);
				return;		// id/Name ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û
			}


			if(dto==null||!dto.getUserBirth().equals(userBirth)) {

				req.setAttribute("message1", "∞°¿‘¡§∫∏");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "∏ﬁ¿Œ»≠∏È");
				req.setAttribute("message4", "main.do';");
				req.setAttribute("message5", "»∏ø¯∞°¿‘¡¯«‡");
				req.setAttribute("message6", "signUpGo.do';");

				url = "/movie/signUpConfirm.do";
				forward(req,resp,url);

				return;		// id/Birth ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û
			}

			if(dto==null||!dto.getUserTel().equals(userTel)) {

				// classø°º≠ jsp∑Œ ≥—±Ê∂ß setAttribute∑Œ ≥—±‰¥Ÿ.
				req.setAttribute("message1", "∞°¿‘¡§∫∏");
				req.setAttribute("message2", "»∏ø¯¡§∫∏∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
				req.setAttribute("message3", "∏ﬁ¿Œ»≠∏È");
				req.setAttribute("message4", "main.do';");
				req.setAttribute("message5", "»∏ø¯∞°¿‘¡¯«‡");
				req.setAttribute("message6", "signUpGo.do';");

				url = "/movie/signUpConfirm.do";
				forward(req,resp,url);

				return;		// id/tel ∞° ∆≤∏Æ∏È ø©±‚º≠ ∏ÿ√Á∂Û
			}

			req.setAttribute("message1", "∞°¿‘¡§∫∏");
			req.setAttribute("message2", "»Æ¿Œ«œΩ≈ æ∆¿Ãµ¥¬ [" + dto.getUserId() + "] ¿‘¥œ¥Ÿ");
			req.setAttribute("message3", "∏ﬁ¿Œ»≠∏È");
			req.setAttribute("message4", "main.do';");
			req.setAttribute("message5", "»∏ø¯∞°¿‘¡¯«‡");
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


		// logout¿€æ˜
		else if(uri.indexOf("logout.do")!=-1) {

			HttpSession session = req.getSession();

			session.removeAttribute("customInfo");		// customInfoø° ¿÷¥¬ ∞™ ªË¡¶ 
			session.invalidate();						// customInfo∂Û¥¬ ∫Øºˆµµ ªË¡¶

			url = "/jspProject/cgvMain.jsp";
			forward(req,resp,url);
		}







		//øµ»≠ ¡¬ºÆ »Æ¿Œ
		else if(uri.indexOf("sit.do")!=-1) {
			HttpSession session=req.getSession();

>>>>>>> loadtest
			String movietype=req.getParameter("movietype");
			String timetype=req.getParameter("timetype");
			String roomtypestring=req.getParameter("roomtype");
			int roomtype=Integer.valueOf(roomtypestring);
<<<<<<< HEAD
			System.out.println(movietype+"ÏòÅÌôî"+timetype+"ÏãúÍ∞Ñ"+roomtype+"ÏÉÅÏòÅÍ¥Ä ÏòàÎß§ ÏãúÏûë");

			MovieSitDAO msdao=new MovieSitDAO(DBCPConn.getConnection());
			List<MovieSitDTO> lists=msdao.getLists(movietype, timetype, roomtype);
			req.setAttribute("lists", lists);
			
=======
			System.out.println(movietype+"øµ»≠"+timetype+"Ω√∞£"+roomtype+"ªÛøµ∞¸ øπ∏≈ Ω√¿€");

			Movie_PaymentDAO msdao=new Movie_PaymentDAO(DBCPConn.getConnection());
			List<Movie_PaymentDTO> lists = msdao.getLists(movietype, timetype, roomtype);
			req.setAttribute("lists", lists);

>>>>>>> loadtest

			url="/jspProject/movie/sit.jsp";
			forward(req, resp, url);
		}
<<<<<<< HEAD
		
		//ÏòÅÌôî ÌèâÍ∞Ä ÏÑ†ÌÉù
=======

		//øµ»≠ ∆Ú∞° º±≈√
>>>>>>> loadtest
		else if(uri.indexOf("movie_evaluation_select.do")!=-1) {
			url="/jspProject/movie/movie_evaluation_select.jsp";
			forward(req, resp, url);
		}
<<<<<<< HEAD
		
		//ÏòÅÌôî ÌèâÍ∞Ä ÌôïÏù∏
=======

		//øµ»≠ ∆Ú∞° »Æ¿Œ
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
			System.out.println(movietype+"ÏòÅÌôî ÌÉÄÏûÖÏúºÎ°ú Îì§Ïñ¥ Ïò® Í∞í");
=======
			System.out.println(movietype+"øµ»≠ ≈∏¿‘¿∏∑Œ µÈæÓ ø¬ ∞™");
>>>>>>> loadtest
			//-----------------------------------------------------------------------
			Movie_AppraisalDAO madao=new Movie_AppraisalDAO(DBCPConn.getConnection());

			String pageNum=req.getParameter("pageNum");
<<<<<<< HEAD
			int currentPage=1;//Ï≤òÏùåÏãúÌñâÌïòÎäî ÌéòÏù¥ÏßÄ
			if(pageNum!=null){
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=madao.countData(movietype);//Ï†ÑÏ≤¥ Îç∞Ïù¥ÌÑ∞ Íµ¨ÌïòÍ∏∞
			req.setAttribute("dataCount",new Integer(dataCount));
			int numPerPage=10;//Ï¥ù ÌéòÏù¥ÏßÄÏàò Íµ¨ÌïòÍ∏∞
			int totalPage=myutil.getPageCount(numPerPage, dataCount);
			//Ï†ÑÏ≤¥ÌéòÏù¥ÏßÄÏàòÎ≥¥Îã§ ÌëúÏãúÌï† ÌéòÏù¥ÏßÄÍ∞Ä Îçî ÌÅ∞ Í≤ΩÏö∞
			if(currentPage>totalPage)
				currentPage=totalPage;
			//dbÏóêÏÑú Í∞ÄÏ†∏Ïò¨ Îç∞Ïù¥ÌÑ∞Ïùò ÏãúÏûëÍ≥º ÎÅù
=======
			int currentPage=1;//√≥¿ΩΩ√«‡«œ¥¬ ∆‰¿Ã¡ˆ
			if(pageNum!=null){
				currentPage=Integer.parseInt(pageNum);
			}
			int dataCount=madao.countData(movietype);//¿¸√º µ•¿Ã≈Õ ±∏«œ±‚
			req.setAttribute("dataCount",new Integer(dataCount));
			int numPerPage=10;//√— ∆‰¿Ã¡ˆºˆ ±∏«œ±‚
			int totalPage=myutil.getPageCount(numPerPage, dataCount);
			//¿¸√º∆‰¿Ã¡ˆºˆ∫∏¥Ÿ «•Ω√«“ ∆‰¿Ã¡ˆ∞° ¥ı ≈´ ∞ÊøÏ
			if(currentPage>totalPage)
				currentPage=totalPage;
			//dbø°º≠ ∞°¡Æø√ µ•¿Ã≈Õ¿« Ω√¿€∞˙ ≥°
>>>>>>> loadtest
			int start=(currentPage-1)*numPerPage+1;
			int end=currentPage*numPerPage;

			List<Movie_AppraisalDTO> lists=madao.getList(start, end, movietype);
			req.setAttribute("lists", lists);
<<<<<<< HEAD
			
			//System.out.println(lists.size());ÌÅ¨Í∏∞Ïû¨Í∏∞
			//ÌéòÏù¥ÏßÄ Ï≤òÎ¶¨
			String listUrl="/study/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			//String listUrl="/study/movie/movie_evaluation.do?movietype="+movietype;
			String pageIndexList=myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			
			url="/jspProject/movie/movie_evaluation.jsp";
			forward(req, resp, url);
		}
		
		//ÏòÅÌôî ÌèâÍ≥º ÏûëÏÑ±
=======

			//System.out.println(lists.size());≈©±‚¿Á±‚
			//∆‰¿Ã¡ˆ √≥∏Æ
			String listUrl="/study/movie/movie_evaluation.do?movietype="+URLEncoder.encode(movietype,"UTF-8");
			String pageIndexList=myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);

			url="/jspProject/movie/movie_evaluation.jsp";
			forward(req, resp, url);
		}

		//øµ»≠ ∆Ú∞˙ ¿€º∫
>>>>>>> loadtest
		else if(uri.indexOf("movie_evaluation_ok.do")!=-1) {

			HttpSession session=req.getSession();
			req.setCharacterEncoding("UTF-8");
<<<<<<< HEAD
			int star_select=Integer.valueOf(req.getParameter("star_select"));
			String content=req.getParameter("content");
			String movietype=(String)session.getAttribute("movietype");
			System.out.println(movietype+"Ïù¥ Í∞íÏù¥ ÏïàÎÑòÏñ¥Ïò§ÎäîÍ≤É Í∞ôÏïÑ");
			//movietype="ÎÇòÏÅúÎÖÄÏÑùÎì§";
=======

			int star_select=Integer.valueOf(req.getParameter("star_select"));
			String content=req.getParameter("content");
			String movietype=(String)session.getAttribute("movietype");
			System.out.println(movietype+"¿Ã ∞™¿Ã æ»≥—æÓø¿¥¬∞Õ ∞∞æ∆");

			//movietype="≥™ª€≥‡ºÆµÈ";
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
		
		
		//ÏòÅÌôî ÏòàÎß§ ÏÑ†ÌÉù
		else if(uri.indexOf("movie_select.do")!=-1) {
			HttpSession session=req.getSession();
			//Î°úÍ∑∏Ïù∏ ÌïòÏßÄ ÏïäÏïòÏùÑÎïå
			System.out.println("ÏòÅÌôî ÏòàÎß§ ÏßÑÏûÖ");
			if(session.getAttribute("customInfo")==null||((CustomInfo)session.getAttribute("customInfo")).getUserId().equals("")) {
				System.out.println("ÎπÑÎ°úÍ∑∏Ïù∏Ïãú..");
=======


		//øµ»≠ øπ∏≈ º±≈√
		else if(uri.indexOf("movie_select.do")!=-1) {

			HttpSession session=req.getSession();

			if(session.getAttribute("customInfo")==null||((CustomInfo)session.getAttribute("customInfo")).getUserId().equals("")) {

				System.out.println("∫Ò∑Œ±◊¿ŒΩ√..");

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
		
		//ÏòÅÌôî Í≤∞Ï†ú ÌéòÏù¥ÏßÄÎ°ú.
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

		//øµ»≠ ∞·¡¶ ∆‰¿Ã¡ˆ∑Œ.
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
					//dto.setUserId("ÏûÑÏãú ÏÇ¨Ïö©Ïûê ÏïÑÏù¥Îîî");
					dto.setSitnum(((i*10)+j));
					lists.add(dto);
//					MovieSitDAO dao=new MovieSitDAO(conn);
//					if(dao.insert(dto)!=1){//ÌÇ§Í∞í Ïò§Î•òÏãú
//							autocommit=false;
//						}
=======

						Movie_PaymentDTO dto=new Movie_PaymentDTO();

						dto.setMovietype(req.getParameter("movietype"));
						dto.setTimetype(req.getParameter("timetype"));
						dto.setRoomtype(Integer.valueOf(req.getParameter("roomtype")));

						System.out.println(((CustomInfo)session.getAttribute("customInfo")).getUserId());

						dto.setUserId(((CustomInfo)session.getAttribute("customInfo")).getUserId());
						//dto.setUserId("¿”Ω√ ªÁøÎ¿⁄ æ∆¿Ãµ");
						dto.setSitnum(((i*10)+j));

						lists.add(dto);

						Movie_PaymentDAO mpdao=new Movie_PaymentDAO(conn);
						//							if(mpdao.insert(dto)!=1){//≈∞∞™ ø¿∑˘Ω√
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
