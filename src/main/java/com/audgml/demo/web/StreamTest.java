package com.audgml.demo.web;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {


  public static void main(String[] args) {

    List<String> names = Arrays.asList("anstn","eirjr", "aqoiq", "gkegop");
    //기존 코딩방식 
    int count = 0; 
    for (String name : names) { 
      if( name.contains("a")) {
        count ++; 
      }
    }
    System.out.println("Count : "+ count);    

    //스트림 이용한 방식 
    int count2 = (int) names.stream().filter(x -> x.contains("a")).count();
    System.out.println("Count2 : "+ count2);
    System.out.println();
    /**
     * ##결과##  
     * Count : 2
     * Count2 : 2
     */

    System.out.println("## map(), concat(), forEach() ##");
    //parallelStream(), map() , forEach()
    //parallelStream은 병렬스트림. 1)map으로 모든 원소에 !를 붙이고 2)모든원소를 하나씩 출력 
    List<String> item = Arrays.asList("pp","tt", "qwe", "zlsdf", "ooo");
    item.parallelStream().map(x -> x.concat("!")).forEach(r -> System.out.print(r + " "));
    System.out.println();
    System.out.println();
    /**
     * ##결과## 
     * qwe! tt! pp! zlsdf! ooo!
     */
    
     //Distinct 스트림의 요소에서 중복제거 
     System.out.println("## distict() : 스트림의 요소에서 중복제거 ##");
     List<Integer> nums = Arrays.asList(1,2,3,4,1,2,3,4,1,2,3,4);
     nums.stream().distinct().forEach(a -> System.out.print(a  + "  "));
    //  nums.stream().distinct().forEach(System.out::println);
     System.out.println();
     System.out.println();
     
    /**
     * ##결과##
     * 1  2  3  4
     */
    System.out.println("## skip() => skip(3)이면 처음3개의 요소 제외하고 나머지 요소들로 새로운 stream 만듬 ##");
    List<Integer> nums2 = Arrays.asList(1,2,3,4,1,2,3,4,1,2,3,4);
    nums2.stream().skip(3).forEach(r -> System.out.print(r + " "));
    System.out.println();
    System.out.println();

    System.out.println("mapToInt() : ");
    IntStream intStream = IntStream.of(2,4,6,8,10); 
    LongStream longStream = intStream.mapToLong(num -> (long)num );
    longStream.forEach(System.out::print);
    System.out.println();
    // 246810
    
    System.out.println("## reduce() : 누적기 ##");
    List<Integer> numList = Arrays.asList(1, 2, 3);
    System.out.println(numList.stream().reduce((b,c) -> b+c).get());
    System.out.println();
    //결과 => 6 ( 1 + 2 + 3 )

    System.out.println("## forEach() : 각요소를 돌면서 값 처리##");
    List<String> nameList = Arrays.asList("영수", "철수", "민아", "수영", "수영");
    Set<String> nameSet = nameList.stream().collect(Collectors.toSet()); 
    nameSet.forEach(result -> System.out.println(result));
    //결과 -> 철수 수영 영수 민아 


  }
}