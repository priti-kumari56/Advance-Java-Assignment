package com.springrest.demo.service;

import com.springrest.demo.dto.UserMapper;
import com.springrest.demo.dto.UserRequestDTO;
import com.springrest.demo.dto.UserResponseDTO;
import com.springrest.demo.exception.ResourceNotFoundException;
import com.springrest.demo.model.User;
import com.springrest.demo.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    // CREATE
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(user));
    }

    // READ BY ID
    @Override
    @Cacheable(value = "users", key = "#id")
    public UserResponseDTO getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapper.toDTO(user);
    }

    // READ ALL
    @Override
    public List<UserResponseDTO> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable)
                .getContent()  
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // UPDATE
    @Override
    @CacheEvict(value = "users", key = "#id")
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        User existingUser = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setName(dto.getName());
        existingUser.setEmail(dto.getEmail());

        return mapper.toDTO(repository.save(existingUser));
    }

    // DELETE
    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        repository.deleteById(id);
    }
}