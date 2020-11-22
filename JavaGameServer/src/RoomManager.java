import java.util.ArrayList;
import java.util.Vector;



public class RoomManager {//대화방의 정보표현 객체

    String title;//방제목
    int count;//방 인원수
    String boss;//방장(방 개설자)
    int RoomNum;
    Vector<String> UserV;//초기 용량(capacity)지정

                         
    
    public RoomManager()
    {
    	UserV = new Vector<String>();
    }

}