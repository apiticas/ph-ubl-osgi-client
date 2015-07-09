package ro.alx.phublclient.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import ro.alx.phublclient.commands.PhUblClientCommands;
import ro.alx.phublclient.impl.PhUblClient;

import java.util.Hashtable;

public class BActivator implements BundleActivator {

    ServiceRegistration registration;
    ServiceRegistration command;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        Hashtable<String, Object> props = new Hashtable<>();
        props.put("osgi.command.scope", "phubl");
        props.put("osgi.command.function", new String[]{"marshal", "unmarshal", "marshalWithoutClassloader", "marshalWithClassloader"});

        Hashtable<String, Object> noprops = new Hashtable<>();

        registration = bundleContext
                .registerService(ro.alx.phublclient.impl.PhUblClient.class.getName(),
                        new PhUblClient(), noprops);

        command = bundleContext.
                registerService(ro.alx.phublclient.commands.PhUblClientCommands.class.getName(),
                        new PhUblClientCommands(), props);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}
