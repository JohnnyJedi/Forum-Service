package telran.java58.forum.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java58.forum.dao.ForumRepository;
import telran.java58.forum.dto.PostAddUpdateDto;
import telran.java58.forum.dto.PostDto;
import telran.java58.forum.model.Post;

import java.time.LocalDate;
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
        return modelMapper.map(post, PostDto.class) ;
    }

    @Override
    public PostDto findPostById(String id) {
        return null;
    }

    @Override
    public void addLike(String id) {

    }

    @Override
    public List<PostDto> findPostsByAuthor(String author) {
        return List.of();
    }

    @Override
    public PostDto addComment(String id, String commenter, String message) {
        return null;
    }

    @Override
    public PostDto deletePost(String id) {
        return null;
    }

    @Override
    public List<PostDto> findPostsByTags(Set<String> tags) {
        return List.of();
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
