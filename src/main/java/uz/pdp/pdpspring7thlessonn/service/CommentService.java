package uz.pdp.pdpspring7thlessonn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring7thlessonn.entity.Comment;
import uz.pdp.pdpspring7thlessonn.entity.Post;
import uz.pdp.pdpspring7thlessonn.entity.User;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.CommentDto;
import uz.pdp.pdpspring7thlessonn.repository.CommentRepository;
import uz.pdp.pdpspring7thlessonn.repository.PostRepository;
import uz.pdp.pdpspring7thlessonn.repository.UserRepository;
import uz.pdp.pdpspring7thlessonn.security.JwtProvider;
import uz.pdp.pdpspring7thlessonn.utils.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse deleteComment(HttpServletRequest request,Long id){
        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        Optional<User> byUsername = userRepository.findByUsername(username);
        User isAdmin = byUsername.get();
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()){
            return new ApiResponse("Bunday comment mavjud emas",false);
        }
        Comment deletingComment = optionalComment.get();
        if (deletingComment.getUser().getUsername().equals(username) || isAdmin.getLavozim().getName().equals(AppConstants.ADMIN)){
            commentRepository.deleteById(id);
            return new ApiResponse("Comment deleted",true);

        }
        return new ApiResponse("Siz commentni o'chira olmaysiz",false);

    }

    public ApiResponse addComment( CommentDto commentDto){

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (optionalPost.isEmpty()){
            return new ApiResponse("Bunday post mavjud emas",false);
        }
        Comment comment = new Comment();
        comment.setPost(optionalPost.get());
        comment.setText(commentDto.getComment());
        commentRepository.save(comment);
        return new ApiResponse("Comment posted",true);

    }


}
