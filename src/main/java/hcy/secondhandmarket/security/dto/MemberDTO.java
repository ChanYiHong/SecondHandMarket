package hcy.secondhandmarket.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/** UserDetailsService 인터페이스가 JPA와 연동하여 실제 사용자의 정보를 가져오기 위해서는
 * UserDetailsService의 유일한 메서드인 loadUserByUsername 을 사용해야 한다.
 * 이 때 사용자의 정보를 찾아서 리턴하는 객체는 UserDetails 인터페이스 타입인데
 * 이를 구현한 User 클래스를 상속받아 정보를 가져오는 객체를 만든다.**/

@Slf4j
@Getter
@Setter
@ToString
public class MemberDTO extends User {

    private String email;

    private String name;

    private boolean fromSocial;

    private String phoneNumber;

    private int rating;

    public MemberDTO(String username,
                     String password,
                     boolean fromSocial,
                     String phoneNumber,
                     int rating,
                     Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }
}
