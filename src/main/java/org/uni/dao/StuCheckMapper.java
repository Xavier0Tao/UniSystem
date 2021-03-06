package org.uni.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.uni.domain.StuCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Tao
* @description 针对表【wt_ums_stu_check】的数据库操作Mapper
* @createDate 2022-04-18 15:20:43
* @Entity org.uni.domain.StuCheck
*/
@Mapper
@Repository
public interface StuCheckMapper extends BaseMapper<StuCheck> {

}




