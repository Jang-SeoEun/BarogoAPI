package barogo.delivery.rs.module.common.converter;

import barogo.delivery.rs.module.common.code.DeliveryStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class DeliveryStatusConverter implements AttributeConverter<DeliveryStatus,String> {

    @Override
    public String convertToDatabaseColumn(DeliveryStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public DeliveryStatus convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Arrays.stream(DeliveryStatus.values()).filter(f->f.getCode().equals(dbData)).findAny().get();
    }
}
