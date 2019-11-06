package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "用户密码")
    private String password;
    private String salt;//盐
    @Excel(name = "用户昵称")
    private String nickname;//昵称
    @Excel(name = "用户手机号")
    private String phone;//手机号
    private String province;//省份
    private String city;//城市
    private String sign;//签名
    @Excel(name = "头像", type = 2, width = 40, height = 20)
    private String photo;//头像
    @Excel(name = "性别")
    private String sex;//性别
    @Column(name = "create_date")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;//添加时间
    private String star_id;//关注明星id
}
