package com.example.newt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class PipelineConsumer {

	public void createProperties(String jobName, String buildNum, String path) throws IOException {

		FileOutputStream fos = null;
		FileInputStream fis = null;
		Properties prop = null;
		Set<Object> keys = null;
		String commitMsg = null;
		
		File file = new File(path + "/job_info.properties");
		fos = new FileOutputStream(file);
		fis = new FileInputStream(file);
		prop = new Properties();

		prop.load(fis);
        keys = prop.keySet();
        for (Object key : keys) {
            prop.setProperty((String) key, "0");
        }

        commitMsg = getRevisionNum(jobName, buildNum);
        commitMsg=commitMsg.replace("<msg>", "");
        commitMsg=commitMsg.replace("</msg>", "");
        parsingCommitMsg(commitMsg, prop);
        prop.save(fos, "nothing");

        fis.close();
        fos.close();


	}

	public String getRevisionNum(String jobName, String buildNum) {

		Client client=null;
		WebResource webResource=null;
		ClientResponse response=null;
		
	
		client = Client.create();
		webResource = client.resource("http://localhost:8182/jenkins/job/").path(jobName).path(buildNum)
				.path("/api/xml").queryParam("xpath", "//changeSet/item/msg");
		

		response = webResource.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		return response.getEntity(String.class);

	}
	
	 public void parsingCommitMsg(String commitMsg, Properties prop) {

	        Integer api = 0, ui = 0, jira = 0, unit = 0;;

	        StringTokenizer token = new StringTokenizer(commitMsg, "#");

	        while (token.hasMoreTokens()) {
	            String s = token.nextToken();
	            if (s.equalsIgnoreCase("UNIT")) {
	                unit = 1;
	            } else if (s.equalsIgnoreCase("API")) {
	                api = 1;

	            } else if (s.equalsIgnoreCase("UI")) {
	                ui = 1;
	            } else if (s.equalsIgnoreCase("JIRA")) {
	                jira = 1;
	            } else {
	                System.out.println("No Data");
	            }
	        }

	        prop.setProperty("UNIT", unit.toString());
	        prop.setProperty("API", api.toString());
	        prop.setProperty("UI", ui.toString());
	        prop.setProperty("JIRA", jira.toString());
	        // save properties to project root folder
//					prop.store(output, null);

	    }



}
