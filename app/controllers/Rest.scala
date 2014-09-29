package controllers

import models._
import securesocial.core._

import play.api._
import com.codahale.jerkson.Json
import play.api.mvc._
import views._
import Database._

object Rest extends Controller with securesocial.core.SecureSocial {

  /**
   * This result directly redirect to the application home.
   */

  def all(page: Long) = UserAwareAction {
    implicit request =>
      if (page < 0)
        BadRequest("page number < 1")
      else {
        val db = inTransaction {

// from(ApplicationDatabase.categories)(categories =>
//         select(categories)
//         orderBy(categories.title)
//       ) 
          val list = from(Database.peopleTable)(p =>
              select(p)
              orderBy(p.id)
          )
          Json.generate(list)
        }

        // val json = Json.generate(db);

        Ok(db).as(JSON)
      }
  }
}