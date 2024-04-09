package kr.co.kimyoungmi.myrestfulservice.repository;

import kr.co.kimyoungmi.myrestfulservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository 필요 없을건데
public interface UserRepository extends JpaRepository<User, Integer> {
}
