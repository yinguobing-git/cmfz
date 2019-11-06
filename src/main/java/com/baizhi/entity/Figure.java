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

@Table(name = "t_figure")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Figure {
    @Id
    private String id;
    private String name;
    @Column(name = "img_path")
    private String imgPath;//路径
    private String description;//描述
    private String status;//状态
    //序列化
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date up_date;//上传时间

}
