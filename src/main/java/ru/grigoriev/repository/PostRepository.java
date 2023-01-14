package ru.grigoriev.repository;

import ru.grigoriev.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> all();

    Optional<Post> getById(long id);

    Optional<Post> save(Post post);

    Optional<Post> removeById(long id);
}
