package cc.unitmesh.untitled.demo.controller;

import cc.unitmesh.untitled.demo.dto.CreateBlogRequest;
import cc.unitmesh.untitled.demo.dto.CreateBlogResponse;
import cc.unitmesh.untitled.demo.entity.BlogPost;
import cc.unitmesh.untitled.demo.service.BlogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @ApiOperation(value = "Get Blog by id")
    @GetMapping("/{id}")
    public BlogPost getBlog(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @ApiOperation(value = "Create a new blog")
    @PostMapping("/")
    public BlogPost createBlog(@RequestBody CreateBlogRequest request) {
        CreateBlogResponse response = new CreateBlogResponse();
        BlogPost blogPost = new BlogPost();
        BeanUtils.copyProperties(request, blogPost);
        BlogPost createdBlog = blogService.createBlog(blogPost);
        BeanUtils.copyProperties(createdBlog, response);
        return createdBlog;
    }

    // 用户故事:
    //
    //| 编号 | AC简述 | GIVEN | WHEN | THEN | 建议优先级 |
    //| --- | --- | --- | --- | --- | --- |
    //| 1 | 财务经理设定新的支付限额 | 财务经理已登录到银行现金管理系统并选择了一个尚未设定支付限额的成员单位账户 | 财务经理输入了新的支付限额并提交 | 系统接受新的支付限额并显示成功的确认消息，同时更新账户的支付限额 | 高 |
    //| 2 | 财务经理查看支付限额建议 | 财务经理已登录到银行现金管理系统并选择了一个尚未设定支付限额的成员单位账户 | 财务经理尝试设定支付限额 | 系统自动计算并显示建议的支付限额 | 高 |
    //| 3 | 财务经理尝试支付超过设定限额的金额 | 财务经理已登录到银行现金管理系统并选择了一个已设定支付限额的成员单位账户，尝试支付超过设定限额的金额 | 财务经理提交支付请求 | 系统拒绝支付请求并显示一个警告消息，指出支付金额超过了设定的支付限额 | 中 |
    //
    //**Sad Path：**
    //
    //| 编号 | AC简述 | GIVEN | WHEN | THEN | 建议优先级 |
    //| --- | --- | --- | --- | --- | --- |
    //| 1 | 财务经理尝试设定不存在的成员单位账户的支付限额 | 财务经理已登录到银行现金管理系统 | 财务经理选择一个不存在的成员单位账户并尝试设定支付限额 | 系统拒绝设定支付限额并显示一个错误消息，指出所选的成员单位账户不存在 | 高 |
    //| 2 | 财务经理尝试设定一个无效的支付限额 | 财务经理已登录到银行现金管理系统并选择了一个成员单位账户 | 财务经理输入了一个无效的支付限额（例如非数字、负数等）并尝试提交 | 系统拒绝设定支付限额并显示一个错误消息，指出支付限额必须是有效的数值 | 高 |
    //| 3 | 财务经理尝试设定超过账户余额的支付限额 | 财务经理已登录到银行现金管理系统并选择了一个成员单位账户，账户余额为X元 | 财务经理输入了一个大于X元的支付限额并尝试提交 | 系统拒绝设定支付限额并显示一个错误消息，指出支付限额不能超过账户的余额 | 中 |
    //
    //**Exceptional Path：**
    //
    //| 编号 | AC简述 | GIVEN | WHEN | THEN | 建议优先级 |
    //| --- | --- | --- | --- | --- | --- |
    //| 1 | 网络异常 | 财务经理已登录到银行现金管理系统并选择了一个成员单位账户 | 在设定支付限额时发生网络异常 | 系统显示一个错误消息，指出网络异常，让用户稍后再试 | 高 |
    //| 2 | 后端服务异常 | 财务经理已登录到银行现金管理系统并选择了一个成员单位账户 | 在设定支付限额时后端服务异常 | 系统显示一个错误消息，指出服务暂时不可用，让用户稍后再试 | 高 |
    //| 3 | 数据库异常 | 财务经理已登录到银行现金管理系统并选择了一个成员单位账户 | 在设定支付限额时数据库异常 | 系统显示一个错误消息，指出数据异常，让用户稍后再试 | 高 |
}