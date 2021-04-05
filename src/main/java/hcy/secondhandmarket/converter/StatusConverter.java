package hcy.secondhandmarket.converter;

import hcy.secondhandmarket.domain.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
        if(attribute.equals(Status.NEW)) return 1;
        else if(attribute.equals(Status.RESERVED)) return 2;
        else if(attribute.equals(Status.SOLD_OUT)) return 3;
        else return null;
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        if(dbData == 1) return Status.NEW;
        else if(dbData == 2) return Status.RESERVED;
        else if(dbData == 3) return Status.SOLD_OUT;
        else return null;
    }
}
