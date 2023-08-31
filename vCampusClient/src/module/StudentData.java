package module;

import entity.MessageTypes;
import entity.StudentRoll;
import entity.User;
import utils.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class StudentData implements MessageTypes{
    ObjectInputStream is;
    ObjectOutputStream os;

    public StudentData(SocketHelper sockethelper) {
        this.is = sockethelper.getIs();
        this.os = sockethelper.getOs();
    }

    public StudentRoll Display1(User user) {
        StudentRoll studentRoll=new StudentRoll(user.getId(),"","","","","","");
        try {
            this.os.writeInt(201);
            this.os.flush();
            this.os.writeObject(studentRoll);
            this.os.flush();
            try {
                if (this.is.readInt() == 2011) {
                    return ((StudentRoll) this.is.readObject());

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<StudentRoll> Displayall() {
        try {
            this.os.writeInt(STUDENTROLL_INFO_QUERY_ALL);
            this.os.flush();
            this.os.writeObject(null);
            this.os.flush();
            try {
                int cmd = this.is.readInt();
                if (cmd == STUDENTROLL_INFO_QUERY_ALL_SUCCESS) {
                    return ((Vector<StudentRoll>) this.is.readObject());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean Add(StudentRoll stu){
        try {
            System.out.println("add");
            this.os.writeInt(STUDENTROLL_ADD);
            this.os.flush();
            this.os.writeObject(stu);
            this.os.flush();
            return this.is.readInt() == STUDENTROLL_ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean Modify(StudentRoll stu) {
        try {
            System.out.println("modify");
            this.os.writeInt(STUDENTROLL_MODIFY);
            this.os.flush();
            this.os.writeObject(stu);
            this.os.flush();
            return this.is.readInt() == STUDENTROLL_MODIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean Delete(StudentRoll stu) {
        try {
            System.out.println("delete");
            this.os.writeInt(STUDENTROLL_DELETE);
            this.os.flush();
            this.os.writeObject(stu);
            this.os.flush();
            return this.is.readInt() == STUDENTROLL_DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
