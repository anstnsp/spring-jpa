package com.audgml.demo.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import static org.springframework.test.web. servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web. servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is; 


import com.audgml.demo.web.dto.HelloResponseDto; 

@RunWith(SpringRunner.class)
@WebMvcTest //웹에 집중할수있는 어노테이션, 선언할경우 @Controller, @ControllerAdvice 사용가능 
public class TestHelloController {

  @Autowired
  private MockMvc mvc; //웹api를 테스트할때 사용 , 스프링mvc테스트의 시작점, 이걸로 get,post api 테스트가능. 

  @Test
  public void 헬로리턴() throws Exception {
    String hello = "hello"; 
    
    mvc.perform(get("/hello"))
       .andExpect(status().isOk())
       .andExpect(content().string(hello));
    System.out.println("sdfsdf");
  }

  @Test
  public void 롬복기능테스트() {
    //given 
    String name = "test";
    int amount = 1000; 

    //when 
    HelloResponseDto dto = new HelloResponseDto(name, amount);

    //then 
    assertThat(dto.getName()).isEqualTo(name);
    assertThat(dto.getAmount()).isEqualTo(amount);

  }

  @Test 
  public void 헬로디티오리턴() throws Exception {
    //given 
    String name = "hello"; 
    int amount = 2000; 

    //when 
    mvc.perform(
      get("/hello/dto")
      /**
       * param()
       * • API 테스트할 때 사용될 요청 파라미터를 설정합니다 
       * • 단， 값은 String만 허용됩니다 
       * • 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자얼로 변경해야만 가능합니다 
       */
      .param("name", name)
      .param("amount", String.valueOf(amount)))
    //then 
      .andExpect(status().isOk())
      /**
       *  jsonPath()
       • JSON 응답값을필드별로검증할수있는메소드입니다 
       • $를 기준으로 필드명을 명시합니다 
       • 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증합니다 
       */
      .andExpect(jsonPath("$.name", is(name)))
      .andExpect(jsonPath("$.amount", is(amount)));
      
    
  }
}