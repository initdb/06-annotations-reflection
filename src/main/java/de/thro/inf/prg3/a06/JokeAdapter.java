package de.thro.inf.prg3.a06;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.thro.inf.prg3.a06.model.Joke;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class JokeAdapter extends TypeAdapter<Joke>
{
	private final Gson gson;

	public JokeAdapter() {
		gson = new Gson();
	}

	public void write(JsonWriter out, Joke jk) throws IOException
	{
		// Note: There is no need to implement the write method,
		// since we're only consuming the API, but not sending to it.
		out.beginObject();
		out.name("id").value(jk.getNumber());
		out.name("title").value(jk.getContent());
		out.name("authors").value(StringUtils.join(jk.getRubrics(), ";"));
		out.endObject();
	}

	@Override
	public Joke read(JsonReader in) throws IOException
	{
		Joke jk = new Joke();

		in.beginObject();
		while (in.hasNext())
		{
			switch (in.nextName())
			{
				case "type":
					if (!in.nextString().equals("success"))
					{
						throw new IOException();
					}
					break;
				/* serialize the inner value simply by calling Gson because we mapped the fields to JSON keys */
				case "value":
					jk = gson.fromJson(in, Joke.class);
					break;
			}
		}
		in.endObject();

		return jk;
	}
}
