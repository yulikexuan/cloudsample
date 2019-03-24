//:com.yulikexuan.cloudlab.sample.domain.api.cyclic.TestExecuter.java


package com.yulikexuan.cloudlab.sample.domain.api.cyclic;


import com.yulikexuan.cloudlab.sample.domain.api.IReportGenerator;
import com.yulikexuan.cloudlab.sample.domain.api.ITestExecuter;


class TestExecuter implements ITestExecuter {

	private final IReportGenerator reportGenerator;

	TestExecuter(IReportGenerator reportGenerator) {
		this.reportGenerator = reportGenerator;
	}
	
	@Override
	public void saveReport() {
		this.reportGenerator.saveReport();    
	}

	@Override
	public void executeRequest() {
		System.out.println(">>>>>>> [TestExecuter] Execute Request ... ... ");
	}
	
}///:~