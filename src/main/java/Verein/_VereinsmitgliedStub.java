package Verein;


/**
* Verein/_VereinsmitgliedStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from vmitglied.idl
* Donnerstag, 7. Juni 2018 14:43 Uhr MESZ
*/

public class _VereinsmitgliedStub extends org.omg.CORBA.portable.ObjectImpl implements Verein.Vereinsmitglied
{

  public String mname ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_mname", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return mname (        );
            } finally {
                _releaseReply ($in);
            }
  } // mname

  public Verein.VereinsmitgliedPackage.verein[] mvereine ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_mvereine", true);
                $in = _invoke ($out);
                Verein.VereinsmitgliedPackage.verein $result[] = Verein.VereinsmitgliedPackage.VereinSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return mvereine (        );
            } finally {
                _releaseReply ($in);
            }
  } // mvereine

  public short erhoeheBeitrag (String verein, short erhoehung)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("erhoeheBeitrag", true);
                $out.write_string (verein);
                $out.write_short (erhoehung);
                $in = _invoke ($out);
                short $result = $in.read_short ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return erhoeheBeitrag (verein, erhoehung        );
            } finally {
                _releaseReply ($in);
            }
  } // erhoeheBeitrag

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Verein/Vereinsmitglied:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _VereinsmitgliedStub
