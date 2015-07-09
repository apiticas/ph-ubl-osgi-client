package ro.alx.phublclient.commands;

import com.helger.ubl21.EUBL21DocumentType;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_21.ApplicationResponseType;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_21.ObjectFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import ro.alx.phublclient.impl.PhUblClient;

import java.net.URL;

public class PhUblClientCommands {

    private static final String APPLICATION_RESPONSE_XML_FILE_PATH = "/ro/alx/phublclient/commands/ApplicationResponse.xml";
    private static final ApplicationResponseType APPLICATION_RESPONSE = new ApplicationResponseType();

    static {
        APPLICATION_RESPONSE.setID("TEST_UBL_ID");
    }

    public String marshal() {
        BundleContext ctx = FrameworkUtil.getBundle(ro.alx.phublclient.impl.PhUblClient.class)
                .getBundleContext();
        ServiceReference ref = ctx.getServiceReference(ro.alx.phublclient.impl.PhUblClient.class);

        PhUblClient transformer = (PhUblClient) ctx.getService(ref);
        try {
            return transformer.marshal(APPLICATION_RESPONSE, EUBL21DocumentType.APPLICATION_RESPONSE).toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String unmarshal() {
        BundleContext ctx = FrameworkUtil.getBundle(ro.alx.phublclient.impl.PhUblClient.class)
                .getBundleContext();
        ServiceReference ref = ctx.getServiceReference(ro.alx.phublclient.impl.PhUblClient.class);

        PhUblClient transformer = (PhUblClient) ctx.getService(ref);

        URL url = PhUblClientCommands.class.getResource(APPLICATION_RESPONSE_XML_FILE_PATH);
        try {
            return ((ApplicationResponseType) transformer.unmarshal(url.openStream(), EUBL21DocumentType.APPLICATION_RESPONSE)).getIDValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String marshalWithoutClassloader() {
        BundleContext ctx = FrameworkUtil.getBundle(ro.alx.phublclient.impl.PhUblClient.class)
                .getBundleContext();
        ServiceReference ref = ctx.getServiceReference(ro.alx.phublclient.impl.PhUblClient.class);

        PhUblClient transformer = (PhUblClient) ctx.getService(ref);
        try {
            return transformer.marshalWithoutClassloader(new ObjectFactory().createApplicationResponse(APPLICATION_RESPONSE), EUBL21DocumentType.APPLICATION_RESPONSE).toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String marshalWithClassloader() {
        BundleContext ctx = FrameworkUtil.getBundle(ro.alx.phublclient.impl.PhUblClient.class)
                .getBundleContext();
        ServiceReference ref = ctx.getServiceReference(ro.alx.phublclient.impl.PhUblClient.class);

        PhUblClient transformer = (PhUblClient) ctx.getService(ref);

        try {
            return transformer.marshalWithClassloader(new ObjectFactory().createApplicationResponse(APPLICATION_RESPONSE), EUBL21DocumentType.APPLICATION_RESPONSE).toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getARSchemaWithClassloader() {
        BundleContext ctx = FrameworkUtil.getBundle(ro.alx.phublclient.impl.PhUblClient.class)
                .getBundleContext();
        ServiceReference ref = ctx.getServiceReference(ro.alx.phublclient.impl.PhUblClient.class);

        PhUblClient transformer = (PhUblClient) ctx.getService(ref);
        try {
            return transformer.getARSchemaWithClassloader();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getARSchemaWithoutClassloader() {
        BundleContext ctx = FrameworkUtil.getBundle(ro.alx.phublclient.impl.PhUblClient.class)
                .getBundleContext();
        ServiceReference ref = ctx.getServiceReference(ro.alx.phublclient.impl.PhUblClient.class);

        PhUblClient transformer = (PhUblClient) ctx.getService(ref);
        try {
            return transformer.getARSchemaWithoutClassloader();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}