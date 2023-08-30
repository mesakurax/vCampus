package module;

import entity.MessageTypes;
import entity.StudentRoll;
import utils.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class StudentData implements java.io.Serializable {
    ObjectInputStream is;
    ObjectOutputStream os;

    public StudentData(SocketHelper sockethelper) {
        this.is = sockethelper.getIs();
        this.os = sockethelper.getOs();
    }

    public StudentRoll Display1() {
        StudentRoll studentRoll = new StudentRoll();
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

    
    public StudentRoll Display2(StudentRoll user) {
        try {
            this.os.writeInt(201);
            this.os.flush();
            this.os.writeObject(user);
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
            this.os.writeInt(MessageTypes.STUDENTROLL_INFO_QUERY_ALL);
            this.os.flush();
            this.os.writeObject(null);
            this.os.flush();
            try {
                int cmd = this.is.readInt();
                if (cmd == MessageTypes.STUDENTROLL_INFO_QUERY_ALL_SUCCESS) {
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

    
    public StudentRoll Student_Search(StudentRoll stu, int cmd) {
        try {
            this.os.writeInt(cmd);
            this.os.flush();
            this.os.writeObject(stu);
            this.os.flush();
            try {
                int readcmd = this.is.readInt();
                System.out.println(readcmd);
                if (readcmd == cmd * 10 + 1) return ((StudentRoll) this.is.readObject());
                else return null;

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean Modify(StudentRoll stu) {
        try {
            System.out.println("modify");
            this.os.writeInt(204);
            this.os.flush();
            this.os.writeObject(stu);
            this.os.flush();
            return this.is.readInt() == 2041;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean Delete(StudentRoll stu) {
        try {
            System.out.println("delete");
            this.os.writeInt(203);
            this.os.flush();
            this.os.writeObject(stu);
            this.os.flush();
            return this.is.readInt() == 2031;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
