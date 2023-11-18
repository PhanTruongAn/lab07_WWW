package vn.edu.iuh.fit.backend.converts;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.backend.enums.ProductStatus;

@Converter(autoApply = true)
public class ProductStatusConvert implements AttributeConverter<ProductStatus,Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStatus attribute) {
        if(attribute == null){
        return null;}
        return attribute.getValue();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer dbData) {
        if(dbData == 1)
            return ProductStatus.ACTIVE;
        if(dbData == 0)
            return ProductStatus.IN_ACTIVE;
        if (dbData == -1)
            return ProductStatus.TERMINATED;
        return null;
    }
}
