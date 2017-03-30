package mush.com.br.blife.model;

import com.orm.SugarRecord;


public class BolsasPorTipo extends SugarRecord {
    private int aPos;
    private int aNeg;
    private int bPos;
    private int bNeg;
    private int abPos;
    private int abNeg;
    private int oPos;
    private int oNeg;

    public BolsasPorTipo() {
    }

    public void setaPos(int aPos) {
        this.aPos = aPos;
    }

    public void setaNeg(int aNeg) {
        this.aNeg = aNeg;
    }

    public void setbPos(int bPos) {
        this.bPos = bPos;
    }

    public void setbNeg(int bNeg) {
        this.bNeg = bNeg;
    }

    public void setAbPos(int abPos) {
        this.abPos = abPos;
    }

    public void setAbNeg(int abNeg) {
        this.abNeg = abNeg;
    }

    public void setoPos(int oPos) {
        this.oPos = oPos;
    }

    public void setoNeg(int oNeg) {
        this.oNeg = oNeg;
    }

    public int getaPos() {
        return aPos;
    }

    public int getaNeg() {
        return aNeg;
    }

    public int getbPos() {
        return bPos;
    }

    public int getbNeg() {
        return bNeg;
    }

    public int getAbPos() {
        return abPos;
    }

    public int getAbNeg() {
        return abNeg;
    }

    public int getoPos() {
        return oPos;
    }

    public int getoNeg() {
        return oNeg;
    }
}
