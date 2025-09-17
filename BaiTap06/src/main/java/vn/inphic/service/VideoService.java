package vn.inphic.service;

import vn.inphic.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    List<Video> findAll();
    Page<Video> findAll(Pageable pageable);
    Optional<Video> findById(Long id);
    Video save(Video video);
    void deleteById(Long id);
    Page<Video> findByTitleContaining(String title, Pageable pageable);
}
