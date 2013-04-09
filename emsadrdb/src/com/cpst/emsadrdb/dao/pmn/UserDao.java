package com.cpst.emsadrdb.dao.pmn;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.Page;
import com.cpst.core.orm.PageUtils;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.core.utils.SpringContextUtil;
import com.cpst.emsadrdb.entity.pmn.User;
@Repository
@Transactional
public class UserDao extends HibernateDao<User,Long>{
	@Autowired
	private DataSource dataSource;
	
	@Transactional(readOnly = true)
	public  Page<User> getPageUsers(User user, Page<User> page){
		String hql="from User u where 1=1 ";
		if(user.getUsLoginId()!=null && !user.getUsLoginId().trim().equals("")){
			hql+=" and u.usLoginId like '%"+user.getUsLoginId().trim()+"%'";
		}
		if(user.getUsName()!=null && !user.getUsName().trim().equals("")){
			hql+=" and u.usName like '%"+user.getUsName().trim()+"%'";
		}
		if(user.getRulLevel()!=null && !user.getRulLevel().toString().equals("")){
			hql+=" and u.rulLevel= "+user.getRulLevel();
		}
		if(user.getUsCountryOffice()!=null && !user.getUsCountryOffice().equals("")){
			hql+=" and u.usCountryOffice= '"+user.getUsCountryOffice()+"'";
		}
		if(user.getUsProvinceOffice()!=null && !user.getUsProvinceOffice().equals("")){
			hql+=" and u.usProvinceOffice= '"+user.getUsProvinceOffice()+"'";
		}
		if(user.getUsCityOffice()!=null && !user.getUsCityOffice().equals("")){
			hql+=" and u.usCityOffice= '"+user.getUsCityOffice()+"'";
		}
		if(user.getUsDistrictOffice()!=null && !user.getUsDistrictOffice().equals("")){
			hql+=" and u.usDistrictOffice= '"+user.getUsDistrictOffice()+"'";
		}
		if(user.getUsDepartmentOffice()!=null && !user.getUsDepartmentOffice().equals("")){
			hql+=" and u.usDepartmentOffice= '"+user.getUsDepartmentOffice()+"'";
		}if(user.getUsPostsegOffice()!=null && !user.getUsPostsegOffice().equals("")){
			hql+=" and u.usPostsegOffice= '"+user.getUsPostsegOffice()+"'";
		}
		page=findPage(page, hql);
		page.setUrl(PageUtils.getPageUrl(page, "URL"));
		return page;
	}
	@Transactional(readOnly = true)
	public User getUserByLoginId(String logingId){
		String hql="from User where usLoginId=?";
		List<User> users=find(hql, logingId);
		if(users!=null && !users.isEmpty()){
			return users.get(0);
		}else{
			return null;
		}
	}
	@Transactional(readOnly = true)
	public User getUserFetchRolesByLoginId(String logingId){
		String hql="from User u left join fetch u.roles where u.usLoginId=? and u.usStatus='1'";
		List<User> users=find(hql, logingId);
		if(users!=null && !users.isEmpty()){
			return users.get(0);
		}else{
			return null;
		}
	}
	
	public void saveUser(User user){
		save(user);
	}
	public void deleteUser(User user){
		delete(user);
	}

}
