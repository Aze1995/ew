package com.ew.common.base;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.ew.common.enums.StatusEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 共用属性
 * @author Mr`Huang
 * @Date 2020年10月31日 上午11:58:56
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4696188299613846119L;
	
    // 备注
	@JSONField(serialize = false)
    private String remark;
    // 创建时间
	@JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    // 创建者
	@JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    // 更新时间
	@JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    // 更新者
	@JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    // 数据状态
    @TableLogic
    @JSONField(serialize = false)
    @TableField(select = false)
    private Integer status = StatusEnum.OK.getCode();

}