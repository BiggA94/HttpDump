package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerOptions;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    var options = new HttpServerOptions().setLogActivity(true);


    vertx.createHttpServer(options)
      .requestHandler(req -> {
        vertx.executeBlocking((Handler<Future<String>>) (future -> {
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
            future.complete((String) stringBuffer.toString());
          });
        }), res -> {
          String result = res.result();
          System.out.println(result);
          req.response().end(result);
        });
      })
      .listen(8080);
  }

  public void append(StringBuffer stringBuffer, String string) {
    stringBuffer.append(string);
    stringBuffer.append('\n');
  }

}
