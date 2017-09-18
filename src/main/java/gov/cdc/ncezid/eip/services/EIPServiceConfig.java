package gov.cdc.ncezid.eip.services;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.Serializable;

@Data
@Configuration
@PropertySource("classpath:application.yml")
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(using = EIPConfigSerialzer.class)

public class EIPServiceConfig implements Serializable{

    @Value("${configuration.pageInfo.defaultPageSize}")
    public long defaultPageSize;

    @Value("${configuration.pageInfo.maxPageSize}")
    public int maxPageSize;
}

class EIPConfigSerialzer extends StdSerializer<EIPServiceConfig> {

    public EIPConfigSerialzer() {
        this(null);
    }

    public EIPConfigSerialzer(Class<EIPServiceConfig> t) {
        super(t);
    }

    @Override
    public void serialize(EIPServiceConfig eipConfig, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeFieldName("configuration");
        jgen.writeStartObject("pageInfo");
            jgen.writeNumberField("defaultPageSize", eipConfig.getDefaultPageSize());
            jgen.writeNumberField("maxPageSize", (long)eipConfig.getMaxPageSize());
        jgen.writeEndObject();
        jgen.writeEndObject();
    }
}
