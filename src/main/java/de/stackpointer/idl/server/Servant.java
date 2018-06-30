package de.stackpointer.idl.server;


import Verein.VereinsmitgliedPackage.verein;
import Verein._VereinsmitgliedImplBase;

public class Servant extends _VereinsmitgliedImplBase {


    private final String mname;
    private final verein[] mvereine;

    Servant(String mname, verein[] mvereine ){
        this.mname = mname;
        this.mvereine = mvereine;
    }


    public String mname() {
        return mname;
    }

    public verein[] mvereine() {
        return mvereine;
    }

    public short erhoeheBeitrag(String vereinname, short erhoehung) {
        for(verein v: this.mvereine){
            if(v.vname.equals(vereinname)){
                v.vbeitrag += erhoehung;
                return v.vbeitrag;
            }
        }
        return Short.MIN_VALUE;
    }
}
