package com.ll.queryDSL.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getList(){
        return postRepository.findAll();
    }

    public Post getPost(Integer id){
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다 : " + id));
    }

    public void save(String subject, String content) {
        Post post = new Post();
        post.setSubject(subject);
        post.setContent(content);
        post.setCreateDate(LocalDateTime.now());
        postRepository.save(post);
    }
}
