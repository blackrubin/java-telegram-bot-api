package io.github.rubinsoft.bot.wimpb;

import spark.Request;
import spark.Response;
import spark.Route;

public class BrainLoader implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String chatid = request.queryParams("chatid");
		String token = request.queryParams("debug");
		boolean debug = (token!=null)?token.equals("true"):false;
		return
				"<!DOCTYPE html><html lang=\"en\">"
				+ "<head><title>File Upload</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>"
				+ "<body><br/>"
				+ "<form method=\"POST\" action=\"upload\" enctype=\"multipart/form-data\" >"
				+ ((debug)?"debug:<input type=\"text\" value=\"true\" id=\"debug\" name=\"debug\" readonly style=\"background-color:#eeeeee\" /> <br/><br/>":"")
				+ "ChatID:<input type=\"text\" value=\""+chatid+"\" id=\"chatid\" name=\"chatid\" readonly style=\"background-color:#eeeeee\" /> <br/><br/>"
				+ "File:<input type=\"file\" name=\"file\" id=\"file\" /> <br/><br/>"
				+ "<input type=\"submit\" value=\"Upload\" name=\"upload\" id=\"upload\" />"
				+ "</form><br/><br/>"
				+ "Requisiti Cervello:<ul><li>deve rispondere con 0 (<i>non accusa</i>) o 1 (<i>accusa</i>)</li><li>file .jar</li><li>max 10MB</li><li>eventuali file di storico necessari al Cervello, devono essere creati nella cartella in cui il jar si trova (non e' ammessa alberatura)</li></ul>"
				+ "</body></html>";
	}
}
