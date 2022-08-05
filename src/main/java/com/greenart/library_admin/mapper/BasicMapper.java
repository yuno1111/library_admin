package com.greenart.library_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.genre_info;
import com.greenart.library_admin.data.writer_info;

@Mapper
public interface BasicMapper {
    public void insertGenre(String name);
    public void insertWriter(String name);
    public void deleteGenre(Integer seq);
    public void deleteWriter(Integer seq);
    public List<genre_info> selectGenre();
    public List<writer_info> selectWriter();
}
