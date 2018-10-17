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
import com.woodare.template.helper.cache.DownMerchantFundAccounts;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.SearchDownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;

/**
 * @author lu_feng
 */
public class DownMerchantFundAccountListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return DownMerchantFundAccountData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		List<DownMerchantFundAccountData> items = (List<DownMerchantFundAccountData>) cacheMaps.get(DownMerchantFundAccountData.class.getName());
		DownMerchantFundAccounts.resetAll(items);
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IDownMerchantFundAccountDAO dao = application.getBean(IDownMerchantFundAccountDAO.class);
		SearchDownMerchantFundAccountData search = new SearchDownMerchantFundAccountData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<DownMerchantFundAccount> models = dao.searchItems(search);
		return SaftyBeanUtils.cloneToList(models, DownMerchantFundAccountData.class, new String[] { "createDate" });

	}
}
