package groups.capturepacket;

import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class CapturePacket {
	public static void main(String[] args) {
		
		//��ȡ���ص�����������(����ӿ�)
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		for(NetworkInterface n: devices) {
			System.out.println(n.name + " | " +n.description);
		}
		JpcapCaptor jpcap = null;
		int caplen = 62535;
		boolean promiscCheck = true;
		

			//ָ��һ�����������������ظ��������Ĳ�������
			for(NetworkInterface device : devices){
				try {
					jpcap = JpcapCaptor.openDevice(device, caplen, promiscCheck, 50);
					NICapturePacket network = new NICapturePacket(jpcap);
					System.out.println(device.name+"��ʼץ��");
					new Thread(network).start();
				}catch(IOException e){
					System.out.println("����!ϵͳ����!");
					e.printStackTrace();
				}
			}			
	}
}

		


				
				
 
//				System.out.println("�汾��IPv4");
//				System.out.println("����Ȩ��" + ip.priority);
//				System.out.println("���ַ��������������� " + ip.t_flag);
//				System.out.println("���ַ�����ߵĿɿ��ԣ�" + ip.r_flag);
//				System.out.println("���ȣ�" + ip.length);
//				System.out.println("��ʶ��" + ip.ident);
//				System.out.println("DF:Don't Fragment: " + ip.dont_frag);
//				System.out.println("NF:Nore Fragment: " + ip.more_frag);
//				System.out.println("Ƭƫ�ƣ�" + ip.offset);
//				System.out.println("����ʱ�䣺" + ip.hop_limit);
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
//				System.out.println("Э�飺" + ip.protocol);
//				System.out.println("ԴIP " + ip.src_ip.getHostAddress());
//				System.out.println("Ŀ��IP " + ip.dst_ip.getHostAddress());
//				System.out.println("Դ�������� " + ip.src_ip);
//				System.out.println("Ŀ���������� " + ip.dst_ip);
//				
//				System.out.println("----------------------------------------------");
 
