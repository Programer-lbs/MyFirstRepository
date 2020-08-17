package groups.capturepacket;

import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class CapturePacket {
	public static void main(String[] args) {
		
		//获取本地的网络适配器(网络接口)
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		for(NetworkInterface n: devices) {
			System.out.println(n.name + " | " +n.description);
		}
		JpcapCaptor jpcap = null;
		int caplen = 62535;
		boolean promiscCheck = true;
		

			//指定一个网络适配器并返回该适配器的操作对象
			for(NetworkInterface device : devices){
				try {
					jpcap = JpcapCaptor.openDevice(device, caplen, promiscCheck, 50);
					NICapturePacket network = new NICapturePacket(jpcap);
					System.out.println(device.name+"开始抓包");
					new Thread(network).start();
				}catch(IOException e){
					System.out.println("警告!系统错误!");
					e.printStackTrace();
				}
			}			
	}
}

		


				
				
 
//				System.out.println("版本：IPv4");
//				System.out.println("优先权：" + ip.priority);
//				System.out.println("区分服务：最大的吞吐量： " + ip.t_flag);
//				System.out.println("区分服务：最高的可靠性：" + ip.r_flag);
//				System.out.println("长度：" + ip.length);
//				System.out.println("标识：" + ip.ident);
//				System.out.println("DF:Don't Fragment: " + ip.dont_frag);
//				System.out.println("NF:Nore Fragment: " + ip.more_frag);
//				System.out.println("片偏移：" + ip.offset);
//				System.out.println("生存时间：" + ip.hop_limit);
// 
//				String protocol = "";
//				switch (new Integer(ip.protocol)) {
//				case 1:
//					protocol = "ICMP";
//					break;
//				case 2:
//					protocol = "IGMP";
//					break;
//				case 6:
//					protocol = "TCP";
//					break;
//				case 8:
//					protocol = "EGP";
//					break;
//				case 9:
//					protocol = "IGP";
//					break;
//				case 17:
//					protocol = "UDP";
//					break;
//				case 41:
//					protocol = "IPv6";
//					break;
//				case 89:
//					protocol = "OSPF";
//					break;
//				default:
//					break;
//				}
//				
//				System.out.println("协议：" + ip.protocol);
//				System.out.println("源IP " + ip.src_ip.getHostAddress());
//				System.out.println("目的IP " + ip.dst_ip.getHostAddress());
//				System.out.println("源主机名： " + ip.src_ip);
//				System.out.println("目的主机名： " + ip.dst_ip);
//				
//				System.out.println("----------------------------------------------");
 
