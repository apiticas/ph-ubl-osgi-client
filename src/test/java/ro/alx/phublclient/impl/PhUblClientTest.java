package ro.alx.phublclient.impl;

import com.helger.ubl21.EUBL21DocumentType;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_21.ApplicationResponseType;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

public class PhUblClientTest {

    @Test
    public void testMarshal() throws Exception {
        ApplicationResponseType applicationResponse = new ApplicationResponseType();
        applicationResponse.setID("TEST-ID");

        String ublData = new PhUblClient().marshal(applicationResponse, EUBL21DocumentType.APPLICATION_RESPONSE).toString("UTF-8");
        assert ublData.contains("TEST-ID");
    }

    @Test
    public void testUnmarshal() throws Exception {
        InputStream is = new FileInputStream("src/test/resources/ApplicationResponse.xml");

        ApplicationResponseType applicationResponse = (ApplicationResponseType) new PhUblClient().unmarshal(is, EUBL21DocumentType.APPLICATION_RESPONSE);
        assert "UUID_4321".equals(applicationResponse.getUUIDValue());
    }
}