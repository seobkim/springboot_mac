package exam.org.com.spring.service.posts;

import exam.org.com.spring.domain.posts.Posts;
import exam.org.com.spring.domain.posts.PostsRepository;
import exam.org.com.spring.web.dto.PostsResponseDto;
import exam.org.com.spring.web.dto.PostsSaveRequestDto;
import exam.org.com.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalAccessError("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id){
        Posts entity= postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }
}
