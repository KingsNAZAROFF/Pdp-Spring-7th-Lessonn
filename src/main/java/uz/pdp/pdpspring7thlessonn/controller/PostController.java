package uz.pdp.pdpspring7thlessonn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring7thlessonn.entity.Post;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.PostDto;
import uz.pdp.pdpspring7thlessonn.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('Add_POST')")
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@Valid @RequestBody PostDto postDto){
        ApiResponse response = postService.addPost(postDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/editPost/{id}")
    public ResponseEntity<?> editPost(@PathVariable Long id, @Valid @RequestBody PostDto postDto){
        ApiResponse response = postService.editPost(id, postDto);
        return  ResponseEntity.status(response.isSuccess()?200:409).body(response);

    }
    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        ApiResponse response = postService.deletePost(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
    @GetMapping("/All")
    public ResponseEntity<?> getAll(@RequestParam int page){
        Page<Post> posts = postService.getPosts(page);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Post one = postService.getOne(id);
        return ResponseEntity.ok(one);
    }


}
