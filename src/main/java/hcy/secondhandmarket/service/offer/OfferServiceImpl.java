package hcy.secondhandmarket.service.offer;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Offer;
import hcy.secondhandmarket.dto.offer.OfferResponseDTO;
import hcy.secondhandmarket.dto.offer.OfferSaveDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.repository.offer.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long save(OfferSaveDTO offerSaveDTO) {

        Optional<Item> itemOptional = itemRepository.findById(offerSaveDTO.getItemId());

        if(itemOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 물품이 없음 id : " + offerSaveDTO.getItemId());
        }

        Item item = itemOptional.get();

        Optional<Member> memberOptional = memberRepository.findByEmail(offerSaveDTO.getEmail(), false);

        if(memberOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 회원이 없음 id : " + offerSaveDTO.getEmail());
        }

        Member member = memberOptional.get();

        Offer offer = dtoToEntity(offerSaveDTO, item, member);

        offerRepository.save(offer);

        return offer.getId();

    }

    @Override
    public OfferResponseDTO getOne(Long id) {
        log.info("Get One Offer id : {}", id);

        Optional<Offer> offerOptional = offerRepository.getOneWithItemByOfferId(id);

        if(offerOptional.isEmpty()) {
            throw new IllegalStateException("해당 하는 요청이 없습니다 " + id);
        }

        Offer offer = offerOptional.get();

        return entityToDTO(offer);

    }

    @Override
    public PageResponseDTO<Object[], OfferResponseDTO> getList(PageRequestDTO pageRequestDTO, Long itemId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = offerRepository.getListByItemId(pageable, itemId);

        Function<Object[], OfferResponseDTO> fn = entity -> {
            return entityToDTO((Offer) entity[0], (Item) entity[1], (Member) entity[2]);
        };

        return new PageResponseDTO<>(fn, result);
    }

    @Override
    public PageResponseDTO<Object[], OfferResponseDTO> getListMyPage(PageRequestDTO pageRequestDTO, String email) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = offerRepository.getListByItemOwnerEmail(email, pageable);

        Function<Object[], OfferResponseDTO> fn = entity -> {
            return entityToDTO((Offer) entity[0], (Item) entity[1], (Member) entity[2]);
        };

        return new PageResponseDTO<>(fn, result);

    }

    @Override
    @Transactional
    public void denyOffer(Long id) {

        log.info("Deny Offer : {}", id);

        Optional<Offer> offerOptional = offerRepository.findById(id);

        if(offerOptional.isEmpty()) {
            throw new IllegalStateException("해당 하는 요청이 없습니다 " + id);
        }

        Offer offer = offerOptional.get();

        offer.denyOffer();

    }

    @Override
    @Transactional
    public void startNegotiation(Long id) {

        log.info("Negotiation start : {}", id);

        Optional<Offer> offerOptional = offerRepository.findById(id);

        if(offerOptional.isEmpty()) {
            throw new IllegalStateException("해당 하는 요청이 없습니다 " + id);
        }

        Offer offer = offerOptional.get();

        offer.negoOffer();

    }
}
