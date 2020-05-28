package com.ischoolbar.programmer.web.system.service;

import java.util.List;

import com.ischoolbar.programmer.core.common.service.CommonService;
import com.ischoolbar.programmer.web.system.pojo.base.TSAttachment;

/**
 * 
 * @author   
 *
 */
public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}
