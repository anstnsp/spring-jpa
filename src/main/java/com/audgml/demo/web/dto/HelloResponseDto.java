package com.audgml.demo.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
/** @RequiredArsContructor 
 *  선언된모든 final 필드가 포함된생성자를생성해줍니다 
 * • final이 없는 필드는 생성자에 포함되지 않습니다 
 */
@RequiredArgsConstructor
public class HelloResponseDto {

  private final String name; 
  private final int amount; 

}