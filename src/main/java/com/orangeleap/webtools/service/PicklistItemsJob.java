package com.orangeleap.webtools.service;

import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;

public class PicklistItemsJob extends QuartzJobBean {
  private PicklistItemsJobHelper jobHelper;
  
  protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
	  jobHelper.updateCache();
  }

public PicklistItemsJobHelper getJobHelper() {
	return jobHelper;
}

public void setJobHelper(PicklistItemsJobHelper jobHelper) {
	this.jobHelper = jobHelper;
}
}