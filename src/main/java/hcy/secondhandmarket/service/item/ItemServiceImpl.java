package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.repository.category.CategoryRepository;
import hcy.secondhandmarket.repository.emdarea.EmdAreaRepository;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.itemimage.ItemImageRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.security.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final MemberRepository memberRepository;
    private final EmdAreaRepository emdAreaRepository;
    private final CategoryRepository categoryRepository;


    @Override
    @Transactional
    public Long save(ItemSaveDTO itemSaveDto, MemberDTO memberDTO) {

        log.info("Saving Item : {}", itemSaveDto);

        /** JPA에서 엔티티를 저장할 때, 연관된 모든 엔티티는 "영속 상태"여야 한다!
         * 따라서 연관된 모든 엔티티를 영속 상태로 만듬. (DB에서 가져옴)**/
        // member
        Optional<Member> memberOptional = memberRepository.findByEmail(memberDTO.getEmail(), memberDTO.isFromSocial());

        if(memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("Check out email or password!");
        }

        Member member = memberOptional.get();

        // emd (읍면동)
        EmdArea area = emdAreaRepository.findById(itemSaveDto.getSellingAreaId()).get();

        // category
        Category category = categoryRepository.findById(itemSaveDto.getCategoryId()).get();

        Map<String, Object> entityMap = dtoToEntity(itemSaveDto, member, area, category);

        Item item = (Item) entityMap.get("item");

        itemRepository.save(item);

        List<ItemImage> itemImageList = (List<ItemImage>) entityMap.get("imgList");

        itemImageList.forEach(itemImage -> {
            itemImageRepository.save(itemImage);
        });

        return item.getId();
    }

    @Override
    public ItemResponseDTO getOne(Long itemId) {
        Object[] result = itemRepository.findItemById(itemId);
        return entityToDTO((Item) result[0], (Member) result[1], (Category) result[2], (List<ItemImage>) result[3],
                (EmdArea) result[3], (SiggArea) result[4], (SidoArea) result[5]);
    }
}
