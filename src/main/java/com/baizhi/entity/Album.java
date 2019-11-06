package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_album")
@Data
@AllArgsConstructor
@NoArgsConstructor
//专辑表
public class Album {
    @Id
    private String id;
    private String name;
    @Column(name = "cover_img")
    private String coverImg;//封面
    private Integer count;//集数
    private String content;//内容
    @Column(name = "pub_date")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;//出版时间
    @Column(name = "star_id")
    private String starId;
    private Star star;
}
