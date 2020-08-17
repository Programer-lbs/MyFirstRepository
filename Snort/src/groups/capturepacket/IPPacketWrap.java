package groups.capturepacket;

import java.sql.Timestamp;
import java.util.Date;

import jpcap.packet.IPPacket;

public class IPPacketWrap {
	private String protocol;
	private String src;
	private String dst;
	private Timestamp time;
	
	public IPPacketWrap() {
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
