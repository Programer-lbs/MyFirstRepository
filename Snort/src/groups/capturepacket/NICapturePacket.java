package groups.capturepacket;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jpcap.JpcapCaptor;
import jpcap.packet.ARPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

public class NICapturePacket implements Runnable{
	private JpcapCaptor jpcap;
	
	public NICapturePacket(JpcapCaptor jpcap) {
		this.jpcap = jpcap;
	}
	
	
	@Override
	public void run() {
		List<IPPacketWrap> iplist = new ArrayList<>();
		List<ARPPacketWrap> arplist = new ArrayList<>();
		
		while(true) {
			Packet packet  = jpcap.getPacket();
			if(packet instanceof IPPacket) {
				iplist.add(IPHandler((IPPacket)packet));
				if(iplist.size()==30) {
					//AddPacketToDB.insertIP(iplist);
					iplist.clear();
				}		
				
			}
			if(packet instanceof ARPPacket) {
				arplist.add(ARPHandler((ARPPacket)packet));
				if(iplist.size()==10) {
					//AddPacketToDB.insertARP(arplist);
					arplist.clear();
				}	
			}
		}
	}
	
	//UDP、TCP包处理函数
	public IPPacketWrap IPHandler(IPPacket packet) {
			IPPacketWrap ip = new IPPacketWrap();
			Date now  = new Date();
			ip.setTime(new Timestamp(now.getTime()));
			
		
			String[] IPMsg = packet.toString().split(" ");
					
			//获取捕获数据包的时间
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
			String srcPort="";
			String dstPort="";
					
			//数据包的源IP
			String srcIP = packet.src_ip.getHostAddress();	
			//数据包的目的IP
			String  dstIP = packet.dst_ip.getHostAddress();
					
			if(packet.protocol==packet.IPPROTO_TCP || packet.protocol==packet.IPPROTO_UDP) {
				//数据包的源端口
				srcPort = IPMsg[10];
				//数据包的目的端口
				dstPort = IPMsg[12];
			}
			if(packet.protocol==packet.IPPROTO_TCP) {
				ip.setProtocol("TCP");
				ip.setSrc(srcIP+":"+srcPort);
				ip.setDst(dstIP+":"+dstPort);
				System.out.println(sdf.format(now)+"\t"+"TCP"+"\t"+srcIP+":"+srcPort+"----->"+dstIP+":"+dstPort);
			}
			if(packet.protocol==packet.IPPROTO_UDP) {
				ip.setProtocol("UDP");
				ip.setSrc(srcIP+":"+srcPort);
				ip.setDst(dstIP+":"+dstPort);
				System.out.println(sdf.format(now)+"\t"+"UDP"+"\t"+srcIP+":"+srcPort+"----->"+dstIP+":"+dstPort);
			}
			if(packet.protocol==packet.IPPROTO_ICMP) {
				ip.setProtocol("ICMP");
				ip.setSrc(srcIP);
				ip.setDst(dstIP);
				System.out.println(sdf.format(now)+"\t"+"ICMP"+"\t"+srcIP+"----->"+dstIP);
			}
			return ip;
						
	}
		
	//arp数据包处理函数
	public ARPPacketWrap ARPHandler(ARPPacket packet) {
		
		ARPPacketWrap arp = new ARPPacketWrap();
		Date now  = new Date();
		arp.setTime(new Timestamp(now.getTime()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String senderMAC = packet.getSenderHardwareAddress().toString();
		String senderIP = packet.getSenderProtocolAddress().toString();
		
		arp.setProtocol("ARP");
		arp.setSrc(senderIP+"/"+senderMAC);
		
		String targetMAC = packet.getTargetHardwareAddress().toString();
		String targetIP = packet.getTargetProtocolAddress().toString();
		arp.setDst(targetIP+"/"+targetMAC);
		System.out.println(sdf.format(now)+"\t"+"ARP"+"\t"+senderIP+"/"+senderMAC+"---->"+targetIP+"/"+targetMAC);
		return arp;
	}

}
