package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

case class Article(title: String, content: String)

class Application extends Controller {

  val articleForm: Form[Article] = Form(
    mapping(
      "title" -> nonEmptyText,
      "content" -> nonEmptyText
    )(Article.apply)(Article.unapply)
  )


  def index = Action {
    Ok(views.html.index())
  }

  def editArticle = Action {
    val emptyArticle = Article("", "")
    Ok(views.html.article(articleForm.fill(emptyArticle)))
  }

  def postArticle = Action { implicit request =>
    articleForm.bindFromRequest.fold(
      formWithError => {
        Ok(views.html.index())
      },
      article => {
        Ok(views.html.index())
      }
    )
  }

}
