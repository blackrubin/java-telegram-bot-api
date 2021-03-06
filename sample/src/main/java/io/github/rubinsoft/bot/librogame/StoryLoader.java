package io.github.rubinsoft.bot.librogame;

import spark.Request;
import spark.Response;
import spark.Route;

public class StoryLoader implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String chatid = request.queryParams("chatid");
		String token = request.queryParams("debug");
		boolean debug = (token!=null)?token.equals("true"):false;
		return
				"<!DOCTYPE html><html lang=\"en\">"
				+ "<head><title>File Upload</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>"
				+ "<body><br/>"
				+ "<form method=\"POST\" action=\"uploadStory\" enctype=\"multipart/form-data\" >"
				+ ((debug)?"debug mode.<input type=\"hidden\" value=\"true\" id=\"debug\" name=\"debug\" readonly style=\"background-color:#eeeeee\" /> <br/><br/>":"")
				+ "ChatID:<input type=\"text\" value=\""+chatid+"\" id=\"chatid\" name=\"chatid\" readonly style=\"background-color:#eeeeee\" /> <br/><br/>"
				+ "Story title:<input type=\"text\" name=\"storyTitle\" id=\"storyTitle\" /> <br/>"
				+ "Story content:<br/><textarea rows=\"17\" cols=\"80\" name=\"content\" id=\"content\"></textarea><br/><br/>"
				+ "<input type=\"checkbox\" name=\"alfa\" value=\"alfa\"/> in progress story (check this if you want to be the only that see this story - for testing)<br/><br/>"
				+ "<input type=\"submit\" value=\"UploadStory\" name=\"uploadStory\" id=\"uploadStory\" />"
				+ "<br/><br/><br/>Note: How to write a story? <a href=\"https://fruitmaster-botfactory.rhcloud.com/storyloaderhelp?lang=en\">go to wiki</a>"
				+ "</form><br/><br/>"
				+ "</body></html>";
	}
}
