package com.Intellijspringproject.service;

import com.Intellijspringproject.entity.Comment;
import com.Intellijspringproject.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
