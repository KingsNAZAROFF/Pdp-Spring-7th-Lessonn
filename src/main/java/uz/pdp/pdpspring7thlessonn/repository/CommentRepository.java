package uz.pdp.pdpspring7thlessonn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspring7thlessonn.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
