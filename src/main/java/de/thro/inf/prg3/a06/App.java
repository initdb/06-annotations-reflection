package de.thro.inf.prg3.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.thro.inf.prg3.a06.model.Joke;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App
{
	private static final int MAX_REQUESTS = 10;

	public static void main(String[] args) throws IOException
	{
		// TODO fetch a random joke and print it to STDOUT
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(Joke.class, new JokeAdapter())
			.registerTypeAdapter(Joke[].class, new JokeArrayAdapter())
			.create();

		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl("http://api.icndb.com/")
			.build();

		ICNDBApi api = retrofit.create(ICNDBApi.class);
		int requests = 0;

		while(requests++ < MAX_REQUESTS) {
			Call<Joke> jokeCall = api.getRandomJoke();
			Response<Joke> jokeResponse = jokeCall.execute();

			Joke jk = jokeResponse.body();

			System.out.println(jk.toString());
		}
	}

}
