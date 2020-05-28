package com.ischoolbar.programmer.web.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ischoolbar.programmer.core.common.service.impl.CommonServiceImpl;
import com.ischoolbar.programmer.web.system.pojo.base.TSAttachment;
import com.ischoolbar.programmer.web.system.service.DeclareService;


@Service("declareService")
@Transactional
public class DeclareServiceImpl extends CommonServiceImpl implements DeclareService {

	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description)
	{
		String hql="from TSAttachment t where t.businessKey='"+businessKey+"' and t.description='"+description+"'";
		return commonDao.findByQueryString(hql);
	}
	
}
