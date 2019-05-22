package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.service.category.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 标签 controller.
 * @author:CG
 * @date:2019/04/21 21:41
 */
@RequestMapping("/admin/tag")
@Controller
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse delete(Integer id) {
        Integer uId = 1;
        try {
            tagService.deleteById(uId, id);
        } catch (Exception e) {
            log.error("TagController.delete 删除标签失败。", e);
            return ApiResponse.error("操作失败");
        }
        return ApiResponse.success();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ApiResponse add(@RequestParam("tagStr") String tagStr) {


        return ApiResponse.success();
    }


}
