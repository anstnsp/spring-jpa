package com.audgml.demo.web;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Java8Tutorial {

  public static void main(String[] args) {
    //Optional은 Null포인터에러를 막는 좋은 방법임. 
    Optional<String> optional = Optional.of("anstnsp");
    
    boolean test1 = optional.isPresent(); //값있으면 true 없으면 false 
    String test2 = optional.get(); //값있으면 값 리턴하고 없으면 NoSuchElementException 던짐 
    String test3 = optional.orElse("값없다"); //값있으면 그 값 반환하고 없으면 여기에 쓴거반환. 
    
    System.out.println("test1 : "+ test1 ); 
    System.out.println("test2 : "+ test2 );
    System.out.println("test3 : "+ test3 );
    optional.ifPresent(a -> System.out.println(a + "이 존재합니다.")); //값이 존재하지만 컨슈머가 널이면 널포인터던짐.
    
    /**
     * ## 결과 ## 
     * test1 : true
     * test2 : anstnsp
     * test3 : anstnsp
     * anstnsp이 존재합니다.
     */

    List<Integer> numList1 = Arrays.asList(1, 5, 3, 9); 
    numList1.stream().sorted((a,b) -> a.compareTo(b)).forEach(System.out::println);
    


    // Suppliers produce a result of a given generic type. Unlike Functions, 
    // Suppliers don't accept arguments.
    // Supplier<Person> personSupplier = Person::new;
    // personSupplier.get();   // new Person
  }
}