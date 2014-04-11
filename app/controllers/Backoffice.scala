package controllers

import scala.concurrent._
import play.Logger
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Results._
//import play.api.cache._
import format.Formats._
import validation.Constraints._

import play.api.libs.json._
import play.api.libs.json.Json._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import play.api.i18n.Messages

import models._ //{AcelrRole, AcelrUser, ContactUs}
import in.efoundry.constants._
import in.efoundry.utils.{MiscUtils, EfoundryEmail, HtmlEmailMessage}
import scala.concurrent.Future

import securesocial.core._

import scalaz._
import Scalaz._

/*Reactivemongo */
import reactivemongo.api._
import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo._
import play.modules.reactivemongo.json.collection.JSONCollection
import play.autosource.reactivemongo._
import securesocial.core.SecureSocial

object Backoffice  extends  ReactiveMongoAutoSourceController[Person]  with securesocial.core.SecureSocial {
  
  def coll = db.collection[JSONCollection]("persons")  
    
  def index = SecuredAction { implicit request =>    
    val user = request.user
    val email = user.email.getOrElse("")    
    if (Logger.isDebugEnabled) {
      Logger.debug(s"Called once authorization is successful with name - ${user.fullName} and email - $email")
    }
    val acelrUser = AcelrUser.toAcelrUser(user)
	val isAdmin = acelrUser.roles.filter(a => a === AcelrRole.ADMIN).size >= 0
    val msg:Option[String] = if (!isAdmin) Some(Messages("auth.error")(lang)) else None
    Ok(views.html.dashboard("Welcome",acelrUser, msg)).withSession(session + ("admin" -> isAdmin.toString) + ("username" -> email))
  }
    
   /*override val insertAction =  new ActionBuilder[Request]{
    def invokeBlock[A](request: Request[A], block: Request[A] => Future[SimpleResult]) = {
      play.Logger.info(s"Before Insert Action")
      block(request).map{ a =>
        play.Logger.info(s"After Insert Action")
        a
      }
    }
  }*/

   
   
   override val insertAction : EssentialAction = SecuredAction {
    implicit securedRequest =>
    implicit val request: Request[AnyContent] = securedRequest.request
    /*new ActionBuilder[Request]{
    def invokeBlock[A](request: Request[A], block: Request[A] => Future[SimpleResult]) = {
      play.Logger.info(s"Before Insert Action")
      block(request).map{ a =>
        play.Logger.info(s"After Insert Action")
        a
      }
    }
    }*/
    Ok("")
  }
}
  
   //override val deleteAction = Authenticated.asInstanceOf[ActionBuilder[Request]]

  /*override def delete(id: BSONObjectID) : EssentialAction = SecuredAction { request => 
    Async {
	    val action = super.delete(id).asInstanceOf[Action[AnyContent]]
	    request => action(request)
    }
  } 
   
   override def find: EssentialAction = SecuredAction { securerequest =>
		Async {
		super.find(securerequest.request).run
		}
    }
   
   
  def addPerson(name: String, age: Int) = SecuredAction {  implicit request =>    
     	val user = request.user
     	val email = user.email.getOrElse("")	   
     	Logger.debug(s"Called once authorization is successful with name - ${user.fullName} and email - $email")      
     	Logger.debug(s" name - $name and age - $age")
     	val p = Person(name, age)
     	//coll.insert(p)
     	Ok("Hello" + name + " Your are " + age) //Jason.ToJValue(Person(name, age)))  
  }
}
*/