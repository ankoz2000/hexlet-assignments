package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("No post");
        }
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("No post");
        }
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("No comment");
        }
        return commentRepository.getCommentByIdAndPostId(commentId, postId);
    }

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable long postId, @RequestBody CommentDto dto) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("No post");
        }
        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setContent(dto.content());
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable long postId,
                                 @PathVariable long commentId,
                                 @RequestBody CommentDto dto) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("No post");
        }
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("No comment");
        }
        Post post = new Post();
        post.setId(postId);

        Comment comment = commentRepository.getCommentByIdAndPostId(commentId, postId);
        comment.setContent(dto.content());

        return commentRepository.save(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId,
                              @PathVariable long commentId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("No post");
        }
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("No comment");
        }
        commentRepository.deleteById(commentId);
    }
    // END
}
