package com.pie.quartzTest;

import java.io.IOException;
import java.util.List;

public class TestMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<SchedulerConfig> taskConfig = ReadTaskXmlConfig.getTaskConfig();
		System.out.println(22);
	}
}
