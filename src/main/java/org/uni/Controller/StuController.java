package org.uni.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uni.domain.Students;
import org.uni.service.StudentsService;
import org.uni.dto.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StuController {

    @Autowired
    private StudentsService studentsService;

    /**
     * 注册学生用户
     *
     * @param
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Students student) {

        boolean hasDuplicateId = studentsService.hasDuplicateId(student.getId());

        if (hasDuplicateId) return new Result(false, "重复身份证号码");
        return new Result(studentsService.save(student));
    }

    /**
     * 根据学号删除学生
     */
    @DeleteMapping("/del/{sno}")
    public Result delBySno(@PathVariable("sno") int sno) {
        return new Result(studentsService.removeById(sno));
    }

    /**
     * 查询所有学生
     */
    @GetMapping("/getAll")
    public Result getAll() {
        return new Result(studentsService.list());
    }

    /**
     * 根据学号查询学生
     */
    @GetMapping("/{stuNo}")
    public Result getById(@PathVariable Integer stuNo) {
        return new Result(studentsService.getById(stuNo));
    }

    /**
     * 条件查询
     */
    @GetMapping
    public Result conditionalQuery(@RequestParam(required = false, name = "homeTown") String homeTown,
                                   @RequestParam(name = "collegeId", required = false) Integer collegeId,
                                   @RequestParam(name = "majorNo", required = false) Integer majorNo,
                                   @RequestParam(name = "sex", required = false) String sex,
                                   @RequestParam(name = "studentNo", required = false) Integer studentNo) {

        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq(homeTown != null, "wt_sori10", homeTown)
                .eq(collegeId != null, "wt_collid10", collegeId)
                .eq(majorNo != null, "wt_mno10", majorNo)
                .eq(sex != null, "wt_sex10", sex)
                .eq(studentNo != null, "wt_sno10", studentNo);

        List<Students> list = studentsService.list(wrapper);

        return list.size() == 0 ? new Result(false, "没有满足条件的学生") : new Result(list);
    }

    /**
     * 通过学号或身份证登录学生账号
     * @param sno
     * @param identity
     */
    @PostMapping("/login")
    public Result login(@RequestParam(name = "studentNo",required = false) Integer sno
            , @RequestParam(name = "identity", required = false) String identity, HttpServletRequest request) {

        //查询条件
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq(sno != null, "wt_sno10", sno)
                .eq(identity != null, "wt_id10", identity);

        Students stu = studentsService.getOne(wrapper);
        HttpSession session = request.getSession();

        //在login之后把信息加入session中
        if (stu != null) {
            session.setAttribute("ROLE", "STU");
            session.setAttribute("ROLEINFO", stu);
        }

        return stu == null ? new Result(false, "没有此学生信息") : new Result(stu);
    }


}
