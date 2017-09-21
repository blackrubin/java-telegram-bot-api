package io.github.pengrad.openshift;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import io.github.rubinsoft.bot.fruitmasterbot.utils.Constants;
import spark.Request;
import spark.Response;
import spark.Route;

public class WebConsole implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String tailLen_ = request.queryParams("tailLen");
		String file = (request.queryParams("file")==null)?"fruitmaster.log":request.queryParams("file");
		String match = request.queryParams("grep");
		String dir = request.queryParams("dir");
		StringBuilder sb = new StringBuilder();
		if(dir!=null){
			Process p = Runtime.getRuntime().exec("ls "+System.getenv("OPENSHIFT_LOG_DIR"));
			p.waitFor();
			Scanner sc = new Scanner(p.getInputStream());
			for(;sc.hasNext();){
				sb.append(sc.nextLine()+"<br>");
			}
			sc.close();
			return sb.toString();
		}
		int tailLen = (tailLen_==null)?500:Integer.parseInt(tailLen_);

		String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
		sb.append("server time: "+timestamp+"<br><br>");
		//stampo statistiche in ordine inverso
		sb.append("file"+System.getenv("OPENSHIFT_LOG_DIR")+file+"<br><br>");
		//		File stat = new File(Constants.FILE_STATISTICHE);
		File stat = new File(System.getenv("OPENSHIFT_LOG_DIR")+file);
		if (stat.exists()){
			ArrayList<String> al = new ArrayList<>();
			Scanner sc = new Scanner(stat);
			for(;sc.hasNext();){
				String line = sc.nextLine();
				if(match==null || line.contains(match))
					al.add(line);	
			}
			sc.close();
			int start=( (al.size()-tailLen)<0 )? 0 : al.size()-tailLen;
			for(int i=start;i<al.size();i++){
				sb.append(al.get(i)+"<br>");
			}
		}
		//		String TailCommand = 
		//				"/bin/sh -c 'tail "+System.getenv("OPENSHIFT_LOG_DIR")+"fruitmaster.log'";
		//		Process p = Runtime.getRuntime().exec(TailCommand);
		//		p.waitFor();
		//		Scanner sc = new Scanner(p.getInputStream());
		//		for(;sc.hasNext();){
		//			sb.append(sc.nextLine()+"<br>");
		//		}
		//		sc.close();
		//		sc = new Scanner(p.getErrorStream());
		//		for(;sc.hasNext();){
		//			sb.append(sc.nextLine()+"<br>");
		//		}
		//		sc.close();
		return sb.toString();
	}

	public static boolean appendMessage(String message){
		try {
			FileWriter fw = new FileWriter(new File(Constants.FILE_STATISTICHE),true);
			String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
			fw.append(timestamp+": "+message);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
