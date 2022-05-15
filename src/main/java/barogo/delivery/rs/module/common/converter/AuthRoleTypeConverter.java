package barogo.delivery.rs.module.common.converter;

import barogo.delivery.rs.module.common.code.AddressType;
import barogo.delivery.rs.module.common.code.AuthRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class AuthRoleTypeConverter implements AttributeConverter<AuthRole,String> {
    @Override
    public String convertToDatabaseColumn(AuthRole attribute) {
        return attribute.getCode();
    }

    @Override
    public AuthRole convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Arrays.stream(AuthRole.values()).filter(f->f.getCode().equals(dbData)).findAny().get();
    }
}
