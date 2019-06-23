package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Random;

public class GameController extends Controller
{

    java.util.Random random = new Random();


    public FormFactory formFactory;

    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getGameStart(Http.Request request)
    {
        return ok(views.html.GameStart.render());
    }


    public Result postGameStart(Http.Request request)
    {
        DynamicForm form = formFactory.form().bindFromRequest(request);
        String playerName = form.get("playerName");

        return ok(views.html.Cottage.render()).addingToSession(request, "playerName", playerName);
    }

    public Result postCalmYeti(Http.Request request)
    {
        return ok(views.html.CalmYeti.render());
    }


    public Result postRunFromYeti(Http.Request request)
    {
        return ok(views.html.RunFromYeti.render());
    }

    public Result postHideinSnow(Http.Request request)
    {
        return ok(views.html.HideinSnow.render());
    }


    public Result postSharkCage(Http.Request request)
    {
        int choice = random.nextInt(2) + 1;

        switch (choice)
        {
            case (1):
            {
                String playerName = request.session().getOptional("playerName").get();
                return ok(views.html.WinSharkCage.render(playerName));
            }

            case (2):
            {
                String playerName = request.session().getOptional("playerName").get();
                return ok(views.html.LoseSharkCage.render(playerName));
            }

            default:
                break;
        }
        return ok(views.html.SharkCage.render());
    }

    public Result postGetUpAndRun(Http.Request request)
    {
        return ok(views.html.GetUp.render());
    }


}
