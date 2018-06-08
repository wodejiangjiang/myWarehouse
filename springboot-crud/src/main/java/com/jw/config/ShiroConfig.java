package com.jw.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm(DataSource dataSource) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setAuthenticationQuery("select password from t_user1 where name = ?");
        jdbcRealm.setUserRolesQuery("select r.name from t_user_role ur, t_user1 u, t_role r where ur.user_id = u.id and ur.role_id = r.id and u.name =?");
        jdbcRealm.setPermissionsQuery("select p.permission from t_role_permission rp, t_role r, t_permission p where r.id = rp.role_id and r.name = ?");
        jdbcRealm.setPermissionsLookupEnabled(true);
        return jdbcRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setCacheManager(new MemoryConstrainedCacheManager());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean ShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

}
