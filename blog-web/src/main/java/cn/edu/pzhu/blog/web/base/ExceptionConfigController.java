package cn.edu.pzhu.blog.web.base;

import cn.edu.pzhu.base.exception.BusinessException;
import cn.edu.pzhu.base.response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author CG
 * @date 2019/02/28 23:58
 * @discription
 */
@ControllerAdvice
public class ExceptionConfigController {

    @ExceptionHandler
    @ResponseBody
    public ApiResponse exceptionHandler(BusinessException e) {
        return ApiResponse.error(e.getMessage());
    }
}
