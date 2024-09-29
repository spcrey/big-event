package com.spcrey.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spcrey.anno.State;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class Article {
    @NotNull(groups = {Update.class})
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^\\S[\\s\\S]{0,19}$")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String coverImg;

    @State
    private String state;

    @NotNull
    private Integer categoryId;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public interface Add extends Default {}
    
    public interface Update extends Default {}
}
