package de.stackpointer.idl.client;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Properties;

public class ClientExample1 {


    public static void main(String[] args) {

        try {


            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);

            NamingContext ctx =
                    NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
            NamingContext objref = ctx;
            NameComponent nc1 = new NameComponent("plans", "text");
            NameComponent[] name1 = {nc1};
            ctx.rebind(name1, objref);
            System.out.println("plans rebind successful!");

            NameComponent nc2 = new NameComponent("Personal", "directory");
            NameComponent[] name2 = {nc2};
            NamingContext ctx2 = ctx.bind_new_context(name2);
            System.out.println("new naming context added..");

            NameComponent nc3 = new NameComponent("schedule", "text");
            NameComponent[] name3 = {nc3};
            ctx2.rebind(name3, objref);
            System.out.println("schedule rebind successful!");

            NameComponent nc4 = new NameComponent("calender", "text");
            NameComponent[] name4 = {nc4};
            ctx2.rebind(name4, objref);
            System.out.println("calender rebind successful!");


        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (AlreadyBound alreadyBound) {
            alreadyBound.printStackTrace();
        }


    }


}
