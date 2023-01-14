package ru.grigoriev.service;

import org.springframework.stereotype.Service;
import ru.grigoriev.model.Post;
import ru.grigoriev.repository.PostRepositoryStubImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepositoryStubImpl repository;

    public PostService(PostRepositoryStubImpl repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all().stream()
                .filter(Post::isRemoved)
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        return repository.getById(id)
                .filter(Post::isRemoved);
    }

    public Optional<Post> save(Post post) {
        return repository.save(post);
    }

    public Optional<Post> removeById(long id) {
        return repository.removeById(id);
    }
}


