package com.baizhi.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")//登录
    @ResponseBody
    public Map<String, Object> login(Admin admin, String inputCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("用户输入的信息是：" + admin);
        map = adminService.login(admin, inputCode, request);
        return map;
    }

    @RequestMapping("exit")//退出
    public String exit(HttpServletRequest request) {
        //request.getSession().invalidate();
        request.getSession().invalidate();
        return "redirect:/login/login.jsp";
    }

    @RequestMapping("regist")//注册
    @ResponseBody
    public Map<String, Object> regist(HttpServletRequest request, Admin admin, String code) {
        Map<String, Object> map = new HashMap<>();
        //0判断验证码是否相同  验证码相同才进行以下操作
        String code1 = (String) request.getSession().getAttribute("code");
        if (code.equals(code1)) {
            //如果根据手机号查不到用户说明该手机号可用
            String phone = (String) request.getSession().getAttribute("phone");
            if (phone.equals(admin.getPhone())) {
                //提供注册方法
                adminService.regist(admin);
                //注册成功跳转到登录的jsp
                map.put("status", true);
            } else {
                map.put("status", false);
                map.put("message", "该手机号不是注册的手机号");
            }
            map.put("status", false);
            map.put("message", "验证码输入错误");
        }
        return map;
    }

    @RequestMapping("sendMessage")//发送验证码
    @ResponseBody
    public Map sendMessage(String phone, HttpServletRequest req) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //1.查询该手机号是否在数据库中已存在
        Admin admin = new Admin();
        admin.setPhone(phone);
        List<Admin> admins = new ArrayList<Admin>();
        admins = adminService.selectAdminsByPhone(admin);
        System.out.println(admins.size() + "------------*******************--------------------");
        if (admins.size() == 0) {//说明这个手机号还未注册过  可以发送验证码
            System.out.println(phone + "------------------");
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = "LTAIaEI24AEjv8Xr";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "P9CEJSnvgfNXDSbKpdu97tW61KgbSN";//你的accessKeySecret，参考本文档步骤2
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
//        13949121813,15732572971,13331025260,13527628084,
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("天地方圆");
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode("SMS_170835879");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            int s = (int) (Math.random() * 900000) + 100000;
            String code = "";
            code = s + "";
            request.setTemplateParam("{'code':" + code + "}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            System.out.println("*********************************************");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println(sendSmsResponse.getCode());
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                System.out.println("请求成功");
                //请求发送成功后将手机号和验证码存到session中
                HttpSession session = req.getSession();
                session.setAttribute("phone", phone);
                session.setAttribute("code", code);
                map.put("status", true);
                return map;
            } else {
                map.put("status", false);
                map.put("message", "发送验证码失败");
                return map;
            }
        } else {
            map.put("status", false);
            map.put("message", "该手机号已被注册过");
            return map;
        }
    }
}
