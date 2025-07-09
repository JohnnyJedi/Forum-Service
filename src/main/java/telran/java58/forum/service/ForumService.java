package telran.java58.forum.service;

import telran.java58.forum.dto.PostAddUpdateDto;
import telran.java58.forum.dto.PostDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ForumService {
PostDto addPost(String author,PostAddUpdateDto post);

PostDto findPostById(String id);

void addLike(String id);

List<PostDto> findPostsByAuthor(String author);

PostDto addComment(String id,String commenter,String message);

PostDto deletePost(String id);

List<PostDto> findPostsByTags(Set<String>tags);

List<PostDto> findPostsByPeriod(LocalDate dateFrom,LocalDate dateTo);

PostDto updatePost(String id,PostAddUpdateDto post);

}
