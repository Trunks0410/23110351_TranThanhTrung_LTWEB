package vn.inphic.service;

import vn.inphic.entity.Category;
import vn.inphic.entity.User;
import vn.inphic.entity.Video;
import vn.inphic.repository.CategoryRepository;
import vn.inphic.repository.UserRepository;
import vn.inphic.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Video> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Video> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Video save(Video video) {
        if (video.getCategory() != null && video.getCategory().getId() != null) {
            Optional<Category> category = categoryRepository.findById(video.getCategory().getId());
            video.setCategory(category.orElse(null));
        }
        if (video.getUser() != null && video.getUser().getId() != null) {
            Optional<User> user = userRepository.findById(video.getUser().getId());
            video.setUser(user.orElse(null));
        }
        return repository.save(video);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Video> findByTitleContaining(String title, Pageable pageable) {
        return repository.findByTitleContaining(title, pageable);
    }
}
