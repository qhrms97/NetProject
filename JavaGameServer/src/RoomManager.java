import java.util.ArrayList;
import java.util.Vector;



public class RoomManager {//��ȭ���� ����ǥ�� ��ü

    String title;//������
    int count;//�� �ο���
    String boss;//����(�� ������)
    int RoomNum;
    Vector<String> UserV;//�ʱ� �뷮(capacity)����

                         
    
    public RoomManager()
    {
    	UserV = new Vector<String>();
    }

}