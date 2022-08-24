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

import java.util.Date;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    public Page<Comment> findAll(Pageable pageable, Long id){
        return commentRepository.findAllByItem_Id(pageable, id);
    }

    public Comment save(Long id, User user, CommentDto commentDto){
        Item item = itemRepository.findById(id).orElse(null);
        if (item!=null){
            Comment comment = CommentMapper.COMMENT_MAPPER.commentDtoToComment(commentDto);
            comment.setDate(new Date());
            comment.setItem(item);
            comment.setUser(user);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment update(Long id, CommentDto commentDto){
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment!=null){
            comment.setText(commentDto.getText());
            return commentRepository.save(comment);
        }
        return null;
    }

    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment!=null){
            commentRepository.delete(comment);
        }
    }
}
