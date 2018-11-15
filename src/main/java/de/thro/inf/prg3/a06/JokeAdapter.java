package de.thro.inf.prg3.a06;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.thro.inf.prg3.a06.model.Joke;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class JokeAdapter<T extends Joke> extends TypeAdapter<T>
{
	public void write(JsonWriter out, T jk) throws IOException {
		// Note: There is no need to implement the write method,
		// since we're only consuming the API, but not sending to it.
		out.beginObject();
		out.name("id").value(jk.getNumber());
		out.name("title").value(jk.getContent());
		out.name("authors").value(StringUtils.join(jk.getRubrics(), ";"));
		out.endObject();
	}

	@Override
	public T read(JsonReader in) throws IOException {
		final Joke jk = new Joke();

		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
				case "id":
					jk.setNumber(in.nextInt());
					break;
				case "joke":
					jk.setContent(in.nextString());
					break;
				case "categories":
					jk.setRubrics(in.nextString().split(";"));
					break;
			}
		}
		in.endObject();

		return (T) jk;
	}
}
