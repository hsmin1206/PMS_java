package network;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/*
Client에게 User 권한을 얻을 수 있도록 해주는 클래스입니다.

Server에서 Receiver가 선언되면, Receiver를 통해 User가 등록이 되고, User 클래스를 통해 양식에 맞춰 실제 저희 눈에 보이게 되는 메세지 전달이 가능하게 됩니다.
*/
public class User {
	HashMap<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();
	//clientmap은 String 형의 key와 DataOutputStream 형의 Value값을 받음
	
	public synchronized void AddClient(String name, Socket socket) {
		try {
			System.out.println("User class의 AddClient 메소드 들어감 : " + name + " socket : " + socket);
			sendMsg(name + " 님이 입장하셨습니다.", "Server"); // server에 입장 메세지 전달
			clientMap.put(name, new DataOutputStream(socket.getOutputStream()));
			// HashMap의 put(key, value)함수는 key와 value를 받는다 
			
			System.out.println("현재 접속중인 회사원 인원 : " + clientMap.size());
		} catch (Exception e) {
			System.out.println("user exception");
			e.printStackTrace();
		}
	}
	
	public synchronized void RemoveClient(String name) {
		try {
			clientMap.remove(name); //name value를 가지는 client 제거
			
		} catch (Exception e) {
			System.out.println("RemoveClient failed");
			e.printStackTrace();
		}
	}
	
	public synchronized void sendMsg(String msg, String name) throws Exception{
		Iterator iterator = clientMap.keySet().iterator(); //client의 key값들을 읽어옵니다.
		while (iterator.hasNext()) { //key 값의 next가 존재하면 계속 루프
			String clientname = (String) iterator.next(); //채팅을 입력한 유저 이름을 받아와서
			clientMap.get(clientname).writeUTF(name + " : " + msg); //양식대로 채팅을 출력합니다.
		}
	}
}
