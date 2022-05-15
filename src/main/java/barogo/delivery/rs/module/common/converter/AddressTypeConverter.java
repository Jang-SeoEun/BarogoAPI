package barogo.delivery.rs.module.common.converter;

import barogo.delivery.rs.module.common.code.AddressType;
import barogo.delivery.rs.module.common.code.DeliveryStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class AddressTypeConverter implements AttributeConverter<AddressType,String> {
    @Override
    public String convertToDatabaseColumn(AddressType attribute) {
        return attribute.getCode();
    }

    @Override
    public AddressType convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Arrays.stream(AddressType.values()).filter(f->f.getCode().equals(dbData)).findAny().get();
    }
}
