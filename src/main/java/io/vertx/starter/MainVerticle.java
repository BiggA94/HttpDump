package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    var options = new HttpServerOptions().setLogActivity(true);


    vertx.createHttpServer(options)
      .requestHandler(req -> {
        StringBuffer stringBuffer = new StringBuffer();
        append(stringBuffer, "#################");
        append(stringBuffer, "New Request");
        append(stringBuffer, "###### Path:");
        append(stringBuffer, req.path());
        append(stringBuffer, "###### Headers:");
        append(stringBuffer, req.headers().toString());
        append(stringBuffer, "###### Body:");
        req.bodyHandler(buff -> {
          append(stringBuffer, buff.toString());
          append(stringBuffer, "#################");
          req.response().end(stringBuffer.toString());
          System.out.println(stringBuffer.toString());
        });
      })
      .listen(8080);
  }

  public void append(StringBuffer stringBuffer, String string) {
    stringBuffer.append(string);
    stringBuffer.append('\n');
  }

}
