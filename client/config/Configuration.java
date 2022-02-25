package client.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class Configuration
{
    private File file;
    public Map<String, Object> options;

    public Configuration(File file, Map<String, Object> options)
    {
        this.file = file;
        this.options = options;
    }

    public Configuration(File file)
    {
        this.file = file;
        this.options = new HashMap();
    }

    public Object get(String key)
    {
        return this.options.get(key);
    }

    public void set(String key, Object value)
    {
        this.options.put(key, value);
    }

    public void save() throws IOException
    {
        JSONObject jsonobject = new JSONObject(this.options);
        this.file.createNewFile();
        FileWriter filewriter = new FileWriter(this.file);
        filewriter.write(jsonobject.toString());
        filewriter.flush();
        filewriter.close();
    }
}
