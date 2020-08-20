package com.timplant.converters;

import com.timplant.models.Transaction;
import org.apache.johnzon.mapper.*;

import javax.json.JsonValue;
import java.lang.reflect.Type;

public class TransactionJSONConverter<T extends Transaction> implements ObjectConverter.Codec<T>{

    @Override
    public T fromJson(JsonValue jsonValue, Type type, MappingParser mappingParser) {
        return mappingParser.readObject(jsonValue, type);
    }

    @Override
    public void writeJson(Transaction t, MappingGenerator mappingGenerator) {
       mappingGenerator.writeObject(t, mappingGenerator.getJsonGenerator());
    }
}
