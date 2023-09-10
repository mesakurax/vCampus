package module;

import entity.*;
import utils.SocketHelper;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CourseSystem {
    ObjectInputStream is;
    ObjectOutputStream os;

    public CourseSystem(SocketHelper socketHelper) {
        this.is = socketHelper.getIs();
        this.os = socketHelper.getOs();
    }


    public CourseTable ChooseDisplay(User userInfo) {

        try {
            this.os.writeInt(301);
            this.os.flush();
            this.os.writeObject(userInfo);
            this.os.flush();

            System.out.println("ChooseDisplay");

            try {
                if (this.is.readInt() == 3011)
                    return (CourseTable) this.is.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean CprAdd(User userInfo, Course course) {

        try {
            this.os.writeInt(302);
            this.os.flush();
            this.os.writeObject(userInfo);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();
            System.out.println("CprAdd");

            try {
                if (this.is.readInt() == 3021)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean CprDelete(User userInfo, Course course) {

        try {
            this.os.writeInt(303);
            this.os.flush();
            this.os.writeObject(userInfo);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();
            System.out.println("CprDelete");

            try {
                if (this.is.readInt() == 3031)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean CprDelete_2(CrsPickRecord crsPickRecord) {

        try {
            this.os.writeInt(312);
            this.os.flush();
            this.os.writeObject(crsPickRecord);
            this.os.flush();

            System.out.println("CprDelete_2");

            try {
                if (this.is.readInt() == 3121)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean CourseModify(Course course) {

        try {
            this.os.writeInt(304);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();

            System.out.println("CourseModify");

            try {
                if (this.is.readInt() == 3041)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean CourseDelete(Course course) {

        try {
            this.os.writeInt(305);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();

            System.out.println("CourseDelete");

            try {
                if (this.is.readInt() == 3051)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public CourseTable CourseDis() {

        try {
            this.os.writeInt(306);
            this.os.flush();


            System.out.println("CourseDis");

            try {
                if (this.is.readInt() == 3061) {
                    return (CourseTable) this.is.readObject();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean IfChoose(User userInfo, Course course) {

        try {
            this.os.writeInt(307);
            this.os.flush();
            this.os.writeObject(userInfo);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();
            System.out.println("IfChoose");

            try {
                if (this.is.readInt() == 3071)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public CourseTable CourseSearch(Course course, int c) {

        try {
            this.os.writeInt(308);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();
            this.os.writeObject(c);
            this.os.flush();
            System.out.println("CourseSearch");

            try {
                if (this.is.readInt() == 3081)
                    return (CourseTable) this.is.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public RecordTable CprDis() {

        try {
            this.os.writeInt(309);
            this.os.flush();

            System.out.println("CprDis");

            try {
                if (this.is.readInt() == 3091)
                    return (RecordTable) this.is.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean CourseAdd(Course course) {

        try {
            this.os.writeInt(310);
            this.os.flush();
            this.os.writeObject(course);
            this.os.flush();

            System.out.println("CourseAdd");

            try {
                if (this.is.readInt() == 3101)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public RecordTable CprSearch(CrsPickRecord crsPickRecord, int c) {

        try {
            this.os.writeInt(311);
            this.os.flush();
            this.os.writeObject(crsPickRecord);
            this.os.flush();
            this.os.writeObject(c);
            this.os.flush();
            System.out.println("CprSearch");

            try {
                if (this.is.readInt() == 3111)
                    return (RecordTable) this.is.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}