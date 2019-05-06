package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    Future steps = prepareDatabase().compose(v-> startHttpServer());
    steps.setHandler(startFuture.completer());
  }


  private Future prepareDatabase(){
    Future future = Future.future();
    return future;
  }

  private Future startHttpServer(){
    Future future = Future.future();
    return future;
  }
}
