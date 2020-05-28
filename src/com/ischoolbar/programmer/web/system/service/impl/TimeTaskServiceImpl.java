package com.ischoolbar.programmer.web.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ischoolbar.programmer.core.common.service.impl.CommonServiceImpl;
import com.ischoolbar.programmer.web.system.service.TimeTaskServiceI;

@Service("timeTaskService")
@Transactional
public class TimeTaskServiceImpl extends CommonServiceImpl implements TimeTaskServiceI {
	
}