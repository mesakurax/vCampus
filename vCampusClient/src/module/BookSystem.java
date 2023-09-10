package module;

import entity.Book;
import entity.BookIllegal;
import entity.BookRecord;
import entity.Paper;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.List;

public class BookSystem {
    ObjectInputStream is;
    ObjectOutputStream os;

    public BookSystem(SocketHelper socketHelper) {
        this.is = socketHelper.getIs();
        this.os = socketHelper.getOs();
    }


    public boolean admin_addBook(Book bk) {
        try {
            this.os.writeInt(501);
            this.os.flush();
            ;
            this.os.writeObject(bk);
            this.os.flush();
            System.out.println("admin_addBook");
            try {
                if (this.is.readInt() == 5011)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean admin_addpen(BookIllegal illegal) {
        try {
            this.os.writeInt(521);
            this.os.flush();
            this.os.writeObject(illegal);
            this.os.flush();
            System.out.println("admin_addpenality");
            try {
                if (this.is.readInt() == 5211)
                    return true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean admin_delete(Book bk) {
        try {
            this.os.writeInt(502);
            this.os.flush();
            ;
            this.os.writeObject(bk);
            this.os.flush();
            System.out.println("admin_deleteBook");
            try {
                if (this.is.readInt() == 5021)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean admin_deleteillegal(BookIllegal illegal) {
        try {
            this.os.writeInt(524);
            this.os.flush();
            ;
            this.os.writeObject(illegal);
            this.os.flush();
            System.out.println("admin_deleteillegal");
            try {
                if (this.is.readInt() == 5241)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean admin_modify(Book bk) {
        try {
            this.os.writeInt(503);
            this.os.flush();
            ;
            this.os.writeObject(bk);
            this.os.flush();
            System.out.println("admin_modifyBook");
            try {
                if (this.is.readInt() == 5031)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifyillegal(BookIllegal illegal) {
        try {
            this.os.writeInt(522);
            this.os.flush();
            ;
            this.os.writeObject(illegal);
            this.os.flush();
            System.out.println("modifyillegal");
            try {
                if (this.is.readInt() == 5221)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> searchbook(Book bk, int flag) {
        try {
            this.os.writeInt(504);
            this.os.flush();
            ;
            this.os.writeObject(bk);
            this.os.flush();
            this.os.writeInt(flag);
            this.os.flush();
            System.out.println("searchBook");
            try {
                Object receivedObject = this.is.readObject();
                if (receivedObject != null) {
                    List<Book> receivedList = (List<Book>) receivedObject;
                    return receivedList;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userborrow(BookRecord record) {
        try {
            this.os.writeInt(511);
            this.os.flush();
            this.os.writeObject(record);
            this.os.flush();
            System.out.println("userborrowbook");
            try {
                if (this.is.readInt() == 5111)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean userreturn(BookRecord record) {
        try {
            this.os.writeInt(512);
            this.os.flush();
            ;
            this.os.writeObject(record);
            this.os.flush();
            System.out.println("userreturn");
            try {
                if (this.is.readInt() == 5121)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<BookRecord> searchrecord(BookRecord record, int flag) {
        try {
            this.os.writeInt(513);
            this.os.flush();
            this.os.writeObject(record);
            this.os.flush();
            this.os.writeInt(flag); // 将 flag 的值写入输出流
            this.os.flush();
            System.out.println("searchRecord");
            try {
                Object receivedObject = this.is.readObject();
                if (receivedObject != null) {
                    return (List<BookRecord>) receivedObject;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BookIllegal> searchillegal(BookIllegal illegal, int flag) {
        try {
            this.os.writeInt(523);
            this.os.flush();
            this.os.writeObject(illegal);
            this.os.flush();
            this.os.writeInt(flag); // 将 flag 的值写入输出流
            this.os.flush();
            System.out.println("searchillegal");
            try {
                Object receivedObject = this.is.readObject();
                if (receivedObject != null) {
                    return (List<BookIllegal>) receivedObject;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean download(Paper paper) throws IOException, ClassNotFoundException {
        System.out.println(paper.getName());
        this.os.writeInt(535);
        this.os.flush();
        this.os.writeObject(paper);
        this.os.flush();
        int code = this.is.readInt();
        if (code == 5351) {

            String filename = (String) this.is.readObject();
            System.out.println(filename);

            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select Folder");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
                System.out.println("Selected Folder: " + folderPath);
                String savePath = folderPath + "\\" + filename;

                FileOutputStream fos = new FileOutputStream(savePath);
                System.out.println(savePath);
                byte[] bufferx = new byte[4096];
                int bytesReadx;
                while((bytesReadx=this.is.read(bufferx)) != -1) {
                    fos.write(bufferx, 0, bytesReadx);
                    fos.flush();
                    if ((new String(bufferx, 0, bytesReadx)).equals("STOPSTOPSTOP")) {
                        break;
                    }
                }
                fos.close();
                return true;

            }
           }
        return false;
    }

    public String read(Paper paper) throws IOException, ClassNotFoundException {
        System.out.println(paper.getName());
        this.os.writeInt(536);
        this.os.flush();
        this.os.writeObject(paper);
        this.os.flush();
        int code = this.is.readInt();
        if (code == 5361) {
            String filename = (String) this.is.readObject();
            System.out.println(filename);
            String prefix = null;
            String suffix = null;
            int dotIndex = filename.lastIndexOf(".");
            if (dotIndex != -1) {
                prefix = filename.substring(0, dotIndex);
                suffix = filename.substring(dotIndex + 1);
            }
            String projectPath = System.getProperty("user.dir");
            String tempPath = projectPath + "/src/Paper/Source" + filename;
            File tempFile = File.createTempFile(prefix,"."+suffix);
            FileOutputStream fos = new FileOutputStream(tempFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = this.is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                fos.flush();
                if ((new String(buffer, 0, bytesRead)).equals("STOPSTOPSTOP")) {
                    break;
                }
            }
            fos.close();
            tempFile.deleteOnExit();
            return tempFile.getPath();
        }
        return null;
    }
    public boolean addpaper(Paper paper, String path) {
        try {
            this.os.writeInt(531);
            this.os.flush();
            this.os.writeObject(paper);
            this.os.flush();
            File file = new File(path);
            path = file.getAbsolutePath();
            FileInputStream fis;
            byte[] buffer;
            byte[] stopData;
            int bytesRead;
            String stopMessage;
            String filename = file.getName();
            this.os.writeObject(filename);
            this.os.flush();
            fis = new FileInputStream(path);
            buffer = new byte[4096];

            while ((bytesRead = fis.read(buffer)) != -1) {
                this.os.write(buffer, 0, bytesRead);
            }

            this.os.flush();
            fis.close();
            stopMessage = "STOPSTOP";
            stopData = stopMessage.getBytes();
            this.os.write(stopData);
            this.os.flush();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("addpaper");
        try {
            if (this.is.readInt() == 5311)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
    public boolean deletepaper(Paper paper)
    {
        try
        {
            this.os.writeInt(532);
            this.os.flush();
            this.os.writeObject(paper);
            this.os.flush();
            System.out.println("deletepaper");
            try
            {
                if(this.is.readInt()==5321)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean modifypaper(Paper paper,String path)
    {
        try
        {
            this.os.writeInt(533);
            this.os.flush();
            this.os.writeObject(paper);
            this.os.flush();
            File file = new File(path);
            path = file.getAbsolutePath();
            FileInputStream fis;
            byte[] buffer;
            byte[] stopData;
            int bytesRead;
            String stopMessage;
            String filename = file.getName();
            this.os.writeObject(filename);
            this.os.flush();
            fis = new FileInputStream(path);
            buffer = new byte[4096];

            while ((bytesRead = fis.read(buffer)) != -1) {
                this.os.write(buffer, 0, bytesRead);
            }

            this.os.flush();
            fis.close();
            stopMessage = "STOPSTOP";
            stopData = stopMessage.getBytes();
            this.os.write(stopData);
            this.os.flush();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
            System.out.println("modifypaper");
            try
            {
                if(this.is.readInt()==5331)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
    }

    public List<Paper> searchPaper(Paper paper,int flag)
    {
        try {
            this.os.writeInt(534);
            this.os.flush();
            this.os.writeObject(paper);
            this.os.flush();
            this.os.writeInt(flag); // 将 flag 的值写入输出流
            this.os.flush();
            System.out.println("searchpaper");
            try {
                Object receivedObject = this.is.readObject();
                if (receivedObject != null) {
                    System.out.println();
                    return (List<Paper>) receivedObject;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
