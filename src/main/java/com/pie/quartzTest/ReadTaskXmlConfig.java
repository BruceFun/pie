package com.pie.quartzTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ReadTaskXmlConfig {
	// ≈‰÷√Œƒº˛¬∑æ∂
	public final static String configFileName = "taskconfig.xml";

	@SuppressWarnings("unchecked")
	public static List<SchedulerConfig> getTaskConfig() throws IOException{
		List<SchedulerConfig> taskList = new ArrayList<SchedulerConfig>();

		URL url = ClassLoader.getSystemResource(configFileName);
		File file = new File(url.getFile());
		
		if(file.exists() && !file.isDirectory()){
			try {
				SAXBuilder sx = new SAXBuilder();
				Document doc = sx.build(file);

				Element root = doc.getRootElement();
				List<Element> children = root.getChildren();
				for (int i = 0; i < children.size(); i++) {
					SchedulerConfig tc = new SchedulerConfig();

					tc.setJobId(children.get(i).getChildText("jobId"));
					tc.setJobName(children.get(i).getChildText("jobName"));
					tc.setJobGroup(children.get(i).getChildText("jobGroup"));
					tc.setJobStatus(children.get(i).getChildText("jobStatus"));
					tc.setCronExpression(children.get(i).getChildText("cronExpression"));
					tc.setDesc(children.get(i).getChildText("desc"));
					taskList.add(tc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return taskList;
	}
}
