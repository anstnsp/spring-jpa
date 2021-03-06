package com.audgml.demo.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.audgml.demo.domain.BaseTimeEntity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity



public class Posts extends BaseTimeEntity { //실제 db와 매칭될 클래스를 Entity클래스라고 함. 

  //웬만하면 Entity의 PK는 Long 타입의 AutoIncrement를 추천합니디
  @Id //해당 테이블의 pk필드를 나타냄. 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 

  /**  @Column 
   * • 테이블의 갈럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼 이됩니다 
   * • 사용하는 이유는， 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다 
   * • 문자열의 경우 VARCHAR(255)가 기본값인데， 사이즈를 500으로 늘리고 싶거나(ex title),
   *   타입을 TEXT로 변경하고 싶거나(ex: content) 등의 경우에 사용됩니다 
   */
  @Column(length = 500, nullable = false) // 사용하는 이유는， 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다 
  private String title; 

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content; 

  private String author; 

  @Builder
  public Posts(String title, String content, String author) {
    this.title = title; 
    this.content = content; 
    this.author = author; 
  }


/**
 * 자바빈 규약을 생각하면서 getter/setter를 무작정 생성하는 경우가 있습 
  니다. 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 
  하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경 시 정말 
  복잡해집니다. 
  그래서 Entity 클래스에서는 절대 Setter 메소드를 만들지 않습니다 대신 해 
  당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 
  메소드를 추가해야만 합나다. 
  ex) public class Order{ 
        public void cancelOrder、(){ 
          this . status false; 
        }
      public void 주문서비스의-취소이벤트 (){ order.cancelIrder(); } 
 */
    public void update(String title, String content) {
      this.title = title; 
      this.content = content; 
    }
}
