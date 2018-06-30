package de.stackpointer.idl.client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import java.util.Properties;

public class ClientExample2 {


    public static void main(String[] args) {
        try {

            //   In the above section, Starting the Java IDL Transient Naming Service, the nameserver was started on port 1050. The following code ensures that the client program is aware of this port number.


            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);


            // The following code obtains the initial naming context.

            NamingContext nc =
                    NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            //  The list method lists the bindings in the naming context. In this case, up to 1000 bindings from the initial naming context will be returned in the BindingListHolder; any remaining bindings are returned in the BindingIteratorHolder.

            BindingListHolder bl = new BindingListHolder();
            BindingIteratorHolder blIt = new BindingIteratorHolder();
            nc.list(1000, bl, blIt);

            //   This code gets the array of bindings out of the returned BindingListHolder. If there are no bindings, the program ends.

            Binding[] bindings = bl.value;
            if (bindings.length == 0) return;

            //  The remainder of the code loops through the bindings and prints the names out.

            for (int i = 0; i < bindings.length; i++) {

                // get the object reference for each binding
                org.omg.CORBA.Object obj = nc.resolve(bindings[i].binding_name);
                String objStr = orb.object_to_string(obj);
                int lastIx = bindings[i].binding_name.length - 1;

                // check to see if this is a naming context
                if (bindings[i].binding_type == BindingType.ncontext) {
                    System.out.println("Context: " +
                            bindings[i].binding_name[lastIx].id);
                } else {
                    System.out.println("Object: " +
                            bindings[i].binding_name[lastIx].id);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


}
