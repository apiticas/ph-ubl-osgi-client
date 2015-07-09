package ro.alx.phublclient.impl;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.xml.transform.ResourceStreamSource;
import com.helger.ubl21.EUBL21DocumentType;
import com.helger.ubl21.UBL21Marshaller;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_21.ApplicationResponseType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
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
        UBL21Marshaller.writeUBLDocument(document, PhUblClient.class.getClassLoader(), documentType, new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent validationEvent) {
                return true;
            }
        }, new StreamResult(os));
        os.close();

        return os;
    }

    public Object unmarshal(InputStream data, EUBL21DocumentType documentType) {
        System.out.println("Unmarshalling UBL document of type " + documentType);

        Source source = new StreamSource(data);
        return UBL21Marshaller.readUBLDocument(source, PhUblClient.class.getClassLoader(), documentType.getImplementationClass(), new ValidationEventHandler() {
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

    public String getARSchemaWithClassloader() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("schemas/ubl21/maindoc/UBL-ApplicationResponse-2.1.xsd", ApplicationResponseType.class.getClassLoader());
        ResourceStreamSource resourceStreamSource = new ResourceStreamSource(classPathResource);

        return Boolean.toString(resourceStreamSource.getInputStream() != null);
    }

    public String getARSchemaWithoutClassloader() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("schemas/ubl21/maindoc/UBL-ApplicationResponse-2.1.xsd");
        ResourceStreamSource resourceStreamSource = new ResourceStreamSource(classPathResource);

        return Boolean.toString(resourceStreamSource.getInputStream() != null);
    }
}