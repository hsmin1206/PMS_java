package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;


public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream in = null; //이 변수는 사용자가 입력하는 채팅 부분에 해당됩니다.
		BufferedReader in2 = null; //이 변수는 사용자의 닉네임에 해당됩니다. 
		
		DataOutputStream out = null; //이 변수는 사용자가 입력한 데이터를 출력할 때 사용합니다.
		
		try {
//			InetAddress ia = null; //Local Host IP Address 가져오기 위한 변수
//			ia = InetAddress.getLocalHost(); //현재 PC의 IP Address 가져오기
//			socket = new Socket(ia, 9000); //Client의 IP와 port 번호 입력
			socket = new Socket("192.168.0.90", 9003); //Client의 IP와 port 번호 입력
			
			/*
			 * DataInputStream은 입력 스트림을 받는 매개변수이며,
			 * socket.getInputStream()함수를 통해 소켓에서 전달되는 데이터 스트림을 읽어옵니다.
			 * BufferedReader는 Scanner와 비슷한 개념입니다.
			 * Scanner보다 빠르다는 장점이 있지만 String형으로 버퍼에 저장하기 때문에
			 * 따로 데이터를 가공해서 사용해야하는 경우가 많습니다.
			 */
			in = new DataInputStream(socket.getInputStream());
			in2 = new BufferedReader(new InputStreamReader(System.in));
			out = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("닉네임을 입력해주세요 : ");
			String data = in2.readLine(); // 채팅에 사용할 닉네임을 받아옵니다.
//			LoginView loginView = new LoginView();
		    	
//			MemberInformation info = new MemberInformation();
			
			out.writeUTF(data); //닉네임을 UTF-8로 변경 후 출력스트림에 넣습니다.
			Thread th = new Thread(new Send(out)); //새로운 쓰레드에 out을 집어넣도록 합니다.
			th.start();  //쓰레드 시작
		} catch (IOException e) {
			System.out.println("Client excception");
			System.out.println(e.getStackTrace());
		}
	}

}
