package fr.rolandl.retrofit;

import java.util.List;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import fr.rolandl.retrofit.bo.Repo;
import fr.rolandl.retrofit.viewmodel.RepoViewModel;

public final class MainActivity
    extends AppCompatActivity
    implements Observer<List<Repo>>
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ViewModelProviders.of(this).get(RepoViewModel.class).repositories.observe(this, this);
  }

  @Override
  public void onChanged(@Nullable List<Repo> repos)
  {
    if (repos != null)
    {
      for (final Repo repo : repos)
      {
        Log.d(MainActivity.class.getSimpleName(), repo.toString());
      }
    }
  }

}
