package com.song.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by song on 2017/2/5.
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -1792190449713107658L;

    @Length(min = 2,max = 10)
    private String name;

    @NotEmpty
    private String mobile;

    @Length(min = 6,max = 16)
    private String password;

}

