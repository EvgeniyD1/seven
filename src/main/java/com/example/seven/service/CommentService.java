package com.example.seven.service;

import com.example.seven.domain.Comment;
import com.example.seven.domain.User;
import com.example.seven.domain.item.Item;
import com.example.seven.dto.CommentDto;
import com.example.seven.dto.CommentMapper;
import com.example.seven.repository.CommentRepository;
import com.example.seven.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    public Page<CommentDto> findAll(Pageable pageable, Long id){
        return commentRepository.findAllByItem_Id(pageable, id)
                .map(CommentMapper.COMMENT_MAPPER::commentToCommentDto);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CommentDto save(Long id, User user, CommentDto commentDto){
        Item item = itemRepository.findById(id).orElse(null);
        if (item!=null){
            Comment comment = CommentMapper.COMMENT_MAPPER.commentDtoToComment(commentDto);
            comment.setDate(new Date());
            comment.setItem(item);
            comment.setUser(user);
            commentRepository.save(comment);
            return CommentMapper.COMMENT_MAPPER.commentToCommentDto(comment);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CommentDto update(Long id, CommentDto commentDto){
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment!=null){
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
            return CommentMapper.COMMENT_MAPPER.commentToCommentDto(comment);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment!=null){
            commentRepository.delete(comment);
        }
    }
}
