package io.vertx.starter.database;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

import java.util.HashMap;

class WikiDatabaseServiceImpl implements WikiDatabaseService {

  private static final Logger LOGGER = LoggerFactory.getLogger(WikiDatabaseServiceImpl.class);
  private final HashMap<SqlQuery, String> sqlQueries;
  private final JDBCClient dbClient;
  WikiDatabaseServiceImpl(JDBCClient dbClient, HashMap<SqlQuery, String> sqlQueries, Handler<AsyncResult
      <WikiDatabaseService>> readyHandler) {
    this.dbClient = dbClient;
    this.sqlQueries = sqlQueries;
    dbClient.getConnection(ar -> {
      if (ar.failed()) {
        LOGGER.error("Could not open a database connection", ar.cause());
        readyHandler.handle(Future.failedFuture(ar.cause()));
      } else {
        SQLConnection connection = ar.result();
        connection.execute(sqlQueries.get(SqlQuery.CREATE_PAGES_TABLE), create -> {
          connection.close();
          if (create.failed()) {
            LOGGER.error("Database preparation error", create.cause());
            readyHandler.handle(Future.failedFuture(create.cause()));
          } else {
            readyHandler.handle(Future.succeededFuture(this));
          }
        });
      }
    });
  }

  @Override
  public WikiDatabaseService fetchAllPages(Handler<AsyncResult<JsonArray>> resultHandler) {
    return null;
  }

  @Override
  public WikiDatabaseService fetchPage(String name, Handler<AsyncResult<JsonObject>> resultHandler) {
    return null;
  }

  @Override
  public WikiDatabaseService createPage(String title, String markdown, Handler<AsyncResult<Void>> resultHandler) {
    return null;
  }

  @Override
  public WikiDatabaseService savePage(int id, String markdown, Handler<AsyncResult<Void>> resultHandler) {
    return null;
  }

  @Override
  public WikiDatabaseService deletePage(int id, Handler<AsyncResult<Void>> resultHandler) {
    return null;
  }
}
