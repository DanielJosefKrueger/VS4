package de.stackpointer.idl.client;

import Verein.Vereinsmitglied;
import Verein.VereinsmitgliedHelper;
import Verein.VereinsmitgliedPackage.verein;
import com.sun.corba.se.impl.io.TypeMismatchException;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Arrays;
import java.util.Properties;

public class MyClient {


    public static void main(String[] args) {
        try{
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);


            // The following code obtains the initial naming context.

            NamingContext nc =
                    NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            //  The list method lists the bindings in the naming context. In this case, up to 1000 bindings from the initial naming context will be returned in the BindingListHolder; any remaining bindings are returned in the BindingIteratorHolder.

            BindingListHolder bl = new BindingListHolder();
            BindingIteratorHolder blIt= new BindingIteratorHolder();
            nc.list(1000, bl, blIt);

            Binding[] bindings = bl.value;
            if (bindings.length == 0) return;


            findVereineForCorbaName(nc, "Hamburg/Hansakultur/Petersen", true);
            raiseFeeForCorbaName(nc,"Hamburg/Hansakultur/Nilssen", "Hansakultur", 100 );
            findVereineForCorbaName(nc, "Hamburg/Hansakultur/Petersen", true);
            printMitgliederOfVerein(nc, "Hamburg/Hansakultur");






        } catch (Exception invalidName) {
            invalidName.printStackTrace();
        }


    }


    public static Vereinsmitglied findVereinsmitGlied(NamingContext context, String name) throws Exception{

        String[] split = name.split("/");

        if(split.length!=3) {
            throw new IllegalArgumentException("A Name must consist of exact 3 parts, divided by a slash!");
        }

        NameComponent comp1 = new NameComponent (split[0], "");
        NameComponent comp2 = new NameComponent (split[1], "");
        NameComponent comp3 = new NameComponent (split[2], "");
        NameComponent[] comp = {comp1, comp2, comp3};
        Object resolved = context.resolve(comp);
        if(resolved instanceof Vereinsmitglied){
            return  (Vereinsmitglied) resolved;
        }else{
            throw new TypeMismatchException();
        }
    }




    public static verein[] findVereineForCorbaName(NamingContext context, String name, boolean print) throws Exception {

        Vereinsmitglied vereinsmitglied  = findVereinsmitGlied(context, name);
            if(print) {
                System.out.println("Vereine für" + vereinsmitglied.mname());
                for (verein ver : vereinsmitglied.mvereine()) {
                    System.out.println(ver.vname + "," + ver.vbeitrag);
                }
            }
            return vereinsmitglied.mvereine();
        }



    public static void raiseFeeForCorbaName(NamingContext context, String name, String verein,  int amount) throws Exception {
        Vereinsmitglied vereinsmitglied  = findVereinsmitGlied(context, name);
        int current = vereinsmitglied.erhoeheBeitrag(verein,(short) amount);
        System.out.println("Für das Vetreinsmitglied " + vereinsmitglied.mname() + "wurde der Beitrag um " + amount + " auf nun" + current + "erhöht");
    }





    public static void printMitgliederOfVerein(NamingContext context, String name) throws Exception {
        Vereinsmitglied[] mitglieder = getVereinsMitgliederForVerein(context, name);
        System.out.println("Mitlgieder des Verines" + name + ":");
        for (Vereinsmitglied vm : mitglieder) {
            System.out.println("Mitglied: " + vm.mname());
        }
    }



    public static Vereinsmitglied[] getVereinsMitgliederForVerein(NamingContext context, String name) throws Exception{
        String[] split = name.split("/");

        if(split.length!=2) {
            throw new IllegalArgumentException("A Name must consist of exact 3 parts, divided by a slash!");
        }

        NameComponent comp1 = new NameComponent (split[0], "");
        NameComponent comp2 = new NameComponent (split[1], "");
        NameComponent[] comp = {comp1, comp2};
        Object resolved = context.resolve(comp);
        NamingContext namingContext = NamingContextHelper.narrow(resolved);
        BindingListHolder bl = new BindingListHolder();
        BindingIteratorHolder blIt = new BindingIteratorHolder();
        namingContext.list(1000, bl, blIt);
        Binding[] bindings = bl.value;
        if (bindings.length == 0) return new Vereinsmitglied[0];


        Vereinsmitglied[] mitglieder = new Vereinsmitglied[bindings.length];
        for (int i = 0; i < bindings.length; i++) {
            // get the object reference for each binding
            org.omg.CORBA.Object obj = namingContext.resolve(bindings[i].binding_name);
            mitglieder[i] = VereinsmitgliedHelper.narrow(obj);
        }
        return mitglieder;
    }







}
