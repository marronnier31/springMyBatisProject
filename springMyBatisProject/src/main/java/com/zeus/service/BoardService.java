package com.zeus.service;

import java.util.List;

import com.zeus.domain.Board;

public interface BoardService {
	public int create(Board board) throws Exception; 
	public Board read(Board board) throws Exception; 
	public int update(Board board) throws Exception; 
	public int delete(Board board) throws Exception; 
	public List<Board> list() throws Exception; 
	public List<Board> search(Board board) throws Exception; 
}
