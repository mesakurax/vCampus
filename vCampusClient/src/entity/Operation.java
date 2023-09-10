package entity;

import java.io.Serializable;
import java.util.HashMap;

public class Operation implements Serializable {

    private static final long serialVersionUID = 999888L;
    private int operationcode;
    private HashMap<Integer, User> users;
    private boolean isSuccess;

    public Operation()
    {
        users = new HashMap<Integer, User>();
        operationcode=000;
        isSuccess=false;
    }
    public Operation(HashMap<Integer, User> userstemp, int tempcode, boolean isSuccesstemp)
    {

        users=userstemp;
        operationcode=tempcode;
        isSuccess=isSuccesstemp;
    }
    public void setUsers(HashMap<Integer, User> userstemp)
    {
        users=userstemp;
    }
    public  HashMap<Integer, User> getUsers()
    {
        return users;
    }

    public void setOperationcode(int codetemp)
    {
        operationcode=codetemp;
    }

    public int getOperationcode()
    {
        return operationcode;
    }

    public void setSuccess(boolean result)
    {
        isSuccess=result;
    }

    public boolean getSuccess()
    {
        return isSuccess;
    }

    public void addUser(User utemp)
    {
        users.put(users.size()+1,utemp );
    }

    public User getUser(int no)
    {
        return users.get(no);
    }

    public void operClear()
    {
        operationcode=000;
        users.clear();
        isSuccess=false;
    }

    public void copy(Operation o)
    {
        setOperationcode(o.getOperationcode());
        setSuccess(o.getSuccess());
        users.clear();
        int times=o.getUsers().size();
        for(int i=1;i<=times;i++)
        {
            users.put(i,o.getUser(i));
        }
    }




}
