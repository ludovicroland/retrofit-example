package fr.rolandl.retrofit;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import fr.rolandl.retrofit.bo.Repo;
import fr.rolandl.retrofit.ws.Services;
import fr.rolandl.retrofit.ws.Services.OnResponseListener;

public final class MainActivity
    extends AppCompatActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //On fait l'appel
    Services.getInstance().listRepos("ludovicroland", new OnResponseListener<List<Repo>>()
    {
      @Override
      public void onSuccess(List<Repo> result)
      {
        //Si l'appel à réussi, je termine dans cette méthode
        //je récupère ma liste de données
        for (final Repo repo : result)
        {
          Log.d(MainActivity.class.getSimpleName(), repo.toString());
        }
      }

      @Override
      public void onFailure(Throwable throwable)
      {
        //Si l'appel à échoué, je termine ici
        //TODO : afficher une erreur à l'utilisateur
      }
    });
  }

}
