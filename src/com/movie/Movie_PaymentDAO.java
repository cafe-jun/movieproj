package com.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Movie_PaymentDAO {
	private Connection conn;
	public Movie_PaymentDAO(Connection conn) {
		this.conn=conn;
	}
	public int insert(Movie_PaymentDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql="insert into movie_paymenttable (movietype,timetype,roomtype,sitnum,userid) values (?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMovietype());
			pstmt.setString(2, dto.getTimetype());
			pstmt.setInt(3, dto.getRoomtype());
			pstmt.setInt(4, dto.sitnum);
			pstmt.setString(5, dto.userId);
			result=pstmt.executeUpdate();
			pstmt.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return result;
	}
	public List<Movie_PaymentDTO> getLists(String movietype,String timetype,int roomtype){
		List<Movie_PaymentDTO> lists=new ArrayList<Movie_PaymentDTO>();
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs=null;
		try {
			sql="select movietype,timetype,roomtype,sitnum,userid from movie_paymenttable where movietype=? and timetype=? and roomtype=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, movietype);
			pstmt.setString(2, timetype);
			pstmt.setInt(3, roomtype);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Movie_PaymentDTO dto =new Movie_PaymentDTO();
				dto.setSitnum(rs.getInt("sitnum"));
				dto.setUserId(rs.getString("userid"));
				lists.add(dto);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return lists;
	}
}
