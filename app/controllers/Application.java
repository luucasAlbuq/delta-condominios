package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

	public static Result loadPublicHTML(String any) {
		return ok(index.render());
	}
	//
	// public static Result preflight(String all) {
	// response().setHeader("Access-Control-Allow-Origin", "*");
	// response().setHeader("Allow", "*");
	// response().setHeader("Access-Control-Allow-Methods",
	// "POST, GET, PUT, DELETE, OPTIONS");
	// response()
	// .setHeader("Access-Control-Allow-Headers",
	// "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
	// return ok();
	// }
}
