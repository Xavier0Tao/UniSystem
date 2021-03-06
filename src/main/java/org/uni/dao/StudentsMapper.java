package org.uni.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.uni.domain.Students;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Tao
* @description 针对表【wt_ums_students】的数据库操作Mapper
* @createDate 2022-04-18 15:24:49
* @Entity org.uni.domain.Students
*/
@Mapper
@Repository
public interface StudentsMapper extends BaseMapper<Students> {

}




