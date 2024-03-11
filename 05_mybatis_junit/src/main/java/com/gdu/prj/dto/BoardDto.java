package com.gdu.prj.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDto {
private int rn; 
// mybatis가 DTO에 있는 필드에 자동으로 연결해서 데이터를 저장해주는데, 
//rn 칼럼이 원래 없어서 저장할 공간이 없으니까 여기에 rn을 추가해준다. 
//이렇게 앞으로 DTO 필드가 하나씩 늘어납니당..... 이제 BoardDto=table 이건 조금씩 바뀌기 시작함.
private int board_no;
private String title;
private String contents;
private Date modified_at;
private Date created_at;
}
