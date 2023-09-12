package network;

import java.io.DataInputStream;
import java.net.Socket;

/*
 * extends Thread, implements Runnable은 쓰레드 구현을 위해 사용합니다.
 * extends Thread는 Thread를 상속받고 객채화한 뒤에 객체명.start()를 통해 사용하며,
 * implements Runnable은 Thread 객체 안에 쓰레드를 사용하려는 객체를 넣어줘서 객체화한 뒤 사용합니다.
 * extends Thread와 implements Runnable은 비슷합니다.
 * 하지만 implements Runnable을 사용하면 다중 상속이 가능합니다.
 */
public class Receiver implements Runnable{
	Socket Socket;
	DataInputStream in;
	String name;
	User user = new User();
	
	/*
	 * 전역변수들을 전부 초기화합니다.
	 * 또한 user 클래스에 AddClient를 호출하여 사용자를 등록합니다.
	 */
	public Receiver(User user, Socket socket) throws Exception {System.out.println("Receiver 생성자 들어감 user : " + user + " Socket : " + socket);
		this.user = user;
		this.Socket = socket;
		in = new DataInputStream(socket.getInputStream());
		this.name = in.readUTF(); //UTF-8 로 인코딩 후 읽어옴
		user.AddClient(name, socket); //사용자 등록
				
	}
	
	@Override
	public void run() {System.out.println("Receiver class의 run 메소드 들어감");
		try {
			while (true) {
				String msg = in.readUTF(); //in에 들어온 메시지를 UTF-8로 인코딩 후 읽어옴
				user.sendMsg(msg, name); //name 이름을 가진 user가 msg라는 메시지를 보냄
			}
		} catch (Exception e) {
			System.out.println("에러 발생해서 name 유저를 client에서 삭제 함");
			user.RemoveClient(this.name); //에러 발생시 name 유저를 client에서 삭제
			e.printStackTrace();
		}
	}

}
