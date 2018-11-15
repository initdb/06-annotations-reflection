package de.thro.inf.prg3.a06;

import de.thro.inf.prg3.a06.model.Joke;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args)
	{
		// TODO fetch a random joke and print it to STDOUT
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://api.icndb.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		ICNDBApi api = retrofit.create(ICNDBApi.class);

		Call<Joke> joke = api.getRandomJoke();
		//Call<List<Repo>> repos = service.listRepos("octocat");
		System.out.println(joke);
	}

}
