package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontvo.CourseQueryVo;
import com.atguigu.eduservice.entity.vo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseQueryVo courseQueryVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseQueryVo);
        return R.ok().data(map);
    }
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVoList);
    }

    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
