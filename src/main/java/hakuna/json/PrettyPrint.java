package hakuna.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class PrettyPrint {

    public static void main(String[] args) throws IOException {
        JsonFactory jf = new JsonFactory();
        try (JsonParser p = args.length > 0 ? jf.createParser(new File(args[0])) : jf.createParser(System.in);
                JsonGenerator g = args.length > 1 ? jf.createGenerator(new File(args[1]), JsonEncoding.UTF8) : jf.createGenerator(System.out)) {
            prettyPrint(p, g);
        }
    }

    public static void prettyPrint(JsonParser p, JsonGenerator g) throws IOException {
        g.setPrettyPrinter(new DefaultPrettyPrinter());
        JsonToken token;
        while ((token = p.nextToken()) != null) {
            switch (token) {
            case START_ARRAY:
                g.writeStartArray();
                break;
            case END_ARRAY:
                g.writeEndArray();
                break;
            case START_OBJECT:
                g.writeStartObject();
                break;
            case END_OBJECT:
                g.writeEndObject();
                break;
            case FIELD_NAME:
                g.writeFieldName(p.getCurrentName());
                break;
            case VALUE_FALSE:
                g.writeBoolean(false);
                break;
            case VALUE_TRUE:
                g.writeBoolean(true);
                break;
            case VALUE_NULL:
                g.writeNull();
                break;
            case VALUE_NUMBER_FLOAT:
                g.writeNumber(p.getDecimalValue());
                break;
            case VALUE_NUMBER_INT:
                g.writeNumber(p.getBigIntegerValue());
                break;
            case VALUE_STRING:
                g.writeString(p.getText());
                break;
            case NOT_AVAILABLE:
            case VALUE_EMBEDDED_OBJECT:
                break;
            }
        }
    }

}
