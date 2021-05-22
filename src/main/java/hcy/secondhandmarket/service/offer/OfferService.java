package hcy.secondhandmarket.service.offer;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Offer;
import hcy.secondhandmarket.domain.OfferStatus;
import hcy.secondhandmarket.dto.offer.OfferResponseDTO;
import hcy.secondhandmarket.dto.offer.OfferSaveDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;

public interface OfferService {

    Long save(OfferSaveDTO offerSaveDTO);

    PageResponseDTO<Object[], OfferResponseDTO> getList(PageRequestDTO pageRequestDTO, Long itemId);

    // 내가 올린 아이템에 들어온 요청들.
    PageResponseDTO<Object[], OfferResponseDTO> getListMyPage(PageRequestDTO pageRequestDTO, String email);

    // 제안 거절.
    void denyOffer(Long id);

    default Offer dtoToEntity(OfferSaveDTO offerSaveDTO, Item item, Member member) {

        return Offer.builder()
                .item(item)
                .member(member)
                .offerPrice(offerSaveDTO.getOfferPrice())
                .message(offerSaveDTO.getMessage())
                .offerStatus(OfferStatus.WAIT)
                .build();

    }

    default OfferResponseDTO entityToDTO(Offer offer, Item item, Member member) {

        return OfferResponseDTO.builder()
                .id(offer.getId())
                .offerPrice(offer.getOfferPrice())
                .message(offer.getMessage())
                .offerStatus(offer.getOfferStatus())
                .nickname(member.getName())
                .itemId(item.getId())
                .title(item.getTitle())
                .build();

    }

}
