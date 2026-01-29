package com.zeus.mapper;

import java.util.List;

import com.zeus.domain.Board;

public interface BoardMapper {
	public void create(Board board) throws Exception; 
	public Board read(Integer no) throws Exception; 
	public void update(Board board) throws Exception; 
	public void delete(Board board) throws Exception; 
	public List<Board> list() throws Exception;
	public List<Board> search(Board board) throws Exception; 
}
