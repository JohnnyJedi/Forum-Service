package telran.java58.forum.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import telran.java58.forum.dao.ForumRepository;
import telran.java58.forum.dto.CommentDto;
import telran.java58.forum.dto.PostAddUpdateDto;
import telran.java58.forum.dto.PostDto;
import telran.java58.forum.dto.exceptions.NotFoundException;
import telran.java58.forum.model.Comment;
import telran.java58.forum.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
    private final ForumRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto addPost(String author, PostAddUpdateDto postAddUpdateDto) {
        Post post = modelMapper.map(postAddUpdateDto, Post.class);
        post.setAuthor(author);
        repository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = repository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = repository.findById(id).orElseThrow(NotFoundException::new);
        post.addLike();
        repository.save(post);
    }

    @Override
    public List<PostDto> findPostsByAuthor(String author) {
        return repository.findByAuthorIgnoreCase(author).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    public PostDto addComment(String id, String commenter, String message) {
        Post post = repository.findById(id).orElseThrow(NotFoundException::new);
        Comment comment = new Comment(commenter, message);
        post.addComment(comment);
        repository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostsByTags(Set<String> tags) {
        return repository.findByTagsIn(tags).stream()
                .map(post -> modelMapper.map(post,PostDto.class))
                .toList();
    }

    @Override
    public List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return List.of();
    }

    @Override
    public PostDto updatePost(String id, PostAddUpdateDto post) {
        return null;
    }
}
