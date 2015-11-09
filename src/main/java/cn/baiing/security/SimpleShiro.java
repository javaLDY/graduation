package cn.baiing.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import cn.baiing.db.model.User;

/**
 * 简化Shiro使用的工具类
 * 
 * @author xueqi
 *
 */
public final class SimpleShiro {
	private SimpleShiro() {
	}

	/**
	 * 获得系统当前用户
	 * 
	 * @return
	 */
	public static User getCurrentSystemUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 按照系统加密规则加密原始密码
	 * 
	 * @param plainPassword
	 * @return
	 */
	public static String encryptPassword(String plainPassword) {
		return new Sha256Hash(plainPassword).toHex();
	}
	
}
