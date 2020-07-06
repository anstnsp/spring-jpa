package com.audgml.demo.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

//이것은 컨트롤러에 현재 인증 된 사용자 프린시 펄을 주입하는 데 사용할 수있는 메타 주석입니다.

@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}