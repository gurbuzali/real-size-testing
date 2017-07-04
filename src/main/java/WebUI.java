/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.gson.Gson;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * todo add proper javadoc
 */
public class WebUI {

    static String s = "[\n" +
            "    \"i-041f4d39a98db7f9a\", \n" +
            "    \"i-04afca0f54a3e8ee6\"\n" +
            "]";

    static String ss = "[\n" +
            "    [\n" +
            "        [\n" +
            "            \"10.0.0.127\"\n" +
            "        ], \n" +
            "        [\n" +
            "            \"10.0.0.19\"\n" +
            "        ]\n" +
            "    ]\n" +
            "]";

    public static void parseInstanceIds(String[] args) throws Exception {
        Gson gson = new Gson();
        List<String> list = gson.<List<String>>fromJson(s, List.class);
    }

    public static void parseIp(String ss) throws Exception {
        Gson gson = new Gson();
        List<List<String>> list = (List<List<String>>)gson.fromJson(ss, List.class).get(0);
        final StringBuilder builder = new StringBuilder();
        list.forEach(l -> {
            builder.append(l.get(0));
            builder.append("\n");
        });
        System.out.println(builder.toString());
    }

    public static void test(String[] args) throws Exception {
        Server server = new Server(8080);
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler.setResourceBase(".");

        server.setHandler(resourceHandler);

//        server.setHandler(new AbstractHandler() {
//            @Override public void handle(String target,
//                                         Request baseRequest,
//                                         HttpServletRequest request,
//                                         HttpServletResponse response) throws IOException, ServletException {
//                response.setContentType("text/html;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_OK);
//                baseRequest.setHandled(true);
//                if (target.equals("/")) {
//                    response.getWriter().println("<h1>Hello</h1>");
//                }
//                else if (target.equals("/populate")) {
//                    String sourceName = request.getParameter("sourceName");
//                    response.getWriter().println(sourceName + " map populated!!!");
//                } else if (target.equals("/wordCount")) {
//                    String sourceName = request.getParameter("sourceName");
//                    String sinkName = request.getParameter("sinkName");
//                } else if (target.equals("/jusWordCount")) {
//                    String sourceName = request.getParameter("sourceName");
//                    String sinkName = request.getParameter("sinkName");
//                }
//            }
//        });
        server.start();
        server.dumpStdErr();
        server.join();
    }

}
