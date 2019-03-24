//:package com.yulikexuan.cloudlab.sample.domain.api.cyclic;.TestScenario.java.java


package com.yulikexuan.cloudlab.sample.domain.api.cyclic;


import com.yulikexuan.cloudlab.sample.domain.api.ITestExecuter;


class TestScenario implements Runnable {

	private final ITestExecuter testExecuter;

    TestScenario(ITestExecuter testExecuter) {
        this.testExecuter = testExecuter;
    }

    @Override
    public void run() {
    	this.testExecuter.executeRequest();
    }
	
}///:~