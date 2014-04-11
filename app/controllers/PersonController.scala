package controllers

import scala.concurrent._
import play.Logger
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Results._
import play.api.libs.json._
import format.Formats._
import validation.Constraints._
import play.api.libs.functional.syntax._
import play.api.libs.iteratee.Done
import reactivemongo.api._
import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo._
import play.modules.reactivemongo.json.collection.JSONCollection
import play.autosource.reactivemongo._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current
import play.api.libs.json.Json._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import play.api.i18n.Messages
import in.efoundry.constants._
import in.efoundry.utils.{EfoundryConfig, MiscUtils, EfoundryEmail, HtmlEmailMessage}
import scala.concurrent.Future
import security.MyDeadboltHandler
import be.objectify.deadbolt.scala.DeadboltActions
import models._
import com.novus.salat.json.ToJValue

object PersonController extends  ReactiveMongoAutoSourceController[Person]  with securesocial.core.SecureSocial  with DeadboltActions
{ 

  def coll = db.collection[JSONCollection]("persons")  

  def index = SecuredAction {
    
    implicit request =>
    val user = request.user
    val email = user.email.getOrElse("")
    
    Ok("ok")
  }
  
   def person = Action {implicit request =>
    Ok(views.html.addPerson(addPersonForm,Map(),new MyDeadboltHandler()))
  }

  val addPersonForm: Form[Person] = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number)(Person.apply)(Person.unapply))

  def addPerson(name: String, age: Int) = Action {  implicit request =>
    //Future {
    
    // val user = request.user
    // val email = user.email.getOrElse("")
    //if (Logger.isDebugEnabled) {
    //  Logger.debug(s"Called once authorization is successful with name - ${user.fullName} and email - $email")      
       Logger.debug(s" name - $name and age - $age")      
    //}     

     val p = Person(name, age)
     coll.insert(p)
      
    	Ok("Hello" + name + " Your are " + age) //Jason.ToJValue(Person(name, age)))
   /* addPersonForm.bindFromRequest.fold(
      errors => {
        var msg = addPersonForm.constraints.keySet.map(k => {
          errors.error(k.toString).map(e => {
            e.message
          })
        }).map(a => {"- " + a.getOrElse(Constants.NULLSTRING)}).filter(a => a.size >= 3).mkString("###")
        if (Logger.isDebugEnabled) {
          Logger.debug(s"Received error in Contact us data on form submit: $msg")
        }
            BadRequest(views.html.addPerson(errors,Map(Constants.ERROR -> msg),new MyDeadboltHandler()))
      },
      fb => {
        if (Logger.isDebugEnabled) {
          Logger.debug("Received Contact us data on form submit")
        }
          //val emailBodyForCustomer = views.html.contact.contactEmail(fb,Messages("default.company.name")(lang))
          //EfoundryEmail.send(HtmlEmailMessage(to = Seq(fb.email), subject = Messages("contact.email.title.for.customer")(lang), body = emailBodyForCustomer.toString, from = EfoundryConfig.config.getString("mail.from")))
          //EfoundryEmail.send(HtmlEmailMessage(to = Seq("reactive.developer@gmail.com"), subject = Messages("contact.email.title")(lang), body = fb.toString, from = EfoundryConfig.config.getString("mail.from")))
            Ok(views.html.addPerson(addPersonForm, Map(Constants.SUCCESS -> Messages("addPersonForm.form.success.msg")(lang)),new MyDeadboltHandler()))
      })
    } */
  //} 
  
  
}
}
