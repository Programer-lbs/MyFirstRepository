package groups.capturepacket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jpcap.packet.Packet;


public class AddPacketToDB {
	private static Connection conn;
	private static String url="jdbc:mysql://localhost:3306/packet";
	private static String username="root";
	private static String password = "admin";
	
	public static void connectionSql() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn  = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void insertIP(List<IPPacketWrap> packetList) {
		connectionSql();
		String sql = "insert into packet values(?,?,?,?)";
		PreparedStatement ps=null;
		try {
			 ps = conn.prepareStatement(sql);
			 for(int i  = 0;i<packetList.size();i++) {
				 ps.setTimestamp(1, packetList.get(i).getTime());
				 ps.setString(2, packetList.get(i).getProtocol());
				 ps.setString(3, packetList.get(i).getSrc());
				 ps.setString(4, packetList.get(i).getDst());
				 ps.execute();
			 }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insertARP(List<ARPPacketWrap> packetList) {
		connectionSql();
		String sql = "insert into ip values(?,?,?,?)";
		PreparedStatement ps=null;
		try {
			 ps = conn.prepareStatement(sql);
			 for(int i  = 0;i<packetList.size();i++) {
				 ps.setTimestamp(1, packetList.get(i).getTime());
				 ps.setString(2, packetList.get(i).getProtocol());
				 ps.setString(3, packetList.get(i).getSrc());
				 ps.setString(4, packetList.get(i).getDst());
				 ps.execute();
			 }
			ps.close();
			conn.close();
		} catch (SQLException e) {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
