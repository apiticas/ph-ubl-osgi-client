package ro.alx.phublclient.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.helger.ubl21.EUBL21DocumentType;
import com.helger.ubl21.UBL21Marshaller;

public class PhUblClient {

    public ByteArrayOutputStream marshal(Object document, EUBL21DocumentType documentType) throws Exception {
        System.out.println("Marshalling UBL document of type " + documentType);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        UBL21Marshaller.writeUBLDocument(document, PhUblClient.class.getClassLoader(), documentType, new ValidationEventHandler() {
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
        return UBL21Marshaller.readUBLDocument(source, PhUblClient.class.getClassLoader(), ublDocumentType.getImplementationClass(), new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent validationEvent) {
                return true;
            }
        });
    }

    public ByteArrayOutputStream marshalWithoutClassloader(Object document, EUBL21DocumentType documentType) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(documentType.getImplementationClass());
        Marshaller marshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(document, baos);

        return baos;
    }

    public ByteArrayOutputStream marshalWithClassloader(Object document, EUBL21DocumentType documentType) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(documentType.getImplementationClass().getPackage().getName(), PhUblClient.class.getClassLoader());
        Marshaller marshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(document, baos);

        return baos;
    }
}