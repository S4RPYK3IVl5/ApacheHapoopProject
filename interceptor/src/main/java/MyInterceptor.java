import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class MyInterceptor implements Interceptor {

    public void initialize() {

    }

    public Event intercept(Event event) {

        String s = new String(event.getBody());
        String[] split = s.split(",");

        Map<String, String> map = event.getHeaders();
        map.put("timestamp", split[4]);
        event.setHeaders(map);

        return event;

    }

    public List<Event> intercept(List<Event> list) {
        for (Event event : list)
            intercept(event);
        return list;
    }

    public void close() {

    }

    public static class Builder implements Interceptor.Builder
    {
        @Override
        public void configure(Context context) {
        }

        @Override
        public Interceptor build() {
            return new MyInterceptor();
        }
    }

}
