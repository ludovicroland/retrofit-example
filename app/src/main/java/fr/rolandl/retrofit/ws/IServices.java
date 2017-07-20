package fr.rolandl.retrofit.ws;

import java.util.List;

import fr.rolandl.retrofit.bo.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Ludovic Roland
 * @since 2017.07.20
 */
public interface IServices
{

  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);

}
