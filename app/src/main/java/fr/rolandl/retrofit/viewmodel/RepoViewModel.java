package fr.rolandl.retrofit.viewmodel;

import java.util.List;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import fr.rolandl.retrofit.bo.Repo;
import fr.rolandl.retrofit.ws.Services;

/**
 * @author Ludovic Roland
 * @since 2017.12.19
 */
public final class RepoViewModel
    extends ViewModel
{

  public final MutableLiveData<List<Repo>> repositories = new MutableLiveData<>();

  private final Handler handler;

  public RepoViewModel()
  {
    super();
    handler = new Handler();
    repositories.setValue(Services.getInstance().listRepos("ludovicroland"));

    startThread();
  }

  private void startThread()
  {
    handler.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        repositories.setValue(Services.getInstance().listRepos("ludovicroland"));
        startThread();
      }
    }, 1000);
  }

}
