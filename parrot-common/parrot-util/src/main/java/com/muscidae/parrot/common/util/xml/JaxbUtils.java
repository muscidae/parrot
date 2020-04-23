package com.muscidae.parrot.common.util.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author muscidae
 * @date 2019/10/16 21:21
 * @copyright ©2019
 * @description Jaxb工具类
 */
public final class JaxbUtils {

    private final Map<Class<?>, JAXBContext> jaxbContextSyncMap = new ConcurrentHashMap<>(64);

    private JaxbUtils(){ }

    private <T> JAXBContext getJaxbContext(Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = jaxbContextSyncMap.get(clazz);
        if (null == jaxbContext) {
            jaxbContext = JAXBContext.newInstance(clazz);
            jaxbContextSyncMap.putIfAbsent(clazz, jaxbContext);
        }
        return jaxbContext;
    }

    @SuppressWarnings("unchecked")
    public <T> T xmlToObject(String xml, Class<T> clazz) {
        try {
            return (T) getJaxbContext(clazz).createUnmarshaller().unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T xmlToObject(InputStream inputStream, Class<T> clazz){
        try {
            return (T) getJaxbContext(clazz).createUnmarshaller().unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> String ObjectToXml(T t) {
        return ObjectToXml(t, true, false);
    }

    public <T> String ObjectToXml(T t, boolean format) {
        return ObjectToXml(t, true, format, StandardCharsets.UTF_8);
    }

    public <T> String ObjectToXml(T t, boolean fragment, boolean format) {
        return ObjectToXml(t, fragment, format, StandardCharsets.UTF_8);
    }

    public <T> String ObjectToXml(T t, boolean fragment, boolean format, Charset charset) {
        return ObjectToXml(t, fragment, format, charset, null);
    }

    public <T> String ObjectToXml(T t, boolean fragment, boolean format, Charset charset, String header) {
        StringWriter strWriter = new StringWriter();
        if (header != null) strWriter.write(header);
        try {
            Marshaller marshaller = getJaxbContext(t.getClass()).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment); // 省略xml头
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format); // 格式化输出
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charset.name()); // 设置字符编码
            marshaller.marshal(t, strWriter);
            return strWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class CDataAdapter extends XmlAdapter<String, String> {
        @Override
        public String unmarshal(String str) {return str;}
        @Override
        public String marshal(String str) {return "<![CDATA[" + str + "]]>";}
    }

    public enum Singleton {
        INSTANCE;
        private JaxbUtils jaxbUtils;
        Singleton(){jaxbUtils = new JaxbUtils();}
        public JaxbUtils newInstance(){return jaxbUtils;}
    }

}
