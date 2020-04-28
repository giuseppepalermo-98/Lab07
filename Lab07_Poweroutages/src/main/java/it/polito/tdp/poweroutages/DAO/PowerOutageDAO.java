package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import it.polito.tdp.poweroutages.model.EventiBlackOut;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<EventiBlackOut> getBlackOut(Nerc n){
		String sql="SELECT * FROM poweroutages WHERE nerc_id=? ";
		List<EventiBlackOut>list=new ArrayList<>();
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, n.getId());
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				LocalDateTime data_i= rs.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime data_f=rs.getTimestamp("date_event_finished").toLocalDateTime();
				EventiBlackOut e=new EventiBlackOut(rs.getDate("date_event_began").toLocalDate(), rs.getInt("event_type_id"), rs.getInt("customers_affected"), data_i, data_f);
				
				list.add(e);
			}
			
			conn.close();
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
