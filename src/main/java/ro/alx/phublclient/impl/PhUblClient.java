package ro.alx.phublclient.impl;

import com.helger.ubl.EUBL21DocumentType;
import com.helger.ubl.UBL21Marshaller;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PhUblClient {

    public ByteArrayOutputStream marshal(Object document, EUBL21DocumentType documentType) throws Exception {
        System.out.println("Marshalling UBL document of type " + documentType);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        UBL21Marshaller.writeUBLDocument(document, documentType, new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent validationEvent) {
                return true;
            }
        }, new StreamResult(os));
        os.close();

        return os;
    }

    public Object unmarshal(InputStream data, EUBL21DocumentType ublDocumentType) {
        System.out.println("Unmarshalling UBL document of type " + ublDocumentType);

        Source source = new StreamSource(data);
        return UBL21Marshaller.readUBLDocument(source, ublDocumentType.getImplementationClass(), new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent validationEvent) {
                return true;
            }
        });
    }
}
