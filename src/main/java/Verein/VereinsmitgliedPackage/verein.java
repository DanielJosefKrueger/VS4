package Verein.VereinsmitgliedPackage;


/**
* Verein/VereinsmitgliedPackage/verein.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from vmitglied.idl
* Donnerstag, 7. Juni 2018 14:43 Uhr MESZ
*/

public final class verein implements org.omg.CORBA.portable.IDLEntity
{
  public String vname = null;
  public short vbeitrag = (short)0;

  public verein ()
  {
  } // ctor

  public verein (String _vname, short _vbeitrag)
  {
    vname = _vname;
    vbeitrag = _vbeitrag;
  } // ctor

} // class verein