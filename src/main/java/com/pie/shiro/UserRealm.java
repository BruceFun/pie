package com.pie.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.pie.domain.User;
import com.pie.repositories.UserRepository;

/**
 * shiro 登陆及授权拦截器
 * @author bruce_000
 *
 */
public class UserRealm extends AuthorizingRealm implements Realm,InitializingBean{
	@Autowired
	private UserRepository userRepository;

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		
		
		
		
		return null;
	}

	/**
	 * 登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息  
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		User user = userRepository.getUserByName(usernamePasswordToken.getUsername());
		String password = String.valueOf(usernamePasswordToken.getPassword());
		System.out.println("===============" + password);
		System.out.println("===============" + user.getName());
		System.out.println("===============" + this.getName());
//		if(user == null){ // 用户不存在
//			throw new AuthenticationException("用户名/密码错误……");
//		}else if(StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())){ // 排除空用户存在
//			throw new AuthenticationException("用户名/密码错误……");
//		}else if(!user.getPassword().equals(password)){ // 密码错误
//			throw new AuthenticationException("用户名/密码错误……");
//		}
		
		return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), this.getName()); // getName()-realm name
	}
	
	/**
	 * 清除授权信息
	 */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
    
    /**
     * 清除认证信息
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }
    
    /**
     * 清除登陆缓存
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
    
    /**
     * 清除所有授权信息
     */
    public void clearAllCachedAuthorizationInfo() {
        if(getAuthorizationCache() != null){
        	getAuthorizationCache().clear();
        }
    }
    
    /**
     * 清除所有认证信息
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }
    
    /**
     * 清除所有缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		
		return getClass().getName();
	}
}
