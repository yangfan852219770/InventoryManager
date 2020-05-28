package com.ischoolbar.programmer.web.buss.service;

import java.io.Serializable;

import com.ischoolbar.programmer.core.common.service.CommonService;
import com.ischoolbar.programmer.web.buss.entity.base.TBPurchaseEntity;
import com.ischoolbar.programmer.web.buss.entity.base.TBStockEntity;

public interface TBPurchaseServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TBPurchaseEntity t);
 	
 	/**
 	 * 更新入库表和库存表
 	 * @param tbp
 	 * @param tbs
 	 * @return
 	 */
 	public boolean doSaveorUpdateStock(TBPurchaseEntity tbp);
 	
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TBPurchaseEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TBPurchaseEntity t);
}
