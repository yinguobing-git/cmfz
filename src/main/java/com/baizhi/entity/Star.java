package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_star")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Star {
    @Id
    private String id;
    private String nickname;//昵称
    private String realname;//真名
    private String photo;//照片
    private String sex;//性别
    //生日序列化 和 反序列化
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bir;//生日
}
