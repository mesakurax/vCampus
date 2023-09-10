package entity;

import java.io.Serializable;
import java.util.Vector;

public class RecordTable implements Serializable{
    private Vector<CrsPickRecord> CPR;
    private static final long serialVersionUID = 1521465120412151671L;

    public Vector<CrsPickRecord> getCPR() {
        return CPR;
    }

    public void setCPR(Vector<CrsPickRecord> cpr) {
        this.CPR = cpr;
    }

    public RecordTable(Vector<CrsPickRecord> cpr) {
        setCPR(cpr);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CrsPickRecord record : CPR) {
            sb.append(record.toString()).append("\n");
        }
        return sb.toString();
    }

    public int getCount() {
        return CPR.size();
    }
    public CrsPickRecord getIndex(int index) {
        return CPR.elementAt(index);
    }
}

