package com.weixin.publicnation.bean.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 登录人信息
 */
@Data
public class CurrentUser {

	/**
	 * 当前所处项目，可能为空(例如在项目主页时)
	 */
	private Long currentProjectId;

	/**
	 * 所有拥有权限的项目
	 */
	private List<Long> allProjects = Collections.emptyList();

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 租户ID（企业ID）
	 */
	private Long tenantId;

	/**
	 * 企业信息
	 */
	private Object enterpriseSetting;

	/**
	 * 账户类型：系统、租户
	 */
	private Integer accountType;

	/**
	 * 是否管理员，结合accountType可以知道是超级管理员(SuperAdmin)还是FM系统管理员(Admin)
	 */
	private Integer isAdmin;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户拥有的角色
	 */
	private Set<String> roles = Collections.emptySet();

	/**
	 * 用户拥有的权限
	 */
	private Set<String> permissions = Collections.emptySet();

	/**
	 * 用户详细信息，可能为空（例如超级管理员就没有详细信息）
	 */
	private Object detail;

//	@ApiModelProperty("是否是管理员（SuperAdmin、Admin、PAdmin）")
//	public boolean isAdmin() {
//		return isSuperAdmin() || isFMAdmin() || isPAdmin();
//	}

//	@ApiModelProperty("是否是超级管理员（SuperAdmin）")
//	public boolean isSuperAdmin() {
//		if (null != this.accountType && AccountTypeEnum.BOSS.getCode() == this.accountType) {
//			return null != this.isAdmin && BooleanEnum.YES.getCode() == this.isAdmin;
//		}
//		return false;
//	}
//
//	@ApiModelProperty("是否是FM系统管理员（Admin）")
//	public boolean isFMAdmin() {
//		if (null != this.accountType && AccountTypeEnum.BOSS.getCode() == this.accountType) {
//			return null != this.isAdmin && BooleanEnum.YES.getCode() == this.isAdmin;
//		}
//		return false;
//	}

	@ApiModelProperty("是否是项目管理员（PAdmin）")
	public boolean isPAdmin() {
		// TODO 项目管理员实际上只是一种角色而已
		return roles != null && roles.contains("PAdmin");
	}

}
