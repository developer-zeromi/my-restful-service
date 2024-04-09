package kr.co.kimyoungmi.myrestfulservice.dto;

import kr.co.kimyoungmi.myrestfulservice.bean.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RetrieveAllUsersDto {
    private int count;
    private List<User> users;
}
