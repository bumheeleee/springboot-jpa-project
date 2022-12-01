package jpabook.jpashop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    //response dto의 확장성을 더 크게 해준다.
    //private int count; -> 이런식으로 더 들어가도 좋음
    private T data;
}
