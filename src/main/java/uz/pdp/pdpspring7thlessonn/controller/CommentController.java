package uz.pdp.pdpspring7thlessonn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.CommentDto;
import uz.pdp.pdpspring7thlessonn.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentDto commentDto){
        ApiResponse response = commentService.addComment(commentDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT','DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteCom(@Valid @PathVariable Long id,HttpServletRequest request){
        ApiResponse response = commentService.deleteComment(request, id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
