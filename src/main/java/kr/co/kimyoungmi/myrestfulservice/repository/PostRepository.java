package kr.co.kimyoungmi.myrestfulservice.repository;

import kr.co.kimyoungmi.myrestfulservice.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
