package com.ischoolbar.programmer.web.buss.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.core.common.controller.BaseController;
import com.ischoolbar.programmer.core.common.exception.BusinessException;
import com.ischoolbar.programmer.core.common.hibernate.qbc.CriteriaQuery;
import com.ischoolbar.programmer.core.common.model.json.AjaxJson;
import com.ischoolbar.programmer.core.common.model.json.DataGrid;
import com.ischoolbar.programmer.core.constant.Globals;
import com.ischoolbar.programmer.core.util.MyBeanUtils;
import com.ischoolbar.programmer.core.util.StringUtil;
import com.ischoolbar.programmer.tag.core.easyui.TagUtil;
import com.ischoolbar.programmer.web.buss.entity.base.TBGoodsEntity;
import com.ischoolbar.programmer.web.buss.entity.base.TBStockEntity;
import com.ischoolbar.programmer.web.buss.service.TBGoodsServiceI;
import com.ischoolbar.programmer.web.buss.service.TBStockServiceI;
import com.ischoolbar.programmer.web.system.pojo.base.TSRole;
import com.ischoolbar.programmer.web.system.service.SystemService;

/**   
 * @Title: Controller
 * @Description: 货品表
 * @author onlineGenerator
 * @date 2014-03-19 17:21:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBGoodsController")
public class TBGoodsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBGoodsController.class);

	@Autowired
	private TBGoodsServiceI tBGoodsService;
	
	@Autowired
	private TBStockServiceI tbStockService;
	
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 货品表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tBGoods")
	public ModelAndView tBGoods(HttpServletRequest request) {
		return new ModelAndView("buss/goods/tBGoodsList");
	}

	/**
	 * 选择货品
	 * 
	 * @return
	 */
	@RequestMapping(params = "tBGoodsSelect")
	public String goods() {
		return "buss/goods/tBGoodsSelect";
	}
	
	/**
	 * 货品显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridGoods")
	public void datagridGoods(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBGoodsEntity.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TBGoodsEntity tBGoods,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBGoodsEntity.class, dataGrid);
		//查询条件组装器
		com.ischoolbar.programmer.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBGoods, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tBGoodsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除货品表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TBGoodsEntity tBGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tBGoods = systemService.getEntity(TBGoodsEntity.class, tBGoods.getId());
		message = "货品表删除成功";
		try{
			tBGoodsService.delete(tBGoods);
			List<TBStockEntity> findByProperty = tbStockService.findByProperty(TBStockEntity.class, "goodsid", tBGoods.getId());
			if(findByProperty.size() > 0){
				TBStockEntity tbStockEntity = findByProperty.get(0);
				tbStockService.delete(tbStockEntity);
			}
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "货品表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除货品表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "货品表删除成功";
		try{
			for(String id:ids.split(",")){
				TBGoodsEntity tBGoods = systemService.getEntity(TBGoodsEntity.class, 
				id
				);
				tBGoodsService.delete(tBGoods);
				List<TBStockEntity> findByProperty = tbStockService.findByProperty(TBStockEntity.class, "goodsid", tBGoods.getId());
				if(findByProperty.size() > 0){
					TBStockEntity tbStockEntity = findByProperty.get(0);
					tbStockService.delete(tbStockEntity);
				}
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "货品表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加货品表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TBGoodsEntity tBGoods,TBStockEntity tBStock, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "货品表添加成功";
		try{
			tBStock.setTotalcount("0");
			tBGoodsService.save(tBGoods, tBStock);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "货品表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新货品表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TBGoodsEntity tBGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "货品表更新成功";
		TBGoodsEntity t = tBGoodsService.get(TBGoodsEntity.class, tBGoods.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tBGoods, t);
			tBGoodsService.saveOrUpdate(t);
			List<TBStockEntity> findByProperty = tbStockService.findByProperty(TBStockEntity.class, "goodsid", t.getId());
			if(findByProperty.size() > 0){
				TBStockEntity tbStockEntity = findByProperty.get(0);
				tbStockEntity.setGoodsname(tBGoods.getGoodsname());
				tbStockEntity.setGoodstype(tBGoods.getGoodstype());
				tbStockService.saveOrUpdate(tbStockEntity);
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "货品表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 货品表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TBGoodsEntity tBGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBGoods.getId())) {
			tBGoods = tBGoodsService.getEntity(TBGoodsEntity.class, tBGoods.getId());
			req.setAttribute("tBGoodsPage", tBGoods);
		}
		return new ModelAndView("buss/goods/tBGoods-add");
	}
	/**
	 * 货品表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TBGoodsEntity tBGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBGoods.getId())) {
			tBGoods = tBGoodsService.getEntity(TBGoodsEntity.class, tBGoods.getId());
			req.setAttribute("tBGoodsPage", tBGoods);
		}
		return new ModelAndView("buss/goods/tBGoods-update");
	}
}
