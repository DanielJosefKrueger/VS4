package de.stackpointer.idl.server;

import Verein.VereinsmitgliedPackage.verein;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import java.util.ArrayList;
import java.util.Properties;

public class Server {

    public static void main(String[] args) {


        try {


            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);
            org.omg.CORBA.Object objRef
                    = orb.resolve_initial_references ("NameService");
            NamingContext initRef = NamingContextHelper.narrow (objRef);




            NameComponent comp1 = new NameComponent ("Hamburg", "");
            NamingContext hamburgref = initRef.bind_new_context (new NameComponent[]{comp1});

            NameComponent comp2 = new NameComponent ("Bremen", "");
            NamingContext bremenref = initRef.bind_new_context (new NameComponent[]{comp2});


            NameComponent comp3 = new NameComponent ("Hansasport", "");
            NamingContext hansasportRef = hamburgref.bind_new_context (new NameComponent[]{comp3});


            NameComponent comp4 = new NameComponent ("Hansakultur", "");
            NamingContext hansaKulturRef = hamburgref.bind_new_context (new NameComponent[]{comp4});

            NameComponent comp5 = new NameComponent ("Hansahilfe", "");
            NamingContext hansaHilfeRef = hamburgref.bind_new_context (new NameComponent[]{comp5});

            NameComponent comp6 = new NameComponent ("Werdersport", "");
            NamingContext werderSportRef = bremenref.bind_new_context (new NameComponent[]{comp6});

            NameComponent comp7 = new NameComponent ("Werdertheater", "");
            NamingContext werderTheaterRef = bremenref.bind_new_context (new NameComponent[]{comp7});












            verein vereinA1 = new verein("Hansaport", 100);
            Servant arnoldsen = new Servant ("Arnoldsen",
                   new verein[]{vereinA1} );
            orb.connect (arnoldsen);


            verein vereinD1 = new verein("Hansaport", 200);
            verein vereinD2 = new verein("Hansakultur", 500);
            Servant deslefsen = new Servant ("Detlefsen",
                    new verein[]{vereinD1, vereinD2} );
            orb.connect (deslefsen);


            verein vereinN1 = new verein("Hansakultur", 350);
            Servant nilssen = new Servant ("Nilssen",
                    new verein[]{vereinN1} );
            orb.connect (nilssen);


            verein vereinP1 = new verein("Hansahilfe", 500);
            verein vereinP2 = new verein("Hansakultur", 150);
            verein vereinP3 = new verein("Werdersport", 600);
            Servant petersen = new Servant ("Petersen",
                    new verein[]{vereinP1, vereinP2, vereinP3} );
            orb.connect (petersen);


            verein vereinS1 = new verein("Werdersport", 300);
            Servant svenssen = new Servant ("Svenssen",
                    new verein[]{vereinS1} );
            orb.connect (svenssen);

            verein vereinV1 = new verein("Werdertheater", 129);
            Servant vlkersen = new Servant ("Volkersen",
                    new verein[]{vereinV1} );
            orb.connect (vlkersen);

            NameComponent[] name;


            name = new NameComponent[1];
            name[0]= new NameComponent (arnoldsen.mname(), "");
            hansasportRef.rebind (name, arnoldsen);

            name = new NameComponent[1];
            name[0]= new NameComponent (deslefsen.mname(), "");
            hansasportRef.rebind (name, deslefsen);

            System.out.println("nice");

            name = new NameComponent[1];
            name[0]= new NameComponent (deslefsen.mname(), "");
            hansaKulturRef.rebind (name, deslefsen);


            name = new NameComponent[1];
            name[0]= new NameComponent (nilssen.mname(), "");
            hansaKulturRef.rebind (name, nilssen);

            name = new NameComponent[1];
            name[0]= new NameComponent (petersen.mname(), "");
            hansaKulturRef.rebind (name, petersen);

            name = new NameComponent[1];
            name[0]= new NameComponent (petersen.mname(), "");
            hansaHilfeRef.rebind (name, petersen);


            name = new NameComponent[1];
            name[0]= new NameComponent (petersen.mname(), "");
            werderSportRef.rebind (name, petersen);


            name = new NameComponent[1];
            name[0]= new NameComponent (svenssen.mname(), "");
            werderSportRef.rebind (name, svenssen);


            name = new NameComponent[1];
            name[0]= new NameComponent (vlkersen.mname(), "");
            werderTheaterRef.rebind (name, vlkersen);


            System.out.println("Finished Initializing");
            java.lang.Object sync = new java.lang.Object ();
            synchronized (sync) {
                sync.wait();
            }

            System.out.println("Finished");



        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
