import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyInterceptor implements Interceptor {

    public void initialize() {

    }

    public Event intercept(Event event) {
        Map<String, String> map = event.getHeaders();
//        String date = map.get("timestamp");
        String s = new String(event.getBody());
        String[] split = s.split(",");
//        Timestamp timestamp = new Timestamp(Long.parseLong(date));
        map.put("timestamp", split[2]);
        event.setHeaders(map);
//        event.setBody(map.get("timestamp").getBytes());
//        event.setHeaders(map);
//        event.setBody(Arrays.toString(split).getBytes());
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
