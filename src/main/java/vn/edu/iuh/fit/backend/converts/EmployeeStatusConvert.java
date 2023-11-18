package vn.edu.iuh.fit.backend.converts;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;

@Converter(autoApply = true)
public class EmployeeStatusConvert implements AttributeConverter<EmployeeStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus attribute) {
        if(attribute == null){
        return null;}
        return attribute.getValue();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == 0)
            return EmployeeStatus.IN_ACTIVE;
        if(dbData == 1)
            return EmployeeStatus.ACTIVE;
        if(dbData == -1)
            return EmployeeStatus.TERMINATED;
        return null;
    }
}
