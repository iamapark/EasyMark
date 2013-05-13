<%@page import="java.io.*,java.net.*" contentType="text/json; charset=UTF-8"%><%

		String paramUrl = request.getParameter("url");
        StringBuffer sbf = new StringBuffer();
        //Access the page
        try {
                URL url = new URL(paramUrl);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine;
                while ( (inputLine = in.readLine()) != null) sbf.append(inputLine);
                in.close();
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
%><%= sbf.toString() %>