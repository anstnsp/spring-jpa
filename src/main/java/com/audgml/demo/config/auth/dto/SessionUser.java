package com.audgml.demo.config.auth.dto;


import com.audgml.demo.domain.user.User;

import lombok.Getter;

/**
 * SessionUser에는 인증된 사용자 정보만 필요함.  
 */
@Getter
public class SessionUser {
  private String name; 
  private String email; 
  private String picture; 

  public SessionUser(User user) {
    this.name = user.getName(); 
    this.email = user.getEmail();
    this.picture = user.getPicture();
  }

  /**
   * [User클래스를 사용하지 않는 이유]
   * 이는 세션에 저장하기 위해 User 클래스를 세션에 저장하려고 하니， 
    User 클래스에 직렬화를 구현하지 않았다는 의미의 에러입니다. 그럼 오류 
    를 해결하기 위해 User 클래스에 직렬화 코드를 넣으면 될까요? 그것에 
    대해선 생각해 볼 것이 많습니다. 이유는 User 클래스가 엔티티이기 때문입 
    니다. 엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모릅니다. 
    예를 들어 @OneToMany @ManyToMany 등 자식 엔티티를 갖고 있다 
    면 직렬화 대상에 자식들까지 포함되니 성능 이슈，부수 효과가 발생할 확 
    률이 높습니다. 그래서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 
    것이 이후 운영 및 유지보수 때 많은 도움이 됩니다.
   */
}