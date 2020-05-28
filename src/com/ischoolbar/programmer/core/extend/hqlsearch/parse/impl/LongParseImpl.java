package com.ischoolbar.programmer.core.extend.hqlsearch.parse.impl;

import com.ischoolbar.programmer.core.common.hibernate.qbc.CriteriaQuery;
import com.ischoolbar.programmer.core.extend.hqlsearch.HqlGenerateUtil;
import com.ischoolbar.programmer.core.extend.hqlsearch.parse.IHqlParse;

public class LongParseImpl implements IHqlParse {

	
	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		if (HqlGenerateUtil.isNotEmpty(value))
			cq.eq(name, value);		
	}

	
	public void addCriteria(CriteriaQuery cq, String name, Object value,
			String beginValue, String endValue) {
		if (HqlGenerateUtil.isNotEmpty(beginValue)) {
			cq.ge(name, Long.parseLong(beginValue));
		}
		if (HqlGenerateUtil.isNotEmpty(endValue)) {
			cq.le(name, Long.parseLong(endValue));
		}
		if (HqlGenerateUtil.isNotEmpty(value)) {
			cq.eq(name, value);
		}		
	}

}
