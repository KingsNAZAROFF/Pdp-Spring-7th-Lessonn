package uz.pdp.pdpspring7thlessonn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring7thlessonn.entity.Post;
import uz.pdp.pdpspring7thlessonn.exceptions.ResourceNotFoundException;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.PostDto;
import uz.pdp.pdpspring7thlessonn.repository.PostRepository;

import java.util.Locale;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getTitle().toLowerCase(Locale.ROOT).replace(" ","-"));
        postRepository.save(post);
        return new ApiResponse("Post saqlandi",true);
    }

    public ApiResponse editPost(Long id,PostDto postDto){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()){
            return new ApiResponse("Bunday post mavjud emas",false);
        }
        Post editingPost = optionalPost.get();
        editingPost.setTitle(postDto.getTitle());
        editingPost.setText(postDto.getText());
        editingPost.setUrl(postDto.getTitle().toLowerCase(Locale.ROOT).replace(" ","-"));
        postRepository.save(editingPost);
        return new ApiResponse("Post o'zgartirildi",true);

    }

    public ApiResponse deletePost(Long id){
        postRepository.deleteById(id);
        return new ApiResponse("Post o'chirildi",true);
    }
    public Page<Post> getPosts(int page){
        Pageable pageable = PageRequest.of(page,10);
        return postRepository.findAll(pageable);
    }
    public Post getOne(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
       return optionalPost.orElseThrow(()-> new ResourceNotFoundException("Post topilmadi","id :", id));
    }
}
