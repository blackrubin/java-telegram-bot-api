package io.github.pengrad.openshift;

import io.github.pengrad.openshift.DBConnector;
import spark.Request;
import spark.Response;
import spark.Route;

public class Homepage implements Route {
	@Override
	public Object handle(Request request, Response response) throws Exception {
		String content = "";
		try {
			content = DBConnector.getParam("librogame", "HomepageContent");
		} catch (Exception e) {
			return ("Error: "+e.getMessage());
		}
		return
				"<!DOCTYPE html><html lang=\"en\">"
				+ "<head><title>Homepage</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>"
				+ "<body>" + content + "</body></html>";
	}
}
