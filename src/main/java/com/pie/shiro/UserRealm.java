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
 * shiro ��½����Ȩ������
 * @author bruce_000
 *
 */
public class UserRealm extends AuthorizingRealm implements Realm,InitializingBean{
	@Autowired
	private UserRepository userRepository;

	/**
	 * Ȩ����֤
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		
		
		
		
		return null;
	}

	/**
	 * ��½��֤
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//UsernamePasswordToken������������ύ�ĵ�¼��Ϣ  
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		User user = userRepository.getUserByName(usernamePasswordToken.getUsername());
		String password = String.valueOf(usernamePasswordToken.getPassword());
		System.out.println("===============" + password);
		System.out.println("===============" + user.getName());
		System.out.println("===============" + this.getName());
//		if(user == null){ // �û�������
//			throw new AuthenticationException("�û���/������󡭡�");
//		}else if(StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())){ // �ų����û�����
//			throw new AuthenticationException("�û���/������󡭡�");
//		}else if(!user.getPassword().equals(password)){ // �������
//			throw new AuthenticationException("�û���/������󡭡�");
//		}
		
		return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), this.getName()); // getName()-realm name
	}
	
	/**
	 * �����Ȩ��Ϣ
	 */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
    
    /**
     * �����֤��Ϣ
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }
    
    /**
     * �����½����
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
    
    /**
     * ���������Ȩ��Ϣ
     */
    public void clearAllCachedAuthorizationInfo() {
        if(getAuthorizationCache() != null){
        	getAuthorizationCache().clear();
        }
    }
    
    /**
     * ���������֤��Ϣ
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }
    
    /**
     * ������л���
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
