package com.woodare.template.component.cache;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.woodare.framework.component.AbstractCacheLoadListener;
import com.woodare.framework.component.CacheChangedListener;
import com.woodare.framework.component.CacheLoadListener;
import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.PasswayRouteMerchant;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.SearchPasswayRouteMerchantData;
import com.woodare.template.jpa.persistence.persistence.IPasswayRouteMerchantDAO;

/**
 * @author lu_feng
 */
public class PasswayRouteMerchantListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return PasswayRouteMerchantData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		List<PasswayRouteMerchantData> items = (List<PasswayRouteMerchantData>) cacheMaps.get(PasswayRouteMerchantData.class.getName());
		PasswayRouteMerchants.resetAll(items);
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IPasswayRouteMerchantDAO dao = application.getBean(IPasswayRouteMerchantDAO.class);
		SearchPasswayRouteMerchantData search = new SearchPasswayRouteMerchantData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<PasswayRouteMerchant> models = dao.searchItems(search);
		return SaftyBeanUtils.cloneToList(models, PasswayRouteMerchantData.class, new String[] { "createDate" });

	}
}
