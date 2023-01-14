package ru.grigoriev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grigoriev.model.Post;
import ru.grigoriev.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    //@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No content")
    public ResponseEntity<Post> getById(@PathVariable long id) {
        return Optional
                .ofNullable(service.getById(id))
                .filter(Optional::isPresent)
                .map(post -> ResponseEntity.status(HttpStatus.OK).body(post.get()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        return Optional
                .ofNullable(service.save(post))
                .filter(Optional::isPresent)
                .map(p -> ResponseEntity.status(HttpStatus.OK).body(p.get()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeById(@PathVariable long id) {
        return Optional
                .ofNullable(service.removeById(id))
                .filter(Optional::isPresent)
                .map(post -> ResponseEntity.status(HttpStatus.OK).body("Content with id = " + id + ", successfully deleted."))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No content with id = " + id));
    }
}
