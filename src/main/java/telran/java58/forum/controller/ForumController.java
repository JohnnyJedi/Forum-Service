package telran.java58.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.java58.forum.dto.CommentRequestDto;
import telran.java58.forum.dto.PostAddUpdateDto;
import telran.java58.forum.dto.PostDto;
import telran.java58.forum.service.ForumService;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ForumController {
    private final ForumService forumService;

    @PostMapping("/forum/post/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(@PathVariable("user") String author, @RequestBody PostAddUpdateDto post) {
        return forumService.addPost(author, post);
    }

    @GetMapping("/forum/post/{postId}")
    public PostDto findPostById(@PathVariable("postId") String id) {
        return forumService.findPostById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/forum/post/{postId}/like")
    public void addLike(@PathVariable("postId") String id) {
        forumService.addLike(id);
    }

    @GetMapping("forum/posts/author/{user}")
    public List<PostDto> findPostsByAuthor(@PathVariable("user") String author) {
        return forumService.findPostsByAuthor(author);
    }

    @PatchMapping("/forum/post/{postId}/comment/{commenter}")
    public PostDto addComment(@PathVariable("postId") String id, @PathVariable String commenter, @RequestBody CommentRequestDto comment) {
        return forumService.addComment(id, commenter, comment.getMessage());
    }

    @DeleteMapping("/forum/post/{postId}")
    public PostDto deletePost(@PathVariable("postId") String id) {
        System.out.println(">>> IN CONTROLLER deletePost " + id);
        return forumService.deletePost(id);
    }

    @GetMapping("/forum/posts/tags")
    public List<PostDto> findPostsByTags(@RequestParam("values") Set<String> tags) {
        return forumService.findPostsByTags(tags);
    }

    @GetMapping("/forum/posts/period")
    public List<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom,@RequestParam LocalDate dateTo) {
        return forumService.findPostsByPeriod(dateFrom, dateTo);
    }

@PatchMapping("/forum/post/{postId}")
    public PostDto updatePost(@PathVariable("postId") String id,@RequestBody PostAddUpdateDto post) {
        return forumService.updatePost(id, post);
    }
}
