package com.spcrey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spcrey.pojo.Category;

@Mapper
public interface CategoryMapper {
    
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" + 
            " values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    public void add(Category category);

    @Select("select * from category where create_user=#{userId}")
    public List<Category> list(Integer userId);

    @Select("select * from category where id=#{id}")
    public Category findById(Integer id);

    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time=#{updateTime} where id=#{id}")
    public void update(Category category);

    @Delete("delete from category where id=#{id}")
    public void deleteById(Integer id);
}
