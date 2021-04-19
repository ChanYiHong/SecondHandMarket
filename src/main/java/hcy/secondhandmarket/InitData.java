package hcy.secondhandmarket;

import hcy.secondhandmarket.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.HashSet;

@Component
@Profile("local")
@RequiredArgsConstructor
public class InitData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.initMember();
        initDataService.initCategory();
        initDataService.initArea();
    }

    @Component
    static class InitDataService {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private EntityManager em;

        @Transactional
        public void initMember() {
            Member member = new Member(
                    "user1@hcy.com",
                    passwordEncoder.encode("1234"),
                    "홍찬의",
                    "01099559135",
                    5,
                    false,
                    new HashSet<>()
            );

            member.addMemberRole(MemberRole.USER);

            em.persist(member);
        }

        @Transactional
        public void initCategory() {
            em.persist(new Category("전자기기"));
            em.persist(new Category("컴퓨터"));
            em.persist(new Category("인형"));
            em.persist(new Category("주방도구"));
            em.persist(new Category("차량"));
            em.persist(new Category("캠핑"));
            em.persist(new Category("기타"));
        }

        @Transactional
        public void initArea() {
            SidoArea 서울 = SidoArea.builder().name("서울").build();
            SidoArea 경기 = SidoArea.builder().name("경기").build();

            em.persist(서울);
            em.persist(경기);

            SiggArea 송파구 = SiggArea.builder().name("송파구").sidoArea(서울).build();
            SiggArea 강남구 = SiggArea.builder().name("강남구").sidoArea(서울).build();

            SiggArea 영통구 = SiggArea.builder().name("영통구").sidoArea(경기).build();
            SiggArea 권선구 = SiggArea.builder().name("권선구").sidoArea(경기).build();

            em.persist(송파구);
            em.persist(강남구);

            em.persist(영통구);
            em.persist(권선구);

            EmdArea 잠실2동 = EmdArea.builder().name("잠실2동").siggArea(송파구).build();
            EmdArea 가락동 = EmdArea.builder().name("가락동").siggArea(송파구).build();

            EmdArea 논현동 = EmdArea.builder().name("논현동").siggArea(강남구).build();
            EmdArea 신사동 = EmdArea.builder().name("신사동").siggArea(강남구).build();

            EmdArea 정자3동 = EmdArea.builder().name("정자3동").siggArea(영통구).build();
            EmdArea 정자2동 = EmdArea.builder().name("정자2동").siggArea(영통구).build();

            EmdArea 권선1동 = EmdArea.builder().name("권선2동").siggArea(권선구).build();
            EmdArea 권선2동 = EmdArea.builder().name("권선2동").siggArea(권선구).build();

            em.persist(잠실2동);
            em.persist(가락동);
            em.persist(논현동);
            em.persist(신사동);
            em.persist(정자3동);
            em.persist(정자2동);
            em.persist(권선1동);
            em.persist(권선2동);

        }
    }
}
