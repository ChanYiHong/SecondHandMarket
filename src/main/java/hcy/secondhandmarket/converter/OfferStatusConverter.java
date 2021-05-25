package hcy.secondhandmarket.converter;

import hcy.secondhandmarket.domain.OfferStatus;

import javax.persistence.AttributeConverter;

public class OfferStatusConverter implements AttributeConverter<OfferStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OfferStatus attribute) {
        if(attribute.equals(OfferStatus.WAIT)){
            return 0;
        }else if(attribute.equals(OfferStatus.ACCEPT)){
            return 1;
        }else if(attribute.equals(OfferStatus.DENY)){
            return 2;
        }else if(attribute.equals(OfferStatus.NEGO)){
            return 3;
        }
        else return null;
    }

    @Override
    public OfferStatus convertToEntityAttribute(Integer dbData) {
        if(dbData == 0) {
            return OfferStatus.WAIT;
        }else if(dbData == 1) {
            return OfferStatus.ACCEPT;
        }else if(dbData == 2){
            return OfferStatus.DENY;
        }else if(dbData == 3) {
            return OfferStatus.NEGO;
        }else return null;
    }
}
