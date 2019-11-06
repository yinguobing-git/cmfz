package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_chapter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    @Id
    private String id;
    private String name;
    private String singer;//歌手
    private String size;//歌曲大小
    private String duration;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;//创建时间
    private String albumId;


}
