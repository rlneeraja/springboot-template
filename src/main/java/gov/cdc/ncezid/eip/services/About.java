package gov.cdc.ncezid.eip.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Created by marcelo on 10/7/16.
 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(locations = "classpath:about.yml")
@JsonSerialize(using = AboutSerializer.class)
public class About implements Serializable {
    private String summary;
    private List<ContactInfo> contacts;
    private List<String> versions ;
    private String docs;
    private String currentRelease;



    @Data
    public static class ContactInfo {
        private String name;
        private String email;
        private String role;
    }
}

//Classes annotated with Configuration are not serializable out of the box.
//Needed to create this custom Serializer in order to get the About class above properly serialized.
class AboutSerializer extends StdSerializer<About> {

    public AboutSerializer() {
        this(null);
    }

    public AboutSerializer(Class<About> t) {
        super(t);
    }

    @Override
    public void serialize(About about, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("summary", about.getSummary());
        jgen.writeFieldName("versions");
        jgen.writeStartArray();
        for (String v: about.getVersions()) {
            jgen.writeString(v);
        }
        jgen.writeEndArray();
        jgen.writeStringField("docs", about.getDocs());
        jgen.writeStringField("currentRelease", about.getCurrentRelease());
        jgen.writeFieldName("contacts");
        jgen.writeStartArray();
        for (About.ContactInfo c: about.getContacts()) {
            jgen.writeStartObject();
            jgen.writeStringField("name", c.getName());
            jgen.writeStringField("email", c.getEmail());
            jgen.writeStringField("role", c.getRole());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();

        jgen.writeEndObject();
    }
}