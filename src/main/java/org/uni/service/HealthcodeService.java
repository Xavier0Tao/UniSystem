package org.uni.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.uni.domain.StuHealthcode;
import org.uni.dto.healthCondition;

/**
* @author Tao
* @description 针对表【wt_ums_stu_healthcode】的数据库操作Service
* @createDate 2022-04-18 15:22:09
*/
public interface HealthcodeService extends IService<StuHealthcode> {
    String judgeCode(healthCondition condition);

    boolean setCode(String identity,String color);

    StuHealthcode queryCodeStu(int sno, String name);


}
