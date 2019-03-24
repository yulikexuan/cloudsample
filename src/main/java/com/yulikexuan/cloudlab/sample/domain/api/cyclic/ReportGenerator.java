//:package com.yulikexuan.cloudlab.sample.domain.api.cyclic;.ReportGenerator.java.java


package com.yulikexuan.cloudlab.sample.domain.api.cyclic;


import java.util.Optional;

import com.yulikexuan.cloudlab.sample.domain.api.IReportGenerator;


class ReportGenerator implements IReportGenerator {
	
	private static ReportGenerator instance;
	
	private Runnable testScenario;
	
	private ReportGenerator() {}
	
	static ReportGenerator getInstance() {
		if (instance == null) {
			instance = new ReportGenerator();
		}
		return instance;
	}

	@Override
	public void showTestScenario() {
		Optional.ofNullable(this.testScenario)
				.ifPresent(Runnable::run);
	}
	
    public void setTestScenario (Runnable testScenario) {
    	this.testScenario = testScenario;
    }
    
    @Override
    public void saveReport() {
    	System.out.println(">>>>>>> [ReportGenerator] Print Report ... ... ");
    }
    
}///:~