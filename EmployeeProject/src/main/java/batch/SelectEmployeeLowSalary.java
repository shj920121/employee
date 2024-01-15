package batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SelectEmployeeLowSalary implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			URL url = new URL("http://localhost:9999/employee/low-salary-list");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while((line = br.readLine()) != null)
					sb.append(line);
				JSONArray json = new JSONArray(sb.toString());
				
				for(int i=0; i<json.length(); i++) {
					System.out.println(json.getJSONObject(i).getString("eno")
					+ " / " + json.getJSONObject(i).getString("salary")
					+ " / " + json.getJSONObject(i).getString("positionNo")
					+ " / " + json.getJSONObject(i).getString("department")
					+ " / " + json.getJSONObject(i).getString("name")
					+ " / " + json.getJSONObject(i).getString("positionName")
						);
					}				
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}

}
