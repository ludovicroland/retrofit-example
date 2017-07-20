package fr.rolandl.retrofit.ws;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import fr.rolandl.retrofit.bo.Repo;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Ludovic Roland
 * @since 2017.07.20
 */
public final class Services
{

  public interface OnResponseListener<T>
  {

    void onSuccess(T result);

    void onFailure(Throwable throwable);

  }

  private static volatile Services instance;

  public static Services getInstance()
  {
    if (instance == null)
    {
      synchronized (Services.class)
      {
        if (instance == null)
        {
          instance = new Services();
        }
      }
    }

    return instance;
  }

  private final IServices retrofit;

  public Services()
  {
    final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    okHttpBuilder.readTimeout(30, TimeUnit.SECONDS);
    okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS);

    final GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setLenient();

    final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    retrofitBuilder.baseUrl("https://api.github.com/");
    retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    retrofitBuilder.client(okHttpBuilder.build());

    retrofit = retrofitBuilder.build().create(IServices.class);
  }

  public void listRepos(@NonNull String user, @NonNull final OnResponseListener<List<Repo>> listener)
  {
    retrofit.listRepos(user).enqueue(new Callback<List<Repo>>()
    {
      @Override
      public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response)
      {
        if (response.isSuccessful() == true)
        {
          //1.stocker les informations dans Realm
          //TODO
          //2. les donner
          listener.onSuccess(response.body());
        }
        else
        {
          //vérifier si les informations sont pas déjà dans Realm

          //cas n°1 : je chope les informations depuis Realm : success
          //null doit bien évidemment être remplacé avec les données qui viennent de Realm
          listener.onSuccess(null);

          //cas n°2 : les données ne sont pas dans Realm : failure
          //listener.onFailure(t);

        }
      }

      @Override
      public void onFailure(Call<List<Repo>> call, Throwable t)
      {
        //vérifier que les informations sont pas déjà dans realm

        //cas n°1 : je chope les informations depuis Realm : success
        //null doit bien évidemment être remplacé avec les données qui viennent de Realm
        listener.onSuccess(null);

        //cas n°2 : les données ne sont pas dans Realm : failure
        //listener.onFailure(t);
      }
    });
  }

}
