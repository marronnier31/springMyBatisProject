package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Board;
import com.zeus.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;

	@Override
	@Transactional
	public int create(Board b) throws Exception {
		return mapper.create(b);
	}

	@Override
	@Transactional(readOnly = true)
	public Board read(Board board) throws Exception {
		return mapper.read(board);
	}

	@Override
	public int update(Board b) throws Exception {
		return mapper.update(b);
		
	}

	@Override
	@Transactional
	public int delete(Board board) throws Exception {
		return mapper.delete(board);
	}

	@Override
	public List<Board> list() throws Exception {
		return mapper.list();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Board> search(Board board) throws Exception {
		return mapper.search(board);
	}

}